/**
 * 车辆信息查询
 */
app.controller('warningDisposalController', ['$scope','$state','$modal', '$log','$filter','SweetAlert','warningDisposalService','$interval','SweetAlert',
  function($scope, $state, $modal, $log,$filter,SweetAlert,warningDisposalService,$interval,SweetAlert) {
	
	$scope.currentPage = 1;//初始化当前页
    $scope.pageSize = 16;//初始化每页大小
    $scope.queryConditionData = {
            'currentPage':  1,
            'pageSize': $scope.pageSize
    }
    $scope.disposalMethods = "处置下发";
    $scope.hyDepartment = "物流云平台";
    $scope.disposalProcess = {};
	
	//翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	   	$scope.queryWarningDisposalViewList();
    };
    
    //浏览最大记录数
    $scope.pageQuery = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = 1;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
    };
    
    //查询预警处置流程
    $scope.queryWarningDisposalViewList = function () {
    	$scope.queryConditionData['userType'] = "1";
    	$scope.queryConditionData['department'] = "1";
    	warningDisposalService.queryWarningDisposalViewList($scope.queryConditionData,function (data) {
			 if (data.state == 200) {
				$scope.warningDisposalViewList = data.messageBody.warningDisposalViewList;
				$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
			 }
		 }, function (err) {
		 })
    };
    
    //预警信息点击事件
    $scope.warningOnClick = function(data) {
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
	    		$scope.earlyWarningInfo['warningTypeExplain']="违规路线行驶";
	    		$scope.earlyWarningInfo['warningExplain'] = "车辆不按照规定路线行驶！";
	    		break;
	    	case '2':
	    		$scope.earlyWarningInfo['warningTypeExplain']="违规时间行驶";
	    		$scope.earlyWarningInfo['warningExplain'] = "车辆不按照规定时间行驶！";
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
    	// 设置处置时间
//		$scope.disposalProcess['jgDisposalTime'] = new Date();
		$scope.disposalProcess['jgDisposalTime'] = $filter('date')(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 设置处理人
		$scope.disposalProcess['jgUserId'] = "1";
    	var jgDisposalInstructions = "";
    	switch($scope.disposalMethods)
    	{
	    	case '处置不下发':
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
	    	case '处置下发':
	    		// 设置下发部门
	    		$scope.disposalProcess['hyDepartment'] = $scope.hyDepartment;
	    		$scope.disposalProcess['jgDisposalInstructions'] = $scope.disposalMethods + ",下发部门： " + $scope.hyDepartment;
	      	  	break;
    	}
    	warningDisposalService.updateDisposalProcess($scope.disposalProcess,function (data) {
			 if (data.state == 200) {
				 SweetAlert.swal("", "处置成功", "warning");
				 // 初始化页面
				 $scope.disposalProcess = {};
				 $scope.earlyWarningInfo = {};
				 $scope.vehicleInfo = {};
				 $scope.disposalRules = "";
				 $scope.disposalMethods = "处置下发";
				 $scope.hyDepartment = "物流云平台";
				 $("#repeat").checked=false;
				 $("#wrong").checked=false;
            	 $scope.queryWarningDisposalViewList();
			 }else{
				 SweetAlert.swal("", "处置失败", "warning");
			 }
		 }, function (err) {
			 SweetAlert.swal("", "处置失败", "warning");
		 })
    };
    
    
    
    
    //每10秒刷新一次车辆实时位置--暂定只刷新10次
    $interval(function(){
    	$scope.queryWarningDisposalViewList();
    },10000);
    
    
	
}]);

//预警类型格式化
app.filter('reverse', function() { //可以注入依赖
    return function(text) {
    	switch(text)
    	{
	    	case '1':
	    		return "违规路线行驶";
	    		break;
	    	case '2':
	    		return "违规时间行驶";
	      	  	break;
	    	case '3':
	    		return "超速行驶";
	    		break;
	    	case '4':
	    		return "疲劳驾驶";
	    		break;
    	}
    }
});