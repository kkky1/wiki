<template>
  <a-pagination :default-current="1" :total="total" :defaultPageSize="size" />
</template>

<script>
export default {
  name: 'Pagination',
  props:['current','total','size'],
  mounted() {
    console.log(this.total)
    console.log(this.size)
  }
}
</script>

<style scoped>

</style>