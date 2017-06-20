coldWeb.controller('componentManage', function ($rootScope, $scope, $state, $cookies, $http, $location) {
	
	$scope.load = function(){
		 $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
			   $rootScope.admin = data;
				if($rootScope.admin == null || $rootScope.admin.user_id == 0 || admin.user_id==undefined){
					url = "http://" + $location.host() + ":" + $location.port() + "/login.html";
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
	$scope.Allprojects = [];
	 // 获取当前冷库的列表
	  
    $scope.getComponents = function() {
		$http({
			method : 'POST',
			url : '/i/component/findComponentPage',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				provinceid : $scope.searchprovinceid,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.components = data.list;
			console.log(data.list);
		});
	}

	$scope.pageChanged = function() {
		$scope.getComponents();
	}
	$scope.getComponents();
	// 获取当前冷库的列表
	$scope.provinceChanged = function(provinceid) {
		$scope.getComponents();
	}
    
	$scope.goSearch = function () {
		$scope.getComponents();
    }
	
	$scope.searchProvinceSelected = function () {
		$scope.getComponents();
    }
	
	
	$scope.showAll = function () {
		$state.reload();
    }
	
	
});
