cc.Class({
    extends: cc.Component,

    properties: {
        wen2Prefab:{
            type: cc.Prefab,
            default:null,
        },
        score:{
            type:cc.Label,
            default:null,
        },

    },

    // use this for initialization
    onLoad: function () {
        this.countTime=0;
        cc.log("wen2",Math.floor(Global.gameTime*10));
        //this.pawnWen();
        this.score.string="Score 0";
        Global.User_Score=0;
        this.pawnWen();

    },

    // called every frame, uncomment this function to activate update callback
     update: function (dt) {
         this.countTime+=dt;
         if(this.countTime>2){
            this.pawnWen();
            cc.log("wen2",Math.floor(Global.gameTime*10));
            this.countTime=0;
         }
         

    },
    pawnWen: function() {
        // 使用给定的模板在场景中生成一个新节点
        var newWen = cc.instantiate(this.wen2Prefab);
        // 将新增的节点添加到 Canvas 节点下面
        this.node.addChild(newWen);
        newWen.game=this;

        // 为星星设置一个位置
        newWen.setPosition(this.getNewWenPosition());
    },

    getNewWenPosition: function () {
        var randX=0;
        var randY=0;
        //尽可能的使得位置混乱
        if(Math.floor(this.countTime*10)%3===0){
            randX +=200;
        }else if(Math.floor(this.countTime*10)%3===1){
            randX-=200;
        }else{
            randX-=100;
        }


        if(Math.floor(this.countTime*100)%3===0){
            randX -=90;
        }else if(Math.floor(this.countTime*100)%3===1){
            randX+=50;
        }else{
            randX+=100;
        }
        
        if(Math.floor(this.countTime*10)%3===0){
            randY +=50;
        }else if(Math.floor(this.countTime*10)%3===1){
            randY +=200;
        }else{
            randY +=60;
        }
        if(Math.floor(this.countTime*100)%3===0){
            randY -=100;
        }else if(Math.floor(this.countTime*100)%3===1){
            randY -=200;
        }else{
            randY -=60;
        }
       
        return cc.p(randX, randY);
    }
});
