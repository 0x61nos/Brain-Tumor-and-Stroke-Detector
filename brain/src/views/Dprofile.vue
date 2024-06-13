<template>
  <div class="container home">
    <div class="row section1">
      <div class="col-lg-7 mt-5 pt-5 col-sm-12">
        <h1>Hello Doctor {{ state.filteredItems.firstName }}</h1>
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
  <Requests :doctorid="store.state.patient.roles[0].id" />
  <!-- <Upcoming /> -->
</template>
<script>
import { reactive,computed,onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import Requests from '@/components/Doctor/Requests.vue'
import Upcoming from '@/components/Doctor/Upcoming.vue';
import axios from "axios";
export default {
  components: {
    Requests,
    Upcoming
  },
  setup(props) {
    const state = reactive({
      filteredItems:{},
      doctors: [
        {
          id: 1,
          name: "Dr.Sama",
          location: "Egypt",
          rate: 5,
          img: "doc1",
          desc:'Tumor specialist1'
        },
        {
          id: 2,
          name: "Dr.Eslam",
          location: "Egypt",
          rate: 5,
          img: "doc2",
          desc:'Tumor specialist2'
        },
        {
          id: 3,
          name: "Dr.Alia",
          location: "Egypt",
          rate: 5,
          img: "doc3",
          desc:'Tumor specialist3'
        },
      ],
    });
    const router = useRouter();
    const store = useStore();
    onMounted(async()=>{
      if(store.state.patient.roles[0].name!="doctor"){
        router.push("/login");
      }
      await axios.get('http://localhost:8080/api/v1/user/'+store.state.patient.roles[0].id)
      .then(res=>{
        // console.log(res.data);
        state.filteredItems=res.data
      })
      .catch(err=>{
        console.log(err);
      })
      // state.filteredItems=state.doctors.filter(item => item.id == props.id);
      // =state.filteredItems[0];
      // console.log(state.filteredItems.img);
    })
  return { state,store };
}
}
</script>

<style>

</style>