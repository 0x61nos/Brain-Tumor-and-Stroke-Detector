<template>
  <div class="about1 container  ">
        <div class="title">
          <h1>Brain Tumor</h1>
        </div>
        <div class="row">
          <div class="col-lg-8 col-md-12">
            <h3>Symptoms</h3>
            <p>
              If you are experiencing new. server, or persistent
              symptoms,contact a health care provider.<br />
              Meningioma brain tumor usually comes with no symptoms, as it grows
              gradually the patient may show the following symptoms:
            </p>
            <ul>
              <li>Headache which gets severe with time</li>
              <li>change in vision causing double or blurred vision</li>
              <li>Hearing disability</li>
              <li>
                Ringing in ears Loss of memory and diggiculty in concentrating
              </li>
              <li>Weakness in arms and legs</li>
              <li>Loss of smell</li>
              <li>Seizures</li>
              <li>Numbness</li>
              <li>Speech problems</li>
            </ul>
          </div>
          <div class="col-lg-4 col-md-12">
            <img src="../../assets/about1.png" alt="" />
          </div>
        </div>
      </div>
      <div class="navg">
      <div class="nav1" @click="swiper.slideNext()"></div>
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
.Swiper{
  img{
    width: 100%;
  }
  .title{
    color: var(--textcolor);
    padding: 20px;
    font-weight: bold;
    font-size: 28px;
    text-align: center;
  }
  h3{
    color: var(--textcolor) !important;
    font-weight: bold;
  }
.navg{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 20px;
    div{
      width: 17px;
      height: 17px;
      border-radius: 50%;
      cursor: pointer;
      background-color: #1A998E;
    }
  }
}
</style>