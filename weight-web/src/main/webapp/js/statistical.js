angular.module('app', ['ngFileUpload']).controller('index', function ($scope, Upload, $http) { 
//	// 显示最大页数
//    $scope.maxSize = 12;
//    // 总条目数(默认每页十条)
//    $scope.bigTotalItems = 12;
//    // 当前页
//    $scope.bigCurrentPage = 1;
	
//	$scope.getRecords();
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart1'));
    
    $scope.initEchart1=function(xArray, enterNum, leaveNum){
		option = {
			    backgroundColor: '#394056',
			    title: {
			        text: '进出车辆',
			        textStyle: {
			            fontWeight: 'normal',
			            fontSize: 16,
			            color: '#F1F1F3'
			        },
			        left: '6%'
			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            lineStyle: {
			                color: '#57617B'
			            }
			        }
			    },
			    legend: {
			        icon: 'rect',
			        itemWidth: 14,
			        itemHeight: 5,
			        itemGap: 13,
			        data: ['进入', '离开'],
			        right: '4%',
			        textStyle: {
			            fontSize: 12,
			            color: '#F1F1F3'
			        }
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
});
