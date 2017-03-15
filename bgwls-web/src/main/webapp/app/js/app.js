var coldWeb = angular.module('ColdWeb', ['ui.bootstrap', 'ui.router', 'ui.checkbox',
    'ngCookies', 'xeditable', 'isteven-multi-select', 'angucomplete', 'angular-table','ngFileUpload','remoteValidation']);
var weburl = "http://localhost:8989";
angular.element(document).ready(function ($ngCookies, $http, $rootScope) {
	angular.bootstrap(document, ['ColdWeb']);
});
coldWeb.run(function (editableOptions, adminService, $location,$http) {
	$http.defaults.withCredentials = true;
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
    /*$.ajax({type: "GET",cache: false,dataType: 'json',url: weburl+'/i/admin/findAdmin'}).success(function(data){
      	admin = data.entity;*/
        window.localStorage.weburl = "http://localhost:8989";
        admin = window.localStorage.lkuser;
      	if(admin == null || admin==''||admin==undefined){
  			url = "login.html";
  			window.location.href = url;
  		}
  		adminService.setAdmin(admin);
      /*});*/
});


coldWeb.factory('adminService',['$rootScope','$http', function($rootScope,$http){
	return {
		setAdmin: function(admin){
	    	$rootScope.admin = admin;
	    	$rootScope.logout = function () {
		        	$http.get(window.localStorage.weburl+'/i/admin/logout').success(function(data){
		            window.localStorage.lkuser ='';
	            });
	        	window.location.reload();
	        };
	    },
	};
}]);


coldWeb.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/home");
    //index
    $stateProvider.state('login', {
        url: '/login',
        controller: 'login',
        templateUrl: 'login.html'
    }).state('home', {
        url: '/home',
        controller: 'home',
        templateUrl: 'app/template/home.html'
    }).state('search', {
        url: '/search',
        controller: 'search',
        templateUrl: 'app/template/search.html'
    }).state('info', {
        url: '/info/{id}',
        controller: 'info',
        templateUrl: 'app/template/info.html'
    }).state('multi-query', {
        url: '/multi-query/{key}',
        controller: 'multi-query',
        templateUrl: 'app/template/multi-query.html'
    }).state('goods-list', {
        url: '/goods-list/{key}',
        controller: 'goods-list',
        templateUrl: 'app/template/goods-list.html'
    }).state('coldStoragelist', {
        url: '/storageManage',
        controller: 'storageManage',
        templateUrl: 'app/template/storageManage.html'
    }).state('commentManage', {
        url: '/commentManage',
        controller: 'commentManage',
        templateUrl: 'app/template/commentManage.html'
    }).state('review', {
        url: '/coldStorage/{rdcID}/review',
        controller: 'review',
        templateUrl: 'app/template/review.html'
    }).state('coldStorageAdd', {
        url: '/coldStorageAdd',
        controller: 'coldStorageAdd',
        templateUrl: 'app/template/coldStorageInfo.html'
    }).state('coldStorageEdit', {
        url: '/coldStorageEdit/:rdcID',
        controller: 'coldStorageEdit',
        templateUrl: 'app/template/editStorage.html'
    }).state('coldStorageMap', {
        url: '/coldStorageMap',
        controller: 'coldStorageMap',
        templateUrl: 'app/template/coldStorageMap.html'
    }).state('adminlist', {
        url: '/adminlist',
        controller: 'adminlist',
        templateUrl: 'app/template/adminManage.html'
    }).state('infoManage', {
        url: '/infoManage',
        controller: 'infoManage',
        templateUrl: 'app/template/infoManage.html'
    }).state('spiderConfig', {
        url: '/spiderConfig',
        controller: 'spiderConfig',
        templateUrl: 'app/template/spiderConfig.html'
    }).state('storageConfig', {
        url: '/storageConfig',
        controller: 'storageConfig',
        templateUrl: 'app/template/storageConfig.html'
    }).state('operatinLog',{
    	url: '/operationLog',
    	controller: 'operationLog',
    	templateUrl: 'app/template/operationLog.html'
    }).state('coldStorageAudit', {
        url: '/coldStorageAudit/:rdcID',
        controller: 'coldStorageAudit',
        templateUrl: 'app/template/editStorage.html'
    }).state('coldStorageHonorAudit', {
        url: '/coldStorageHonorAudit/:rdcId',
        controller: 'coldStorageHonorAudit',
        templateUrl: 'app/template/coldStorageHonor.html'
    }).state('coldStorageAuthAudit', {
        url: '/coldStorageAuthAudit/:rdcId',
        controller: 'coldStorageAuthAudit',
        templateUrl: 'app/template/coldStorageAuth.html'
    }).state('companylist', {
        url: '/companylist',
        controller: 'companylist',
        templateUrl: 'app/template/companyManage.html'
    }).state('storageRelate', {
        url: '/storageRelate/:companyId',
        controller: 'storageRelate',
        templateUrl: 'app/template/storageRelate.html'
    }).state('userRelate', {
        url: '/userRelate/:companyId',
        controller: 'userRelate',
        templateUrl: 'app/template/userRelate.html'
    });
});