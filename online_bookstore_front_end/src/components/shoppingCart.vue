<template>
  <view>
    <el-table :data="tableData">
      <el-table-column label="选中">
        <template #default="scope">
          <el-switch
            v-model="tableData[scope.$index].checked"
            class="mt-2"
            style="margin-left: 24px"
            inline-prompt
          />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品名" />
      <el-table-column prop="price" label="商品价格" width="180" />
      <el-table-column prop="num" label="商品数目" width="180" />
      <el-table-column label="总价" width="180">
        <template #default="scope">
          {{
            (
              tableData[scope.$index].price * tableData[scope.$index].num
            ).toFixed(2)
          }}
        </template>
      </el-table-column>

      <el-table-column label="更改数量" width="180"
        ><template #default="scope">
          <el-input-number v-model="tableData[scope.$index].num" :min="1" ></el-input-number>
          </template
      ></el-table-column>
    </el-table>
    <div >
      <el-pagination
        layout="prev, pager, next"
        :total="num"
        :page-size="pagesize"
        :current-page="currentpage"
        @current-change="handleCurrentChange" 
        >
      </el-pagination>
    </div>

    <view id="priceTag"
      ><el-tag
        class="ml-2"
        size="large"
        type="success"
        style="margin-right: 5px"
        >总价：￥{{ total }}</el-tag
      >
      <el-button type="danger" @click="buy">付款</el-button></view
    >
  </view>
</template>

<script >
export default {
  name: "shoppingCart",
  components: {},
  data() {
    return {
      currentPage: 1,
      pagesize: 5,
      num:0,
      total: 0,
      tableData: [
      ],
      order: false,
      orderDetail: {
        id: "",
      },
    };
  },
  methods: {
    buy(){
      let list=[];
      let num=[];
      let idList=[];
      for(let i=0;i<this.tableData.length;i++)
      {
        if(this.tableData[i].checked)
        {
          idList.push(this.tableData[i].id);
          list.push(this.tableData[i].goodsId);
          num.push(this.tableData[i].num);
        }
      }
      this.axios.post("shopCar/buy",{"idList":idList,"list":list,"num":num}).then((res)=>{
        console.log(res);
        this.$message({
              message:"付款成功",
              type:"success",
            });
        })
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
    },
    getNum()
    {
      this.axios.get("shopCar/countNum").then((res)=>{
        //向上取整
       this.num=Math.ceil(res.data.data/this.pagesize);
       this.getData();
      })
    },
    getData()
    {
      let data={nowPage:this.currentPage,pageSize:this.pagesize};
       this.axios.post("shopCar/getList",this.qs.stringify(data)).then((res)=>{
         let data=res.data.data;
         for(let i=0;i<data.length;i++)
         {
           data[i].checked=false;
           data[i].totalPrice=data[i].num*data[i].price;
         }
         this.tableData=data;
      })
    }
  },
  watch: {
    currentPage:{
      handler() {
        console.log('123');
          this.getData();
      },
    },
    tableData: {
      handler(val) {
        var sum = 0;
        for (let i = 0; i < val.length; i++) {
          val[i].totalPrice = val[i].num * val[i].price;
          if (val[i].checked) {
            console.log(val[i].totalPrice);
            sum += val[i].totalPrice;
          }
        }
        this.total = sum;
      },
      // 这里是关键，代表递归监听 items 的变化
      deep: true,
    },
  },
  mounted()
  {
    this.getNum();
  },
};
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
#priceTag {
  float: right;
  margin-right: 50px;
}
</style>
<style>
.el-pagination{
  text-align:center !important;
} 
</style>