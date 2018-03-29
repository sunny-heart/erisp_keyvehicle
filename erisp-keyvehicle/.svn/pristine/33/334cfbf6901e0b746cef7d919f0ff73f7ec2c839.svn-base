/**
 * 车辆信息查询
 */
app.controller('vehicleManageController', ['$scope','$state','$modal', '$log','$filter','vehicleManageService','SweetAlert',
  function($scope, $state, $modal, $log,$filter,vehicleManageService,SweetAlert) {
	
	
//	$scope.currentPage = 1;//初始化当前页
//    $scope.pageSize = 10;//初始化每页大小
//    $scope.queryConditionData = {
//            'currentPage':  1,
//            'pageSize': $scope.pageSize
//    }
    $(document).ready(function(){
    	var x=document.getElementById("inn");
	    if(x){
	    	x.style.height = $(window).height() - 60 +"px";//设置地图初始化高度
	    }
    })
	
	//翻页
//    $scope.pageChanged = function () {
//	   	//当前页
//	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
//	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
//	   	$scope.queryFunction();
//    };
    
    //浏览最大记录数
    $scope.pageQuery = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = 1;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	   	$scope.queryFunction();
	   	// 失去焦点
	   	$(".form-control").blur();
    };
    
    //查询
    $scope.query = function () {
        $scope.queryConditionData['currentPage'] = 1;
        $scope.queryFunction();
    };
    
    //查询方法
    $scope.queryFunction = function () {
    	vehicleManageService.queryVehicleInfo($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.vehicleInfo = data.messageBody.vehicleInfo;
//            	$scope.totalItems = data.messageBody.page.count;
//            	$scope.currentPage = data.messageBody.page.page;
//            	$scope.pages = data.messageBody.page.pages;
            }
        }, function (err) {
        })
    };
    
  
  //初始化查询条件
	$scope.queryFunction();
	
	//根据车牌号搜索
	 $scope.queryVehicleInfoByPlateNumber = function(){
		 vehicleManageService.queryVehicleInfoByPlateNumber($scope.queryConditionData.plateNumber,function (data) {
	            if (data.state == 200) {
	            	$scope.vehicleInfo = data.messageBody.vehicleInfoList;
//	            	$scope.totalItems = data.messageBody.page.count;
//	            	$scope.currentPage = data.messageBody.page.page;
//	            	$scope.pages = data.messageBody.page.pages;
//	            	if($scope.vehicleInfoList != null && 
//	            			$scope.vehicleInfoList.length * 42 > ($(window).height()-70) - 90){
//	            		$("#filtrateContent").css("width","288px");
//	            		$(".iframe_filtrate").css("width","288px");
//	            	}
//	            	if($scope.vehicleInfoList != null && $scope.vehicleInfoList.length > 0){
//	            		$("#totalNum").css("display","block");
//	            	}
	            }
	        }, function (err) {
	        })
	    }
    
  //删除
	$scope.deleteVehicleInfoById = function(id) {
		SweetAlert.swal({
            title: "注意！",
            text: "该条信息将会彻底删除，是否确定要删除？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
            	vehicleManageService.deleteVehicleInfoById(id,function (data) {
                    if (data.state == 200) {
            			if(data.messageBody.result) {
            			    SweetAlert.swal({
                                title: "删除成功!",
                                type: "success",
                                timer: 2000,
                                confirmButtonText: "确定"
                            });
            				$scope.queryFunction();
            			}else {
            			    SweetAlert.swal({
                                title: "删除失败!",
                                type: "error",
                                timer: 2000,
                                confirmButtonText: "确定"
                            });
            			}
                    }
                }, function (err) {
                })
            } 
        });
	}
	
	//添加与修改
    $scope.addOrUpdateVehicleInfo = function(type,data) {
        $scope.temp = {
            'type':type,
            'vehicle':data
        };
        var modalInstance = $modal.open({
            templateUrl : 'assets/views/vehicleManagement/vehicleManage/vehicleAddOrUpdate.html',
            controller : 'vehicleAddOrUpdateController',
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
            if(result == 1){
                SweetAlert.swal({
                    title: "操作成功!",
                    type: "success",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
                $scope.queryFunction(); 
            }else if(result == 0){
                SweetAlert.swal({
                    title: "操作失败!",
                    type: "error",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
                $scope.queryFunction();
            }else if(result == 2){
                SweetAlert.swal({
                    title: "车牌号码已存在，请重新输入！",
                    type: "error",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
                $scope.queryFunction();
            }
        }, function(err) {
        });
    };
    
	//重置
	$scope.reset = function(){
		$("#plateNumber").empty();
		$scope.queryConditionData = {};
	};
	
} ]);