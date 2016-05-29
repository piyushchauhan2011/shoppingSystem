angular.module('app').controller('AdminCtrl', function($scope, $http) {
	$scope.name = 'Admin';
	
	$scope.orders = [];
	  
	  $http.get('/orders')
	  	.then(function(res) {
	  		$scope.orders = res.data;
	  	}).catch(function(err) {
	  	});

});