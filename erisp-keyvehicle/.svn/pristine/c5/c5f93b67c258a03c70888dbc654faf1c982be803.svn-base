/*
 * 签收状态
 */
app.controller('warningSignController', ['$scope','$state','$timeout','$modal', '$log','$filter','warningSignService','SweetAlert',
  function($scope, $state, $timeout, $modal, $log,$filter,warningSignService,SweetAlert) {
	
	var isCheckAll = false; //是否全选
	$scope.signView = {};
	$scope.currentPage = 1;//初始化当前页
    $scope.pageSize = 10;//初始化每页大小
    $scope.queryConditionData = {
            'currentPage':  1,
            'pageSize': $scope.pageSize
    }
    $("#first").attr("disabled",true);//设置首页按钮不可用
	$("#previous").attr("disabled",true);//设置上一页按钮不可用
    
    /*$(document).ready(function(){
    	var x=document.getElementById("inn");
	    if(x){
	    	x.style.height = $(window).height() - 60 +"px";//设置地图初始化高度
	    }
    })*/
	
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
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningSignService.searchWarningInfo($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
//    	    	console.log(data.messageBody.warningSignInfoList);
            	$scope.warningSignInfoList = data.messageBody.warningSignInfoList;
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
	
	//修改抄告状态
    $scope.updateSignState = function(){
    	var idList = [];
    	$.each($("input:checkbox[name='id']:checked"),function(){
    		idList.push($(this).val());
        });
    	$scope.signView.idList = idList;
    	warningSignService.updateSignState($scope.signView,function (data) {
    		if(data.state==200){
    			var result = data.messageBody.result;
    			if(result>=1){
    	               SweetAlert.swal({
    	                   title: "签收成功!",
    	                   type: "success",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
    	               $scope.queryFunction();
    	           }else{
    	           	SweetAlert.swal({
    	                   title: "签收失败!",
    	                   type: "error",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
    	           	   $scope.queryFunction();
    	           }
			};
		},function(err){
			SweetAlert.swal({
                title: "签收失败，请联系管理员~",
                type: "error",
                timer: 2000,
                confirmButtonText: "确定"
            });
		});
    }
	
	//全选|反选
    $scope.swapCheck = function() {  
        if (isCheckAll) {  
            $("input:checkbox[name='id']").each(function() {  
                this.checked = false;  
            });  
            isCheckAll = false;  
        } else {  
            $("input:checkbox[name='id']").each(function() {  
                this.checked = true;  
            });  
            isCheckAll = true;  
        }  
    }
	
	//模态窗口出不来加了个延时
	/*$timeout(function(){
		$(".form_datetime").datetimepicker({
			lang:'ch',
			timepicker:true,
			formatDate:'Y-m-d H:i'
		});
	},500);*/
	
	
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
		   if($scope.anyPage == $scope.pages || $scope.anyPage > $scope.pages ){
				  $("#last").attr("disabled",true);
				  $("#next").attr("disabled",true);
			   }
	   }
	   
	 //经纬度转化地址
	    function getBaiduAddress(GpsX, GpsY,callback){
	    	// 百度地图API功能
	    	// 创建地理编码实例，用来获取坐标地址
	    	var myGeo = new BMap.Geocoder();
	    	//GPS坐标
	    	var x = GpsX;
	    	var y = GpsY;
	    	var ggPoint = new BMap.Point(x,y);

	    	var baiduPoint;
	    	var baiduAddress;
	    	
	    	//gps坐标转换为百度坐标转换完之后的回调函数
	    	translateCallback = function (data){
	    		if(data.status === 0) {
	    		
	    			//根据坐标获取地址信息
	    			myGeo.getLocation(data.points[0], function(result){
	    				if (result){
	    				baiduPoint = data.points[0];
	    				baiduAddress = result.address;
	    				//	alert(baiduAddress);
	    				callback(baiduAddress);
	    				}
	    			});	 
	    		}
	    	}
	    	
	    	//将gps坐标转换为百度坐标
	    	var convertor = new BMap.Convertor();
	    	var pointArr = [];
	    	pointArr.push(ggPoint);
	    	convertor.translate(pointArr, 1, 5, translateCallback);
	    }
	
} ]);

//预警类型
app.filter('warningInfo', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "车辆违规路线行驶";
				break;
			case "2":
				return "车辆违规时间行驶";
				break;
			case "3":
				return "车辆超速行驶";
				break;
			case "4":
				return "车辆疲劳驾驶";
				break;
		}
    }
});

//签收状态
app.filter('warningSign', function() { 
	return function(text) {
		switch(text)
		{
		case 1:
			return "已签收";
			break;
		case 0:
			return "未签收";
			break;
		}
	}
});


	