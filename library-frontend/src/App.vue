<script>
import Authors from "@/components/Authors.vue";
import ExploreBooks from "@/components/ExploreBooks.vue";
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header.vue";
import Hero from "@/components/Hero.vue";
import Introduction from "@/components/Introduction.vue";


// Import toàn bộ CSS từ assets/css
import "@/assets/css/animate.css";
import "@/assets/css/owl.carousel.min.css";
import "@/assets/css/bootstrap.min.css";
import "@/assets/css/bootsnav.css";
import "@/assets/css/style.css";

// FontAwesome từ CDN nên sẽ để trong index.html
export default {
  name: "App",
  components: {
    Authors,
    ExploreBooks,
    Footer,
    Header,
    Hero,
    Introduction
  }
};
</script>

<template>
  <div id="app">
    <Header />
    <Hero />
    <Introduction />
    <ExploreBooks />
    <Authors />
    <Footer />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'

// Nạp các file JS sau khi DOM đã được render
onMounted(() => {
  const scripts = [
    "/src/assets/js/jquery.js",
    "/src/assets/js/bootstrap.min.js",
    "/src/assets/js/bootsnav.js",
    "/src/assets/js/owl.carousel.min.js",
    "/src/assets/js/custom.js"
  ]

  scripts.forEach(src => {
    const script = document.createElement('script')
    script.src = src
    script.async = false
    document.body.appendChild(script)
  })
})
</script>

<style>
/* Bạn có thể thêm các CSS toàn cục ở đây nếu cần */
</style>
