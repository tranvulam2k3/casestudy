<template>
  <section id="authors" class="testimonial">
    <div class="container">
      <div class="section-header">
        <p>Tác giả <span>nổi bật</span></p>
        <h2>Tác giả nổi bật</h2>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="testimonial-carousel owl-carousel">
            <div class="testimonial-box" v-for="author in authors" :key="author.id">
              <div class="testimonial-img">
                <img :src="author.image" :alt="author.name">
              </div>
              <div class="testimonial-content">
                <h2>{{ author.name }}</h2>
                <h3>{{ author.field }}</h3>
                <p>{{ author.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'AuthorsComponent',
  data() {
    return {
      authors: [
        {
          id: 1,
          name: 'Nguyễn Nhật Ánh',
          field: 'Văn học thiếu nhi',
          description: 'Nhà văn nổi tiếng với nhiều tác phẩm đặc sắc như "Mắt biếc", "Tôi thấy hoa vàng trên cỏ xanh"...',
          image: './assets/images/authors/author1.jpg'
        },
        {
          id: 2,
          name: 'Rosie Nguyễn',
          field: 'Kỹ năng sống',
          description: 'Tác giả của những cuốn sách self-help nổi tiếng như "Tuổi trẻ đáng giá bao nhiêu".',
          image: './assets/images/authors/author2.jpg'
        },
        {
          id: 3,
          name: 'Tô Hoài',
          field: 'Văn học thiếu nhi',
          description: 'Nhà văn nổi tiếng với tác phẩm "Dế Mèn phiêu lưu ký", một tác phẩm kinh điển của văn học Việt Nam.',
          image: './assets/images/authors/author3.jpg'
        },
        {
          id: 4,
          name: 'Dale Carnegie',
          field: 'Phát triển bản thân',
          description: 'Tác giả nổi tiếng với "Đắc nhân tâm", tác phẩm self-help bán chạy nhất mọi thời đại.',
          image: './assets/images/authors/author4.jpg'
        }
      ]
    }
  },
  mounted() {
    // Khởi tạo carousel sau khi component được mount
    // Lưu ý: Cần import thư viện owl carousel trước khi sử dụng
    this.$nextTick(() => {
      if (window.jQuery && window.jQuery.fn.owlCarousel) {
        window.jQuery('.testimonial-carousel').owlCarousel({
          items: 3,
          margin: 0,
          loop: true,
          autoplay: true,
          smartSpeed: 1000,
          dots: false,
          autoplayHoverPause: false,
          responsiveClass: true,
          responsive: {
            0: { items: 1 },
            640: { items: 2 },
            992: { items: 3 }
          }
        });
      } else {
        console.warn('Owl Carousel chưa được tải. Kiểm tra lại việc import thư viện.');
      }
    });
  }
}
</script>

<style scoped>
/* Styles cụ thể cho component này, nếu cần */
</style> 