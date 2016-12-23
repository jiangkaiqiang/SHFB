wlsWeb.controller('news-info',function($http, $location, $state, $rootScope,$stateParams,$scope) {
	$scope.load = function(){
		   $scope.newID = $stateParams.newID;
	    	$http.get('/i/information/findInformationByID', {
	            params: {
	                "inforID": $scope.newID
	            }
	        }).success(function(data){
	        	   $scope.information = data;
	        	   document.getElementById("inforContent").innerHTML=$scope.information.content;
			   });
		   };
		$scope.load();
		$scope.newsComment = function(infoID){
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
	                "commentid" : infoID,
	                "flag" : 0
	            }
	        }).success(function(data){
	        	alert(data.message);
	        	$state.reload();	
	      });
		};
});
