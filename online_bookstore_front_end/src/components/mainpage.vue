<template>
  <view>
    <shuffl />
    <el-divider></el-divider>
    <el-row>
      <el-col :span="4">
        <el-affix :offset="120">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <el-input
                  v-model="keyWord"
                  class="w-50 m-2"
                  size="small"
                  placeholder="关键词搜索"
                />
               <el-button @click="getBookList()">搜索</el-button> 
              </div>
            </template>
            <view>
              <el-button style="margin:5px" :type="tagType(item.id)" v-for="item in typeList" :key="item.id" @click="changeType(item.id)">
                {{ item.name }}
              </el-button>
            </view>
          </el-card>
        </el-affix>
      </el-col>
      <el-col :span="20"><booklist v-loading="loading" :books="bookList"/></el-col>
    </el-row>
    <!-- 回到顶部 -->
    <el-backtop />
  </view>
</template>

<script>
import Booklist from "./booklist.vue";
import shuffl from "./shuffl.vue";

export default {
  components: {
    shuffl,
    Booklist,
  },
  data() {
    return {
      keyWord:"",
      typeList:null,
      selectedtype:-1,
      bookList:[],
      loading: false,
    };
  },
  methods: {
    tagType(index)
    {
      if(index==this.selectedtype)
      {
        return "success";
      }
      return "warning";
    },
    changeType(index)
    {
      if(index==this.selectedtype)
      {
        this.selectedtype=-1;
      }
      else
      {
        this.selectedtype=index;
      }
      
    },
    getBookList()
    {
      this.loading=true;
      let data={keyWord:this.keyWord,type:this.selectedtype};
      this.axios.post("book/getBooks",this.qs.stringify(data)).then((res)=>{
        this.bookList=res.data.data;
        this.loading=false;
      }).catch(()=>{
        this.$message({
          message:"网络异常",
          type:"error",
        });
        this.loading=false;
      })
    }
  },
  created() {
    this.loading=true;
    this.axios.get("bookType/getAll").then((res)=>{
      console.log(res);
      this.typeList=res.data.data;
      this.loading=false;
    }).catch(()=>{
      this.$message({
        message:"网络异常",
        type: 'error'
      });
      this.loading=false;
    });
    
    this.getBookList();
    
  },
};
</script>


<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
  margin-left: 20px;
}
</style>

