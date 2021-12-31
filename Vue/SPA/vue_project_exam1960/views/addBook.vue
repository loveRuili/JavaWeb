<template>
  <div>
        <router-link to="/bookIndex">返回列表页</router-link>
        <table border="1">
        <tr>
            <td>图书名称</td>
            <td><input type="text" v-model="book.bname"></td>
        </tr>
        <tr>
            <td>图书分类</td>
            <td>
                <select v-model="book.cid">
                    <option value="">-请选择-</option>
                    <option v-for="(category,index) in categoryList" :key="index" :value="category.cid">
                        {{category.cname}}
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td>图书价格</td>
            <td><input type="text" v-model="book.price"></td>
        </tr>
        <tr>
            <td>出版时间</td>
            <td><input type="date" v-model="book.publish_time"></td>
        </tr>
        <tr>
            <input type="button" value="添加" @click="addBook()">
        </tr>
    </table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            categoryList:[],
            book:{},
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
        addBook(){
            this.$axios.post("/book/addBook",this.book)
            .then(res => {
                alert("添加成功！")
                this.$router.push("/bookIndex")
            })
            .catch(err => {
                console.error(err); 
            })
        }
    },
    created(){
        this.selectAllCategory();
    }
}
</script>

<style>

</style>