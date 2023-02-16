<template>
  <div>
    <a-card class="cross" hoverable style="width: 300px">
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
      <a-card-meta :title="bookContent.name" >
        <template #avatar>
          <a-avatar src="https://joeschmoe.io/api/v1/random"/>
        </template>
      </a-card-meta>
    </a-card>
  </div>
</template>
<script>
export default {
  name: 'Mycontent',
  props: ['bookContent'],
  updated() {
    console.log("123",this.bookContent)
  },
  computed: {
    books() {
      return this._props.bookContent.content
    }
  }
};
</script>

<style>
.cross {
  display: inline-block;
  margin: 20px 40px;
  padding-left: 30px;
}
</style>