<template>
  <a-form-model layout="inline" :model="formInline" @submit="handleSubmit" @submit.native.prevent>
    <a-form-model-item>
      <a-input v-model="formInline.loginName" placeholder="loginName">
        <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
      </a-input>
    </a-form-model-item>
    <a-form-model-item>
      <a-input v-model="formInline.password" type="password" placeholder="Password">
        <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
      </a-input>
    </a-form-model-item>
    <a-form-model-item>
      <a-button
          type="primary"
          html-type="submit"
          :disabled="formInline.loginName === '' || formInline.password === ''"
      >
        Log in
      </a-button>
    </a-form-model-item>
  </a-form-model>
</template>
<script>
import axios from "axios";
export default {
  name:'userLogin',
  props:['formInline'],
  data() {
    return {

    };
  },
  methods: {
    handleSubmit(e) {

    },
  },
};
</script>
