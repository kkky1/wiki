<template>
  <a-table rowKey="id" :columns="columns" :data-source="data">
    <a slot="name" slot-scope="text">{{ text }}</a>
    <a slot="delete" slot-scope="text">
      <a-button type="danger" @click="deleteBook(text.id)">
        删除
      </a-button>
    </a>
    <a slot="edit" slot-scope="text" href="javascript:;">
      <a-button type="primary">
        修改
      </a-button>
    </a>
  </a-table>
</template>
<script>
import axios from "axios";

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    scopedSlots: {customRender: 'name'},
  },
  {
    title: '封面',
    dataIndex: 'age',
    key: 'age',
    width: 80,
  },
  {
    title: '分类一',
    dataIndex: 'category1Id',
    key: 'category1Id ',
    ellipsis: true,
  },
  {
    title: '分类二',
    dataIndex: 'category2Id',
    key: 'category2Id',
    ellipsis: true,
  },
  {
    title: '阅读量',
    dataIndex: 'viewCount',
    key: 'viewCount',
    ellipsis: true,
  },
  {
    title: '点赞数',
    dataIndex: 'voteCount',
    key: 'voteCount',
    ellipsis: true,
  },
  {
    title: 'delete', dataIndex: '', key: 'x', scopedSlots: {customRender: 'delete'}
  },
  {
    title: 'edit', dataIndex: '', key: 'y', scopedSlots: {customRender: 'edit'}
  }
];

const data = [];

export default {
  name: 'AdminBook',
  data() {
    return {
      data,
      columns,
    };
  },
  mounted() {
    axios.get('/ebook/list').then((resp) => {
      if (resp.status === 200) {
        this.data = resp.data.content
        console.log(this.data)
      } else {
        alert("数据加载失败")
      }
    })
  },
  methods:{
    deleteBook(id){
      console.log(id)
    }
  },
};
</script>
