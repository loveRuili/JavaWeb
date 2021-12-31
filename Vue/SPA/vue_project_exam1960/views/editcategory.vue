<template>
  <table border="1">
        <tr>
            <td>分类名称</td>
            <td><input type="text" v-model="category.cname"></td>
        </tr>
        <tr>
            <td>类别介绍</td>
            <td><input type="text" v-model="category.cdesc"></td>
        </tr>
        <tr>
            <input type="button" value="更新" @click="editCategory()">
        </tr>
    </table>
</template>

<script>
export default {
    data() {
        return {
            category:{}
        }
    },
    methods:{
        selectCategoryByCid(cid){
            this.$axios.get("/category/"+cid)
            .then(res => {
                this.category = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        editCategory(){
            this.$axios.put("/category",this.category)
            .then(res => {
                alert("修改成功！")
                this.$router.push("/categoryIndex");
            })
            .catch(err => {
                console.error(err); 
            })
        }
    },
    created(){
        var cid = this.$route.params.cid
        this.selectCategoryByCid(cid)
    }
}
</script>

<style>

</style>