wlsWeb.controller('post-bar',function($http, $state,$rootScope, $stateParams,$location, $scope) {
	// 显示最大页数
    $scope.maxSize = 10;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 10;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.AllPublishs = [];
	$scope.optAudit = 8;
	$scope.citys = [];
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
    	var userID = 0;
    	if($rootScope.user!=null&&$rootScope.user.id!=undefined){
    		userID = $rootScope.user.id;
    	}
		$http({
			method : 'POST',
			url : '/i/publish/findPublishList',
			params : {
				userID : userID,
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				schoolid  : $scope.schoolid,
				audit : $scope.optAudit,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.size;
			$scope.numPages = data.pages;
			$scope.AllPublishs = data.list;
		});
	};

	$scope.firstPage = function() {
		$scope.bigCurrentPage = 1;
		$scope.getPublishs();
	};
	
	$scope.endPage = function() {
		$scope.bigCurrentPage = $scope.numPages;
		$scope.getPublishs();
	};
	
	$scope.pagedes = function() {
		if($scope.bigCurrentPage>1){
			$scope.bigCurrentPage = $scope.bigCurrentPage-1;
			$scope.getPublishs();
		}
		   
	};
	
	$scope.pageadd = function() {
		if($scope.bigCurrentPage<$scope.numPages){
			$scope.bigCurrentPage = $scope.bigCurrentPage+1;
			$scope.getPublishs();
		}
		  
	};
	
	 // 获取省列表
    $http.get('/i/city/findProvinceList').success(function (data) {
    	$scope.provinces = data;
    	var pro = {"pr_id":-1,"pr_province":"全部省份"};
    	$scope.provinces.push(pro);
    	$scope.provinces = sortJson($scope.provinces,"pr_id");
        $scope.provinceid = -1;
    });

        var ci = {"ci_id":-1,"ci_city":"全部城市"};
     	$scope.citys.push(ci);
     	$scope.citys = sortJson($scope.citys,"ci_id");
     	$scope.cityid = -1;
    
    // 根据省ID查询城市列表
    $scope.provinceSelected = function () {
    	$scope.cityid = -1;
    	$scope.schoolid = -1;
    	$scope.schools = undefined;
        $http.get('/i/city/findCitysByProvinceId', {
            params: {
                "provinceID": $scope.provinceid
            }
        }).success(function (data) {
            $scope.citys = data;
            var ci = {"ci_id":-1,"ci_city":"全部城市"};
        	$scope.citys.push(ci);
        	$scope.citys = sortJson($scope.citys,"ci_id");
        });
        $scope.getPublishs();
    };
    
 // 根据城市ID查询学校列表
    $scope.citySelected = function () {
        $scope.schoolid = -1;
    	$http.get('/i/city/findSchoolsByCityId', {
            params: {
                "cityID": $scope.cityid
            }
        }).success(function (data) {
        	 $scope.schools = data;
             var sh = {"sh_id":-1,"sh_shool":"全部学校"};
         	 $scope.schools.push(sh);
         	 $scope.schools = sortJson($scope.schools,"sh_id");
        });
    	$scope.getPublishs();
    };
    
 // 根据学校查询
    $scope.schoolSelected = function (schoolid) {
    	$scope.schoolid = schoolid;
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
    
    $scope.praiseBlog = function (publishID) {
    	if($rootScope.user==null||$rootScope.user.id==undefined){
    		alert("请先登录再点赞");
    		return;
    	}
    	$http.get('/i/publish/praisePublish', {
            params: {
                "publishID": publishID,
                "userID": $rootScope.user.id
            }
        }).success(function (data) {
        	$scope.getPublishs();
        });
    };
    
    $scope.goBlogInfo = function(publishID) {
      	 $state.go('blog-info', {"publishID": publishID});
   	};
});