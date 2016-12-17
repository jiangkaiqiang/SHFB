wlsWeb.controller('register',function($http, $location, $scope) {
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
      if($scope.telephone!=undefined){
    	$http.get( "/i/user/sendSignUpCode",{
    		params : {telephone : $scope.telephone}
    	}).success(function(data) {
			alert(data.message);
	   });
       }
    	else{
    		alert("请先输入手机号");
    	}
   };
   $scope.register = function() {
	   if( $scope.username==undefined&&$scope.telephone!=undefined){
		   $http.get( "/i/user/verifySignUpCode",{
 	    		params : {telephone : $scope.telephone}
 	    	}).success(function(data) {
 	    		if(data.success){
 	    			
 	    		}
 	    		alert(data.message);
 		   });
	   }
	   else if( $scope.username!=undefined&&$scope.email!=undefined&&$scope.password!=undefined){
  			$http.get( "/i/user/signup",{
  				params : {
  					username : $scope.username,
  					password :  $scope.password,
  					rpassword : $scope.rpassword,
  	    			telephone : $scope.telephone,
  	    			email : $scope.email,
  	    			suproleid : $scope.suproleid
  	    			}
  	    	}).success(function(data) {
  	    		alert(data.message);
  		   });
  	   }
	   $('#form_wizard_1').find('.button-previous').show();
	   $('#form_wizard_1').find('.button-next2').show();
	   $('#form_wizard_1').find('.button-next').hide();
	   $('#form_wizard_1').find('#tab2').addClass("active");
       $('#form_wizard_1').find('#tab1').removeClass("active");
	   $('#form_wizard_1').find('#bar div').css("width","66.67%");
  };
  $scope.register2 = function() {
	  $('#form_wizard_1').find('.button-previous').hide();
	  $('#form_wizard_1').find('.button-previous2').show();
	  $('#form_wizard_1').find('.button-submit').show();
	  $('#form_wizard_1').find('.button-next2').hide();
	  $('#form_wizard_1').find('#tab3').addClass("active");
      $('#form_wizard_1').find('#tab2').removeClass("active");
	  $('#form_wizard_1').find('#bar div').css("width","100%"); 
  }
  $scope.back = function() {
	  $('#form_wizard_1').find('.button-previous').hide();
	  $('#form_wizard_1').find('.button-previous2').hide();
	  $('#form_wizard_1').find('.button-submit').hide();
	  $('#form_wizard_1').find('.button-next2').hide();
	  $('#form_wizard_1').find('.button-next').show();
	  $('#form_wizard_1').find('#tab1').addClass("active");
      $('#form_wizard_1').find('#tab2').removeClass("active");
	  $('#form_wizard_1').find('#bar div').css("width","33.33%");  
  }
  $scope.back2 = function() {
	  $('#form_wizard_1').find('.button-previous2').hide();
	  $('#form_wizard_1').find('.button-previous').show();
	  $('#form_wizard_1').find('.button-submit').hide();
	  $('#form_wizard_1').find('.button-next2').show();
	  $('#form_wizard_1').find('.button-next').hide();
	  $('#form_wizard_1').find('#tab2').addClass("active");
      $('#form_wizard_1').find('#tab3').removeClass("active");
	  $('#form_wizard_1').find('#bar div').css("width","66.67%");  
  }

});
