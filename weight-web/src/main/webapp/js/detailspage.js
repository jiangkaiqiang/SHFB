angular.module('app', ['ngFileUpload']).controller('detailspage', function ($scope, Upload, $http) { 
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
