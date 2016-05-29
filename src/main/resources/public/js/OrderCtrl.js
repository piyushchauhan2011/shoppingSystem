angular.module('app')
	.controller('OrderCtrl', function($scope, $stateParams) {
		console.log('order id: ', $stateParams.id);
	});