<template>
    

  <div class="col-lg-6 col-sm-12">
    
    <div class="scan">
      
      <div class="box">
        <div class="col-12 mt-5      ">
      <h2>For Brain Stroke</h2>
    </div>
        <div class="card">
          <div class="top">
            <p>Upload CT here</p>
          </div>

          <div
            class="drag-area"
            @dragover.prevent="onDragOver"
            @dragleave.prevent="onDragLeave"
            @drop.prevent="onDrop"
          >
            <span>
              <span class="select" role="button" @click="selectFiles"
                >Browse files
              </span>
            </span>
            <input
              name="file"
              type="file"
              class="file"
              ref="fileInput"
              @change="onFileSelect"
            />
          </div>
          <div class="container">
            <div class="image" v-for="img in images" :key="img.id">
              <span class="delete" @click="deleteImage">&times;</span>
              <img :src="img.url" />
            </div>
          </div>
          <!-- <button type="button">Upload</button> -->
        </div>
        <div class="submit">
          <button @click="submitmodel2" :disabled="image==''?true:false" class="btnlink"> Scan </button>
          <p class="text-danger  pt-2" v-if="image==''">You should upload img</p>
        </div>
      </div>
    </div>
  </div>
    <div  :class="' col-lg-6 col-sm-12 text-center    '">
    <div class="scan">
      <div class="box">
        <div class="col-12  mt-5    ">
      <h2>The Result</h2>
    </div>
        <div class="card">
          <div  class="  result " >
          <div   :class=" datas==1?'text-danger have text-center  ':'text-success have text-center '" >
            <h2 v-if="ress">{{ canser }} </h2>
          </div>
        </div>
      </div>
      <div class="submit">
          <!-- <button @click="submitmodel" class="btnlink">Scan</button> -->
          <!-- <button @click="submitmodel2" class="btnlink"> brain stroke </button> -->
        </div>
    </div>
      
    </div>

    
  </div>
  

  
</template>
<script>
import axios from "axios";
export default {
  props:{
    userid:String
  },
  data() {
    return {
      images: [],
      isDragging: false,
      image: "",
      ress: false,
      datas: null,
      canser: "",
    };
  },
  methods: {
    selectFiles() {
      this.$refs.fileInput.click();
    },
    onFileSelect(event) {
      const files = event.target.files;
      this.image = event.target.files[0];
      if (files.length === 0) return;
      for (let i = 0; i < files.length; i++) {
        if (files[i].type.split("/")[0] != "image") continue;
        if (!this.images.some((e) => e.name === files[i].name)) {
        }
        this.images.push({
          name: files[i].name,
          url: URL.createObjectURL(files[i]),
        });
      }
    },
    deleteImage(index) {
      this.images.splice(index, 1);
      this.image=""
    },
    onDragOver(event) {
      event.preventDefault();
      this.isDragging = true;
      event.dataTransfer.dropEffect = "copy";
    },
    onDragLeave(event) {
      event.preventDefault();
      this.isDragging = false;
    },
    onDrop(event) {
      event.preventDefault();
      this.isDragging = false;
      const files = event.dataTransfer.files;
      for (let i = 0; i < files.length; i++) {
        if ((files[i].type.split("/")[0] = "image")) continue;
        if (this.images.some((e) => e.name === files[i].name)) {
          this.images.push({
            name: files[i].name,
            url: URL.createObjectURL(files[i]),
          });
        }
      }
    },
    async submitmodel() {
      await axios
        .post(
          "image/upload/"+ this.userid,
          {
            image: this.image,
            typeModel: 1,
          },
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((res) => {
          console.log(res);
          this.ress = true;
          this.canser = res.data.canser;
          this.datas = res.data.No;
        })
        .catch((err) => console.log(err));
      // console.log(this.image[0]);
    },
    async submitmodel2() {
      // console.log(this.userid);
      if (!this.image=='') {
        await axios
        .post(
          "image/upload/" + this.userid,
          {
            image: this.image,
            typeModel: 2,
          },
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((res) => {
          console.log(res);
          this.ress = true;
          this.canser = res.data.canser;
          this.datas = 1;
        })
        .catch((err) => console.log(err));
      }
      // console.log(this.image);
    },
  },
};
</script>
<style lang="scss" scoped>
.card {
  border-radius: 10px;
  border: 3px dashed var(--textcolor);
  text-align: center;
  overflow: hidden;
  width: 80%;
  margin: auto;
  margin-top: 20px;
  padding: 50px 20px 20px;
  p {
    color: var(--textcolor);
    font-size: 25px;
    font-weight: bold;
  }
  span.select {
    color: var(--textcolor);
    display: block;
    margin-top: 20px;
    font-weight: bold;
  }
  div.select {
    color: #dc0606;
    font-size: 25px;
    font-weight: 600;
  }
  .image {
    width: 50%;
    margin: auto;
    overflow: hidden;
    position: relative;
    margin-top: 10px;
    img {
      width: 100%;
      height: 140px;
    }
    span {
      position: absolute;
      font-weight: 500;
      font-size: 30px;
      color: #dc0606;
      cursor: pointer;
      top: 5px;
      right: 10px;
    }
  }
}
.submit {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  gap: 20px;
  button {
    border: none;
  }
}
.have{
  // border-radius: 10px;
  // border: 3px dashed var(--textcolor);
  // text-align: center;
  // overflow: hidden;
  // width: 80%;
  // margin: auto;
  // margin-top: 80px;
  // padding: 50px 20px 20px;
}
.card input,
.card .drag-area .on-drag,
.card .drag-area.dragover .visible {
  display: none;
}
</style>