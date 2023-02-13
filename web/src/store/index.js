import Vuex from "vuex";
import Vue from "vue";
Vue.use(Vuex)
import BookContent from "@/store/BookContent";
export default new Vuex.Store({
    modules:{
        BookContent
    }
})
