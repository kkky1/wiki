<template>
  <div>
    <a-card class="cross" hoverable style="width: 300px" v-for="one,index in books" :key="index">
      <template #cover>
        <img
            alt="example"
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
        />
      </template>
      <template #actions>
        <setting-outlined key="setting"/>
        <edit-outlined key="edit"/>
        <ellipsis-outlined key="ellipsis"/>
      </template>
      <a-card-meta :title="one.name" :description="one.description">
        <template #avatar>
          <a-avatar src="https://joeschmoe.io/api/v1/random"/>
        </template>
      </a-card-meta>
    </a-card>
  </div>
</template>
<script>
import {SettingOutlined, EditOutlined, EllipsisOutlined} from '@ant-design/icons-vue';
import {defineComponent} from 'vue';

export default defineComponent({
  name: 'Mycontent',
  components: {
    SettingOutlined,
    EditOutlined,
    EllipsisOutlined,
  },
  props: ['bookContent'],
  mounted() {
    console.log(this)
  },
  computed: {
    books() {
      return this._props.bookContent.content
    }
  }
});
</script>

<style>
.cross {
  display: inline-block;
  margin: 20px 40px;
  padding-left: 30px;
}
</style>