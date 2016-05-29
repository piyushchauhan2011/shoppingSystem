var app = angular.module('app', ['ngAnimate', 'ngAria', 'ngMessages', 'ui.router', 'ngMaterial', 'md.data.table']);

angular.module('app')
	.config(function($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/');
		
		$stateProvider
			.state('home', {
				url: '/',
				templateUrl: 'views/home/index.html',
				controller: 'HomeCtrl'
			})
			.state('login', {
				url: '/login',
				templateUrl: 'views/home/login.html',
				controller: 'LoginCtrl'
			})
			.state('ordersStatus', {
				url: '/orders-status',
				templateUrl: 'views/home/order-status.html',
				controller: 'OrdersStatusCtrl'
			})
			.state('confirmOrder', {
				url: '/order/{id}/confirm-order',
				templateUrl: 'views/home/confirm-order.html',
				controller: 'ConfirmOrderCtrl'
			})
			.state('admin', {
				url: '/admin',
				templateUrl: 'views/admin/index.html',
				controller: 'AdminCtrl'
			})
			.state('adminOrder', {
				url: '/admin/order/{id}',
				templateUrl: 'views/admin/order.html',
				controller: 'OrderCtrl'
			});
	});