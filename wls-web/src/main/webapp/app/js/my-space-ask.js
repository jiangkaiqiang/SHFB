wlsWeb.controller('my-space-ask',function($http, $location, $scope,$state, $stateParams,$rootScope) {
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
					 $http.get('/i/user/askResumeFlag', {
					            params: {
					            	"askUserID" :$rootScope.user.id,
					                "spaceUserID": $scope.spaceID
					            }
					        }).success(function (data) { 
					        	$scope.askResumeFlag = data;
					        	/*$scope.educates = {};
								$scope.skills = {};
								$scope.honors = {};
								$scope.jobs = {};*/
								if($scope.askResumeFlag==true){
									 $http.get('/i/user/findEducateByUserID', {
								            params: {
								                "userID": $scope.user.id
								            }
								        }).success(function (data) { 
								        	if(data.success){
								        		$scope.educates = data.data;
								        	}
								     });
									 $http.get('/i/user/findSkillByUserID', {
								            params: {
								                "userID": $scope.user.id
								            }
								        }).success(function (data) { 
								        	if(data.success){
								        		$scope.skills = data.data;
								        	}
								     });
									 $http.get('/i/user/findHonorByUserID', {
								            params: {
								                "userID": $scope.user.id
								            }
								        }).success(function (data) { 
								        	if(data.success){
								        		$scope.honors = data.data;
								        	}
								     });
									 $http.get('/i/user/findJobByUserID', {
								            params: {
								                "userID": $scope.user.id
								            }
								        }).success(function (data) { 
								        	if(data.success){
								        		$scope.jobs = data.data;
								        	}
								     });
									 }
					 });
					 $scope.followerNum = 0;
					 $http.get('/i/user/findFollowerByUserId', {
				            params: {
				                "userID": $scope.user.id
				            }
				        }).success(function (data) { 
				        	if(data!=null){
				        	$scope.followers = data;
				        	$scope.followerNum = $scope.followers.length;
				        	}
				     });
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
					 
					 if($scope.user.roleid!=null){
						 $http.get('/i/user/findRoleById', {
					            params: {
					                "roleID": $scope.user.roleid
					            }
					        }).success(function (data) {
					            $scope.userRole = data.name;
					     });
						 }
						 else{
							 $scope.userRole = '';
						 }
					 
					 if($scope.user.provinceid!=null){
					 $http.get('/i/city/findProvinceById', {
				            params: {
				                "provinceID": $scope.user.provinceid
				            }
				        }).success(function (data) {
				            $scope.userProvince = data.pr_province;
				     });
					 }
					 else{
						 $scope.userProvince = '';
					 }
					 if($scope.user.cityid!=null){
					 $http.get('/i/city/findCityById', {
				            params: {
				                "CityID": $scope.user.cityid
				            }
				        }).success(function (data) {
				            $scope.userCity = data.ci_city;
				     });
					 }
					 else{
						 $scope.userCity = '';
					 }
					 if($scope.user.schoolid!=null){
						 $http.get('/i/city/findSchoolById', {
					            params: {
					                "schoolID": $scope.user.schoolid
					            }
					        }).success(function (data) {
					            $scope.userSchool = data.sh_shool;
					     });
						 }
					else{
							 $scope.userSchool = '';
						 }
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
		
		$scope.askResume = function(){
			$http.post('/i/message/addMessage', {
	            params: {
	            	"messageSenderID" :$rootScope.user.id,
	                "messageReceiverID": $scope.spaceID,
	                "msgcategory" : 2,
	                "messageContent" : "申请查看您的简历"
	            }
	        }).success(function (data) {  
	        	if(data.success){
	        		alert(data.message);
	        		$scope.askFollowFlag = false;
	        	}
	        	else{
	        		alert(data.message);
	        	}
	     });
		};

	    $scope.goBlogInfo = function(publishID) {
	      	 $state.go('blog-info', {"publishID": publishID});
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
});
