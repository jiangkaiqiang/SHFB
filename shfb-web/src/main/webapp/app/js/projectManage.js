coldWeb.controller('projectManage', function ($rootScope, $scope, $state, $cookies, $http, $location) {
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
	  
    $scope.getProjects = function() {
		$http({
			method : 'POST',
			url : '/i/project/findProjectList',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				provinceid : $scope.searchprovinceid,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.Allprojects = data.list;
		});
	}

	$scope.pageChanged = function() {
		$scope.getProjects();
	}
	$scope.getProjects();
	// 获取当前冷库的列表
	$scope.provinceChanged = function(provinceid) {
		$scope.getProjects();
	}
    
	$scope.goSearch = function () {
		$scope.getProjects();
    }
	
	$scope.searchProvinceSelected = function () {
		$scope.getProjects();
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
                "provinceID": $scope.projectUpdate.pr_id
            }
        }).success(function (data) {
            $scope.citysForUpdate = data;
            $scope.projectUpdate.ci_id = data[0].ci_id;
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
	
    $scope.goDeleteProject = function (projectID) {
    	if(delcfm()){
    	$http.get('/i/project/deleteProjectByID', {
            params: {
                "projectID": projectID
            }
        }).success(function (data) {
        	$scope.getProjects();
        	alert("删除成功");
        });
    	}
    }
    $scope.deleteProjects = function(){
    	if(delcfm()){
    	var projectIDs = [];
    	for(i in $scope.selected){
    		projectIDs.push($scope.selected[i].pro_id);
    	}
    	if(projectIDs.length >0 ){
    		$http({
    			method:'DELETE',
    			url:'/i/project/deleteProjectByIDs',
    			params:{
    				'projectIDs': projectIDs
    			}
    		}).success(function (data) {
    			$scope.getProjects();
            	alert("删除成功");
            });
    	}
    	}
    }
   
    
    $scope.selected = [];
    $scope.toggle = function (project, list) {
		  var idx = list.indexOf(project);
		  if (idx > -1) {
		    list.splice(idx, 1);
		  }
		  else {
		    list.push(project);
		  }
    };
    $scope.exists = function (project, list) {
    	return list.indexOf(project) > -1;
    };
    $scope.isChecked = function() {
        return $scope.selected.length === $scope.Allprojects.length;
    };
    $scope.toggleAll = function() {
        if ($scope.selected.length === $scope.Allprojects.length) {
        	$scope.selected = [];
        } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
        	$scope.selected = $scope.Allprojects.slice(0);
        }
    };
    
    function checkInput(){
        var flag = true;
        // 检查必须填写项
        if ($scope.proname == undefined || $scope.proname == '') {
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
            	url:'/i/project/addProject',
    			params:{
    				'pro_name': $scope.proname,
    				'contacts_name': $scope.contactName,
    				'contacts_tel' : $scope.contactTel,
    				'contacts_phone' : $scope.contactPhone,
    				'pr_id': $scope.addProvinceid,
    				'ci_id': $scope.addCityid,
    				'address' : $scope.address,
    				'details' : $scope.detail
    			}
    		}).then(function (resp) {
    			 alert("添加成功");
                 $scope.getProjects();
                 $("#addProject").modal("hide"); 
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.name);
            });
          } else {
            alert("请填写项目名称和联系人!");
        }
    }
    $scope.goDetail = function(projectID) {
    	$http.get('/i/project/findProjectByID', {
            params: {
                "projectID": projectID
            }
        }).success(function(data){
		    if(data!=null&&data.project.pro_id!=undefined){
				 $scope.projectDetail = data;
		    }
	     });
	};
	
	 $scope.goUpdate = function(projectID) {
	    	$http.get('/i/project/findProjectByID', {
	            params: {
	                "projectID": projectID
	            }
	        }).success(function(data){
			    if(data!=null&&data.project.pro_id!=undefined){
					 $scope.projectUpdate = data.project;
					 // 获取省列表
					 
					    $http.get('/i/city/findProvinceList').success(function (data) {
					        $scope.provincesForUpdate = data;
					        if($scope.projectUpdate.pr_id!=null&&$scope.projectUpdate.pr_id!=undefined){
					        	 $http.get('/i/city/findCitysByProvinceId', {
					                 params: {
					                     "provinceID": $scope.projectUpdate.pr_id
					                 }
					             }).success(function (data) {
					                 $scope.citysForUpdate = data;
					             });
					   	    }
					        else{
					        	$scope.projectUpdate.pr_id = data[0].pr_id;
					        }
					    });
			    }
		     });
		};
		function checkInputForUpdate(){
	        var flag = true;
	        // 检查必须填写项
	        if ($scope.projectUpdate.pro_name == undefined || $scope.projectUpdate.pro_name == '') {
	            flag = false;
	        }
	        if ($scope.projectUpdate.contacts_name == undefined || $scope.projectUpdate.contacts_name == '') {
	            flag = false;
	        }
	        return flag;
	    }
		 $scope.update = function(){
		        if (checkInputForUpdate()){
		            $http({
		            	method : 'GET',
		            	url:'/i/project/updateProject',
		    			params:{
		    				'pro_id': $scope.projectUpdate.pro_id,
		    				'pro_name': $scope.projectUpdate.pro_name,
		    				'contacts_name': $scope.projectUpdate.contacts_name,
		    				'contacts_tel' : $scope.projectUpdate.contacts_tel,
		    				'contacts_phone' : $scope.projectUpdate.contacts_phone,
		    				'pr_id': $scope.projectUpdate.pr_id,
		    				'ci_id': $scope.projectUpdate.ci_id,
		    				'address' : $scope.projectUpdate.address,
		    				'details' : $scope.projectUpdate.details
		    			}
		    		}).then(function (resp) {
		    			 alert("更新成功");
		                 $scope.getProjects();
		                 $("#updateProject").modal("hide"); 
		            }, function (resp) {
		                console.log('Error status: ' + resp.status);
		            }, function (evt) {
		                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
		                console.log('progress: ' + progressPercentage + '% ' + evt.name);
		            });
		          } else {
		            alert("请填写项目名称和联系人!");
		        }
		    }
		 
		 $scope.importComponent=function(){
			 var projectIDs = [];
		    	for(i in $scope.selected){
		    		projectIDs.push($scope.selected[i].pro_id);
		    	}
		    	if(projectIDs.length == 0 ){
		    		alert("请选择项目");		    		
		    	}else{
		    		$('#importPic').modal('show')
		    		
		    	}
		 }
		 
		 $scope.loadTem=function(){
			 window.location.href="../../assets/file/componentTemplate.xlsx";
		 }
		 
		 $scope.importPic=function(){
//			 $http({
//				 method:'POST',
//				 url: '/i/project/fileUpload',
//				 headers: {
//
//				    'Content-Type': undefined
//
//				  },
//				  data: {
//
//				    filename:document.getElementsByClassName('input-file')[0].files[0],
//				    problemType: '3'
//
//				  },
//				  transformRequest: function(data, headersGetter) {
//
//				    let formData = new FormData();
//				    angular.forEach(data, function (value, key) {
//				    formData.append(key, value);
//
//				    }
//
//				    return formData;
//
//				   }
//
//				})
//				.success(function()  {
//
//				  alert('Upload Successfully');
//
//				})
//				.error(function()  {
//
//				  alert('Fail to upload, please upload again');
//
//				});
			 $http({
			        method: 'POST',
			        url: '/i/project/fileUpload',
			        headers: {
			        	'Content-Type': undefined
			        	},
			        data: {
			        		 	filename:document.getElementsByClassName('input-file')[0].files[0],
			        		 	problemType: '3'
			        		 	},
			       transformRequest:function(){
			    	   var formData = new FormData();
			    	   angular.forEach(data,function(value, key){
			    		   formData.append(key, value);
			    	   });
			    	   
			       }
			    }).then(function successCallback(response) {
			            $scope.names = response.data.sites;
			        }, function errorCallback(response) {
			            // 请求失败执行代码
			    });
		 }
		 
});
