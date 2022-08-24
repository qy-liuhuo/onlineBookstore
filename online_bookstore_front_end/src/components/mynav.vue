<template>
  <view>
    <el-tabs class="demo-tabs" v-model="nowPage">
      <el-tab-pane name="shop" :key="shop">
        <template #label>
          <span class="custom-tabs-label">
            <span><i class="iconfont icon-shouye" />商城</span>
          </span>
        </template>
        <mainpage v-bind:nowPage="nowPage" v-on:changePage = "changePage" />
      </el-tab-pane>

      <el-tab-pane name="shoppingCart" :key="shoppingCart"
        ><template #label>
          <span class="custom-tabs-label">
            <span><i class="iconfont icon-gouwuchekong" />购物车</span>
          </span>
        </template>
        <shoppingCart v-bind:nowPage="nowPage" v-on:changePage = "changePage"/>
      </el-tab-pane>

      <el-tab-pane name="order" :key="order"
        ><template #label>
          <span class="custom-tabs-label">
            <span><i class="iconfont icon-shouye1" />订单</span>
          </span>
        </template>
        <orderlist v-bind:nowPage="nowPage" v-on:changePage = "changePage"/>
      </el-tab-pane>
      <el-tab-pane name="login" v-if="!state" :key="login"
        ><template #label style="">
          <span
            class="custom-tabs-label"
            @click="drawer = true"
            style="padding-right: 20px"
          >
            <span><i class="iconfont icon-denglu" />登录</span>
          </span>
        </template>
        <login v-bind:nowPage="nowPage" v-on:changePage = "changePage" />
      </el-tab-pane>
      <el-tab-pane name="login" v-if="state" :key="logout"
        ><template #label style="">
          <span
            class="custom-tabs-label"
            @click="logout()"
            style="padding-right: 20px"
          >
            <span><i class="iconfont icon-denglu" />注销</span>
          </span>
        </template>
      </el-tab-pane>
    </el-tabs>

    
  </view>
</template>
<script >
import login from "./login.vue";
import mainpage from "./mainpage.vue";
import orderlist from "./orderlist.vue";
import shoppingCart from "./shoppingCart.vue";
export default {
  components: { login, mainpage, orderlist, shoppingCart },
  name: "mynav",
  data() {
    return {
      nowPage:"shop",
      state:true,
    };
  },
  methods: {
    changePage(page)
    {
      console.log(page);
      this.nowPage=page;
    },
    logout()
    {
      this.axios.get("user/logout").then((res)=>{
        console.log(res);
        this.state=false;
        this.nowPage="login";
      })
    }
  },
  created() {
    this.axios
          .post("user/checkState")
          .then((res) => {
              if(res.data.code!=200)
              {
                this.nowPage="login";
                this.state=false;
              }
              else
              {
                this.state=true;
              }
          }).catch(()=>{
              this.$message({
                showClose: true,
                message: "网络异常",
                type: 'error'
              });
          });
  },
};
</script>
<style>
.el-tabs__nav {
  float: right;
}
</style>