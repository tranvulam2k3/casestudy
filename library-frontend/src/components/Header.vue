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
            <a class="navbar-brand" href="#">techzen library<span></span></a>
          </div>
          <div class="collapse navbar-collapse menu-ui-design" id="navbar-menu">
            <ul class="nav navbar-nav navbar-right" data-in="fadeInDown" data-out="fadeOutUp">
              <li class="scroll active"><a href="#home">Trang chủ</a></li>
              <li class="scroll"><a href="#introduction">Giới thiệu</a></li>
              <li class="scroll"><a href="#explore">Khám phá</a></li>
              <li class="scroll"><a href="#authors">Tác giả</a></li>
              <li class="scroll"><a href="#contact">Đăng nhập</a></li>
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
    // Các phương thức xử lý
  }
}
</script>

<style scoped>
/* Các style specific cho Header, nếu cần */
</style> 