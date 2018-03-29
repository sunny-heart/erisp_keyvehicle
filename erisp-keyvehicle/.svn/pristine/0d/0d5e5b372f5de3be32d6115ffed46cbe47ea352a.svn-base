'use strict';
/**
 * baseApp Main Controller
 */
app.controller('AppCtrl', ['$rootScope','$location', '$scope','$state', '$translate', '$localStorage', '$window', '$document', '$timeout', '$interval', 'cfpLoadingBar',
function ($rootScope, $location, $scope, $state, $translate, $localStorage, $window, $document, $timeout, $interval, cfpLoadingBar) {

//     Loading bar transition
//     -----------------------------------
	
//	var $j = jQuery.noConflict();//自定义一个jQuery快捷方式
//    var $win = $j($window);
    var $win = $($window);
    
//    var absUrl = $location.absUrl();
//    var split=absUrl.split("?");
    
    $rootScope.allElements = {
			pointsElements:null,
			polylineElements:null,
			polygonElements:null
	};

    // deterring browser's default events
    $(document).bind("contextmenu",function(e){
        e.preventDefault();
        return false;
    });
    
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //start loading bar on stateChangeStart
    	/*var temp = false;
    	try{
    		if(null != $rootScope.timer){
	        		clearInterval($rootScope.timer);
        	}
        	if(null != $rootScope.allElements){
        		if(null != $rootScope.allElements.pointsElements){
        			removeElements_common($rootScope.allElements.pointsElements);
        			$rootScope.allElements.pointsElements = null;
        		}
        		if(null != $rootScope.allElements.polylineElements){
        			removeElements_common($rootScope.allElements.polylineElements);
        			$rootScope.allElements.polylineElements = null;
        		}
        		if(null != $rootScope.allElements.polygonElements){
        			removeElements_common($rootScope.allElements.polygonElements);
        			$rootScope.allElements.polygonElements = null;
        		}
        	}
	        
        	// 解决浏览器在没有地图的页面刷新时，会出现地图的问题
        	switch (toState.title) {
				case "资产查询":
				case "基站管理":
				case "读卡器管理":
				case "天线管理":
					temp = true;
					break;

				default:
					break;
			}
        }catch(e){
        	
        }  
	    if(temp) {
	    	$timeout(
	    			function() {
	    				$("#serverDiv").css("display","none");
	                },1200
	        );
	    }*/
    	cfpLoadingBar.start();
    });
    
    /*$rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //start loading bar on stateChangeStart
        cfpLoadingBar.start();

    });*/
    $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {

        //stop loading bar on stateChangeSuccess
        event.targetScope.$watch("$viewContentLoaded", function () {

            cfpLoadingBar.complete();
        });

        // scroll top the page on change state

//        $document.scrollTo(0, 0);

        if (angular.element('.email-reader').length) {
            angular.element('.email-reader').animate({
                scrollTop: 0
            }, 0);
        }

        // Save the route title
        $rootScope.currTitle = $state.current.title;
    });

    // State not found
    $rootScope.$on('$stateNotFound', function (event, unfoundState, fromState, fromParams) {
        //$rootScope.loading = false;
        console.log(unfoundState.to);
        // "lazy.state"
        console.log(unfoundState.toParams);
        // {a:1, b:2}
        console.log(unfoundState.options);
        // {inherit:false} + default options
    });

    $rootScope.pageTitle = function () {
        return $rootScope.app.name + ' - ' + ($rootScope.currTitle || $rootScope.app.description);
    };

    // save settings to local storage
    if (angular.isDefined($localStorage.layout)) {
        $scope.app.layout = $localStorage.layout;

    } else {
        $localStorage.layout = $scope.app.layout;
    }
    $scope.$watch('app.layout', function () {
        // save to local storage
        $localStorage.layout = $scope.app.layout;
    }, true);

    //global function to scroll page up
    $scope.toTheTop = function () {

        $document.scrollTopAnimated(0, 600);

    };

    // angular translate
    // ----------------------

    $scope.language = {
        // Handles language dropdown
        listIsOpen: false,
        // list of available languages
        available: {
            'en': 'English',
            'it_IT': 'Italiano',
            'de_DE': 'Deutsch'
        },
        // display always the current ui language
        init: function () {
            var proposedLanguage = $translate.proposedLanguage() || $translate.use();
            var preferredLanguage = $translate.preferredLanguage();
            // we know we have set a preferred one in app.config
            $scope.language.selected = $scope.language.available[(proposedLanguage || preferredLanguage)];
        },
        set: function (localeId, ev) {
            $translate.use(localeId);
            $scope.language.selected = $scope.language.available[localeId];
            $scope.language.listIsOpen = !$scope.language.listIsOpen;
        }
    };

    $scope.language.init();

    // Function that find the exact height and width of the viewport in a cross-browser way
    var viewport = function () {
        var e = window, a = 'inner';
        if (!('innerWidth' in window)) {
            a = 'client';
            e = document.documentElement || document.body;
        }
        return {
            width: e[a + 'Width'],
            height: e[a + 'Height']
        };
    };
    // function that adds information in a scope of the height and width of the page
    $scope.getWindowDimensions = function () {
        return {
            'h': viewport().height,
            'w': viewport().width
        };
    };
    // Detect when window is resized and set some variables
    $scope.$watch($scope.getWindowDimensions, function (newValue, oldValue) {
        $scope.windowHeight = newValue.h;
        $scope.windowWidth = newValue.w;
        if (newValue.w >= 992) {
            $scope.isLargeDevice = true;
        } else {
            $scope.isLargeDevice = false;
        }
        if (newValue.w < 992) {
            $scope.isSmallDevice = true;
        } else {
            $scope.isSmallDevice = false;
        }
        if (newValue.w <= 768) {
            $scope.isMobileDevice = true;
        } else {
            $scope.isMobileDevice = false;
        }
    }, true);
    
    // Apply on resize
    $win.on('resize', function () {
        $scope.$apply();
    });
    
    //用于控制地图是否加载完成
//    $rootScope.isMapEnd = false;
//    function init(){
//    	alert(1);
//    	var map;
//    	require([  
//			"esri/map"
//		], function(Map) {  
//			alert(2);
//			map = new Map("map", {  
//			basemap: "topo",  
//			center: [104.0657754083, 30.6583098090],  
//			zoom: 14,
//			logo:false
//		});
//			});
//    	}
	
//	$scope.load = function() {
//    	init();
//    };
    
//		tgisServer.initServer("serverDiv");
//    	tgisServer.Event.serverStartFinished=function(){  
//    		tgisServer.toEarthView();//切换为三维地图
//        	$rootScope.isMapEnd = true;
//    	 };
//    	tgisServer.initServer("serverDiv"); 
//		tgisServer.Map.centerAndZoom(93.50270,42.846914,15);
//		if(null != document.getElementById("serverDiv")){
//			 document.getElementById("serverDiv").style.height = ($(window).height()-70)+"px";
//		}
//		tgisServer.SetTransformButtonRightBottom();
	
//    $rootScope.dOr2d = "";//用于判断是"3d","2d"
//    tgisServer.Event.showTypeChanged(function(e){ 
//    	 $rootScope.dOr2d = e;
//    });
     
    /********复选框状态**********************/
    $rootScope.checkedName="";
    $rootScope.checkedState=false;
    $scope.vehicleTypeClick = function($event){
    	$rootScope.checkedName = $event.target.value;
    	$rootScope.checkedState = $event.target.checked;
    }
    
    /********预警监控页面复选框状态**********************/
    $rootScope.warningCheckedName="";
    $rootScope.warningCheckedState=false;
    $scope.warningVehicleTypeClick = function($event){
    	$rootScope.warningCheckedName = $event.target.value;
    	$rootScope.warningCheckedState = $event.target.checked;
    }
    
    $rootScope.basePicUrl = "/vehiclePic/";
    
}]);
