app.controller('warningMapController', [
		'$scope',
		'$filter',
		'$timeout',
		'$state',
		'warningMapService',
		'$rootScope',
		'SweetAlert',
		'$interval',
		'$modal',
		'$stateParams',
		function($scope, $filter, $timeout, $state, warningMapService, $rootScope, SweetAlert, $interval, $modal, $stateParams) {
			
	$scope.queryConditionData = {};
	$scope.locations = [];		//存储所有车辆信息数据
	$scope.hisLocations = [];	//存储某段时间内某辆车的历史轨迹信息数组
	$scope.realLocations = [];	//存储某一车辆历史轨迹信息数组
	$scope.queryConditionData.vehicleType = "";
	$scope.vehicleModel = {};	
//	$scope.plateNumberLocations = ["B","C","D"];//车牌地址数组
	$scope.plateNumberLocations = ["G"];//车牌地址数组
	$scope.plateInfo = {};	//车牌信息集合
	$scope.disposalMethods = "处置";
    $scope.hyDepartment = "物流云平台";
    $scope.disposalProcess = {};
	var graps = []; //存储图标图层实例
	var len;//graps的长度
	
	var map;	//地图
	var dialog; //弹出框
	var layerOfNew; //新能源图层
	var layerOfDangerous; //危化品图层
	var layerOfCold; //冷链车图层
	var layerOfOrdinary; //普通车图层
	var layerOfNewOff; //新能源离线图层
	var layerOfDangerousOff; //危化品离线图层
	var layerOfColdOff; //冷链车离线图层
	var layerOfOrdinaryOff; //普通车离线图层
	var layerOfOnVehicle; //定位某两车的图层
	var textGrapLayer;//定位某辆车的车牌号图层
	var gra;
	var pN;		//选中车辆的车牌号码
	var refresh = false;//实时信息是否定时刷新，默认false
	var hisRefresh = false;//车辆历史轨迹是否定时刷新，默认为false
	var fristComing = true;//是否第一次进入,默认为true
	var timeClicked = false;//包含开始结束时间的div是否被点击，默认为false
	var infoIsClicked = false;//基本信息按钮是否被点击，默认为false
	var trajectoryIsClicked = false;//行驶轨迹按钮是否被点击，默认为false
	var earlyWarningIsClicked = false;//预警处置按钮是否被点击，默认为false
	var gpsIsClicked = false;//GPS轨迹按钮是否被点击，默认为false
	var bayonetIsClicked = false;//卡口轨迹按钮是否被点击，默认为false
	var hourIsClicked = false;//1小时内按钮是否被点击，默认为false
	var dayIsClicked = false;//24小时内按钮是否被点击，默认为false
	var customizeIsClicked = false;//自定义时间按钮是否被点击，默认为false
	var singleCar; //记录上个选中车辆的实时标记，以便移除
	var haveShowLine = false; //标记是否打开了行驶轨迹
	var timeRefresh; //实时刷新车辆历史轨迹
	var realLocalRefresh; //实时刷新单个车辆位置定时器
	var warningRefresh; //预警信息定时刷新
	var showLineRefresh; //历史轨迹定时刷新
	var queryRefresh; //车辆位置定时刷新
	var globalPlateNumber; //记录选中辆车的车牌号
    var showLineLength = 1; //定义每次加载的历史轨迹长度
    var loadLocalTimes = 0; //加载某辆车实时位置的次数
    var warningCheckedName;	//菜单选择框名称
    var posX; //气泡弹出div的x坐标
    var posY;//气泡弹出div的y坐标
    var vehicleBaseInfo; //车辆基本信息
    var warning;//预警信息传递到跳转页面
    var accidentTotal = 0;//事故次数传递到跳转页面
    var illegalTotal = 0;//违法次数传递到跳转页面
    var isRegisterArea = false;//是否只显示注册地相关信息
    var showZoom;//分局显示不同层级
    var showCenterPoint;//分局显示不同中心点
	
	$scope.currentPage = 1;//初始化当前页
    $scope.pageSize = 10;//初始化每页大小
    $scope.queryConditionData = {
            'currentPage':  1,
            'pageSize': $scope.pageSize
    }
    
    var textgraph;//文字要素
    var textgraphs=[];//文字要素数组
    var layerOfOrdinaryText; //普通车标记图层
    var layerOfNewText; //新能源图层
	var layerOfDangerousText; //危化品图层
	var layerOfColdText; //冷链车图层
	var mapZoom = 16;
//	var mapZoom = 8;//内网地图缩放等级
	//标记当前图层是否在显示状态
	var layerOfOrdinaryFlag = false;
	var layerOfNewFlag = false;
	var layerOfDangerousFlag = false;
	var layerOfColdFlag = false;
	var departName = $rootScope.depName;
	
	$scope.showall = true;//panel显示全部左侧
	$scope.showright = false;//右侧
	$scope.showrightWarning = false; //右侧预警列表
	$scope.showrightCheckbox = false; //右侧预警列表
	
	$scope.showAllTypeIco = true; //显示全部车辆类型图标注释
	$scope.showUpTypeIco = false;
	
	$scope.showTotal = true;//显示统计信息
	
	$scope.toLeftRigth = function(showFlag,position){
		if(position == "left"){
			$scope.showall = showFlag;
		}else if(position == "right"){
			$scope.showright = showFlag;
			$scope.showrightWarning = showFlag;
			$scope.showrightCheckbox = showFlag;
			$("#rightDiv").attr("style","display:inline;width:12%;position: absolute;z-index:4;top:0px;right:0px;");
			$("#rightWarningDiv").attr("style","border: 1px solid;border-color: #6f90b4;width: 64%;");
		}
	}
	
	$scope.changeLeftRigth = function(showFlag,position){
		if(position == "left"){
			$scope.showall = showFlag;
		}else if(position == "right"){
			$scope.showright = showFlag;
			$scope.showrightWarning = showFlag;
			$scope.showrightCheckbox = showFlag;
			$("#rightDiv").attr("style","display:inline;width:12%;position: absolute;z-index:4;top:0px;right:0px;");
			$("#rightWarningDiv").attr("style","border: 1px solid;border-color: #6f90b4;width: 64%;");
		}
		if(showFlag){
			if(layerOfOnVehicle != null & layerOfOnVehicle != undefined) {
	        	layerOfOnVehicle.clear();
	        }
	        if(textGrapLayer != null & textGrapLayer != undefined) {
	        	textGrapLayer.clear();
	        }
	        pt = new esri.geometry.Point(showCenterPoint[0], showCenterPoint[1]);
	        map.centerAndZoom(pt,showZoom);
	        $interval.cancel(realLocalRefresh);
	        $scope.showAllGraphics();
	        queryRefresh = $interval(function(){
	        	$scope.query("");
	        },5000);
		}else{
			$scope.oneVehicleReressh();
		}
	}
    
	$scope.toUpDown = function(showFlag,position){
		if(position == "down"){
			$scope.showAllTypeIco = showFlag;
		}else if(position == "up"){
			$scope.showUpTypeIco = showFlag;
		}
	}
	
	//切换车辆统计信息显示或隐藏
    $scope.showTotalLabel = function(showFlag){
    	$scope.showTotal = showFlag;
    }
	
    //监听复选框选中状态--预警监控页面不分车辆类型
    /*$scope.$watch('warningCheckedName + warningCheckedState',function(){
    	if(realLocalRefresh!=undefined){
			$interval.cancel(realLocalRefresh);
		}
		$("#oneWarningTotalDiv").hide();
    	if($rootScope.warningCheckedState){
    		if(layerOfOnVehicle!=null & layerOfOnVehicle!=undefined){
            	layerOfOnVehicle.clear();
            }
    		switch ($rootScope.warningCheckedName) {
			case "新能源":
				if(layerOfNew.graphics.length<=0){
					$scope.query("新能源");
				}
				layerOfNewFlag = true;
				$scope.queryConditionData.newChecked = "新能源";
				layerOfNew.setVisibility(true);
				$scope.queryConditionData.vehicleType = "新能源";
				if(map.getZoom()>mapZoom){
            		layerOfNewText.setVisibility(true);
            	}else{
            		layerOfNewText.setVisibility(false);
            	}
				break;
			case "冷链":
				if(layerOfCold.graphics.length<=0){
					$scope.query("冷链车");
				}
				layerOfColdFlag = true;
				$scope.queryConditionData.dangerousChecked = "冷链车";
				layerOfCold.setVisibility(true);
				$scope.queryConditionData.vehicleType = "冷链车";
				if(map.getZoom()>mapZoom){
            		layerOfColdText.setVisibility(true);
            	}else{
            		layerOfColdText.setVisibility(false);
            	}
				break;
			case "危化品":
				if(layerOfDangerous.graphics.length<=0){
					$scope.query("危化品");
				}
				layerOfDangerousFlag = true;
				$scope.queryConditionData.coldChecked = "危化品";
				layerOfDangerous.setVisibility(true);
				$scope.queryConditionData.vehicleType = "危化品";
				if(map.getZoom()>mapZoom){
					layerOfDangerousText.setVisibility(true);
            	}else{
            		layerOfDangerousText.setVisibility(false);
            	}
				break;
			case "普通":
				if(layerOfOrdinary.graphics.length<=0){
					$scope.query("普通车");
				}
				layerOfOrdinaryFlag = true;
				$scope.queryConditionData.ordinaryChecked = "普通车";
				layerOfOrdinary.setVisibility(true);
				$scope.queryConditionData.vehicleType = "普通车";
				if(map.getZoom()>mapZoom){
					layerOfOrdinaryText.setVisibility(true);
            	}else{
            		layerOfOrdinaryText.setVisibility(false);
            	}
				break;
			default:
				break;
			}
    		$("#rightDiv").attr("style","display:inline;width:10%;position: absolute;z-index:4;top:0px;right:0px;");
    	}else{
    		switch ($rootScope.warningCheckedName) {
			case "新能源":
				layerOfNewFlag = false;
				$scope.queryConditionData.newChecked = null;
				layerOfNew.setVisibility(false);
				layerOfNewText.setVisibility(false);
				break;
			case "冷链":
				layerOfColdFlag = false;
				$scope.queryConditionData.dangerousChecked = null;
				layerOfCold.setVisibility(false);
				layerOfColdText.setVisibility(false);
				break;
			case "危化品":
				layerOfDangerousFlag = false;
				$scope.queryConditionData.coldChecked = null;
				layerOfDangerous.setVisibility(false);
				layerOfDangerous.setVisibility(false);
				break;
			case "普通":
				layerOfOrdinaryFlag = false;
				$scope.queryConditionData.ordinaryChecked = null;
				layerOfOrdinary.setVisibility(false);
				layerOfOrdinaryText.setVisibility(false);
				break;
			default:
				break;
			}
    	}
    });*/
    
  //翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData.currentPage = $scope.currentPage;
	   	$scope.queryFunction();
    };
    
    $(document).ready(function(){
    	for(var i = 0;i<$scope.plateNumberLocations.length;i++){
    		$("#plateNumberLocations").append("<option value='"+$scope.plateNumberLocations[i]+"'>"+$scope.plateNumberLocations[i]+"</option>");
    	}
    })
	
	//根据车牌号搜索历史轨迹
	/*$scope.queryVehicleHisTrajectoryInfoByPlateNumber = function(){
		warningMapService.queryVehicleHisTrajectoryInfoByPlateNumber(pN,function (data) {
		$scope.realLocations = [];
		if (data.state == 200) {
			$scope.vehicleHisTrajectoryInfo = data.messageBody.vehicleHisTrajectoryInfoList;
			var result = $scope.vehicleHisTrajectoryInfo;
			for(var i=0;i<result.length;i++){
				var location = "";
				location = result[i].latitude+"-"+result[i].longitude+"-"+result[i].vehicleType;
				$scope.realLocations.push(location);
				}
			}
		}, function (err) {
		})
		}*/
    
     //根据车牌号搜索车辆信息
	 $scope.queryVehicleByPlateNumber = function(){
		 $scope.queryConditionData.plateNumber = "川" + $scope.plateInfo.location + $("#plateNumber").val().toUpperCase().trim();
		 $scope.queryConditionData.pageSize = 10;
		 warningMapService.queryByPlateNumber($scope.queryConditionData,function (data) {
	            if (data.state == 200) {
	            	$scope.vehicleInfoList = data.messageBody.vehicleInfoList;
	            	$scope.totalItems = data.messageBody.page.count;
	            	$scope.currentPage = data.messageBody.page.page;
	            	$scope.pages = data.messageBody.page.pages;
	            }
	        }, function (err) {
	        })
	        
	    }
	 
	 //根据车牌号码查询车辆卡口信息
	 $scope.queryAllRecPlateInfo = function(){
		 $("#bayonet").attr("style","display:inline");
		 $("#dataLoad").attr("style","display:inline"); 
		 warningMapService.queryRecPlateInfo($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				 $scope.bayonetData = data.messageBody.recPlateInfoList;
				 if(0 == $scope.bayonetData.length){
					 $("#dataLoad").attr("style","display:none"); 
					 $("#notData").show().delay(1000).hide(300); 
				 }
				 $("#dataLoad").attr("style","display:none"); 
			 }
		 }, function (err) {
			 $("#dataLoad").attr("style","display:none"); 
			 $("#errorDiv").show().delay(1000).hide(300); 
		 })
	 }
	 
	 //根据用户类型和部门名称获取预警处置信息
	 $scope.queryWarningDisposalViewList = function(){
		 $scope.queryConditionData.userType = "1";
		 $scope.queryConditionData.department = "1";
		 $scope.queryConditionData.pageSize = 20;
		 warningMapService.queryWarningDisposalViewList($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				 $scope.warningDisposalViewList = data.messageBody.warningDisposalViewList;
				 var result = $scope.warningDisposalViewList;
				 var showResult = [];
				 for(var i = 0;i<=result.length;i++){
					 if(undefined != result[i] && null != result[i]){
						 if(undefined != result[i].earlyWarningInfo && null != result[i].earlyWarningInfo){
//							 var today = new Date(new Date().toLocaleDateString()).getTime();
							 var nowDate = new Date();
							 var today = Date.parse(new Date(nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate()));
							 var time = result[i].earlyWarningInfo.warningStartTime.time;
//							 if(time > today){
								 showResult[i] = result[i];
//							 }
						 }
					 }
				 }
				 $scope.showWarningDisposalViewList = showResult;
			 }
		 }, function (err) {
		 })
	 }
	 
	//重置
	$scope.reset = function(){
		$("#plateNumber").empty();
		$scope.queryConditionData = {};
	};

	//查询车辆的实时信息
	$scope.queryVehicleRealTimeInfo = function () {
		warningMapService.queryVehicleRealTimeInfo($scope.queryConditionData,function (data) {
			if (data.state == 200) {
				$scope.vehicleRealTimeInfo = data.messageBody.vehicleRealTimeInfo;
				var result = $scope.vehicleRealTimeInfo;
				for(var i=0;i<result.length;i++){
					var location = "";
					location = result[i].latitude+"-"+result[i].longitude+"-"+result[i].vehicleType;
					$scope.locations.push(location);
					}
				$scope.showMap();
				}
			}, function (err) {
		})
	};

	//查询某段时间内某辆车的历史轨迹信息
	/*$scope.queryServiceLocation = function() {
		$scope.queryConditionData.startTime = $("#startTime").val();
        $scope.queryConditionData.endTime = $("#endTime").val();
        $scope.queryConditionData.plateNumber = pN;
        $scope.hisLocations = [];
	    warningMapService.queryLocations($scope.queryConditionData,function (data) {
	    //清空存储某段时间内某辆车的历史轨迹信息数组
	    if (data.state == 200) {
	    	$scope.movePaths = eval(data.messageBody.movePaths);
	        var result = $scope.movePaths;
	        for(var i=0;i<result.length;i++){
	        	var location = "";
	            location = result[i].latitude+"-"+result[i].longitude+"-"+result[i].vehicleType+"-"+result[i].plateNumber;
	            $scope.hisLocations.push(location);
	            }
	        }
	    }, function (err) {
	    })
	    }*/
	
	//查询所有车辆信息
    $scope.queryFunction = function (data) {
    	$scope.queryConditionData.plateNumber = "川" + $scope.plateInfo.location + $("#plateNumber").val().toUpperCase().trim();
    	$scope.queryConditionData.pageSize = 10;
    	$scope.queryConditionData.departmentName = $rootScope.chineseDepName;
    	warningMapService.queryWarningVehicleInfo($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.vehicleInfoList = data.messageBody.vehicleInfo;
            	$scope.totalItems = data.messageBody.page.count;
            	$scope.currentPage = data.messageBody.page.page;
            	$scope.pages = data.messageBody.page.pages;
            }
        }, function (err) {
        })
    };
	
	$scope.timePreviousClick = function($event){
		var obj = $event.target;
		if($(obj).attr("id")!="diyTimePrevious"){
			$scope.queryConditionData.timePrevious = $(obj).find('.hiddenSpan').text();
	        $scope.queryConditionData.startTime = null;
	        $scope.queryConditionData.endTime = null;
	        }else{
	        	$scope.queryConditionData.timePrevious = null;
	        }
		}
	
	//点击车辆信息显示其基本信息和轨迹选择方框
	$scope.checkedVehicle = function(data,e){
		//$scope.clearHisLine();
		hisRefresh = false;
		pN = data.plateNumber;
		globalPlateNumber = pN;
		$scope.oneInfo = data;
		$scope.showOneVehicleInfo(data.plateNumber);
		//选中行加色
		$(e.currentTarget).addClass("vehicleInfoTr").siblings().removeClass("vehicleInfoTr");
	}
	
	//点击一辆车辆，显示其相关的界面和信息
	$scope.showOneVehicleInfo = function(plateNumber){
		if(queryRefresh!=undefined){
			$interval.cancel(queryRefresh);
		}
		if(realLocalRefresh!=undefined){
			$interval.cancel(realLocalRefresh);
		}
		$scope.hideAllGraphics();
		$scope.tabOnOneVehicle(plateNumber);
//		$scope.queryWarningDisposalViewList();
		map.setZoom(14);
	}
	
	//加载车辆行驶信息
	$scope.loadOneDriveInfo = function(plateNumber){
		globalPlateNumber = plateNumber;
		$scope.queryVehicleDriveInfo(plateNumber);
		$scope.plateNumber = plateNumber;
		$("#oneWarningTotalDiv").show();
	}
	
	//查某车预警及速度
	$scope.queryVehicleDriveInfo = function(plateNumber){
		$scope.plateNumber = plateNumber;
		warningMapService.queryVehicleDriveInfo(plateNumber,function (data) {
			 if (data.state == 200) {
				 $scope.vehicleDriveInfo = data.messageBody.vehicleDriveInfo;
			 }
		})
	}
	
	//标记出选定的车辆
	$scope.tabOnOneVehicle = function(plateNumber) {
	    var queryConditionParams = {};
	    queryConditionParams['plateNumber'] = plateNumber;
	    var pt, pic;
	    var attr;
	    //result:车辆基本信息
	    var result = [];
	    //vehicleRealInfo:车辆实时信息
	    var vehicleRealInfo = [];
	    //vehicleDriveInfo:车辆预警信息
	    var vehicleDriveInfo = [];
	    warningMapService.queryOneVehicleRealTimeInfoSyn(queryConditionParams)
	    .then(function(data) {
	        /*****************************车辆基本信息*******************************/
	        if (data.state == 200 && data.messageBody.oneVehicleRealTimeInfo != null) {
	            if(layerOfOnVehicle != null & layerOfOnVehicle != undefined) {
	            	layerOfOnVehicle.clear();
	            }
	            if(textGrapLayer != null & textGrapLayer != undefined) {
	            	textGrapLayer.clear();
	            }
	            $scope.oneVehicleInfo = data.messageBody.oneVehicleRealTimeInfo;
	            result = $scope.oneVehicleInfo;

	            /*********************车辆实时信息*********************/
	            var pn = plateNumber.replace("川", "");
	            warningMapService.queryVehicleInfoByVehicleTypeSyn(departName, isRegisterArea, plateNumber)
	            .then(function(data) {
	                if (data.status == 0 && data.datas.length>0) {
	                    vehicleRealInfo = data.datas[0];
	                } else {
	                    vehicleRealInfo["speed"] = "0";
	                    vehicleRealInfo["course"] = "0";
	                    vehicleRealInfo["maxSpeed"] = "0";
	                    vehicleRealInfo["longitude"] = "";
	                    vehicleRealInfo["latitude"] = "";
	                }
	                
	                /*********************车辆预警信息*********************/
	                warningMapService.queryDataOfDialogSyn(pn, departName)
	                .then(function(data) {
	                    if (data.status == 0 && data.datas.length>0) {
	                        vehicleDriveInfo = data.datas[0];
	                    } else {
	                        vehicleDriveInfo["warningStartTime"] = "";
	                        vehicleDriveInfo["warningRuleName"] = "";
	                        vehicleDriveInfo["roadName"] = "";
	                        vehicleDriveInfo["yearWarningTimes"] = "0";
	                        vehicleDriveInfo["warningTimes"] = "0";
	                        vehicleDriveInfo["disposalWay"] = 0;
	                    }
	                    if (null != result.accidentTotal && undefined != result.accidentTotal) {
	                        accidentTotal = result.accidentTotal; //事故次数
	                    }
	                    if (null != result.illegalTotal && undefined != result.illegalTotal) {
	                        illegalTotal = result.illegalTotal; //违法次数
	                    }
	                    
	                    //添加车标
	                    if(vehicleRealInfo.longitude!="" && vehicleRealInfo.latitude!=""){
	    	                $scope.hideAllGraphics();
	    		            pt = new esri.geometry.Point(vehicleRealInfo.longitude, vehicleRealInfo.latitude);
	    		            var textgraph = new esri.Graphic(pt,new esri.symbol.TextSymbol(plateNumber).setOffset(20, 10));
	    		            pic = new esri.symbol.PictureMarkerSymbol("assets/images/warning.gif", imageSize, imageSize);
	    		            attr = {
	    		                "vehicleType": result.vehicleType,
	    		                "plateNumber": result.plateNumber
	    		            };
		                    map.infoWindow.resize(400, 580);
		                    var vehicleType = result.vehicleType.replace(/车/, "");
		                    var title = "<div style='line-height:2;'><font style='font-weight:bold;font-size:16px;color:#000000;'>" + 
		                    	"车牌号码：<label class='pn' style='font-size:16px;'>" + plateNumber + "</label>——" + vehicleType + "物流车</font></div>" + 
		                    	"<div style='height:2px;border:none;border-top:1px solid #919191;width:98%;'></div>";
		                    var content = "<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:65px;'>" + 
		                    	"<div class='col-md-12 font_black_famil'>" + 
		                    	"<table style='font-size:16px;width:100%;'>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>预警时间：</font>" + $scope.convertWarningTime(vehicleDriveInfo.warningStartTime) + "</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>预警规则名：</font>" + $scope.convertWarningRuleName(vehicleDriveInfo.warningRuleName) + "</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>预警地点：</font>" + $scope.convertRoadname(vehicleDriveInfo.roadName) + "</td></tr>" + 
		                    	"</table></div></div>" + "<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:130px;'>" + 
		                    	"<div class='col-md-12 font_black_famil'>" + "<div style='font-size: 16px;'>基本信息</div>" + 
		                    	"<div class='title_line_map' style='width:100%;'></div>" + "<table style='font-size:16px;width:100%;'>" + 
		                    	"<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>入城证：</font>" + $scope.convertIntoCityCard(result.intoCityCard) + "</td>" + 
		                    	"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>车牌类型：</font>" + $scope.convertPlateType(result.plateType) + "</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>行业主管部门：</font>" + result.competentAuthority + "</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>注册登记时日期：</font>" + $scope.convertDate(result.registrationDate) + "</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>所属企业：</font>" + result.ascriptionCompany + "</td></tr>" + 
		                    	"</table></div></div>" + 
		                    	"<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:150px;margin-top:10px;'>" + 
		                    	"<div class='col-md-12 font_black_famil'>" + "<div style='font-size: 16px;'>状态信息</div>" + 
		                    	"<div class='title_line_map' style='width:100%;'></div>" + "<table style='font-size:16px;width:100%'>" + 
		                    	"<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>年度累计报警次数：</font>" + vehicleDriveInfo.yearWarningTimes + "次</td>" + 
		                    	"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>在线离线状态：</font>" + $scope.convertOnlineState(vehicleRealInfo.gpsState) + "</td></tr>" + 
		                    	"<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>车辆速度：</font><font style='color: green;font-weight:bold;'>" + vehicleRealInfo.speed + "</font>km/h</td>" + 
		                    	"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>年审状态：</font>" + result.motTestState + "</td></tr>" + 
		                    	"<tr><td style='font-size:14px;'><a style='color:red;' class='accidentTotal'><font style='font-weight:bold;font-size:14px;'>年度事故次数：</font>" + result.accidentTotal + "次</a></td>" + 
		                    	"<td style='font-size:14px;'><a style='color:red;' class='illegalTotal'><font style='font-weight:bold;font-size:14px;'>年度违法次数：</font>" + result.illegalTotal + "次</a></td></tr>" + 
		                    	"<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>车辆角度：</font><font style='color: green;font-weight:bold;'>" + vehicleRealInfo.course + "</font>度</td>" + 
		                    	"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>今日最高速度：</font><font style='color: green;font-weight:bold;'>" + vehicleRealInfo.maxSpeed + "</font>km/h</td></tr>" + 
		                    	"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>今日行驶预警次数：</font><font style='color: red;font-weight:bold;'>" + vehicleDriveInfo.warningTimes + "</font>次</td></tr>" + 
		                    	"</table></div></div>" + 
		                    	"<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:100px;margin-top:10px;'>" + 
		                    	"<div class='col-md-12 font_black_famil'>" + "<div style='font-size: 20px; '>操作</div>" + 
		                    	"<div class='title_line_map' style='width:100%;'></div>" + 
		                    	"<table style='width:100%'><tr><td>&nbsp;&nbsp;<img id='warningButton' hideValue='" + plateNumber + "' src='assets/images/" + 
		                    	(vehicleDriveInfo.disposalWay == 0 ? "warning_deal_button.png'": "warning_enter_button.png'") + " style='cursor:pointer;'></td>" + 
		                    	"<td>&nbsp;&nbsp;<img id='toRealLine' hideType='one' hideValue='" + plateNumber + "' src='assets/images/real_line_button.png' style='cursor:pointer;'></td>" + 
		                    	"<td>&nbsp;&nbsp;<img id='toHisLine' hideType='one' hideValue='" + plateNumber + "' src='assets/images/his_line_button.png' style='cursor:pointer;'></td></tr>" + 
		                    	"<tr><td style='height:32px;'>&nbsp;&nbsp;<img id='showPlatePic' hideValue='" + result.platePictureUrl + "' src='assets/images/show_pic_button.png' style='cursor:pointer;'></td>" + 
		                    	"<td colspan='2'>&nbsp;&nbsp;<img id='bayonetButton' hideValue='" + plateNumber + "' src='assets/images/rec_button.png' style='cursor:pointer;'></td></tr></table>" + 
		                    	"</div></div>";
	
		                    var gp = new esri.Graphic(pt, pic, attr);
		                    layerOfOnVehicle = new esri.layers.GraphicsLayer();
		                    layerOfOnVehicle.add(gp);
		                    layerOfOnVehicle.add(textgraph);
		                    var infoTemplate = new esri.InfoTemplate(title, content);
		                    layerOfOnVehicle.setInfoTemplate(infoTemplate);
		                    textGrapLayer = new esri.layers.GraphicsLayer();
		                    textGrapLayer.add(textgraph);
		                    map.addLayer(layerOfOnVehicle);
		                    map.addLayer(textGrapLayer);
		                    if (singleCar != null && singleCar != '') {
		                        map.graphics.remove(singleCar);
		                    }
		                    map.graphics.redraw();
		                    //把地图中心定位到该车辆的位置
		                    map.centerAt(pt);
		                    /*if (loadLocalTimes == 0) {
		                    	if(realLocalRefresh!=undefined){
		                    		$interval.cancel(realLocalRefresh);
		                    	}else{
			                        realLocalRefresh = $interval(function() {
			                            $scope.tabOnOneVehicle(globalPlateNumber);
			                        },5000, 60);
		                    	}
		                    }*/
		                    loadLocalTimes++;
	                	}else{
			        		$("#msgDiv").show().delay(1000).hide(1000);
			        	}
	                });
	            });
	        }
	    });
	}
	
	//单搜某辆车后定时刷新
	$scope.oneVehicleReressh = function(){
		if (globalPlateNumber != undefined) {
            realLocalRefresh = $interval(function() {
                $scope.tabOnOneVehicle(globalPlateNumber);
            },5000, 60);
        }
	}

    //预警信息点击事件
    $scope.warningOnClick = function(data) {
    	$("#earlyWarningDisposalDiv").attr("style","display:inline");
    	//预警处置视图信息
    	$scope.warningDisposalView = data;
    	//处置流程信息
    	$scope.disposalProcess = data.disposalProcess;
    	//预警信息
    	$scope.earlyWarningInfo = data.earlyWarningInfo;
    	//预警事件、预警说明格式化
    	switch($scope.earlyWarningInfo.warningType)
    	{
	    	case '1':
	    		$scope.earlyWarningInfo['warningTypeExplain']="违反时间区域行驶";
	    		$scope.earlyWarningInfo['warningExplain'] = "车辆不按照时间区域行驶！";
	    		break;
	    	case '3':
	    		$scope.earlyWarningInfo['warningTypeExplain']="超速行驶";
	    		$scope.earlyWarningInfo['warningExplain'] = "车辆超速行驶，限速为100公里/小时，当前时速105公里/小时！";
	    		break;
	    	case '4':
	    		$scope.earlyWarningInfo['warningTypeExplain']="疲劳驾驶";
	    		$scope.earlyWarningInfo['warningExplain'] = "车辆连续行驶4小时以上未进行20分钟休息！";
	    		break;
    	}
    	//车辆信息
    	$scope.vehicleInfo = data.vehicleInfo;
    	$scope.vehicleInfo['vehicleKind'] = "物流车";
    	//处置细则
    	var date = new Date().setTime($scope.earlyWarningInfo.warningStartTime.time);
    	var dateAsString = $filter('date')(date, "yyyy年MM月dd日 HH时mm分ss秒"); 
    	$scope.disposalRules = dateAsString + $scope.vehicleInfo.vehicleType + $scope.vehicleInfo.plateNumber + $scope.earlyWarningInfo.warningExplain;
    };
    
    
  //修改预警处置流程
    $scope.updateDisposalProcess = function () {
    	$("#earlyWarningDisposalDiv").attr("style","display:none");
    	// 设置处置时间
		$scope.disposalProcess['jgDisposalTime'] = $filter('date')(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 设置处理人
		$scope.disposalProcess['jgUserId'] = $rootScope.fstrName;
		var jgDisposalInstructions = "";
    	switch($scope.disposalMethods)
    	{
	    	case '忽略':
	    		// 获取处置原因
	    		if($("#wrong").is(':checked')){
	    			jgDisposalInstructions += $("#wrong").val();
	    		}
	    		if($("#repeat").is(':checked')){
	    			jgDisposalInstructions += ",";
	    			jgDisposalInstructions += $("#repeat").val();
	    		}
	    		// 设置处置原因
	    		$scope.disposalProcess['jgDisposalInstructions'] = $scope.disposalMethods + ",原因： " + jgDisposalInstructions;
	    		// 设置处置流程状态为结束
	    		$scope.disposalProcess['state'] = 1;
	    		break;
	    	case '处置':
	    		// 设置下发部门
	    		$scope.disposalProcess['hyDepartment'] = $scope.hyDepartment;
	    		$scope.disposalProcess['jgDisposalInstructions'] = $scope.disposalMethods + ",下发部门： " + $scope.hyDepartment;
	      	  	break;
    	}
    	warningMapService.updateDisposalProcess($scope.disposalProcess,function (data) {
			 if (data.state == 200) {
				 SweetAlert.swal("", "处置成功", "success");
				 // 初始化页面
				 $scope.disposalProcess = {};
				 $scope.earlyWarningInfo = {};
				 $scope.vehicleInfoEarlyWarning = {};
				 $scope.disposalRules = "";
				 $scope.disposalMethods = "处置";
				 $scope.hyDepartment = "物流云平台";
				 $("#repeat").checked=false;
				 $("#wrong").checked=false;
//            	 $scope.queryWarningDisposalViewList();
			 }else{
				 SweetAlert.swal("", "处置失败", "warning");
			 }
		 }, function (err) {
			 SweetAlert.swal("", "处置失败", "warning");
		 })
    };
    
    //处置下发方式改变
    function checkDisposalMethods(value){
    	switch (value) {
		case "忽略":
			$("#reasonDiv").attr("style","display:inline");
			break;
		case "处置":
			$("#reasonDiv").attr("style","display:none");
			break;
		}
    }
    
	//模态窗口出不来加了个延时
	$timeout(function(){
		$(".form_datetime").datetimepicker({
			lang:'ch',
			timepicker:true,
			formatDate:'Y-m-d H:i'
		});
	},500);
	
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
            map = new Map("warningMap", {  
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
//    		var layer1 = new esri.layers.ArcGISDynamicMapServiceLayer("http://20.0.56.14:8399/arcgis/rest/services/cd_transperant/MapServer");
//    		layer1.setVisibleLayers([0,1,2]);//设置图层显示
//    		layer1.setOpacity(0.5);//设置图层透明度
//          map.addLayer(layer1);
            
            layerOfNew = new GraphicsLayer();
            map.addLayer(layerOfNew);
            //添加点击事件
     		layerOfNew.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
     		
            layerOfDangerous = new GraphicsLayer();
            map.addLayer(layerOfDangerous);
            //添加点击事件
     		layerOfDangerous.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
     		
            layerOfCold = new GraphicsLayer();
            map.addLayer(layerOfCold);
            layerOfCold.on("mouse-down",function(e){
            	$scope.showDialog(e);
            });
            
            layerOfOrdinary = new GraphicsLayer();
            map.addLayer(layerOfOrdinary);
            //添加点击事件
     		layerOfOrdinary.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
     		
     		layerOfNewOff = new GraphicsLayer();
            map.addLayer(layerOfNewOff);
            //添加点击事件
     		layerOfNewOff.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
     		
            layerOfDangerousOff = new GraphicsLayer();
            map.addLayer(layerOfDangerousOff);
            //添加点击事件
     		layerOfDangerousOff.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
     		
            layerOfColdOff = new GraphicsLayer();
            map.addLayer(layerOfColdOff);
            layerOfColdOff.on("mouse-down",function(e){
            	$scope.showDialog(e);
            });
            
            layerOfOrdinaryOff = new GraphicsLayer();
            map.addLayer(layerOfOrdinaryOff);
            //添加点击事件
     		layerOfOrdinaryOff.on("mouse-down",function(e){
     			$scope.showDialog(e);
            });
            
            layerOfNewText = new GraphicsLayer();
            map.addLayer(layerOfNewText);
            layerOfDangerousText = new GraphicsLayer();
            map.addLayer(layerOfDangerousText);
            layerOfColdText = new GraphicsLayer();
            map.addLayer(layerOfColdText);
            layerOfOrdinaryText = new GraphicsLayer();
            map.addLayer(layerOfOrdinaryText);
            
            layerOfNewText.setVisibility(false);
    		layerOfOrdinaryText.setVisibility(false);
    		layerOfDangerousText.setVisibility(false);
    		layerOfColdText.setVisibility(false);
            map.on("zoom", function(e){
            	if(map.getZoom()>mapZoom){
            		layerOfNewText.setVisibility(true);
            		layerOfOrdinaryText.setVisibility(true);
            		layerOfDangerousText.setVisibility(true);
            		layerOfColdText.setVisibility(true);
            	}else{
            		layerOfNewText.setVisibility(false);
            		layerOfOrdinaryText.setVisibility(false);
            		layerOfDangerousText.setVisibility(false);
            		layerOfColdText.setVisibility(false);
            	}
            });
            
          //点击车辆图标弹出气泡
       	 $scope.showDialog = function(e){
       		 var plateNumber = e.graphic.attributes.plateNumber;
       		 var warningType = e.graphic.attributes.warningType;
      		 var warningStartTime = e.graphic.attributes.warningStartTime;
      		 var gpsState = e.graphic.attributes.gpsState;
      		 var longitude = e.graphic.attributes.longitude;
      		 var latitude = e.graphic.attributes.latitude;
      		 var roadName = e.graphic.attributes.roadName;
      		 var speed = e.graphic.attributes.speed;
      		 var maxSpeed = e.graphic.attributes.maxSpeed;
      		 var vehicleInfo;
     		 var vehicleBaseInfo;
     		 warningMapService.queryVehicleDriveInfo(plateNumber,function (data) {
    			if (data.state == 200) {
    				 vehicleBaseInfo = data.messageBody.vehicleInfo;
    			}
    			var plate = plateNumber.replace('川','');
    			warningMapService.queryDataOfDialog(plate,departName,function (data) {
      			if (data.status == 0) {
      				 vehicleInfo = data.datas[0];
      			}
      			warning = vehicleInfo;
      			var vehicleType;
      			if(null != vehicleBaseInfo && undefined != vehicleBaseInfo){
      				accidentTotal = vehicleBaseInfo.accidentTotal;//事故次数
      				illegalTotal = vehicleBaseInfo.illegalTotal;//违法次数
      			}
      			vehicleType = vehicleBaseInfo.vehicleType.replace(/车/, "");
      			var title = "<div style='line-height:2;'><font style='font-weight:bold;font-size:16px;color:#000000;'>" +
	      			"车牌号码：<label class='pn' style='font-size:16px;'>"+plateNumber+"</label>——"+vehicleType+"物流车</font></div>" +
	      			"<div style='height:2px;border:none;border-top:1px solid #919191;width:98%;'></div>";
				var content = "<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:65px;'>"+
					"<div class='col-md-12 font_black_famil'>"+
	     			"<table style='font-size:16px;width:100%;'>" +
	  	     	    "<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>最新预警时间：</font>"+$scope.convertWarningTime(vehicleInfo.warningStartTime)+"</td></tr>" +
	  	     	    "<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>预警规则名：</font>"+$scope.convertWarningRuleName(vehicleInfo.warningRuleName)+"</td></tr>" +
	  	     	    "<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>最新预警地点：</font>"+$scope.convertRoadname(vehicleInfo.roadName)+"</td></tr>" +
	  	     	    "</table></div></div>"+
					"<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:130px;margin-top:10px;'>"+
	 				"<div class='col-md-12 font_black_famil'>"+
					"<div style='font-size: 20px;'>基本信息</div>"+
					"<div class='title_line_map' style='width:100%;'></div>"+
	     			"<table style='font-size:16px;width:100%;'>" +
	  	     	    "<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>入城证：</font>"+$scope.convertIntoCityCard(vehicleBaseInfo.intoCityCard)+"</td>" +
	    			"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>车牌类型：</font>"+$scope.convertPlateType(vehicleBaseInfo.plateType)+"</td></tr>" +
	    			"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>行业主管部门：</font>"+vehicleBaseInfo.competentAuthority+"</td></tr>" +
	    			"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>注册登记时日期：</font>"+$scope.convertDate(vehicleBaseInfo.registrationDate)+"</td></tr>" +
	     			"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>所属企业：</font>"+vehicleBaseInfo.ascriptionCompany+"</td></tr>" +
	    			"</table></div></div>"+
	    			"<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:150px;margin-top:10px;'>"+
         			"<div class='col-md-12 font_black_famil'>"+
    				"<div style='font-size: 16px;'>状态信息</div>"+
    				"<div class='title_line_map' style='width:100%;'></div>"+
         			"<table style='font-size:16px;width:100%'>" +
         		    "<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>年度累计报警次数：</font>"+vehicleInfo.yearWarningTimes+"次</td>" +
         		    "<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>在线离线状态：</font>"+$scope.convertOnlineState(gpsState)+"</td></tr>" +
         			"<tr><td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>车辆速度：</font><font style='color: green;font-weight:bold;'>"+parseInt(speed)+"</font>km/h</td>"+
         			"<td style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>年审状态：</font>"+vehicleBaseInfo.motTestState+"</td></tr>" +
         			"<tr><td style='font-size:14px;'><a style='color:red;' class='accidentTotal'><font style='font-weight:bold;font-size:14px;'>年度事故次数：</font>"+vehicleBaseInfo.accidentTotal+"次</a></td>" +
        			"<td style='font-size:14px;'><a style='color:red;' class='illegalTotal'><font style='font-weight:bold;font-size:14px;'>年度违法次数：</font>"+vehicleBaseInfo.illegalTotal+"次</a></td></tr>"+
          			"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>今日最高速度：</font><font style='color: green;font-weight:bold;'>"+parseInt(maxSpeed)+"</font>km/h</td></tr>" +
         			"<tr><td colspan='2' style='font-size:14px;'><font style='font-weight:bold;font-size:14px;'>今日行驶预警次数：</font><font style='color: red;font-weight:bold;'>"+vehicleInfo.warningTimes+"</font>次</td></tr>" +
        			"</table></div></div>"+
        			"<div class='row-fluid' style='border: 1px solid #ccc; width: 100%;height:100px;margin-top:10px;'>"+
	     			"<div class='col-md-12 font_black_famil'>"+
  					"<div style='font-size: 16px; '>操作</div>"+
  					"<div class='title_line_map' style='width:100%;'></div>"+
	     			"<table style='width:100%'><tr><td>&nbsp;&nbsp;<img id='warningButton' hideValue='"+plateNumber+"' src='assets/images/"
	     				+ (warning.disposalWay == 0 ? "warning_deal_button.png'" : "warning_enter_button.png'") + " style='cursor:pointer;'></td>"+
	     			"<td>&nbsp;&nbsp;<img id='toRealLine' hideType='all' hideValue='"+plateNumber+"' hideLongitude='"+longitude+"' hideLatitude='"+latitude+"' src='assets/images/real_line_button.png' style='cursor:pointer;'></td>" +
	     			"<td>&nbsp;&nbsp;<img id='toHisLine' hideType='all' hideValue='"+plateNumber+"' hideLongitude='"+longitude+"' hideLatitude='"+latitude+"' src='assets/images/his_line_button.png' style='cursor:pointer;'></td></tr>"+
	     			"<tr><td style='height:32px;'>&nbsp;&nbsp;<img id='showPlatePic' hideValue='"+vehicleBaseInfo.platePictureUrl+"' src='assets/images/show_pic_button.png' style='cursor:pointer;'></td>"+
	     			"<td colspan='2'>&nbsp;&nbsp;<img id='bayonetButton' hideValue='"+plateNumber+"' src='assets/images/rec_button.png' style='cursor:pointer;'></td></tr></table>"+
	     			"</div></div>";
    			
				var infoTemplate = new esri.InfoTemplate(title,content);
				e.graphic.setInfoTemplate(infoTemplate);
       		 }, function (err) {
       		 });
     		 }, function (err) {
     		 });
       	 }
        });   
    }
    /**********************************地图图层显示end**********************************/

    $scope.convertRoadname = function(name){
    	var roadname;
    	if(name==null || name==undefined){
    		roadname = "暂无街道数据";
    	}else{
    		roadname = name;
    	}
    	return roadname;
    }
    
    $scope.convertOnlineState = function(state){
    	var onlineState;
    	switch(state){
    		case 0:
	    		onlineState = "离线";
	    		break;
    		case 1:
    			onlineState = "在线";
    			break;
    		default :
    			onlineState = "离线";
    			break;
    	}
    	return onlineState;
    }
    
    //关闭dialog
    $("body").on("click","#closeDialog",function(){
    	$("div.dijitPopup").attr("style","display:none");
    });
    
    $("body").on("click","#warningButton",function(){
    	dijit.popup.close(dialog);
    	var plateNumber = $(this).attr("hideValue");
    	vehicle = $scope.vehicleInfoView;
    	$scope.toDisposalWindow(plateNumber,warning,accidentTotal,illegalTotal);//进入预警处置弹窗
    });
    
    $("body").on("click","#toRealLine",function(){
    	dijit.popup.close(dialog);
    	var plateNumber = $(this).attr("hideValue");
    	var rightCheckedName = [];//右侧已选在线离线及注册
    	var longitude = $(this).attr("hideLongitude");
    	var latitude = $(this).attr("hideLatitude");
    	var pageType = $(this).attr("hideType");
    	$("input[type=checkbox].rightCheckbox:checked").each(function(){
    		rightCheckedName.push(this.name);
    	});
    	var params={plateNumber:plateNumber, longitude:longitude, latitude:latitude,
    			type:"warningMap", rightCheckedName:rightCheckedName,pageType:pageType};
    	$state.go('app.warningDisposal.realLineMap',{params:JSON.stringify(params)});
    });
    
    $("body").on("click","#toHisLine",function(){
    	dijit.popup.close(dialog);
    	var plateNumber = $(this).attr("hideValue");
    	var rightCheckedName = [];//右侧已选在线离线及注册
    	var longitude = $(this).attr("hideLongitude");
    	var latitude = $(this).attr("hideLatitude");
    	var pageType = $(this).attr("hideType");
    	$("input[type=checkbox].rightCheckbox:checked").each(function(){
    		rightCheckedName.push(this.name);
    	});
    	var params={plateNumber:plateNumber, longitude:longitude, latitude:latitude,
    			type:"warningMap", rightCheckedName:rightCheckedName,pageType:pageType};
    	$state.go('app.warningDisposal.hisLineMap',{params:JSON.stringify(params)});
    });
    
    //查看车辆图片
    $("body").on("click","#showPlatePic",function(){
    	var pic = $(this).attr("hideValue");
    	var vehicleInfo = {};
    	vehicleInfo['platePictureUrl']=pic;
        $scope.temp = {
            'vehicleInfo':vehicleInfo
        };
        var modalInstance = $modal.open({
            templateUrl : 'assets/views/warningDisposal/vehiclePicture.html',
            controller : 'showVehiclePictureController',
            backdrop: 'static',
            keyboard: false,
            size : 'lg',
            resolve : {
                params : function() {
                    return $scope.temp;
                }
            }
        });
        modalInstance.result.then(function(result) {
            if(result == null){
                SweetAlert.swal({
                    title: "无数据",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
            }
        }, function(err) {
        });
    });
    
    //处置页面
    $scope.toDisposalWindow = function(plateNumber,warning,accidentTotal,illegalTotal) {
        $scope.temp = {
            'plateNumber':plateNumber,
            'warning':warning,
            'accidentTotal':accidentTotal,
            'illegalTotal':illegalTotal
        };
        var modalInstance = $modal.open({
            templateUrl : 'assets/views/warningDisposal/warningDisposalWindow.html',
            controller : 'warningDisposalWindowCtrl',
            backdrop: 'static',
            keyboard: false,
            size : 'lg',
            resolve : {
                params : function() {
                    return $scope.temp;
                    $scope.query("");
                }
            }
            
        });
        modalInstance.result.then(
        	function(result) {
	            if(result == null){
	                SweetAlert.swal({
	                    title: "无数据",
	//                  type: "success",
	                    timer: 2000,
	                    confirmButtonText: "确定"
	                });
	            }
	        }, 
	        function(err) {
	        }
        );
    };
    
     //根据车辆类型搜索车辆信息
	 $scope.query = function(vehicleType){
		 $scope.vehicleModel.vehicleType = vehicleType;
		 var plateNumber = "";
		 warningMapService.queryVehicleInfoByVehicleType(departName,isRegisterArea,plateNumber,function (data) {
			 if (data.status == 0) {
				$scope.vehicleInfoView = data;
				$scope.locations = [];//清空集合
				var result = $scope.vehicleInfoView.datas;
	            graps = [];
	            showLocation(result);
			}
		 }, function (err) {
		 })
	 };

	 function showLocation(result) {
         layerOfNew.clear();
         layerOfDangerous.clear();
         layerOfCold.clear();
         layerOfOrdinary.clear();
         layerOfNewOff.clear();
         layerOfDangerousOff.clear();
         layerOfColdOff.clear();
         layerOfOrdinaryOff.clear();
         layerOfNewText.clear();
         layerOfDangerousText.clear();
         layerOfColdText.clear();
         layerOfOrdinaryText.clear();
         for(var i = 0;i<result.length;i++){
             var pic;
             var vType = $scope.convertVehicleType(result[i].vehicleType);
             var picUrl;
             if(result[i].warningState == 1 || result[i].warningState == 2){
                 picUrl = "assets/images/warning_off.png";
             }else if(result[i].warningState == 0){
                 picUrl = "assets/images/warning.gif";
             }else{
                 picUrl = "assets/images/warning_off.png";
             }
             pic = new esri.symbol.PictureMarkerSymbol(picUrl,imageSize,imageSize);
             //创建图片样式符合
             var pt = new esri.geometry.Point(result[i].longitude,result[i].latitude);//创建一个点对象
             var attr = {"vehicleType":vType,"plateNumber":result[i].plateNumber,"warningType":result[i].warningType,
                 "warningStartTime":result[i].warningStartTime,"gpsState":result[i].gpsState,
                 "longitude":result[i].longitude,"latitude":result[i].latitude,"roadName":result[i].roadName,"speed":result[i].speed,"maxSpeed":result[i].maxSpeed};
             map.infoWindow.resize(400,580);
             gra = new esri.Graphic(pt,pic,attr);//设置样式
             textgraph = new esri.Graphic(pt,new esri.symbol.TextSymbol(result[i].plateNumber).setOffset(20, 10));
             //把图层实例放入数组中以便后面有针对性的移除
             if(graps.length<=len){
                 graps.push(gra);
                 textgraphs.push(textgraph);
             }
             layerOfNew.add(gra);
             layerOfNewText.add(textgraph);
         }
     }
	 
	 //转换气泡中的最近预警时间
	 $scope.convertWarningTime = function(warningEndTime){
		 var endTime;
		 if(warningEndTime != null && warningEndTime!=""){
			 endTime = $filter("date")(warningEndTime, "yyyy-MM-dd HH:mm:ss");
		 }else{
			 endTime = "";
		 }
		 return endTime;
	 }
	 
	 //转换日期，格式：年月日
	 $scope.convertDate = function(beforeDate){
		 var newDate;
		 if(beforeDate != null){
			 newDate = $filter("date")(beforeDate.time, "yyyy-MM-dd");
		 }else{
			 newDate = "";
		 }
		 return newDate;
	 }
	 
	 $scope.convertWarningType = function(warningType){
		var realWarningType;
		switch(warningType)
     	{
     	case '1':
    		return "时间区域预警";
    		break;
    	case '3':
    		return "超速预警";
    		break;
    	case '4':
    		return "疲劳驾驶预警";
    		break;
 	    	default:
 	    		realWarningType = "";
				break;
     	}
		return realWarningType;
	 }
	 //预警规则名转换
	 $scope.convertWarningRuleName = function(text){
		 var realWarningRuleName;
		 switch(text)
		 {
		 case null:
		 case "":
		 case undefined:
			 realWarningRuleName = "无";
			 break;
		 default:
			 realWarningRuleName = text;
		 break;
		 }
		 return realWarningRuleName;
	 }
	 
	//车辆类型转换
	 $scope.convertPlateType = function(plateType){
		 var realPlateType;
		 switch(plateType)
		 {
		 case '01':
			 realPlateType = "大车";
			 break;
		 case '02':
			 realPlateType = "小车";
			 break;
		 default:
			 realPlateType = "";
		 	break;
		 }
		 return realPlateType;
	 }
	 
	 //设置默认车辆图片
	 $scope.convertPic = function(picUrl){
		 var url;
		 if(picUrl==null || picUrl==undefined || picUrl==""){
			 url = "plate_none.png";
		 }else{
			 url = picUrl;
		 }
		 return url;
	 }
	 
	 //统计车辆数据
	 $scope.queryEarlyWarningInfoCounts = function(){
		 warningMapService.queryEarlyWarningInfoCounts("",departName,isRegisterArea,function (data) {
				if (data.status == 0) {
					 $scope.earlyWarningInfoCounts =data.datas[0];
					 console.log($scope.earlyWarningInfoCounts);
				 }
			 }, function (err) {
			 });
	 }
	 
	 //点击查询车辆历史轨迹
     /*$scope.queryHistTra = function(){
     	refresh = false;
     	if(!hisRefresh){
     		hisRefresh = true;
     		$interval.cancel(realLocalRefresh);
     		loadLocalTimes = 0;
         	timeRefresh = setTimeout(function(){showLine(map);}, 1000, 1);
     	}
     }*/
     
     //清除所有车辆图层
     $scope.hideAllGraphics = function(){
    	 layerOfNew.setVisibility(false);
 		 layerOfDangerous.setVisibility(false);
 		 layerOfCold.setVisibility(false);
 		 layerOfOrdinary.setVisibility(false);
 		 layerOfNewOff.setVisibility(false);
		 layerOfDangerousOff.setVisibility(false);
		 layerOfColdOff.setVisibility(false);
		 layerOfOrdinaryOff.setVisibility(false);
 		 layerOfNewText.setVisibility(false);
		 layerOfDangerousText.setVisibility(false);
		 layerOfColdText.setVisibility(false);
		 layerOfOrdinaryText.setVisibility(false);
     }
     
     //显示所有车辆图层
     $scope.showAllGraphics = function(){
    	 layerOfNew.setVisibility(true);
 		 layerOfDangerous.setVisibility(true);
 		 layerOfCold.setVisibility(true);
 		 layerOfOrdinary.setVisibility(true);
 		 layerOfNewOff.setVisibility(true);
		 layerOfDangerousOff.setVisibility(true);
		 layerOfColdOff.setVisibility(true);
		 layerOfOrdinaryOff.setVisibility(true);
     }
     
     //加点
     /*function showLocation(x, y, pic,type,plateNumber,warningType,warningStartTime,gpsState,roadName,speed,maxSpeed) {
     	//创建图片样式符合
     	var pt = new esri.geometry.Point(x,y);//创建一个点对象
     	var attr = {"vehicleType":type,"plateNumber":plateNumber,"warningType":warningType,
     			"warningStartTime":warningStartTime,"gpsState":gpsState,
     			"longitude":x,"latitude":y,"roadName":roadName,"speed":speed,"maxSpeed":maxSpeed};
     	map.infoWindow.resize(500,580);
     	gra = new esri.Graphic(pt,pic,attr);//设置样式
     	textgraph = new esri.Graphic(pt,new esri.symbol.TextSymbol(plateNumber).setOffset(20, 10));
     	//把图层实例放入数组中以便后面有针对性的移除
     	if(graps.length<=len){
     		graps.push(gra);
     		textgraphs.push(textgraph);
     	}
     	layerOfNew.add(gra);
     	layerOfNewText.add(textgraph);
         /!*switch(type){
            case "新能源":
     			if(gpsState==1){
     				layerOfNew.add(gra);
     			}else{
     				layerOfNewOff.add(gra);
     			}
	     		layerOfNewText.add(textgraph);
	     		break;
     		case "冷链车":
     			if(gpsState==1){
     				layerOfCold.add(gra);
     			}else{
     				layerOfColdOff.add(gra);
     			}
	     		layerOfColdText.add(textgraph);
	     		break;
     		case "危化品":
	     		if(gpsState==1){
     				layerOfDangerous.add(gra);
     			}else{
     				layerOfDangerousOff.add(gra);
     			}
	     		layerOfDangerousText.add(textgraph);
	     		break;
     		case "普通车":
     			if(gpsState==1){
     				layerOfOrdinary.add(gra);
     			}else{
     				layerOfOrdinaryOff.add(gra);
     			}
	     		layerOfOrdinaryText.add(textgraph);
	     		break;
     	}*!/
     };*/
     
     /********监听在线离线复选框状态 start**********************/
     $scope.lineStateName="";
     $scope.lineStateValue=false;
     $scope.lineCheck = function($event){
    	 $scope.lineStateName = $event.target.value;
    	 $scope.lineStateValue = $event.target.checked;
     }
     $scope.$watch('lineStateName + lineStateValue',function(){
    	 if(realLocalRefresh!=undefined){
 			$interval.cancel(realLocalRefresh);
 		 }
     	 if($scope.lineStateValue){
     		switch($scope.lineStateName){
     			case "online":
					layerOfNew.setVisibility(true);
					layerOfCold.setVisibility(true);
					layerOfDangerous.setVisibility(true);
					layerOfOrdinary.setVisibility(true);
     		 		break;
     			case "offline":
					layerOfNewOff.setVisibility(true);
					layerOfColdOff.setVisibility(true);
					layerOfDangerousOff.setVisibility(true);
					layerOfOrdinaryOff.setVisibility(true);
     				break;
     		}
     	}else{
     		switch($scope.lineStateName){
	 			case "online":
					layerOfNew.setVisibility(false);
					layerOfCold.setVisibility(false);
					layerOfDangerous.setVisibility(false);
					layerOfOrdinary.setVisibility(false);
	 		 		break;
	 			case "offline":
					layerOfNewOff.setVisibility(false);
					layerOfColdOff.setVisibility(false);
					layerOfDangerousOff.setVisibility(false);
					layerOfOrdinaryOff.setVisibility(false);
	 				break;
     		}
     	}
     });
     /********监听在线离线复选框状态 end**********************/
     
     /********监听注册复选框状态 start**********************/
     $scope.registerCheck = function($event){
    	 if(realLocalRefresh!=undefined){
 			$interval.cancel(realLocalRefresh);
 		 }
    	 var a = $('#register').is(':checked');
    	 if(a){
    		 isRegisterArea = true;
    	 }else{
    		 isRegisterArea = false;
    	 }
    	 layerOfNew.clear();
 		layerOfDangerous.clear();
 		layerOfCold.clear();
 		layerOfOrdinary.clear();
 		layerOfNewOff.clear();
 		layerOfDangerousOff.clear();
 		layerOfColdOff.clear();
 		layerOfOrdinaryOff.clear();
 		layerOfNewText.clear();
 		layerOfDangerousText.clear();
 		layerOfColdText.clear();
 		layerOfOrdinaryText.clear();
 		$scope.query();
     }
    /********监听注册复选框状态 end**********************/
     
     function showAlert(){
    	 swal({
             title: "车牌号码不能为空！",
             type: "error",
             timer: 2000,
             confirmButtonText: "确定"
         });
     	}
    
   	//历史轨迹
 	/*function showLine(map){
 		 haveShowLine = true; //标记行驶轨迹已打开
 		 var hisLocations = $scope.hisLocations;	//包含车辆经纬度的字符串
         var paths = [];
         if(hisLocations.length > 0){
         	for(var i = 0;i<showLineLength;i++){
	           	var local = hisLocations[i].split("-");
	           	var his = [];
	           	his[0] = local[1]*1;
	           	his[1] = local[0]*1;
	           	paths.push(his);
       		}
         	showLineLength = showLineLength+2;
         	if(showLineLength>hisLocations.length-2 & showLineLength<hisLocations.length){
         		showLineLength = hisLocations.length;
         	}
         }
		  	//在地图上连线
		    polylineJson={"paths": [paths],"spatialReference":{"wkid":4326}}; 
		    var graphicsLayer = new esri.layers.GraphicsLayer();//添加线的图层，方便清除上一个图层所画的线
	    	map.graphics.clear();//清除地图上现有的点
	    	map.removeLayer(graphicsLayer);//清除地图上的线
		    var polyline=new esri.geometry.Polyline(polylineJson);
		    var sys=new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new esri.Color([0,0,255]),3);
		    var graphic2=new esri.Graphic(polyline,sys);
		   	map.graphics.add(graphic2); 
		   	
		   	if(hisLocations.length > 0 ){
		   		//前面+n，这里就要-(n-1)
		   		var local = hisLocations[showLineLength-3].split("-");
		   		if(local[2] == "新能源"){
		   			var pic = new esri.symbol.PictureMarkerSymbol("assets/images/new-energy-vehicle-warning.gif",imageSize,imageSize);
		   		}else if(local[2] == "危化品"){
		   			var pic = new esri.symbol.PictureMarkerSymbol("assets/images/dangerous-goods-vehicle-warning.gif",imageSize,imageSize);
		   		}else if(local[2] == "冷链车"){
		   			var pic = new esri.symbol.PictureMarkerSymbol("assets/images/cold-chain-vehicle-warning.gif",imageSize,imageSize);
		   		}else if(local[2] == "普通车"){
		   			var pic = new esri.symbol.PictureMarkerSymbol("assets/images/ordinary-vehicle-warning.gif",imageSize,imageSize);
		   		}
		   		showLocation(local[1]*1,local[0]*1,local[2]*1,local[3]*1,pic);
		   		
		   		var centerPoint = new esri.geometry.Point(local[1]*1,local[0]*1);
//			   	map.centerAndZoom(centerPoint,6);//内网地图缩放等级
		   		map.centerAndZoom(centerPoint,12);
		   	}
		   	
		   	//在地图上添加点
		   	function showLocation(x, y, type, plateNumber, pic) {  
		   		var pt = new esri.geometry.Point(x,y);//创建一个点对象
		   		var attr = {"vehicleType":type,"plateNumber":plateNumber};
		   		var infoTemplate = new esri.InfoTemplate("车牌号码：${plateNumber}","车辆类型：${vehicleType}");
		   		var gp = new esri.Graphic(pt,pic,attr);//设置样式
		   		map.graphics.add(gp);//添加到图层中        
		   	};
		   	if(hisRefresh){
		   		//js控制ID为hisButton的按钮的点击
		   		$("#hisButton").trigger("click"); 

		   		if(showLineLength<=hisLocations.length){
		   			showLineRefresh = $interval(function(){
		   				showLine(map);
		   			},1000,1);
		   		}else{
		   			showLineLength = 2;
		   		}
		   	}
		   	
 	};*/
 	
 	//设置div可拖动
    accident = document.getElementById("accident");
    illegal = document.getElementById("illegal");
    bayonet = document.getElementById("bayonet");
    bayonetAddressDiv = document.getElementById("bayonetAddressDiv");
    earlyWarningDisposalDiv = document.getElementById("earlyWarningDisposalDiv");
    document.onmouseup = function(){
        document.onmousemove = null;//鼠标举起，停止
    }
    $scope.mouseEvent = function(id){
    	id.onmousedown=function(e){
            posX = event.x - id.offsetLeft;//获得横坐标x
            posY = event.y - id.offsetTop;//获得纵坐标y
            document.onmousemove = eventMousemove;//调用函数，只要一直按着按钮就能一直调用
        }
        function eventMousemove(ev){
            if(ev==null) ev = window.event;//IE
            id.style.left = (ev.clientX - posX) + "px";
            id.style.top = (ev.clientY - posY) + "px";
        }
    }
    $scope.mouseEvent(accident);
    $scope.mouseEvent(illegal);
    $scope.mouseEvent(bayonet);
    $scope.mouseEvent(bayonetAddressDiv);
    $scope.mouseEvent(earlyWarningDisposalDiv);
 	
 	//气泡中事故次数点击事件
 	$("body").on("click","a.accidentTotal",function(){
 		$scope.queryConditionData.plateNumber = $("label.pn").text();
 		$scope.queryConditionData.requestType = "accident";
 		warningMapService.searchAccidentInfoNoPage($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				 $scope.accidentInfoList =data.messageBody.accidentInfoList; 
				 $("#illegal").attr("style","display:none");
				 $("#accident").attr("style","display:inline");
			 }
		 });
 	});
 	
 	//气泡中违法次数点击事件
 	$("body").on("click","a.illegalTotal",function(){
 		$scope.queryConditionData.plateNumber = $("label.pn").text();
 		$scope.queryConditionData.requestType = "illegal";
 		warningMapService.searchIllegalInfoNoPage($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				 $scope.illegalInfoList =data.messageBody.illegalInfoList; 
				 $("#accident").attr("style","display:none");
				 $("#illegal").attr("style","display:inline");
			 }
		 });
 	});
 	
 	//气泡中当日卡口过车查询点击事件
 	$("body").on("click","#bayonetButton",function(){
 		$scope.queryConditionData.plateNumber = $(this).attr("hideValue");
 		//从当天0点开始，保留--colin
// 		var nowDate = new Date();
// 		var today = Date.parse(new Date(nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate()));
// 		$scope.queryConditionData.startTime = $filter("date")(today, "yyyy-MM-dd HH:mm");
//	    $scope.queryConditionData.endTime = $filter('date')(new Date(), "yyyy-MM-dd HH:mm");
 		$scope.queryConditionData.timePrevious = "1440";//24小时内
	    $scope.queryAllRecPlateInfo();
 	});
 	
 	//点击卡口信息显示位置和图片
	$scope.bayonetOnClick = function(data){
		$scope.bayonetInfo  = data;
		$("#bayonetAddressDiv").attr("style","display:inline");
	}
    
 	$("#closeAccident").click(function(){
    	$("#accident").attr("style","display:none");
    	$scope.accidentInfoList = {};
    });
 	
 	$("#closeIllegal").click(function(){
    	$("#illegal").attr("style","display:none");
    	$scope.illegalInfoList = {};
    });
 	
 	$("#closeBayonet").click(function(){
 		$("#bayonet").attr("style","display:none");
 		$scope.bayonetData = {};
 	});
 	
 	$("#closeBayonetAddressDiv").click(function(){
 		$("#bayonetAddressDiv").attr("style","display:none");
 	});
 	
    //清除行驶轨迹还原地图
    /*$scope.clearHisLine = function(){
    	if(haveShowLine){
			var graphicses = map.graphics.graphics;
			for(var i=0;i<graphicses.length;i++){
				if(graphicses[i].geometry.type=="polyline"){
					map.graphics.remove(graphicses[i]);
				}
			}
			clearTimeout(timeRefresh);
			haveShowLine = false;
	        $("#infoDiv").attr("style","display:inline");
	    	$("#trajectoryDiv").attr("style","display:none");
	    	$scope.query();
		}
    }*/
    
    //搜索车牌号码图标的点击事件
    $("#plateNumberSelect").click(function(){
    	$scope.queryConditionData.currentPage = 1;
        if(layerOfOnVehicle!=null & layerOfOnVehicle!=undefined){
        	layerOfOnVehicle.clear();
        }
        if(textGrapLayer != null & textGrapLayer != undefined) {
        	textGrapLayer.clear();
        }
    	hisRefresh = false;
    	if($scope.plateInfo.location == null){
    		$scope.plateInfo.location = "A";
    	}
    	$scope.queryConditionData.onLineState = "";
    	$scope.queryConditionData.plateType = "";
    	$scope.queryConditionData.competentAuthority = "";
    	$scope.queryConditionData.ascriptionCompany = "";
    	$scope.queryFunction();
    	
		$("#oneWarningTotalDiv").hide();
		$("#vehicleInfoDiv").attr("style","display:inline");
		
		//点击查询图标后把水滴标记去掉
		if(singleCar != null && singleCar != ''){
    		map.graphics.remove(singleCar);
    	}
		//重新定位地图中心坐标及缩放大小
		var pt = new esri.geometry.Point(104.0657754083, 30.6583098090);
		
		if(globalPlateNumber!=null && globalPlateNumber!=""){
		    //立即取消实时位置刷新
		   	$interval.cancel(realLocalRefresh);
		   	globalPlateNumber = "";
		   	loadLocalTimes = 0;
		}
    });
    
    //根据分类筛选车辆
    /*function filterVechleByTypes(selectOption){
    	if(selectOption==''){
    		layerOfNew.setVisibility(true);
    		layerOfDangerous.setVisibility(true);
    		layerOfCold.setVisibility(true);
    		layerOfOrdinary.setVisibility(true);
    	}else{
			switch(selectOption)
	    	{
		    	case '新能源':
		    		layerOfNew.setVisibility(true);
		    		layerOfDangerous.setVisibility(false);
		    		layerOfCold.setVisibility(false);
		    		layerOfOrdinary.setVisibility(false);
		    		break;
		    	case '冷链车':
		    		layerOfNew.setVisibility(false);
		    		layerOfDangerous.setVisibility(true);
		    		layerOfCold.setVisibility(false);
		    		layerOfOrdinary.setVisibility(false);
		      	  	break;
		    	case '危化品':
		    		layerOfNew.setVisibility(false);
		    		layerOfDangerous.setVisibility(false);
		    		layerOfCold.setVisibility(true);
		    		layerOfOrdinary.setVisibility(false);
		    		break;
		    	case '普通车':
		    		layerOfNew.setVisibility(false);
		    		layerOfDangerous.setVisibility(false);
		    		layerOfCold.setVisibility(false);
		    		layerOfOrdinary.setVisibility(true);
		    		break;
	    	}
    	}
    }*/
    
    //每10秒刷新一次预警信息
//    warningRefresh = $interval(function(){
//    	$scope.queryWarningDisposalViewList();
//    },10000);
    
    //定时刷新车辆位置--5秒
    queryRefresh = $interval(function(){
    	$scope.query("");
    	$scope.queryEarlyWarningInfoCounts();
    },30000);
    
    $scope.checkedbox = function(){
	    var hisOrRelParams;
		if($stateParams.params!="" && $stateParams.params!=undefined){
			hisOrRelParams=JSON.parse($stateParams.params); //历史/实时轨迹返回的参数
			if(hisOrRelParams.pageType=="all"){
				var rightCheckedNames = hisOrRelParams.rightCheckedName;
				if(rightCheckedNames.length>0){
					$scope.lineStateValue = true;
					for(var i=0;i<rightCheckedNames.length;i++){
						$("[name="+rightCheckedNames[i]+"]:checkbox").prop("checked", true);
						$scope.lineStateName = rightCheckedNames[i];
					}
				}
			    var pt = new esri.geometry.Point(hisOrRelParams.longitude,hisOrRelParams.latitude);
				map.centerAt(pt);
			}else{
				$scope.showall = false;
				$("#plateNumber").val(hisOrRelParams.plateNumber.replace("川A",""));
				$("#plateNumberSelect").click();
				var data = [];
				var e = "";
				data["plateNumber"] = hisOrRelParams.plateNumber;
				$scope.checkedVehicle(data,e);
			}
		}
    }
    
    //确保关闭页面或浏览器后销毁定时器
    $scope.$on('$destroy',function(){  
    	$("[name=新能源]:checkbox").prop("checked", false);
		$("[name=冷链]:checkbox").prop("checked", false);
		$("[name=危化品]:checkbox").prop("checked", false);
		$("[name=普通]:checkbox").prop("checked", false);
    	$interval.cancel(realLocalRefresh);
    	$interval.cancel(queryRefresh);
        loadLocalTimes = 0;
        globalPlateNumber = null;
        $interval.cancel(warningRefresh);
        $rootScope.warningCheckedName="";
        $rootScope.warningCheckedState=false;
        dijit.popup.close(dialog);
        $("body").off("click");
    })
    
    //取消预警方框
    $("#cancleWarning").click(function(){
    	$("#earlyWarningDisposalDiv").attr("style","display:none");
    });
    
    $scope.convertVehicleType = function(type){
    	if(type!=undefined && type!=""){
    		switch(type)
	    	{
		    	case 'A1':
		    		return "新能源";
		    		break;
		    	case 'A2':
		    		return "冷链车"
		      	  	break;
		    	case 'A3':
		    		return "危化品"
		    		break;
		    	case 'A4':
		    		return "普通车"
		    		break;
	    	}
    	}
    }
    
    //入城证转换
	 $scope.convertIntoCityCard = function(intoCityCard){
		 var realIntoCityCard;
		 switch(intoCityCard)
		 {
		 case 'A':
			 realIntoCityCard = "A";
			 break;
		 case 'M':
			 realIntoCityCard = "A1";
			 break;
		 case 'N':
			 realIntoCityCard = "A2";
			 break;
		 case 'E':
			 realIntoCityCard = "A3";
			 break;
		 case 'B':
			 realIntoCityCard = "B";
			 break;
		 case 'P':
			 realIntoCityCard = "B1";
			 break;
		 case 'Q':
			 realIntoCityCard = "B2";
			 break;
		 case 'C':
			 realIntoCityCard = "C";
			 break;
		 case 'R':
			 realIntoCityCard = "C1";
			 break;
		 case 'S':
			 realIntoCityCard = "C2";
			 break;
		 case 'T':
			 realIntoCityCard = "C3";
			 break;
		 case 'U':
			 realIntoCityCard = "D";
			 break;
		 case 'D':
			 realIntoCityCard = "D1";
			 break;
		 case 'F':
			 realIntoCityCard = "D2";
			 break;
		 case 'V':
			 realIntoCityCard = "E";
			 break;
		 case 'G':
			 realIntoCityCard = "F";
			 break;
		 case 'H':
			 realIntoCityCard = "G1";
			 break;
		 case 'I':
			 realIntoCityCard = "G2";
			 break;
		 case 'J':
			 realIntoCityCard = "G3";
			 break;
		 case 'K':
			 realIntoCityCard = "G4";
			 break;
		 case 'L':
			 realIntoCityCard = "H";
			 break;
		 case 'W1':
			 realIntoCityCard = "交通车";
			 break;
		 case 'Z':
			 realIntoCityCard = "快递";
			 break;
		 case 'X':
			 realIntoCityCard = "三绿";
			 break;
		 case 'W':
			 realIntoCityCard = "校车";
			 break;
		 case 'Y':
			 realIntoCityCard = "园林";
			 break;
		 case 'X1':
			 realIntoCityCard = "早餐";
			 break;
		 default:
			 realIntoCityCard = "H";
		 break;
		 }
		 return realIntoCityCard;
	 }
    
	 $scope.initZoomAndCenter = function(){
		 switch ($rootScope.depName) {
		case "01":
			showCenterPoint = [104.010229,30.623509];
//			showZoom = 5;
			showZoom = 14;
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
			showZoom = 14;
			break;
		}
	 }
    
	//初始化查询条件
	$scope.initZoomAndCenter();
    $scope.showMap();
    $scope.queryEarlyWarningInfoCounts();
	$scope.query("");
	$scope.checkedbox();
	$scope.oneVehicleReressh();
	     
} ]);

//预警类型格式化
app.filter('reverse', function() { //可以注入依赖
    return function(text) {
    	switch(text)
    	{
    	case '1':
    		return "时间区域预警";
    		break;
    	case '3':
    		return "超速预警";
    		break;
    	case '4':
    		return "疲劳驾驶预警";
    		break;
    	}
    }
});

//状态格式转化
app.filter('chineseState', function() { 
    return function(text) {
		switch(text)
		{
			case "0":
				return "离线";
				break;
			case "1":
				return "在线";
				break;
			default:
				return "离线";
				break;
		}
    }
});

//车牌类别转化
app.filter('chineseType', function() { 
	return function(text) {
		switch(text)
		{
		case '01':
			return "大车";
			break;
		case '02':
			return "小车";
			break;
		default : 
			return "";
		}
	}
});
