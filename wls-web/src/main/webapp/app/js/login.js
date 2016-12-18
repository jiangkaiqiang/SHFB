wlsWeb.controller('login',function($http, $location, $scope) {
   $scope.login = function() {
	   if($scope.username!=""&&
			   $scope.password!=""&&
			   $scope.username!=undefined&&
			   $scope.password!=undefined){
		   $http.get("/i/user/login",{
 	    		params : {
 	    			userName : $scope.username,
 	    			password : $scope.password
 	    			}
 	    	}).success(function(data) {
 	    		if(data.success){
 	    			alert("登录成功");
 	    			window.location.href="#/home";
 	    		}
 	    		else{
 	    			alert("用户名或密码错误");
 	    		}
 		   });
	   }
	   else{
		   alert("请输入用户名和密码");
	   }
  };
});
