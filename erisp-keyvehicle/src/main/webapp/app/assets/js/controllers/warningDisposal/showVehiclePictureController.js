/**
 * 车辆图片信息
 */
app.controller('showVehiclePictureController', ['$scope', '$modalInstance', 'params',
  function($scope,$modalInstance,params) {
	//默认'暂无图片'
    //$scope.platePictureUrl = "assets/images/plate_none.png";
    var url;
    var picUrl = params.vehicleInfo.platePictureUrl;
    /*if(params.vehicleInfo.platePictureUrl!=null && params.vehicleInfo.platePictureUrl!=undefined && params.vehicleInfo.platePictureUrl!=''){
    	$scope.platePictureUrl = params.vehicleInfo.platePictureUrl;
    }*/
    if(picUrl==null || picUrl==undefined || picUrl==""){
		url = "plate_none.png";
	}else{
		url = picUrl;
	}
    $scope.platePicUrl = url;
    
    //取消
    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
   
    
}]);