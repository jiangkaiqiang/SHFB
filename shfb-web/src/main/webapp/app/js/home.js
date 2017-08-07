coldWeb.controller('home', function ($rootScope, $scope, $state, $cookies, $http, $location) {
	 $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
	      	var user = data;
	      	if(user != null && user.user_id != 0 && user.user_id!=undefined){
	        $.get("/i/userrole/findUserRoleByUserID", {userID: user.user_id},function(data){
	        	if(data!=null&&data.userRole.user_role_id!=undefined){
   				var adminRoleDto = data;
			    	if(adminRoleDto.overView){
			 		   window.location.href = "#/home";
			 	}
			 	else{
			 		if(adminRoleDto.compManage || adminRoleDto.processManage){
			 			window.location.href = "#/componentManage";
			 		}
			 		else{
			 			if(adminRoleDto.projectManage){
			 				window.location.href = "#/projectManage";
			 			}
			 			else{
			 				if(adminRoleDto.compFactoryManage){
			 					window.location.href = "#/compFactoryManage";
			 				}
			 				else{
			 					 if(adminRoleDto.userManage || adminRoleDto.roleManage || adminRoleDto.logManage){
			 						window.location.href = "#/userManage";
			 					 }
			 					 else{
			 						 if(adminRoleDto.productManage){
			 							window.location.href = "#/productManage";
			 						 }
			 						 else{
			 							window.location.href ="#/personalSpace";
			 						 }
			 					 }
			 				}
			 			}
			 		}
			 	}
			    }
	      	 });
	      	}
	      });
	 
	 $scope.goHome=function(){
		 $state.reload();
	 }
	 
	 /*$scope.setIframeHeight=function(iframe) {
			if (iframe) {
			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
			if (iframeWin.document.body) {
			iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
			}
			}
			};

			
			$scope.setIframeHeight(document.getElementById('external-frame'));*/
			
});
