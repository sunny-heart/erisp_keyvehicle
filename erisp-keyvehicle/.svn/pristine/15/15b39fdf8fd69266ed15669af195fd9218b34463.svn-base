/**
 * 预警规则设置
 */
app.controller('warningRuleController',['$rootScope','$scope', '$element', '$parse', '$state','$modal','SweetAlert',"$http", "$stateParams",
function($rootScope, $scope, $element, $parse, $state, $modal, SweetAlert, $http, $stateParams) {

    var map;
    var toolbar;
    var webMercatorUtil;

    $scope.ruleData = {
        rule : [],
        vehicleType : [],
        cityCard : []
    };

    //车辆类型
    $scope.carTypeList = [{
        id : '',
        name : '物流车',
        items : [{
            id : 'A1',
            name : '新能源车'
        }, {
            id : 'A3',
            name : '危化品车'
        }, {
            id : 'A2',
            name : '冷链车'
        }, {
            id : 'A4',
            name : '普通车'
        }]
    }, {
        id : 'C',
        name : '公交车'
    }, {
        id : 'B',
        name : '出租车'
    } ];

    // 车牌类型数据
    $scope.carPlateTypeList = [{
        id : '01',
        name : '大车'
    }, {
        id : '02',
        name : '小车'
    }/*, {
        id : 'green',
        name : '绿牌'
    }*/];

    $scope.ringRoad = ringRoad;

    // 入城证数据
    $scope.cityCards = [
        {id: 'A', name: 'A'},
        {id: 'M', name: 'A1'},
        {id: 'N', name: 'A2'},
        {id: 'E', name: 'A3'},
        {id: 'B', name: 'B'},
        {id: 'P', name: 'B1'},
        {id: 'Q', name: 'B2'},
        {id: 'C', name: 'C'},
        {id: 'R', name: 'C1'},
        {id: 'S', name: 'C2'},
        {id: 'T', name: 'C3'},
        {id: 'U', name: 'D'},
        {id: 'D', name: 'D1'},
        {id: 'F', name: 'D2'},
        {id: 'V', name: 'E'},
        {id: 'G', name: 'F'},
        {id: 'H', name: 'G1'},
        {id: 'I', name: 'G2'},
        {id: 'J', name: 'G3'},
        {id: 'K', name: 'G4'},
        {id: 'L', name: 'H'},
        {id: 'W1', name: '交通车'},
        {id: 'Z', name: '快递'},
        {id: 'X', name: '三绿'},
        {id: 'W', name: '校车'},
        {id: 'Y', name: '园林'},
        {id: 'X1', name: '早餐'}
    ];

    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        $("input.form_datetime").datetimepicker({
            format: 'H:i',
            maxView: 1,
            startView: 0,
            minView: 0,
            datepicker:false
        });
    });

    /**
     * 加载数据
     * @param param
     * @param isAsync
     */
	$scope.loadList = function (param, isAsync) {
        if (isAsync) {
            // 同步加载
            $.ajax({
                url : '/erisp-keyvehicle/service/warningRule/queryWarningRuleList?t='+ new Date().getTime(),
                method : 'GET',
                data : param,
                dataType : 'json',
                contentType : "application/x-www-form-urlencoded;charset=UTF-8",
                async : false,
                success : function (data) {
                    if(data.state==200 && data.messageBody.warningRuleList.length >= 1){
                        $scope.warningRuleList = data.messageBody.warningRuleList;
                    }
                },
                error : function (data) {

                }
            });
        } else {
            $http({
                url : '/erisp-keyvehicle/service/warningRule/queryWarningRuleList?t='+ new Date().getTime(),
                method : "GET",
                params : param
            }).success(function(data) {
                $scope.warningRuleList = data.messageBody.warningRuleList;
            }).error(function(data) {
            });
        }
    };

    /**
     * 页面回显绑定数据
     */
	$scope.loadRuleData = function () {
        var id = $stateParams.id;
        if (id !== undefined && id !== null && id !== '') {
            $scope.loadList({id : id}, true);
            var ruleData =  $scope.warningRuleList[0];
            $.extend($scope.ruleData, ruleData);
            delete $scope.ruleData.createTime;
            delete $scope.ruleData.updateTime;
            $scope.ruleData.rule = JSON.parse($scope.ruleData.rule);
            $scope.ruleData.vehicleType = JSON.parse($scope.ruleData.vehicleType);
            $scope.ruleData.cityCard = JSON.parse($scope.ruleData.cityCard);
        }
        $stateParams.id = null;
    };


	$scope.isCheck = function (item, list) {
        return $.inArray(item, list) >= 0;
    };

	$scope.getJsonObject = function (jsonString) {
        return JSON.parse(jsonString);
    };

    $scope.getJsonString = function (jsonObject) {
        return JSON.stringify(jsonObject);
    };

    /**
     * 根据车辆类型ID生成车辆类型
     * @param vehicleTypeId
     */
    $scope.getVehicleType = function (vehicleTypeId, vehicleTypes) {
        var result = null;
        if (vehicleTypes === undefined || vehicleTypes.length === 0) {
            vehicleTypes = $scope.carTypeList;
        }
        $.each(vehicleTypes, function (i, elem) {
            if (elem.id === vehicleTypeId){
                result = elem;
                return result;
            }
            if (elem.items !== undefined && elem.items.length > 0) {
                result = $scope.getVehicleType(vehicleTypeId, elem.items);
            }
        });
        if (result !== null) return result;
    };

	$scope.getVehicleTypes = function(vehicleTypes){
        var result = [];
        if (vehicleTypes === undefined || vehicleTypes.length === 0) {
            vehicleTypes = $scope.carTypeList;
        }
        $.each(vehicleTypes, function (i, elem) {
            if (elem.id !== undefined && elem.id !== ''){
                result.push(elem);
            }
            if (elem.items !== undefined && elem.items.length > 0) {
                result = result.concat($scope.getVehicleTypes(elem.items));
            }
        });
        return result;
    };

    /**
     * 保存速度预警规则
     */
	$scope.saveSpeedRule = function () {
        var vehicleType = [];
        $('input[name="vehicleType"]:checked').each(function(){
            vehicleType.push($(this).val())
        });
        $scope.ruleData.vehicleType = vehicleType;
        var dataUrl = null;
        if ($scope.ruleData.id === undefined || $scope.ruleData.id === ''){
            dataUrl = "/erisp-keyvehicle/service/warningRule/addSpeedRule";
		} else {
            dataUrl = "/erisp-keyvehicle/service/warningRule/updateRule";
		}

        save(dataUrl, $scope.ruleData);
    };


    /**
     * 保存疲劳预警规则
     */
	$scope.saveTiredRule = function () {
        var vehicleType = [];
        $('input[name="vehicleType"]:checked').each(function(){
            vehicleType.push($(this).val())
        });
        $scope.ruleData.vehicleType = vehicleType;
        var dataUrl = null;
        if ($scope.ruleData.id === undefined || $scope.ruleData.id === ''){
            dataUrl = "/erisp-keyvehicle/service/warningRule/addTiredRule";
        } else {
            dataUrl = "/erisp-keyvehicle/service/warningRule/updateRule";
        }

        save(dataUrl, $scope.ruleData);
    };

    /**
     * 保存时间区域预警规则
     */
	$scope.saveTimeAreaRule = function () {
        var vehicleType = [];
        $('input[name="vehicleType"]:checked').each(function(){
            vehicleType.push($(this).val())
        });
        $scope.ruleData.vehicleType = vehicleType;
        var cityCards = [];
        $('input[name="cityCard"]:checked').each(function(){
            cityCards.push($(this).val())
        });
        $scope.ruleData.cityCard = cityCards;
        if ($scope.ruleData.cityCard == '' || $scope.ruleData.cityCard == null){
        	SweetAlert.swal("注意", '请选择入城证！', "warning");
            return;
        }
//        $scope.ruleData.rule[0].cityCard = cityCards;
        var dataUrl = null;
        if ($scope.ruleData.id === undefined || $scope.ruleData.id === ''){
            dataUrl = "/erisp-keyvehicle/service/warningRule/addTimeAreaRule";
        } else {
            dataUrl = "/erisp-keyvehicle/service/warningRule/updateRule";
        }

        save(dataUrl, $scope.ruleData);
    };

    /**
     * 保存车辆聚集预警规则
     */
    $scope.saveBusFocusRule = function () {
        if($scope.ruleData.rule[0].vehicleNumberLimit === undefined || $scope.ruleData.rule[0].vehicleNumberLimit === null) {
            SweetAlert.swal("请输入车辆数量上限！", '', "warning");
            return;
        }
        var vehicleType = [];
        $('input[name="vehicleType"]:checked').each(function(){
            vehicleType.push($(this).val())
        });
        $scope.ruleData.vehicleType = vehicleType;
        var dataUrl = null;
        if ($scope.ruleData.id === undefined || $scope.ruleData.id === ''){
            dataUrl = "/erisp-keyvehicle/service/warningRule/addBusFocusRule";
        } else {
            dataUrl = "/erisp-keyvehicle/service/warningRule/updateRule";
        }

        save(dataUrl, $scope.ruleData);
    };

    function save(url, ruleData) {
        var _data = {};
        $.extend(_data, ruleData);
        var validationStr = validation(_data);
        if(validationStr) {
            SweetAlert.swal("注意", validationStr, "warning");
            return;
        }
        _data.rule = JSON.stringify(_data.rule);
        _data.vehicleType = JSON.stringify(_data.vehicleType);
        _data.cityCard = JSON.stringify(_data.cityCard);
        $.ajax({
            url : url,
            method : 'POST',
            data : _data,
            dataType : 'json',
            contentType : "application/x-www-form-urlencoded;charset=UTF-8",
            success : function (data) {
                if(data.state==200 && data.messageBody.result==1){
                    $("#speedRuleId").val(data.messageBody.id);
                    $state.go("app.warningRule.ruleQuery");
                } else {
                    SweetAlert.swal("保存规则失败！", "", "error");
                }
            },
            error : function (data) {
                SweetAlert.swal("保存规则失败！", "", "error");
            }
        })
    }

    function validation(ruleData) {
        if (ruleData.name == '' || ruleData.name == null){
            return '请输入规则名称！';
        }
        if (ruleData.vehicleType == '' || ruleData.vehicleType == null){
            return '请选择车辆类型！';
        }
    }
    /**
     * 列表页跳转至规则录入页
     * @param id
     * @param ruleType
     */
    $scope.location2Update = function (id, ruleType) {
        var path = null;
        if (ruleType == 'SPEED') {
        	path = 'app.warningRule.ruleSpeedAdd';
		} else if(ruleType == 'TIRED') {
            path = 'app.warningRule.ruleTiredAdd';
        } else if(ruleType == 'TIME_AREA'){
            path = 'app.warningRule.ruleTimeAreaAdd';
        } else if(ruleType == 'BUS_FOCUS'){
            path = 'app.warningRule.ruleBusFocusAdd';
        }
        $state.go(path,{id : id});
    };

    /**
     * 删除规则
     *
     * @param id
     * @param ruleType
     */
    $scope.deleteRule = function (id, ruleType) {
        SweetAlert.swal({
                title: "确定删除吗？",
                text: "你将删除该预警规则！",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            },
            function(isConfirm){
                if (isConfirm) {
                    $.ajax({
                        url : "/erisp-keyvehicle/service/warningRule/deleteWarningRule",
                        method : 'POST',
                        data : {
                            id : id,
                            ruleType : ruleType
                        },
                        dataType : 'json',
                        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
                        async : false,
                        success : function (data) {
                            if(data.state == 200 && data.messageBody.result == 1){
                                $("#speedRuleId").val(data.messageBody.id);
                                swal("预警规则删除成功！", "。", "success");
                                $scope.loadList();
                            };
                        },
                        error : function (data) {
                            SweetAlert.swal("删除规则失败！", "", "error");
                        }
                    });
                }
            });
    };

    $scope.addTimeRange = function () {
        var timeRangeTotal = $scope.ruleData.rule[0].timeRange.length;
        if (timeRangeTotal >= 5){
            SweetAlert.swal("最多添加五条时间区间！", "", "warning");
            return;
        }
        $scope.ruleData.rule[0].timeRange[timeRangeTotal] = {};
    };

    $scope.initTimeRange = function () {
        if ($scope.ruleData.rule === undefined || $scope.ruleData.rule.length === 0)
            $scope.ruleData.rule = [{}];
        if ($scope.ruleData.rule[0].timeRange === undefined)
            $scope.ruleData.rule[0].timeRange = [{}];
    }
    /**********************************地图图层显示start**********************************/
    $scope.showMap = function(){require([
        "esri/map",
        "esri/toolbars/draw",
        'esri/geometry/Geometry',
        "esri/layers/ArcGISTiledMapServiceLayer",
        "esri/geometry/webMercatorUtils",
        'esri/layers/GraphicsLayer',
        "esri/graphic",
        'esri/symbols/SimpleFillSymbol',
        'esri/symbols/SimpleLineSymbol',
        'esri/Color'
        ], function (
            Map,
            Draw,
            Geometry,
            ArcGISTiledMapServiceLayer,
            webMercator,
            GraphicsLayer,
            Graphic,
            SimpleFillSymbol,
            SimpleLineSymbol,
            Color
    ) {
        // BASE_SERVER配置移至app.js
   	    map = new Map("map", {
            // basemap: "streets",
            center: [104.0657754083, 30.6583098090],
            zoom: 3//内网地图缩放等级
        });

        var layer = new ArcGISTiledMapServiceLayer(BASE_SERVER);
        map.addLayer(layer);

        map.on("load", function () {
            //回显地图区域
            if($scope.ruleData.rule[0] !== undefined){
                for (var area in $scope.ruleData.rule[0].area){
                    if (JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.ringRoad1])){
                        //一环
                        angular.element('input[id=ringRoad1]').prop('checked', true);
                    } else if(JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.ringRoad2, $scope.ringRoad.ringRoad1])){
                        //一环至二环
                        angular.element('input[id=ringRoad2]').prop('checked', true);
                    }else if(JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.ringRoad3, $scope.ringRoad.ringRoad2])){
                        //二环至三环
                        angular.element('input[id=ringRoad3]').prop('checked', true);
                    }else if(JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.ringRoad4, $scope.ringRoad.ringRoad3])){
                        //三环至四环
                        angular.element('input[id=ringRoad4]').prop('checked', true);
                    } else if(JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.tianfuguangchang])){
                        //天府广场附近
                        angular.element('input[id=tianfuguangchang]').prop('checked', true);
                    } else if(JSON.stringify($scope.ruleData.rule[0].area[area]) === JSON.stringify([$scope.ringRoad.renmingzhengfu])){
                        //人民政府附近
                        angular.element('input[id=renmingzhengfu]').prop('checked', true);
                    }
                    $scope.drawSelectArea($scope.ruleData.rule[0].area[area]);
                }
            }
        });

        toolbar = new Draw(map, {
            showTooltips: true,
            tooltipOffset: 20,
            drawTime: 90,
            tolerance: 1 //最高精度
        });

        webMercatorUtil = webMercator;
        // toolbar.on("draw-complete", addToMap);
        // 绘制区域完成
        toolbar.on("draw-end", function (event) {
            var rings = event.geometry.rings[0];
            var points = [];
            for (var i = 0; i < rings.length; i++){
                if (DEVELOP_MODE) {
                    // 将坐标转为经纬度
                    var value = webMercator.xyToLngLat(rings[i][0], rings[i][1]);
                    points.push(value);
                } else {
                    //生产环境无需转换
                    points.push(rings[i]);
                }
            }
            console.log(JSON.stringify(points));
            if ($scope.ruleData.rule[0] === undefined) {
                $scope.ruleData.rule[0] = {}
            }
            if (!$scope.ruleData.rule[0].area) {
                $scope.ruleData.rule[0].area = [];
            }
            $scope.ruleData.rule[0].area.push([points]);
            $scope.addToMap(event.geometry);
            toolbar.deactivate();
            map.enableMapNavigation();
        });

        // 激活绘图工具
        $scope.activateToolbar = function () {
            var isSelectModel = false;
            $.each(angular.element('input[id^=ringRoad]'), function (i, elem) {
                if($(elem).prop('checked')){
                    isSelectModel = true;
                }
            });
            if (isSelectModel) {
                SweetAlert.swal("当前为选择预置数据模式。\n如需手动绘制区域，请先清除已选择的预制数据！", "", "warning");
            } else {
                toolbar.activate(Draw.POLYGON);
            }
        };

        // 向地图中添加绘制的图形数据
        $scope.addToMap = function(geometry, isInnerRing){
            //针对带状区域特殊处理，此处有较强的逻辑性，
            for (var graphic in map.graphics.graphics){
                if (map.graphics.graphics[graphic].geometry.rings !== undefined
                    && JSON.stringify(map.graphics.graphics[graphic].geometry.rings) === JSON.stringify(geometry.rings)){
                    map.graphics.remove(map.graphics.graphics[graphic]);
                    return;
                }
            }
            var fillColor = [255,0,0,0.2];
            if (isInnerRing){
                fillColor = [0,255,255,0.2];
            }
            var graphic = new Graphic(geometry, new SimpleFillSymbol(
                SimpleFillSymbol.STYLE_SOLID,
                new SimpleLineSymbol(
                    SimpleLineSymbol.STYLE_SOLID,
                    new Color([238,0,0,0.9]),
                    3
                ),new Color(fillColor)));
            map.graphics.add(graphic);
        };

        // 从地图中移除指定图形
        $scope.removeFromMap = function (geometry, isInnerRing) {
            var isRemove = false;
            for (var graphic in map.graphics.graphics){
                if (map.graphics.graphics[graphic].geometry.rings !== undefined
                    && JSON.stringify(map.graphics.graphics[graphic].geometry.rings) === JSON.stringify(geometry.rings)){
                    map.graphics.remove(map.graphics.graphics[graphic]);
                    isRemove = true;
                    break;
                }
            }
            //针对带状区域特殊处理，此处有较强的逻辑性，
            if (!isRemove){
                $scope.addToMap(geometry, isInnerRing);
            }
        }
    })};

    /**
     * 绘制一个带状或者环形区域，
     */
    $scope.drawSelectArea = function (ribbon) {require([
        'esri/geometry/Geometry', 'esri/geometry/Point', 'esri/geometry/jsonUtils', 'esri/geometry/Polygon', 'esri/SpatialReference'
    ], function (Geometry, Point, jsonUtils, Polygon, SpatialReference) {
        if (ribbon !== undefined && ribbon.length > 0) {
            for (var m = 0; m < ribbon.length; m++) {
                var rings = [];
                for (var j = 0; j < ribbon[m].length; j++) {
                    var xy = webMercatorUtil.lngLatToXY(ribbon[m][j][0], ribbon[m][j][1]);
                    rings.push(xy);
                }
                if (rings.length === 0) {
                    continue;
                }
                var polygon = new Polygon([rings]);
                var _wkid;
                if(DEVELOP_MODE){
                    _wkid = {
                        wkid: 102100
                    };
                } else {
                    _wkid = {
                        //wkid: 4326
                        wkid: 102100
                    }
                }
                var spatialReference = new SpatialReference(_wkid);
                polygon.setSpatialReference(spatialReference);
                var isInnerRing = false;
                if (m === 1) {
                    isInnerRing = true;
                } else {
                    isInnerRing = false;
                }
                $scope.addToMap(polygon, isInnerRing);
            }
        }
    })};

    /**
     * 移除一个带状或者环形区域，
     */
    $scope.removeSelectArea = function (ribbon) {require([
        'esri/geometry/Geometry', 'esri/geometry/Point', 'esri/geometry/jsonUtils', 'esri/geometry/Polygon', 'esri/SpatialReference'
    ], function (Geometry, Point, jsonUtils, Polygon, SpatialReference) {
        if (ribbon !== undefined && ribbon.length > 0) {
            for (var m = 0; m < ribbon.length; m++) {
                var rings = [];
                for (var j = 0; j < ribbon[m].length; j++) {
                    var xy = webMercatorUtil.lngLatToXY(ribbon[m][j][0], ribbon[m][j][1]);
                    rings.push(xy);
                }
                if (rings.length === 0) {
                    continue;
                }
                var polygon = new Polygon([rings]);
                var wkid = {
                    wkid: 102100,
                    latestWkid: 3857
                };
                var spatialReference = new SpatialReference(wkid);
                polygon.setSpatialReference(spatialReference);
                var isInnerRing = false;
                if (m === 0) {
                    isInnerRing = true;
                } else {
                    isInnerRing = false;
                }
                $scope.removeFromMap(polygon, isInnerRing);
            }
        }
    })};
    /**********************************地图图层显示end**********************************/


    //开始选择区域
    $scope.selectMapArea = function () {
        map.disableMapNavigation();
        $scope.activateToolbar();
    };

    //清除已选择区域
    $scope.clearMapArea = function () {
        map.graphics.clear();
        $scope.ruleData.rule[0].area = [];
        angular.element('input[id^=ringRoad]').removeAttr('checked');
    };

    // 区域预制数据点击选择
    $scope.ringRoadCheckChange = function (event) {
        var ribbon;
        if ($(event.target).attr("id") === 'tianfuguangchang'){
            $scope.clearMapArea();
            ribbon = [$scope.ringRoad.tianfuguangchang]
        } else if ($(event.target).attr("id") === 'renmingzhengfu'){
            $scope.clearMapArea();
            ribbon = [$scope.ringRoad.renmingzhengfu]
        } else if ($(event.target).attr("id").indexOf('1') === -1){
            ribbon = [$scope.ringRoad[$(event.target).attr("id")], $scope.ringRoad[$(event.target).prevAll('input').get(0).id]];
        } else {
            ribbon = [$scope.ringRoad[$(event.target).attr("id")]]

        }

        if ($scope.ruleData.rule[0].area === undefined){
            $scope.ruleData.rule[0].area = [];
        }
        if ($(event.target).is(':checked')) {
            $scope.ruleData.rule[0].area.push(ribbon);
            $scope.drawSelectArea(ribbon);
        } else {
            for (var i = 0; i < $scope.ruleData.rule[0].area.length; i++){
                if (JSON.stringify($scope.ruleData.rule[0].area[i]) === JSON.stringify(ribbon)){
                    $scope.ruleData.rule[0].area.splice(i, 1);
                }
            }
            $scope.removeSelectArea(ribbon);
        }
    }
} ]);

app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope,element,attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });

            }
        }
    };
});

function onSelectEndTimeRange(obj) {
    if ($(obj).prev().val() === '? undefined:undefined ?'){
        return;
    }
    if ($(obj).prev().val().localeCompare($(obj).val()) >= 0){
        $(obj).val('24:00');
    }
}

function onSelectStartTimeRange(obj) {
    if ($(obj).next().val() === '? undefined:undefined ?'){
        return;
    }
    if ($(obj).next().val().localeCompare($(obj).val()) <= 0){
        $(obj).val('00:00');
    }
}