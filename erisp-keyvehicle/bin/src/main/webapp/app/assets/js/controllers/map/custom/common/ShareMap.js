/**
 * 调用地图的公共方法 使用方法： 1.在需要的模块引入依赖 'mapInit','mainCss'文件（config.router.js）
 * 2.在对应的ctroller模块，引入ShareMap模块 3.新建ShareMap对象，调用需要的方法即可使用 例子见 通知模块 createTime
 * 2016/8/8
 */
define([ "dojo/_base/declare", "esri/Map", "esri/views/SceneView",
		"esri/widgets/Home", "esri/layers/GraphicsLayer", "esri/Graphic",
		"esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol",
		"esri/symbols/PictureMarkerSymbol", "esri/PopupTemplate",
		"esri/geometry/Polyline","esri/geometry/Polygon","esri/symbols/SimpleLineSymbol",
		"esri/symbols/SimpleFillSymbol",
		"dojo/domReady!" ], function(declare, Map, SceneView, Home,
		GraphicsLayer, Graphic, Point, SimpleMarkerSymbol, PictureMarkerSymbol,
		PopupTemplate,Polyline,Polygon,SimpleLineSymbol,SimpleFillSymbol) {
	return declare("ShareMap", null, {
		constructor : function() {

		},
		/**
		 * 初始化地图
		 * 
		 * @param container
		 *            div的id
		 * @param longitude
		 *            定位中心经度
		 * @param latitude
		 *            定位中心纬度
		 * @return result map:地图对象，view:三维地图对象
		 */
		_initMap : function(container, longitude, latitude) {
			var result, map, view;
			map = new Map({
				basemap : 'streets'
			});
			view = new SceneView({
				container : container,
				map : map,
				center : [ longitude, latitude, 30 ],
				scale : 50000000,
				zoom : 10,
				camera : {
					position : {
						x : longitude - 0.013,
						y : latitude - 0.04,
						z : 1266.7049653716385,
						longitude : longitude - 0.013,
						latitude : latitude - 0.04,
						spatialReference : 3857
					},
					heading : 0.34445102566290225,
					tilt : 64.9
				}
			});
			result = {
				map : map,
				view : view
			};
			// 添加回到原点
			var homeBtn = new Home({
				view : view
			}, "homediv");
			homeBtn.startup();
			view.ui.add(homeBtn, "top-left");
			return result;
		},

		/**
		 * 新增图层到地图上
		 * 
		 * @param map
		 *            地图对象
		 * @param index
		 *            图层需要显示的层级
		 * @returns graphicLayer 新增的图层对象
		 */
		_addGraphicLayer : function(map, index) {
			var graphicLayer;
			try {
				graphicLayer = new GraphicsLayer();
				map.add(graphicLayer, index);

			} catch (e) {
				console.error(e);
			}
			return graphicLayer;
		},

		/**
		 * @param graphicLayer
		 *            图层
		 * @param x
		 *            经度
		 * @param y
		 *            纬度
		 * @param z
		 *            海拔
		 * @param fillColor
		 *            点的填充颜色 格式：[R,G,B]
		 * @param outlineColor
		 *            点的外圈颜色 格式：[R,G,B]
		 * @param outlineWidth
		 *            点外圈线的宽度
		 * @return graphic 点要素对象
		 */
		_addPoint : function(graphicLayer, x, y, z, fillColor, outlineColor,
				outlineWidth) {
			try {
				var graphic;
				var point = new Point({
					x : x,
					y : y,
					z : z
				});
				var symbole = new SimpleMarkerSymbol({
					color : fillColor,
					outline : {
						color : outlineColor,
						width : outlineWidth
					}
				});
				var popupTemplates = new PopupTemplate({
					title : "信息",
					content : "经度：" + x + ",纬度：" + y + "海拔:" + z
				});
				graphic = new Graphic({
					geometry : point,
					symbol : symbole,
					popupTemplate : popupTemplates
				});
				graphicLayer.add(graphic);
			} catch (e) {
				console.error(e);
			}
			return graphic;
		},
		/**
		 * 添加带图片的点
		 * 
		 * @param graphicLayer
		 *            图层
		 * @param x
		 *            经度
		 * @param y
		 *            纬度
		 * @param z
		 *            海拔
		 * @param url
		 *            图片的路径
		 * @param width
		 *            图片宽
		 * @param height
		 *            图片长
		 * @param popTile
		 * @param popContent
		 */
		_addPointPicture : function(graphicLayer, x, y, z, url, width, height,
				popTile, popContent) {
			try {
				var graphic;
				var point = new Point({
					x : x,
					y : y,
					z : z
				});
				var symbole = new PictureMarkerSymbol({
					url : url,
					width : width,
					height : height
				});
				var popupTemplates = new PopupTemplate({
					title : "信息",
					content : "<tr>经度：" + x + ",<br>纬度：" + y + ",<br>海拔:" + z
							+ "</tr>"
				});
				graphic = new Graphic({
					geometry : point,
					symbol : symbole,
					popupTemplate : popupTemplates
				});
				graphicLayer.add(graphic);
			} catch (e) {
				console.error(e);
			}
			return graphic;
		},

		/**
		 * 移除单个点
		 * 
		 * @param graphicLayer
		 * @param graphic
		 */
		_removePoint : function(graphicLayer, graphic) {
			try {
				graphicLayer.remove(graphic);
			} catch (e) {
				console.error(e);
			}
		},

		/**
		 * 画线
		 * 
		 * @param graphicLayer
		 *            图层对象
		 * @param paths
		 *            点的集合 [[x1,y1,z1],[x2,y2,z2],...]
		 * @param lineColor线的颜色
		 * @param lineWidth线宽
		 * @param popTile
		 *            弹出框标题
		 * @param popContent
		 *            弹出框内容
		 */
		_addPolyline : function(graphicLayer, paths, lineColor, lineWidth,
				popTile, popContent) {
			var graphic;
			try {
				var polyline = new Polyline(paths);
				var lineSymbol = new SimpleLineSymbol({
					color : lineColor || "red",
					width : lineWidth || 4
				});
				var popupTemplates = new PopupTemplate({
					title : popTile || "信息",
					content : popContent || "线"
				});
				graphic = new Graphic({
					geometry : polyline,
					symbol : lineSymbol,
					popupTemplate : popupTemplates
				});
				graphicLayer.add(graphic);

			} catch (e) {
				console.error(e);
			}
			return graphic;
		},

		/**
		 * 画面
		 * 
		 * @param graphicLayer
		 *            图层
		 * @param rings
		 *            点集合 [[x1,y1,z1],[x2,y2,z2],...]
		 * @param fillColor
		 *            填充颜色[R,G,B,alph(透明度)]
		 * @param outlineColor
		 *            外面线的颜色
		 * @param outlineWidth
		 *            外面线的宽度
		 * @param popTile
		 *            弹出框标题
		 * @param popContent
		 *            弹出框内容
		 */
		_addPolygon : function(graphicLayer, rings, fillColor, outlineColor,
				outlineWidth, popTile, popContent) {
			var graphic;
			try {
				var polygon = new Polygon(rings);
				var fillSymbol = new SimpleFillSymbol({
					color : fillColor || [ 0, 249, 255, 0.9 ],
					outline : {
						color : outlineColor || "red",
						width : outlineWidth || 1
					}
				});
				var popupTemplates = new PopupTemplate({
					title : popTile || "标题",
					content : popContent || "面"
				});
				graphic = new Graphic({
					geometry : polygon,
					symbol : fillSymbol,
					popupTemplate : popupTemplates
				});
				graphicLayer.add(graphic);
			} catch (e) {
				console.error(e);
			}
			return graphic;
		},

		/**
		 * 多个点加载在地图上(这一组数据是在一个图层上的)
		 * 
		 * @param map
		 *            地图
		 * @param paths
		 *            点集合[[x1,y1,z1],[x2,y2,z2],...]
		 * @param url
		 *            图片的url
		 * @param width
		 *            图片宽度
		 * @param height
		 *            图片高度
		 */
		_addManyPoint : function(map, paths, url, width, height) {
			var graphicLayer = new GraphicsLayer();
			try {
				map.add(graphicLayer);
				for (var i = 0; i < paths.length; i++) {
					this._addPointPicture(graphicLayer, paths[i][0], paths[i][1],
							paths[i][2], url, width, height);
				}
			} catch (e) {
				console.error(e);
			}
			return graphicLayer;
		},

		/**
		 * 多条线加载在地图上
		 * 
		 * @param map
		 * @param paths
		 *            [[[x1,y1,z1],[x2,y2,z2],...],[[x1,y1,z1],[x2,y2,z2],...]]
		 * @param lineColor
		 * @param lineWidth
		 * @param popTile
		 * @param popContent
		 */
		_addManyPolyline : function(map, paths, lineColor, lineWidth, popTile,
				popContent) {
			var graphicLayer = new GraphicsLayer();
			try {
				map.add(graphicLayer);
				for (var i = 0; i < paths.length; i++) {
					this._addPolyline(graphicLayer, paths[i], lineColor, lineWidth,
							popTile, popContent);
				}
			} catch (e) {
				console.error(e);
			}
			return graphicLayer;
		},
		/**
		 * 多个面加载在地图上
		 * 
		 * @param map
		 *            地图
		 * @param rings
		 *            面信息数组
		 *            [[[x1,y1,z1],[x2,y2,z2],...],[[x1,y1,z1],[x2,y2,z2],...]]
		 * @param fillColor
		 *            填充颜色
		 * @param outlineColor
		 *            外面线的颜色
		 * @param outlineWidth
		 *            线的宽度
		 * @param popTile
		 *            弹出框标题
		 * @param popContent
		 *            弹出框内容
		 */
		_addManyPolygon : function(map, rings, fillColor, outlineColor,
				outlineWidth, popTile, popContent) {
			var graphicLayer = new GraphicsLayer();
			try {
				map.add(graphicLayer);
				for (var i = 0; i < rings.length; i++) {
					this._addPolygon(graphicLayer, rings[i], fillColor,
							outlineColor, outlineWidth, popTile, popContent)
				}
			} catch (e) {
				console.error(e);
			}
			return graphicLayer;
		},

	/**
	 * 移除图层
	 * @param map 地图
	 * @param layer 图层
	 */
	_removeLayer : function(map,layer){
		var  layerDel;
		try {
			layerDel = map.remove(layer);
		} catch (e) {
			console.error(e);
		}
		return layerDel;
	},

	/**
	 * 移除所有的图层
	 * @param map 地图
	 * @param layer 图层
	 */
	_removeAllLayer:function(){
		var  layersDel;
		try {
			layersDel = map.removeAll();
		} catch (e) {
			console.error(e);
		}
		return layersDel;
	}
	});
});