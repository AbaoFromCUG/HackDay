cc.Class({
    extends: cc.Component,

    properties: {
        // foo: {
        //    default: null,      // The default value will be used only when the component attaching
        //                           to a node for the first time
        //    url: cc.Texture2D,  // optional, default is typeof default
        //    serializable: true, // optional, default is true
        //    visible: true,      // optional, default is true
        //    displayName: 'Foo', // optional
        //    readonly: false,    // optional, default is false
        // },
        // ...
        wenPrefab:{
            type:cc.Prefab,
            default:null,
        },
        loadSprite:{
            type:cc.Sprite,
            default:null
        },
        game1SpriteFrame:{
            type:cc.SpriteFrame,
            default:null

        },
        game2SpriteFrame:{
            type: cc.SpriteFrame,
            default: null,

        },
        game3SpriteFrame:{
            type:cc.SpriteFrame,
            default:null
        },
        button:{
            type:cc.Node,
            default:null,
        }
    },
    buttonOnclick:function(){
        
    },
    // use this for initialization
    onLoad: function () {
        // 获取地平面的 y 轴坐标
        this.groundY = this.loadSprite.node;
        var self=this;
        this.button.on(cc.Node.EventType.TOUCH_END,function(){

                
                
                if(self.loadSprite.spriteFrame==self.game1SpriteFrame){
                    self.button.x=0;
                    self.loadSprite.spriteFrame=self.game3SpriteFrame;
                    self.pawnWen();
                    self.button.x=-10000;
                }else if(self.loadSprite.spriteFrame==self.game2SpriteFrame){
                    cc.director.loadScene("home");
                }
                
           
        });
        this.loadSprite.node.on(cc.Node.EventType.TOUCH_END,function(){
            if(self.loadSprite.spriteFrame==self.game3SpriteFrame&&Global.gameStatus=="begin"){
                self.loadSprite.spriteFrame=self.game2SpriteFrame;
                self.button.x=0;
                
            }
        })
        
    },

    // called every frame, uncomment this function to activate update callback
    // update: function (dt) {

    // },
    pawnWen: function() {
        // 使用给定的模板在场景中生成一个新节点
        var newWen = cc.instantiate(this.wenPrefab);
        // 将新增的节点添加到 Canvas 节点下面
        this.node.addChild(newWen);

        // 为星星设置一个位置
        newWen.getComponent('wen').load = this;
        newWen.setPosition(this.getNewWenPosition());
    },

    getNewWenPosition: function () {
        var randX = 0;
        var randY =0;
       
        return cc.p(randX, randY);
    }
});
