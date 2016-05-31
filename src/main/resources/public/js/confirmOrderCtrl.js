angular.module('app')
	.controller('ConfirmOrderCtrl', function($scope, $stateParams, $http) {
		$scope.name = 'Confirm Order';
		var cartId = $stateParams.id;
		
		$scope.order = {};
		
		$http.get('/carts/' + cartId + '/confirm')
			.then(function(res) {
				$scope.order = res.data;
			}).catch(function(err) {
			});
	});