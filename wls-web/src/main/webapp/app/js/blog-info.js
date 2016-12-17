wlsWeb.controller('blog-info',function($http, $location,$state, $stateParams, $scope) {
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
});
