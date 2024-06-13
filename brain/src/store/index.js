import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";
import createPersistedState from "vuex-persistedstate";
import * as cookies from "js-cookie";
import { data } from "jquery";
// State

const state = {
  user: null,
  patient: null,
};

// Getters

const getters = {
  patient: (state) => {
    return state.patient;
  },
};

// Mutations

const mutations = {
  // Admin
  // setUser(state, user) {
  //   state.user = user;
  // },

  // patient
  setpatient(state, patient) {
    state.patient = patient;
  },

  // setpatientBrach(state, bID) {
  //   state.patient.branch_id = bID;
  // },

  redirectTo(state, payload) {
    router.push({
      name: payload.name,
      params: payload.params,
    });
  },
};

// Actions

const actions = {
  redirectTo({ commit }, payload) {
    commit("redirectTo", { name: payload.name, params: payload.params });
  },

  // Login Admin
  // async AdminLogin(context, { email, password }) {
  //   try {
  //     let response = await axios.post("api/v1/auth/login", {
  //       email: email,
  //       password: password,
  //     });
  //     if (response.status == 200) {
  //       localStorage.setItem("token", response.data.access_token);
  //       axios.defaults.headers.common["Authorization"] =
  //         "Bearer " + localStorage.getItem("token");
  //       // if (response.data.user.role_id == 2) {
  //       //   localStorage.setItem(
  //       //     "branch_id",
  //       //     response.data.user.branchHead_manager[0].id
  //       //   );
  //       // }
  //       // context.commit("setUser", response.data.user);
  //     } else {
  //       throw new Error("Could not Complete Admin Login ..");
  //     }
  //   } catch (err) {
  //     throw new Error(err.response.data.error);
  //   }
  // },


  //patient Signup

  async patientSignup(context, data) {
    console.log(data);
    try {
      let response = await axios.post("api/v1/auth/signUp", data);
      if (response.status == 200) {
        console.log(response.data.user);
        localStorage.setItem("patient_id", response.data.user.id);
        localStorage.setItem("patient", response.data.user.firstName);
        context.commit("setpatient", response.data.user);
        localStorage.setItem("token", response.data.accessToken);
        localStorage.setItem("refreshToken", response.data.refreshToken);
        axios.defaults.headers.common["Authorization"] =
          "Bearer " + localStorage.getItem("token");
      } else {
        throw new Error("Could not Complete patient Login ..");
      }
    } catch (err) {
       console.log(err);
    }
  },
  async patientLogin(context, data) {
    try {
      let response = await axios.post("api/v1/auth/login",data );
      if (response.status == 200) {
        // console.log(response.data.user.roles);
        localStorage.setItem("patient_id", response.data.user.id);
        localStorage.setItem("patient", response.data.user.firstName);
        context.commit("setpatient", response.data.user);
        localStorage.setItem("token", response.data.accessToken);
        localStorage.setItem("refreshToken", response.data.refreshToken);

        axios.defaults.headers.common["Authorization"] =
          "Bearer " + localStorage.getItem("token");
      } 

    } catch (err) {
       console.log(err);
    }
  },

  async patientLogout(context) {
    console.log(12345);
    axios.post("api/v1/auth/logout",{
      refreshToken:localStorage.getItem("refreshToken")
    })
    .then(res => console.log(res))
    .catch(err => {
      context.commit("setpatient", null);
      localStorage.clear();
      router.push("/login");
    })
    // try {
    //   let res = await axios.post("api/v1/auth/logout");
    //   console.log(res);
      // context.commit("setpatient", null);
      // localStorage.clear();
      
    //   // router.push("/login");
    // } catch (err) {
    // }
  },

  // patient
  patient(context, patient) {
    context.commit("patient", patient);
  },
}; // end actions
const modules = {};
export default createStore({
  state,
  getters,
  mutations,
  actions,
  modules,
  plugins: [
    createPersistedState({
      getState: (key) => cookies.getJSON(key),
      setState: (key, state) =>
        cookies.set(key, state, { expires: 3, secure: true }),
    }),
  ],
});