app.factory("warningSignService",["$http",function($http) {
		
		//查询所有预警签收状态
    	function queryWarningSign(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/warningSign/queryWarningSign";
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
    	
    	//根据条件分页搜索预警信息
    	function searchWarningInfo(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/warningSign/searchWarningInfo";
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
    	
    	// 修改签收状态
    	function updateSignState(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/warningSign/updateSignState";
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
	    	queryWarningSign:queryWarningSign,
	    	searchWarningInfo:searchWarningInfo,
	    	updateSignState:updateSignState
	    }
    }
])