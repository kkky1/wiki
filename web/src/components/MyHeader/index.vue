<template>
  <a-layout-header class="header">
    <success-alert ref="message" v-show="false"></success-alert>
    <div class="logo"/>
    <a-menu
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">
        <router-link to="/home">首页</router-link>
      </a-menu-item>
      <a-menu-item key="2">
        <router-link to="/adminBook">图书管理</router-link>
      </a-menu-item>
      <a-menu-item key="3">
        <router-link to="/adminCategory">分类管理</router-link>
      </a-menu-item>
      <a-menu-item key="4">
        <router-link to="/adminUser">用户管理</router-link>
      </a-menu-item>
      <a-menu-item key="5">
        <router-link to="/about">关于我们</router-link>
      </a-menu-item>
      <a-menu-item v-show="userInfo.id" key="6">
        <span> 您好,{{ userInfo.name }} </span>
      </a-menu-item>

      <a-button v-show="!userInfo.id" type="primary" @click="showUser">
        登录
      </a-button>
    </a-menu>
    <a-modal
        title="Title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk()"
        @cancel="handleCancel"
    >
      <userLogin :formInline="formInline"/>
    </a-modal>
  </a-layout-header>
</template>
<script>
import userLogin from '@/views/Edit/userLogin'
import axios from 'axios'
import successAlert from '@/components/Alert/AlertSuccess'

export default ({
  name: 'MyHeader',
  components: {
    userLogin, successAlert
  },
  data() {
    return {
      visible: false,
      confirmLoading: false,
      formInline: {
        loginName: '',
        password: '',
      },
      userInfo: {},
    }
  },
  methods: {
    showUser() {
      this.visible = true
    },
    handleOk() {
      this.showUser();
      axios.post(`/user/login`, this.formInline).then((resp) => {
        console.log("resp", this.formInline)
        if (resp.status === 200) {
          this.visible = false
          this.$refs.message.successalert(resp.data)
          this.userInfo = resp.data.content
        } else {
          alert("数据加载失败")
        }
      })
    },
    handleCancel(e) {
      this.visible = false;
    },
  }
})
</script>