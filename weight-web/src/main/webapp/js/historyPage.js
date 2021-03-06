angular.module('app', ['ngFileUpload']).controller('history', function ($scope, Upload, $http,$rootScope) { 
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
			url : '/i/record/findRecordList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				startTime : $("#startTime").val(),
				endTime : $("#endTime").val(),
				startEntryTime : $("#startEntryTime").val(),
				endEntryTime : $("#endEntryTime").val(),
				keyword : encodeURI($scope.keyword,"UTF-8"),
				material : encodeURI($scope.material,"UTF-8"),
				companyName : encodeURI($scope.companyName,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.AllRecords = data.list;
			$scope.numPages = data.pages;
		});
	}
	$scope.getRecords();
	$scope.goSearch = function () {
		$scope.getRecords();
    }
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
	
	$('#datetimepicker1').datetimepicker({  
    	autoclose:true
    }).on('dp.change', function (e) {  
    });  
   $('#datetimepicker2').datetimepicker({  
    	autoclose:true
    }).on('dp.change', function (e) {  
    });  
   $('#datetimepicker3').datetimepicker({  
 	autoclose:true
   }).on('dp.change', function (e) {  
   });  
   $('#datetimepicker4').datetimepicker({  
 	autoclose:true
   }).on('dp.change', function (e) {  
   });  
});
