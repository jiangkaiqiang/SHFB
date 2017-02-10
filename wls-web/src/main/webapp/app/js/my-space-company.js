wlsWeb.controller('my-space-company',function($http, $location,$rootScope, $scope, $state,Upload) {
    $scope.load = function(){
    	$http.get('/i/user/findUser').success(function (data) {
			if(data!=null&&data.id!=undefined){
				 $scope.user = data;
				 if($scope.user.sex==0){
						$scope.user.sex = "男";
					}
					else{
						$scope.user.sex = "女";
					}
					if($scope.user.score<100){
						$scope.level = 1;
					}
					else if($scope.user.score<500&&$scope.user.score>=100){
						$scope.level = 2;
					}
					else if($scope.user.score>=500){
						$scope.level = 3;
					}
					else{
						$scope.level = 1;
					}
					$scope.findMessageByReceiverId();
					$http.get('/i/user/findFollowerByUserId', {
			            params: {
			                "userID": $scope.user.id
			            }
			        }).success(function (data) { 
			        	$scope.followers = data;
			        	$scope.followerNum = $scope.followers.length;
			     });
				 }
				else{
					alert("请先登录");
					window.location.href="#/login";
				}
    	 });
	   };
	$scope.load();
	$scope.findMessageByReceiverId = function(){
		 $http.get('/i/message/findMessageByReceiverId', {
	            params: {
	                "userID": $scope.user.id
	            }
	        }).success(function (data) { 
	        	$scope.messages = data;
	        	$scope.messageNum = $scope.messages.length;
	     });
	 };
	$scope.logout = function() {
		   $http.get( "/i/user/logout").success(function(data){
			   window.location.reload();
		   });
	  };
	$scope.saveInfo = function() {
		if($scope.user.sex=="男"){
			$scope.sex = 0;
		}
		else{
			$scope.sex = 1;
		}
	    	$http.get( "/i/user/updateUser",{
	    		params : {
	    			realname : $scope.user.realname,
	    			telephone : $scope.user.telephone,
	    			position: $scope.user.position,
	    			company : $scope.user.company,
	    			address : $scope.user.address,
	    			phone : $scope.user.phone,
	    			email : $scope.user.email,
	    			signature : $scope.user.signature,
	    			sex : $scope.sex
	    		}
	    	}).success(function(data) {
	    		if(data.success){
	    			alert("信息修改成功");
	    			$state.reload();
	    		}
	    		else{
	    			alert("信息修改失败");
	    		}
		   });
	   };
	   
	   $scope.updateAvatar = function() {
		   var useravatar = $('#useravatar').get(0).files[0];
		   if(useravatar==null||useravatar==undefined){
			   alert("请选择图片");
			   return;
		   }
		   data = {
				   useravatar: useravatar
	            };
		   Upload.upload({
               url: '/i/user/updateAvatar',
               headers :{ 'Content-Transfer-Encoding': 'utf-8' },
               data: data
           }).then(function (resp) {
    		   $state.reload();
           });
       };
       
       
       $scope.updatePhoto = function() {
		   var userphoto = $('#userphoto').get(0).files[0];
		   if(userphoto==null||userphoto==undefined){
			   alert("请选择图片");
			   return;
		   }
		   data = {
				   userphoto: userphoto
	            };
		   Upload.upload({
               url: '/i/user/updatePhoto',
               headers :{ 'Content-Transfer-Encoding': 'utf-8' },
               data: data
           }).then(function (resp) {
        	   $state.reload();
           });
       };
	   
	   $scope.deleteMessage  = function(messageID) {
		   if(delcfm()){
	    	$http.get( "/i/message/deleteMessage",{
	    		params : {
	    			msgID : messageID,
	    			messageSenderID : $scope.user.id
	    			}
	    	}).success(function(data) {
	    		alert("删除成功");
	    		$scope.findMessageByReceiverId();
		   });
		   }
	   };
	   
	   $scope.messageResponse  = function(messageID,messageReceiverID,msgcategory
			   ,resopnseMsg) {
	    	$http.get( "/i/message/messageResponse",{
	    		params : {
	    			messageID : messageID,
	    			messageReceiverID : messageReceiverID,
	    			messageResponse : resopnseMsg,
	    			msgcategory : msgcategory,
	    			messageSenderID : $scope.user.id
	    		}
	    	}).success(function(data) {
	    		alert("回复成功");
	    		$scope.findMessageByReceiverId();
		   });
	   };
	   $scope.goUserSpace = function(userID) {
	    	 $state.go('my-space-ask', {"spaceID": userID});
		};
	    
		 $scope.responseMessage = function(messageID){
	            $("#reply_area-"+messageID).css("display","");
	    };
});
