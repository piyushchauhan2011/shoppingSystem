angular.module('app')
	.controller('OrdersStatusCtrl', function($scope, $http) {
		$scope.orders = [];
		  
		  $http.get('/orders')
		  	.then(function(res) {
		  		$scope.orders = res.data;
		  	}).catch(function(err) {
		  	});
	});