angular.module('app')
	.component('headerComponent', {
		templateUrl: '/views/shared/header.html',
		controller: 'HeaderCtrl'
	});

angular.module('app')
	.controller('HeaderCtrl', function($scope, $mdDialog, $state) {
		$scope.name = 'Piyush Chauhan';
		$scope.openMenu = function($mdOpenMenu, ev) {
	    originatorEv = ev;
	    $mdOpenMenu(ev);
	  };
	  $scope.notificationsEnabled = true;
	  $scope.logout = function() {
	    $state.go('login');
	    $scope.name = 'Guest';
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
	});