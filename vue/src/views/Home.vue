<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 100px">
      <el-col :span="6">
        <el-card style="color:#409EFF">
          <div><i class="el-icon-user-solid"></i>用户总数</div>
          <div style="padding: 10px;text-align: center;font-weight: bold">
              100
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color:#0f8d3d">
          <div> <i class="el-icon-money"></i>销售总量</div>
          <div style="padding: 10px;text-align: center;font-weight: bold">
            ￥100
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color:#ffa340">
          <div ><i class="el-icon-wallet"></i>收益总额</div>
          <div style="padding: 10px;text-align: center;font-weight: bold">
            ￥100
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card style="color:#e40e27">
          <div ><i class="el-icon-s-shop"></i>门店总数</div>
          <div style="padding: 10px;text-align: center;font-weight: bold">
            10
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 400px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>
    
  </div>
</template>

<script>

import * as echarts from 'echarts'

export default {
  name:"Home",
  data(){
    return{

    }
  },mounted(){
    var option;

    //折线图
    option = {
      title:{
        text: '各季度会员数量',
        subtext: '统计图',
        left: 'center'
      },
      xAxis: {
        type: 'category',
        data: ["Season1","Season2","Season3","Season4"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
    
    

    // 饼图
    var pieOption = {
      title: {
        text: '各季度会员数量',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '数据详情',
          type: 'pie',
          radius: [20,120],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
        };

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);


    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);
   
    this.request.get("/echarts/members").then(
      res=>{ 
        option.series[0].data=res.data
        option.series[1].data=res.data
        myChart.setOption(option);


        pieOption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
        ]
        pieChart.setOption(pieOption);
      }
    )
  }
}
</script>

<style>

</style>