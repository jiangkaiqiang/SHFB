var coldWeb = angular.module('ColdWeb', ['ui.bootstrap', 'ui.router', 'ui.checkbox',
    'ngCookies', 'xeditable', 'isteven-multi-select', 'angucomplete', 'angular-table','ngFileUpload','remoteValidation']);
//coldWeb.constant('coldWebUrl', 'http://www.smartcold.org.cn/i/');
angular.element(document).ready(function ($ngCookies, $http, $rootScope) {
	angular.bootstrap(document, ['ColdWeb']);
});
coldWeb.run(function (editableOptions, adminService, $location) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
    $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
      	admin = data;
      	if(admin == null || admin.user_id == 0 || admin.user_id==undefined){
  			url = "http://" + $location.host() + ":" + $location.port() + "/login.html";
  			window.location.href = url;
  		}
  		adminService.setAdmin(admin);
      });
});

coldWeb.factory('adminService',['$rootScope','$http', function($rootScope,$http){
	return {
		setAdmin: function(admin){
	    	$rootScope.admin = admin;
	    	if(admin != null && admin.user_id != 0 && admin.user_id!=undefined){
	    	$http.get('/i/userrole/findUserRoleByUserID', {
	            params: {
	                "userID": $rootScope.admin.user_id
	            }
	        }).success(function(data){
			    if(data!=null&&data.userRole.user_role_id!=undefined){
			    	$rootScope.adminRoleDto = data;
			    }
		     });
	    	}
	    	$rootScope.logout = function () {
	        	$http.get('/i/user/logout').success(function(data){
	        		$rootScope.admin = null;
	            });
	        	window.location.reload();
	        };
	    },
	}
}])

coldWeb.config(function ($stateProvider, $urlRouterProvider) {
	/* $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
	      	var user = data;
	      	if(user != null && user.user_id != 0 && user.user_id!=undefined){
	        $.get("/i/userrole/findUserRoleByUserID", {userID: user.user_id},function(data){
	        	if(data!=null&&data.userRole.user_role_id!=undefined){
      				var adminRoleDto = data;
			    	if(adminRoleDto.overView){
			 		   $urlRouterProvider.otherwise("/home");
			 	}
			 	else{
			 		if(adminRoleDto.compManage || adminRoleDto.processManage){
			 			$urlRouterProvider.otherwise("/componentManage");
			 		}
			 		else{
			 			if(adminRoleDto.projectManage){
			 				$urlRouterProvider.otherwise("/projectManage");
			 			}
			 			else{
			 				if(adminRoleDto.compFactoryManage){
			 				   $urlRouterProvider.otherwise("/compFactoryManage");
			 				}
			 				else{
			 					 if(adminRoleDto.userManage || adminRoleDto.roleManage || adminRoleDto.logManage){
			 						   $urlRouterProvider.otherwise("/userManage");
			 					 }
			 					 else{
			 						 if(adminRoleDto.productManage){
			 							 $urlRouterProvider.otherwise("/productManage");
			 						 }
			 						 else{
			 							 $urlRouterProvider.otherwise("/personalSpace");
			 						 }
			 					 }
			 				}
			 			}
			 		}
			 	}
			    }
	      	 });
	      	}
	      	else{*/
	      		 $urlRouterProvider.otherwise("/home");
	      /*	}
	      });
*/
 
    //index
    $stateProvider.state('home', {
        url: '/home',
        controller: 'home',
        templateUrl: 'app/template/home.html'
    }).state('userManage', {
        url: '/userManage',
        controller: 'userManage',
        templateUrl: 'app/template/userManage.html'
    }).state('projectManage', {
        url: '/projectManage',
        controller: 'projectManage',
        templateUrl: 'app/template/projectManage.html'
    }).state('compFactoryManage', {
        url: '/compFactoryManage',
        controller: 'compFactoryManage',
        templateUrl: 'app/template/compFactoryManage.html'
    }).state('componentManage', {
        url: '/componentManage',
        controller: 'componentManage',
        templateUrl: 'app/template/componentManage.html'
    }).state('operationLog', {
        url: '/operationLog',
        controller: 'operationLog',
        templateUrl: 'app/template/operationLog.html'
    }).state('personalSpace', {
        url: '/personalSpace',
        controller: 'personalSpace',
        templateUrl: 'app/template/personalSpace.html'
    }).state('componentInfo', {
        url: '/componentInfo',
        controller: 'componentInfo',
        templateUrl: 'app/template/componentInfo.html'
    }).state('componentOrder', {
        url: '/componentOrder',
        controller: 'componentOrder',
        templateUrl: 'app/template/componentOrder.html'
    }).state('userRoleManage', {
        url: '/userRoleManage',
        controller: 'userRoleManage',
        templateUrl: 'app/template/userRoleManage.html'
    }).state('productManage', {
        url: '/productManage',
        controller: 'productManage',
        templateUrl: 'app/template/productManage.html'
    });
});