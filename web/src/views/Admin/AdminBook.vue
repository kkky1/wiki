<template>
  <div>
    <a-button type="primary" @click="addModal()">
      新增
    </a-button>
    <!--        <success-alert style="display: inline-block"></success-alert>-->
    <a-table :pagination="false" rowKey="id" :columns="columns" :data-source="data.records">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <a slot="delete" slot-scope="text">
        <a-button type="danger" @click="deleteBook(text.id)">
          删除
        </a-button>
      </a>
      <a slot="edit" slot-scope="text" href="javascript:;">
        <a-button type="primary" @click="showModal(text.id)">
          修改
        </a-button>
      </a>
      <a slot="count" slot-scope="text" href="javascript:;">
        <router-link :to="`/concrete?ebookid=${text.id}`" >
          点击查看
        </router-link>
      </a>
    </a-table>
    <a-pagination :default-current="1" :defaultPageSize="data.size" :total="data.total*data.size" @change="changePage"/>
    <a-modal
        title="Title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk()"
        @cancel="handleCancel"
    >
      <edit :fromdata="fromData"/>
    </a-modal>
  </div>
</template>
<script>
import axios from "axios";
import edit from '@/views/Edit'
import successAlert from '@/components/Alert/AlertSuccess'

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
  },
  {
    title: '文档数', dataIndex: '', key: 'z', scopedSlots: {customRender: 'count'}
  }
];

const data = [];

export default {
  name: 'AdminBook',
  components: {
    edit, successAlert
  },
  data() {
    return {
      banner: false,
      closable: true,
      data,
      columns,
      total: 99,
      current: 1,
      pagesize: 3,
      visible: false,
      confirmLoading: false,
      fromData: {}
    };
  },
  mounted() {
    this.getCurrentList();
  },
  methods: {
    getCurrentList() {
      axios.get(`/ebook/list/${this.current}/${this.pagesize}`).then((resp) => {
        if (resp.status === 200) {
          this.data = resp.data.content
          console.log(this.data)
        } else {
          alert("数据加载失败")
        }
      })
    },
    deleteBook(id) {
      axios.delete(`/ebook/delete/${id}`).then((resp) => {
        if (resp.status === 200) {
          console.log("删除成功")
          this.getCurrentList()
        } else {
          alert("数据加载失败")
        }
      })
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
    showModal(id) {
      this.visible = true
      axios.get(`/ebook/detail/${id}`).then((resp) => {
        if (resp.status === 200 && resp.data.content != null) {
          this.fromData = resp.data.content;
        } else {
          alert("数据加载失败")
        }
      })
    },
    addModal() {
      this.visible = true
      this.fromData = {}
      this.getCurrentList()
    },
    handleOk() {
      console.log()
      axios.post(`/ebook/updateBook`, this.fromData).then((resp) => {
        if (resp.status === 200) {
          console.log("success")
        } else {
          alert("数据加载失败")
        }
      })
      this.visible = false
      this.getCurrentList()
    },
    handleCancel() {
      this.visible = false;
    },
  },


}
;
</script>

<style>
</style>
