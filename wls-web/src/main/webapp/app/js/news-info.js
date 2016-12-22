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
		$scope.newsComment = function(){
			if($rootScope.user.id==undefined){
				alert("请先登录!");
			}
			$http.get('/i/information/findInformationByID', {
	            params: {
	                "userID": $rootScope.user.id,
	                "commentDetail" : $scope.commentDetail
	            }
	        }).success(function(data){
	        	alert(data.message);
	      });
		};
});
