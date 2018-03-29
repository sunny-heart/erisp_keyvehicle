app.factory("warningDisposalService",["$http",function($http) {
		
		// 获取预警处置流程列表信息
    	function queryWarningDisposal(params,success,error) {
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
    	
    	// 获取已经处置过的预警信息
    	function queryDisposaledList(params,success,error) {
    		var dataUrl = "/erisp-keyvehicle/service/warningDisposal/queryDisposaledList";
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
    	
    	// 修改预警处置流程信息
    	function updateDisposalProcess(disposalUserId,id,disposalDept,plateNumber,disposalTimeStr,disposalWay,disposalDetail,success,error) {
//    		var dataUrl = "/erisp-keyvehicle/service/warningDisposal/updateDisposalProcess";
    		var plate = plateNumber.replace('川','');
    		var dataUrl = "http://"+dataIp+"/erisp-dataservice/warningInfo/disposeWarning?id=" +
    			id +"&plateNumber=" + plate +
    			"&disposalWay=" + disposalWay;
    		
    		if(disposalWay == 6) {//处置录入时的参数位置不同
    			dataUrl += "&enterUserId=" + disposalUserId +
    			"&enterDept=" + disposalDept+
    			"&enterTimeStr=" + disposalTimeStr +
    			"&enterDetail=" + disposalDetail;
    		} else {
    			dataUrl += "&disposalUserId="+disposalUserId+
    			"&disposalDept="+disposalDept+
				"&disposalTimeStr="+disposalTimeStr+
				"&disposalDetail="+disposalDetail;
    		}
    		
    		dataUrl += "&callBack=JSON_CALLBACK";
    		
    		$http.jsonp(dataUrl)
    		.success(function(data) {
    			success(data);
    	    }).error(function(data){
    	    	error(data);
    	    })
    		
    		/*$http({
    			url : dataUrl,
    			method : "POST",
    			params : {"disposalProcessJsonStr":params}
    		}).success(function(data) {
    			success(data);
    		}).error(function(data) {
    			error(data)
    		});*/
    	}
    	
    	// 修改处置录入流程信息
//    	function updateDisposalEntry(params,success,error) {
    	function updateDisposalEntry(enterUserId,id,plateNumber,entryDept,enterTimeStr,disposalWay,enterDetail,success,error) {
//        		var dataUrl = "/erisp-keyvehicle/service/warningDisposal/updateDisposalProcess";
    		var plate = plateNumber.replace('川','');
        		var dataUrl = "http://"+dataIp+"/erisp-dataservice/warningInfo/disposeWarning" +
        				"?enterUserId="+enterUserId+
        				"&id="+id+
        				"&plateNumber=" + plate +
        				"&entryDept="+entryDept+
        				"&enterTimeStr="+enterTimeStr+
        				"&disposalWay="+disposalWay+
        				"&enterDetail="+enterDetail+
        				"&callBack=JSON_CALLBACK";
        		$http.jsonp(dataUrl)
        		.success(function(data) {
        			success(data);
        	    }).error(function(data){
        	    	error(data);
        	    })
    		
    		
    		/*var dataUrl = "/erisp-keyvehicle/service/warningDisposal/updateDisposalEntry";
    		$http({
    			url : dataUrl,
    			method : "POST",
    			params : {"disposalEntryJsonStr":params}
    		}).success(function(data) {
    			success(data);
    		}).error(function(data) {
    			error(data)
    		});*/
    	}
    	
    	// 获取车辆年审预警信息
    	function queryWarningOfMotTestViewList(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/queryVehicleMotTest";
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
    	
    	// 处置车辆年审预警
    	function dealMotTest(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/vehicleInfo/dealMotTest";
            $http({
                url : dataUrl,
                method : "POST",
                params : {"id":params}
            }).success(function(data) {
                success(data);
            }).error(function(data) {
                error(data)
            });
    	}
    	
    	// 根据预警Id查询预警处置信息，预警信息，车辆信息
    	function queryOneWarningDisposalInfo(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/warningDisposal/queryOneWarningDisposalInfo";
            $http({
                url : dataUrl,
                method : "GET",
                params : {warningId:params}
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
    	
    	// 修改预警处置流程信息  -- 临时(colin)
    	function update(params,success,error) {
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
    	
	    return {
	    	queryWarningDisposal:queryWarningDisposal,
	    	queryDisposaledList:queryDisposaledList,
	    	updateDisposalProcess:updateDisposalProcess,
	    	updateDisposalEntry:updateDisposalEntry,
	    	queryWarningOfMotTestViewList:queryWarningOfMotTestViewList,
	    	dealMotTest:dealMotTest,
	    	queryOneWarningDisposalInfo:queryOneWarningDisposalInfo,
	    	queryVehicleInfoByPlateNumber:queryVehicleInfoByPlateNumber,
	    	update:update
	    }
    }
])