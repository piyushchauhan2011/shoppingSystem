angular.module('app')
	.controller('LoginCtrl', function($scope, $http) {
		$scope.login = function() {
			console.log('logged in');
		};
	});