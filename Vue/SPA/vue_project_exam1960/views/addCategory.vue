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
            <input type="button" value="添加" @click="addCategory()">
        </tr>
    </table>
</template>

<script>
export default {
    data() {
        return {
            category:{},
        }
    },
    methods:{
        addCategory(){
            this.$axios.post("category",this.category)
            .then(res => {
                alert("添加成功！")
                this.$router.push("/categoryIndex")
            })
            .catch(err => {
                console.error(err); 
            })
        }
    },
}
</script>

<style>

</style>