angular.module('app')
	.controller('ConfirmOrderCtrl', function($scope, $stateParams, $http) {
		$scope.name = 'Confirm Order';
		
		$scope.deliveryAddress = 'One thing to notice is the value in the access attribute of the <intercept-url> elements. Those have to be rewritten to be valid expressions. Spring Security supports a couple of expressions out of the box (see Table 7-7 for a list). Using constructs like and, or, and not one can create very powerful and flexible expressions.';
		
		var orderId = $stateParams.id;
		
		$scope.order = {};
		
		$http.get('/orders/' + orderId)
			.then(function(res) {
				$scope.order = res.data;
			}).catch(function(err) {
			});
	});