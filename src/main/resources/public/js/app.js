var app = angular.module('app', ['ngAnimate', 'ngAria', 'ngMessages', 'ui.router', 'ngMaterial']);

angular.module('app').controller('AppCtrl', function($scope, $mdDialog) {
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
});