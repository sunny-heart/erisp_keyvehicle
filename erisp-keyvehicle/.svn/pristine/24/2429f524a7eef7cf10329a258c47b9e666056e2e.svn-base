app.factory("accidentManageService",["$http",function($http) {
    	
	// GET查询所有事故信息
	function query(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/accidentInfo/query";
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
	
	// 根据id查询事故信息
	function queryAccidentInfoById(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/accidentInfo/queryAccidentInfoById";
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
	
	//分页查询事故信息
	function searchAccidentInfo(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/accidentInfo/searchAccidentInfo";
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
		queryAccidentInfoById:queryAccidentInfoById,
		searchAccidentInfo:searchAccidentInfo
    }
}
])