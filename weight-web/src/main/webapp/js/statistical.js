angular.module('app', ['ngFileUpload']).controller('index', function ($scope, Upload, $http,$rootScope) { 
	$scope.load = function(){
		 $.ajax({type: "GET",cache: false,dataType: 'json',url: '/i/user/findUser'}).success(function(data){
			   $rootScope.admin = data;
				if($rootScope.admin == null||$rootScope.admin ==undefined || $rootScope.admin.user_id == 0 || $rootScope.admin.user_id==undefined ){
					url = "../login.html";
					window.location.href = url;
				}
		   });
	};
	$scope.load();
	//	// 显示最大页数
//    $scope.maxSize = 12;
//    // 总条目数(默认每页十条)
//    $scope.bigTotalItems = 12;
//    // 当前页
//    $scope.bigCurrentPage = 1;
	
//	$scope.getRecords();
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart1'));
    
    var myChart2 = echarts.init(document.getElementById('echart2'));
    
    $scope.initEchart1=function(xArray, enterNum, leaveNum){
		option = {
			    //backgroundColor: '#394056',
//			    title: {
//			        text: '进出车辆',
//			        textStyle: {
//			            fontWeight: 'normal',
//			            fontSize: 16,
//			            color: '#FFFFFF'
//			        },
//			        left: '6%'
//			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            lineStyle: {
			                color: '#57617B'
			            }
			        }
			    },
			    legend: {
    		        x : 'right',
    		        y : 'top',
    		        icon: 'rect',
    		        data:['进入','离开']
    		    },
			   
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: [{
			        type: 'category',
			        boundaryGap: false,
			        axisLine: {
			            lineStyle: {
			                color: '#57617B'
			            }
			        },
			        data: xArray
			    }],
			    yAxis: [{
			        type: 'value',
			        name: '数量（辆）',
			        axisTick: {
			            show: false
			        },
			        axisLine: {
			            lineStyle: {
			                color: '#57617B'
			            }
			        },
			        axisLabel: {
			            margin: 10,
			            textStyle: {
			                fontSize: 14
			            }
			        },
			        splitLine: {
			            lineStyle: {
			                color: '#57617B'
			            }
			        }
			    }],
			    series: [{
			        name: '进入',
			        type: 'line',
			        smooth: true,
			        symbol: 'circle',
			        symbolSize: 5,
			        showSymbol: false,
			        lineStyle: {
			            normal: {
			                width: 1
			            }
			        },
			        areaStyle: {
			            normal: {
			                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                    offset: 0,
			                    color: 'rgba(137, 189, 27, 0.3)'
			                }, {
			                    offset: 0.8,
			                    color: 'rgba(137, 189, 27, 0)'
			                }], false),
			                shadowColor: 'rgba(0, 0, 0, 0.1)',
			                shadowBlur: 10
			            }
			        },
			        itemStyle: {
			            normal: {
			                color: 'rgb(137,189,27)',
			                borderColor: 'rgba(137,189,2,0.27)',
			                borderWidth: 12

			            }
			        },
			        data: enterNum
			    }, {
			        name: '离开',
			        type: 'line',
			        smooth: true,
			        symbol: 'circle',
			        symbolSize: 5,
			        showSymbol: false,
			        lineStyle: {
			            normal: {
			                width: 1
			            }
			        },
			        areaStyle: {
			            normal: {
			                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                    offset: 0,
			                    color: 'rgba(0, 136, 212, 0.3)'
			                }, {
			                    offset: 0.8,
			                    color: 'rgba(0, 136, 212, 0)'
			                }], false),
			                shadowColor: 'rgba(0, 0, 0, 0.1)',
			                shadowBlur: 10
			            }
			        },
			        itemStyle: {
			            normal: {
			                color: 'rgb(0,136,212)',
			                borderColor: 'rgba(0,136,212,0.2)',
			                borderWidth: 12

			            }
			        },
			        data: leaveNum
			    }]
			};
		// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	}
    
    $scope.getRecords = function() {
		$http({
			method : 'POST',
			url : '/i/record/numStatistics',
			params : {
				
			}
		}).success(function(res) {
			var ele = res.data;
			console.log(ele.dateRes);
			console.log(ele.entryArray);
			console.log(ele.leaveArray);
			$scope.initEchart1(ele.dateRes,ele.entryArray,ele.leaveArray);
		});
	}
    
    $scope.getRecords();
    
    
    $scope.initEchart2=function(xArray, chartData1, chartData2){
    	option = {
    		    title : {
    		        text: '车辆进出统计',
    		        subtext: '暂时',
    		        left: '6%'
    		    },
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    legend: {
    		        x : 'right',
    		        y : 'top',
    		        top: '5%',
    		        data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
    		    },
//    		    toolbox: {
//    		        show : true,
//    		        feature : {
//    		            mark : {show: true},
//    		            dataView : {show: true, readOnly: false},
//    		            magicType : {
//    		                show: true,
//    		                type: ['pie', 'funnel']
//    		            },
//    		            restore : {show: true},
//    		            saveAsImage : {show: true}
//    		        }
//    		    },
    		    calculable : true,
    		    series : [
    		        {
    		            name:'半径模式',
    		            type:'pie',
    		            radius : [20, 110],
    		            center : ['25%', '50%'],
    		            roseType : 'radius',
    		            label: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            lableLine: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            data:[
    		                {value:10, name:'rose1'},
    		                {value:5, name:'rose2'},
    		                {value:15, name:'rose3'},
    		                {value:25, name:'rose4'},
    		                {value:20, name:'rose5'},
    		                {value:35, name:'rose6'},
    		                {value:30, name:'rose7'},
    		                {value:30, name:'rose8'}
    		            ]
    		        },
    		        {
    		            name:'面积模式',
    		            type:'pie',
    		            radius : [30, 110],
    		            center : ['75%', '50%'],
    		            roseType : 'area',
    		            data:[
    		                {value:10, name:'rose1'},
    		                {value:5, name:'rose2'},
    		                {value:15, name:'rose3'},
    		                {value:25, name:'rose4'},
    		                {value:20, name:'rose5'},
    		                {value:35, name:'rose6'},
    		                {value:30, name:'rose7'},
    		                {value:40, name:'rose8'}
    		            ]
    		        }
    		    ]
    		};
		// 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option);
	}
    $scope.initEchart2();
});
