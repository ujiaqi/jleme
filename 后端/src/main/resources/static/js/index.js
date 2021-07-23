//pm2.5柱状图
(function () {
  var option = {
    color: ['#3398DB'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      }
    },
    grid: {
      left: '0%',
      right: '0%',
      bottom: '5%',
      top: '3%',
      containLabel: true
    },
    xAxis:{
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
        axisTick: {
          alignWithLabel: true
        },
        axisLabel: {
          color: "rgba(255,255,255,.6)",
          fontSize: 12,
          formatter: function(value) {
            if (value.length > 12) {
              return value.substring(0, 7) + "...";
            } else {
              return value;
            }
          }
        },
        axisLine: {
          show: false
        }
      },
    yAxis:{
        type: 'value',
        axisLabel: {
          color: "rgba(255,255,255,.6)",
          fontSize: 12
        },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        },
        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        }
      },
    series:{
        name: '直接访问',
        type: 'bar',
        barWidth: "35%",
        data: [10, 52, 200, 334, 390]
      }
    };
  var myChart = echarts.init(document.querySelector('.bar1 .chart'));
  function getItem(mychart) {
    $.get("https://fishei.cn/competition/pm25", function (data) {

    //console.log(data) //返回对象
    //console.log(data["firstData"]["dataPoints"]) 
    for(var i=0; i<data["firstData"]["dataPoints"].length;i++)
    {
      var detail=data["firstData"]["dataPoints"][i];
      let time = detail["time"].split(" ")
      option.xAxis.data[i]= time[1];
      option.series.data[i]= detail["value"];//获取series的第一行数据，并将模拟的数据赋值给他
    }
  })
    myChart.setOption(option);//重新加载表
  }
  setInterval(getItem, 1000);  //每间隔1s请求一次函数
  //图表自适应
  window.addEventListener('resize', function () {
    myChart.resize();
  })
})();
//噪声柱状图
(function () {
  // 基于准备好的dom，初始化echarts实例

  var data = [70, 34, 60, 78, 69];
  var titlename = ["时段一", "时段二", "时段三", "时段四", "时段五"];
  var valdata = [70, 34, 60, 78, 69];
  var myColor = ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
  var option = {
    //图标位置
    grid: {
      top: "10%",
      left: "22%",
      bottom: "20%"
    },
    xAxis: {
      show: false
    },
    yAxis: [
      {
        show: true,
        data: titlename,
        inverse: true,
        axisLine: {
          show: false
        },
        splitLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          color: "#fff",

          rich: {
            lg: {
              backgroundColor: "#339911",
              color: "#fff",
              borderRadius: 15,
              align: "center",
              width: 15,
              height: 15
            }
          }
        }
      },
      {
        show: true,
        inverse: true,
        data: valdata,
        axisLabel: {
          textStyle: {
            fontSize: 12,
            color: "#fff"
          }
        }
      }
    ],
    series: [
      {
        name: "条",
        type: "bar",
        yAxisIndex: 0,
        data: data,
        barCategoryGap: 50,
        barWidth: 10,
        itemStyle: {
          normal: {
            barBorderRadius: 20,
            color: function (params) {
              var num = myColor.length;
              return myColor[params.dataIndex % num];
            }
          }
        },
      },
      {
        name: "框",
        type: "bar",
        yAxisIndex: 1,
        barCategoryGap: 50,
        data: [100, 100, 100, 100, 100],
        barWidth: 15,
        itemStyle: {
          normal: {
            color: "none",
            borderColor: "#00c1de",
            borderWidth: 3,
            barBorderRadius: 15
          }
        }
      }
    ]
  };
  var myChart = echarts.init(document.querySelector(".bar2 .chart"));
  // 使用刚指定的配置项和数据显示图表。
  function getItem(mychart) {
    $.get("https://fishei.cn/competition/noise", function (data) {

    //console.log(data) //返回对象
    //console.log(data["firstData"]["dataPoints"]) //噪声类型

    for(var i=0; i<data["firstData"]["dataPoints"].length;i++)
    {
      var detail=data["firstData"]["dataPoints"][i];
      data[i]= detail["value"];
      valdata[i]=detail["value"];
      var url=detail["time"];
      var strs = url.split(" ")
      //console.log(strs[1]);
      titlename[i]=strs[1];
    }
  })
    myChart.setOption(option);//重新加载表
  }
  setInterval(getItem, 1000);  //每间隔两分钟请求一次函数
  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();
//24h风速折线图
(function () {
  var option = {
    color: 'yellow',
    tooltip: {
      trigger: 'axis'
    },
    grid: {
      top: "10%",
      left: "0%",
      bottom: "5%",
      right: "0%",
      show: true,
      borderColor: '#012f4a',
      containLabel: true

    },
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
      axisTick: {
        show: false
      },
      axisLabel: {
        color: "rgba(255,255,255,.6)",
        fontSize: 12,
        formatter: function(value) {
          if (value.length > 12) {
            return value.substring(0, 7) + "...";
          } else {
            return value;
          }
        }
      },
      axisLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      borderyGap: false
    },
    yAxis: {
      type: 'value',
      axisTick: {
        show: false
      },
      axisLabel: {
        color: "rgba(255,255,255,.6)",
        fontSize: 12
      },
      axisLine: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      borderyGap: false
    },
    series: [{
      data: [0,2, 4, 6, 8, 10, 12],
      type: 'line',
      smooth: true
    }],
  };
  var myChart = echarts.init(document.querySelector(".bar .chart"));
  function getItem(mychart) {
    $.get("https://fishei.cn/competition/windForce", function (data) {

    //console.log(data) //返回对象
    //console.log(data["firstData"]["dataPoints"]) //风速类型

    for(var i=0; i<data["firstData"]["dataPoints"].length;i++)
    {
      var detail=data["firstData"]["dataPoints"][i];
      let time = detail["time"].split(" ")
      option.xAxis.data[i]= time[1];
      // option.xAxis.data[i]= detail["time"];
      option.series[0].data[i]= detail["value"];//获取series的第一行数据，并将模拟的数据赋值给他
    }
  })
    myChart.setOption(option);//重新加载表
  }
  setInterval(getItem, 1000);  //每间隔两分钟请求一次函数
  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();
//24h温湿度监测
(function () {
  var option = {
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      top: "0%",
      textStyle: {
        color: "rgba(255,255,255,.6)",
        fontSize: 12
      },
      data: ['气温', '湿度']
    },
    grid: {
      left: '0%',
      right: '5%',
      bottom: '5%',
      top: '10%',
      show: true,
      borderColor: '#012f4a',
      containLabel: true

    },
    xAxis: {
      type: 'category',
      axisTick: {
        show: false
      },
      axisLabel: {
        color: "rgba(255,255,255,.6)",
        fontSize: '4px',
        interval: 0,
        formatter: function(value) {
          if (value.length > 12) {
            return value.substring(0, 10) + "...";
          } else {
            return value;
          }
        }
      },
      axisLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      boundaryGap: false,
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri']
    },
    yAxis: {
      type: 'value',
      axisTick: {
        show: false
      },
      axisLabel: {
        color: "rgba(255,255,255,.6)",
        fontSize: 12
      },
      axisLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      splitLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      boundaryGap: false
    },
    series: [
      {
        name: '气温',
        data: [820, 932, 901, 934, 1290],
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        itemStyle: {
          color: "#00d887",
          borderColor: "rgba(221, 220, 107, 0.1)",
          borderWidth: 12
        },
        type: 'line',
        smooth: true,
        lineStyle:
        {
          color: "#00d887",
          width: 2
        },
        areaStyle:
        {
          normal: {
            color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [
                {
                  offset: 0,
                  color: "rgba(0, 216, 135, 0.4)"
                },
                {
                  offset: 0.8,
                  color: "rgba(0, 216, 135, 0.1)"
                }
              ],
              false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          },
        }
      },
      {
        name: "湿度",
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        itemStyle: {
          color: "#0184d5",
          borderColor: "rgba(221, 220, 107, 0.1)",
          borderWidth: 12
        },
        lineStyle:
        {
          color: "#0184d5",
          width: 2
        },
        areaStyle: {
          normal: {
            color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [
                {
                  offset: 0,
                  color: "rgba(1, 123, 213, 0.4)"
                },
                {
                  offset: 0.8,
                  color: "rgba(1, 123, 213, 0.1)"
                }
              ],
              false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          },
        },
        data: [10, 20, 30, 40, 45]
      }
    ]
  };
  var myChart = echarts.init(document.querySelector(".bar3 .chart"));
  function getItem(mychart) {
    $.get("https://fishei.cn/competition/temp", function (data) {

    //console.log(data) //返回对象
    //console.log(data["firstData"]["dataPoints"]) //温度类型
    //console.log(data["secondData"]["dataPoints"]) //湿度类型

    for(var i=0; i<data["firstData"]["dataPoints"].length;i++)
    {
      var detail=data["firstData"]["dataPoints"][i];
      console.log()
      let time = detail["time"].split(" ")
      option.xAxis.data[i]= time[1];
      option.series[0].data[i]= detail["value"];//获取series的第一行数据，并将模拟的数据赋值给他
    }
    for(var i=0; i<data["secondData"]["dataPoints"].length;i++)
    {
      var detail=data["secondData"]["dataPoints"][i];
      option.series[1].data[i]= detail["value"];//获取series的第2行数据，并将模拟的数据赋值给他
    }
  })
    myChart.setOption(option);//重新加载表
  }
  setInterval(getItem, 1000);  //每间隔两分钟请求一次函数

  window.addEventListener("resize", function () {
    myChart.resize();
  });
})()