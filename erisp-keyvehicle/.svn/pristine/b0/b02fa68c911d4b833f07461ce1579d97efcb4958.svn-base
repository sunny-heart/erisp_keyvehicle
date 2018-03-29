app.controller('focusMapController', [
		'$scope',
		'$filter',
		'$timeout',
		'$state',
		'focusMapService',
		'$rootScope',
		'SweetAlert',
		'$interval',
		'$modal',
		'$stateParams',
		'$http',
		function($scope, $filter, $timeout, $state, focusMapService, $rootScope, SweetAlert, $interval, $modal, $stateParams, $http) {
			
	var map;	//地图
	var showZoom;//分局显示不同层级
    var showCenterPoint;//分局显示不同中心点
	var layerOfVehicle;//车标图层
    var layerOfText;//车牌号图层
	var layerOfArea;//区域图层
	var mapZoom = 8;
 	$scope.ringRoad = ringRoad;//环路和重点区域数据
    
	/**********************************地图图层显示start**********************************/
    $scope.showMap = function(){
    	require([  
            "esri/map", 
            "esri/layers/ArcGISTiledMapServiceLayer", 
            "esri/geometry/Extent", 
            "esri/geometry/Point", 
            "esri/geometry/Polyline", 
            "esri/SpatialReference",  
            "esri/symbols/SimpleMarkerSymbol",
            "esri/symbols/PictureMarkerSymbol", 
            "esri/symbols/SimpleLineSymbol", 
            "esri/layers/GraphicsLayer", 
            "esri/symbols/SimpleFillSymbol", 
            "esri/lang",
            "esri/Color", 
            "dojo/dom-style",
            "dijit/TooltipDialog", 
            "dijit/popup", 
            "esri/graphic"  
        ], function(
        		Map, 
        		ArcGISTiledMapServiceLayer, 
        		Extent, 
        		Point, 
        		Polyline, 
        		SpatialReference, 
        		SimpleMarkerSymbol,
        		PictureMarkerSymbol, 
        		SimpleLineSymbol, 
        		GraphicsLayer, 
        		SimpleFillSymbol, 
                esriLang,
                Color, 
                domStyle,
                TooltipDialog, 
                dijitPopup,
        		Graphic) {  
            // BASE_SERVER配置移至app.js
            map = new Map("focusMap", {  
//                center: [104.0657754083, 30.6583098090],  
//              zoom: 4,//内网地图缩放等级
                center: showCenterPoint,  
                zoom: showZoom,//内网地图缩放等级
                logo:false,
                nav:false,
                slider:false
            }); 
    		var layer = new ArcGISTiledMapServiceLayer(BASE_SERVER);
            map.addLayer(layer);
            //内网分局图层--勿删
    		var layer1 = new esri.layers.ArcGISDynamicMapServiceLayer("http://20.0.56.14:8399/arcgis/rest/services/cd_transperant/MapServer");
    		layer1.setVisibleLayers([0,1,2]);//设置图层显示
    		layer1.setOpacity(0.5);//设置图层透明度
            map.addLayer(layer1);
		    layerOfVehicle = new esri.layers.GraphicsLayer();
			map.addLayer(layerOfVehicle);
			layerOfText = new esri.layers.GraphicsLayer();
			map.addLayer(layerOfText);
			layerOfText.setVisibility(false);
			layerOfArea = new esri.layers.GraphicsLayer();
			map.addLayer(layerOfArea);
			
			//map.infoWindow.resize(60,280);
			layerOfArea.on("mouse-over",function(e){
				var id = e.graphic.attributes.warningRuleId;
				focusMapService.queryWarningRuleList({id : id},function (data) {
					if (data.state == 200) {
						$scope.warningRuleList = data.messageBody.warningRuleList;
						console.log($scope.warningRuleList.length);
						var warningRule = $scope.warningRuleList[0];
						console.log(warningRule.name);
						var content = warningRule.name;
						var tooltipDialog = new dijit.TooltipDialog({
							content: content,
							style: "background: rgba(0, 204, 255, 0.6); overflow-x:scroll; overflow-x:visible; width: 220px; height: 50px;border:3px solid #CCCCCC; line-height: 3; z-index: 100;"
						});
						dijit.popup.open({
							popup: tooltipDialog,
							x: 200,
							y: 850
						});
						tooltipDialog.startup();
					}
					
				}, function (err) {
				})
			});
			
			layerOfArea.on("mouse-out",function(e){
				$("div.dijitPopup").attr("style","display:none");
			});
			
			//关闭dialog
			$("body").on("click","#closeDialog",function(){
				$("div.dijitPopup").attr("style","display:none");
			});
			
			map.on("zoom",function(e){
				if(map.getZoom()>mapZoom){
					layerOfText.setVisibility(true);
				}else{
					layerOfText.setVisibility(false);
				}
			})

            map.on("load", function () {
                // $scope.drawSelectArea([$scope.ringRoad.tianfuguangchang]);
                // $scope.drawSelectArea([$scope.ringRoad.renmingzhengfu]);
				var pt;
				var textgraph;
				if ($scope.busFocusWarnRefresh == undefined){
					$scope.busFocusWarnRefresh = $interval(function() {
						$scope.getBusFocusWarning(function (data) {
							layerOfArea.clear();
							layerOfVehicle.clear();
							layerOfText.clear();
							$.each(data, function (i,e) {
								var points = [];
								for (var i=0;i<e.area.length;i++){
									points.push([e.area[i].x,e.area[i].y]);
								}
								var totalVehicleCounts = e.gpsDatas.length;
								var warningRuleId = e.warningRuleId;
								if (e.state == 0){
									$scope.drawSelectArea([points], 0, totalVehicleCounts, warningRuleId);
								} else if(e.state == 2){
									$scope.drawSelectArea([points], 2, totalVehicleCounts, warningRuleId);
								} else {
									$scope.drawSelectArea([points], 1, totalVehicleCounts, warningRuleId);
								}
								
								//加点
								var datas = e.gpsDatas;
								for(var g=0;g<datas.length;g++){
									pt = new esri.geometry.Point(datas[g].longitude, datas[g].latitude);
									pic = new esri.symbol.PictureMarkerSymbol("assets/images/taxi/taxi-90.png", imageSize, imageSize);
									attr = {"plateNumber": datas[g].plateNumber};
									var gp = new esri.Graphic(pt, pic, attr);
									layerOfVehicle.add(gp);
									
									textgraph = new esri.Graphic(pt,new esri.symbol.TextSymbol(datas[g].plateNumber).setOffset(20, 10));
									layerOfText.add(textgraph);
									
								}
							});
						});
					},5000, 1000);
				}
			});

            // 向地图中添加绘制的图形数据
            $scope.addToMap = function(geometry, warningState, totalVehicleCounts, warningRuleId){
                //针对带状区域特殊处理，此处有较强的逻辑性，
                for (var graphic in layerOfArea.graphics){
                    if (layerOfArea.graphics[graphic].geometry.rings !== undefined
                        && JSON.stringify(layerOfArea.graphics[graphic].geometry.rings) === JSON.stringify(geometry.rings)){
                        layerOfArea.remove(layerOfArea.graphics[graphic]);
                        return;
                    }
                }
                var fillColor = [0,255,0,0.2];
				var borderColor = [0,255,0,0.5];
                if (warningState == 2){
                    fillColor = [255,0,0,0.2];
                    borderColor = [255,0,0,0.5]
                }else if (warningState == 1){
                    fillColor = [250,128,0,0.2];
                    borderColor = [250,128,0,0.5]
                }
				var attr = {"warningRuleId":warningRuleId};	
				var title = "<div style='line-height:2;'>"+
							"<font style='font-weight:bold;font-size:18px;color:#000000;z-index:-1;'>车辆统计</font></div>";
				var content = "<div style='height:2px;margin-bottom:12px;border:none;border-top:1px solid #919191;width:98%'></div>"+
							  "<font style='font-weight:bold;font-size:14px;color:#000000;z-index:-1;'>出租车："+totalVehicleCounts+"</font>";
				var infoTemplate = new esri.InfoTemplate(title,content);
                var graphic = new Graphic(geometry, new SimpleFillSymbol(
                    SimpleFillSymbol.STYLE_SOLID,
                    new SimpleLineSymbol(
                        SimpleLineSymbol.STYLE_SOLID,
                        new Color(borderColor),
                        3
                    ),new Color(fillColor)),attr,infoTemplate);
                layerOfArea.add(graphic);
            };

            /**
             * 绘制一个带状或者环形区域，
             */
            $scope.drawSelectArea = function (ribbon, warningState, totalVehicleCounts, warningRuleId) {require([
                'esri/geometry/Geometry', 'esri/geometry/Point', 'esri/geometry/jsonUtils', 'esri/geometry/Polygon', 'esri/SpatialReference', "esri/geometry/webMercatorUtils"
            ], function (Geometry, Point, jsonUtils, Polygon, SpatialReference, webMercatorUtils) {
                if (ribbon !== undefined && ribbon.length > 0) {
                    for (var m = 0; m < ribbon.length; m++) {
                        var rings = [];
                        for (var j = 0; j < ribbon[m].length; j++) {
                            var xy = webMercatorUtils.lngLatToXY(ribbon[m][j][0], ribbon[m][j][1]);
                            rings.push(xy);
                        }
                        if (rings.length === 0) {
                            continue;
                        }
                        var polygon = new Polygon([rings]);
                        var wkid = {
                            wkid: 102100,
                            latestWkid: 3857
                        };
                        var spatialReference = new SpatialReference(wkid);
                        polygon.setSpatialReference(spatialReference);
                        $scope.addToMap(polygon, warningState, totalVehicleCounts, warningRuleId);
                    }
                }
            })};
        });   
    }
    /**********************************地图图层显示end**********************************/
    
    $scope.initZoomAndCenter = function(){
		 switch ($rootScope.depName) {
		case "01":
			showCenterPoint = [104.010229,30.623509];
//			showZoom = 5;
			showZoom = 5;
			break;
		case "02":
			showCenterPoint = [104.052628,30.728445];
			showZoom = 5;
			break;
		case "03":
			showCenterPoint = [104.117757,30.603133];
			showZoom = 5;
			break;
		case "04":
			showCenterPoint = [104.981480,30.679894];
			showZoom = 5;
			break;
		case "05":
			showCenterPoint = [104.144179,30.695388];
			showZoom = 5;
			break;
		case "06":
			showCenterPoint = [104.017999,30.635954];
			showZoom = 5;
			break;
		default:
			showCenterPoint = [104.0657754083, 30.6583098090];
			showZoom = 4;
			break;
		}
	 };
    
    
    $scope.initZoomAndCenter();
    $scope.showMap();

    $scope.getBusFocusWarning = function (calback) {
        var dataUrl = "http://" + dataIp + '/erisp-dataservice/busFocusInfo/getBusFocusInfo?callBack=JSON_CALLBACK&t=' + new Date().getTime();

        $http.jsonp(dataUrl)
            .success(function (data) {
                calback(data);
            }).error(function (data) {
            error(data);
        });

    };
	
	$scope.$on('$destroy', function(){
		$interval.cancel($scope.busFocusWarnRefresh);
		map.destroy();
	})
} ]);

