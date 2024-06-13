<template>
  <div class="row my-5 pt-3">
    <div class=" apphead text-center fs-3">
      Appointments Status
    </div>
  </div>
  <div class="row mt-5">
    <div class="boxs" >
      <div class="box"  v-for=" item in state.Booked"  :key="item.id">
        <div class="name">
          {{ item.user[0].firstName + ' ' + item.user[0].lastName}}
        </div>
        <div class="day">
          {{item.day}}
        </div>
        <div class="appointment">
          <i class="fa-solid fa-clock"></i>
          {{item.fromm}} Pm
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
          <div v-if="item.cancle!=null"  style="color: #E94D4D;">
            Canceled
          </div>
          <div v-else-if="item.confirmed==true"  style="color: #1A998E;">
            <i class="fa-solid fa-check-circle "></i>
            Confirmed
          </div>
          <div v-else-if="item.cancle==null &&item.confirmed==null">
            <i class="fa-solid fa-clock"></i>
            Watiting
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
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
      // store.state.patient
      if (store.state.patient == null) {
        router.push("/login");
      }
      else{
        try {
          let userid=store.state.patient.id
          await axios.get('api/v1/appointment/userId/'+userid)
          .then(res=>{
            // console.log(res.data);

            // console.log(res.data[0]);
            state.Booked=res.data
            state.Booked.map(item=>{
              item.user.sort(function(a, b) { 
              return  ( a.id - b.id   );
            })
            })
            console.log(state.Booked);
            state.Booked=state.Booked.filter(item=> item.user[0].id == props.id)
            
          })
          .catch(err=>{
            console.log(err);
          })
        // // console.log(app);
        // // console.log(id);
        } catch (err) {
          console.log(err);
        }
      }
    });
    
    return { state };
  },
}
</script>

<style lang="scss" >
.apphead{
  color: var(--maincolor);
}
.boxs{
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 20px;
  flex-wrap: wrap;
  .box{
    background-color: #EBEBEB;
    box-shadow:  0 0 4px 0 #0000002d;    
    padding:7px  20px ;
      border-radius: 10px;
    width: 25%;
    .name{
      font-size: 24px;
      color: var(--maincolor);
      margin: 7px 0;
    }
    .day{
      font-size: 20px;
      margin: 7px 0;
    }
    .appointment,
    .location,
    .status
    {
      color: #868383;
      margin: 7px 0;
      svg{
        margin-right: 10px;
      }
    }
    .status
    {
      text-align: right;
    }
  }
}
</style>