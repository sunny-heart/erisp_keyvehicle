app.factory("busMapService",["$http","$q",function($http,$q) {
	
	// 根据车牌号码分页查询车辆信息
	function queryByPlateNumber(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/vehicleHisTrajectoryInfo/queryByPlateNumber";
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
	
	// 根据车牌号码查询所有车辆信息
	function queryVehicleByPlateNumber(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/vehicleHisTrajectoryInfo/queryVehicleByPlateNumber";
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
	
	// 根据车牌号码查询一辆车辆信息
	function queryOneVehicleByPlateNumber(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/vehicleHisTrajectoryInfo/queryVehicleByPlateNumber";
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
	
	// 根据车辆类型查询车辆信息
	function queryVehicleInfoByVehicleType(vehicleType,departName,plateNumber,onlineState,isRegisterArea,success,error) {
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/realTimeInfo/getRealTimeInfo" +
				"?vehicleType="+vehicleType+
				"&mapArea="+departName+
				"&plateNumber="+plateNumber+
				"&onlineState="+onlineState+
				"&isRegisterArea="+isRegisterArea+
				"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			success(data);
	    }).error(function(data){
	    	error(data);
	    })
	}
	
	// 根据车辆类型查询车辆信息--同步
	function queryVehicleInfoByVehicleTypeSyn(vehicleType,departName,plateNumber,onlineState,isRegisterArea,success,error) {
		var deferred = $q.defer();
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/realTimeInfo/getRealTimeInfo" +
				"?vehicleType="+vehicleType+
				"&mapArea="+departName+
				"&plateNumber="+plateNumber+
				"&onlineState="+onlineState+
				"&isRegisterArea="+isRegisterArea+
				"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			deferred.resolve(data);
	    }).error(function(data){
	    	error(data);
	    })
	    return deferred.promise;
	}
	
	// 查询某辆车的基本信息和违法事故统计及预警信息
	function queryVehicleOfOtherInfoByPlateNumber(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/vehicleHisTrajectoryInfo/queryVehicleOfOtherInfoByPlateNumber";
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
	
	// GET查询所有车辆实时信息
	function queryVehicleRealTimeInfo(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/vehicleRealTimeInfo/queryVehicleRealTimeInfo";
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
	
	//查询车某一辆车的实时信息
	function queryOneVehicleRealTimeInfo(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/vehicleRealTimeInfo/queryOneVehicleRealTimeInfo";
        $http({
            url : dataUrl,
            method : "GET",
            params : {queryConditionParams:params}
        }).success(function(data) {
            success(data);
        }).error(function(data) {
            error(data)
        });
	}
	
	//查询车某一辆车的实时信息--同步
	function queryOneVehicleRealTimeInfoSyn(params,success,error) {
		var deferred = $q.defer();
        var dataUrl = "/erisp-keyvehicle/service/vehicleRealTimeInfo/queryOneVehicleRealTimeInfo";
        $http({
            url : dataUrl,
            method : "GET",
            params : {queryConditionParams:params}
        }).success(function(data) {
            //success(data);
        	deferred.resolve(data);
        }).error(function(data) {
            error(data)
        });
        return deferred.promise;
	}
	
	//查询某段时间内某辆车的位置信息
	function queryLocations(params,success, error) {
        var dataUrl = "/erisp-keyvehicle/service/vehicleHisTrajectoryInfo/queryLocations";
        $http({
            url: dataUrl,
            method: "GET",
            params: params
        }).success(function (data) {
            success(data);
        }).error(function (data) {
            error(data)
        });
	}
	
	//查询某段时间内某辆车的位置信息
	function queryRealOrHisLocations(plateNumber,departName,startTime,endTime,success, error) {
		var plate = plateNumber.replace('川','');
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/historyTrail/getHistoryTrail?plateNumber="+plate+"&mapArea="+departName+"&startTime="+startTime+"&endTime="+endTime+"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			success(data);
	    }).error(function(data){
	    	error(data);
	    })
	}
	
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
	
	// GET根据用户类型和部门名称获取预警处置信息
	function queryWarningDisposalViewList(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/warningDisposal/queryWarningDisposalViewList";
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

	//查车辆数及预警数
	function queryEarlyWarningInfoCounts(vehicleType,departName,isRegisterArea,success,error) {
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/realTimeInfo/getVehicleStatistic?vehicleType="+vehicleType+"&departName="+departName+"&isRegisterArea="+isRegisterArea+"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			success(data);
	    }).error(function(data){
	    	error(data);
	    })
	}
	
	//查车辆行驶时间和速度等
	function queryVehicleDriveInfo(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/vehicleRealTimeInfo/queryVehicleDriveInfo";
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
	
	//气泡中状态数据
	function queryDataOfDialog(plateNumber,departName,success,error) {
		var plate = plateNumber.replace('川','');
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/warningInfo/getWarningInfo?plateNumber="+plate+"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			success(data);
	    }).error(function(data){
	    	error(data);
	    })
	}
	
	//气泡中状态数据--同步
	function queryDataOfDialogSyn(plateNumber,departName,success,error) {
		var deferred = $q.defer();
		var plate = plateNumber.replace('川','');
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/warningInfo/getWarningInfo?plateNumber="+plate+"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			deferred.resolve(data);
        }).error(function(data) {
            error(data)
        });
        return deferred.promise;
	}
	
	// 修改预警处置流程信息
	function updateDisposalProcess(params,success,error) {
		var dataUrl = "/erisp-keyvehicle/service/warningDisposal/updateDisposalProcess";
		$http({
			url : dataUrl,
			method : "POST",
			params : {"disposalProcessJsonStr":params}
		}).success(function(data) {
			success(data);
		}).error(function(data) {
			error(data)
		});
	}
	
	//查车辆行驶时间和速度等
	function queryAllRecPlateInfo(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/recPlateInfo/queryAllRecPlateInfo";
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
	
	//查询事故信息--不分页
	function searchAccidentInfoNoPage(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/accidentInfo/searchAccidentInfoNoPage";
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
	
	//查询违法信息--不分页
	function searchIllegalInfoNoPage(params,success,error) {
        var dataUrl = "/erisp-keyvehicle/service/illegalInfo/searchIllegalInfoNoPage";
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
	
	//查询车辆状态(在线，离线)
	function queryVehicleState(vehicleInfoList,success, error) {
		var dataUrl = "http://"+dataIp+"/erisp-dataservice/realTimeInfo/queryGPSState?queryVOs="+JSON.stringify(vehicleInfoList)+"&callBack=JSON_CALLBACK";
		$http.jsonp(dataUrl)
		.success(function(data) {
			success(data);
	    }).error(function(data){
	    	error(data);
	    })
	}
	
    return {
    	queryByPlateNumber:queryByPlateNumber,
    	queryVehicleByPlateNumber:queryVehicleByPlateNumber,
    	queryOneVehicleByPlateNumber:queryOneVehicleByPlateNumber,
    	queryVehicleInfoByVehicleType:queryVehicleInfoByVehicleType,
    	queryVehicleOfOtherInfoByPlateNumber:queryVehicleOfOtherInfoByPlateNumber,
    	queryVehicleRealTimeInfo:queryVehicleRealTimeInfo,
    	queryOneVehicleRealTimeInfo:queryOneVehicleRealTimeInfo,
    	queryLocations:queryLocations,
    	queryRealOrHisLocations:queryRealOrHisLocations,
    	queryVehicleInfo:queryVehicleInfo,
    	queryWarningDisposalViewList:queryWarningDisposalViewList,
    	queryEarlyWarningInfoCounts:queryEarlyWarningInfoCounts,
    	queryVehicleDriveInfo:queryVehicleDriveInfo,
    	queryDataOfDialog:queryDataOfDialog,
    	queryWarningDisposalViewList:queryWarningDisposalViewList,
    	updateDisposalProcess:updateDisposalProcess,
    	queryAllRecPlateInfo:queryAllRecPlateInfo,
    	searchAccidentInfoNoPage:searchAccidentInfoNoPage,
    	searchIllegalInfoNoPage:searchIllegalInfoNoPage,
    	queryVehicleState:queryVehicleState,
    	queryDataOfDialogSyn:queryDataOfDialogSyn,
    	queryOneVehicleRealTimeInfoSyn:queryOneVehicleRealTimeInfoSyn,
    	queryVehicleInfoByVehicleTypeSyn:queryVehicleInfoByVehicleTypeSyn
    }
}
])