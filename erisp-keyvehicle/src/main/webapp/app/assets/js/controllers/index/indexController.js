/**
 * 跳转
 */
app.controller('indexController', ['$scope','$location','$state','$modal','SweetAlert','$document',
  function($scope,$location, $state, $modal,SweetAlert,$document){
	$state.go('app.map.cargoMap');
}]);
