angular.module('app')
	.controller('OrderCtrl', function($scope, $stateParams, $http) {
		$scope.orderId = $stateParams.id;
		
		$http.get('/orders/' + $scope.orderId)
			.then(function(res) {
				$scope.order = res.data;
				
				$scope.packag = {
					    orderId: $scope.order.id,
					    deliveryAddress: $scope.order.deliveryAddress
					};
				
				$http.post('http://localhost:9080/packages', $scope.packag)
				.then(function(res) {
					$scope.packag = res.data;
				});
			});
		
		$scope.addPackageItem = function(cartItem) {
			var item = {
				title: cartItem.product.title,
				description: cartItem.product.description,
				quantity: cartItem.quantity,
				itemId: cartItem.id
			};
			$http.post('http://localhost:9080/packages/' + $scope.packag.id + '/item', item)
				.then(function(res) {
					$scope.packag = res.data;
				});
		}
		
		$scope.removePackageItem = function(packageItem) {
			$http.delete('http://localhost:9080/packages/' + $scope.packag.id + '/item/' + packageItem.id)
			  	.then(function(res) {
			  		$scope.packag = res.data;
			  	});
		}
		
	});