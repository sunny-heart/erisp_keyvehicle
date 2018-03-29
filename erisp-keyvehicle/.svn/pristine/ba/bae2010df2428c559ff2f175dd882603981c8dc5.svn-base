app.factory("dealPhotoService",["$http",function($http) {
		
		// 更新车辆图片
    	function updateVehiclePic(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/saveVehiclePic";
            $http({
                url : dataUrl,
                method : "POST",
                params : params
            }).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data)
            });
    	}
    	
	    return {
	    	updateVehiclePic:updateVehiclePic
	    }
    }
])