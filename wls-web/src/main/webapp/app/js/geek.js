wlsWeb.controller('geek',function($http, $location,$rootScope, $scope,$state, $stateParams) {
	// 显示最大页数
    $scope.maxSize = 10;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 10;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.Allusers = [];
	$scope.optAudit = 8;
	$scope.citys = [];
	$scope.schools = [];
	$scope.cityid = -1;
	$scope.provinceid = -1;
	$scope.AllCategory = [
	                      {id:"8",name:"全部"},
	                      {id:"1",name:"设计狮"},
	                      {id:"2",name:"程序猿"},
	                      {id:"3",name:"学霸"},
	                      {id:"4",name:"墨客"},
	                      {id:"5",name:"极客"},
	                      {id:"6",name:"技术控"}
	];
	 // 获取当前geek的列表
    $scope.getUsers = function() {
    	var rootUserid = 0;
    	if($rootScope.user!=null&&$rootScope.user.id!=undefined){
    		rootUserid = $rootScope.user.id;
    	}
		$http({
			method : 'POST',
			url : '/i/user/findUserList',
			params : {
				userid : rootUserid,
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				provinceid  : $scope.provinceid,
				cityid  : $scope.cityid,
				schoolid  : $scope.schoolid,
				audit : $scope.optAudit,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.size;
			$scope.numPages = data.pages;
			$scope.Allusers = data.list;
		});
	};
	
	$scope.firstPage = function() {
		$scope.bigCurrentPage = 1;
		$scope.getUsers();
	};
	
	$scope.endPage = function() {
		$scope.bigCurrentPage = $scope.numPages;
		$scope.getUsers();
	};
	
	$scope.pagedes = function() {
		if($scope.bigCurrentPage>1){
			$scope.bigCurrentPage = $scope.bigCurrentPage-1;
			$scope.getUsers();
		}
		   
	};
	
	$scope.pageadd = function() {
		if($scope.bigCurrentPage<$scope.numPages){
			$scope.bigCurrentPage = $scope.bigCurrentPage+1;
			$scope.getUsers();
		}
		  
	};
	
	/* // 获取省列表
    $http.get('/i/city/findProvinceList').success(function (data) {
    	$scope.provinces = data;
    	var pro = {"pr_id":-1,"pr_province":"全部省份"};
    	$scope.provinces.push(pro);
    	$scope.provinces = sortJson($scope.provinces,"pr_id");
        $scope.provinceid = -1;
        $scope.curprovince = data[0];
        $scope.provinces[0].isOn = true;
    });

        var ci = {"ci_id":-1,"ci_city":"全部城市"};
     	$scope.citys.push(ci);
     	//$scope.citys = sortJson($scope.citys,"ci_id");
     	$scope.cityid = -1;
     	
     	var sh = {"sh_id":-1,"sh_shool":"全部学校"};
     	$scope.schools.push(sh);
     	//$scope.schools = sortJson($scope.schools,"sh_id");
     	$scope.schoolid = -1;
    
    // 根据省ID查询城市列表
    $scope.provinceSelected = function () {
    	$scope.schools = [];
    	$scope.schools.push(sh);
    	$scope.cityid = -1;
    	$scope.schoolid = -1;
    	//$scope.schools = sortJson($scope.schools,"sh_id");
    	$scope.getUsers();
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
    };
    
 // 根据城市ID查询学校列表
    $scope.citySelected = function () {
        $scope.schoolid = -1;
    	$scope.getUsers();
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
    };
    
 // 根据学校查询
    $scope.schoolSelected = function () {
    	$scope.getUsers();
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
    };*/
    
	
	$scope.searchProvince = function(provincename){
    	if(provincename==''){
    		$("#geekProvinceUl").css("display","none");
    		$scope.provinceid = -1;
	    	$scope.getUsers();
    	}
    	else{
    		 $http.get('/i/city/findProvinceByName', {
    	            params: {
    	                "provinceName": provincename
    	            }
    	        }).success(function (data) {
     	    	$scope.totalProvinces = data;
     	    	$("#geekProvinceUl").css("display","");
     	    });
    	}
    };
    $scope.chooseProvince = function(province){
    	$scope.provincename = province.pr_province;
    	$("#geekProvinceUl").css("display","none");
    	$scope.provinceid = province.pr_id;
    	$scope.getUsers();
    };
    
    
    
    
	
    $scope.searchCity = function(cityname){
    	if(cityname==''){
    		$("#geekCityUl").css("display","none");
    		$scope.cityid = -1;
	    	$scope.getUsers();
    	}
    	else{
    		 $http.get('/i/city/findCityByNameAndProvinceId', {
    	            params: {
    	                "cityName": cityname,
    	                "provinceID": $scope.provinceid
    	            }
    	        }).success(function (data) {
     	    	$scope.totalCitys = data;
     	    	$("#geekCityUl").css("display","");
     	    });
    	}
    };
    $scope.chooseCity = function(city){
    	$scope.cityname = city.ci_city;
    	$("#geekCityUl").css("display","none");
    	$scope.cityid = city.ci_id;
    	$scope.getUsers();
    };
    
    
	  $scope.searchSchool = function(schoolname){
	    	if(schoolname==''){
	    		$("#geekSchoolUl").css("display","none");
	    		$scope.schoolid = -1;
		    	$scope.getUsers();
	    	}
	    	else{
	    		 $http.get('/i/city/findSchoolByNameAndCityId', {
	    	            params: {
	    	                "schoolName": schoolname,
	    	                "cityID": $scope.cityid
	    	            }
	    	        }).success(function (data) {
	     	    	$scope.totalSchools = data;
	     	    	$("#geekSchoolUl").css("display","");
	     	    });
	    	}
	    };
	    $scope.chooseSchool = function(school){
	    	$scope.schoolname = school.sh_shool;
	    	$("#geekSchoolUl").css("display","none");
	    	$scope.schoolid = school.sh_id;
	    	$scope.getUsers();
	    };
	    
    $scope.goUserSpace = function(userID) {
    	 $state.go('my-space-ask', {"spaceID": userID});
	};
    
	$scope.pageChanged = function() {
		$scope.getUsers();
	};
	$scope.getUsers();

	$scope.auditChanged = function(optAudit) {
		$scope.optAudit = optAudit;
		$scope.getUsers();
	};
    
	$scope.goSearch = function () {
		$scope.getUsers();
    };
});
