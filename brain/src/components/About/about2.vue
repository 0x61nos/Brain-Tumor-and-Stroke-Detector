<template>
  <div class="about1 container  ">
        <div class="title">
          <h1>Brain Stroke</h1>
        </div>
        <div class="row">
          <div class="col-lg-8 col-md-12">
            <h3>Symptoms</h3>
            <p>
              




            </p>
            <ul>
              <li>Numbness or weakness in the face, arm, or leg, especially on one side of the body.</li>
              <li>Confusion or trouble speaking or understanding speech.</li>
              <li>Trouble seeing in one or both eyes.</li>
              <li>
                Troublé walking, dizziness, or problems with balance.
              </li>
              <li>Severe headache with no known cause .</li>
            </ul>
          </div>
          <div class="col-lg-4 col-md-12">
            <img src="../../assets/about1.png" alt="" />
          </div>
        </div>
      </div>
      <div class="navg">
      <div class="nav1" @click="swiper.slideNext()"></div>
      <!-- <div class="nav1" @click="swiper.slide()"></div> -->
    </div>
</template>

<script>
  import { useSwiper } from 'swiper/vue';

export default {
  setup() {
    const swiper = useSwiper();

    return {
      swiper,
    };
  },
};
</script>

<style lang="scss">
.navg{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 20px;
    div{
      width: 20px;
      height: 20px;
      background-color: #1A998E;
    }
  }
</style>