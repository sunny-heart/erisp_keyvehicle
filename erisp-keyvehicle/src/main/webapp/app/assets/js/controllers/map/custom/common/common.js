//////////////////公共方法/////////////////
/**
 * 初始化地图,按照二维地图定位
 * @param mapDiv 需要加载地图的div的id 如："serverDiv"
 * @param initLong 经度
 * @param initLat 纬度
 * @param initZoom 层级
 * @return tgisServer地图对象
 */
function initMap_common(mapDiv,initLong,initLat,initZoom){
//	if(null != tgisServer){
		var map;
    	require([  
			"esri/map"
		], function(Map) {  
			alert(2);
			map = new Map("map", {  
			basemap: "topo",  
			center: [104.0657754083, 30.6583098090],  
			zoom: 14,
			logo:false
		});
			});
//		tgisServer.initServer(mapDiv);
//		tgisServer.Map.centerAndZoom(initLong,initLat,initZoom);
//	}
}

/**
 * 在地图上批量添加点
 * @param dataPois后台传过来的点数据
 * @param mappath二维地图图片路径
 * @param earthpath 三维地图图片路径
 * @param imgWidth 图片宽度
 * @param imgHeight 图片高都
 * @param callback 点击点之后的回调函数
 */ 
function addPointsOnMap_common(dataPois,mappath,earthpath,imgWidth,imgHeight,callback,service_type){
	var elements =[];
	if(null == tgisServer || null == dataPois || dataPois.length == 0){
		return;
	}
	 for (var i = 0; i < dataPois.length; i++) {
		 var icon_text = dataPois[i].assetName;
		 if(service_type == "move_path"){
        	 icon_text = formatDate(dataPois[i].lastTime,"-");
         }
		 var config={  
			name:dataPois[i].id,
			text:icon_text,
			mapicon:mappath,  
			earthicon:earthpath,  
			id:tgisServer.createGuid(),  
			width:imgWidth,  
			height:imgHeight,  
			longitude:dataPois[i].longitude,  
			latitude:dataPois[i].latitude,
			attribute:dataPois[i]
			};  
		
		 var marker=tgisServer.createMarker(config);
		 elements.push(marker);
		 tgisServer.Event.AddMarkerClickEvent(marker,function(pval){
			 callback(pval,marker);
		 });
	 }
	 return elements;
}

/**
 * 在地图上添加线
 * @param  passData 点数据的集合
 * @param lineColor线的颜色 "#FF0000"
 * @param lineWidth线的宽度 2
 * @param opacityNum线的透明度 0-1
 * @return elements 线的数组
 */
function addPolylineOnMap_common(passData,lineColor,lineWidth,opacityNum){
	var elements = [];
	if(null == tgisServer || null == passData || passData.length == 0){
		return;
	}
	var Points=[];
	 for (var i = 0; i < passData.length; i++) {
         var point = {
        	lon: passData[i].longitude,
        	lat: passData[i].latitude
         }
         Points.push(point);  
     }
	var lineObj={  
	name:"line",  
	id:tgisServer.createGuid(),
	width:lineWidth,  
	color:lineColor,  
	points:Points,  
	altitudetype:1,//默认是1。1代表贴地、2代表贴模型、3代表绝对高程  
	opacity:opacityNum 
	};  
	tgisServer.createPolyline(lineObj);
	elements.push(lineObj);
	return elements;
}

/**
 * 已知经纬度在地图上添加面
 * @param  lineWidth 边线宽度
 * @param lineColor 边线颜色
 * @param fillColor 填充颜色
 * @param lineOpacity 线的透明度
 * @param fillOpacity 填充的透明度
 * @param datas 点集
 * 
 */
function addPolygonOnMap_common(lineWidth,lineColor,fillColor,lineOpacity,fillOpacity,datas){
	var elements = [];
	Points=[]; 
	for (var i = 0; i < datas.length; i++) {
		var point = {
			lon:datas[i].X,
			lat:datas[i].Y
		};
		Points.push(point);
	}
	var polygonObj={  
			name:"polygon",  
			id:tgisServer.createGuid(),  
			lineWidth:lineWidth,  
			lineColor:lineColor,  
			fillColor:fillColor,  
			lineOpacity:lineOpacity,  
			fillOpacity:fillOpacity,  
			altitudetype:1,//默认是1。0代表贴模型、1代表贴地、3代表绝对高程  
			points:Points  
			};  
	tgisServer.createPolygon(polygonObj);
	elements.push(polygonObj);
	return elements;
}

/**
 * 手动添加面
 * @param lineWidth
 * @param lineColor
 * @param fillColor
 * @param lineOpacity
 * @param fillOpacity
 * @param callback
 */
function addPolygonOnMap_common2(lineWidth,lineColor,fillColor,lineOpacity,fillOpacity,callback){
	tgisServer.Event.polygonCreateEvent(function(points){
		var polygonObj={
			name:"polygon",
		 	id:tgisServer.createGuid(),
			lineWidth:lineWidth,
			lineColor:lineColor,
			lineOpacity:lineOpacity,
			fillOpacity:fillOpacity,
			fillColor:fillColor,
			altitudetype:2,//默认是1。1代表贴地、2代表贴模型、3代表绝对高程
			points:points
		};
		tgisServer.createPolygon(polygonObj);
		callback(polygonObj);
	});
}

/**
 * 移除地图上的对象
 * @param elements 需要移除的地图对象
 */
function removeElements_common(elements){
	if(null == elements || elements.length == 0||null == tgisServer){
		return;
	}
	for(var i=0;i<elements.length;i++){
		tgisServer.removeObj(elements[i]);
	}
}


/**
 * 三维飞行定位
 * @param  lon 经度
 * @param  lat纬度
 * @param  heading 朝向
 * @param  tilt 俯仰
 * @param  range 视距
 * @param  time 时间
 */
function flyTo_common(lon,lat,heading,tilt,range,time){
	tgisServer.Earth.flyToLookAt(lon,lat,heading,tilt,range,time);
}

/**
 * 地图定位到中心点
 * @param is2d
 * @param lon 经度
 * @param lat 纬度
 */
function mapCenterAt_common(is2d,center){
	if(null == is2d || center == null){
		return;
	}
	if(is2d == "3d"){
		flyTo_common(center.lon,center.lat,360,89,508.7,2);
	}else if(is2d == "2d"){
		tgisServer.Map.centerAndZoom(center.lon,center.lat,18);  
	}
}

 

/**
 * 判断一个点是否在面里面
 * @param inpoint 判断的点 longitude latitude
 * @param inpolygon 判断的面 
 * @return true 内 false 外
 */
function PolygonContainsPoint_common(inpoint,inpolygon){
	if(null == inpolygon || null == inpoint){
		return;
	}
	var points = inpolygon[0].mappoints;
	var inpolCount = points.length;
	var nCross = 0;
	for(var i= 0;i<inpolCount;++i){
		var p1 = points[i];
		var p2 = points[(i + 1) % inpolCount];
		if(p1.y == p2.y){// p1p2 与 y=p0.y平行
			continue;
		}
		if(inpoint.latitude < Math.min(p1.y, p2.y)){// 交点在p1p2延长线上
			continue;
		}
		if(inpoint.latitude >= Math.max(p1.y, p2.y)){// 交点在p1p2延长线上
			continue;
		}
		 // 求交点的 X 坐标
		var x = (inpoint.latitude - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
		if(x > inpoint.longitude){ // 只统计单边交点
			nCross++;
		}
	}
	// 单边交点为偶数，点在多边形之外
	return (nCross%2 == 1);
}

/**
 * 鼠标左键获取经纬度
 * @param callback 回调函数
 */
function leftClickGetXy(callback){
	tgisServer.Event.MouseLeftKeyEvent(callback); 
}


/**
 * 根据id获取地图上的点对象
 * @param id 数据的id
 * @param resultPoints 之前地图上打点返回的值
 * @returns 点对象
 */
function queryElemntById_common(id,resultPoints){
	var element = null;
	var guidId = resultPoints.guidId;
	for(var i=0;i<guidId.length;i++){
		if(guidId[i].value == id){
			element = guidId[i].element;
		}
	}
	return element;
}

/////////////////////////其他//////////
/**
 * 获取路径
 */
function getRealPath_common(){
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;
    return basePath ;
     }

/**
 *颜色选择 
 */
function taxiColorSwitch_common(value){
	var res = "";
	switch (value) {
	case 1:
		res = '黑';
		break;
	case 2:
		res = '白';
		break;
    case 3:
        res = '红';
        break;
    case 4:
        res = '橙';
        break;
    case 5:
        res = '黄';
        break;
    case 6:
        res = '绿';
        break;
    case 7:
        res = '青';
        break;
    case 8:
        res = '蓝';
        break;
    case 9:
        res = '紫';
        break;
   default:
        res = '无';
}
	return res;
}

/**
 * 添加模型
 * @param dataPois 数据
 * @param  path 模型地址
 * @return 添加的对象
 */
function addModel_common(dataPois,path){
	var elements = [];
	if(null == tgisServer){
		return;
	}
	 for (var i = 0; i < dataPois.length; i++) {
		var config={
			id:tgisServer.createGuid(),
			name:dataPois[0].name,
			longitude:dataPois[i].longitude,  
			latitude:dataPois[i].latitude,
			altitude:774.6779799945216,
			spiny:-45,//Y方向的旋转值
			modelpath:path
		};
		var model = tgisServer.Earth.createModel(config);
		/*tgisServer.Event.AddModelClickEvent(model,function(pval){
			 callback(pval,dataPois[i]);
		 });*/
		elements.push(config);
	 }
	 return elements;
}

/**
 * 如果小于10补0
 */
function addZero(time){
	if(time<10){
		return "0"+time;
	}
	return time;
}

/**
 * 格式化日期对象
 */
function formatDate(date,type){
	var year = date.year + 1900;
   	var month = date.month + 1;
   	var day = date.date;
   	var hour = date.hours;
   	var minute = date.minutes;
   	var second = date.seconds;
   	var result;
   	if(type == "chinese"){
   		result = year + "年" + addZero(month) + "月" + addZero(day) + "日 " + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
   	}else if(type == "-"){
   		result = year + "-" + addZero(month) + "-" + addZero(day) + " " + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
   	}
   	return result;
}
