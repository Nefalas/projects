var socket = io.connect();

$(function() {

  var $userFormArea = $('#userFormArea');
  var $messageArea  = $('#messageArea');
  var $newUserArea  = $('#newUserArea');
  var $users        = $('#users');

  var $userForm     = $('#userForm');
  var $username     = $('#username');
  var $password     = $('#password');
  var $key          = $('#key');

  var $newUserForm  = $('#newUserForm');
  var $newEmail     = $('#email');

  var $messageForm  = $('#messageForm');
  var $message      = $('#message');
  var $chat         = $('#chat');
  var $saveMessage  = $('#saveMessage');
  var $expiresIn    = $('#messageExpiresIn');

  var $adminControl = $('#adminControl');

  var key, currentUsername;

  var fps = 24;
  var msPerFrame = 1000/fps;
  var webcam = document.getElementById('webcam');
  var canvas = document.getElementById('canvas');
  var context = canvas.getContext('2d');
  var vendorUrl = window.URL || window.webkitURL;

  navigator.getMedia = navigator.getUserMedia
    || navigator.webkitGetUserMedia
    || navigator.mozGetUserMedia
    || navigator.msGetUserMedia;

  function getMedia() {
    navigator.getMedia({
      video: true,
      audio: false
    }, function(stream) {
      webcam.src = vendorUrl.createObjectURL(stream);
      webcam.play();
    }, function(error) {
      console.log('Could not get media: ' + error);
    })
  }

  $('#toggleWebcam').click(getMedia);

  webcam.addEventListener('play', function() {
    draw(this, context);
  }, false);

  function draw(video, context) {
    var width = webcam.videoWidth;
    var height = webcam.videoHeight;
    context.canvas.width = width;
    context.canvas.height = height;
    context.drawImage(video, 0, 0, width, height);
    if (width && height) {
      var image = context.getImageData(0, 0, width, height);
      image.data = transformImageData(image.data);
      //sendImageData(image.data);
      context.putImageData(image, 0, 0);
    }
    setTimeout(draw, msPerFrame, video, context);
  }

  function sendImageData(imageData) {
    socket.emit('image data', {
      username: currentUsername,
      imageData: imageData
    })
  }

  function transformImageData(data) {
    var i, r, g, b, a;
    for (i = 0; i < data.length; i = i+4) {
      r = data[i];
      g = data[i+1];
      b = data[i+2];
      a = data[i+3];
      data[i]   = apply(r);
      data[i+1] = apply(g);
      data[i+2] = apply(b);
      data[i+3] = a;
    }
    return data;
  }

  function apply(x) {
    return x;
  }

  loadStyleSheet();

  $userForm.submit((e) => {
    e.preventDefault();
    if ($username.val() && $key.val() && $password.val()) {
      socket.emit('protected username', {
        username: $username.val(),
        password: $password.val()
      });
    } else if ($username.val() && $key.val()) {
      socket.emit('new user', $username.val());
    }
  });

  $messageForm.submit(handleSubmit).keydown((e) => {
    if (e.keyCode == 13) {
      handleSubmit(e);
    }
  });

  function handleSubmit(e) {
    e.preventDefault();
    if ($message.val()) {
      var encrypted = CryptoJS.AES.encrypt($message.val(), key);
      socket.emit('send message', {
        message: encrypted.toString(),
        save: $saveMessage.prop('checked'),
        expiresIn: $expiresIn.val()
      });
      $message.val('');
    }
  }

  $newUserForm.submit((e) => {
    e.preventDefault();
    if ($newEmail.val()) {
      socket.emit('send invitation', $newEmail.val());
      showOnly($messageArea);
    }
  });

  socket.on('invalid username', (data) => {
    $('#newUsernameError').show();
    $('#newUsernameGroup').addClass('has-error');
  });

  socket.on('user added', (data) =>{
    $('#newUsernameError').hide();
    $('#newUsernameGroup').removeClass('has-error');
    $newUsername.val('');
    $newPassword.val('');
    showOnly($messageArea);
  });

  socket.on('new message', (data) => {
    var decrypted;
    try {
      decrypted = CryptoJS.AES.decrypt(data.message, key)
        .toString(CryptoJS.enc.Utf8);
    } catch (err) {
      decrypted = data.message;
    }
    if (!decrypted) {
      decrypted = data.message;
    }
    $chat.append('<div><strong>' + data.username +
      '</strong>: ' + decrypted + '</div>');
  });

  socket.on('get users', (data) => {
    var html = "";
    for (i = 0; i < data.length; i++) {
      var classes = "list-group-item";
      if (data[i].admin || data[i].username == currentUsername) {
        classes += " special-user"
      }
      html += '<li class="' + classes + '">'
        + data[i].username;
      if (data[i].admin) {
        html += '<span class="glyphicon glyphicon-bookmark admin"'
          + 'aria-hidden="true"></span>'
      }
      html += '</li>'
    }
    $users.html(html);
  });

  socket.on('login accepted', (data) => {
    key = $key.val();
    currentUsername = $username.val();
    $username.val('');
    $key.val('');
    showOnly($messageArea);
  });

  socket.on('admin granted', (data) => {
    $adminControl.show();
  });

  socket.on('ask password', (data) => {
    $('#password-input').show();
  });

  $('#toggleNewUser').click(() => {
    showOnly($newUserArea);
  });

  $('#cancelNewUser').click((e) => {
    e.preventDefault();
    showOnly($messageArea);
  });

  function showOnly(element) {
    $userFormArea.hide();
    $messageArea.hide();
    $newUserArea.hide();
    element.show();
  }
});

function swapStyleSheet(sheet) {
  $('#stylesheet').attr('href', '/styles/' + sheet);
  saveStyleSheet(sheet);
}

function saveStyleSheet(sheet) {
  if (typeof(Storage !== "undefined")) {
    localStorage.setItem("CryptoChatStyleSheet", sheet);
  }
}

function loadStyleSheet() {
  if (localStorage.getItem("CryptoChatStyleSheet")) {
    swapStyleSheet(localStorage.getItem("CryptoChatStyleSheet"));
  }
}

function toggleFullScreen() {
  if ((document.fullScreenElement && document.fullScreenElement !== null) ||
    (!document.mozFullScreen && !document.webkitIsFullScreen)) {
    if (document.documentElement.requestFullScreen) {
      document.documentElement.requestFullScreen();
    } else if (document.documentElement.mozRequestFullScreen) {
      document.documentElement.mozRequestFullScreen();
    } else if (document.documentElement.webkitRequestFullScreen) {
      document.documentElement.webkitRequestFullScreen(
        Element.ALLOW_KEYBOARD_INPUT
      );
    }
  } else {
    if (document.cancelFullScreen) {
      document.cancelFullScreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
      document.webkitCancelFullScreen();
    }
  }
}
