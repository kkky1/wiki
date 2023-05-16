<template>
  <a-form :model="fromdata" :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">

    <a-form-item label="名称">
      <a-input  v-model="fromdata.name"/>
    </a-form-item>
    <a-form-item label="父分类">
      <a-input v-model="fromdata.parent"/>
    </a-form-item>
    <a-form-item label="电子书">
      <a-input v-model="fromdata.ebookId"/>
    </a-form-item>
    <a-form-item label="顺序">
      <a-input-number v-model="fromdata.sort"/>
    </a-form-item>
    <a-form-item label="内容">
      <div id="editor">
        <Toolbar
            class="w-e-toolbar"
            style="border-bottom: 1px solid #ccc"
            :editor="editor"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            class=".w-e-text-container"
            style="height: 500px; overflow-y: hidden;"
            v-model="fromdata.content"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="onCreated"
        />
      </div>

    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
    </a-form-item>
  </a-form>
</template>

<script>

import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
  name: 'editDoc',
  components: { Editor, Toolbar },
  props: ['fromdata'],
  mounted() {
    this.form.setFieldsValue({
      'name': this.fromdata.name,
      'parent': this.fromdata.parent,
      'sort': this.fromdata.sort,
      'ebookId': this.fromdata.ebookId,
      'content': this.fromdata.content
    }),
        setTimeout(() => {
          this.html = this.fromdata.content
        }, 1500)
  },
  data() {
    return {
      formLayout: 'horizontal',
      form: this.$form.createForm(this),
      editor: null,
      html: '<p></p>',
      toolbarConfig: { },
      editorConfig: { placeholder: '请输入内容...' },
      mode: 'default', // or 'simple'
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
    },
    onCreated(editor) {
      this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
    },
  },
};
</script>

<style src="@wangeditor/editor/dist/css/style.css">

</style>