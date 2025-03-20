<template>
  <section id="authors" class="testimonial">
    <div class="container">
      <div class="section-header">
        <h2>Tác giả nổi bật</h2>
      </div>
      <div class="author-container">
        <div class="author-item" v-for="author in authors" :key="author.id">
          <div class="author-image">
            <img :src="author.image" :alt="author.name">
          </div>
          <div class="author-content">
            <h3 class="author-name">{{ author.name }}</h3>
            <p class="author-description">{{ author.description }}</p>
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
          description: 'Nhà văn nổi tiếng với nhiều tác phẩm đặc sắc như "Mắt biếc", "Tôi thấy hoa vàng trên cỏ xanh"...',
          image: '/src/assets/images/authors/author3.jpg'
        },
        {
          id: 2,
          name: 'Rosie Nguyễn',
          description: 'Tác giả của những cuốn sách self-help nổi tiếng như "Tuổi trẻ đáng giá bao nhiêu".',
          image: '/src/assets/images/authors/author3.jpg'
        },
        {
          id: 3,
          name: 'Tô Hoài',
          description: 'Nhà văn nổi tiếng với tác phẩm "Dế Mèn phiêu lưu ký", một tác phẩm kinh điển của văn học Việt Nam.',
          image: '/src/assets/images/authors/author3.jpg'
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
#authors {
  padding: 80px 0;
  background-color: #f8f9fb;
}

.section-header {
  text-align: center;
  margin-bottom: 50px;
}

.section-header h2 {
  color: #333;
  font-size: 32px;
  font-weight: 700;
  position: relative;
  padding-bottom: 15px;
}

.section-header h2:after {
  content: '';
  position: absolute;
  display: block;
  width: 50px;
  height: 3px;
  background: #98d1d3;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
}

.section-header p {
  color: #666;
  margin-bottom: 5px;
}

.section-header p span {
  color: #4e4ffa;
}

.author-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
  margin-top: 30px;
}

.author-item {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
  text-align: center;
}

.author-item:hover {
  transform: translateY(-10px);
}

.author-image {
  width: 150px;
  height: 150px;
  margin: 30px auto 20px;
  border-radius: 50%;
  overflow: hidden;
  border: 4px solid #9fc6d8;
  box-shadow: 0 0 0 4px rgba(78, 79, 250, 0.1);
}

.author-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.5s ease;
}

.author-item:hover .author-image img {
  transform: scale(1.1);
}

.author-content {
  padding: 0 30px 60px;
  
}

.author-name {
  color: #333;
  font-size: 30px;
  font-weight: 700;
  margin-bottom: 5px;
}


.author-description {
  color: #666;
  font-size: 20px;
  line-height: 1.6;
}

@media (max-width: 992px) {
  .author-container {
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  }
}

@media (max-width: 768px) {
  .author-container {
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  }
  
  .author-image {
    width: 100px;
    height: 100px;
  }
}
</style> 