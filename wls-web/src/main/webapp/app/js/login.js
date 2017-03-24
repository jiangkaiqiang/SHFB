wlsWeb.controller('login',function($http, $location, $scope,$state,  $rootScope) {
   if($rootScope.user!=undefined&&$rootScope.user.id!=undefined){
	   if($rootScope.user.suproleid==1){
		   window.location.href = "#/my-space";
   			}
   			else{
   				window.location.href ="#/my-space-company";
   			}
   }
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
 	    			 $http.get('/i/user/findUser').success(function (data) {
 	    				$rootScope.user = data;
 	    				if($rootScope.user.suproleid==1){
 	    					window.location.href = "#/my-space";
 	  	   			}
 	  	   			else{
 	  	   			window.location.href ="#/my-space-company";
 	  	   			}
 	    			});
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
  $scope.sendPwdVerifyCode = function() {
	  if($scope.telephonenum==undefined){ 
    	  alert("请先输入手机号");
    	  return;
      }
	  $http.get( "/i/user/userTelephoneVerify",{
	  		params : {telephone : $scope.telephonenum}
	  	   }).success(function(data) {
	  		   if(!data.success){
	  		   resetCode();
	           $http.get( "/i/user/sendPwdVerifyCode",{
	    		params : {telephonenum : $scope.telephonenum}
	               	}).success(function(data) {
				         alert(data.message);
		            });
	  		  }
	  		   else{
	  				alert(data.message);
	  		   }
		   });
 };
 $scope.pwdCodeVerify = function() {
	    	$http.get( "/i/user/pwdCodeVerify",{
	    		params : {pwdverifycode : $scope.pwdverifycode}
	    	}).success(function(data) {
				if(data.success){
					 jQuery('.login-form').hide();
			         jQuery('.reset-form').show();
				     jQuery('.forget-form').hide();
				}
				else{
					alert(data.message);
				}
		   });
};

$scope.changePwd = function() {
	if($scope.changepwd1==undefined||$scope.changepwd2==undefined||
			$scope.changepwd1==''||$scope.changepwd2==''){
		alert("密码不能为空");
		return;
	}
	if($scope.changepwd1!=$scope.changepwd2){
		alert("两次密码不一致");
		return;
	}
	$http.get( "/i/user/updateUserPwd",{
		params : {changepwd : $scope.changepwd1,
			telephone : $scope.telephonenum
			}
	}).success(function(data) {
		if(data.success){
			alert(data.message);
			$state.reload();
		}
		else{
			alert(data.message);
		}
   });
};

function resetCode(){
    $('#J_getCode').hide();
    $('#J_second').html('60');
    $('#J_resetCode').show();
    var second = 60;
    var timer = null;
    timer = setInterval(function(){
        second -= 1;
        if(second >0 ){
            $('#J_second').html(second);
        }else{
            clearInterval(timer);
            $('#J_getCode').show();
            $('#J_resetCode').hide();
        }
    },1000);
}
});
