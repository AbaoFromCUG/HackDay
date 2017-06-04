cc.Class({
    extends: cc.Component,

    properties: {
        FirstButton:{
            type:cc.Node,
            default:null,

        },
        SecondButton:{
            type:cc.Node,
            default:null
        },
        ThirdButthon:{
            type:cc.Node,
            default:null
        },
        
    },
    FirstButtonOnclick: function(){
        cc.director.loadScene("game");
    },
    // use this for initialization
    onLoad: function () {
        var self=this;
        this.FirstButton.on(cc.Node.EventType.TOUCH_END,self.FirstButtonOnclick);
        
    },

    // called every frame, uncomment this function to activate update callback
    // update: function (dt) {

    // },
});
