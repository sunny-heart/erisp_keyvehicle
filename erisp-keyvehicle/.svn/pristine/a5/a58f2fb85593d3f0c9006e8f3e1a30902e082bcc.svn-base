app.factory("smsService",["$http",function($http) {
	
		// 短信发送
    	function sendSMS(params,success,error) {
            var dataUrl = "/erisp-keyvehicle/service/sms/sendSMS";
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
	    	sendSMS:sendSMS
	    }
    }
])