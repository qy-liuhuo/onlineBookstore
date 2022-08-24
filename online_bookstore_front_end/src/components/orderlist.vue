<template>
  <view>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="id" label="订单号" width="150" />
      <el-table-column prop="time" label="下单日期" width="180" />
      <el-table-column prop="total" label="订单总额" width="180" />
      <el-table-column prop="state" label="订单状态" width="180" />
      <el-table-column label="详情" width="180"
        ><template #default="scope">
          <el-button type="primary" @click="showOrder(scope.$index, scope.row)"
            >详情</el-button
          >
        </template></el-table-column
      >
    </el-table>

    <el-drawer v-model="order" title="测试" :direction="direction">
      <el-row>
        <el-col :span="5">
          订单号:<span style="padding-left: 10px">{{
            orderDetail.id
          }}</span></el-col
        >
      </el-row>
      <el-row>
        <el-col>
          订单细节:<span style="padding-left: 10px"
            >{{orderDetail.description}}</span
          ></el-col
        >
      </el-row>
    </el-drawer>
  </view>
</template>

<script >
export default {
  name: "orderlist",
  components: {},
  data() {
    return {
      tableData: [
      ],
      order: false,
      orderDetail: {
        id:"",
        description: "",
      },
    };
  },
  methods: {
    handleClose: () => {},
    showOrder(index, row) {
      console.log(index);
      console.log(row);
      this.order = true;
      this.orderDetail.id = row.id;
      this.orderDetail.description = row.description;
    },
  },
  created() {
    this.axios.post("order/getOrderList").then((res)=>{console.log(res);
      var data=res.data.data;
      for(var i=0;i<data.length;i++)
      {
        console.log(data[i].time);
        data[i].time=new Date(data[i].time).Format("yyyy-MM-dd hh:mm");
      }
      this.tableData=data;
    });
  },
};
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>