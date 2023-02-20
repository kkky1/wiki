const state = {
    userInfo: sessionStorage.getItem("USER")||{}
}

const mutations = {
    SENDUSERINFO(state, value) {
        state.userInfo = value
        sessionStorage.setItem("USER",JSON.stringify(value))
    }
}

const actions = {}

export default {
    state, mutations, actions
}