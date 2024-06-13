<template>
  <div class="container home">
    <div class="row section1">
      <div class="col-lg-7 mt-5 pt-5 col-sm-12">
        <!-- <h1>Hello {{state.user.firstName}} </h1> -->
        <p>
          Get instant update about the patient history, medical request and
          manage appointments.
        </p>
        <!-- <router-link to="/about" class="btnlink mt-5">Read More</router-link> -->
      </div>
      <div class="col-lg-5">
        <img src="../assets/Doctors-bro 1.png" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  components:{
  },
  setup() {
    const state = reactive({
      
      userid:null,
      user:store.state.patient
    });
    const store = useStore();
    const router = useRouter();
    onMounted(() => {
      // if (store.state.patient == null) {
      //   state.userid=store.state.patient?store.state.patient.id:null
      //   router.push("/login");
      // }
    });
    
    return { state,store };
  },
};
</script>

</script>

<style>

</style>