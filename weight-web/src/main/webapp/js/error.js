angular.module('app', ['ngFileUpload']).controller('error', function ($scope, Upload, $http,$rootScope) { 
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
	setTimeout(function(){
		$(".viewer01").viewer();
	},1000);
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
	
	// 显示最大页数
    $scope.maxSizeTime = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItemsTime = 12;
    // 当前页
    $scope.bigCurrentPageTime = 1;
	$scope.getTimeRecords = function() {
		$http({
			method : 'POST',
			url : '/i/record/findErrorTimeRecordList',
			params : {
				pageNum : $scope.bigCurrentPageTime,
				pageSize : $scope.maxSizeTime,
				startTime : $scope.startTimeTime,
				endTime : $scope.endTimeTime,
				keyword : encodeURI($scope.keywordTime,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItemsTime = data.total;
			$scope.AllRecordsTime = data.list;
			$scope.numPagesTime = data.pages;
		});
	}
	$scope.getTimeRecords();
	
	$scope.deleteRecordById = function(recordID) {
		if(delcfm()){
	    	$http.get('/i/record/deleteRecordByID', {
	            params: {
	                "recordID": recordID
	            }
	        }).success(function (data) {
	        	$scope.getTimeRecords();
	        	alert("删除成功");
	        });
	    	}
	}
	
	function delcfm() {
        if (!confirm("确认要删除？")) {
            return false;
        }
        return true;
    }
	
	$scope.firstPageTime = function() {
		$scope.bigCurrentPageTime = 1;
		$scope.getTimeRecords();
	};
	
	$scope.endPageTime = function() {
		$scope.bigCurrentPageTime = $scope.numPagesTime;
		$scope.getTimeRecords();
	};
	
	$scope.pagedesTime = function() {
		if($scope.bigCurrentPageTime>1){
			$scope.bigCurrentPageTime = $scope.bigCurrentPageTime-1;
			$scope.getTimeRecords();
		}
		   
	};
	
	$scope.pageaddTime = function() {
		if($scope.bigCurrentPageTime<$scope.numPagesTime){
			$scope.bigCurrentPageTime = $scope.bigCurrentPageTime+1;
			$scope.getTimeRecords();
		}
		  
	};
});
