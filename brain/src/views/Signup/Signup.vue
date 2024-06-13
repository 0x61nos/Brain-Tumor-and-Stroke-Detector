<template>
  <div class="container">
    <div class="div">
      <div class="row">
        <div class="col-lg-5 col-md-12">
          <img src="../../assets/Login.png" alt="" />
        </div>
        <div class="col-lg-7 col-md-12">
          <div class="form p-5">
            <h2>Create an account</h2>
            <p>Sing up to continue</p>
            <form @submit.prevent="signup">
              <div class="row">
                <div class="col-6">
                  <label for="">Name : </label>
                  <div class="input">
                    <input
                      type="text"
                      v-model="state.firstName"
                      placeholder="Enter your Name"
                    />
                  </div>
                  <span class="text-danger fw-bold" v-if="v$.firstName.$error">
                    {{ v$.firstName.$errors[0].$message }} </span
                  ><br />
                </div>
                <div class="col-6">
                  <label for="">LastName : </label>
                  <div class="input">
                    <input
                      v-bind:type="'text'"
                      placeholder="Enter your LastName"
                      v-model="state.lastName"
                    />
                  </div>
                  <span class="text-danger fw-bold" v-if="v$.email.$lastName">
                    {{ v$.lastName.$errors[0].$message }} </span
                  ><br />
                </div>
              </div>
              <div class="row">
                <label for="">Email : </label>
                <div class="input">
                  <input
                    type="text"
                    v-model="state.email"
                    placeholder="Please enter your email"
                  />
                  <span class="text-danger fw-bold" v-if="v$.email.$error">
                    {{ v$.email.$errors[0].$message }} </span
                  ><br />
                </div>
                <label for="">Password : </label>
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
                  {{ v$.password.$errors[0].$message }} </span
                ><br />
              </div>
              <div class="row mt-4">
                <div class="col-12">
                  <button class="btnlink" type="submit">Sign Up</button>
                </div>
              </div>
            </form>
            <div class="row mt-3">
              <div class="col-12">
                <span>Olready have an account? </span>
                <router-link to="/signup"> Login</router-link>
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
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      showPassword: false,
    });
    const store = useStore();
    onMounted(() => {
      if (store.state.patient != null) {
        // router.push("/");
      }
    });
    const router = useRouter();
    const rules = computed(() => {
      return {
        email: { email, required },
        password: { required, minLength: minLength(6) },
        firstName: { required, minLength: minLength(3) },
        lastName: { required, minLength: minLength(2) },
      };
    });
    const v$ = useVuelidate(rules, state);
    const signup = async () => {
      v$.value.$validate();
      if (!v$.value.$error) {
        try {
          await store.dispatch("patientSignup", {
            email: state.email,
            firstName: state.firstName,
            lastName: state.lastName,
            password: state.password,
          });
          router.push("/");
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

    return { state, signup, v$, toggleShowPassword };
  },
};
</script>

<style lang="scss">
.div {
  margin: 80px 0 0 0;
}
</style>