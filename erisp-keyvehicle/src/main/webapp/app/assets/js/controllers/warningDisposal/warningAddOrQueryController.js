/*
 * 预警添加
 */
app.controller('warningAddOrQueryController', ['$timeout','$scope','$modal','$modalInstance','SweetAlert','warningSetService',
  function($timeout,$scope,$modal,$modalInstance,SweetAlert,warningSetService) {
	$scope.warningData={};
	//添加预警
	$scope.ok=function(){
		$scope.warningData.startDate=$("#startDate").val();
		$scope.warningData.endDate=$("#endDate").val();
		$scope.warningData.startTime=$("#startTime").val();
		$scope.warningData.endTime=$("#endTime").val();
		 var validationStr = validation();
		 if(validationStr) { 
	            SweetAlert.swal("注意", validationStr, "warning");
	        }else{
	        	if($scope.warningData.plateNumber!=undefined){
	        		var plateNumber = $scope.warningData.plateNumber.toUpperCase().trim();
	        		$scope.warningData.plateNumber = plateNumber;
	        	}
	        	warningSetService.addWarning($scope.warningData,function(data){
	    			if(data.state==200 && data.messageBody.result==1){
	    				$modalInstance.close(true);
	    			};
	    		},function(err){
	    			$modalInstance.close(false);
	    		});
	        }
	};
	
	
	//重置
    $scope.reset = function(){
        $scope.warningData = {};
    }
	 //取消
    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
    
    function validation () {
        if($scope.warningData.vehicleTypeMain == undefined || $scope.warningData.vehicleTypeMain == "") {
            return "主类车辆类型不能为空！";
        }else if($scope.warningData.vehicleType == undefined || $scope.warningData.vehicleType == "") {
            return "车辆类型不能为空！";
        }else if($scope.warningData.warningType == undefined || $scope.warningData.warningType == "") {
            return "预警类型不能为空！";
        }else if($("#startDate").val() == "") {
            return "开始日期不能为空！";
        }else if($("#endDate").val() == "") {
            return "开始日期不能为空！";
        }else if($("#startTime").val() == "") {
            return "开始时间不能为空！";
        }else if($("#endTime").val() == "") {
            return "结束时间不能为空！";
        }else if($("#route").val() == "") {
            return "违规路线设置不能为空！";
        }else if($("#drivingTime").val() == "") {
            return "疲劳驾驶时间不能为空！";
        }
    };
    //添加时间
   $scope.addtime=function(){
   	 $(".form_datetime").datetimepicker({
   			lang:'ch',
   			//timepicker:true,
   		  //  pickDate: false,
   		    pickTime:true,
   		    minuteStep:15,
   			format:'H:00:00',
   		//	startView: 2,
   		//	minView: 2,
   			showSecond:true
//   			formatDate:'Y-m-d H:i'
   			//formatDate: "yyyy-mm-dd"
   		});
    }
  //添加日期
   $scope.addDate=function(){
	   	 $(".form_date").datetimepicker({
	   			lang:'ch',
	   			timepicker:false,
	   			//format:'d/m/Y',
	   			format:'Y-m-d',
//	   			formatDate:'Y-m-d H:i'
	   			//formatDate: "yyyy-mm-dd"
	   			//minDate:'-1970/01/02', // yesterday is minimum date
	   			//maxDate:'+1970/01/02' // and tommorow is maximum date calendar
	   		
	   		});
	    }
	
}]);