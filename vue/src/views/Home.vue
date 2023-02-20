<template>
  <div class="home">
    <!--查找-->
    <div style="margin-top:10px; margin-bottom: 10px; margin-left: 10px; display: flex" >
      <el-input  v-model="searchData" style="width: 20%; margin-right:5px;" placeholder="请输入关键字" clearable></el-input>
      <el-button type="primary" @click="LoadPage">搜索</el-button>
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <!--  添加弹出框-->
    <el-dialog v-model="add_dialogVisible" title="添加一部电影" width="350px" draggable>
      <el-form ref="form" :model="form"  style="margin:0px">
        <el-form-item label="电影名" label-width="60px" prop="name">
          <el-input v-model="form.name" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="年份" label-width="60px" prop="year">
          <el-input v-model="form.year" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="地区" label-width="60px" prop="country">
          <el-input v-model="form.country" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="评分" label-width="60px" prop="score">
          <el-input v-model="form.score" type="text" style="width: 240px"></el-input>
        </el-form-item>

        <div id="div1"></div>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="add_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="add">确认</el-button
        >
      </span>
      </template>
    </el-dialog>

    <!--  编辑弹出框-->
    <el-dialog v-model="edit_dialogVisible" title="编辑电影信息" width="700px" style="height: 80%" draggable>
      <el-form :model="form" style="margin:0px">
        <el-form-item label="电影" label-width="60px" prop="name">
          <el-input v-model="form.name" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="年代" label-width="60px" prop="year">
          <el-input v-model="form.year" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="地区" label-width="60px" prop="country">
          <el-input v-model="form.country" type="text" style="width: 240px"></el-input>
        </el-form-item>
        <el-form-item label="评分" label-width="60px" prop="score">
          <el-input v-model="form.score" type="text" style="width: 240px" ></el-input>
        </el-form-item>
        <div id="div2" ref="editor2"></div>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="edit_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="edit">确认</el-button
        >
      </span>
      </template>
    </el-dialog>
    <el-table :data="tableData" stripe style="width: 100%; height: 80%; font-size: 15px">
      <el-table-column prop="name" label="电影名"  align="center" sortable />
      <el-table-column prop="country" label="地区"  align="center" sortable />
      <el-table-column prop="year" label="年代"  align="center" sortable />
      <el-table-column prop="score" label="评分"  align="center" sortable />
      <el-table-column prop="action" label="操作" align="center">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDel(scope.row)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 250px">
      <el-pagination
          v-model:current-page="pagination.currentPage"
          v-model:page-size="pagination.pageSize"
          :page-sizes="[15]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import request from"@/utils/request"
export default {
  name: "Home",
  data() {
    return {
      form: {
        name:'',
        year:'',
        country:'',
        score:''
      },
      pagination:{
        currentPage:1,
        total: 5,
        pageSize: 15
      },
      loading: '',
      currentPage: 1,
      total: 10,
      add_dialogVisible: false,
      edit_dialogVisible: false,
      tableData : [],
      searchData: ""
    }
  },
  created() {
    this.LoadPage();
  },
  methods: {
    LoadPage(){
      request.get("/movie/"+this.pagination.currentPage+"/"+this.pagination.pageSize,{
        params:{
          name: this.searchData
        }
      }).then(res=>{
        console.log(res);
        this.pagination.total = res.data.total;
        this.pagination.currentPage = res.data.current;
        this.pagination.pageSize = res.data.size;
        this.tableData = res.data.records;
      })

    },
    handleAdd() {
      this.add_dialogVisible = true;
      this.form = {};
    },
    add() {
      request.post("/movie", this.form).then(res =>{
        console.log(res);
        this.add_dialogVisible = false;
        this.LoadPage();
      })
    },
    handleEdit(row){
      this.edit_dialogVisible = true;
      request.get("/movie/"+row.id).then(res=>{
        this.form = res.data;
      })
      console.log(this.form);

    },
    edit() {
      request.put("/movie", this.form).then(res=>{
        if(res.data){
          this.$message({
            type: "success",
            message: "更新成功"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.edit_dialogVisible = false;
        this.LoadPage();
      })

    },
    handleDel(row){
      this.$confirm('是否删除【'+row.name+'】？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          request.delete("/movie/"+row.id).then(res=>{
            if(res.code ==="200"){
              this.$message({
                type: "success",
                message: "删除成功"
              })
            }else{
              this.$message({
                type: "error",
                message: "删除失败"
              })
            }
            this.LoadPage();
          })
        })
        .catch(() => {
          // 取消
        })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.LoadPage();
    },
    handleSizeChange(pageSize) {
      this.pagination.pageSize = pageSize;
      this.LoadPage();
    }
  }
}
</script>

<style scoped>

</style>