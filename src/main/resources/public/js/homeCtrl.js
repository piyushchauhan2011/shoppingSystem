angular.module('app').controller('HomeCtrl', function($scope, $http, CartService, $state, $mdDialog) {
  $scope.products = [];
  
  $http.get('/products')
  	.then(function(res) {
  		$scope.products = res.data;
  	}).catch(function(err) {
  	});
  
  $http.post('/carts')
	.then(function(res) {
		$scope.cart = res.data;
	}).catch(function(err) {});
  
  $scope.addProduct = function(product) {
	  $http.put('/carts/' + $scope.cart.id + '/products', product)
	  	.then(function(res) {
	  		$scope.cart = res.data;
	  	});
  };
  
  $scope.removeProduct = function(cartItem) {
	  $http.delete('/carts/' + $scope.cart.id + '/products/' + cartItem.id)
	  	.then(function(res) {
	  		$scope.cart = res.data;
	  	});
  }
  
  $scope.checkout = function() {
	  $http.get('/carts/' + $scope.cart.id + '/checkout')
	  	.then(function(res) {
	  		if(res.data.success) $state.go('confirmOrder', {id: $scope.cart.id});
	  		else {
	  			$mdDialog.show(
	  				$mdDialog.alert()
	  			        .title('Error')
	  			        .textContent('Low inventory')
	  			        .ok('OK')
	  			);
	  		}
	  	});
  }

});

angular.module('app').service('CartService', function($q, $http) {
	var cart;
	this.getCart = function() {
		var deferred = $q.defer();
		
		if(cart) {
			return deferred.resolve(cart);
		} else {
			$http.post('/carts')
		  	.then(function(res) {
		  		cart = res.data;
		  		deferred.resolve(cart);
		  	}).catch(function(err) {});
		}
		
		return deferred.promise;
	}
});