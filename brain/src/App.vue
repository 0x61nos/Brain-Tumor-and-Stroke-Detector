<template>
  <NaveBar />
  <router-view/>
</template>
<script>
import NaveBar from '@/components/NavBar/NaveBar'
export default {
  components:{NaveBar}
  
}
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
*{
  padding: 0;
  margin: 0;
  font-family: "Poppins", sans-serif;
}
body{
  padding: 0;
  margin: 0 ;
  box-sizing: border-box;
  overflow-x: hidden;
}
:root{
  --maincolor: #1A998E;
  --whitecolor: #FFFFFF;
  --graycolor:#BBE0DD;
  --textcolor:#199A8D;
}
/* Track */
::-webkit-scrollbar {
  width: 10px; /* width of the scrollbar */
}

/* Handle */
::-webkit-scrollbar-thumb {
  border-radius: 15px;
  background: linear-gradient(to top,var(--maincolor),var(--graycolor));
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: var(--textcolor);
   /* color of the scrollbar handle on hover */
}

a.abtn{
  padding: 5px 25px;
  gap: 10px;
  border-radius: 21px;
  color: inherit;
  text-decoration: none;
  text-align: center;
  display: inline-block;
  cursor: pointer;
  
}
a.abtn1{
  background:var(--graycolor);
  color: black;
}
a.abtn2{
  background:white;
  color:var(--textcolor);
}
.btnlink{
  color: var(--whitecolor) !important;
  background-color: var(--maincolor);
  padding: 7px 20px;
  width: fit-content;
  text-decoration: none;
  font-size: 18px;
  border-radius: 10px;
  font-weight: bold;
  display: block;
  border: none;
}
</style>