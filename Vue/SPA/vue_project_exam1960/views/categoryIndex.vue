<template>
  <div>
      <router-link to="/">返回首页</router-link> |
      <router-link to="/addCategory">添加分类</router-link>
      <table border="1">
           <tr>
               <td>分类ID</td>
               <td>分类名称</td>
               <td>类别介绍</td>
               <td>操作</td>
           </tr>
           <tr v-for="(category,index) in categoryList" :key="index">
               <td>{{category.cid}}</td>
               <td>{{category.cname}}</td>
               <td>{{category.cdesc}}</td>
               <td>
                   <input type="button" value="修改" @click="editCategory(category.cid)">
                   <input type="button" value="删除" @click="deleCategory(category.cid)">
               </td>
           </tr>
      </table>
  </div>
</template>

<script>
export default {
    data() {
        return {
            categoryList:[]//分类数组
        }
    },
    methods:{
        selectAllCategory(){
            this.$axios.get("/category")
            .then(res => {
                this.categoryList = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        editCategory(cid){
            this.$router.push("/editcategory/"+cid)
        },
        deleCategory(cid){
            if(confirm("确定删除？")){
                this.$axios.delete("/category/"+cid)
                .then(res => {
                    
                    this.selectAllCategory();
                })
                .catch(err => {
                    console.error(err); 
                })
            }
        }
    },
    created(){
        this.selectAllCategory();
    }
}
</script>

<style>

</style>