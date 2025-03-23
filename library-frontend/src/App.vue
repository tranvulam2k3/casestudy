<script>
import Header from "@/components/Header.vue";
import BookHeader from "@/components/BookHeader.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "App",
  components: {
    Header,
    BookHeader,
    Footer
  },
  computed: {
    isBookPage() {
      return this.$route.path.startsWith('/book');
    }
  }
};
</script>

<template>
  <div id="app">
    <BookHeader v-if="isBookPage" />
    <Header v-else />
    <router-view />
    <Footer />
  </div>
</template>

<style>
/* Bạn có thể thêm các CSS toàn cục ở đây nếu cần */
</style>