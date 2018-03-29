/**
 * 全局方法服务
 */
app.factory("globalMethodsService",["$rootScope","$log",
    function($rootScope,$log){
    return {
        /**
         * 自动生成资产编码生成
         * 规则：行政编码(6位)+资产类型(2位)+随机数(6位)
         */
        generateAssetNumber: function (areaCode,type) {
            var randomCode = Math.random()*1000000;
            while(randomCode < 100000){
                randomCode = Math.random()*1000000;
            }
            return areaCode + type + parseInt(randomCode);
        }
    }
}])