/**
 * 车辆信息查询
 */

app.controller('warningDisposalWindowCtrl', ['$rootScope','$scope','$stateParams','$modalInstance','params','$state','$modal', '$log','$filter','SweetAlert','warningDisposalService','$interval','SweetAlert',
  function($rootScope,$scope, $stateParams,$modalInstance, params,$state, $modal, $log,$filter,SweetAlert,warningDisposalService,$interval,SweetAlert) {
    
    $scope.disposalMethods = "处置";
//    $scope.hyDepartment = "一分局一大队";
    $scope.disposalProcess = {};
    $scope.otherReason = {};//用于存放选择其它时输入的原因
    $scope.plateNumber = params.plateNumber;//车牌号码
//    $scope.vehicle = params.vehicle;//从接口传递过来的全部信息
    $scope.earlyWarningInfo = params.warning;//预警信息
    $scope.accidentTotal = params.accidentTotal;//事故次数
    $scope.illegalTotal = params.illegalTotal;//违法次数
    $scope.warningId;//预警Id
    var depName = $rootScope.depName;//分局名称
    
    $scope.vehicleInfo = {};
//    console.log($scope.vehicleInfo);
//    console.log($scope.earlyWarningInfo);
    
  //取消
    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
	
//    console.log($stateParams.plateNumber);
    
    //根据车牌号搜索
	 $scope.queryVehicleInfoByPlateNumber = function(){
		 warningDisposalService.queryVehicleInfoByPlateNumber($scope.plateNumber,function (data) {
	            if (data.state == 200) {
	            	$scope.vehicleInfo = data.messageBody.vehicleInfoList[0];
	            	switch($scope.earlyWarningInfo.warningType)
	   		    	{
	   			    	case '1':
	   			    		$scope.earlyWarningInfo['warningTypeExplain']="违反时间区域行驶";
	   			    		$scope.earlyWarningInfo['warningExplain'] = "车辆不按照时间区域行驶！【"
	   			    			+$scope.earlyWarningInfo.warningRuleName +"】";
	   			    		break;
		   			    case '3':
		   			    		$scope.earlyWarningInfo['warningTypeExplain']="超速行驶";
		   			    		if(undefined == $scope.earlyWarningInfo.id || $scope.earlyWarningInfo.id == '' || $scope.earlyWarningInfo.id == null){
		   			    			if(null != $scope.earlyWarningInfo.speed && undefined != $scope.earlyWarningInfo.speed){
		   			    				$scope.earlyWarningInfo['warningExplain'] = "超速行驶，最大时速" 
		   			    					+ parseInt($scope.earlyWarningInfo.speed) + "公里/小时！【"
		   			    					+$scope.earlyWarningInfo.warningRuleName +"】";
		   			    			}else{
		   			    				$scope.earlyWarningInfo['warningExplain'] = "超速行驶【"
		   			    					+$scope.earlyWarningInfo.warningRuleName +"】";
		   			    			}
		   			    		}else{
		   			    			if(null != $scope.earlyWarningInfo.limitedMaxSpeed && undefined != $scope.earlyWarningInfo.limitedMaxSpeed
		   			    					&& null != $scope.earlyWarningInfo.speed && undefined != $scope.earlyWarningInfo.speed){
		   			    				$scope.earlyWarningInfo['warningExplain'] = "超速行驶，限速为" 
		   			    					+ $scope.earlyWarningInfo.limitedMaxSpeed + "公里/小时，最大时速" 
		   			    					+ parseInt($scope.earlyWarningInfo.speed) + "公里/小时！【"
		   			    					+$scope.earlyWarningInfo.warningRuleName +"】";
		   			    			}else{
		   			    				$scope.earlyWarningInfo['warningExplain'] = "超速行驶【"
		   			    					+$scope.earlyWarningInfo.warningRuleName +"】";
		   			    			}
		   			    		}
		   			    		break;
		   			    	case '4':
		   			    		$scope.earlyWarningInfo['warningTypeExplain']="疲劳驾驶";
		   			    		$scope.earlyWarningInfo['warningExplain'] = "疲劳驾驶【"
		   			    			+$scope.earlyWarningInfo.warningRuleName +"】";
		   			    		break;
		   		    	}
		            	var date = new Date().setTime($scope.earlyWarningInfo.warningStartTime);
		   		    	var dateAsString = $filter('date')(date, "yyyy年MM月dd日 HH时mm分ss秒"); 
		   		    	if(undefined != $scope.vehicleInfo && null != $scope.vehicleInfo){
		   		    		if(undefined != $scope.vehicleInfo.vehicleType &&  null != $scope.vehicleInfo.vehicleType && undefined != $scope.earlyWarningInfo.warningExplain && null != $scope.earlyWarningInfo.warningExplain){
			   		    		$scope.disposalRules = dateAsString + $scope.vehicleInfo.vehicleType + "["+$scope.vehicleInfo.plateNumber+"]" + $scope.earlyWarningInfo.warningExplain;
			   		    	}else{
			   		    		$scope.disposalRules = "无";
			   		    	}
		   		    	}else{
		   		    		$scope.disposalRules = "未获取到该车辆的车辆信息，"+ $scope.earlyWarningInfo.warningExplain
				    		SweetAlert.swal("", "未获取到车辆信息", "warning");
		   		    	}
		   		    	
		   		    	if($scope.earlyWarningInfo.warningExplain == "" || $scope.earlyWarningInfo.warningExplain == null || $scope.earlyWarningInfo.warningExplain == undefined
		   		    			|| $scope.earlyWarningInfo.roadName == "" || $scope.earlyWarningInfo.roadName == null || $scope.earlyWarningInfo.roadName == undefined){
		   		    		$scope.earlyWarningInfo.content = "无";
		   		    	}else{
		   		    		$scope.earlyWarningInfo.content = "车牌号为【"+$scope.vehicleInfo.plateNumber+"】"+"车辆在"+$scope.earlyWarningInfo.roadName + $scope.earlyWarningInfo.warningExplain;
		   		    	}
		            }
		        }, function (err) {
		        })
		    }	
    
  //根据预警Id查询预警处置信息，预警信息，车辆信息
//    $scope.queryOneWarningDisposalInfo = function () {
//    	if(null == $scope.warningId || "" == $scope.warningId){
//    		 $modalInstance.dismiss('cancel');
//    		SweetAlert.swal("", "该车辆没有预警信息！", "warning");
//    	}else{
//    		warningDisposalService.queryOneWarningDisposalInfo($scope.warningId,function (data) {
//   			 if (data.state == 200) {
   				//预警处置视图信息
//   				$scope.warningDisposalView = data.messageBody.queryOneWarningDisposalInfo;
//   				data = $scope.warningDisposalView;
//   				console.log($scope.warningDisposalView);
   				//处置流程信息
//   		    	$scope.disposalProcess = $scope.warningDisposalView.disposalProcess;
//   		    	var result = $scope.disposalProcess.jgDisposalInstructions;
   		    	//预警信息
//   		    	$scope.earlyWarningInfo = $scope.warningDisposalView.earlyWarningInfo;
   		    	//年度事故次数
//   		    	$scope.accidentTotal = data.accidentTotal;
   		    	//年度违法次数
//   		    	$scope.illegalTotal = data.illegalTotal;
   		    	//预警事件、预警说明格式化
//   		    	switch($scope.earlyWarningInfo.warningType)
//   		    	{
//   			    	case '1':
//   			    		$scope.earlyWarningInfo['warningTypeExplain']="违规路线行驶";
//   			    		$scope.earlyWarningInfo['warningExplain'] = "不按照规定路线行驶！";
//   			    		break;
//   			    	case '2':
//   			    		$scope.earlyWarningInfo['warningTypeExplain']="违规时间行驶";
////   			    		var startDate = new Date().setTime(data.warningSet.driveStartDate.time);
////   			    		var endDate = new Date().setTime(data.warningSet.driveEndDate.time);
////   			    		var startTime = new Date().setTime(data.warningSet.driveStartTime.time);
////   			    		var endTime = new Date().setTime(data.warningSet.driveEndTime.time);
////   			        	var startDateAsString = $filter('date')(startDate, "yyyy年MM月dd日"); 
////   			        	var endDateAsString = $filter('date')(endDate, "yyyy年MM月dd日"); 
////   			        	var startTimeAsString = $filter('date')(startTime, "HH时mm分ss秒"); 
////   			        	var endTimeAsString = $filter('date')(endTime, "HH时mm分ss秒"); 
////   			    		$scope.earlyWarningInfo['warningExplain'] = "不按照规定时间行驶,违规行驶开始日期：" + startDateAsString + "，结束日期：" + endDateAsString + "，每日违规行驶开始时间 ："+ startTimeAsString +"，每日结束时间："+ endTimeAsString +"！";
//   			    		$scope.earlyWarningInfo['warningExplain'] = "不按照规定时间行驶！";
//   			      	  	break;
//   			    	case '3':
//   			    		$scope.earlyWarningInfo['warningTypeExplain']="超速行驶";
////   			    		if(undefined == data.warningSet || data.warningSet == '' || data.warningSet == null){
////   			    			$scope.earlyWarningInfo['warningExplain'] = "超速行驶，当前时速" + data.earlyWarningInfo.speed + "公里/小时！";
////   			    		}else{
////   			    			$scope.earlyWarningInfo['warningExplain'] = "超速行驶，限速为" + data.warningSet.highSpeed + "公里/小时，当前时速" + data.earlyWarningInfo.speed + "公里/小时！";
////   			    		}
//   			    		break;
//   			    	case '4':
//   			    		$scope.earlyWarningInfo['warningTypeExplain']="疲劳驾驶";
//   			    		$scope.earlyWarningInfo['warningExplain'] = "连续行驶4小时以上未进行20分钟休息！";
//   			    		break;
//   		    	}
   		    	//车辆信息
//   		    	$scope.vehicleInfo = $scope.warningDisposalView.vehicleInfo;
//   		    	$scope.vehicleInfo['vehicleKind'] = "物流车";
//   		    	console.log($scope.vehicleInfo);
   		    	
//   		    	if(data.vehicleInfo.motTestDate!=null){
//   		    		var currentYear = new Date();
//   		    		//注册日期
//   		    		var rd = new Date().setTime(data.vehicleInfo.registrationDate.time);
//   		    		//最近年审日期
//   		    		var nmtd = new Date().setTime(data.vehicleInfo.motTestDate.time);
//   		    		var newmtd = new Date($filter('date')(nmtd, "yyyy-MM-dd"));
//   		    		//去年年审最后期限
//   		        	var lastmtd = new Date((currentYear.getFullYear()-1)+"-"+$filter('date')(rd, "MM-dd")); 
//   		        	if(lastmtd>=newmtd){
//   		        		$scope.vehicleInfo['motTestState'] = "未年审";
//   		        	}else{
//   		        		$scope.vehicleInfo['motTestState'] = "正常";
//   		        	}
//   		    	}else{
//   		    		$scope.vehicleInfo['motTestState'] = "正常";
//   		    	}
   		    	
   		    	//处置细则
//   		    	var date = new Date().setTime($scope.earlyWarningInfo.warningStartTime);
//   		    	var dateAsString = $filter('date')(date, "yyyy年MM月dd日 HH时mm分ss秒"); 
//   		    	$scope.disposalRules = dateAsString + $scope.vehicleInfo.vehicleType + $scope.vehicleInfo.plateNumber + $scope.earlyWarningInfo.warningExplain;
//   			 }else{
//   				 $modalInstance.dismiss('cancel');
//   			 }
//   		 }, function (err) {
//   			 $modalInstance.dismiss('cancel');
//   		 })
//    	}
//    };
	 
	 /**
	  * 修改处置方式的时候，需要同步隐藏与显示下方的内容，注意这里的ID“disposalMethods”与其它地方de冲突了，这里改为了2
	  */
	 $("body").on("change","#disposalMethods2",function(){
		var data = $("#disposalMethods2").val();
    	switch (data) {
			case "不处置":
				$("#ignoreReason").attr("style","display:inline");
				$("#detailedMthod").attr("style","display:none");
				break;
			case "处置":
				$("#detailedMthod").attr("style","display:inline");
				$("#ignoreReason").attr("style","display:none");
				break;
		}
	 });
   		    	
    //修改预警处置流程
    $scope.updateDisposalProcess = function () {
    	// 设置处置时间
		$scope.disposalProcess['jgDisposalTime'] = $filter('date')(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 设置处理人
		$scope.disposalProcess['jgUserId'] = $rootScope.fstrName;
		$scope.disposalProcess['jgDisposalInstructions'] = 0;
		// 设置处置部门
		$scope.disposalProcess['hyDepartment'] = $scope.hyDepartment;
		
		var disposalMethods = $("#disposalMethods2").val();
    	switch(disposalMethods) {
	    	case '不处置':
	    		if($("#ignoreReason #wrong").is(':checked')) {//错误预警
	    			$scope.disposalProcess['jgDisposalInstructions'] = 3;
	    			$scope.disposalRules = '不处置：错误预警信息!';
	    			
	    		} else if($("#ignoreReason #repeat").is(':checked')) {//重复预警
	    			$scope.disposalProcess['jgDisposalInstructions'] = 4;
	    			$scope.disposalRules = '不处置：重复预警信息!';
	    			
	    		} else if($("#ignoreReason #others").is(':checked')) {//其他原因
	    			$scope.disposalProcess['jgDisposalInstructions'] = 5;
	    			$scope.disposalRules = '不处置：其他原因!'+ $scope.otherReason.reason;
	    			
	    		}
	    		// 设置处置流程状态为结束
	    		$scope.disposalProcess['state'] = 1;
	    		break;
	    	case '处置':
	    		if($("#detailedMthod #dispatch").is(':checked')) {//派警处置
	    			$scope.disposalProcess['jgDisposalInstructions'] = 1;
	    			
	    		} else if($("#detailedMthod #copy").is(':checked')) {//抄告处置
	    			$scope.disposalProcess['jgDisposalInstructions'] = 2;
	    		}
	    		
	      	  	break;
    	}
    	//处置方式为0，表示未选择处置方式
    	if($scope.disposalProcess['jgDisposalInstructions'] == 0){
      		
      		if($("#warningEnterDetail #rules").val() == null 
      				|| $("#warningEnterDetail #rules").val() == undefined 
      				|| $("#warningEnterDetail #rules").val() == '') {
      			SweetAlert.swal("", "处置方式或细则不能为空！", "error");
      			return;
      		}
      		//如果都不匹配表明是处置录入
			$scope.disposalProcess['jgDisposalInstructions'] = 6;
			$scope.disposalRules = $("#warningEnterDetail #rules").val();
    	}
    	
    	//预警请求参数
//    	$scope.disposalProcess.jgDisposalTime;//处置时间
///    	$scope.disposalProcess.jgUserId;//处置人id
//   	$scope.disposalProcess.jgDisposalInstructions;//处置方式
//    	$scope.disposalRules;//处置细则
//    	$scope.warningId = $scope.earlyWarningInfo.id;//预警id
//   	$scope.plateNumber;//车牌号码
    	
    	warningDisposalService.updateDisposalProcess($rootScope.fstrName,$scope.earlyWarningInfo.id,depName,
    			$scope.plateNumber,$scope.disposalProcess.jgDisposalTime,
    			$scope.disposalProcess.jgDisposalInstructions,$scope.disposalRules,function (data) {
    		if (data.status == 0) {
				 SweetAlert.swal("", "处置成功", "success");
				 // 初始化页面
				 $scope.disposalProcess = {};
				 $scope.earlyWarningInfo = {};
				 $scope.vehicleInfo = {};
				 $scope.disposalRules = "";
				 $scope.disposalMethods = "处置";
//				 $scope.hyDepartment = "一分局一大队";
//				 $scope.hyDepartment = $rootScope.depName + $rootScope.groupName;
				 $("#repeat").checked=false;
				 $("#wrong").checked=false;
				 $("#otherReason").attr("style","display:none");
//            	 $scope.queryWarningDisposalViewList();
			 }else{
				 SweetAlert.swal("", "处置失败", "warning");
			 }
		 }, function (err) {
			 SweetAlert.swal("", "处置失败", "warning");
		 })
		 $modalInstance.dismiss('cancel'); 
    };
    
    $scope.queryVehicleInfoByPlateNumber();
    
    //查看车辆图片
    $scope.showVehiclePicture = function(data) {
    	if(data==undefined){return false;}
        $scope.temp = {
            'vehicleInfo':data
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
    };
    
    /**
	  * 修改处置方式的时候，需要同步隐藏与显示下方的内容，注意这里的ID“disposalMethods”与其它地方de冲突了，这里改为了2
	  */
	$("body").on("click","input[name='disposalWay']:checkbox",function(){
		$("input[name='disposalWay']:checkbox").each(function() {
			$(this).attr("checked",false);
		});
		this.checked = true;
		if(this.checked){
			var selectOption = $(this).val();
			if(selectOption == '其它'){
				$("#otherReason").attr("style","display:inline");
			}else{
				$("#otherReason").attr("style","display:none");
			}
		}
	});
    
    
//    	require([  
//            "esri/map", 
//            "esri/layers/ArcGISTiledMapServiceLayer", 
//            "esri/graphic"  
//        ], function(
//        		Map, 
//        		ArcGISTiledMapServiceLayer, 
//        		Graphic) {
//            // BASE_SERVER配置移至app.js
//            var map = new Map("map", {  
//                center: [104.0657754083, 30.6583098090],  
//                zoom: 14,
//                logo:false,
//                nav:false,
//                slider:false
//            }); 
//    		var layer = new ArcGISTiledMapServiceLayer(BASE_SERVER);
//            map.addLayer(layer);
//        });   
	
}]);

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

//预警内容车牌号说明
app.filter('noteOfPlateNumber', function() { //可以注入依赖
    return function(text) {
    	if(text!=null && text!=undefined){
    		return "车牌号为【"+text+"】";
    	}
    }
});

//预警内容预警地点说明
app.filter('noteOfPlace', function() { //可以注入依赖
    return function(text) {
    	if(text!=null && text!=undefined){
    		return "车辆在"+text;
    	}
    }
});

//设置默认车辆图片
app.filter('defaultPic', function() { //可以注入依赖
    return function(text) {
    	if(text==null || text==undefined || text==''){
    		return "plate_none.png";
    	}else{
    		return text;
    	}
    }
});

//判断数据是否为空
app.filter('chineseIsNull', function() { //可以注入依赖
    return function(text) {
    	switch(text)
    	{
	    	case null:
	    	case undefined:
	    	case "":
	    		return "无";
	    		break;
	    	default :
	    		return text;
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


