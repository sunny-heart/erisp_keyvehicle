/**
*图例
*一般应用于featureLayer
*createTime 2016/8/4
*/
define(["dojo/_base/declare",  
        "esri/widgets/Legend",
        "dojo/domReady!"], function (declare,Legend) {  
        return declare("LegendWidget",null,{
        	_view:null,
        	_featureLayer:null,
        	_tile:"Legend",
        	_legend:null,
            constructor: function (view,featureLayer,tile) {  
                this.view = view;
                this.featureLayer = featureLayer;
                this.tile = tile;
            },
            //显示图例
        	_showLegend:function(){
        		 _legend = new Legend({
  	 			  view: this.view,
  	 			  layerInfos: [{
  	 			    layer: this.featureLayer,
  	 			    title: this.tile
  	 			  }],
  	 			});//,"legendW"表示图例显示在什么地方，自定义div的id
        		_legend.startup();
  	 			this.view.ui.add(_legend, "top-right");
        	},
        	//销毁图例
            _destroyLegend:function(){
            	if(_legend){
            		_legend.destroy();
            	}
            }
        });  
    });  