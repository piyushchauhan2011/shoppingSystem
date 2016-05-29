angular.module('app').controller('HomeCtrl', function($scope, $http) { 
  $scope.cart = {};
  $scope.products = [];
  
  $http.get('/products')
  	.then(function(res) {
  		$scope.products = res.data;
  	}).catch(function(err) {
  	});
  
  $http.get('/carts/1')
  	.then(function(res) {
  		$scope.cart = res.data;
  		console.log('cart: ', $scope.cart);
  	}).catch(function(err) {
  	})

});