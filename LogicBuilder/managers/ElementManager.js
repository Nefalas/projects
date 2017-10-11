ElementManager = function(basicComponentManager, wireManager) {
    this.basicComponentManager = basicComponentManager;
    this.wireManager = wireManager;
};

ElementManager.prototype.getElement = function(x, y) {
    var basicComponent = this.basicComponentManager.getComponent(x, y);
    if (basicComponent !== undefined) {
        return {
            element: basicComponent,
            type: "basicComponent"
        };
    }
    var wire = this.wireManager.getWire(x, y);
    if (wire !== undefined) {
        var point = wire.getPoint(x, y);
        return {
            element: point,
            type: "point"
        };
    }

    return undefined;
};

ElementManager.prototype.inform = function(x, y) {
    var elementInfo = this.getElement(x, y);
    if (elementInfo !== undefined) {
        var type = elementInfo.type;
        var element = elementInfo.element;

        console.log(type + " at x: " + x + ", y: " + y);
        console.log("hasSource: " + element.hasSource + ", hasGround: " + element.hasGround);
        console.log("active: " + element.active);
        console.log(" ");
    } else {
        console.log("Empty cell");
        console.log(" ");
    }
};