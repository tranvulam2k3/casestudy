<template>
  <div class="top-area">
    <div class="header-area">
      <nav class="navbar navbar-default bootsnav navbar-sticky navbar-scrollspy"
        data-minus-value-desktop="70" data-minus-value-mobile="55" data-speed="1000">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
              <i class="fa fa-bars"></i>
            </button>
            <router-link class="navbar-brand" to="/">techzen library<span></span></router-link>
          </div>
          <div class="collapse navbar-collapse menu-ui-design" id="navbar-menu">
            <ul class="nav navbar-nav navbar-right" data-in="fadeInDown" data-out="fadeOutUp">
              <li class="scroll">
                <a href="#home" @click.prevent="scrollToSection('home')">Trang chủ</a>
              </li>
              <li class="scroll">
                <a href="#introduction" @click.prevent="scrollToSection('introduction')">Giới thiệu</a>
              </li>
              <li class="scroll">
                <a href="#explore" @click.prevent="scrollToSection('explore')">Khám phá</a>
              </li>
              <li class="scroll">
                <a href="#authors" @click.prevent="scrollToSection('authors')">Tác giả</a>
              </li>
              <!-- <li class="scroll">
                <router-link to="/book">Danh sách sách</router-link>
              </li> -->
              <li class="scroll">
                <router-link to="/login">Đăng nhập</router-link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
    <div class="clearfix"></div>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
  data() {
    return {
      // Dữ liệu của component
    }
  },
  methods: {
    scrollToSection(sectionId) {
      // Nếu đang không ở trang chủ, chuyển về trang chủ trước
      if (this.$route.path !== '/') {
        this.$router.push('/').then(() => {
          // Đợi DOM cập nhật và script tải xong
          setTimeout(() => {
            this.smoothScrollToSection(sectionId);
          }, 300);
        });
      } else {
        this.smoothScrollToSection(sectionId);
      }
    },
    
    smoothScrollToSection(sectionId) {
      // Cập nhật URL hash để lưu trạng thái
      if (window.location.hash !== `#${sectionId}`) {
        this.$router.replace({ path: '/', hash: `#${sectionId}` });
      }
      
      // Scroll đến phần tử
      setTimeout(() => {
        const element = document.getElementById(sectionId);
        if (element) {
          window.scrollTo({
            top: element.offsetTop - 70, // Trừ đi chiều cao của header
            behavior: 'smooth'
          });
        }
      }, 100);
    }
  },
  mounted() {
    // Xử lý scroll ban đầu nếu có hash trong URL
    if (this.$route.hash) {
      const targetId = this.$route.hash.substring(1);
      setTimeout(() => {
        this.smoothScrollToSection(targetId);
      }, 500);
    }
  }
}
</script>

<style scoped>
/* Các style specific cho Header, nếu cần */
</style> 