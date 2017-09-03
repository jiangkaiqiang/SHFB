angular.module('app', ['ngFileUpload']).controller('detailspage', function ($scope, Upload, $http, $rootScope) { 
	$scope.load = function(){
		 $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
			   $rootScope.admin = data;
				if($rootScope.admin == null||$rootScope.admin ==undefined || $rootScope.admin.user_id == 0 || $rootScope.admin.user_id==undefined ){
					url = "../login.html";
					window.location.href = url;
				}
		   });
	};
	$scope.load();
	$scope.getCases = function() {
		$http({
			method : 'POST',
			url : '/i/record/findRecordByID',
			params : {
				recordID : getQueryString("id")
			}
		}).success(function(data) {
			$scope.Case = data;
		});
	};
	$scope.getCases();
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
		} 
});
