<template>
  <div class="container doctors">
    <h3>Our Doctors</h3>
    <h1>Meet Our Professional Doctors</h1>
    <div class="cards">
      <div class="card" v-for="item in doctors" :key="item.id">
        <img :src="item.imageProfile" alt="" />
        <div class="details">
          <h2>{{ item.firstName +' '+item.lastName }}</h2>
          <p>
            <i class="fa-solid fa-location-dot"></i>
            {{ item.location }}</p>
          <div class="stars">
            <i
              v-for="(star, index) in 5"
              class="fa fa-star"
              :key="index"
            >
            </i>
          </div>
          <router-link :to="{ name: 'doctor', params: { id:item.id}}">View More..</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      doctors: [
        {
          id: 1,
          name: "Dr.Sama",
          location: "Egypt",
          rate: 5,
          img: "doc1",
        },
        {
          id: 2,
          name: "Dr.Eslam",
          location: "Egypt",
          rate: 5,
          img: "doc2",
        },
        {
          id: 3,
          name: "Dr.Alia",
          location: "Egypt",
          rate: 5,
          img: "doc3",
        },
      ],
    };
  },
  async mounted() {
    await axios
      .get(
        "api/v1/user/role?roleName=doctor" 
        )
      .then((res) => {
        // console.log(res.data);
        this.doctors = res.data;
      })
      .catch((error) => {
        console.log(error);
        // console.log(error.response.s);
      });
  },
};
</script>

<style lang="scss">
.doctors {
  margin-bottom: 50px;
  h3 {
    color: var(--textcolor);
    font-weight: bold;
    text-align: center;
    width: 100%;
  }
  h1 {
    text-align: center;
    width: 100%;
    margin: 30px 0 50px;
    color: black !important ;
  }
  .cards {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    gap: 30px;
    margin: 30px 0;
    .card {
      width: 250px;
      box-shadow: 0 0 8px 0 #00000046;
      img {
        width: 100%;
      }
      .details{
        width: 100%;
        padding: 10px 10px 20px;
        text-align: center;
        display: flex;
        gap: 5px;
        flex-direction: column;
        align-items: center;
        justify-content: space-evenly;
        h2{
          font-size: 25px;
          font-weight: 500;
        }
        p{
          color: #CCC7C7;
          font-size: 18px;
          i{
            color: #868383;
          }
        }
        .stars{
          color:#F1C40F ;
          display: flex;
          gap: 3px;
        }
        a{
          text-decoration: none;
          font-weight: bold;
          font-size: 20px;
          color: var(--textcolor);
        }
      }
    }
  }
}
</style>