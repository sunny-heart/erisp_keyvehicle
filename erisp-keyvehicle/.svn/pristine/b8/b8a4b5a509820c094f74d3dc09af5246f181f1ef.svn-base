/**
 * 车辆预警设置
 */
app.controller('warningSetController', ['$rootScope','$scope','$state','$modal','warningSetService','SweetAlert',
  function($rootScope,$scope, $state, $modal,warningSetService,SweetAlert){
	

	//添加与修改
	$scope.add = function() {
		var modalInstance = $modal.open({
			templateUrl : 'assets/views/warningDisposal/warningAdd.html',
			controller : 'warningAddOrQueryController',
			backdrop: 'static',
			keyboard: false,
			windowClass: 'erisp-modal',
			size : 'lg',//md
			resolve :{
				
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
                $scope.queryWarningInfo();
            }else{
            	SweetAlert.swal({
                    title: "操作失败!",
                    type: "error",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
            	 $scope.queryWarningInfo();
            }
        }, function(err) {
		});
	};
   
	//重置查询条件
    $scope.reset = function(){
		$scope.queryConditionData = {};
    }
    
    $scope.currentPage = 1;//初始化当前页
	$scope.pageSize = 10;//初始化每页大小
	$scope.queryConditionData = {
	        'currentPage':  1,
	        'pageSize': $scope.pageSize
	}
	
	//翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	   	$scope.queryWarningInfo();
    };
    
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
    $scope.queryWarningInfo = function () {
    	warningSetService.queryWarningInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.warningInfoList = data.messageBody.warningInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
    };
    
  
    //初始化查询条件
	$scope.queryWarningInfo();
	
	//删除
	$scope.deleteWarningInfo = function(info) {
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
            	warningSetService.deleteWarningInfo({"id":info.id,"warningType":info.warningType},function (data) {
                    if (data.state == 200) {
            			if(data.messageBody.count == 1) {
            			    SweetAlert.swal({
                                title: "删除成功!",
                                type: "success",
                                timer: 2000,
                                confirmButtonText: "确定"
                            });
            			    $scope.queryWarningInfo();
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
	
	
	//修改预警
   $scope.updateWarningInfo=function(dataInfo){
	   var modalInstance = $modal.open({
			templateUrl : 'assets/views/warningDisposal/warningUpdate.html',
			controller : 'warningUpdateController',
			backdrop: 'static',
			keyboard: false,
			windowClass: 'erisp-modal',
			size : 'lg',//md
			resolve :{
				params:function(){
					//return $scope.warningData;
					return dataInfo;
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
               $scope.queryWarningInfo();
           }else{
           	SweetAlert.swal({
                   title: "操作失败!",
                   type: "error",
                   timer: 2000,
                   confirmButtonText: "确定"
               });
           	 $scope.queryWarningInfo();
           }
       }, function(err) {
		}); 
	   
	   
   }
	
}]);
