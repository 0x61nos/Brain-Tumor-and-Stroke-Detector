<template>
  <div class="section">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 col-sm-12 image">
          <img src="../../assets/home2.png" alt="" />
        </div>
        <div class="col-lg-6 col-sm-12 boxs">
          <div>
            <div class="box1">
              <h2>Scan your own MRI</h2>
              <p>
                Our website offers a unique feature that allows users to
                onveniently upload their X-ray images, enabling them to abstain
                accurate diagnoses and valuable insights regarding their medical
                condition.
              </p>
              <router-link to="">Scan now</router-link>
            </div>
          </div>
          <br>
          <div class="alaginend">
            <div class="box2">
              <h2>Our Doctors</h2>
              <p>
                " Find one of the best doctors to get instant medical advice and
                second opinion for your health problems.
              </p>
              <router-link to="">Choose a doctor</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.section {
  margin: 60px 0;
  position: relative;

  .row {
    height: 100vh;
    align-items: center;
    position: relative;
    img {
      width: 100% !important;
    }
    .boxs {
      height: 100%;
      position: relative;

      p {
        font-size: 14px;
      }
      a,
      h2 {
        font-size: 20px;
        color: var(--textcolor);
        font-weight: bold;
        text-align: center;
        text-decoration: none;
      }
      >div{
        width: 100%;
      }
      >div.alaginend{
        display: flex;
        justify-content: end;
      }
      .box1 {
        text-align: center;
        height: 280px;
        width: 230px;
        background-color: #effef1;
        padding: 20px 10px;
      }
      .box2 {
        padding: 20px 10px;
        text-align: center;

        height: 230px;
        width: 230px;
        background-color: #fef0ff;
      }
    }
  }
  @media (max-width: 991px) {
    .row {
      height: 150vh;
    }
    .image {
      height: 40%;
    }
    .boxs {
      height: 65%;
    }
  }
}
</style>