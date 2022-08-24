<template>
  <div>
    <el-row>
      <el-col :span="14">
        <div class="grid-content bg-purple-light" style="position: relative; top: 20%; transform: translateY(-20%)">
          <img src="@/assets/login_left.png" style="width: 80%" />
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple login-container">
          <div class="title-container">
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="
                display: flex;
                justify-content: center;
                background-color: #f1f2f6;
              ">
              <el-menu-item index="1" @click="changeLogin" is-active>登录</el-menu-item>
              <el-menu-item index="2" @click="changeRegister">注册</el-menu-item>
            </el-menu>
          </div>
          <el-form v-if="isLogin" ref="formRef" :model="form" label-width="120px">
            <el-form-item class="inputItem" label="账号">
              <el-input v-model="username" name="username"></el-input>
            </el-form-item>
            <el-form-item class="inputItem" label="密码">
              <el-input v-model="password" name="password" show-password></el-input>
            </el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
          </el-form>
          <el-form v-if="!isLogin" ref="formRef" :model="form" label-width="120px">
            <el-form-item class="inputItem" label="账号">
              <el-input v-model="username" name="username"></el-input>
            </el-form-item>
            <el-form-item class="inputItem" label="密码">
              <el-input v-model="password" name="password" show-password></el-input>
            </el-form-item>
            <el-form-item class="inputItem" label="确认密码">
              <el-input v-model="confirmPassword" name="password" show-password></el-input>
            </el-form-item>
            <el-button type="primary" @click="register">注册</el-button>
          </el-form>
        </div>
      </el-col>
      <el-col :span="2"></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "login",
  props:['nowPage'],
  data() {
    return {
      isLogin: true,
      username: "",
      password: "",
      confirmPassword: "",
    };
  },
  methods: {
    changeLogin() {
      this.isLogin = true;
    },
    changeRegister() {
      this.isLogin = false;
    },
    register() {
      if (this.password != this.confirmPassword) {
        alert("两次密码输入不一致");
      } else {
        var data = { username: this.username, password: this.password ,confirmPassword:this.confirmPassword}; //定义一个data储存需要带的参数
        this.axios
          .post("user/register", this.qs.stringify(data))
          .then((res) => {
              if(res.data.code!=200)
              {
                this.$message({
                                showClose: true,
                                message: res.data.msg,
                                type: 'error'
                              });
              }
              else
              {
                this.$message({
                                showClose: true,
                                message: "注册成功",
                                type: 'success'
                              });
                this.$emit('changePage',"shop");
              }
          }).catch((err)=>{
              this.$message({
                showClose: true,
                message: err,
                type: 'error'
              });
          });
      }
    },
    login() {
      
      var data = { username: this.username, password: this.password }; //定义一个data储存需要带的参数
        this.axios
          .post("user/login", this.qs.stringify(data))
          .then((res) => {
            if(res.data.code!=200)
              {
                this.$message({
                                showClose: true,
                                message: res.data.msg,
                                type: 'error'
                              });
              }
              else
              {
                this.$message({
                                showClose: true,
                                message: "登录成功",
                                type: 'success'
                              });
                this.$emit('changePage',"shop");
              }

          }).catch((err)=>{
              this.$message({
                showClose: true,
                message: err,
                type: 'error'
              });
          });
    },
  },
};
</script>


<style lang="scss" scoped>
.login-container {
  position: relative;
  top: 20%;
  transform: translateY(-20%);
  text-align: center;
}
.title-container {
  margin-bottom: 50px;
}
.inputItem {
  margin-top: 30px !important;
}
</style>

