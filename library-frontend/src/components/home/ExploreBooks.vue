<template>
  <section id="explore" class="featured-books">
    <div class="container">
      <div class="section-header">
        <p>Khám phá những cuốn sách nổi bật</p>
        <h2>Sách nổi bật</h2>
      </div>
      <div class="books-grid">
        <div class="book-card" v-for="book in limitedBooks" :key="book.id">
          <div class="book-image">
            <img v-if="book.image" :src="`http://localhost:8080${book.image}`" :alt="book.title" @error="handleImageError">
            <img v-else :src="placeholderImage" :alt="book.title">
          </div>
          <div class="book-info">
            <span class="book-category">{{ book.genre }}</span>
            <div class="book-details">
              <span> {{ book.authorName }}</span>
              <span>còn {{ book.quantity }} quyển</span>
              <span>{{ book.status }}</span>
            </div>
          </div>
          <div class="book-content">
            <h3 class="book-title">{{ book.title }} </h3>
            <div class="book-price">{{ formatPrice(book.price) }}</div>
            <p class="book-description">{{ book.description }}</p>
            <button class="borrow-button" @click="borrowBook(book.id)">
              Mượn sách
            </button>
          </div>
        </div>
      </div>
      <div class="load-more">
        <button class="load-more-button" @click="loadMoreBooks" :disabled="loading">
          {{ loading ? 'Đang tải...' : 'Xem thêm' }}
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import BookService from '../../services/BookService';

export default {
  name: 'ExploreBooksComponent',
  data() {
    return {
      books: [],
      loading: false,
      bookForm: {
        id: null,
        title: "",
        author: "",
        genre: "",
        quantity: 0,
        price: 0.0,
        status: "",
        image: "",
        authorName: "",
      },
      placeholderImage: 'https://via.placeholder.com/250x350?text=No+Image',
    };
  },
  computed: {
    limitedBooks() {
      return this.books.slice(0, 6);
    }
  },


  methods: {
    formatPrice(price) {
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    },
    loadBooks() {
      this.loading = true;
      BookService.getBooks()
        .then(response => {
          // Đảm bảo mỗi sách đều có mô tả ngắn
          this.books = response.data.map(book => ({
            ...book,
            description: book.description || 'Chưa có mô tả cho sách này.',
            year: book.year || 'N/A'
          }));
          console.log("Loaded books:", this.books);
        })
        .catch(error => {
          console.error("Error loading books:", error);
        })
        .finally(() => {
          this.loading = false;
        });
    },
    borrowBook(bookId) {
      console.log("Mượn sách với ID:", bookId);
      // Gọi API hoặc xử lý mượn ở đây
    },
    loadMoreBooks() {
      this.$router.push('/book');
    },
    handleImageError(event) {
      event.target.src = this.placeholderImage;
    }
  },
  mounted() {
    this.loadBooks();
  }
}
</script>




<style scoped>
.featured-books {
  padding: 80px 0;
  background-color: #fff;
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
  background: #4e4ffa;
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

.books-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 30px;
  margin-bottom: 40px;
}

.book-card {
  background: white;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
  height: 100%;
}

.book-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
}

.book-image {
  height: 250px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f9f9f9;
  padding: 20px;
  border-bottom: 1px solid #eee;
  position: relative;
}

.book-image::after {
  content: '';
  display: block;
  padding-bottom: 100%;
}

.book-image img {
  max-height: 100%;
  max-width: 100%;
  object-fit: contain;
  transition: transform 0.5s ease;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}

.book-card:hover .book-image img {
  transform: scale(1.05);
}

.book-info {
  background-color: #f5f7fa;
  padding: 12px 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  color: #666;
}

.book-category {
  background-color: #4e4ffa;
  color: white;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 500;
}

.book-details {
  display: flex;
  gap: 10px;
}

.book-details span {
  font-size: 12px;
}

.book-content {
  padding: 20px;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

.book-title {
  color: #333;
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 8px;
  line-height: 1.3;
}

.book-price {
  color: #4e4ffa;
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 15px;
}

.book-description {
  color: #666;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 20px;
  flex-grow: 1;
}

.borrow-button {
  background-color: #4e4ffa;
  color: white;
  border: none;
  border-radius: 6px;
  padding: 12px 0;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  width: 100%;
}

.borrow-button:hover {
  background-color: #3838d1;
}

.load-more {
  text-align: center;
  margin-top: 30px;
}

.load-more-button {
  background-color: transparent;
  color: #4e4ffa;
  border: 2px solid #4e4ffa;
  border-radius: 6px;
  padding: 12px 30px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.load-more-button:hover {
  background-color: #4e4ffa;
  color: white;
}

.load-more-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

@media (max-width: 992px) {
  .books-grid {
    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  }
  
  .book-image {
    height: 200px;
  }
}

@media (max-width: 768px) {
  .books-grid {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
  
  .book-image {
    height: 180px;
  }
  
  .book-info {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }
  
  .book-details {
    flex-direction: column;
    gap: 5px;
  }
}
</style> 