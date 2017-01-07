wlsWeb.controller('register',function($http, $location, $scope,$rootScope) {
	$scope.suproleid = 1;
	$scope.company_gate = function() {
       $scope.suproleid = 2;
	   $('#company_gate div').addClass("selected");
       $('#geek_gate div').removeClass("selected");
    };
    $scope.geek_gate = function() {
    	 $scope.suproleid = 1;  
		 $('#geek_gate div').addClass("selected");
         $('#company_gate div').removeClass("selected");
    };
    $scope.getVerCode = function() {
      if($scope.telephone==undefined){ 
    	  alert("手机号不能为空");
    	  return;
      }
      $http.get( "/i/user/userTelephoneVerify",{
  		params : {telephone : $scope.telephone}
  	   }).success(function(data) {
  		   if(data.success){
  			 resetCode();
  			 $http.get( "/i/user/sendSignUpCode",{
  	    		params : {telephone : $scope.telephone}
  	    	  }).success(function(data) {
  				alert(data.message);
  		      });
  		   }
  		   else{
  				alert(data.message);
  		   }
	   });
   };
   $scope.register = function() {
	   $('#form_wizard_1').find('.button-previous').show();
	   $('#form_wizard_1').find('.button-next2').show();
	   $('#form_wizard_1').find('.button-next').hide();
	   $('#form_wizard_1').find('#tab2').addClass("active");
       $('#form_wizard_1').find('#tab1').removeClass("active");
	   $('#form_wizard_1').find('#bar div').css("width","66.67%");
  };
  $scope.register2 = function() {
	  if($scope.username==null||$scope.verCode==null||
			  /*$scope.email==null||*/
			  $scope.password==null||$scope.rpassword==null){
		  alert("所填信息不能为空！");
		  return;
	  }
	  if($scope.password!=$scope.rpassword){
		  alert("两次密码不一致！");
		  return;
	  }
	  
	  $http.get( "/i/user/existenceUserName",{
  		params : {userName : $scope.username}
  	  }).success(function(data) {
  		  if(data.success){ 
	         $http.get( "/i/user/verifySignUpCode",{
	    		params : {signUpCode : $scope.verCode}
	        	}).success(function(data) {
	    	    	if(data.success){
	    			$http.get( "/i/user/signup",{
	     				params : {
	     					username : $scope.username,
	     					password :  $scope.password,
	     					rpassword : $scope.rpassword,
	     	    			telephone : $scope.telephone,
	     	    			email : "",
	     	    			suproleid : $scope.suproleid,
	     	    			avatar : "../../assets/img/people/"+Math.round(Math.random()*9+6)+".jpg"
	     	    			}
	     	    	}).success(function(data) {
	     	    		if(data.success){
	     	    			 $('#form_wizard_1').find('.button-previous').hide();
	     	    			  $('#form_wizard_1').find('.button-previous2').show();
	     	    			  $('#form_wizard_1').find('.button-submit').show();
	     	    			  $('#form_wizard_1').find('.button-next2').hide();
	     	    			  $('#form_wizard_1').find('#tab3').addClass("active");
	     	    		      $('#form_wizard_1').find('#tab2').removeClass("active");
	     	    			  $('#form_wizard_1').find('#bar div').css("width","100%"); 
	     	    		}
	     	    		else{
	     	    			alert("注册失败");
	     	    		}
	     		   });
	    		}
	    		else{
	    			alert(data.message);
	    		}
		   });
  		}
		else{
			alert(data.message);
		}
  	   });
	};
  
  $scope.loginGoSpace = function() {
	  $http.get("/i/user/login",{
   		params : {
   			userName : $scope.username,
   			password : $scope.password
   			}
   	}).success(function(data) {
   		if(data.success){
   		   $http.get('/i/user/findUser').success(function (data) {
				$rootScope.user = data;
			});
   			if($scope.suproleid==1){
    			 window.location.href="#/my-space";
    			  
   			}
   			else{
   			    window.location.href="#/my-space-company";
   			}
   		}
	   });
  };
  
  $scope.back = function() {
	  $('#form_wizard_1').find('.button-previous').hide();
	  $('#form_wizard_1').find('.button-previous2').hide();
	  $('#form_wizard_1').find('.button-submit').hide();
	  $('#form_wizard_1').find('.button-next2').hide();
	  $('#form_wizard_1').find('.button-next').show();
	  $('#form_wizard_1').find('#tab1').addClass("active");
      $('#form_wizard_1').find('#tab2').removeClass("active");
	  $('#form_wizard_1').find('#bar div').css("width","33.33%");  
  };
  $scope.back2 = function() {
	  $('#form_wizard_1').find('.button-previous2').hide();
	  $('#form_wizard_1').find('.button-previous').show();
	  $('#form_wizard_1').find('.button-submit').hide();
	  $('#form_wizard_1').find('.button-next2').show();
	  $('#form_wizard_1').find('.button-next').hide();
	  $('#form_wizard_1').find('#tab2').addClass("active");
      $('#form_wizard_1').find('#tab3').removeClass("active");
	  $('#form_wizard_1').find('#bar div').css("width","66.67%");  
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
