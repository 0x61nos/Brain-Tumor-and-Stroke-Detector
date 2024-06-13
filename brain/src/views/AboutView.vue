<template>
  <Swiper
  :modules="[Virtual]"
  :loop="true" :slides-per-view="1"  virtual
  class="Swiper"
  >
    <SwiperSlide >
      <About />
    </SwiperSlide>
    <SwiperSlide>
      <About2 />

    </SwiperSlide>

    <!-- Add more slides as needed -->
    
  </Swiper>
</template>

<script>
import About from '../components/About/about.vue';
import About2 from '../components/About/about2.vue';
import { Swiper, SwiperSlide ,useSwiper} from "swiper/vue";
import { Navigation, Pagination, Scrollbar, A11y,Virtual } from "swiper/modules";
// Import Swiper styles
import "swiper/css";
import "swiper/scss";
import "swiper/scss/navigation";
import "swiper/scss/pagination" 
export default {
  components: {
    Swiper,
    
    SwiperSlide,
    About,
    About2,
  },
  setup() {
    const swiper = useSwiper();
    const onSwiper = (swiper) => {
      console.log(swiper);
    };
    const onSlideChange = () => {
      console.log("slide change");
    };
    
    return {
      onSwiper,
      swiper,
      Virtual,
      swiperOptions: {
        slidesPerView: 2,
        spaceBetween: 30,
        pagination: {
          el: '.swiper-pagination',
          clickable: true
        },
        navigation: {
          enable:true,
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      },
      
      onSlideChange,
      modules: [Navigation, Pagination, Scrollbar, A11y],
    };
  },
};
</script>
<style lang="scss" scoped>
.Swiper {
  margin: 100px 0 0 0;
  font-size: larger;
  
}
</style>
