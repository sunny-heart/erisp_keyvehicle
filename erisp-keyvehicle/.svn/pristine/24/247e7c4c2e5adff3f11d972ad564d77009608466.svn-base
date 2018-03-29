var app = angular.module('pamsApp', ['pams']);
app.run(['$rootScope', '$state', '$stateParams','$location',
function ($rootScope, $state, $stateParams,$location) {
	
//	var absUrl = $location.absUrl();
	var absUrl = "http://localhost:8889/erisp-keyvehicle/app/index.html?depId=00&roleId=1902&userId=gkhb001&groupId=null#/app/index"
	var split1 = absUrl.split("?");
	var split2 = split1[1].split("&");
	var depId = split2[0].split("=")[1];
	var roleId = split2[1].split("=")[1];
	var userId = split2[2].split("=")[1];
	var groupId = split2[3].split("#")[0].split("=")[1];
	$rootScope.resource = [];
	
	var dataUrl = "/erisp-keyvehicle/service/authController/auth.data"
	var params={"depId":depId,"roleId":roleId,"userId":userId,"groupId":groupId};
	$.ajax({
        url: dataUrl,
        type: "post",
        dataType : "json",
        data :params,
        success : function(result){
        	var fstrName = result.messageBody.result.fstrName;//用户名称
        	var fstrDesc = result.messageBody.result.fstrDesc;//用户描述
        	var groupName = result.messageBody.result.groupName;//大队名称
        	var depName = result.messageBody.result.depName;//分局名称
        	if(null != fstrName && "" != fstrName){
        		$rootScope.fstrName = fstrName;//用户名称
        	}
        	if(null != fstrDesc && "" != fstrDesc){
        		$rootScope.fstrDesc = fstrDesc;//用户描述
        	}
        	if(null != groupName && "" != groupName){
        		$rootScope.groupName = groupName;//大队名称
        	}
        	if(null != depName && "" != depName){
        		$rootScope.chineseDepName = depName;;//分局名称
        	}
//        	$rootScope.chineseDepName = "交通管理局";//分局名称
        	//中文部门名称，直接查数据库用
        	var name = $rootScope.chineseDepName;
        	switch (name) {
			case "交通管理局":
				$rootScope.depName = "00";
				break;
			case "成都市公安局":
				$rootScope.depName = "00";
				break;
			case "一分局":
				$rootScope.depName = "01";
				break;
			case "二分局":
				$rootScope.depName = "02";
				break;
			case "三分局":
				$rootScope.depName = "03";
				break;
			case "四分局":
				$rootScope.depName = "04";
				break;
			case "五分局":
				$rootScope.depName = "05";
				break;
			case "六分局":
				$rootScope.depName = "06";
				break;
			case "七分局":
				$rootScope.depName = "07";
				break;
			case "二环高架大队":
				$rootScope.depName = "08";
				break;
			default:
				break;
			}
        	if(name=="交通管理局" || name=="成都市公安局"){
        		$rootScope.departName = "";
        	}else{
        		$rootScope.departName = name;//分局名称--中文
        	}
        	$rootScope.registerAreaName = "管辖区";
        	$rootScope.bubbleTitleSize = "18";//气泡标题文字大小
        	$rootScope.bubbleContentSize = "12";//气泡内容文字大小
//        	$rootScope.resource = result.messageBody.result.resourceName;//菜单
//        	$rootScope.length = result.messageBody.result.resourceName.length;//菜单个数
        }
});
	

    // Attach Fastclick for eliminating the 300ms delay between a physical tap and the firing of a click event on mobile browsers
    FastClick.attach(document.body);

    // Set some reference to access them from any scope
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    // GLOBAL APP SCOPE
    // set below basic information
    $rootScope.app = {
        name: '重点车辆动态GPS综合监管系统', // name of your project
        author: 'eri', // author's name or company name
        description: 'Angular Bootstrap Admin Template', // brief description
        version: '1.0', // current version
        year: ((new Date()).getFullYear()), // automatic current year (for copyright information)
        isMobile: (function () {// true if the browser is a mobile device
            var check = false;
            if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
                check = true;
            };
            return check;
        })(),
        layout: {
            isNavbarFixed: true, //true if you want to initialize the template with fixed header
            isSidebarFixed: true, // true if you want to initialize the template with fixed sidebar
            isSidebarClosed: false, // true if you want to initialize the template with closed sidebar
            isFooterFixed: false, // true if you want to initialize the template with fixed footer
            theme: 'theme-4', // indicate the theme chosen for your project
            logo: 'assets/images/logo.png', // relative path of the project logo
        }
    };
    
    //用户信息
    $rootScope.user = {
//        name: 'Peter',
//        job: 'ng-Dev',
//        picture: 'app/img/user/02.jpg'
    };
}]);


// translate config
app.config(['$translateProvider',
function ($translateProvider) {

    // prefix and suffix information  is required to specify a pattern
    // You can simply use the static-files loader with this pattern:
    $translateProvider.useStaticFilesLoader({
        prefix: 'assets/i18n/',
        suffix: '.json'
    });

    // Since you've now registered more then one translation table, angular-translate has to know which one to use.
    // This is where preferredLanguage(langKey) comes in.
    $translateProvider.preferredLanguage('en');

    // Store the language in the local storage
    $translateProvider.useLocalStorage();

}]);

// Angular-Loading-Bar
// configuration
app.config(['cfpLoadingBarProvider',
function (cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeBar = true;
    cfpLoadingBarProvider.includeSpinner = false;

}]);



















