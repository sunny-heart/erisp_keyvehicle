app.factory("vehicleManageService",["$http",function($http) {
		
		// GET查询所有车辆信息
    	function queryVehicleInfo(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/queryVehicleInfo";
            $http({
                url : dataUrl,
                method : "GET",
                params : params
            }).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data)
            });
    	}
    	
    	// 删除车辆信息
    	function deleteVehicleInfoById(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/deleteVehicleInfoById";
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
    	
    	// 添加或者修改车辆信息
    	function addOrUpdateVehicleInfo(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/addOrUpdateVehicleInfo";
    		$http({
    			url : dataUrl,
    			method : "POST",
    			params : {"vehicleInfoJsonStr":params}
    		}).success(function(data) {
    			success(data);
    		}).error(function(data) {
    			error(data)
    		});
    	}
    	
    	// 根据车牌号码查询车辆信息
    	function queryVehicleInfoByPlateNumber(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/queryVehicleInfoByPlateNumber";
    		$http({
    			url : dataUrl,
    			method : "GET",
    			params : {plateNumber:params}
    		}).success(function(data) {
    			success(data);
    		}).error(function(data) {
    			error(data)
    		});
    	}
    	
    	// 根据车牌id查询车辆信息
    	function queryVehicleInfoById(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/queryVehicleInfoById";
    		$http({
    			url : dataUrl,
    			method : "GET",
    			params : params 
    		}).success(function(data) {
    			success(data);
    		}).error(function(data) {
    			error(data)
    		});
    	}
    	
	    return {
	    	queryVehicleInfo:queryVehicleInfo,
	    	deleteVehicleInfoById:deleteVehicleInfoById,
	    	addOrUpdateVehicleInfo:addOrUpdateVehicleInfo,
	    	queryVehicleInfoByPlateNumber:queryVehicleInfoByPlateNumber,
	    	queryVehicleInfoById:queryVehicleInfoById
	    }
    }
])