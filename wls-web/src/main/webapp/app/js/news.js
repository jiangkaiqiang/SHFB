wlsWeb.controller('news',function($http, $location, $state,$scope) {
	// 显示最大页数
    $scope.maxSize = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 12;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.AllNews = [];
	$scope.optAudit = 8;
	$scope.AllCategory = [
	                      {id:"8",name:"全部"},
	                      {id:"1",name:"科技类"},
	                      {id:"2",name:"互联网类"},
	                      {id:"3",name:"校园类"},
	                      {id:"4",name:"财经类"},
	                      {id:"5",name:"创业类"}
	];
	 // 获取当前news的列表
    $scope.getNews = function() {
		$http({
			method : 'POST',
			url : '/i/information/findAllInformation',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				audit : $scope.optAudit,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.size;
			$scope.AllNews = data.list;
		});
	};
    
	$scope.pageChanged = function() {
		$scope.getNews();
	};
	$scope.getNews();

	$scope.auditChanged = function(optAudit) {
		$scope.optAudit = optAudit;
		$scope.getNews();
	};
    
	$scope.goSearch = function () {
		$scope.getNews();
    };
    
    $scope.goNewsInfo = function(newID) {
   	 $state.go('news-info', {"newID": newID});
	};
});
