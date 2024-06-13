<template>
    <div class="row my-5 pt-5">
    <div class=" apphead text-center text-black fs-3">
      Your upcoming appointments
    </div>
  </div>
  <div class="row mt-5 text-center">
    <div class="boxs">
      <div class="box">
        <div class="image">
          <img src="../../assets/brain sides-rafiki 1.png" alt="">
        </div>
        <div class="name">
          Ali Maher
        </div>
        <div class="day">
          Sunday
        </div>
        <div class="appointment">
          <i class="fa-solid fa-clock"></i>
          11:00 Am 
          . 
          02:00 Pm 
          
        </div>
        <div class="location">
          <p>
            <i class="fa-solid fa-location-dot"></i>
            Egypt
          </p>
        </div>
        <div class="status text-center">
          <button>Cancel</button>
          <!-- <button class="cancel" >Decrease</button>
          <button class="confirm">Confirm</button> -->
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>
.box{
  background-color: #fff;
  padding: 0;
  margin-bottom: 150px;
  .image{
    background: #F9F8F8;
    width: 100%;
  }
  button{
    padding: 5px 15px;
  margin: 0 5px;
  border: none;
  outline: none;
  color: #E84A4A;
  font-weight: bold;
  border-radius: 8px;
  }
}
</style>