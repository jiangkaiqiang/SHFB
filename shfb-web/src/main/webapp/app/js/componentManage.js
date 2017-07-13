coldWeb.controller('componentManage', function ($rootScope, $scope, $state, $cookies, $http, $location) {
	
	 $scope.isinfoShow = false;  
	 $scope.ismanageShow = true;
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
	$scope.components = [];
	$scope.picture_comp_makes = [];
	$scope.picture_product_accepts = [];
	$scope.picture_receipts = [];
	$scope.picture_installs = [];
	 // 获取当前冷库的列表
	  
    $scope.getComponents = function() {
    	   $scope.pro_idf ="";
    	   if($scope.selectedProject!=undefined && $scope.selectedProject.pro_id!=undefined) {
    		   $scope.pro_idf=$scope.selectedProject.pro_id;
		   }
    	   $scope.single_namef="";
		   if($scope.selectSingle!=undefined&&$scope.selectSingle.single_name!=undefined) {
			   $scope.single_namef=$scope.selectSingle.single_name;
		   }
		   $scope.floorf="";
		   if($scope.$scope!=undefined&&$scope.selectFloor.floor!=undefined) {
			   $scope.floorf=$scope.selectFloor.floor;
		   }
		   $scope.component_typef="";
		   if($scope.selectType!=undefined&&$scope.selectType.component_type!=undefined) {
			   $scope.component_typef=$scope.selectType.component_type;
		   }
		   $scope.component_status_idf="";
		   if($scope.selectStatus!=undefined&&$scope.selectStatus.component_status_id!=undefined) {
			   $scope.component_status_idf=$scope.selectStatus.component_status_id;
		   }
		$http({
			method : 'POST',
			url : '/i/component/findComponentPage',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				pro_id : $scope.pro_idf,
				single_name : $scope.single_namef,
				floor:$scope.floorf,
				component_type:$scope.component_typef,
				component_status_id:$scope.component_status_idf,
				userProjectID : $rootScope.admin.pro_id,
				userCompFactoryID : $rootScope.admin.comp_factory_id
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.total;
			$scope.components = data.list;
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
	
	$scope.showInfo = function (component_id) {
		 $scope.isinfoShow = true;  
		 $scope.ismanageShow = false;
		 $scope.getCompProgresses(component_id);
		 $scope.findComponentInfoById(component_id);
		 
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
	
	//查询所有构件类型
	$scope.findComponentTypes = function(){
		$http({
			method : 'GET',
			url : '/i/component/findComponentTypes',
		}).success(function(data) {
			$scope.componentTypes = data;
		});
	}
	$scope.findComponentTypes();
	
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
	
		
		//查询构件进度
	   $scope.getCompProgresses = function(component_id) {
			$http({
				method : 'GET',
				url : '/i/compProgress/selectByPrimaryKey',
				params : {
					component_id : component_id
				}
			}).success(function(data) {
				$scope.progresses = data;
			});
		}
	   //查询构件详细信息
	   $scope.findComponentInfoById = function(component_id) {
			$http({
				method : 'GET',
				url : '/i/component/findComponentInfoById',
				params : {
					component_id : component_id
				}
			}).success(function(data) {
				$scope.componentInfo = data;
				$("#statusSel").children().each(function(){
					if($(this).val()==$scope.componentInfo.component_status_name) {
						
						$(this).attr("selected","selected");
					}
				});
				if($scope.componentInfo.picture_comp_make != undefined) {
					console.log($scope.componentInfo.picture_comp_make);
					$scope.picture_comp_makes=$scope.componentInfo.picture_comp_make.split(";")
					console.log($scope.picture_comp_makes);
				}
				if($scope.componentInfo.picture_product_accept != undefined) {
					$scope.picture_product_accepts=$scope.componentInfo.picture_product_accept.split(";")
				}
				if($scope.componentInfo.picture_receipt != undefined) {
					$scope.picture_receipts=$scope.componentInfo.picture_receipt.split(";")
				}
				if($scope.componentInfo.picture_install != undefined) {
					$scope.picture_installs=$scope.componentInfo.picture_install.split(";")
				}
				
			});
		}
	   
	   
	   //创建图片查看器
	   $scope.viewerShow=function(){
		   console.log($("#viewer1").html());
		  $("#viewer1").viewer();
		  $("#viewer2").viewer();
		  $("#viewer3").viewer();
		  $("#viewer4").viewer();
		  $("#viewer5").viewer();
	   }
	   
	   //返回按钮
	   $scope.goback=function(){
		   $state.reload();
	   }
	   //查询构件所有状态
	   $scope.showStatus=function(){
		   $http({
				method : 'GET',
				url : '/i/component/findComponentStatus'
			}).success(function(data) {
				$scope.statuslist = data;
				
			});
	   }
	   $scope.showStatus();
	   
	   //编辑
	   $scope.editComp=function(){
		   $scope.isedit=true;
		   $(".datetimepickerDisplay").css("display","block");
		   $('.datetimepickerDisplay').datetimepicker({  
		    	format: 'yyyy-mm-dd',
		    	autoclose:true,
		    	minView:'month'
		    }).on('dp.change', function (e) {  
		    });
	   }
	   
	   //保存编辑
	   $scope.uploadComp=function(){
		   $http({
				method : 'GET',
				url : '/i/component/updateByPrimaryKeySelective',
				params : {
						component_id : $("#component_id").html(),
						expedit_date : $("#expedit_date").val(),
						plan_begin_date : $("#plan_begin_date").val(),
						plan_end_date : $("#plan_end_date").val(),
						real_begin_date : $("#real_begin_date").val(),
						real_end_date : $("#real_end_date").val(),
						component_status_id : $("#statusSel").val(),
						product_plan_begin_date : $("#product_plan_begin_date").val(),
						product_plan_end_date : $("#product_plan_end_date").val()
					}
			}).success(function(data) {
				alert(data.message);
				$scope.componentInfo.component_status_name=$("#statusSel").find("option:selected").text();
				$scope.componentInfo.expedit_date=$("#expedit_date").val();
				$scope.componentInfo.plan_begin_date=$("#plan_begin_date").val();
				$scope.componentInfo.plan_end_date=$("#plan_end_date").val();
				$scope.componentInfo.real_begin_date=$("#real_begin_date").val();
				$scope.componentInfo.real_end_date=$("#real_end_date").val();
				$scope.componentInfo.product_plan_begin_date=$("#product_plan_begin_date").val();
				$scope.componentInfo.product_plan_end_date=$("#product_plan_end_date").val();
				
				$scope.isedit=false;
				$(".datetimepickerDisplay").css("display","none");
			});
	   }  
	   
	   $scope.deleteComp = function (component_id) {
	    	if(delcfm()){
	    	$http.get('/i/component/deleteComp', {
	            params: {
	                "component_id": component_id
	            }
	        }).success(function (data) {
	        	$scope.getComponents();
	        	alert(data.message);
	        });
	    	}
	    }
	   
		function delcfm() {
	        if (!confirm("确认要删除？")) {
	            return false;
	        }
	        return true;
		}
		
		function isConfirm(info) {
	        if (!confirm(info)) {
	            return false;
	        }
	        return true;
		}
		
		
		//构件导出
		$scope.exportComp = function(){
			
			var parmStr="";
	    	for(i in $scope.selected){
	    		parmStr += $scope.selected[i].component_id +","
	    	}
	    	if(parmStr=="") {alert("请选择构件"); return;}
	    	window.location.href="/i/component/exportComp?componentIdStrs="+parmStr;
		    	
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
	        return $scope.selected.length === $scope.components.length;
	    };
	    $scope.toggleAll = function() {
	        if ($scope.selected.length === $scope.components.length) {
	        	$scope.selected = [];
	        } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
	        	$scope.selected = $scope.components.slice(0);
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
	     
	    //点击下单
	    $scope.placeOrder = function(){
	    	   if($scope.selectedProject!=undefined && $scope.selectedProject.pro_id!=undefined) {
			   }else{
				   alert("请选择项目");
				   return;
			   }
			   if($scope.selectSingle!=undefined&&$scope.selectSingle.single_name!=undefined) {
			   }else{
				   alert("请选择单体");
				   return;
			   }			   
			   
			   //查询构件厂
			   $scope.findCompFactorys = function(){
					$http({
						method : 'GET',
						url : '/i/compfactory/findCompFactorysForOrder',
					}).success(function(data) {
						$scope.factorys = data;
					});
				}
				$scope.findCompFactorys();
			   
			   
			   $('#placeOrder').modal('show');			 
	    }
	    
	    //点击催货
	    $scope.good_expe = function(){
	    	//判断有无选择
	    	var parmStr = "";
	    	for(i in $scope.selected){
	    		parmStr += $scope.selected[i].component_id +","
	    	}
	    if(parmStr=="") {alert("请选择构件"); return;}		   			   
			   
	    $('#good_expedit').modal('show');			 
	    
	    }
	    
	    
	    //确认下单
	    $scope.addPlaceOrder=function(){
	
	    	   var pro_ida ="";
	    	   if($scope.selectedProject!=undefined && $scope.selectedProject.pro_id!=undefined) {
	    		   pro_ida=$scope.selectedProject.pro_id;
			   }
	    	   var single_namea="";
			   if($scope.selectSingle!=undefined&&$scope.selectSingle.single_name!=undefined) {
				   single_namea=$scope.selectSingle.single_name;
			   }
			   
			   var floora="";
			   if($scope.selectFloor!=undefined&&$scope.selectFloor.floor!=undefined) {
				   floora=$scope.selectFloor.floor;
			   }

			   var comp_factory_id="";
			   if($scope.selectedFactory!=undefined&&$scope.selectedFactory.comp_factory_id!=undefined) {
				   comp_factory_id=$scope.selectedFactory.comp_factory_id;
			   }
			   
	      $http({
			method : 'POST',
			url : '/i/component/placeOrder',
			params : {
				pro_id : pro_ida,
				single_name : single_namea,
				floor : floora,
				comp_factory_id:comp_factory_id,
				plan_end_date:$scope.plan_end_date,
				order_user_id:$rootScope.admin.user_id,
				order_username:$rootScope.admin.user_name
			}
		}).success(function(data) {
			alert(data.message);
			$scope.getComponents();
		});
	    }
	    
	    //批量删除
	    $scope.deleteComps = function () {
	    	//判断有无选择
	    	var parmStr = "";
	    	for(i in $scope.selected){
	    		parmStr += $scope.selected[i].component_id +","
	    	}
	    	if(parmStr=="") {alert("请选择构件"); return;}
	    	
	    	if(delcfm()){
	    	$http.get('/i/component/deleteComps', {
	            params: {
	                "component_ids": parmStr
	            }
	        }).success(function (data) {
	        	//刷新数据
	        	$scope.getComponents();
	        	//清空选择的值
	        	$scope.selected = [];
	        	alert(data.message);
	        });
	    	}
	    }
	     //催货
	     $scope.expeditGood = function () {
	    	
		    	var parmStr = "";
		    	for(i in $scope.selected){
		    		parmStr += $scope.selected[i].component_id +","
		    	}
	    	 
	    	if(isConfirm("确认催货")){
	    	$http.get('/i/component/expeditGood', {
	            params: {
	                "component_ids": parmStr,
	                "expedit_date":$scope.good_expedit_date
	            }
	        }).success(function (data) {
	        	$scope.getComponents();
	        	$scope.selected = [];
	        	alert(data.message);
	        });
	    	}
	    }
	    
	    $scope.QRcodeShow = function(component_num){
	    	$("#qrcode").empty();
	    	$('#QRcode').modal('show');
	    	$("#qrcode").qrcode({
			    render: "canvas",//二维码生成方式 table/canvas
			    text: "http://wujianbuild.com/n/"+component_num, //二维码的内容
			    width: 100,//默认生成的二维码大小是 256×256
			    height: 100
			});
	    }
	    $('#datetimepicker1').datetimepicker({  
	    	format: 'yyyy-mm-dd',
	    	autoclose:true,
	    	minView:'month'
	    }).on('dp.change', function (e) {  
	    });
	    
	    
	    $('#expedit_datetime').datetimepicker({  
	    	format: 'yyyy-mm-dd',
	    	autoclose:true,
	    	minView:'month'
	    }).on('dp.change', function (e) {  
	    });
	  
	    $scope.loadExe=function(){
	    	 if(isConfirm("是否下载读卡程序")) {
	    		 window.location.href="../../assets/file/cardRead.zip";
	    	 }
			 
		 }
	    
	     
});
