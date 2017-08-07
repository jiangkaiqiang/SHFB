coldWeb.controller('componentOrder', function ($rootScope, $scope, $state, $cookies, $http, $location) {
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
	$scope.componentOrders = [];
	 // 获取当前冷库的列表
	  
    $scope.getComponentOrders = function() {
    	
    	$scope.pro_idf ="";
 	   if($scope.selectedProject!=undefined && $scope.selectedProject.pro_id!=undefined) {
 		   $scope.pro_idf=$scope.selectedProject.pro_id;
		   }
 	   $scope.single_namef="";
		   if($scope.selectSingle!=undefined&&$scope.selectSingle.single_name!=undefined) {
			   $scope.single_namef=$scope.selectSingle.single_name;
		   }
		   $scope.floorf="";
		   if($scope.selectFloor!=undefined && $scope.selectFloor.floor!=undefined) {
			   $scope.floorf=$scope.selectFloor.floor;
		   }
    	
		$http({
			method : 'POST',
			url : '/i/componentOrder/findComponentOrderPage',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				pro_id : $scope.pro_idf,
				single_name : $scope.single_namef,
				userProjectID : $rootScope.admin.pro_id,
				userCompFactoryID : $rootScope.admin.comp_factory_id,
				floor:$scope.floorf
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.componentOrders = data.list;
		});
	}
    
    $scope.pageChanged = function() {
    	$scope.getComponentOrders();
	}
    
    $scope.getComponentOrders();
	
	$scope.showAll = function () {
		$state.reload();
    }
	
	//查询所有项目
	$scope.findProjects = function(){
		$http({
			method : 'GET',
			url : '/i/project/findAllProjectByUserID',
			params : {
				userProjectID : $rootScope.admin.pro_id
			}
		}).success(function(data) {
			$scope.projects = data;
			
//			$scope.selectedProject=data[0];
//			$scope.projectChange($scope.selectedProject.pro_id);
		});
	}
	$scope.findProjects();
	
	$scope.projectChange=function(pro_id){
		$http({
			method : 'GET',
			url : '/i/component/findSingleByProject',
			params : {
				pro_id : pro_id
			}
		}).success(function(data) {
			$scope.singles = data;
//			$scope.selectSingle = $scope.singles[0];
//			try
//			{
//				$scope.singleChange($scope.selectedProject.pro_id, $scope.selectSingle.single_name);
//			}
//			catch(err)
//			{
//				$scope.floors = [];
//				
//			}
		});
	}
	
	$scope.singleChange=function(pro_id, single_name){
		$http({
			method : 'GET',
			url : '/i/component/findFloorBySel',
			params : {
				pro_id : pro_id,
				single_name : single_name
			}
		}).success(function(data) {
			$scope.floors = data;
			//$scope.selectFloor = $scope.floors[0];
		});
	}
	
	 $scope.selectByPrimaryKey = function(order_id) {
	    	$http.get('/i/componentOrder/selectByPrimaryKey', {
	            params: {
	                order_id: order_id
	            }
	        }).success(function(data){
	        	$scope.order = data;
		     });
		};
		
	$scope.updateByPrimaryKeySelective = function(){
		console.log();
		$http.get('/i/componentOrder/updateByPrimaryKeySelective', {
            params: {
                order_id: $scope.order.order_id,
                plan_begin_date:$scope.order.plan_begin_date,
                plan_end_date:$scope.order.plan_end_date,
                real_begin_date:$scope.order.real_begin_date,
                real_end_date:$scope.order.real_end_date
            }
        }).success(function(data){
        	alert(data.message);
        	$("#updateOrder").css("aria-hidden",true);
        	$scope.getComponentOrders();
	     });
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
	        return $scope.selected.length === $scope.componentOrders.length;
	    };
	    $scope.toggleAll = function() {
	        if ($scope.selected.length === $scope.componentOrders.length) {
	        	$scope.selected = [];
	        } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
	        	$scope.selected = $scope.componentOrders.slice(0);
	        }
	    };
	    

		//进度计划导出
		$scope.exportCompOrder = function(){
			
			var parmStr="";
	    	for(i in $scope.selected){
	    		parmStr += $scope.selected[i].order_id +","
	    	}
	    	if(parmStr=="") {alert("请选择计划"); return;}
	    	window.location.href="/i/componentOrder/exportCompOrder?order_idStrs="+parmStr;
		    	
		}
		
		 $('.datetimepickerDisplay').datetimepicker({  
		    	format: 'yyyy-mm-dd',
		    	autoclose:true,
		    	minView:'month'
		    }).on('dp.change', function (e) {  
		    });
	
});
