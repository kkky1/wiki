<template>
  <div >
    <a-card class="cross" hoverable style="width: 300px" v-for="(book,index) in bookContent" :key="index">

      <router-link :to="`/concrete?ebookid=${book.id}`">
      {{bookContent.id}}
      <template #cover>
        <img
            alt="example"
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
        />
      </template>

        <like />{{bookContent[index].voteCount}}
        <read/>{{bookContent[index].viewCount}}
      <template #actions>
        <setting-outlined key="setting"/>
        <edit-outlined key="edit"/>
        <ellipsis-outlined key="ellipsis"/>
      </template>
      <router-link :to="'/docDetail?ebookId='+book.id">
      <a-card-meta :title="book.name" @click="clickLike(book.id)">
        <template #avatar>
        </template>
      </a-card-meta>
      </router-link>
      </router-link>
    </a-card>

  </div>
</template>
<script>
import axios from "axios";
import like from '@/views/BookDetail/Like'
import read from '@/views/BookDetail/Read'
import Like from "@/views/BookDetail/Like";
import Read from "@/views/BookDetail/Read";
export default {
  name: 'Mycontent',
  components: {Read, Like},
  props: ['bookContent','like','read'],
  updated() {
  },
  methods:{
    clickLike(id){
      axios.post(`/ebook/like/${id}`).then((resp) => {
        if (resp.status === 200 && resp.data.content != null) {
          console.log("点赞成功")
        } else {
          alert("数据加载失败")
        }
      })
    },
    getCurrentList() {
      axios.get(`/category/list`).then((resp) => {
        if (resp.status === 200) {
        } else {
          alert("数据加载失败")
        }
      })
    },
  }
};
</script>

<style>
.cross {
  display: inline-block;
  margin: 20px 40px;
  padding-left: 30px;
}
</style>