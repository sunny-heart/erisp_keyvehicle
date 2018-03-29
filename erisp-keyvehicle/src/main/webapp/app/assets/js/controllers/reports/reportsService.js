app.factory("reportsService",["$http",function($http) {
		
		// 根据车辆类型统计
    	function countByVehicleTypes(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/reports/countByTypes";
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
    	
    	// 根据主管部门统计
    	function countByDepartments(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/reports/countByDepartments";
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
    	
    	// 根据分局统计
    	function countWarningBySuboffices(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/reports/countWarningBySuboffices";
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
    	
    	// 根据公司统计
    	function countWarningByCompanies(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/reports/countWarningByCompanies";
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
	    	countByVehicleTypes:countByVehicleTypes,
	    	countByDepartments:countByDepartments,
	    	countWarningBySuboffices:countWarningBySuboffices,
	    	countWarningByCompanies:countWarningByCompanies
	    }
    }
])