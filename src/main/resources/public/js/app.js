var app = angular.module('app', ['ngAnimate', 'ngAria', 'ngMessages', 'ui.router', 'ngMaterial', 'md.data.table']);

angular.module('app')
	.config(function($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/');
		
		$stateProvider
			.state('home', {
				url: '/',
				templateUrl: 'views/home/index.html',
				controller: 'HomeCtrl'
			});
	});

angular.module('app').controller('HomeCtrl', function($scope, $mdDialog) {
	$scope.name = 'Piyush Chauhan';
	$scope.openMenu = function($mdOpenMenu, ev) {
    originatorEv = ev;
    $mdOpenMenu(ev);
  };
  $scope.notificationsEnabled = true;
  $scope.toggleNotifications = function() {
    $scope.notificationsEnabled = !$scope.notificationsEnabled;
    
  };
  $scope.redial = function() {
    $mdDialog.show(
      $mdDialog.alert()
        .targetEvent(originatorEv)
        .clickOutsideToClose(true)
        .parent('body')
        .title('Suddenly, a redial')
        .textContent('You just called a friend; who told you the most amazing story. Have a cookie!')
        .ok('That was easy')
    );
    originatorEv = null;
  };
  $scope.checkVoicemail = function() {
    // This never happens.
  };
  
  $scope.selected = [];
  
  $scope.desserts = [{
	  name: 'First'
  }, { name: 'Second' }];

});