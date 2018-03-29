app.factory("warningDisposalService",["$http",function($http) {
		
		// 获取预警处置流程列表信息
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
    	
    	
	    return {
	    	queryWarningDisposalViewList:queryWarningDisposalViewList,
	    	updateDisposalProcess:updateDisposalProcess
	    }
    }
])