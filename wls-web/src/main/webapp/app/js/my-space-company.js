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
	  
    function delcfm() {
	        if (!confirm("确认要删除？")) {
	            return false;
	        }
	        return true;
	}
	$scope.saveInfo = function() {
		if($scope.user.sex=="男"){
			$scope.sex = 0;
		}
		else{
			$scope.sex = 1;
		}
	    	$http.get( "/i/user/updateUser",{
	    		params : {
	    			nickname : $scope.user.nickname,
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
        	   window.location.reload();
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
	    		//alert("删除成功");
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
	    		$("#quick_reply_alter_com").modal("show");
	    		$scope.findMessageByReceiverId();
		   });
	   };
	   $scope.goUserSpace = function(userID) {
		   $http.get('/i/user/findUserByID', {
	            params: {
	                "spaceUserID": userID
	            }
	        }).success(function(data){
				 $scope.user = data;
				 if($scope.user.suproleid==1){
					 $state.go('my-space-ask', {"spaceID": userID});
				 }
				 else if($scope.user.suproleid==2){
					 $state.go('my-space-company-ask', {"spaceID": userID});
				 }
				 else{
					 alert("用户不存在！！");
				 }
   	 });
		};
	    
		 $scope.responseMessage = function(messageID){
	            $("#reply_area-"+messageID).css("display","");
	    };
});
