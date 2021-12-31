<template>
  <div>
      用户名：<input type="text" v-model="user.username"><br>
      密码：<input type="text" v-model="user.password"><br>
      <input type="button" value="登录">
  </div>
</template>

<script>
export default {
    data() {
        return {
            user:{}
        }
    },
    methods:{
        
    }
}
</script>

<style>

</style>