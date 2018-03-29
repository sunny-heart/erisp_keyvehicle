app.factory("focusMapService",["$http","$q",function($http,$q) {
	
	// 根据车辆类型查询车辆信息--跟实时数据一个接口--同步
	function queryVehicleInfoByVehicleTypeSyn(departName,isRegisterArea,plateNumber,success,error) {
		var deferred = $q.defer();
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/realTimeInfo/getRealTimeInfo" +
				"?isWarning=true&mapArea="+departName+
				"&isRegisterArea="+isRegisterArea+
				"&plateNumber="+plateNumber+
				"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			//success(data);
			deferred.resolve(data);
	    }).error(function(data){
	    	error(data);
	    })
	    return deferred.promise;
	}
	
	// GET预警规则查询
	function queryWarningRuleList(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/warningRule/queryWarningRuleList";
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
    	queryVehicleInfoByVehicleTypeSyn:queryVehicleInfoByVehicleTypeSyn,
		queryWarningRuleList:queryWarningRuleList
    }
}
])