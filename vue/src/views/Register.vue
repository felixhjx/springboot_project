<template>
    <div class="wrapper">
      <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 350px; padding: 20px; border-radius: 10px">
        <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册</b></div>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-form-item prop="username">
            <el-input ploaceholder="请输入用户名" size="medium" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input size="medium" ploaceholder="请输入密码" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <el-input size="medium" ploaceholder="请确认密码" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.cofirmPassword"></el-input>
          </el-form-item>
          <el-form-item style="margin: 5px 0; text-align: right">
            <el-button type="primary" size="small"  autocomplete="off" @click="login">确认</el-button>
            <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/login')">返回登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "Login",
    data() {
      return {
        user: {},
        rules: {
          //登录后端验证
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],
          cofirmPassword: [
            { required: true, message: '请确认密码', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ]
        },
      }
    },
    methods: {
      login() {
        this.$refs['userForm'].validate((valid) => 
        {
          if (valid) {  // 表单校验合法
            if(this.user.password!==this.user.cofirmPassword){
                this.$message("两次输入不一致")
                return false
            }
            this.request.post("/user/register", this.user)
            .then(res => {
              if(res.code==='200') {
                this.$message.success("注册成功")
              } else {
                this.$message.error("用户名或密码错误")
              }
            }
            )
          } 
        });
      }
    }
  }
  </script>
  
  <style>
    .wrapper {
      height: 100vh;
      background-image: linear-gradient(to bottom right, #d909e0 ,  #76063c);
      overflow: hidden;
    }
  </style>
  