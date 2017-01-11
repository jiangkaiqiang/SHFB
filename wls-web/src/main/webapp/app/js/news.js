wlsWeb.controller('news',function($http, $location, $state,$scope,$interval) {
	 /*var date=new Date();
     var h=date.getHours();
     var m=date.getMinutes();
     var s=date.getSeconds();
     if(h==12&&m==0&&s==0){
          callFunction();
     }*/
	//定时任务插入爬取数据到数据库
	$scope.chuangyebang = $interval(function(){
		 var date=new Date();
	     var h=date.getHours();
	     var m=date.getMinutes();
	     var s=date.getSeconds();
	     if(h==23&&m==39&&s==0){
		   $http.get('/i/information/addInformationWithChuangyebang').success(function (data) {
	    });
	   }
	}, 1000);
	$scope.iresearch = $interval(function(){
		 var date=new Date();
	     var h=date.getHours();
	     var m=date.getMinutes();
	     var s=date.getSeconds();
	     if(h==23&&m==54&&s==0){
		 $http.get('/i/information/addInformationWithIresearch').success(function (data) {
	    });
	    }
	}, 1000);
	$scope.yiouKeJi = $interval(function(){
		 var date=new Date();
	     var h=date.getHours();
	     var m=date.getMinutes();
	     var s=date.getSeconds();
	     if(h==23&&m==57&&s==0){
		$http.get('/i/information/addInformationWithYiouKeJi').success(function (data) {
	    });
	     }
	}, 1000);
	$scope.yiouWenChuang = $interval(function(){
		 var date=new Date();
	     var h=date.getHours();
	     var m=date.getMinutes();
	     var s=date.getSeconds();
	     if(h==23&&m==48&&s==0){
		$http.get('/i/information/addInformationWithYiouWenChuang').success(function (data) {
	    });
	     }
	}, 1000);
	$scope.cXiaoYuanPsy = $interval(function(){
		 var date=new Date();
	     var h=date.getHours();
	     var m=date.getMinutes();
	     var s=date.getSeconds();
	     if(h==23&&m==51&&s==0){
		  $http.get('/i/information/addInformationWithCXiaoYuanPsy').success(function (data) {
	    });
	    }
	}, 1000);
	// 显示最大页数
    $scope.maxSize = 10;
    // 总条目数(默认每页十条)
    $scope.bigTotalItems = 10;
    // 当前页
    $scope.bigCurrentPage = 1;
	$scope.AllNews = [];
	$scope.optAudit = 8;
	$scope.AllCategory = [
	                      {id:"8",name:"全部"},
	                      {id:"1",name:"科技类"},
	                      {id:"2",name:"文娱类"},
	                      {id:"3",name:"创业类"},
	                      {id:"4",name:"时事类"},
	                      {id:"5",name:"校园类"}
	                      
	];
	$scope.AllSize = [
	                      {id:10,value:"10"},
	                      {id:20,value:"20"},
	                      {id:30,value:"30"},
	                      {id:40,value:"40"},
	                      {id:50,value:"50"}
	];
	 // 获取当前news的列表
    $scope.getNews = function() {
		$http({
			method : 'POST',
			url : '/i/information/findAllInformation',
			params : {
				pageNum : $scope.bigCurrentPage,
				pageSize : $scope.maxSize,
				audit : $scope.optAudit,
				keyword : encodeURI($scope.keyword,"UTF-8"),
			}
		}).success(function(data) {
			$scope.bigTotalItems = data.size;
			$scope.numPages = data.pages;
			$scope.AllNews = data.list;
		});
	};
    
	$scope.firstPage = function() {
		$scope.bigCurrentPage = 1;
		$scope.getNews();
	};
	
	$scope.endPage = function() {
		$scope.bigCurrentPage = $scope.numPages;
		$scope.getNews();
	};
	
	$scope.pagedes = function() {
		if($scope.bigCurrentPage>1){
			$scope.bigCurrentPage = $scope.bigCurrentPage-1;
			$scope.getNews();
		}
		   
	};
	
	$scope.pageadd = function() {
		if($scope.bigCurrentPage<$scope.numPages){
			$scope.bigCurrentPage = $scope.bigCurrentPage+1;
			 $scope.getNews();
		}
		  
	};
	
	$scope.getNews();

	$scope.auditChanged = function(optAudit) {
		$scope.optAudit = optAudit;
		$scope.getNews();
	};
    
	$scope.goSearch = function () {
		$scope.getNews();
    };
    
    $scope.showNewsNum = function (num) {
    	$scope.maxSize = num;
		$scope.getNews();
    };
    
    $scope.goNewsInfo = function(newID) {
   	 $state.go('news-info', {"newID": newID});
	};
});
