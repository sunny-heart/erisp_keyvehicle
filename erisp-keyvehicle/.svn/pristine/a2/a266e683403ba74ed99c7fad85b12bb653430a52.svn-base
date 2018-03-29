/**
 * 处置抄告
 */
app.controller('warningCopyController', ['$rootScope','$scope','Excel','$state','$timeout','$modal','warningCopyService','SweetAlert',
  function($rootScope,$scope,Excel,$state,$timeout, $modal,warningCopyService,SweetAlert){
	
	var idTmr;
	var currentTab;
	var department = $rootScope.departName;
	$scope.queryConditionData = {};

	$scope.query = function(){
		var tabChecked = $("li.selected").text();
 	    switch(tabChecked){
 	       case "预警信息":
 	    	  $scope.queryEarlyWaringInfoList();
 	    	  break;
 	       case "事故信息":
 	    	  $scope.queryAccidentInfoList();
 	    	  break;
 	       case "违法信息":
 	    	  $scope.queryIllegalInfoList();
 	    	  break;
 	    }
	}
	
	//重置查询条件
    $scope.reset = function(){
		$scope.queryConditionData = {};
		$scope.queryConditionData = {
		        'currentPage':  1,
		        'pageSize': $scope.pageSize
		}
		$("#startTime").val();
		$("#endTime").val();
    };
    
    //设置所属企业的select可以输入
   /* $('#ascriptionCompany').editableSelect({ 
        effects: 'slide' 
    });*/
    
    var isCheckAll = false; //是否全选
    $scope.disposalProcessView = {};
    $scope.currentPage = 1;//初始化当前页
	$scope.pageSize = 10;//初始化每页大小
	$scope.queryConditionData = {
	        'currentPage':  1,
	        'pageSize': $scope.pageSize
	}
	$("#first").attr("disabled",true);//设置首页按钮不可用
	$("#previous").attr("disabled",true);//设置上一页按钮不可用
	
	//翻页
    $scope.pageChanged = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = $scope.currentPage;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	   	$scope.query();
    };
    
    //浏览最大记录数
    $scope.pageQuery = function () {
	   	//当前页
	   	$scope.queryConditionData['currentPage'] = 1;
	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
	  // 	$scope.queryFunction();
	   	// 失去焦点
	   	$(".form-control").blur();
    };
    
    //查询上一页
    $scope.previousPage=function(){
 	   $("#last").attr("disabled",false);
 		 $("#next").attr("disabled",false);
 	   var number = $scope.currentPage-1;
 	   if(number>0){
 		   $scope.queryConditionData['currentPage'] = number;
 	   }else{
 		   $scope.queryConditionData['currentPage'] = 1;
 	   }
 	   	$scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   	$scope.query();
 	   	if(number==1 || number < 0){
 		   	 $("#first").attr("disabled",true);
 			 $("#previous").attr("disabled",true);
 		   	}
    };
    
 	//查询下一页
    $scope.nextPage=function(){
 	   $("#first").attr("disabled",false);
 	   $("#previous").attr("disabled",false);
 	   if($scope.currentPage == $scope.pages){
 		   return;
 	   }
 	   
 	   var number = $scope.currentPage+1;
 	   $scope.queryConditionData['currentPage'] = number;
 	   if(number == $scope.pages || number > $scope.pages ){
 			  $scope.queryConditionData['currentPage'] = $scope.pages;
 			  $("#last").attr("disabled",true);
 			  $("#next").attr("disabled",true);
 		   }
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.query();
    };
    
    //查询最后一页
    $scope.lastPage=function(){
 	   $scope.queryConditionData['currentPage'] = $scope.pages;
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.query();
 	   $("#last").attr("disabled",true);
 	   $("#next").attr("disabled",true);
 	   $("#first").attr("disabled",false);
 	   $("#previous").attr("disabled",false);
    };
    
    //查询某一页
    $scope.go=function(){
 	   var anyNumber=$scope.anyPage;
 	   if(anyNumber>$scope.pages){
 		   anyNumber=$scope.pages;
 	   }else if(anyNumber<1){
 		   anyNumber=1;
 	   }
 	   $scope.queryConditionData['currentPage'] = anyNumber;
 	   $scope.queryConditionData['pageSize'] = $scope.pageSize;
 	   $scope.query();
 	  $("#first").attr("disabled",false);
	   $("#previous").attr("disabled",false);
	   $("#last").attr("disabled",false);
	   $("#next").attr("disabled",false);
 	   if($scope.anyPage == $scope.pages || $scope.anyPage > $scope.pages ){
 			  $("#last").attr("disabled",true);
 			  $("#next").attr("disabled",true);
 		   }
 	  if($scope.anyPage == 1 || $scope.anyPage < 1){
 		  $("#first").attr("disabled",true);
 		  $("#previous").attr("disabled",true);
 	   }
    };
    
    $scope.refreshConditions = function(){
    	$scope.queryConditionData['currentPage'] = 1;
        $scope.queryConditionData['pageSize'] = $scope.pageSize;
        $("#first").attr("disabled",true);
		$("#previous").attr("disabled",true);
		$("#last").attr("disabled",false);
		$("#next").attr("disabled",false);
    }
    
    //查询方法
    $scope.queryEarlyWaringInfoList = function () {
    	$("#warningTypeDiv").show();
    	$("#accidentTypeDiv").hide();
    	$("#illegalTypeDiv").hide();
    	if(currentTab!="warning"){
    		$scope.refreshConditions();
    	}
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	$scope.queryConditionData.ascriptionCompany = $("#ascriptionCompany").val();
    	$scope.queryConditionData.department = department;
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningCopyService.queryEarlyWaringInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.earlyWaringInfoList = data.messageBody.earlyWaringInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
        currentTab = "warning";
    };
    
    //查询事故信息
    $scope.queryAccidentInfoList = function () {
    	$("#warningTypeDiv").hide();
    	$("#accidentTypeDiv").show();
    	$("#illegalTypeDiv").hide();
    	if(currentTab!="accident"){
    		$scope.refreshConditions();
    	}
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	$scope.queryConditionData.ascriptionCompany = $("#ascriptionCompany").val();
    	$scope.queryConditionData.department = department;
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
        warningCopyService.queryAccidentInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.accidentInfoList = data.messageBody.accidentInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
        currentTab = "accident";
    };
    
    //查询违法信息
    $scope.queryIllegalInfoList = function () {
    	$("#warningTypeDiv").hide();
    	$("#accidentTypeDiv").hide();
    	$("#illegalTypeDiv").show();
    	if(currentTab!="illegal"){
    		$scope.refreshConditions();
    	}
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	$scope.queryConditionData.ascriptionCompany = $("#ascriptionCompany").val();
    	$scope.queryConditionData.department = department;
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
        	$scope.queryConditionData.plateNumber = plateNumber;
    	}
        warningCopyService.queryIllegalInfoList($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.illegalInfoList = data.messageBody.illegalInfoList;
            	$scope.totalItems = data.messageBody.total;
            	$scope.currentPage = data.messageBody.currentPage;
            	$scope.pages = data.messageBody.pages;
            }
        }, function (err) {
        })
        currentTab = "illegal";
    };
    
    //查询方法(不分页，用于导出excel)
    $scope.queryEarlyWaringInfoListCounts = function () {
    	$scope.queryConditionData.startTime = $("#startTime").val();
    	$scope.queryConditionData.endTime = $("#endTime").val();
    	if($scope.queryConditionData.plateNumber!=undefined){
    		var plateNumber = $scope.queryConditionData.plateNumber.toUpperCase().trim();
    		$scope.queryConditionData.plateNumber = plateNumber;
    	}
    	warningCopyService.queryEarlyWaringInfoListCounts($scope.queryConditionData,function (data) {
    		if (data.state == 200) {
    			$scope.earlyWaringInfoListCounts = data.messageBody.earlyWaringInfoListCounts;
    			console.log($scope.earlyWaringInfoListCounts.length);
    		}
    	}, function (err) {
    	})
    };
    
    //修改抄告状态
    $scope.updateJgCcState = function(){
//    $scope.exportToExcel = function(tableId){
//    	var warningIdList = [];
    	var earlyWarning = $scope.earlyWaringInfoListCounts;
    	for(var i = 0;i<earlyWarning.length;i++){
    		console.log(earlyWarning[i].id);
    		warningIdList.push(earlyWarning[i].id);
    	}
    	$.each($("input:checkbox[name='warningId']:checked"),function(){
    		warningIdList.push($(this).val());
        });
    	$scope.disposalProcessView.warningIdList = warningIdList;
//    	$scope.exportHref = Excel.tableToExcel(tableId, 'sheet name');
//        $timeout(function() { location.href = $scope.exportHref; }, 100);
    	warningCopyService.updateJgCcState($scope.disposalProcessView,function (data) {
    		if(data.state==200){
    			var result = data.messageBody.result;
    			if(result>=1){
    	               SweetAlert.swal({
    	                   title: "抄告成功!",
    	                   type: "success",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
//    	               $scope.exportHref = Excel.tableToExcel(tableId, 'sheet name');
//    	               $timeout(function() { location.href = $scope.exportHref; }, 100); // trigger download
    	               $scope.queryEarlyWaringInfoList();
    	           }else{
    	           	SweetAlert.swal({
    	                   title: "抄告失败!",
    	                   type: "error",
    	                   timer: 2000,
    	                   confirmButtonText: "确定"
    	               });
    	           	   $scope.queryEarlyWaringInfoList();
    	           }
			};
		},function(err){
			SweetAlert.swal({
                title: "抄告失败，请联系管理员~",
                type: "error",
                timer: 2000,
                confirmButtonText: "确定"
            });
		});
    }
    
  //获取所属企业下拉列表
    $scope.inputAscriptionCompanyName = function(){
    	$scope.queryConditionData.ascriptionCompany = $("#ascriptionCompany").val();
    	console.log($scope.queryConditionData.ascriptionCompany);
    	warningCopyService.queryVehicleInfoAscriptionCompany($scope.queryConditionData,function (data) {
    	    if (data.state == 200) {
            	$scope.ascriptionCompanyList = data.messageBody.ascriptionCompanyList;
            	var list = $scope.ascriptionCompanyList;
            	console.log(list);
            	var datas = [];
            	for(var i=0;i<list.length;i++){
            		var data = {};
            		data["label"] = list[i].ascriptionCompany;
            		data["value"] = list[i].ascriptionCompany;
            		datas.push(data);
            	}
            	$('#ascriptionCompany').autocompleter('clearCache');
            	$('#ascriptionCompany').autocompleter('destroy');
            	$('#ascriptionCompany').autocompleter({
                    highlightMatches: true,
                    source: datas,
                    template: '{{ label }}',
                    hint: true,
                    empty: false,
                    limit: 5,
                    callback: function (value, index, selected) {
                        if (selected) {
                        	//$('#ascriptionCompany').attr("hideValue",selected.id); 
                        }
                    }
                });
            }
        }, function (err) {
        })
	}
    
    //导出excel
    $scope.exportToExcel = function() {
    	var startTime = "";
    	var endTime = "";
    	var competentAuthority = "";
    	var ascriptionCompany = "";
    	var sTime = $("#startTime").val();
    	var eTime = $("#endTime").val();
    	var cAuthority = $("#competentAuthority").val();
    	var aCompany = $("#ascriptionCompany").val();
    	if(sTime!=undefined){startTime = sTime;}
    	if(eTime!=undefined){endTime = eTime;}
    	if(cAuthority!=undefined){competentAuthority = cAuthority;}
    	if(aCompany!=undefined){ascriptionCompany = aCompany;}
    	window.location="/erisp-keyvehicle/service/earlyWarningInfo/exportExcel?department="+department
    		+"&startTime="+startTime+"&endTime="+endTime+"&competentAuthority="+competentAuthority
    		+"&ascriptionCompany="+ascriptionCompany;
    }
    
    //全选|反选
    $scope.swapCheck = function() {  
        if (isCheckAll) {  
            $("input:checkbox[name='warningId']").each(function() {  
                this.checked = false;  
            });  
            isCheckAll = false;  
        } else {  
            $("input:checkbox[name='warningId']").each(function() {  
                this.checked = true;  
            });  
            isCheckAll = true;  
        }  
    }  
    
    //查看详情页面
    $scope.queryDetails = function(data) {
        $scope.temp = {
            'warningDetails':data
        };
        var modalInstance = $modal.open({
            templateUrl : 'assets/views/warningDisposal/warningDetails.html',
            controller : 'warningDetailsController',
            backdrop: 'static',
            keyboard: false,
            size : 'lg',
            resolve : {
                params : function() {
                    return $scope.temp;
                }
            }
        });
        modalInstance.result.then(function(result) {
            if(result == null){
                SweetAlert.swal({
                    title: "无数据",
//                    type: "success",
                    timer: 2000,
                    confirmButtonText: "确定"
                });
            }
        }, function(err) {
        });
    };
	
	$(".form_datetime").datetimepicker({
		lang:'ch',
		timepicker:true,
		formatDate:'Y-m-d H:i'
	});
	
	$scope.tabToggle = function(){
		var $div_li =$("div.tab_menu ul li");
	    $div_li.click(function(){
			$(this).addClass("selected")            //当前<li>元素高亮
				   .siblings().removeClass("selected");  //去掉其他同辈<li>元素的高亮
	        var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
			$("div.tab_box > div")   	//选取子节点。不选取子节点的话，会引起错误。如果里面还有div 
					.eq(index).show()   //显示 <li>元素对应的<div>元素
					.siblings().hide(); //隐藏其他几个同辈的<div>元素
		}).hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		})
	}
	
	$scope.tabToggle();
	$scope.queryEarlyWaringInfoList();
	
}]);

//预警类型
app.filter('warningType', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "违规路线行驶";
				break;
			case "2":
				return "违规时间行驶";
				break;
			case "3":
				return "超速行驶";
				break;
			case "4":
				return "疲劳驾驶";
				break;
		}
    }
});

//预警类型
app.filter('warningInfo', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "车辆违规路线行驶";
				break;
			case "2":
				return "车辆违规时间行驶";
				break;
			case "3":
				return "车辆超速行驶";
				break;
			case "4":
				return "车辆疲劳驾驶";
				break;
		}
    }
});

//处置状态
app.filter('dealState', function() { 
    return function(text) {
		if(text==0){
			return "未处置";
		}else if(0<text<3){
			return "处置中";
		}else{
			return "不处置";
		}
    }
});

//抄告状态
app.filter('ccState', function() { 
    return function(text) {
		switch(text)
		{
			case "0":
				return "未抄告";
				break;
			case "1":
				return "已抄告";
				break;
		}
    }
});

//事故类型
app.filter('atype', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "简易事故";
				break;
			case "2":
				return "一般事故";
				break;
		}
    }
});

//违法类型
app.filter('itype', function() { 
    return function(text) {
		switch(text)
		{
			case "1":
				return "现场简易违法";
				break;
			case "2":
				return "现场一般违法";
				break;
			case "3":
				return "非现场电子监控";
				break;
		}
    }
});