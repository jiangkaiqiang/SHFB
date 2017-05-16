wlsWeb.controller('my-space-company',function($http, $location,$rootScope, $scope, $state,Upload) {
    $scope.load = function(){
    	$http.get('/i/user/findUser').success(function (data) {
			if(data!=null&&data.id!=undefined){
				 $scope.user = data;
				 if($scope.user.sex==0){
						$scope.user.sex = "男";
					}
					else{
						$scope.user.sex = "女";
					}
					if($scope.user.score<100){
						$scope.level = 1;
					}
					else if($scope.user.score<500&&$scope.user.score>=100){
						$scope.level = 2;
					}
					else if($scope.user.score>=500){
						$scope.level = 3;
					}
					else{
						$scope.level = 1;
					}
					$scope.findMessageByReceiverId();
					$http.get('/i/user/findFollowerByUserId', {
			            params: {
			                "userID": $scope.user.id
			            }
			        }).success(function (data) { 
			        	$scope.followers = data;
			        	$scope.followerNum = $scope.followers.length;
			     });
					
					 $scope.publishNum = 0;
					 $http.get('/i/publish/findPublishByUserId', {
				            params: {
				                "userID": $scope.user.id
				            }
				        }).success(function (data) { 
				        	if(data!=null){
				        	$scope.publishs = data;
				        	$scope.publishNum = $scope.publishs.length;
				        	}
				     });
				 }
				else{
					alert("请先登录");
					window.location.href = "#/login";
				}
    	 });
	   };
	$scope.load();
	$scope.findMessageByReceiverId = function(){
		 $http.get('/i/message/findMessageByReceiverId', {
	            params: {
	                "userID": $scope.user.id
	            }
	        }).success(function (data) { 
	        	$scope.messages = data;
	        	$scope.messageNum = $scope.messages.length;
	     });
	 };
	$scope.logout = function() {
		   $http.get( "/i/user/logout").success(function(data){
			   window.location.reload();
		   });
	  };
	  
    function delcfm() {
	        if (!confirm("确认要删除？")) {
	            return false;
	        }
	        return true;
	}
	$scope.saveInfo = function() {
		if($scope.user.sex=="男"){
			$scope.sex = 0;
		}
		else{
			$scope.sex = 1;
		}
	    	$http.get( "/i/user/updateUser",{
	    		params : {
	    			nickname : $scope.user.nickname,
	    			telephone : $scope.user.telephone,
	    			position: $scope.user.position,
	    			company : $scope.user.company,
	    			address : $scope.user.address,
	    			phone : $scope.user.phone,
	    			email : $scope.user.email,
	    			signature : $scope.user.signature,
	    			sex : $scope.sex
	    		}
	    	}).success(function(data) {
	    		if(data.success){
	    			$state.reload();
	    		}
	    		else{
	    			alert("信息修改失败");
	    		}
		   });
	   };
	   
	   $scope.updateAvatar = function() {
		   var useravatar = $('#useravatar').get(0).files[0];
		   if(useravatar==null||useravatar==undefined){
			   alert("请选择图片");
			   return;
		   }
		   data = {
				   useravatar: useravatar
	            };
		   Upload.upload({
               url: '/i/user/updateAvatar',
               headers :{ 'Content-Transfer-Encoding': 'utf-8' },
               data: data
           }).then(function (resp) {
        	   window.location.reload();
           });
       };
       
       
       $scope.updatePhoto = function() {
		   var userphoto = $('#userphoto').get(0).files[0];
		   if(userphoto==null||userphoto==undefined){
			   alert("请选择图片");
			   return;
		   }
		   data = {
				   userphoto: userphoto
	            };
		   Upload.upload({
               url: '/i/user/updatePhoto',
               headers :{ 'Content-Transfer-Encoding': 'utf-8' },
               data: data
           }).then(function (resp) {
        	   $state.reload();
           });
       };
	   
	   $scope.deleteMessage  = function(messageID) {
		   if(delcfm()){
	    	$http.get( "/i/message/deleteMessage",{
	    		params : {
	    			msgID : messageID,
	    			messageSenderID : $scope.user.id
	    			}
	    	}).success(function(data) {
	    		//alert("删除成功");
	    		$scope.findMessageByReceiverId();
		   });
		   }
	   };
	   
	   $scope.messageResponse  = function(messageID,messageReceiverID,msgcategory
			   ,resopnseMsg) {
	    	$http.get( "/i/message/messageResponse",{
	    		params : {
	    			messageID : messageID,
	    			messageReceiverID : messageReceiverID,
	    			messageResponse : resopnseMsg,
	    			msgcategory : msgcategory,
	    			messageSenderID : $scope.user.id
	    		}
	    	}).success(function(data) {
	    		$("#quick_reply_alter_com").modal("show");
	    		$scope.findMessageByReceiverId();
		   });
	   };
	   $scope.goUserSpace = function(userID) {
		   $http.get('/i/user/findUserByID', {
	            params: {
	                "spaceUserID": userID
	            }
	        }).success(function(data){
				 $scope.user = data;
				 if($scope.user.suproleid==1){
					 $state.go('my-space-ask', {"spaceID": userID});
				 }
				 else if($scope.user.suproleid==2){
					 $state.go('my-space-company-ask', {"spaceID": userID});
				 }
				 else{
					 alert("用户不存在！！");
				 }
   	 });
		};
		
		 $scope.deletePublish  = function(publishID) {
			   if(delcfm()){
		    	$http.get( "/i/publish/deletePublish",{
		    		params : {
		    			publishID : publishID,
		    			userID : $scope.user.id
		    			}
		    	}).success(function(data) {
		    		//alert("删除成功");
		    		$scope.publishs = data;
		        	$scope.publishNum = $scope.publishs.length;
			   });
			   }
		   };
		   $scope.goBlogInfo = function(publishID) {
		      	 $state.go('blog-info', {"publishID": publishID});
		   	};
		   	
			$scope.schoolid = -1;
			$('body')
			 .on('click', '#optionsRadios1', function(e) {
				    $scope.schoolid = -1;
			        $("#select_school").css("display","none");
			    });
			 $('body')
			    .on('click', '#optionsRadios2', function(e) {
			        $("#select_school").css("display","");
			    });
			 
		  	$scope.updatePublish  = function(publishID) {
		   		$("#tab_4list").css("display","none");
		   		$("#tab-4update").css("display","");
		   	    $http.get('/i/publish/findPublishByID', {
	                params: {
	                    "publishID": publishID
	                }
	            }).success(function(data,status,config,headers){
	                $scope.publish = data;
	                /*$scope.totalPicFiles = $scope.publish.picFiles;
	                $scope.totalAppendixs = $scope.publish.appendixDtos;
	                $scope.totalVideoFiles = $scope.publish.pubVideo;*/
	                $scope.title = $scope.publish.title;
	                $scope.schoolid = $scope.publish.schoolid;
	                $scope.content = $scope.publish.content;
	                if($scope.schoolid!=-1&&$scope.schoolid!=null&&$scope.schoolid!=undefined){
	                	 $("input[name='optionsRadios1']").eq(1).attr("checked","checked");
	                	 $("#select_school").css("display","");
	                	 $http.get('/i/city/findSchoolById', {
	         	            params: {
	         	                "schoolID": $scope.schoolid
	         	            }
	         	        }).success(function(data){
	         				 $scope.defaultschool = data;
	         				$scope.schoolname=$scope.defaultschool.sh_shool; 
	            	    });
	                }
	                else{
	                	 $("input[name='optionsRadios1']").eq(0).attr("checked","checked");
	                }
	                $("input[name='optionsRadios3']").eq($scope.publish.pubcategory-1).attr("checked","checked");
	                document.getElementById("contentdetail").innerHTML=$scope.publish.content;
	             });
		    };
		    
		    $scope.searchSchool = function(schoolname){
		    	if(schoolname==''){
		    		$("#schoolUl").css("display","none");
		    	}
		    	else{
		    		 $http.get('/i/city/findSchoolByName', {
		    	            params: {
		    	                "schoolName": schoolname
		    	            }
		    	        }).success(function (data) {
		     	    	$scope.totalSchools = data;
		     	    	$("#schoolUl").css("display","");
		     	    });
		    	}
		    };
		    $scope.chooseSchool = function(school){
		    	$scope.schoolname = school.sh_shool;
		    	$("#schoolUl").css("display","none");
		    	$scope.schoolid = school.sh_id;
		    };
		    
		   
		    
		    $scope.addPicFiles = function () {
		    	if($scope.totalPicFiles==undefined){
		    		$scope.totalPicFiles = [];
		    	}
				if($scope.picfiles.length==0){return;};
				var allfiles = $scope.totalPicFiles.concat($scope.picfiles);
				if(allfiles.length>6){alert("最多选择6张！");return;}
		        $scope.totalPicFiles=allfiles; 
		    };
		    $scope.dropPicFile = function(picFile){
		        angular.forEach($scope.totalPicFiles,function(item, key){
		            if(item == picFile){
		                $scope.totalPicFiles.splice(key,1); return false;
		            }
		        });
		    };
		    $scope.addAppendixs = function () {
		    	if($scope.totalAppendixs==undefined){
		    	 $scope.totalAppendixs = [];
		    	}
				if($scope.appendixs.length==0){return;};
				var allfiles = $scope.totalAppendixs.concat($scope.appendixs);
				if(allfiles.length>3){alert("最多选择3个！");return;}
		        $scope.totalAppendixs=allfiles; 
		    };
		    $scope.dropAppendixFile = function(file){
		        angular.forEach($scope.totalAppendixs,function(item, key){
		            if(item == file){
		                $scope.totalAppendixs.splice(key,1); return false;
		            }
		        });
		    };
		    
		    $scope.addVideoFiles = function () {
		    	if($scope.totalVideoFiles==undefined){
		    	$scope.totalVideoFiles = [];
		    	}
				if($scope.videofiles.length==0){return;};
				var allfiles = $scope.totalVideoFiles.concat($scope.videofiles);
				if(allfiles.length>2){alert("最多选择2个！");return;}
		        $scope.totalVideoFiles=allfiles; 
		    };
		    $scope.dropVideoFile = function(file){
		        angular.forEach($scope.totalVideoFiles,function(item, key){
		            if(item == file){
		                $scope.totalVideoFiles.splice(key,1); return false;
		            }
		        });
		    };
		    
		    function checkInput() {
				var flag = true;
				// 检查必须填写项
				if ($scope.title == undefined || $scope.title == '') {
					flag = false;
				}
				if ($scope.content == undefined || $scope.content == '') {
					flag = false;
				}
				return flag;
			}
		    
		    $scope.confirmUpdatePub  = function() {
		    	if (checkInput()) {
					   data = {
							    'title' : $scope.title,
				            	'describe' : $scope.describe,
								'pubcategory' : $('input[name="optionsRadios3"]:checked').val(),
								//'publisher' : $rootScope.user.id,
								'publisher' : $rootScope.user.id,
								'content' : $scope.content,
								'schoolid' : $scope.schoolid,
								'publishid' : $scope.publish.id
				            };
					        if($scope.totalPicFiles!=undefined)
				            for(var i = 0; i < $scope.totalPicFiles.length; i++){
				                data["picFile" + i] = $scope.totalPicFiles[i];
				            }
					        if($scope.totalAppendixs!=undefined)
				            for(var i = 0; i < $scope.totalAppendixs.length; i++){
				                data["appendix" + i] = $scope.totalAppendixs[i];
				            }
					        if($scope.totalVideoFiles!=undefined)
				            for(var i = 0; i < $scope.totalVideoFiles.length; i++){
				                data["videoFile" + i] = $scope.totalVideoFiles[i];
				            }
				       Upload.upload({
				                url: '/i/publish/addPublish',
				                headers :{ 'Content-Transfer-Encoding': 'utf-8' },
				                data: data
				            }).success(function (data) {
			            if(data.success){
			            	alert("修改成功");
			            	$state.reload();
			            }
			        });
				} else {
					alert("标题和内容不允许为空!");
				}
		    };
		    
		    $scope.cancelUpdate  = function() {
		    	$("#tab-4update").css("display","none");
		   		$("#tab_4list").css("display","");
		    };
		   	
		 $scope.responseMessage = function(messageID){
	            $("#reply_area-"+messageID).css("display","");
	    };
});
