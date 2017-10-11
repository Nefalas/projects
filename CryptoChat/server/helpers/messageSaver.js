var fs = require('fs');
var path = require('path');

var storagePath = path.join(__dirname, '..', 'storage', 'savedMessages.json');
var encoding = 'utf8';

MessageSaver = function() {
  this.savedMessages = [];
  this.fillList();

  var self = this;
  var removeOldInterval = setInterval(function() {
    self.removeOldMessages();
  }, 5000);
}

MessageSaver.prototype.fillList = function() {
  this.savedMessages = JSON.parse(fs.readFileSync(storagePath, encoding));
  return this.savedMessages;
}

MessageSaver.prototype.addMessage = function(message, username, expiresIn) {
  this.fillList();
  var newList = this.savedMessages;
  newList.push(
    {
      message: message,
      username: username,
      postedAt: (new Date()).getTime(),
      expiresAt: (new Date()).getTime() + expiresIn*60000
    }
  )
  this.savedMessages = newList;
  this.saveToStorage();
  this.removeOldMessages();
}

MessageSaver.prototype.removeMessage = function(index) {
  this.savedMessages.splice(index, 1);
  if (!this.savedMessages) {
    this.savedMessages = [];
  }
  this.saveToStorage();
}

MessageSaver.prototype.removeAllMessages = function() {
  fs.writeFile(storagePath, '[]', encoding, (err) => {
    if (err) {
      console.log('Could not remove messages: ' + err);
    }
  })
}

MessageSaver.prototype.removeOldMessages = function() {
  var savedMessages = this.fillList();
  if (savedMessages.length) {
    var currentTime = (new Date()).getTime();
    for (var i = savedMessages.length-1; i > -1; i--) {
      if (savedMessages[i].expiresAt <= currentTime) {
        this.removeMessage(i);
        console.log("Removed old message (" + i + ")");
      }
    }
  }
}

MessageSaver.prototype.saveToStorage = function() {
  fs.writeFileSync(storagePath, JSON.stringify(this.savedMessages), encoding);
}

module.exports = MessageSaver;
