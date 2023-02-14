<template>
  <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="名称">
      <a-input
          v-decorator="['name', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="分类一">
      <a-input
          v-decorator="['category1Id', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="分类二">
      <a-input
          v-decorator="['category2Id', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="阅读量">
      <a-input-number v-decorator="['read', { initialValue: 3 } ]"/>
    </a-form-item>
    <a-form-item label="点赞数">
      <a-input-number v-decorator="['like', { initialValue: 3 }]" setFieldsValue="fromdata.voteCount"/>
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
      'name':this.fromdata.name,
      'category1Id':this.fromdata.category1Id,
      'category2Id':this.fromdata.category2Id,
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
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
        }
      });
    },
  },
};
</script>