/**
 * 处理车辆图片
 */
app.controller('dealPhotoController', ['$scope','$state','$modal', '$log','$filter','SweetAlert','dealPhotoService','$interval','SweetAlert','$timeout',
  function($scope, $state, $modal, $log,$filter,SweetAlert,dealPhotoService,$interval,SweetAlert,$timeout) {
	 
	 $scope.queryConditionData = {};
	 //更新数据库图片名称
	 $scope.updateVehiclePic = function(){
		 $("#progressbar").show();
		 $("#background_layer").show();
		 $scope.queryConditionData.urlPath = $("#urlPath").val();
		 dealPhotoService.updateVehiclePic($scope.queryConditionData,function (data) {
             if (data.state == 200) {
            	$scope.msg = data.messageBody.msg;
             }
            $("#progressbar").hide();
 			$("#background_layer").hide();
         }, function (err) {
         })
	  }
	 
}]);
