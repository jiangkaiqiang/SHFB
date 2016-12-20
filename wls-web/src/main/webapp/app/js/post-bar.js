wlsWeb.controller('post-bar',function($http, $state, $stateParams,$location, $scope) {
	// 显示最大页数
    $scope.maxSize = 12;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 12;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.AllPublishs = [];
	$scope.optAudit = 8;
	$scope.AllCategory = [
	                      {id:"8",name:"全部"},
	                      {id:"1",name:"科技类"},
	                      {id:"2",name:"互联网类"},
	                      {id:"3",name:"校园类"},
	                      {id:"4",name:"财经类"},
	                      {id:"5",name:"创业类"}
	];
	 // 获取当前geek的列表
    $scope.getPublishs = function() {
		$http({
			method : 'POST',
			url : '/i/publish/findPublishList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				schoolid  : $scope.schoolid,
				audit : $scope.optAudit,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.AllPublishs = data.list;
		});
	};

    // 获取学校列表
    $http.get('/i/city/findSchoolList').success(function (data) {
    	$scope.schools = data;
        var sh = {"sh_id":-1,"sh_shool":"全部学校"};
    	 $scope.schools.push(sh);
    	 $scope.schools = sortJson($scope.schools,"sh_id");
    	 $scope.curschool = data[0];
    	 $scope.schools[0].isOn = true;
    });
    
 // 根据学校查询
    $scope.schoolSelected = function (curschool) {
    	$scope.schoolid = curschool.sh_id;
    	$scope.getPublishs();
    };
    
    
    function sortJson(json,key){ 
        for (var i = 0; i < json.length; i++) {
            for (var j = 0; j < json.length-1; j++) {
                if (json[j][key]>json[j+1][key]) {
                    var temp=json[j];
                    json[j]=json[j+1];
                    json[j+1]=temp;
                };
            };
        };
        return json;
    };
    
    $scope.goUserSpace = function(userID) {
    	window.location.href="#/my-space-ask?id="+userID;
	};
    
	$scope.pageChanged = function() {
		$scope.getPublishs();
	};
	$scope.getPublishs();

	$scope.auditChanged = function(optAudit) {
		$scope.optAudit = optAudit;
		$scope.getPublishs();
	};
    
	$scope.goSearch = function () {
		$scope.getPublishs();
    };
    
    $scope.goBlogInfo = function(publishID) {
      	 $state.go('blog-info', {"publishID": publishID});
   	};
});