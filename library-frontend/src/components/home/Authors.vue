<template>
  <section id="authors" class="testimonial">
    <div class="container">
      <div class="section-header">
        <h2>Tác giả nổi bật</h2>
      </div>
      <div class="author-container">
        <div class="author-item" v-for="author in authors" :key="author.id">
          <div class="author-image">
            <img :src="`http://localhost:8080${author.image}`" :alt="author.title">
          </div>
          <div class="author-content">
            <h3 class="author-name">{{ author.name }}</h3>
            <p class="author-description">{{ author.bio }}</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import AuthorService from '../../services/AuthorService';

export default {
  name: 'AuthorsComponent',
  data() {
    return {
      authors: [],
      authorForm: {
        id: null,
        name: "",
        image: "",
        bio: ""
      }
    }
  },
  methods: {
    loadAuthors() {
      AuthorService.getAuthors()
        .then(response => {
          this.authors = response.data;
        })
        .catch(error => {
          console.error("Error loading authors:", error);
        })
        .finally(() => {
          this.loading = false;
        });
    }
  },
  mounted() {
    this.loadAuthors();
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

/* ----- Phần quan trọng: đổi từ grid sang flex ----- */
.author-container {
  display: flex;
  /* Không cho xuống hàng, tất cả item nằm trên một dòng */
  flex-wrap: nowrap;
  /* Cho phép cuộn ngang */
  overflow-x: auto;
  /* Khoảng cách giữa các item */
  gap: 30px;
  margin-top: 30px;

  /* Tùy chọn: cuộn mượt */
  scroll-behavior: smooth;
}

/* Mỗi item có độ rộng cố định, thay vì để tự co giãn */
.author-item {
  flex: 0 0 300px;
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
  text-align: center;
  /* Để hiển thị đẹp hơn khi cuộn, thêm margin-bottom nếu muốn */
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
</style>
