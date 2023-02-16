<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu mode="inline" :style="{ height: '100%', borderRight: 0 }">
        <a-sub-menu key="sub1" >
          <template #title>
              <span>
                欢迎光临
              </span>
          </template>
        </a-sub-menu>
        <a-sub-menu key="sub2" v-for="(nav,index) in bookCategory" :key="index">
          <template #title>
              <span>
                {{ nav.name }}
              </span>
          </template>
          <a-menu-item v-for="(secondNav,index) in nav.children" :key="index" @click="handleclick(secondNav.id)">
            {{ secondNav.name }}
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <p  v-show="initView">欢迎观看图书</p>
        <Mycontent v-show="!initView" :bookContent="bookContent"/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import {UserOutlined, LaptopOutlined, NotificationOutlined} from '@ant-design/icons-vue';
import {defineComponent, ref} from 'vue';
import axios from "axios";
import Mycontent from '@/views/MyContent'
import {Tool} from '@/util/tool'

export default defineComponent({
  name: 'Home',
  components: {
    UserOutlined,
    LaptopOutlined,
    NotificationOutlined,
    Mycontent
  },
  mounted() {
    // axios.get('/ebook/list').then((resp) => {
    //   if (resp.status === 200) {
    //     this.bookContent = resp.data
    //   } else {
    //     alert("数据加载失败")
    //   }
    // })
    this.getCurrentList()
  },
  data() {
    return {
      initView:true,
      bookContent: [],
      bookCategory: [{
        id: '',
        name: '',
        children: [{
          id: '',
          name: ''
        }]
      }]
    }
  },
  methods: {
    getCurrentList() {
      axios.get(`/category/list`).then((resp) => {
        if (resp.status === 200) {
          console.log(resp.data.content)
          this.bookCategory = Tool.array2Tree(resp.data.content, 0)
          console.log(Tool.array2Tree(resp.data.content, 0))
        } else {
          alert("数据加载失败")
        }
      })
    },
    handleclick(id) {
      this.initView = false
      axios.get(`/category/getParentId/${id}`).then((resp) => {
        if (resp.status === 200) {
          this.bookContent = resp.data
          console.log(this.bookContent)
        } else {
          alert("数据加载失败")
        }
      })
    }
  },
})
</script>

<style scoped>

</style>