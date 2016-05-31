angular.module('app')
	.controller('ConfirmOrderCtrl', function($scope, $stateParams, $http, $state) {
		$scope.name = 'Confirm Order';
		var cartId = $stateParams.id;
		
		$http.get('/carts/' + cartId)
			.then(function(res) {
				$scope.cart = res.data;
			}).catch(function(err) {
			});
		
		$scope.confirmOrder = function() {
			$http.get('/carts/' + cartId + '/confirm')
				.then(function(res) {
					$state.go('ordersStatus');
				});
		}
	});