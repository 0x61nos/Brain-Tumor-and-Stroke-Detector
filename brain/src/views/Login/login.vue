<template>
  <div class="container">
    <div class="div">
      <div class="row">
        <div class="col-lg-5 col-md-12">
          <img src="../../assets/Login.png" alt="" />
        </div>
        <div class="col-lg-6 col-md-12">
          <div class="form p-5">
            <h2>Welcome Back!</h2>
            <p>Login to continue</p>
            <form @submit.prevent="login">
              <label for="">Email</label>
              <div class="input">
                <input
                  type="text"
                  v-model="state.email"
                  placeholder="Please enter your email"
                />
              </div>
              <span class="text-danger fw-bold" v-if="v$.email.$error">
                {{ v$.email.$errors[0].$message }} </span
              ><br />
              <label for="">Password</label>
              <div class="input">
                <input
                  :type="state.showPassword ? 'text' : 'password'"
                  v-model="state.password"
                  placeholder="Please enter your Password"
                />
                <span class="toggle" @click="toggleShowPassword">
                  <i class="fas fa-eye-slash"></i>
                </span>
              </div>
              <span class="text-danger fw-bold" v-if="v$.password.$error">
                {{ v$.password.$errors[0].$message }}
              </span>
              <div class="row mt-2">
                <router-link to="/c" class="text-end"
                  >Forget Pasword!</router-link
                >
              </div>
              <div class="row mt-2">
                <div class="col-12">
                  <button type="submit" class="btnlink">Login</button>
                </div>
              </div>
            </form>
            <div class="row mt-3">
              <div class="col-12">
                <span>New User? </span>
                <router-link to="/signup"> Create Account</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";

import { useVuelidate } from "@vuelidate/core";
import { required, email, minLength } from "@vuelidate/validators";
export default {
  setup() {
    const state = reactive({
      email: "",
      password: "",
      showPassword: false,
    });
    const store = useStore();
    onMounted(() => {
      console.log(store.state.patient);
      if (store.state.patient != null) {
        router.push("/");
      }
    });
    const router = useRouter();
    const rules = computed(() => {
      return {
        email: { email, required },
        password: { required, minLength: minLength(3) },
      };
    });
    const v$ = useVuelidate(rules, state);
    const login = async () => {
      v$.value.$validate();
      if (!v$.value.$error) {
        try {
          await store.dispatch("patientLogin", {
            email: state.email,
            password: state.password,
          });
          if(store.state.patient.roles!=null&&store.state.patient.roles[0].name=="doctor")
          {
            router.push("/dprofile/"+store.state.patient.id);
          }
          else router.push("/services");
        } catch (err) {
          console.log(err);
        }
      } else {
      }
    };
    function toggleShowPassword() {
      state.showPassword = !state.showPassword;
      let x = document.querySelector(".fa-eye-slash")
        ? document.querySelector(".fa-eye-slash")
        : document.querySelector(".fa-eye");
      x.classList.toggle("fa-eye-slash");
      x.classList.toggle("fa-eye");
    }

    return { state, login, v$, toggleShowPassword };
  },
};
</script>

<style lang="scss">
.div {
  margin: 80px 0 0 0;
  .toggle {
    background: none;
    border: none;
    cursor: pointer;
  }
  img {
    max-width: 100%;
  }
  h2 {
    color: var(--textcolor);
    font-weight: bold;
  }
  p {
    font-size: 15px;
    margin: 0;
  }
  form {
    label {
      color: var(--textcolor);
      font-weight: bold;
      margin: 10px 0;
    }
    .input {
      width: 100%;
      background-color: #e6f0ed;
      padding: 5px;
      border-radius: 10px;
      input {
        background-color: #e6f0ed;
        width: 93%;
        border: none;
        outline: none;
        padding: 0px 10px;
      }
      svg {
        color: var(--textcolor) !important;
      }
    }
    span {
      margin-right: 10px;
    }
  }
  a {
    color: var(--textcolor);
    text-decoration: none;
    display: inline;
  }
}
</style>