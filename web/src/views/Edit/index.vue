<template>
  <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="名称">
      <a-input
          v-decorator="['名称', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="分类一">
      <a-input
          v-decorator="['分类一', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="分类二">
      <a-input
          v-decorator="['分类二', { rules: [{ required: true, message: 'Please input your note!' }] }]"
      />
    </a-form-item>
    <a-form-item label="阅读量">
      <a-input-number v-decorator="['input-number', { initialValue: 3 }]" :min="1" :max="10"/>
    </a-form-item>
    <a-form-item label="点赞数">
      <a-input-number v-decorator="['input-number', { initialValue: 3 }]" :min="1" :max="10"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
    </a-form-item>
  </a-form>
</template>

<script>
export default {
  name: 'Edit',
  data() {
    return {
      formLayout: 'horizontal',
      form: this.$form.createForm(this, {name: 'coordinated'}),
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