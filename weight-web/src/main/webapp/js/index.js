angular.module('app', ['ngFileUpload']).controller('index', function ($scope, Upload, $http,$rootScope) { 
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
	// 显示最大页数
    $scope.maxSize = 10;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 10;
    // 当前页
    $scope.bigCurrentPage = 1;
    function getFirstRecords() {
      $http({
		method : 'POST',
		url : '/i/record/findFirstRecord',
		params : {
		}
	   }).success(function(data) {
		$scope.firstRecord = data;
	   });
    }
    getFirstRecords();
    jQuery(document).ready(function(){
    	    oTimer = setInterval(getFirstRecords,500);
    	    oTimer2 = setInterval(getRecords,500);
    });
	$scope.getRecords = function() {
		$http({
			method : 'POST',
			url : '/i/record/findRecordList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				startTime : $scope.startTime,
				endTime : $scope.endTime,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.AllRecords = data.list;
		});
	}
	$scope.getRecords();
	
	setTimeout(function(){
		$(".viewer01").viewer();
	},1000);
	
});
