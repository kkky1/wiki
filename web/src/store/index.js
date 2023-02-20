import Vuex from "vuex";
import Vue from "vue";
Vue.use(Vuex)
import UserContent from "@/store/UserContent";
import BookContent from "@/store/BookContent";
export default new Vuex.Store({
    modules:{
        BookContent,UserContent
    }
})
