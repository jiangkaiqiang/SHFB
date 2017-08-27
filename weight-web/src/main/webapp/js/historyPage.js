angular.module('app', ['ngFileUpload']).controller('history', function ($scope, Upload, $http) { 
	// 显示最大页数
    $scope.maxSize = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 12;
    // 当前页
    $scope.bigCurrentPage = 1;
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
			$scope.numPages = data.pages;
		});
	}
	$scope.getRecords();
	$scope.firstPage = function() {
		$scope.bigCurrentPage = 1;
		$scope.getRecords();
	};
	
	$scope.endPage = function() {
		$scope.bigCurrentPage = $scope.numPages;
		$scope.getRecords();
	};
	
	$scope.pagedes = function() {
		if($scope.bigCurrentPage>1){
			$scope.bigCurrentPage = $scope.bigCurrentPage-1;
			$scope.getRecords();
		}
		   
	};
	
	$scope.pageadd = function() {
		if($scope.bigCurrentPage<$scope.numPages){
			$scope.bigCurrentPage = $scope.bigCurrentPage+1;
			$scope.getRecords();
		}
		  
	};
	
	setTimeout(function(){
		$(".viewer01").viewer();
	},1000);
});
