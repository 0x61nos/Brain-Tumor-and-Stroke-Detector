<template>
  <div class="row mt-5 pt-3">
    <div class=" apphead text-center fs-3">
      <img src="../../assets/date.png" alt="" srcset="">
      Available Appointments
    </div>
  </div>
  <div class="row mt-5">
    <div class="boxs">
      <div  v-for="item in state.Booked" :key="item.id"
       class="box text-center">
        
        <div class="day">
          {{item.date+' '+ item.day }}
        </div>
        <div class="appointment">
          <i class="fa-solid fa-clock"></i>
          From :   {{ item.from }}
          <br>
          <i class="fa-solid fa-clock"></i>
          to :
          {{item.to}}
        </div>
        <button  @click="Book(item.id)" >Book</button>
      </div>
    </div>
  </div>
</template>

<script>
// import store from '@/store';
import axios from 'axios';
import { computed, onMounted, reactive } from "vue";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {

  props: {
    appid: String,
  },
    setup(props) {
      const state = reactive({
        Booked: [
        {
          id: 1,
          day: "Monday",
          from: "2.00",
          to:'4.00',
          date:'6 - 5 - 2024'
        },
        {
          id: 2,
          day: "Tuesday",
          from: "1.00",
          to:'3.00',
          date:'7 - 5 - 2024'
        },
        {
          id: 3,
          day: "Wednesday",
          from: "3.00",
          to:'5.00',
          date:'8 - 5 - 2024'
        },
        {
          id: 4,
          day: "Thursday",
          from: "1.00",
          to:'3.00',
          date:'9 - 5 - 2024'
        },
      ],
      Bookeddone: [
        {
          id: 1,
          day: "Monday",
          from: "2.00",
          to:'4.00',
          date:'6 - 5 - 2024'
        },
        {
          id: 2,
          day: "Tuesday",
          from: "1.00",
          to:'3.00',
          date:'7 - 5 - 2024'
        },
        {
          id: 3,
          day: "Wednesday",
          from: "3.00",
          to:'5.00',
          date:'8 - 5 - 2024'
        },
        {
          id: 4,
          day: "Thursday",
          from: "1.00",
          to:'3.00',
          date:'9 - 5 - 2024'
        },
      ],

      book:false
    });
    const store = useStore();
    const router = useRouter();
    onMounted(() => {
      // store.state.patient
      if (store.state.patient == null) {
        router.push("/login");
      }
    });
    const Book = async (id) => {
        try {
          let userid=store.state.patient.id
          // console.log(id);
          let app=state.Booked.filter(item => item.id==id)
          console.log(app);
          await axios.post('api/v1/appointment',{
            day:app[0].day,
            date:app[0].date,
            fromm:app[0].from,
            too:app[0].to,
            userId:userid,
            doctorId:props.appid
          })
          .then(res=>{
            // console.log(res);
            state.Booked=state.Bookeddone.filter(item => item.id !=id)
            state.Bookeddone=[...state.Booked]
            state.book=true
          })
          .catch(err=>{
            console.log(err);
          })
        // // console.log(app);
        // // console.log(id);
        } catch (err) {
          console.log(err);
        }
      
    };
    return { state, Book };
  },
  

}
</script>

<style lang="scss" scoped>
.apphead{
  color: var(--maincolor);
  font-size: 40px;
  font-weight: 500;
  img{
    width: 64px;
  }
}
.boxs{
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 20px;
  flex-wrap: wrap;
  .box{
    // background-color: #EBEBEB;
    box-shadow:  0 0 4px 0 #0000002d;    
    padding:7px  20px ;
      border-radius: 10px;
    width: 25%;
    border: 2px solid var(--maincolor);
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
    button{
      background-color: var(--maincolor);
      border: none;
      outline: none;
      color: white;
      padding: 7px 25px;
      margin-top: 10px;
      border-radius: 8px;
      // font-weight: bold;
    }
  }
}
</style>