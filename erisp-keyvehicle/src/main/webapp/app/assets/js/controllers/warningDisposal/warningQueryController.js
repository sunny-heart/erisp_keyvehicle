/**
 * 车辆预警查询
 */
app.controller('warningQueryController', ['$rootScope','$scope','Excel','$state','$timeout','$modal','warningQueryService','SweetAlert',
  function($rootScope,$scope,Excel,$state,$timeout, $modal,warningQueryService,SweetAlert){
	
	var idTmr;

	//重置查询条件
    $scope.reset = function(){
		$scope.queryConditionData = {};
		$scope.queryConditionData = {
		        'currentPage':  1,
		        'pageSize': $scope.pageSize
		}
		$("#startTime").val();
		$("#endTime").val();
    };
    
    var isCheckAll = false; //是否全选
    $scope.disposalProcessView = {};
    $scope.currentPage = 1;//初始化当前页
	$scope.pageSize = 10;//初始化每页大小
	$scope.queryConditionData = {
	        'currentPage':  1,
	        'pageSize': $scope.pageSize
	}
	$("#first").attr("disabled",true);//设置首页按钮不可用
	$("#previous").attr("disabled",true);//设置上一页按钮不可用
	$scope.queryConditionData.departmentName = $rootScope.chineseDepName;
	
	//翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	   	$scope.queryEarlyWaringInfoList();
	   	$scope.queryEarlyWaring();
    };
    
    //浏览最大记录数
    $scope.pageQuery = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = 1;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	  // 	$scope.queryFunction();
	   	// 失去焦点
	   	$(".form-control").blur();
    };
    
    //查询上一页
    $scope.previousPage=function(){
 	   $("#last").attr("disabled",false);
 		 $("#next").attr("disabled",false);
 	   var number = $scope.currentPage-1;
 	   if(number>0){
 		   $scope.queryConditionData['currentPage'] = number;
 	   }else{
 		   $scope.queryConditionData['currentPage'] = 1;
 	   }
 	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   	$scope.queryEarlyWaringInfoList();
 	   $scope.queryEarlyWaring();
 	   	if(number==1 || number < 0){
 		   	 $("#first").attr("disabled",true);
 			 $("#previous").attr("disabled",true);
 		   	}
    };
    
 	//查询下一页
    $scope.nextPage=function(){
 	   $("#first").attr("disabled",false);
 	   $("#previous").attr("disabled",false);
 	   if($scope.currentPage == $scope.pages){
 		   return;
 	   }
 	   
 	   var number = $scope.currentPage+1;
 	   $scope.queryConditionData['currentPage'] = number;
 	  $scope.queryEarlyWaring();
 	   if(number == $scope.pages || number > $scope.pages ){
 			  $scope.queryConditionData['currentPage'] = $scope.pages;
 			  $("#last").attr("disabled",true);
 			  $("#next").attr("disabled",true);
 		   }
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.queryEarlyWaringInfoList();
    };
    
    //查询最后一页
    $scope.lastPage=function(){
 	   $scope.queryConditionData['currentPage'] = $scope.pages;
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.queryEarlyWaringInfoList();
 	  $scope.queryEarlyWaring();
 	   $("#last").attr("disabled",true);
 	   $("#next").attr("disabled",true);
 	   $("#first").attr("disabled",false);
 	   $("#previous").attr("disabled",false);
    };
    
    //查询某一页
    $scope.go=function(){
 	   var anyNumber=$scope.anyPage;
 	   if(anyNumber>$scope.pages){
 		   anyNumber=$scope.pages;
 	   }else if(anyNumber<1){
 		   anyNumber=1;
 	   }
 	   $scope.queryConditionData['currentPage'] = anyNumber;
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.queryEarlyWaringInfoList();
 	  $scope.queryEarlyWaring();
 	 $("#first").attr("disabled",false);
	   $("#previous").attr("disabled",false);
	   $("#last").attr("disabled",false);
	   $("#next").attr("disabled",false);
 	   if($scope.anyPage == $scope.pages || $scope.anyPage > $scope.pages ){
 			  $("#last").attr("disabled",true);
 			  $("#next").attr("disabled",true);
 		   }
 	  if($scope.anyPage == 1 || $scope.anyPage < 1){
 		  $("#first").attr("disabled",true);
 		  $("#previous").attr("disabled",true);
 	   }
    };
    
    //查询
    $scope.query = function (requestType) {
    	if(requestType!=undefined && requestType!=""){
    		$scope.queryConditionData['requestType'] = requestType;
    	}
        $scope.queryConditionData['currentPage'] = 1;
        $scope.queryConditionData['pageSize'] = $scope.pageSize;
        $scope.queryEarlyWaringInfoList();
        $scope.queryEarlyWaringInfoListCounts();
        $("#first").attr("disabled",true);
		$("#previous").attr("disabled",true);
		$("#last").attr("disabled",false);
		$("#next").attr("disabled",false);
    };
    
    //查询
    $scope.queryEarly = function () {
    	$scope.queryConditionData['currentPage'] = 1;
    	$scope.queryConditionData['pageSize'] = $scope.pageSize;
    	$scope.queryEarlyWaring();
    	$("#first").attr("disabled",true);
    	$("#previous").attr("disabled",true);
    	$("#last").attr("disabled",false);
    	$("#next").attr("disabled",false);
    };
    
    //查询方法
    $scope.queryEarlyWaringInfoList = function () {
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningQueryService.queryEarlyWaringInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.earlyWaringInfoList = data.messageBody.earlyWaringInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
        warningQueryService.queryAccidentInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.earlyWaringInfoList = data.messageBody.earlyWaringInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
        warningQueryService.queryIllegalInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.earlyWaringInfoList = data.messageBody.earlyWaringInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
    };
    
    //预警查询方法(用于预警查询页面)
    $scope.queryEarlyWaring = function () {
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
    		$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningQueryService.queryEarlyWaring($scope.queryConditionData,function (data) {
    		if (data.state == 200) {
    			$scope.earlyWaringInfo = data.messageBody.earlyWaringInfo;
    			$scope.totalItems = data.messageBody.total;
    			$scope.currentPage = data.messageBody.currentPage;
    			$scope.pages = data.messageBody.pages;
    		}
    	}, function (err) {
    	})
    };
    
    //查询方法(不分页，用于导出excel)
    $scope.queryEarlyWaringInfoListCounts = function () {
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
    		$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningQueryService.queryEarlyWaringInfoListCounts($scope.queryConditionData,function (data) {
    		if (data.state == 200) {
    			$scope.earlyWaringInfoListCounts = data.messageBody.earlyWaringInfoListCounts;
    			console.log($scope.earlyWaringInfoListCounts.length);
    		}
    	}, function (err) {
    	})
    };
    
    //修改抄告状态
    $scope.updateJgCcState = function(){
//    $scope.exportToExcel = function(tableId){
//    	var warningIdList = [];
    	var earlyWarning = $scope.earlyWaringInfoListCounts;
    	for(var i = 0;i<earlyWarning.length;i++){
    		console.log(earlyWarning[i].id);
    		warningIdList.push(earlyWarning[i].id);
    	}
    	$.each($("input:checkbox[name='warningId']:checked"),function(){
    		warningIdList.push($(this).val());
        });
    	$scope.disposalProcessView.warningIdList = warningIdList;
//    	$scope.exportHref = Excel.tableToExcel(tableId, 'sheet name');
//        $timeout(function() { location.href = $scope.exportHref; }, 100);
    	warningQueryService.updateJgCcState($scope.disposalProcessView,function (data) {
    		if(data.state==200){
    			var result = data.messageBody.result;
    			if(result>=1){
    	               SweetAlert.swal({
    	                   title: "抄告成功!",
    	                   type: "success",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
//    	               $scope.exportHref = Excel.tableToExcel(tableId, 'sheet name');
//    	               $timeout(function() { location.href = $scope.exportHref; }, 100); // trigger download
    	               $scope.queryEarlyWaringInfoList();
    	           }else{
    	           	SweetAlert.swal({
    	                   title: "抄告失败!",
    	                   type: "error",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
    	           	   $scope.queryEarlyWaringInfoList();
    	           }
			};
		},function(err){
			SweetAlert.swal({
                title: "抄告失败，请联系管理员~",
                type: "error",
                timer: 2000,
                confirmButtonText: "确定"
            });
		});
    }
    
    //导出excel
    $scope.exportToExcel = function() {
    	window.location="/erisp-keyvehicle/service/earlyWarningInfo/exportExcel";
    }
    
    //全选|反选
    $scope.swapCheck = function() {  
        if (isCheckAll) {  
            $("input:checkbox[name='warningId']").each(function() {  
                this.checked = false;  
            });  
            isCheckAll = false;  
        } else {  
            $("input:checkbox[name='warningId']").each(function() {  
                this.checked = true;  
            });  
            isCheckAll = true;  
        }  
    }  
    
    $scope.queryEarlyWaring();
    
    //查看详情页面
    $scope.queryDetails = function(data) {
        $scope.temp = {
            'warningDetails':data
        };
        var modalInstance = $modal.open({
            templateUrl : 'assets/views/warningDisposal/warningDetails.html',
            controller : 'warningDetailsController',
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
//                    type: "success",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
            }
        }, function(err) {
        });
    };
	
	$(".form_datetime").datetimepicker({
		lang:'ch',
		timepicker:true,
		formatDate:'Y-m-d H:i'
	});
	
	$scope.queryEarly();
}]);

//预警类型
app.filter('warningType', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "时间区域预警";
				break;
			case "3":
				return "超速预警";
				break;
			case "4":
				return "疲劳驾驶预警";
				break;
		}
    }
});

//预警类型
app.filter('warningInfo', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "车辆违反时间区域预警规则";
				break;
			case "3":
				return "车辆违反超速预警规则";
				break;
			case "4":
				return "车辆违反疲劳驾驶预警规则";
				break;
		}
    }
});

//处置状态
app.filter('dealState', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "未处置";
				break;
			case "2":
				return "处置中";
				break;
			case "3":
				return "已处置";
				break;
		}
    }
});

//处置状态
app.filter('chineseVehicle', function() { 
	return function(text) {
		switch(text)
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
});

//抄告状态
app.filter('ccState', function() { 
    return function(text) {
		switch(text)
		{
			case "0":
				return "未抄告";
				break;
			case "1":
				return "已抄告";
				break;
		}
    }
});

//事故类型
app.filter('atype', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "简易事故";
				break;
			case "2":
				return "一般事故";
				break;
		}
    }
});

//违法类型
app.filter('itype', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "现场简易违法";
				break;
			case "2":
				return "现场一般违法";
				break;
			case "3":
				return "非现场电子监控";
				break;
		}
    }
});