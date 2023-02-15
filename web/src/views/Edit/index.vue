<template>
  <a-form :model="fromdata" :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="名称">
      <a-input v-model="fromdata.name"/>
    </a-form-item>
    <a-form-item label="分类一">
      <a-input v-model="fromdata.category1Id"/>
    </a-form-item>
    <a-form-item label="分类二">
      <a-input v-model="fromdata.category2Id"/>
    </a-form-item>
    {{ fromdata }}
    <a-form-item label="阅读量">
      <a-input-number v-model="fromdata.viewCount"/>
    </a-form-item>
    <a-form-item label="点赞数">
      <a-input-number v-model="fromdata.voteCount"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
    </a-form-item>
  </a-form>
</template>

<script>

export default {
  name: 'Edit',
  props: ['fromdata'],
  mounted() {
    this.form.setFieldsValue({
      'name': this.fromdata.name,
      'category1Id': this.fromdata.category1Id,
      'category2Id': this.fromdata.category2Id,
      'read': this.fromdata.viewCount,
      'like': this.fromdata.voteCount
    })
  },
  data() {
    return {
      formLayout: 'horizontal',
      form: this.$form.createForm(this),
    };
  },
  methods: {
    handleSubmit(e) {
      alert("213")
      e.preventDefault();
      console.log("213")
      // axios.get(`/ebook/detail/${id}`).then((resp) => {
      //   if (resp.status === 200 && resp.data.content != null) {
      //     this.fromData = resp.data.content;
      //   } else {
      //     alert("数据加载失败")
      //   }
      // })
    },
  },
};
</script>