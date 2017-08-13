angular.module('app', ['ngFileUpload']).controller('error', function ($scope, Upload, $http) { 
	// 显示最大页数
    $scope.maxSize = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 12;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.getRecords = function() {
		$http({
			method : 'POST',
			url : '/i/record/findErrorRecordList',
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
	
	$scope.addCarNumByRecordId = function(recordId,car_num) {
		if(car_num==undefined ||car_num==""){
			alert("请输入有效车牌号");
			return;
		}
		$http({
			method : 'POST',
			url : '/i/record/addCarNumByRecordIdEntry',
			params : {
				recordId :recordId,
				carNum : encodeURI(car_num,"UTF-8"),
			}
		}).success(function(data) {
			$scope.getRecords();
		});
	}
});
