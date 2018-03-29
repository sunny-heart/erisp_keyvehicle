/**
 * 按分局统计报表
 */
app.controller('reportsBySubofficesController', ['$scope','$rootScope','$state','$modal', '$log','$filter','SweetAlert','reportsService','$interval','SweetAlert','$timeout',
  function($scope, $rootScope, $state, $modal, $log,$filter,SweetAlert,reportsService,$interval,SweetAlert,$timeout) {
	
	 $scope.queryConditionData = {};
	 $scope.queryConditionData.countTimeType = "月份";
	 $scope.queryConditionData.startMonth = "01";
	 $scope.queryConditionData.endMonth = "12";
	 $scope.queryConditionData.subofficeName = $rootScope.departName;
	 $scope.queryConditionData.sortType = "统计时间";
	 
	 //年份下拉列表
	 $scope.optionsOfYear = [];
	 $scope.computeOptionsOfYear = function(){
		 for(var i=2017; i<=2050; i++){
			 $scope.optionsOfYear.push(i);
		 }
	 }
	 
	 $scope.countTimeTypeChange = function(){
		 var type = $("#countTimeType option:selected").val();
		 if(type == "月份"){
			 $scope.queryConditionData.startMonth = "01";
			 $scope.queryConditionData.endMonth = "12";
			 $scope.queryConditionData.startQuarter = "";
			 $scope.queryConditionData.endQuarter = "";
		 }else if(type == "季度"){
			 $scope.queryConditionData.startMonth = "";
			 $scope.queryConditionData.endMonth = "";
			 $scope.queryConditionData.startQuarter = "1";
			 $scope.queryConditionData.endQuarter = "4";
		 }else{
			 $scope.queryConditionData.startMonth = "";
			 $scope.queryConditionData.endMonth = "";
			 $scope.queryConditionData.startQuarter = "";
			 $scope.queryConditionData.endQuarter = "";
		 }
		 $scope.countWarningBySuboffices();
	 }
	 
	 //重置查询条件
     $scope.reset = function(){
		$scope.queryConditionData = {};
		$scope.queryConditionData.countTimeType = "月份";
     };
	 
	 //根据分局统计
	 $scope.countWarningBySuboffices = function(){
		 var myDate = new Date();
		 //获取当前年
		 var year=myDate.getFullYear();
		 //获取当前月
		 var month=myDate.getMonth()+1;
		 if(month<10){month="0"+month;}
		 var currentStartTime = year+"-01";
		 var currentEndTime = year+"-"+month;
		 reportsService.countWarningBySuboffices($scope.queryConditionData,function (data) {
	            if (data.state == 200) {
	            	$scope.listBySuboffices = data.messageBody.listBySuboffices;
	            	var map = $scope.listBySuboffices;
	            	$("#countsTable tbody").empty();
	            	if(map!=undefined){
		            	$.each(map,function(key,values){     
		            		for(var i=0;i<values.length;i++){
		            			var k = key.split("-");
		            			var subofficeName = k[1];
		            			var vehicleTotals = k[2];
		            			
		            			var countTime = "";
		            			if($scope.queryConditionData.countTimeType=="季度"){
		            				var q = values[i].countTime.split("-");
		            				countTime = "<td>"+q[0]+"年 第"+q[1]+"季度</td>";
		            			}else{
		            				countTime = "<td>"+values[i].countTime+"</td>";
		            			}
		            			
		            			if(i==0){
		            				var newRow="<tr>"+
		    						"<td rowspan='"+values.length+"'>"+subofficeName+"</td>"+
		    						countTime+
		    						"<td>"+values[i].warningCounts+"</td>"+
		    						"<td>"+values[i].vehicleWarningCounts+"</td>"+
		    						"<td rowspan='"+values.length+"'>"+vehicleTotals+"</td>"+
		    						"<td>"+(Math.round(values[i].vehicleWarningCounts/vehicleTotals*10000)/100).toFixed(2)+"%</td>"+
		    					    "</tr>";
		            				$("#countsTable tbody").append(newRow);
		            			}else{
		            				var newRow="<tr>"+
		            				countTime+
		    						"<td>"+values[i].warningCounts+"</td>"+
		    						"<td>"+values[i].vehicleWarningCounts+"</td>"+
		    						"<td>"+(Math.round(values[i].vehicleWarningCounts/vehicleTotals*10000)/100).toFixed(2)+"%</td>"+
		    					    "</tr>";
		            				$("#countsTable tbody").append(newRow);
		            			}
		            		}
		            	 }); 
	            	}
	            }
         }, function (err) {
         })
	  }
	 
	 $scope.show = function(){
		 $(".all").hide();
		 $(".search").show();
	 }
	 
	 //模态窗口出不来加了个延时
	 $timeout(function(){
		$(".form_datetime").datetimepicker({
			lang:'ch',
			timepicker:false,
			format:'Y-m'
		});
	},500);
	 
	 $scope.countWarningBySuboffices();
	 $scope.computeOptionsOfYear();
}]);
