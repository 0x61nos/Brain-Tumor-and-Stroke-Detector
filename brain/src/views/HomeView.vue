<template>
  <div class="container home">
    <div class="row section1">
      <div class="col-lg-7 mt-5 col-sm-12">
        <h1>Automated Detection of Brain Tumor and Stroke Using Al</h1>
        <p>
          This website offers a cutting-edge solution for the early detection and diagnosis of brain tumors and strokes using advanced artificial intelligence (AI) algorithms. By leveraging deep learning and image processing techniques.
        </p>
        <router-link to="/about" class="btnlink mt-5">Read More</router-link>
      </div>
      <div class="col-lg-5">
        <img src="../assets/home1.png" alt="" />
      </div>
    </div>
  </div>
  <Section2 />
  <Doctor />
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import Section2 from "@/components/home/Section2";
import Doctor from "@/components/Doctor/Doctor.vue";
export default {
  name: "HomeView",
  components: {
    HelloWorld,
    Section2,
    Doctor,
  },
};
</script>
<style lang="scss">
.home {
  margin-top: 80px;
  @media (max-width: 768px) {
    .section1 {
      flex-direction: column-reverse;
      justify-content: center;
      align-items: center;
    }
  }
  h1 {
    color: var(--maincolor);
    font-size: 35px;
  }
  p {
    font-size: 20px;
    padding: 10px 50px 0 0;
  }
  img {
    max-width: 100%;
  }
}
</style>
