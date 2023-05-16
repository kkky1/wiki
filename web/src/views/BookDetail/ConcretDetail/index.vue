<template>
  <div>
    <a-row>
      <a-col :span="8">
        <a-button type="primary" @click="addModal()">
          新增
        </a-button>

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
            @cancel="handleCancel"
        >
        </a-modal>
      </a-col>


      <a-col :span="16">
        <a-button type="primary" @click="saveDoc">保存</a-button>
        <editDoc :fromdata="fromData"/>
      </a-col>
    </a-row>



  </div>
</template>
<script>
import {Tool} from "@/util/tool";
import axios from "axios";
import editDoc from '@/views/Edit/editDoc'
import successAlert from '@/components/Alert/AlertSuccess'
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
  name: 'concrete',
  components: {
    editDoc, successAlert
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
      fromData: {},
    };
  },
  mounted() {
    this.findContent()
  },
  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  },
  methods: {
    deleteDoc(id) {
      axios.delete(`/doc/delete/${id}`).then((resp) => {
        if (resp.status === 200) {
          this.$message.success("删除成功")
          this.findContent()
        } else {
          this.$message.error("操作失败")
        }
      })
    },
    changePage(page, pageSize) {
      axios.get(`/doc/list/${page}/${this.pagesize}`).then((resp) => {
        if (resp.status === 200) {
          this.data = resp.data.content
        } else {
          alert("数据加载失败")
        }
      })
    },
    showModal(id) {
      this.visible = false
      this.findContent(id)
      axios.get(`/doc/detail/${id}`).then((resp) => {
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
      this.findContent()
    },
    saveDoc() {
      axios.post(`/doc/updateDoc`, this.fromData).then((resp) => {
        console.log(this.fromData)
        if (resp.status === 200) {
          this.$
        } else {
          alert("数据加载失败")
        }
      })
      this.visible = false
      this.findContent()
    },
    findContent(){
      axios.get(`/doc/doclist/${this.$route.query.ebookid}`).then((resp) => {
        console.log(resp)
        if (resp.status === 200) {
          this.fromData.content = resp.data.content;
          this.data = Tool.array2Tree(resp.data.content,0)

        } else {
          alert("数据加载失败")
        }
      })
    },
    handleCancel(e) {
      this.visible = false;
    },
  },
};
</script>

<style>

</style>

