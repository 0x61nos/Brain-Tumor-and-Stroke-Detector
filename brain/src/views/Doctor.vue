<template>
  <div class="doctor container mb-5">
    <div class="row">
       <div class="col-lg-3 col-md-4">
        <div class="image">
          <img :src="state.filteredItems.imageProfile" alt="">
        </div>
      </div>
      <div class="col-lg-9 col-md-8">
        <div class="details">
          <h2>{{ state.filteredItems.firstName +' '+state.filteredItems.lastName }}</h2>
          <h3>{{ state.filteredItems.desc }}</h3>
          <p>
            <i class="fa-solid fa-location-dot"></i>
            {{ state.filteredItems.location }}</p>
          <div class="stars">
            <h4>Rate here</h4>
            <i
              v-for="(star, index) in 5"
              class="fa fa-star"
              :key="index"
            >
            </i>
          </div>
        </div>
      </div> 
    </div>
    <Appointments :id=id></Appointments>
    <Booked :appid=id></Booked>
  </div>
</template>
<script>
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
import Appointments from '@/components/Doctor/Appointments'
import Booked from '@/components/Doctor/Booked.vue'
import axios from "axios";
export default {
  components:{
    Appointments,
    Booked
  },

data() {
    return {
    };
  },
  props: {
    id: String,
  },
  
  setup(props) {
    const state = reactive({
        filteredItems:{},
    });
    const store = useStore();
    const router = useRouter();
    onMounted(async() => {
      if (store.state.patient == null) {
        router.push("/login");
      }
      else{
        await axios
      .get(
        "api/v1/user/"+props.id 
        )
      .then((res) => {
        state.filteredItems=res.data;
      })
      .catch((error) => {
        console.log(error);
        // console.log(error.response.s);
      });
      }
    });
    
    return { state };
  },

  

}
</script>

<style lang="scss">
.doctor{
  margin-top: 120px;
  img{
    width: 100%;
  }
  .details{
    padding: 10px 20px;
    h2{
      font-size: 35px;
    }
    h3{
      font-size: 18px;
      color: var(--textcolor);
    }
    p ,.stars{
      color: #CCC7C7;
      font-size: 18px;
      svg{
        cursor: pointer;
        margin: 0 5px;
        :hover{
          color:#F1C40F ;
          font-size: 20px;


        }
      }
    }
    
  }
}
</style>