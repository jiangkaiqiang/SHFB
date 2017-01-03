wlsWeb.controller('blog-info',function($http, $rootScope,$location,$state, $stateParams, $scope) {
    $scope.load = function(){
           $scope.publishID = $stateParams.publishID;
            $http.get('/i/publish/findPublishByID', {
                params: {
                    "publishID": $scope.publishID
                }
            }).success(function(data,status,config,headers){
                   $scope.publish = data;
                   document.getElementById("pubContent").innerHTML=$scope.publish.content;
               });
           };
        $scope.load();
        $scope.blogComment = function(publishID){
            if($rootScope.user==null||$rootScope.user.id==undefined){
                alert("请先登录!");
                return;
            }
            if($scope.commentDetail==undefined||$scope.commentDetail==""){
                alert("请输入评论内容");
                return;
            }
            $http.get('/i/comment/insertComment', {
                params: {
                    "userID": $rootScope.user.id,
                    "commentDetail" : $scope.commentDetail,
                    "commentid" : publishID,
                    "flag" : 1
                }
            }).success(function(data){
            	$("#comment_alter").modal("show");
                $scope.load();    
          });
        };
         $scope.responseFirst = function(commentID,firstResponse){
                if($rootScope.user==null||$rootScope.user.id==undefined){
                    alert("请先登录!");
                    return;
                }
                if(firstResponse==undefined||firstResponse==""){
                    alert("请输入回复内容");
                    return;
                }
                $http.get('/i/response/insertResponse', {
                    params: {
                        "userID": $rootScope.user.id,
                        "responseDetail" : firstResponse,
                        "responseid" : commentID,
                        "flag" : 0
                    }
                }).success(function(data){
                	$("#reply_alter").modal("show");
                    $scope.load();   
              });
            };
            
            $scope.responseSecond = function(usernickname,commentID,secondResponse){
                if($rootScope.user==null||$rootScope.user.id==undefined){
                    alert("请先登录!");
                    return;
                }
                if(secondResponse==undefined||secondResponse==""){
                    alert("请输入回复内容");
                    return;
                }
                $http.get('/i/response/insertResponse', {
                    params: {
                        "userID": $rootScope.user.id,
                        "responseDetail" : "@"+usernickname + "  "+ secondResponse,
                        "responseid" : commentID,
                        "flag" : 0
                    }
                }).success(function(data){
                	$("#reply_alter").modal("show");
                    $scope.load();   
              });
            };
            
            $scope.responseComment = function(commentID){
                $("#reply_area-"+commentID).css("display","");
                $("#reply_area_second-"+commentID).css("display","none");
            };
            $scope.openReple = function(commentID){
                $("#open_reple-"+commentID).css("display","none");
                $("#close_reple-"+commentID).css("display","");
                $("#comment_second-"+commentID).css("display","");
                $("#reply_area-"+commentID).css("display","none");
            };
            $scope.closeReple = function(commentID){
                $("#close_reple-"+commentID).css("display","none");
                $("#open_reple-"+commentID).css("display","");
                $("#comment_second-"+commentID).css("display","none");
                $("#reply_area_second-"+commentID).css("display","none");
            };
            $scope.responseCommentSecond = function(responseID,commentID){
				/*alert(responseID+"   commentID="+commentID);*/
                $("#reply_area_second-"+responseID).css("display","");
                $("#reply_area-"+commentID).css("display","none");
            };
});
