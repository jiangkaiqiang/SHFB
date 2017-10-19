coldWeb.controller('compFactoryManage', function ($rootScope, $scope, $state, $cookies, $http, $location) {
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
	$scope.Allcompfactorys = [];
	 // 获取当前冷库的列表

	  
    $scope.getCompFactorys = function() {
		$http({
			method : 'POST',
			url : '/i/compfactory/findCompFactoryList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				provinceid : $scope.searchprovinceid,
				userCompFactoryID : $rootScope.admin.comp_factory_id,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.Allcompfactorys = data.list;
		});
	}

	$scope.pageChanged = function() {
		$scope.getCompFactorys();
	}
	$scope.getCompFactorys();
	// 获取当前冷库的列表
	$scope.provinceChanged = function(provinceid) {
		$scope.getCompFactorys();
	}
    
	$scope.goSearch = function () {
		$scope.getCompFactorys();
    }
	
	$scope.searchProvinceSelected = function () {
		$scope.getCompFactorys();
    }
	
	$scope.showAll = function () {
		$state.reload();
    }
	
	 // 获取省列表
    $http.get('/i/city/findProvinceList').success(function (data) {
        $scope.provinces = data;
        $scope.addProvinceid = data[0].pr_id;
    });
    // 根据省ID查询城市列表
    $scope.provinceSelected = function () {
        $http.get('/i/city/findCitysByProvinceId', {
            params: {
                "provinceID": $scope.addProvinceid
            }
        }).success(function (data) {
            $scope.citys = data;
            $scope.addCityid = data[0].ci_id;
        });
    };
    
    $scope.citySelected = function () {
    };
    
    
    
    // 根据省ID查询城市列表
    $scope.provinceSelectedForUpdate = function () {
        $http.get('/i/city/findCitysByProvinceId', {
            params: {
                "provinceID": $scope.compfactoryUpdate.pr_id
            }
        }).success(function (data) {
            $scope.citysForUpdate = data;
            $scope.compfactoryUpdate.ci_id = data[0].ci_id;
        });
    };
    
    $scope.citySelectedForUpdate = function () {
    };
	
	function delcfm() {
	        if (!confirm("确认要删除？")) {
	            return false;
	        }
	        return true;
	}
	
    $scope.goDeleteCompFactory = function (compfactoryID) {
    	if(delcfm()){
    	$http.get('/i/compfactory/deleteCompFactoryByID', {
            params: {
                "compFactoryID": compfactoryID
            }
        }).success(function (data) {
        	$scope.getCompFactorys();
        	alert("删除成功");
        });
    	}
    }
    $scope.deleteCompFactorys = function(){
    	if(delcfm()){
    	var compfactoryIDs = [];
    	for(i in $scope.selected){
    		compfactoryIDs.push($scope.selected[i].compfactory.comp_factory_id);
    	}
    	if(compfactoryIDs.length >0 ){
    		$http({
    			method:'DELETE',
    			url:'/i/compfactory/deleteCompFactoryByIDs',
    			params:{
    				'compFactoryIDs': compfactoryIDs
    			}
    		}).success(function (data) {
    			$scope.getCompFactorys();
            	alert("删除成功");
            });
    	}
    	}
    }
   
    
    $scope.selected = [];
    $scope.toggle = function (compfactory, list) {
		  var idx = list.indexOf(compfactory);
		  if (idx > -1) {
		    list.splice(idx, 1);
		  }
		  else {
		    list.push(compfactory);
		  }
    };
    $scope.exists = function (compfactory, list) {
    	return list.indexOf(compfactory) > -1;
    };
    $scope.isChecked = function() {
        return $scope.selected.length === $scope.Allcompfactorys.length;
    };
    $scope.toggleAll = function() {
        if ($scope.selected.length === $scope.Allcompfactorys.length) {
        	$scope.selected = [];
        } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
        	$scope.selected = $scope.Allcompfactorys.slice(0);
        }
    };
    
    function checkInput(){
        var flag = true;
        // 检查必须填写项
        if ($scope.compfactoryname == undefined || $scope.compfactoryname == '') {
            flag = false;
        }
        if ($scope.contactName == undefined || $scope.contactName == '') {
            flag = false;
        }
        return flag;
    }
    $scope.submit = function(){
        if (checkInput()){
            $http({
            	method : 'GET',
            	url:'/i/compfactory/addCompFactory',
    			params:{
    				'comp_factory_name': $scope.compfactoryname,
    				'contacts_name': $scope.contactName,
    				'contacts_tel' : $scope.contactTel,
    				'contacts_phone' : $scope.contactPhone,
    				'pr_id': $scope.addProvinceid,
    				'ci_id': $scope.addCityid,
    				'address' : $scope.address
    				}
    		}).then(function (resp) {
    			 alert("添加成功");
                 $scope.getCompFactorys();
                 $("#addCompFactory").modal("hide"); 
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.name);
            });
          } else {
            alert("请填写构件厂名称和联系人!");
        }
    }
    $scope.goDetail = function(compfactoryID) {
    	$http.get('/i/compfactory/findCompFactoryByID', {
            params: {
                "compFactoryID": compfactoryID
            }
        }).success(function(data){
		    if(data!=null&&data.compfactory.comp_factory_id!=undefined){
				 $scope.compfactoryDetail = data;
		    }
	     });
	};
	
	 $scope.goUpdate = function(compfactoryID) {
	    	$http.get('/i/compfactory/findCompFactoryByID', {
	            params: {
	                "compFactoryID": compfactoryID
	            }
	        }).success(function(data){
			    if(data!=null&&data.compfactory.comp_factory_id!=undefined){
					 $scope.compfactoryUpdate = data.compfactory;
					 // 获取省列表
					 
					    $http.get('/i/city/findProvinceList').success(function (data) {
					        $scope.provincesForUpdate = data;
					        if($scope.compfactoryUpdate.pr_id!=null&&$scope.compfactoryUpdate.pr_id!=undefined){
					        	 $http.get('/i/city/findCitysByProvinceId', {
					                 params: {
					                     "provinceID": $scope.compfactoryUpdate.pr_id
					                 }
					             }).success(function (data) {
					                 $scope.citysForUpdate = data;
					             });
					   	    }
					        else{
					        	$scope.compfactoryUpdate.pr_id = data[0].pr_id;
					        }
					    });
			    }
		     });
		};
		function checkInputForUpdate(){
	        var flag = true;
	        // 检查必须填写项
	        if ($scope.compfactoryUpdate.comp_factory_name == undefined || $scope.compfactoryUpdate.comp_factory_name == '') {
	            flag = false;
	        }
	        if ($scope.compfactoryUpdate.contacts_name == undefined || $scope.compfactoryUpdate.contacts_name == '') {
	            flag = false;
	        }
	        return flag;
	    }
		 $scope.update = function(){
		        if (checkInputForUpdate()){
		            $http({
		            	method : 'GET',
		            	url:'/i/compfactory/updateCompFactory',
		    			params:{
		    				'comp_factory_id': $scope.compfactoryUpdate.comp_factory_id,
		    				'comp_factory_name': $scope.compfactoryUpdate.comp_factory_name,
		    				'contacts_name': $scope.compfactoryUpdate.contacts_name,
		    				'contacts_tel' : $scope.compfactoryUpdate.contacts_tel,
		    				'contacts_phone' : $scope.compfactoryUpdate.contacts_phone,
		    				'pr_id': $scope.compfactoryUpdate.pr_id,
		    				'ci_id': $scope.compfactoryUpdate.ci_id,
		    				'address' : $scope.compfactoryUpdate.address
		    			}
		    		}).then(function (resp) {
		    			 alert("更新成功");
		                 $scope.getCompFactorys();
		                 $("#updateCompfactory").modal("hide"); 
		            }, function (resp) {
		                console.log('Error status: ' + resp.status);
		            }, function (evt) {
		                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
		                console.log('progress: ' + progressPercentage + '% ' + evt.name);
		            });
		          } else {
		            alert("请填写构件厂名称和联系人!");
		        }
		    }
});
