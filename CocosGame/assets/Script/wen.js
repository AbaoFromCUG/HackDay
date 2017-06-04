cc.Class({
    extends: cc.Component,

    properties: {
        moveSpeed: 0,
        HP:0,
    },

    // use this for initialization
    onLoad: function () {
        var self=this;
        this.node.on(cc.Node.EventType.TOUCH_END,function(){
            self.HP-=Global.User_ATK;
            if(self.HP<=0){
                self.node.destroy();
                Global.gameStatus="begin";
            }
        });
         
             
             this.node.runAction(this.setRunAction())
         
    },
    setRunAction: function () {
        var action0 = cc.moveBy(0.5, cc.p( 0, - 200));      
        var action1=cc.moveBy(0.5,cc.p(-200,200));
        var action2=cc.moveBy(0.5,cc.p(200,200));
        var action3=cc.moveBy(0.5,cc.p(200,-200));
        var action4=cc.moveBy(0.5,cc.p(-200,0));

        return cc.repeatForever(cc.sequence(cc.sequence(cc.sequence(action0,action1),cc.sequence(action2,action3)), action4));
    },
    // called every frame, uncomment this function to activate update callback
     update: function (dt) {
        


     },
});
