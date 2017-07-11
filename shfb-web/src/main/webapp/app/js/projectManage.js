coldWeb.controller('projectManage', function ($rootScope, $scope, $state, $cookies, $http, $location, Upload) {
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
				userProjectID : $rootScope.admin.pro_id,
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
    		projectIDs.push($scope.selected[i].project.pro_id);
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
    

    $scope.submit = function(){
    	 if ($scope.proname == undefined || $scope.proname == '') {
             alert("请添加项目名称！");
             return; 
          }
          if ($scope.contactName == undefined || $scope.contactName == '') {
          	alert("请添加联系人！");
              return; 
          }
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
		
		 $scope.update = function(){
			 if ($scope.projectUpdate.pro_name == undefined || $scope.projectUpdate.pro_name == '') {
	        	 alert("请添加项目名称！");
	             return; 
	        }
	        if ($scope.projectUpdate.contacts_name == undefined || $scope.projectUpdate.contacts_name == '') {
	        	alert("请添加联系人！");
	              return; 
	        }
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
		    }
		 
		 $scope.importComponent=function(){
			 var projectIDs = [];
		    	for(i in $scope.selected){
		    		projectIDs.push($scope.selected[i].project.pro_id);
		    	}
		    	if(projectIDs.length != 1 ){
		    		alert("请选择一个项目");		    		
		    	}else{
		    		$('#importComp').modal('show')
		    		
		    	}
		 }
		 
		 $scope.loadTem=function(){
			 window.location.href="../../assets/file/componentTemplate.xls";
		 }
		 
		 $scope.importComp=function(){
			 showLoad("正在加载，请稍后......");
			 $http({
					url: '/i/project/fileUpload',
					method: 'POST',
					headers: {
						'Content-Type': undefined
					},
					transformRequest: function() {
						var formData = new FormData();
						var projectIDs = [];
				    	for(i in $scope.selected){
				    		projectIDs.push($scope.selected[i].project.pro_id);
				    	}
						formData.append('file', $('#upfile')[0].files[0]);
						formData.append('pro_id',projectIDs[0]);
						return formData;
					}
				}).success(function (data) {
					closeLoad();
					alert(data.message);   //返回上传后所在的路径
				});
		 }
		 
		 $scope.totalPicFiles = [];
		 $scope.addPicFiles = function () {
				if($scope.picfiles.length==0){return;};
				var totalsize = 0;
				for(var i = 0; i < $scope.picfiles.length; i++){
					totalsize += $scope.picfiles[i].size;
				}
				if(totalsize>1024*1024*5){
					alert("上传的文件总大小不能超过5M");
					return;
				}
				for(var i = 0; i < $scope.picfiles.length; i++){
					var fileName = $scope.picfiles[i].name;
					var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
				    var suppotFile = new Array();
				    suppotFile[0] = "png";
				    suppotFile[1] = "jpg";
				    if(suppotFile[0]!=fileType&&suppotFile[1]!=fileType){
				    	alert("请上传png或jpg格式的图片！");
				    	return;
				     };
				}
				var allfiles = $scope.totalPicFiles.concat($scope.picfiles);
		        $scope.totalPicFiles=allfiles; 
		    };
		    $scope.importPic = function() {
		    	showLoad("正在加载，请稍后......");
				       /*Upload.upload({
				                url: '/i/project/importPic',
				                headers :{ 'Content-Transfer-Encoding': 'utf-8' },
				                data: { files:  $scope.totalPicFiles}
				            }).success(function (data) {
			            if(data.success){
			            	alert("上传成功");
			            	window.location.href="#/post-bar";
			            }
			        });*/
		    	$http({
					url: '/i/project/importPic',
					method: 'POST',
					headers: {
						'Content-Type': undefined
					},
					transformRequest: function() {
						var formData = new FormData();
						for(var i = 0; i < $scope.totalPicFiles.length; i++){
							formData.append('files',$scope.totalPicFiles[i]);
			            }
						return formData;
					}
				}).success(function (data) {
					closeLoad();
					alert(data.message);   //返回上传后所在的路径
				});
			};
			//loading  
			function showLoad(tipInfo) {  
			    var iWidth = 120;     //弹出窗口的宽度;  
			    var iHeight = 0;    //弹出窗口的高度;  
			    var scrolltop = 0;  
			    var scrollleft = 0;  
			    var cheight = 0;  
			    var cwidth = 0;  
			    var eTip = document.createElement('div');  
			    eTip.setAttribute('id', 'tipDiv');  
			    eTip.style.position = 'absolute';  
			    eTip.style.display = 'none';  
			    eTip.style.border = 'solid 0px #D1D1D1';  
			    eTip.style.backgroundColor = '#4B981D';  
			    eTip.style.padding = '5px 15px';  
			  
			    if(document.body.scrollTop){//这是一个js的兼容  
			        scrollleft=document.body.scrollLeft;  
			        scrolltop=document.body.scrollTop;  
			        cheight=document.body.clientHeight;  
			        cwidth=document.body.clientWidth;  
			    }  
			    else{  
			        scrollleft=document.documentElement.scrollLeft;  
			        scrolltop=document.documentElement.scrollTop;  
			        cheight=document.documentElement.clientHeight;  
			        cwidth=document.documentElement.clientWidth;  
			    }  
			    iHeight = eTip.offsetHeight;  
			    var v_left=(cwidth-iWidth)/2 + scrollleft; //  
			    var v_top=(cheight-iHeight)/2+ scrolltop;  
			    eTip.style.left = v_left + 'px';  
			    eTip.style.top = v_top + 'px';  
			  
			    eTip.innerHTML = '<span style=\'color:#ffffff; font-size:18px\'>' + tipInfo + '</span>';  
			    try {  
			        document.body.appendChild(eTip);  
			    } catch (e) { }  
			    $("#tipDiv").css("float", "right");  
			    $("#tipDiv").css("z-index", "99");  
			    $('#tipDiv').show();  
			    ShowMark();  
			}  
			  
			function closeLoad() {  
			    $('#tipDiv').hide();  
			    HideMark();  
			}  
			  
			  
			//显示蒙灰层  
			function ShowMark() {  
			    var xp_mark = document.getElementById("xp_mark");  
			    if (xp_mark != null) {  
			        //设置DIV  
			        xp_mark.style.left = 0 + "px";  
			        xp_mark.style.top = 0 + "px";  
			        xp_mark.style.position = "absolute";  
			        xp_mark.style.backgroundColor = "#000";  
			        xp_mark.style.zIndex = "1";  
			        if (document.all) {  
			            xp_mark.style.filter = "alpha(opacity=50)";  
			            var Ie_ver = navigator["appVersion"].substr(22, 1);  
			            if (Ie_ver == 6 || Ie_ver == 5) { hideSelectBoxes(); }  
			        }  
			        else { xp_mark.style.opacity = "0.5"; }  
			        xp_mark.style.width = "100%";  
			        xp_mark.style.height = "100%";  
			        xp_mark.style.display = "block";  
			    }  
			    else {  
			        //页面添加div explainDiv,注意必须是紧跟body 内的第一个元素.否则IE6不正常.  
			        $("body").prepend("<div id='xp_mark' style='display:none;'></div>");  
			        ShowMark(); //继续回调自己  
			    }  
			}  
			  
			//隐藏蒙灰层  
			function HideMark() {  
			    var xp_mark = document.getElementById("xp_mark");  
			    xp_mark.style.display = "none";  
			    var Ie_ver = navigator["appVersion"].substr(22, 1);  
			    if (Ie_ver == 6 || Ie_ver == 5) { showSelectBoxes(); }  
			}  
});
