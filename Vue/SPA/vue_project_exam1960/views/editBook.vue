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
            <input type="button" value="更新" @click="editBook()">
        </tr>
    </table>
    </div>
  
</template>

<script>
export default {
    data() {
        return {
            book:{},
            categoryList:[]
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
        selectBookByBid(bid){
            this.$axios.get("/book/"+bid)
            .then(res => {
                this.book = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        editBook(){
            this.$axios.put("/book",this.book)
            .then(res => {
                alert("更新成功！")
                this.$router.push("/bookIndex")
            })
            .catch(err => {
                console.error(err); 
            })
        }
    },
    created(){
        this.selectAllCategory();
        var bid = this.$route.params.bid;
        this.selectBookByBid(bid);
    }
}
</script>

<style>

</style>