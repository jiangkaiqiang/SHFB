var wlsWeb = angular.module('WlsWeb', ['ui.router',
     'xeditable',  'ngFileUpload']);
angular.element(document).ready(function ( $http, $rootScope) {
	angular.bootstrap(document, ['WlsWeb']);
});
wlsWeb.run(function (editableOptions, userService, $location) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
    $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
      	user = data;
  		userService.setUser(user);
      });
});

wlsWeb.factory('userService',['$rootScope','$http', function($rootScope,$http){
	return {
		setUser: function(user){
	    	$rootScope.user = user;
	    	$rootScope.logout = function () {
	        	$http.get('/i/user/logout').success(function(data){
	        		$rootScope.user = null;
	            });
	        	window.location.href="#/home";
	        };
	        $rootScope.goSpace = function() {
	        	if($rootScope.user.id==undefined){
	        		alert("请先登录");
	        	}
	        	else{
	        		if($rootScope.user.suproleid==1){
	        			window.location.href="#/my-space";
	        		}
	        		else{
	        			window.location.href="#/my-space-company";
	        		}
	        	}
	        };
	    },
	};
}]);

JS.Engine.start('conn');
JS.Engine.on(
        { 
           msgData : function(msgData){
        	   $(".msgPush").show();
        	   setTimeout(function(){$(".msgPush").hide();}, 3000);
           },
       }
   );

wlsWeb.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/home");
    
    //index
    $stateProvider.state('home', {
        url: '/home',
        controller: 'home',
        templateUrl: '../../home.html'
    }).state('geek', {
        url: '/geek',
        controller: 'geek',
        templateUrl: 'app/template/geek.html'
    }).state('my-space', {
        url: '/my-space',
        controller: 'my-space',
        templateUrl: 'app/template/my-space.html'
    }).state('my-space-company', {
        url: '/my-space-company',
        controller: 'my-space-company',
        templateUrl: 'app/template/my-space-company.html'
    }).state('register', {
        url: '/register',
        controller: 'register',
        templateUrl: '../../register.html'
    }).state('login', {
        url: '/login',
        controller: 'login',
        templateUrl: '../../login.html'
    }).state('post-bar', {
        url: '/post-bar',
        controller: 'post-bar',
        templateUrl: 'app/template/post-bar.html'
    }).state('news', {
        url: '/news',
        controller: 'news',
        templateUrl: 'app/template/news.html'
    }).state('post-message', {
        url: '/post-message',
        controller: 'post-message',
        templateUrl: 'app/template/post-message.html'
    }).state('news-info', {
        url: '/news-info/:newID',
        controller: 'news-info',
        templateUrl: 'app/template/news-info.html'
    }).state('blog-info', {
        url: '/blog-info/:publishID',
        controller: 'blog-info',
        templateUrl: 'app/template/blog-info.html'
    }).state('my-space-ask', {
        url: '/my-space-ask/:spaceID',
        controller: 'my-space-ask',
        templateUrl: 'app/template/my-space-ask.html'
    }).state('my-space-company-ask', {
        url: '/my-space-company-ask/:spaceID',
        controller: 'my-space-company-ask',
        templateUrl: 'app/template/my-space-company-ask.html'
    });
});