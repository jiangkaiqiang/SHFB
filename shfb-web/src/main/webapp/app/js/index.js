coldWeb.controller('index', function ($scope, $state, $cookies, $http, $location) {
	   $scope.goMenu = function (name,url) {
		   /* angular.element(name).addClass('highlight');*/
	   		window.location.href=url;
	   	};
	$(function() {
	//  侧栏下拉菜单暂时隐藏
	var liID = window.location.hash.substring(2, window.location.hash.length);
	$("#"+liID).addClass('highlight').siblings().removeClass('highlight');
	$(".list li").click(function(event){
		/*$(".list li").attr('value','');
		var i = $(this).index();
		var urlArry = ['#/home','#/componentManage','#/userManage','#/projectManage',
		               '#/compFactoryManage','#/productManage','#/personalSpace','#/'];
		$(this).attr('value', urlArry[i]);*/
		$(this).addClass('highlight').siblings().removeClass('highlight');
		$(this).addClass("highlight")			//为当前元素增加highlight类
			.children(".liSon").slideDown().end()			//将子节点的ul元素显示出来并重新定位到上次操作的元素
		.siblings().removeClass("highlight")		//获取元素的兄弟元素，并去掉他们的highlight类
			.children(".liSon").slideUp();				//将兄弟元素下的ul元素隐藏
	});
});
});
