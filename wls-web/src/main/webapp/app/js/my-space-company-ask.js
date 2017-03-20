wlsWeb.controller('my-space-company-ask',function($http, $location, $scope,$state, $stateParams,$rootScope) {
    $scope.load = function(){
    	 $scope.spaceID = $stateParams.spaceID;
	    	$http.get('/i/user/findUserByID', {
	            params: {
	                "spaceUserID": $scope.spaceID
	            }
	        }).success(function(data){
			if(data!=null&&data.id!=undefined){
				 $scope.user = data;
				 $http.get('/i/user/askFollowFlag', {
			            params: {
			            	"askUserID" :$rootScope.user.id,
			                "spaceUserID": $scope.spaceID
			            }
			        }).success(function (data) {  
			        	$scope.askFollowFlag = data;
			     });
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
					 $scope.publishNum = 0;
					 $http.get('/i/publish/findPublishByUserId', {
				            params: {
				                "userID": $scope.user.id
				            }
				        }).success(function (data) { 
				        	if(data!=null){
				        	$scope.publishs = data;
				        	$scope.publishNum = $scope.publishs.length;
				        	}
				     });
					$http.get('/i/user/findFollowerByUserId', {
			            params: {
			                "userID": $scope.user.id
			            }
			        }).success(function (data) { 
			        	$scope.followers = data;
			        	$scope.followerNum = $scope.followers.length;
			     });
			}
    	 });
	   };
	   $scope.load();
	   
	   $scope.attention = function(){
			$http.get('/i/user/addFollow', {
	            params: {
	            	"askUserID" :$rootScope.user.id,
	                "spaceUserID": $scope.spaceID
	            }
	        }).success(function (data) {  
	        	if(data.success){
	        		$scope.askFollowFlag = true;
	        		/*$("#attention_me").modal("show");*/
	        	}
	        	else{
	        		alert(data.message);
	        	}
	     });
		};
		
		$scope.unattention = function(){
			$http.get('/i/user/deleteFollow', {
	            params: {
	            	"askUserID" :$rootScope.user.id,
	                "spaceUserID": $scope.spaceID
	            }
	        }).success(function (data) {  
	        	if(data.success){
	        		$scope.askFollowFlag = false;
	        		/*$("#unattention_me").modal("show");*/
	        	}
	        	else{
	        		alert(data.message);
	        	}
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
		
		$scope.sendMessage = function(){
	   		if($rootScope.user==null||$rootScope.user.id==undefined){
	   			alert("请先登录");
	   			return;
	   		}
			$http.get('/i/message/addMessage', {
	            params: {
	            	"messageSenderID" :$rootScope.user.id,
	                "messageReceiverID": $scope.spaceID,
	                "msgcategory" : 1,
	                "messageContent" : $scope.messageContent
	            }
	        }).success(function (data) {  
	        	if(data.success){
	        		alert(data.message);
	        		$state.reload();
	        	}
	        	else{
	        		alert(data.message);
	        		$state.reload();
	        	}
	     });
		};
		 $scope.goBlogInfo = function(publishID) {
	      	 $state.go('blog-info', {"publishID": publishID});
	   	};  
});
