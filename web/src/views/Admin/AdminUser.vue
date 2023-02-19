<template>
  <div>
    <a-button type="primary" @click="addModal()">
      新增
    </a-button>
            <success-alert ref="message" style="display: inline-block"></success-alert>
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
    </a-table>
    <a-pagination :default-current="1" :defaultPageSize="data.size" :total="data.total*data.size" @change="changePage"/>
    <a-modal
        title="Title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk()"
        @cancel="handleCancel"
    >
      <editUser :fromdata="fromData"/>
      <p></p>
    </a-modal>

  </div>
</template>
<script>
import axios from "axios";
import editUser from '@/views/Edit/editUser'
import successAlert from '@/components/Alert/AlertSuccess'

const columns = [
  {
    title: '登录名',
    dataIndex: 'loginName',
    key: 'loginName',
    scopedSlots: {customRender: 'loginName'},
  },
  {
    title: '昵称',
    dataIndex: 'name',
    key: 'name',
    width: 80,
  },
  {
    title: '密码',
    dataIndex: 'password',
    key: 'password ',
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
  name: 'AdminUser',
  components: {
    editUser, successAlert
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
      axios.get(`/user/list/${this.current}/${this.pagesize}`).then((resp) => {
        if (resp.status === 200) {
          this.data = resp.data.content
          console.log(this.data)
        } else {
          alert("数据加载失败")
        }
      })
    },
    deleteBook(id) {
      axios.delete(`/user/delete/${id}`).then((resp) => {
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
      axios.get(`/user/list/${page}/${this.pagesize}`).then((resp) => {
        if (resp.data.success) {
          this.data = resp.data.content
          console.log(this.data)
        } else {
          alert("数据加载失败")
        }
      })
    },
    showModal(id) {
      this.visible = true
      axios.get(`/user/detail/${id}`).then((resp) => {
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
      // this.ModalText = 'The modal will be closed after two seconds';
      axios.post(`/user/updateBook`, this.fromData).then((resp) => {
        if (resp.data.success) {
          console.log(this.$refs.message.successalert(resp.data))
          this.visible = false
          this.getCurrentList()
        } else {
          this.$refs.message.successalert(resp.data)
        }
      })

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
