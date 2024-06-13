<template>
  <div class="row my-5 pt-3">
    <div class=" apphead text-center fs-3">
      Appointments’ Requests
    </div>
  </div>
  <div class="row my-5 pb-5 ">
    <div class="boxs">
      <div class="box" v-for="item in state.Booked" :key="item.id" >
        <div class="name">
          {{  item.user[1].firstName }}
          {{  item.user[1].lastName }}
        </div>
        <div class="day">
          {{ item.day }}
        </div>
        <div class="appointment">
          <i class="fa-solid fa-clock"></i>
          {{ item.fromm }} pm
          . 
          {{item.too}} Pm 
          
        </div>
        <div class="location">
          <p>
            <i class="fa-solid fa-location-dot"></i>
            Egypt
          </p>
        </div>
        <div class="status">
          <button class="cancel"  @click="cancel(item.id)">Cancel</button>
          <button class="confirm" @click="confirm(item.id)">Confirm</button>
          <!-- <i class="fa-solid fa-clock"></i>
          Watiting -->
        </div>
      </div>
    </div>
  </div>
</template>

<script >
import axios from 'axios';
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
// api/v1/appointment/
props: {
    id: String,
    userid:String
  },
    setup(props) {
      const state = reactive({
        Booked: [ ],
      book:false
    });
    const store = useStore();
    const router = useRouter();
    onMounted(async() => {
        try {
          let userid=store.state.patient.id
          await axios.get('api/v1/appointment/userId/'+userid)
          .then(res=>{

            console.log(res.data);
            state.Booked=res.data
            state.Booked.map(item=>{
              item.user.sort(function(a, b) { 
              return  ( a.id - b.id   );
            })
            })
            console.log(state.Booked);
            state.Booked=res.data.filter(item=> item.user[1].id == props.id)
            state.Booked=res.data.filter(item=> item.cancle != true )
            // console.log(state.Booked);
            
            state.Booked=state.Booked.filter(item=> item.confirmed != true)
            // console.log(state.Booked);
            
          })
          .catch(err=>{
            console.log(err);
          })
        } catch (err) {
          console.log(err);
        }
    });
    let confirm = async (id)=>{
      await axios.put('api/v1/appointment/confirm/'+id)
      .then(res=>{
        state.Booked=state.Booked.filter((item) => item.id !==id)
        console.log(res.data);
      })
      .catch(err=>{
        console.log(err);
      })
    }
    let cancel = async (id)=>{
      await axios.put('api/v1/appointment/cancle/'+id)
      .then(res=>{
        state.Booked=state.Booked.filter((item) => item.id !==id)
        console.log(res.data);
      })
      .catch(err=>{
        console.log(err);
      })
    }
    
    return { state ,confirm ,cancel};
  },
}
</script>

<style lang="scss" scoped>
.status{ button{
  padding: 5px 15px;
  margin: 0 5px;
  border: none;
  outline: none;
  color: #fff;
  border-radius: 8px;
  
}
.confirm{
  background-color: #1A998E;
}
.cancel{
    background-color: #E84A4A;
  }
}
</style>