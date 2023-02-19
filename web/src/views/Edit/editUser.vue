<template>
  <a-form :model="fromdata" :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="名称">
      <a-input v-model="fromdata.loginName" :disabled="!!fromdata.id"/>
    </a-form-item>
    {{fromdata}}
    <a-form-item label="昵称">
      <a-input v-model="fromdata.name" />
    </a-form-item>
    <a-form-item label="密码">
      <a-input-number v-model="fromdata.password"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
    </a-form-item>
  </a-form>
</template>

<script>

export default {
  name: 'editUser',
  props: ['fromdata'],
  mounted() {
    this.form.setFieldsValue({
      'loginName': this.fromdata.loginName,
      'name': this.fromdata.name,
      'password': this.fromdata.password
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