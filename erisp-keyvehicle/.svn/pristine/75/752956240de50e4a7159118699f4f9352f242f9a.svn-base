app.controller('trailCtrl', ['$scope','$stateParams','$filter','$timeout','$state',
		'mapService','$rootScope','SweetAlert','$interval',function($scope, $stateParams, $filter, $timeout, $state, mapService, $rootScope,SweetAlert, $interval) {
	
	var map;	//地图
	var gra;
	var pN;		//选中车辆的车牌号码
	$scope.plateNumberLocations = ["G"];//车牌地址数组
	var hisRefresh = false;//车辆历史轨迹是否定时刷新，默认为false
	var gpsIsClicked = true;//GPS轨迹按钮是否被点击，默认为false
	var bayonetIsClicked = false;//卡口轨迹按钮是否被点击，默认为false
	var hourIsClicked = false;//1小时内按钮是否被点击，默认为false
	var dayIsClicked = false;//24小时内按钮是否被点击，默认为false
	var customizeIsClicked = false;//自定义时间按钮是否被点击，默认为false
	var haveShowLine = false; //标记是否打开了行驶轨迹
	$scope.plateInfo={};
	var timeRefresh; //实时刷新车辆历史轨迹
	$scope.queryConditionData = {};
	$scope.hisLocations = [];	//存储某段时间内某辆车的历史轨迹信息数组
	var showLineLength = 5; //定义每次加载的历史轨迹长度
	//	var realLocalRefresh; //实时刷新单个车辆位置定时器
	$scope.showall = true;//默认为显示
	$scope.showAllTypeIco = true; //显示全部车辆类型图标注释
	var lineGrap; //轨迹图层
	var recGrap; //卡口图层
	var pointGrap;
	var divType;
	var departName = $rootScope.depName;//分局名称
	var hisLineInterval;
	var lastLongitude;//上一个GPS的经度
	var lastLatitude;//上一个GPS的纬度
	
	$scope.toLeftRigth = function(showFlag){
		$scope.showall = showFlag;
	}
	
	//初始化车牌前缀
	 $(document).ready(function(){
	    	for(var i = 0;i<$scope.plateNumberLocations.length;i++){
	    		$("#plateNumberLocations").append("<option value='"+$scope.plateNumberLocations[i]+"'>"+$scope.plateNumberLocations[i]+"</option>");
	    	}
	    })
	    
	  //点击卡口信息显示位置和图片
		$scope.bayonetOnClick = function(data){
			$scope.bayonetInfo  = data;
			$("#bayonetAddressDiv").attr("style","display:inline");
		}
	    
	//清除行驶轨迹还原地图
	  $scope.clearHisLine = function(){
	    	if(haveShowLine){
				var graphicses = map.graphics.graphics;
				for(var i=0;i<graphicses.length;i++){
					if(graphicses[i].geometry.type=="polyline"){
						map.graphics.remove(graphicses[i]);
					}
				}
				clearTimeout(timeRefresh);
				haveShowLine = false;
//				$("#startTime").val("");
//				$("#endTime").val("");
//				$scope.queryConditionData.startTime = null;
//		        $scope.queryConditionData.endTime = null;
			}
	    }
	 
	//点击选择时间段
	$scope.timePreviousClick = function($event){
		var obj = $event.target;
		if($(obj).attr("id")!="customize"){
			$scope.queryConditionData.timePrevious = $(obj).find('.hiddenSpan').text();
//	        $scope.queryConditionData.startTime = null;
//	        $scope.queryConditionData.endTime = null;
	        }else{
	        	$scope.queryConditionData.timePrevious = null;
	        }
		
		}
	
	$scope.toUpDown = function(showFlag,position){
		if(position == "down"){
			$scope.showAllTypeIco = showFlag;
		}else if(position == "up"){
			$scope.showUpTypeIco = showFlag;
		}
	}
	
	//加载时间控件
	$timeout(function(){
		$(".form_datetime").datetimepicker({
			lang:'ch',
			timepicker:true,
			formatDate:'Y-m-d H:i'
		});
	},500);
	
	//按钮被点击和没有被点击所执行的方法
    $scope.isClicked = function(isClicked,id,bac){
    	if(!isClicked){
        	$scope.changeColor(id,bac);
        }
    };
    
    //鼠标经过事件
    $scope.buttonClicked = function(){
    	if(customizeIsClicked){
    		$("#startTimeTr").attr("style","display:inline");
    		$("#endTimeTr").attr("style","display:inline");
    	}else{
    		$("#startTimeTr").attr("style","display:none");
    		$("#endTimeTr").attr("style","display:none");
    	}
    };
    
    //鼠标经过按钮时背景颜色和字体颜色的改变
    $scope.changeColor = function(id,bac){
    	document.getElementById(id).onmouseover = function(){this.style.background = '#aac1cb';this.style.color = bac;}
    	document.getElementById(id).onmouseout = function(){this.style.background = bac;this.style.color = '#979797';}
    };
    $scope.restoreColor = function(id,bac){
    	//当按钮已经被点击，鼠标经过按钮时背景颜色和字体颜色的不发生改变#dbe8f7
    	document.getElementById(id).onmouseover = function(){this.style.background = "#2a65a2";this.style.color = 'white';}
    	document.getElementById(id).onmouseover = function(){this.style.background = '#2a65a2';this.style.color = "white";}
    };
    
    //一小时内的点击事件
	$("#hour").click(function(){
		hourIsClicked = true;
		dayIsClicked = false;
		customizeIsClicked = false;
//		var pt = new esri.geometry.Point(104.0657754083, 30.6583098090);
		$scope.buttonClicked();
		$("#hour").css("background-color","#2a65a2");
    	$("#hour").css("color","white");
    	$("#day").css("background-color","#dbe8f7");
    	$("#day").css("color","#2a65a2");
    	$("#customize").css("background-color","#dbe8f7");
    	$("#customize").css("color","#2a65a2");
    	$("#gps").css("background-color","#dbe8f7");
    	$("#gps").css("color","#2a65a2");
    	$("#bayonet").css("background-color","#dbe8f7");
    	$("#bayonet").css("color","#2a65a2");
	});
	//24小时内的点击事件
	$("#day").click(function(){
		dayIsClicked = true;
		hourIsClicked = false;
		customizeIsClicked = false;
//		var pt = new esri.geometry.Point(104.0657754083, 30.6583098090);
		$scope.buttonClicked();
		$("#day").css("background-color","#2a65a2");
    	$("#day").css("color","white");
    	$("#hour").css("background-color","#dbe8f7");
    	$("#hour").css("color","#2a65a2");
    	$("#customize").css("background-color","#dbe8f7");
    	$("#customize").css("color","#2a65a2");
    	$("#gps").css("background-color","#dbe8f7");
    	$("#gps").css("color","#2a65a2");
    	$("#bayonet").css("background-color","#dbe8f7");
    	$("#bayonet").css("color","#2a65a2");
	});
	//选择时间确定按钮的点击事件
	/*$("#hisButton").click(function(){
		$scope.queryConditionData.timePrevious = null;
		var pt = new esri.geometry.Point(104.0657754083, 30.6583098090);
	});*/
	
	//开始时间的点击事件
	$("#startTime").click(function(){
		$scope.queryConditionData.timePrevious = null;
	});
	//结束时间的点击事件
	$("#endTime").click(function(){
		$scope.queryConditionData.timePrevious = null;
	});
	
	//点击查询车辆历史轨迹
    $scope.queryHistTra = function(){
    	if(!hisRefresh){
    		hisRefresh = true;
//    		$interval.cancel(realLocalRefresh);
    		loadLocalTimes = 0;
//        	timeRefresh = setTimeout(function(){showLine(map);}, 1000, 1);
    		showLine(map);
    	}
//    	setTimeout(function(){showLine(map)}, refreshMap);
//    	timeRefresh = setTimeout(function(){showLine(map)}, 5000);
//    	timeRefresh = setTimeout(function(){showLine(map);}, 1000, 1);
    }
//    $("#hisButton").click(function()); 
    
  //GPS轨迹按钮的点击事件
    $("#gps").click(function(){
    	divType = "";
    	$interval.cancel(hisLineInterval);
    	recGrap.clear();
    	lineGrap.clear();
    	pointGrap.clear();
    	hisRefresh = false;
    	$scope.queryServiceLocation();
    	$("#bayonetDiv").attr("style","display:none");
    	$("#gps").css("background-color","#2a65a2");
    	$("#gps").css("color","white");
    	$("#bayonet").css("background-color","#dbe8f7");
    	$("#bayonet").css("color","#2a65a2");
//    	$("#hour").css("background-color","#dbe8f7");
//    	$("#hour").css("color","#2a65a2");
//    	$("#day").css("background-color","#dbe8f7");
//    	$("#day").css("color","#2a65a2");
//    	$("#customize").css("background-color","#dbe8f7");
//    	$("#customize").css("color","#2a65a2");
    	$("#bayonetAddressDiv").attr("style","display:none");
    });
    //卡口轨迹按钮的点击事件
    $("#bayonet").click(function(){
    	divType = "rec";
    	$interval.cancel(hisLineInterval);
    	recGrap.clear();
    	lineGrap.clear();
    	pointGrap.clear();
    	var pt = new esri.geometry.Point(104.0657754083, 30.6583098090);
//    	map.centerAndZoom(pt,4);//内网地图缩放等级
    	map.centerAndZoom(pt,14);
    	$scope.hisLocations =[];
    	$scope.clearHisLine();
		$scope.queryAllRecPlateInfo();
    	$("#bayonet").css("background-color","#2a65a2");
    	$("#bayonet").css("color","white");
    	$("#gps").css("background-color","#dbe8f7");
    	$("#gps").css("color","#2a65a2");
//    	$("#hour").css("background-color","#dbe8f7");
//    	$("#hour").css("color","#2a65a2");
//    	$("#day").css("background-color","#dbe8f7");
//    	$("#day").css("color","#2a65a2");
//    	$("#customize").css("background-color","#dbe8f7");
//    	$("#customize").css("color","#2a65a2");
    });
    
  //自定义按钮的点击事件
    $("#customize").click(function(){
		customizeIsClicked = true;
		hourIsClicked = false;
		dayIsClicked = false;
		$scope.buttonClicked();
		$("#customize").css("background-color","#2a65a2");
    	$("#customize").css("color","white");
    	$("#day").css("background-color","#dbe8f7");
    	$("#day").css("color","#2a65a2");
    	$("#hour").css("background-color","#dbe8f7");
    	$("#hour").css("color","#2a65a2");
    	$("#gps").css("background-color","#dbe8f7");
    	$("#gps").css("color","#2a65a2");
    	$("#bayonet").css("background-color","#dbe8f7");
    	$("#bayonet").css("color","#2a65a2");
	});
    
  //取消卡口图片的点击事件
    $("#imgCancleBayonet").click(function(){
    	$("#bayonetAddressDiv").attr("style","display:none");
    });
    
    //加点
    /*function showLocation(x, y, pic,type,plateNumber,ascriptionCompany,speed) {  
    	//创建图片样式符合
    	var pt = new esri.geometry.Point(x,y);//创建一个点对象
    	var attr = {"vehicleType":type,"plateNumber":plateNumber,"ascriptionCompany":ascriptionCompany,"speed":speed};
    	var infoTemplate = new esri.InfoTemplate("车牌号码：${plateNumber}","车辆类型：${vehicleType}," +
    			"所属企业：${ascriptionCompany}," +
    			"速度：${speed}km/h");
    	gra = new esri.Graphic(pt,pic,attr,infoTemplate);//设置样式
    	//把图层实例放入数组中以便后面有针对性的移除
    	if(graps.length<=len){
    		graps.push(gra);
    	}
    	map.graphics.add(gra);//添加到图层中    
    };*/
    
 	//历史轨迹
 	function showLine(map){
 		 haveShowLine = true; //标记行驶轨迹已打开
 		 var hisLocations = $scope.hisLocations;	//包含车辆经纬度的字符串
         var paths = [];
         if(hisLocations.length > 0){
        	 var i = 0;
     	     if (i == 0) {
     	    	hisLineInterval = $interval(function() {
     	    		if (hisLocations.length>0 && i == hisLocations.length) {
     	            	$interval.cancel(hisLineInterval);
     	            }else{
 		    	        var local = hisLocations[i].split("-");
 		    	        ++i;
 		    	        //如果本条gps跟上个GPS一样则不加点连线
 		    	        if(lastLongitude!=local[0] || lastLatitude!=local[1]){
 			    	        lastLongitude = local[0]*1;
 			    	        lastLatitude = local[1]*1;
 				           	var his = [];
 				           	his[0] = local[1]*1;//纬度
 				           	his[1] = local[0]*1;//经度
 				           	paths.push(his);
 				           	polylineJson={"paths": [paths],"spatialReference":{"wkid":4326}}; 
 						    var graphicsLayer = new esri.layers.GraphicsLayer();//添加线的图层，方便清除上一个图层所画的线
 			//		    	map.graphics.clear();//清除地图上现有的点
 						    lineGrap.clear();
 			//		    	map.removeLayer(graphicsLayer);//清除地图上的线
 						    var polyline=new esri.geometry.Polyline(polylineJson);
 						    var sys=new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new esri.Color([0,0,255]),3);
 						    var graphic2=new esri.Graphic(polyline,sys);
 						   	lineGrap.add(graphic2);
 						   	
 						   //加点
 						   pointGrap.clear();
 						   //var local = hisLocations[i].split("-");
 		    			   if(local[2] == "A1"){
 		    				   var pic = new esri.symbol.PictureMarkerSymbol("assets/images/new-energy-vehicle-online-90.png",21,21);
 		    			   }else if(local[2] == "A2"){
 		    				   var pic = new esri.symbol.PictureMarkerSymbol("assets/images/dangerous-goods-vehicle-online-90.png",21,21);
 		    			   }else if(local[2] == "A3"){
 		    				   var pic = new esri.symbol.PictureMarkerSymbol("assets/images/cold-chain-vehicle-online-90.png",21,21);
 		    			   }else if(local[2] == "A4"){
 		    				   var pic = new esri.symbol.PictureMarkerSymbol("assets/images/ordinary-vehicle-online-90.png",21,21);
 		    			   }
 		    			   var pt = new esri.geometry.Point(local[1]*1,local[0]*1);//创建一个点对象
 		    			   var attr = {"vehicleType":local[2]*1,"plateNumber":local[3]*1};
 		    			   //var infoTemplate = new esri.InfoTemplate("车牌号码：${plateNumber}","车辆类型：${vehicleType}");
 		    			   var gp = new esri.Graphic(pt,pic,attr);//设置样式
 		    			   var pn = local[3];
 		    			   var textgraph = new esri.Graphic(pt,new esri.symbol.TextSymbol(pn).setOffset(20, 10));
 		    			   pointGrap.add(gp);//添加到图层中 
 		    			   pointGrap.add(textgraph);
 		    			   var centerPoint = new esri.geometry.Point(local[1]*1,local[0]*1);
 		    			   map.centerAt(centerPoint);
 		    	      }
     	           }
     	        },
     	        100);
     	    }
         }
 	};
 	
 	//根据车牌号码查询车辆卡口信息
	 $scope.queryAllRecPlateInfo = function(){
		 hisRefresh = false;
		 if(null == $scope.queryConditionData.timePrevious){
				var sTime = $("#startTime").val();
				var eTime = $("#endTime").val();
				if("" == sTime || "" == eTime){
					$("#noTime").show().delay(1000).hide(300);
					return;
				}else{
					$scope.queryConditionData.startTime = sTime;
					$scope.queryConditionData.endTime = eTime;
				}
			}
		 $("#dataLoad").attr("style","display:inline"); 
		 if($scope.plateInfo.location == null){
    		$scope.plateInfo.location = "A";
    	 }
		 $scope.queryConditionData.plateNumber = "川" + $scope.plateInfo.location + $("#plateNumber").val().toUpperCase().trim();
		 mapService.queryAllRecPlateInfo($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				 $scope.bayonetData = data.messageBody.recPlateInfoList;
				 var recInfo = $scope.bayonetData;
				 if(0 == $scope.bayonetData.length){
					 $("#dataLoad").attr("style","display:none"); 
					 $("#notData").show().delay(1000).hide(300); 
				 }else{
					 $("#bayonetDiv").attr("style","display:inline");
				 }
				 $("#dataLoad").attr("style","display:none"); 
				 $scope.queryServiceLocation();
				 map.infoWindow.resize(520, 600);
				 for(var i=0;i<recInfo.length;i++){
					 var pic = new esri.symbol.PictureMarkerSymbol("assets/images/rec_ico.png",21,21);
					 var pt = new esri.geometry.Point(recInfo[i].longitude,recInfo[i].latitude);
					 var gra = new esri.Graphic(pt,pic);
					 recGrap.add(gra);
					 var location = recInfo[i].fstrLocation;
					 var url = recInfo[i].fstrPictrueurl;
					 var recTime = $scope.convertTime(recInfo[i].fdtTime);
					 var title = "<div style='line-height:1.5;margin-left:6px;'><font style='font-weight:bold;font-size:18px;color:#000000;'>" +
       	     			"卡口位置：<label class='pn'>"+location+"</label></font></br>" +
       	     			"<font style='font-weight:bold;font-size:18px;color:#000000;'>采集时间：<label class='pn'>"+recTime+"</label></font></div>";
					 var content = "<img class='img' src="+url+"></img>";
					 var infoTemplate = new esri.InfoTemplate(title,content);
					 recGrap.setInfoTemplate(infoTemplate);
				 }
				 map.graphics.redraw();
			 }
		 }, function (err) {
			 $("#dataLoad").attr("style","display:none"); 
			 $("#errorDiv").show().delay(1000).hide(300); 
		 })
	 }
	 
	//关闭dialog
    $("body").on("click","#closeDialog",function(){
    	$("div.dijitPopup").attr("style","display:none");
    });
	
     //转换时间
	 $scope.convertTime = function(time){
		 var afterTime;
		 if(time != null){
			 afterTime = $filter("date")(time.time, "yyyy-MM-dd HH:mm:ss");
		 }else{
			 afterTime = "";
		 }
		 return afterTime;
	 }
    
	//查询某段时间内某辆车的历史轨迹信息
	$scope.queryServiceLocation = function() {
		
		if($scope.plateInfo.location == null){
    		$scope.plateInfo.location = "A";
    	}
		$scope.queryConditionData.plateNumber = "川" + $scope.plateInfo.location + $("#plateNumber").val().toUpperCase().trim();
//		console.log($scope.queryConditionData.plateNumber);
		var plateNumber = $scope.queryConditionData.plateNumber;
		var startTime;
		var endTime;
		if(null == $scope.queryConditionData.timePrevious){
			var sTime = $("#startTime").val();
			var eTime = $("#endTime").val();
			if("" == sTime || "" == eTime){
				$("#noTime").show().delay(1000).hide(300);
				return;
			}else{
				startTime = sTime +":00";
				endTime = eTime +":00";
			}
		}else{
			var timePrevious = $scope.queryConditionData.timePrevious;
			var currentDate = new Date();
			var date = new Date(currentDate.valueOf() - 60*1000*timePrevious);
			startTime = $filter("date")(date, "yyyy-MM-dd HH:mm:ss");
			endTime = $filter("date")(currentDate, "yyyy-MM-dd HH:mm:ss");
//			startTime = date.Format("yyyy-MM-dd HH:mm:ss");
//			endTime = currentDate.Format("yyyy-MM-dd HH:mm:ss");
		}
		$scope.queryConditionData.startTime = $("#startTime").val();
	    $scope.queryConditionData.endTime = $("#endTime").val();
        $scope.hisLocations = [];
        mapService.queryRealOrHisLocations(plateNumber,departName,startTime,endTime,function (data) {
    	    //清空存储某段时间内某辆车的历史轨迹信息数组
    	    //if (data.state == 200) {
    	    if (data.status == 0) {
    	    	//$scope.movePaths = eval(data.messageBody.movePaths);
    	    	$scope.movePaths = eval(data.datas);
	        var result = $scope.movePaths;
	        //对取到的集合按时间排序
	        result.sort(function(a,b){
	            return a.dateTime-b.dateTime;
	        });
	        for(var i=0;i<result.length;i++){
	        	var location = "";
//	        	console.log(result[0].vehicleType);
	            location = result[i].latitude+"-"+result[i].longitude+"-"+result[i].vehicleType+"-"+result[i].plateNumber;
	            $scope.hisLocations.push(location);
	            }
	        $scope.queryHistTra();
	        }
	    }, function (err) {
	    })
	    }
	
	/**********************************地图图层显示start**********************************/
    $scope.showMap = function(){
    	require([  
            "esri/map", "esri/layers/ArcGISTiledMapServiceLayer", "esri/geometry/Extent", "esri/geometry/Point", "esri/geometry/Polyline", "esri/SpatialReference",  
            "esri/symbols/SimpleMarkerSymbol","esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol", "esri/layers/GraphicsLayer", "esri/graphic"  
        ], function(Map, ArcGISTiledMapServiceLayer, Extent, Point, Polyline, SpatialReference, SimpleMarkerSymbol,PictureMarkerSymbol, SimpleLineSymbol, GraphicsLayer, Graphic) {
            // BASE_SERVER配置移至app.js
            map = new Map("trail", {  
                center: [104.0657754083, 30.6583098090],  
//              zoom: 14,//内网地图缩放等级
                zoom: 14,
                logo:false,
                nav:false,
                slider:false
            }); 
    		var layer = new ArcGISTiledMapServiceLayer(BASE_SERVER);
            map.addLayer(layer);
            
            lineGrap = new GraphicsLayer();
            map.addLayer(lineGrap);
            
            recGrap = new GraphicsLayer();
            map.addLayer(recGrap);
            
            pointGrap = new GraphicsLayer();
			map.addLayer(pointGrap);
        });   
    }
    /**********************************地图图层显示end**********************************/
	
    //确保关闭页面或浏览器后销毁定时器
    $scope.$on('$destroy',function(){  
    	$interval.cancel(hisLineInterval);
    })
    
    //显示地图
    $scope.showMap();
//    $scope.queryServiceLocation();
} ]);


	



 
  

