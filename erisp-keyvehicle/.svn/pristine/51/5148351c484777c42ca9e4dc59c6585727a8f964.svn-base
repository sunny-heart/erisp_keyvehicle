/**
 * 事故信息查询
 */
app.controller('accidentManageController', ['$scope','$state','$timeout','$modal', '$log','$filter','accidentManageService','SweetAlert',
  function($scope, $state, $timeout, $modal, $log,$filter,accidentManageService,SweetAlert) {
	
	$scope.currentPage = 1;//初始化当前页
    $scope.pageSize = 10;//初始化每页大小
    $scope.queryConditionData = {
            'currentPage':  1,
            'pageSize': $scope.pageSize
    }
    $("#first").attr("disabled",true);//设置首页按钮不可用
	$("#previous").attr("disabled",true);//设置上一页按钮不可用
    
    $(document).ready(function(){
    	var x=document.getElementById("inn");
	    if(x){
	    	x.style.height = $(window).height() - 60 +"px";//设置地图初始化高度
	    }
    })
	
	//翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
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
    
  //查询
    $scope.query = function () {
        $scope.queryConditionData['currentPage'] = 1;
        $scope.queryConditionData['pageSize'] = $scope.pageSize;
        $scope.queryFunction();
        $("#first").attr("disabled",true);
		$("#previous").attr("disabled",true);
		$("#last").attr("disabled",false);
		$("#next").attr("disabled",false);
    };
    
    //查询方法
    $scope.queryFunction = function () {
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	accidentManageService.searchAccidentInfo($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.accidentInfoList = data.messageBody.accidentInfoList;
            	$scope.totalItems = data.messageBody.page.count;
            	$scope.currentPage = data.messageBody.page.page;
            	$scope.pages = data.messageBody.page.pages;
            }
        }, function (err) {
        })
    };
    
	//重置
	$scope.reset = function(){
		$("#plateNumber").empty();
		$scope.queryConditionData = {};
		$scope.queryConditionData = {
		        'currentPage':  1,
		        'pageSize': $scope.pageSize
		}
		$("#startTime").val();
		$("#endTime").val();
	};
	
	//模态窗口出不来加了个延时
	$timeout(function(){
		$(".form_datetime").datetimepicker({
			lang:'ch',
			timepicker:true,
			formatDate:'Y-m-d H:i'
		});
	},500);
	
	
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
		   	$scope.queryFunction();
		   	if(number==1 || number < 0){
			   	 $("#first").attr("disabled",true);
				 $("#previous").attr("disabled",true);
			   	}
	   }
	   
		//查询下一页
	   $scope.nextPage=function(){
		   $("#first").attr("disabled",false);
		   $("#previous").attr("disabled",false);
		   if($scope.currentPage == $scope.pages){
			   return;
		   }
		   var number = $scope.currentPage+1;
		   $scope.queryConditionData['currentPage'] = number;
		   if(number == $scope.pages || number > $scope.pages ){
				  $scope.queryConditionData['currentPage'] = $scope.pages;
				  $("#last").attr("disabled",true);
				  $("#next").attr("disabled",true);
			   }
		   $scope.queryConditionData['pageSize'] = $scope.pageSize;
		   $scope.queryFunction();
	   }
	   
	   //查询最后一页
	   $scope.lastPage=function(){
		   $scope.queryConditionData['currentPage'] = $scope.pages;
		   $scope.queryConditionData['pageSize'] = $scope.pageSize;
		   $scope.queryFunction();
		   $("#last").attr("disabled",true);
		   $("#next").attr("disabled",true);
		   $("#first").attr("disabled",false);
		   $("#previous").attr("disabled",false);
	   }
	   
	   //默认加载
	   $scope.queryFunction();
	   
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
		   $scope.queryFunction();
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
	   }
} ]);

//事故类型
app.filter('type', function() { 
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