wlsWeb.controller('news-info',function($http, $location, $state, $stateParams,$scope) {
	$scope.load = function(){
		   $scope.newID = $stateParams.newID;
	    	$http.get('/i/information/findInformationByID', {
	            params: {
	                "inforID": $scope.newID
	            }
	        }).success(function(data,status,config,headers){
	        	   $scope.information = data;
	        	   document.getElementById("inforContent").innerHTML=$scope.information.content;
			   });
		   };
		$scope.load();
});
