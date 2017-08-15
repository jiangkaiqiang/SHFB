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
			url : '/i/record/findErrorEntryRecordList',
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
			if($scope.bigTotalItems<=0){
				document.getElementById("entryError").style.display="none";
				document.getElementById("leaveError").style.display="";
			}
		});
	}
	$scope.getRecords();
	
	$scope.goToLeaveError = function() {
		$http({
			method : 'POST',
			url : '/i/record/findErrorEntryRecordList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				startTime : $scope.startTime,
				endTime : $scope.endTime,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			if($scope.bigTotalItems>0){
				alert("请先处理完进入异常数据才可以处理离开异常数据！");
			}
			else{
				$scope.getRecords();
			}
		});
	};
	
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
	
	// 显示最大页数
    $scope.maxSizeLeave = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItemsLeave = 12;
    // 当前页
    $scope.bigCurrentPageLeave = 1;
	$scope.getLeaveRecords = function() {
		$http({
			method : 'POST',
			url : '/i/record/findErrorLeaveRecordList',
			params : {
				pageNum : $scope.bigCurrentPageLeave,
				pageSize : $scope.maxSizeLeave,
				startTime : $scope.startTimeLeave,
				endTime : $scope.endTimeLeave,
				keyword : encodeURI($scope.keywordLeave,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItemsLeave = data.total;
			$scope.AllRecordsLeave = data.list;
			$scope.numPagesLeave = data.pages;
		});
	}
	$scope.getLeaveRecords();
	
	$scope.firstPageLeave = function() {
		$scope.bigCurrentPageLeave = 1;
		$scope.getLeaveRecords();
	};
	
	$scope.endPageLeave = function() {
		$scope.bigCurrentPageLeave = $scope.numPagesLeave;
		$scope.getLeaveRecords();
	};
	
	$scope.pagedesLeave = function() {
		if($scope.bigCurrentPageLeave>1){
			$scope.bigCurrentPageLeave = $scope.bigCurrentPageLeave-1;
			$scope.getLeaveRecords();
		}
		   
	};
	
	$scope.pageaddLeave = function() {
		if($scope.bigCurrentPageLeave<$scope.numPagesLeave){
			$scope.bigCurrentPageLeave = $scope.bigCurrentPageLeave+1;
			$scope.getLeaveRecords();
		}
		  
	};
	
	$scope.addCarNumByLeaveRecordId = function(recordId,car_num) {
		if(car_num==undefined ||car_num==""){
			alert("请输入有效车牌号");
			return;
		}
		$http({
			method : 'POST',
			url : '/i/record/addCarNumByRecordIdLeave',
			params : {
				recordId :recordId,
				carNum : encodeURI(car_num,"UTF-8"),
			}
		}).success(function(data) {
			if(data.status==1){
				alert("添加无效，没有找到对应的车辆");
			}
			else{
				$scope.getLeaveRecords();
			}
		});
	}
});
