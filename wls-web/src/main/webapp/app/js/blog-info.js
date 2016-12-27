wlsWeb.controller('blog-info',function($http, $rootScope,$location,$state, $stateParams, $scope) {
	$scope.load = function(){
		   $scope.publishID = $stateParams.publishID;
	    	$http.get('/i/publish/findPublishByID', {
	            params: {
	                "publishID": $scope.publishID
	            }
	        }).success(function(data,status,config,headers){
	        	   $scope.publish = data;
	        	   document.getElementById("pubContent").innerHTML=$scope.publish.content;
			   });
		   };
		$scope.load();
		$scope.blogComment = function(publishID){
			if($rootScope.user==null||$rootScope.user.id==undefined){
				alert("请先登录!");
			}
			if($scope.commentDetail==undefined||$scope.commentDetail==""){
				alert("请输入评论内容");
			}
			$http.get('/i/comment/insertComment', {
	            params: {
	                "userID": $rootScope.user.id,
	                "commentDetail" : $scope.commentDetail,
	                "commentid" : publishID,
	                "flag" : 1
	            }
	        }).success(function(data){
	        	alert(data.message);
	        	$state.reload();	
	      });
		};
});
