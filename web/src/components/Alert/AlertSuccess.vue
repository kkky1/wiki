<template>
  <a-button ref="successMessage" type="primary" @click="successalert(message)">
      {{message}}
  </a-button>
</template>
<script>
export default {
  methods: {
    successalert(message) {
      if (message.success){
        console.log("message",message)
        console.log(message.success)
        this.$message.success(`${message.message}`);
      }
      else {
        console.log("message",message)
        console.log(message.success)
        this.$message.warning(`${message.message}`);
      }
    },
  },
};
</script>
