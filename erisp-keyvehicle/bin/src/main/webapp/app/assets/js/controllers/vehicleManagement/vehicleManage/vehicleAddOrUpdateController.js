/*
 * 车辆新增或修改
 */
app.controller('vehicleAddOrUpdateController', ['$scope','$filter','$log','$modal', '$modalInstance','SweetAlert', 'params','vehicleManageService','$timeout',
  function($scope,$filter,$log,$modal,$modalInstance,SweetAlert,params,vehicleManageService,$timeout) {
    
    var value,text;
    $scope.type = params.type;
    //加载数据
    if(params.type == 'add') {
        $scope.vehicleModel = {};
        $scope.title = "新增车辆";
        value = "";
        $scope.isAdd = true;
    }else if(params.type == 'update') {
        $scope.vehicleModel = {};
        $scope.vehicleModel['id'] = params.vehicle.id;
        $scope.vehicleModel['plateNumber'] = params.vehicle.plateNumber;
        $scope.vehicleModel['applyCompany'] = params.vehicle.applyCompany;
        $scope.vehicleModel['ascriptionCompany'] = params.vehicle.ascriptionCompany;
        $scope.vehicleModel['vehicleType'] = params.vehicle.vehicleType;
        $scope.vehicleModel['authorizedLoad'] = params.vehicle.authorizedLoad;
        $scope.vehicleModel['registrationDate'] = $filter("date")(params.vehicle.registrationDate.time, "yyyy/MM/dd HH:mm:ss");
        $scope.vehicleModel['contacts'] = params.vehicle.contacts;
        $scope.vehicleModel['phoneNumber'] = params.vehicle.phoneNumber;
        $scope.vehicleModel['systemNumber'] = params.vehicle.systemNumber;
        $scope.title = "修改车辆";
        $scope.isAdd = false;
    }
    
    //确定
    $scope.ok = function() {
        //添加与修改
        var validationStr = validation();
        if(validationStr) { 
            SweetAlert.swal("注意", validationStr, "warning");
        }else{
        	//获取的时间为中国标准时间，将其转换为格林尼治时间的零点
        	var time = $("#registrationDate").val()+" 08:00:00";
        	var re = new RegExp("/","g");
    		time=time.replace(re,':').replace(' ',':');
    		time1=time.split(':');
    		$scope.vehicleModel.registrationDate = new Date(parseInt(time1[0]),parseInt(time1[1])-1,parseInt(time1[2]),parseInt(time1[3]),parseInt(time1[4]),parseInt(time1[5]));	
        	vehicleManageService.addOrUpdateVehicleInfo($scope.vehicleModel,function (data) {
               if (data.state == 200) {
                   $modalInstance.close(data.messageBody.result);
               }else {
                   $modalInstance.close(false);
               }
            }, function (err) {
                $modalInstance.close(false);
            })
        }
     };
     
    //重置
    $scope.reset = function(){
        $scope.vehicleModel = {};
    }
    
    //取消
    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
     
     function validation () {
         if($scope.vehicleModel.plateNumber == undefined || $scope.vehicleModel.plateNumber == "") {
             return "车牌不能为空";
         }else if($scope.vehicleModel.applyCompany == undefined  || $scope.vehicleModel.applyCompany == "") {
             return "申报企业不能为空";
         }else if($scope.vehicleModel.ascriptionCompany == undefined || $scope.vehicleModel.ascriptionCompany == "") {
             return "所属企业不能为空";
         }else if($scope.vehicleModel.authorizedLoad == undefined || $scope.vehicleModel.authorizedLoad == 0) {
             return "核定载重不能为空";
         }
         else if($("#registrationDate").val() == "") {
             return "注册登记日期不能为空";
         }
//         else if($scope.vehicleModel.registrationDate == "") {
//        	 return "注册登记日期不能为空";
//         }
         else if($scope.vehicleModel.contacts == undefined || $scope.vehicleModel.contacts == "") {
             return "企业联系人不能为空";
         }else if($scope.vehicleModel.phoneNumber == undefined || $scope.vehicleModel.phoneNumber == "") {
             return "联系电话不能为空";
         }else if($scope.vehicleModel.systemNumber == undefined || $scope.vehicleModel.systemNumber == "") {
             return "所属系统编号不能为空";
         }else if($scope.vehicleModel.vehicleType == undefined || $scope.vehicleModel.vehicleType == 0) {
             return "车辆类型不能为空";
         }else if($scope.vehicleModel.authorizedLoad <= 0){
        	 return "核定载重不能为零和负数";
         }
     };
     
   //模态窗口出不来加了个延时
     $timeout(function(){
    	 $(".form_datetime").datetimepicker({
    			lang:'ch',
    			timepicker:false,
    			//format:'d/m/Y',
    			format:'Y/m/d',
//    			formatDate:'Y-m-d H:i'
    			//formatDate: "yyyy-mm-dd"
    			//minDate:'-1970/01/02', // yesterday is minimum date
    			//maxDate:'+1970/01/02' // and tommorow is maximum date calendar
    		});
     },500);
     
     
  
}]);