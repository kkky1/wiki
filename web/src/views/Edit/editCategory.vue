<template>
  <a-form :model="fromdata" :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="名称">
      <a-input v-model="fromdata.name"/>
    </a-form-item>
    <a-form-item label="父分类">
      <a-input v-model="fromdata.parent"/>
    </a-form-item>
    <a-form-item label="顺序">
      <a-input-number v-model="fromdata.sort"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
    </a-form-item>
  </a-form>
</template>

<script>

export default {
  name: 'editCategory',
  props: ['fromdata'],
  mounted() {
    this.form.setFieldsValue({
      'name': this.fromdata.name,
      'parent': this.fromdata.parent,
      'sort': this.fromdata.sort
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