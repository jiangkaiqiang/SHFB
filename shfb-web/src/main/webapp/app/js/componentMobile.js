angular.module('app', ['ngFileUpload']).controller('index', function ($scope, Upload, $http) { 
	 
	
	 $.ajax({

	        type: 'POST',

	        url: '/i/componentMake/getComInfo' ,

	       data: {comonentNum:comonentNumStr} ,

	       dataType: 'json',
	       
	       success: function(data) {
	    	   $("#pro_name").text(data.pro_name);
	    	   $("#component_type").text(data.component_type);
	    	   $("#component_size").text(data.component_size);
	    	   $("#single_name").text(data.single_name);
	    	   $("#component_num").text(data.component_num);
	    	   $("#floor").text(data.floor);
	    	   $("#concrete_strength").text(data.concrete_strength);
	    	   $("#weight").text(data.weight);
	    	   $("#comp_factory_name").text(data.comp_factory_name);
	    	   $("#contacts_name").text(data.contacts_name);
	    	   $("#contacts_tel").text(data.contacts_tel);
	    	   $("#plan_end_date").text(data.plan_end_date);
	    	   $("#drawing").attr("src",data.drawing);
	    	   $scope.showShengchaYanshou(data.component_id);
	    	   $scope.showReceiveMod(data.component_id);
	       }
	       

	   });
	
	
	
	
	
	
	
	$scope.showShengchaYanshou=function(compId){
	    	
    	 $http.get('/i/productionAcceptance/findProductComponentSizeByKey', {
	            params: {
	                "component_id": compId
	            }
	        }).success(function (data) {
	        	$scope.productComponentSize=data.data;
	        	
	        });
    }
	$scope.showReceiveMod=function(compId){
    	
   	 $http.get('/i/receiptAcceptance/findReceiptComponentSizeByKey', {
	            params: {
	                "component_id": compId
	            }
	        }).success(function (data) {
	        	$scope.receiptComponentSize=data.data;
	        	
	        });
   }
	
});
