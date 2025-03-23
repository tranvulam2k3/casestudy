<template>
    <div>
      <Hero />
      <Introduction />
      <ExploreBooks />
      <Authors />
    </div>
  </template>
  
  <script>
  import Hero from '@/components/home/Hero.vue';
  import Introduction from '@/components/home/Introduction.vue';
  import ExploreBooks from '@/components/home/ExploreBooks.vue';
  import Authors from '@/components/home/Authors.vue';
  
  export default {
    name: 'Home',
    components: {
      Hero,
      Introduction,
      ExploreBooks,
      Authors
    }
  }
  </script>