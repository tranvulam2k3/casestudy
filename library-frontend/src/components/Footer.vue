<template>
  <footer id="contact" class="contact">
    <div class="container">
      <div class="footer-top">
        <div class="row">
          <div class="col-md-3 col-sm-6">
            <div class="single-footer-widget">
              <div class="footer-logo">
                <a href="#">techzen library</a>
              </div>
              <p>
                Thư viện trực tuyến, nơi lưu giữ và chia sẻ tri thức cho mọi người.
              </p>
              <div class="footer-contact">
                <p>academy@techzen.vn</p>
                <p>093 550 56 40</p>
              </div>
            </div>
          </div>
          <div class="col-md-2 col-sm-6">
            <div class="single-footer-widget">
              <h2>LIÊN KẾT NHANH</h2>
              <ul>
                <li><a href="#home">Trang chủ</a></li>
                <li><a href="#introduction">Giới thiệu</a></li>
                <li><a href="#explore">Khám phá </a></li>
                <li><a href="#authors">Tác giả</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-3 col-xs-12">
            <div class="single-footer-widget">
              <h2>Danh mục nổi bật</h2>
              <div class="row">
                <div class="col-md-6 col-xs-6">
                  <ul>
                    <li><a href="#">Sách mới</a></li>
                    <li><a href="#">Sách bán chạy</a></li>
                    <li><a href="#">Tác giả nổi tiếng</a></li>
                    <li><a href="#">Tài liệu nghiên cứu</a></li>
                  </ul>
                </div>
                <div class="col-md-6 col-xs-6">
                  <ul>
                    <li><a href="#">Văn học</a></li>
                    <li><a href="#">Khoa học</a></li>
                    <li><a href="#">Kinh doanh</a></li>
                    <li><a href="#">Công nghệ</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 col-sm-6">
            <div class="single-footer-widget">
              <h2>Nhận tin</h2>
              <div class="footer-newsletter">
                <p>
                  Đăng ký để nhận thông báo về sách mới, ưu đãi và sự kiện của thư viện.
                </p>
              </div>
              <div class="hm-foot-email">
                <div class="foot-email-box">
                  <input type="text" class="form-control" placeholder="Nhập email của bạn" v-model="email">
                </div>
                <div class="foot-email-subscribe" @click="subscribeNewsletter">
                  <span><i class="fa fa-arrow-right"></i></span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="footer-copyright">
        <div class="row">
          <div class="col-sm-6">
            <p>
              &copy; Copyright {{ new Date().getFullYear() }} <a href="#">TechZen Academy</a>
            </p>
          </div>
          <div class="col-sm-6">
            <div class="footer-social">
              <a href="#"><i class="fa fa-facebook"></i></a>
              <a href="#"><i class="fa fa-instagram"></i></a>
              <a href="#"><i class="fa fa-linkedin"></i></a>
              <a href="#"><i class="fa fa-twitter"></i></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'FooterComponent',
  data() {
    return {
      email: ''
    }
  },
  methods: {
    subscribeNewsletter() {
      if (this.email && this.validateEmail(this.email)) {
        // Trong thực tế, bạn sẽ gọi API từ backend
        // axios.post('http://localhost:8080/api/v1/newsletter/subscribe', { email: this.email });
        alert(`Đã đăng ký nhận tin với email: ${this.email}`);
        this.email = '';
      } else {
        alert('Vui lòng nhập email hợp lệ!');
      }
    },
    validateEmail(email) {
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    }
  }
}
</script>

<style scoped>
/* Styles cụ thể cho component này, nếu cần */
</style> 