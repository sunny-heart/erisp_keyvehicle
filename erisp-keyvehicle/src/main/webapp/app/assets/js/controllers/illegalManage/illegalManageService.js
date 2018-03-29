app.factory("illegalManageService",["$http",function($http) {
    	
	// GET查询所有违法信息
	function query(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/illegalInfo/query";
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
	
	// 根据id查询违法信息
	function queryIllegalInfoById(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/illegalInfo/queryIllegalInfoById";
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
	
	//分页查询违法信息
	function searchIllegalInfo(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/illegalInfo/searchIllegalInfo";
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
		query:query,
		queryIllegalInfoById:queryIllegalInfoById,
		searchIllegalInfo:searchIllegalInfo
    }
}
])