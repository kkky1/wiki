<template>
  <div>
    <a-row type="flex">
      <a-col :flex="2">
        <div>
          <a-button type="primary" @click="addModal()">
            新增
          </a-button>
          <!--        <success-alert style="display: inline-block"></success-alert>-->
          <a-table :pagination="false" rowKey="id" :columns="columns" :data-source="data">
            <a slot="name" slot-scope="text">{{ text }}</a>
            <a slot="delete" slot-scope="text">
              <a-button type="danger" @click="deleteDoc(text.id)">
                删除
              </a-button>
            </a>
            <a slot="edit" slot-scope="text" href="javascript:;">
              <a-button type="primary" @click="showModal(text.id)">
                修改
              </a-button>
            </a>
          </a-table>
          <a-pagination :pagination="false" :default-current="1" :defaultPageSize="data.size" :total="data.total*data.size"
                        @change="changePage"/>
          <a-modal
              title="文档"
              :visible="visible"
              :confirm-loading="confirmLoading"
              @ok="handleOk()"
              @cancel="handleCancel"
          >
            <editDoc :fromdata="fromData"/>
          </a-modal>
        </div>
      </a-col>
      <a-col :flex="3">3 / 5</a-col>
    </a-row>
  </div>
</template>

<script>
import editDoc from '@/views/Edit/editDoc'
import {Tool} from "@/util/tool";
import axios from "axios";
import successAlert from '@/components/Alert/AlertSuccess'
import docBook from '@/views/Admin/DocBook'
const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    scopedSlots: {customRender: 'name'},
  },
  {
    title: '父分类',
    dataIndex: 'parent',
    key: 'parent',
    width: 80,
  },
  {
    title: '排序',
    dataIndex: 'sort',
    key: 'sort ',
    ellipsis: true,
  },
  {
    title: 'delete', dataIndex: '', key: 'x', scopedSlots: {customRender: 'delete'}
  },
  {
    title: 'edit', dataIndex: '', key: 'y', scopedSlots: {customRender: 'edit'}
  }
];
const data = [{
  id: '',
  name: '',
  children: [{
    id: '',
    name: ''
  }]
}];
export default {
  name: 'DocContent',
  components: {
    docBook, successAlert,editDoc
  },
  data() {
    return {
      banner: false,
      closable: true,
      data,
      columns,
      total: 1,
      current: 1,
      pagesize: 1000,
      visible: false,
      confirmLoading: false,
      fromData: []
    };
  },
  mounted() {
    axios.get(`/doc/doclist/${this.$route.query.ebookId}`).then((resp) => {
      if (resp.status === 200) {
        console.log(resp)
        this.data = Tool.array2Tree(resp.data.content,0)
      } else {
        alert("数据加载失败")
      }
    })
  },
  methods: {
    getCurrentList() {
      axios.get(`/doc/list`).then((resp) => {
        if (resp.status === 200) {
          console.log(resp.data.content)
          this.data = Tool.array2Tree(resp.data.content,0)
          console.log(Tool.array2Tree(resp.data.content,0))
        } else {
          alert("数据加载失败")
        }
      })
    },
    deleteDoc(id) {
      axios.delete(`/doc/delete/${id}`).then((resp) => {
        if (resp.status === 200) {
          message.success("删除成功")
          this.getCurrentList()
        } else {
          alert("数据加载失败")
        }
      })
    },
    changePage(page, pageSize) {
      console.log(page, pageSize)
      axios.get(`/doc/list/${page}/${this.pagesize}`).then((resp) => {
        if (resp.status === 200) {
          this.data = resp.data.content
          console.log(this.data)
        } else {
          message.error("数据加载错误")
        }
      })
    },
    showModal(id) {
      console.log(id)
      this.visible = true
      axios.get(`/doc/detail/${id}`).then((resp) => {
        if (resp.status === 200 && resp.data.content != null) {
          this.fromData = resp.data.content;
          message.success("编辑成功")
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
      axios.post(`/doc/updateDoc`, this.fromData).then((resp) => {
        if (resp.status === 200) {
          console.log(this)
        } else {
          alert("数据加载失败")
        }
      })
      this.visible = false
      this.getCurrentList()
    },
    handleCancel(e) {
      console.log('Clicked cancel button');
      this.visible = false;
    },
  },
};
</script>