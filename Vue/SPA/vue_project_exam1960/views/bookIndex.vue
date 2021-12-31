<template>
    <div>
        <router-link to="/">返回首页</router-link> |
        <router-link to="/addBook">添加图书</router-link>
        <br>
        类别：
        <select v-model="bookVo.cid">
            <option value="">-选择分类-</option>
            <option v-for="(category,index) in categoryList" :key="index" :value="category.cid">
                {{category.cname}}
            </option>
        </select>
        书名：
        <input type="text" v-model="bookVo.bname">
        价格：
        <input type="text" v-model="bookVo.minPrice"> --  
        <input type="text" v-model="bookVo.maxPrice">
        <input type="button" value="查询" @click="selectBookBycondition()">
        <br>
        <table border="1">
            <tr>
                <td>
                    全选
                    <input type="checkbox" v-model="selectAll" @change="selectAllFun()">
                    <input type="button" value="删除" @click="batchDel()"></td>
                <td>图书ID</td>
                <td>图书名称</td>
                <td>图书价格</td>
                <td>发行时间</td>
                <td>图书类别</td>
                <td>操作</td>
            </tr>
            <tr v-for="(book,index) in bookList" :key="index">
                <td><input type="checkbox" v-model="dels" :value="book.bid"></td>
                <td>{{book.bid}}</td>
                <td>{{book.bname}}</td>
                <td>{{book.price}}</td>
                <td>{{book.publish_time}}</td>
                <td>{{book.category.cname}}</td>
                <td>
                    <router-link :to="'/editBook/'+book.bid">修改</router-link>
                    <input type="button" value="删除" @click="deleByBid(book.bid)">
                </td>
            </tr>
        </table>
        <div class="block">

  <el-pagination
    layout="prev, pager, next"
    @current-change="handleCurrentChange"
    :page-size="bookVo.pageSize"
    :total="bookTotal">
  </el-pagination>
</div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            bookVo:{
                pageNum:1,//从第一页开始
                pageSize:5//每页五条
            },
            categoryList:[],//分类集合
            bookList:[],//图书集合
            dels:[],//批量删除
            bookTotal:0,//图书总数
            selectAll:false,//默认不全选
        }
    },
    methods:{
        selectBookBycondition(){
            this.$axios.post("/book",this.bookVo)
            .then(res => {
                this.bookList = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        selectAllCategory(){
            this.$axios.get("/category")
            .then(res => {
                this.categoryList = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        selectBookNumbers(){
            this.$axios.get("/book/count")
            .then(res => {
                this.bookTotal = res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        },
        handleCurrentChange(val){
            this.bookVo.pageNum = val;
            this.selectBookBycondition();
        },
        selectAllFun(){
            if(this.selectAll){
                this.bookList.forEach(book=> this.dels.push(book.bid))
            }else{
                this.dels = []
            }
        },
        batchDel(){
            if(this.dels.length==0){
                alert("请至少选择一条数据！")
            }else{
                if(confirm("您确定要删除吗？")){
                    var bids = "";
                    this.dels.forEach(bid =>{
                        bids+=bid+","
                    })
                    bids = bids.substring(0,bids.length-1);
                    this.$axios.delete("/book/"+bids)
                    .then(res => {
                        this.selectBookBycondition();
                    })
                    .catch(err => {
                        console.error(err); 
                    })
                }
            }
        },
        deleByBid(bid){
            this.dels = []
                this.dels.push(bid)
                this.batchDel();
        }
    },
    created(){
        this.selectAllCategory();
        this.selectBookBycondition();
        this.selectBookNumbers();
    },
    watch:{
            'dels':function(){
                if(this.bookList.length==this.dels.length){
                    this.selectAll = true;
                }else{
                    
                    this.selectAll = false;
                }
            }
        }
}
</script>

<style>

</style>