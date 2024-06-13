<template>
  <div class="container-fluid">
    <div class="navbare">
      <router-link to="/" class="logo col-3">
        <img src="../../assets/brain.png" alt="" />
        <div>NEURO HEALTH</div>
      </router-link>
      <div class="list col-5">
        <ul>
          <li><router-link to="/"> Home</router-link></li>
          <li><router-link to="/services"> Services</router-link></li>
          <li><router-link to="/about"> About</router-link></li>
          <!-- <li><router-link to="/"> Home</router-link></li> -->
        </ul>
      </div>
      <div class="log col-3" v-if="state.login">
        <a class="abtn abtn2"  @click="login"> Logout</a>
        <i  class='fa fa-user'></i>
      </div>
      <div class="log col-3" v-else>
        <router-link class="abtn abtn1" to="/login"> Login </router-link>
        <router-link class="abtn abtn2" to="/signup"> Sign Up</router-link>
      </div>
    </div>
    <div class="navmedia">
      <div class="bar">
        <router-link to="/" class="logo col-3">
          <img src="../../assets/brain.png" alt="" />
          <router-link to="/" class="logo col-3">
        <div>NEURO HEALTH</div>
      </router-link>
        </router-link>
        <button @click="toggleButton" :class="isToggled?'active toggled':' toggled'">
          <i class="fa-solid fa-list-check"></i>
        </button>
      </div>
      <div :class="  isToggled?'active dorp':' dorp'">
        <div class="list col-5">
        <ul>
          <li><router-link to="/"> Home</router-link></li>
          <li><router-link to="/services"> Services</router-link></li>
          <li><router-link to="/about"> About</router-link></li>
          <!-- <li><router-link to="/"> Home</router-link></li> -->
        </ul>
      </div>
      <div class="log col-3" v-if="state.login">
        <a class="abtn abtn2"  @click="login"> Logout</a>
      </div>
      <div class="log col-3" v-else>
        <router-link class="abtn abtn1" to="/login"> Login </router-link>
        <router-link class="abtn abtn2" to="/signup"> Sign Up</router-link>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  data() {
    return {
      isToggled: false, // Initially the button is not toggled
    };
  },
  methods: {
    toggleButton() {
      // Toggle the value of isToggled
      this.isToggled = !this.isToggled;
      console.log(this.isToggled);
    },
  },
  setup() {
    const state = reactive({
      login: true
    });
    const store = useStore();
    const router = useRouter();
    onMounted(() => {
      console.log(store.state.patient);
      if (store.state.patient == null) {
        state.login=false
      }
    });
    const login = async () => {
        try {
          await store.dispatch("patientLogout");
          // router.push("/login");
        } catch (err) {
          console.log(err);
        }
      
    };
    return { state, login };
  },
};
</script>

<style lang="scss">
.container-fluid {
  margin: 0 !important;
  width: 100% !important;
  padding: 0 !important;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 10000;
}
.navbare {
  background-color: var(--maincolor);
  color: var(--whitecolor);
  width: 100%;
  height: 70px;
  padding: 10px 40px 10px 40px;
  display: flex;
  align-items: center;
  gap: 20px;
  .logo {
    display: flex;
    align-items: center;
    justify-items: flex-end;
    gap: 10px;
    padding: 0 0 0 30px;
    // flex: 1;
    text-decoration: none;
    color: inherit;
    div {
      font-size: 25px;
      font-weight: bold;
    }
  }
  .list {
    // flex: 3;
    ul {
      display: flex;
      align-items: center;
      justify-content: start;
      gap: 10px;
      list-style: none;
      margin: 0;
      a {
        color: var(--whitecolor);
        text-decoration: none;
        font-family: Poppins;
        font-size: 17px;
        font-weight: 400;
        line-height: 30px;
        text-align: center;
        opacity: 0.7;
      }
      a.router-link-active {
        font-weight: 700;
        font-size: 20px;
        opacity: 1;
      }
    }
  }
  .log {
    // flex: 1;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    gap: 20px;
  }
}
.navmedia {
  display: none;
  background-color: var(--maincolor);
  color: var(--whitecolor);
  width: 100%;
  height: 70px;
  padding: 10px 40px 10px 40px;
  
  gap: 20px;
  .bar {
    justify-content: space-between;
    display: flex;
    align-items: center;
    gap: 20px;
    .logo {
    display: flex;
    align-items: center;
    justify-items: flex-end;
    gap: 10px;
    padding: 0 0 0 30px;
    text-decoration: none;
    color: inherit;
    div {
      font-size: 25px;
      font-weight: bold;
    }
  }
  .toggled{
    width: 50px;
    display: block;
    background-color: transparent;
    color: var(--whitecolor);
    border: none;
    font-size: 30px;
    z-index: 10000;
  }
  }
  .dorp{
    padding: 20px 30px;
    background-color: var(--maincolor);
    transform: translateY(-100%);
    opacity: 0;
    transition: opacity, transform .5s ease-in-out;
    .list {
    // flex: 3;
    ul {
      // display: flex;
      // align-items: center;
      // justify-content: start;
      gap: 10px;
      width: 100%;
      list-style: none;
      margin: 0;
      a {
        color: var(--whitecolor);
        text-decoration: none;
        font-family: Poppins;
        font-size: 17px;
        font-weight: 400;
        line-height: 30px;
        text-align: center;
        opacity: 0.7;
      }
      a.router-link-active {
        font-weight: 700;
        font-size: 20px;
        opacity: 1;
      }
    }
  }
  .log {
    // flex: 1;
    margin: 20px 0;
    display: flex;
      width: 100%;
    align-items: center;
    gap: 20px;
  }
  }
  .dorp.active{
    padding: 20px 30px;
    background-color: var(--maincolor);
    transform: translateY(0%);
    opacity: 1;
  }
}
@media (max-width: 991px) {
  .navmedia {
    display: block;
    // display: flex;
  align-items: center;
  }
  .navbare {
    display: none;
  }
}
</style>