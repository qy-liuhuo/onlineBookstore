<template>
  <view>
    <el-row justify="center" :gutter="10" style="margin-bottom: 100px">
      <el-col v-for="item in books" :key="item.id" :span="5">
        <el-card :body-style="{ padding: '0px' }" shadow="always" @click="nowBook=item;drawer = true">
          <img :src="`http://onlinebookstore.qylh.xyz//bookImg/${item.src}`" class="image" />
          <div style="padding: 14px">
            <span>{{item.name}}</span>
            <div class="bottom">
              <el-button type="danger" style="padding:10px" class="button" 
                >￥{{item.price}}
              </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-drawer
      v-model="drawer"
      title="儿童故事书"
      :direction="direction"
      :before-close="handleClose()"
    >
      <el-row>
        <el-col :span="12">
          <img :src="`http://onlinebookstore.qylh.xyz//bookImg/${nowBook.src}`" class="image" />
        </el-col>
        <el-col :span="1"> </el-col>
        <el-col :span="11">
          <h4>
            {{nowBook.description}}
          </h4>
          价格: <span style="color: red">{{nowBook.price}}</span>
          <br />
          <el-divider></el-divider> 
          <el-form :model="form" style="text-align: center">
            <el-form-item label="数量">
              <el-input-number v-model="form.num" :min="1" :max="nowBook.store"></el-input-number>
            </el-form-item>
          </el-form>
          <el-button style="float: right" type="primary" @click="addToCart(nowBook.id)">加入购物车</el-button>
        </el-col>
      </el-row>
    </el-drawer>
  </view>
</template>

<script >
export default {
  props:['books'],
  name: "booklist",
  components: {},
  data() {
    return {
      drawer: false,
      form: {
        num: 1,
      },
      nowBook:null,
      bookList:[],
    };
  },
  methods:{
    addToCart(id){
      let data={goodsId:id,num:this.form.num};
      this.axios.post("shopCar/addToShopCar",this.qs.stringify(data)).then((res)=>{
        if(res.data.code!=200)
        {
            this.$message({
              message:res.data.msg,
              type:"error",
            });
        }
        else
        {
          this.$message({
              message:"加入成功",
              type:"success",
            });
        }
        this.closeDrawer();
      })
    },
    handleClose()
    {
      this.form.num=1;
    }
  }
};
</script>

<style>
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  min-height: 300px;
  max-height: 300px;
  display: block;
}
</style>