coldWeb.controller('personalSpace', function ($rootScope, $scope, $state, $cookies, $http, $location) {
	 $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
		   $rootScope.admin = data;
			if($rootScope.admin == null || $rootScope.admin.user_id == 0 || admin.user_id==undefined){
				url = "http://" + $location.host() + ":" + $location.port() + "/login.html";
				window.location.href = url;
			}
	   });
});
