wlsWeb.controller('post-message',function($http, $location,$rootScope, $state,$scope) {
	// 获取学校列表
    $http.get('/i/city/findSchoolList').success(function (data) {
    	$scope.schools = data;
        var sh = {"sh_id":-1,"sh_shool":"全部学校"};
    	 $scope.schools.push(sh);
    	$scope.schools = sortJson($scope.schools,"sh_id");
    	 $scope.schoolid = -1;
    });
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
    $scope.pubSubmit = function() {
		//alert(UE.getEditor('container').getContent());
		$scope.content = UE.getEditor('container').getContent();
		if (checkInput()) {
			$http.get('/i/publish/addPublish', {
	            params: {
	            	'title' : $scope.title,
	            	'describe' : $scope.describe,
					'pubcategory' : $('input[name="optionsRadios2"]:checked').val(),
					//'publisher' : $rootScope.user.id,
					'publisher' : 4,
					'content' : $scope.content,
					'schoolid' : $scope.schoolid
	            }
	        }).success(function (data) {
	            if(data.success){
	            	alert("发布成功");
	            	$state.reload();
	            }
	        });
		} else {
			alert("标题和内容不允许为空!");
		}
	};
});
