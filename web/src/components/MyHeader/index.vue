<template>
  <a-layout-header class="header">
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
    </a-menu>
  </a-layout-header>
</template>
<script>

export default ({
  name: 'MyHeader',
  components: {},
})
</script>