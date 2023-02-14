<template>
  <div>
    <a-table :pagination="false" rowKey="id" :columns="columns" :data-source="data.records">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <a slot="delete" slot-scope="text">
        <a-button type="danger" @click="deleteBook(text.id)">
          删除
        </a-button>
      </a>
      <a slot="edit" slot-scope="text" href="javascript:;">
        <a-button type="primary" @click="showModal">
          修改
        </a-button>
      </a>
    </a-table>
    {{ data.size }} {{ data.total }}
    <a-pagination :default-current="1" :defaultPageSize="data.size" :total="data.total*data.size" @change="changePage"/>

    <a-modal
        title="Title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
    >
      <edit/>
      <p>   </p>
    </a-modal>
  </div>
</template>
<script>
import axios from "axios";
import edit from '@/views/Edit'
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
  components:{
    edit
  },
  data() {
    return {
      data,
      columns,
      total: 99,
      current: 1,
      pagesize: 3,
      visible: false,
      confirmLoading: false,
    };
  },
  mounted() {
    axios.get(`/ebook/list/${this.current}/${this.pagesize}`).then((resp) => {
      if (resp.status === 200) {
        this.data = resp.data.content
        console.log(this.data)
      } else {
        alert("数据加载失败")
      }
    })
  },
  methods: {
  deleteBook(id) {
      console.log(id)
    },
  changePage(page, pageSize) {
      console.log(page, pageSize)
      axios.get(`/ebook/list/${page}/${this.pagesize}`).then((resp) => {
        if (resp.status === 200) {
          this.data = resp.data.content
          console.log(this.data)
        } else {
          alert("数据加载失败")
        }
      })
    },
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      this.ModalText = 'The modal will be closed after two seconds';
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel(e) {
      console.log('Clicked cancel button');
      this.visible = false;
    },
  },

}
;
</script>

<style>
</style>
