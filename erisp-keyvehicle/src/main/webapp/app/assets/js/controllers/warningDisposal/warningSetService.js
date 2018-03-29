app.factory("warningSetService",["$http",function($http) {
	
		//添加预警
		function addWarning(param,success,error){
			var dataUrl = "/erisp-keyvehicle/service/warningSet/addWarning";
			$http({
				url:dataUrl,
				method:'GET',
				params:param
			}).success(function(data){
				success(data);
			}).error(function(data){
				error(data)
			});
		}
		
		// GET查询预警信息
    	function queryWarningInfoList(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/warningSet/queryWarningInfoList";
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
    	
    	// 删除预警信息
    	function deleteWarningInfo(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/warningSet/deleteWarningInfo";
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
    	//修改预警
    	function updateWarning(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/warningSet/updateWarning";
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
	    	addWarning:addWarning,
	    	updateWarning:updateWarning,
	    	queryWarningInfoList:queryWarningInfoList,
	    	deleteWarningInfo:deleteWarningInfo
	    }
    }
])