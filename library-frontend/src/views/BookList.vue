<template>
  <div class="book-list-page">
    <div class="container">
      <h1 class="page-title">Danh sách sách</h1>
      
      <!-- Filter section -->
      <div class="filter-section">
        <div class="row">
          <div class="col-md-3">
            <div class="form-group">
              <label for="category">Thể loại</label>
              <select class="form-control" id="category" v-model="filters.genre">
                <option value="">Tất cả</option>
                <option v-for="genre in genres" :key="genre" :value="genre">{{ genre }}</option>
              </select>
            </div>
          </div>
          <div class="col-md-3">
            <div class="form-group">
              <label for="author">Tác giả</label>
              <select class="form-control" id="author" v-model="filters.author">
                <option value="">Tất cả</option>
                <option v-for="author in authors" :key="author" :value="author">{{ author }}</option>
              </select>
            </div>
          </div>
          <div class="col-md-3">
            <div class="form-group">
              <label for="status">Trạng thái</label>
              <select class="form-control" id="status" v-model="filters.status">
                <option value="">Tất cả</option>
                <option value="Còn hàng">Còn hàng</option>
                <option value="Hết hàng">Hết hàng</option>
              </select>
            </div>
          </div>
          <div class="col-md-3">
            <div class="form-group">
              <label for="search">Tìm kiếm</label>
              <input type="text" class="form-control" id="search" v-model="filters.search" placeholder="Tên sách...">
            </div>
          </div>
        </div>
      </div>
      
      <!-- Books grid -->
      <div class="books-grid">
        <div class="book-card" v-for="book in filteredBooks" :key="book.id">
          <div class="book-image">
            <img v-if="book.image" :src="`http://localhost:8080${book.image}`" :alt="book.title" @error="handleImageError">
            <img v-else :src="placeholderImage" :alt="book.title">
          </div>
          <div class="book-info">
            <span class="book-category">{{ book.genre }}</span>
            <div class="book-details">
              <span>còn {{ book.quantity }} quyển</span>
              <span>{{ book.year || 'N/A' }}</span>
              <span>{{ book.status }}</span>
            </div>
          </div>
          <div class="book-content">
            <h3 class="book-title">{{ book.title }}</h3>
            <div class="book-price">{{ formatPrice(book.price) }}</div>
            <p class="book-description">{{ book.description || 'Chưa có mô tả cho sách này.' }}</p>
            <button class="borrow-button" @click="borrowBook(book.id)">
              Mượn sách
            </button>
          </div>
        </div>
      </div>
      
      <!-- Pagination -->
      <div class="pagination-container" v-if="totalPages > 1">
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
              <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">Trước</a>
            </li>
            <li class="page-item" v-for="page in paginationPages" :key="page" :class="{ active: page === currentPage }">
              <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages }">
              <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)">Sau</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from '@/services/BookService';

export default {
  name: 'BookList',
  data() {
    return {
      books: [],
      filteredBooks: [],
      loading: false,
      placeholderImage: 'https://via.placeholder.com/250x350?text=No+Image',
      filters: {
        genre: '',
        author: '',
        status: '',
        search: ''
      },
      genres: [],
      authors: [],
      currentPage: 1,
      booksPerPage: 12,
      totalBooks: 0,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.totalBooks / this.booksPerPage);
    },
    paginationPages() {
      const pages = [];
      const maxVisiblePages = 5;
      
      let startPage = Math.max(1, this.currentPage - Math.floor(maxVisiblePages / 2));
      let endPage = Math.min(this.totalPages, startPage + maxVisiblePages - 1);
      
      if (endPage - startPage + 1 < maxVisiblePages) {
        startPage = Math.max(1, endPage - maxVisiblePages + 1);
      }
      
      for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
      }
      
      return pages;
    }
  },
  methods: {
    formatPrice(price) {
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    },
    async loadBooks() {
      this.loading = true;
      try {
        const response = await BookService.getBooks();
        this.books = response.data;
        
        // Extract unique genres and authors
        this.genres = [...new Set(this.books.map(book => book.genre).filter(Boolean))];
        this.authors = [...new Set(this.books.map(book => book.author).filter(Boolean))];
        
        this.applyFilters();
      } catch (error) {
        console.error("Error loading books:", error);
      } finally {
        this.loading = false;
      }
    },
    applyFilters() {
      let filtered = [...this.books];
      
      // Apply genre filter
      if (this.filters.genre) {
        filtered = filtered.filter(book => book.genre === this.filters.genre);
      }
      
      // Apply author filter
      if (this.filters.author) {
        filtered = filtered.filter(book => book.author === this.filters.author);
      }
      
      // Apply status filter
      if (this.filters.status) {
        filtered = filtered.filter(book => book.status === this.filters.status);
      }
      
      // Apply search filter
      if (this.filters.search) {
        const searchTerm = this.filters.search.toLowerCase();
        filtered = filtered.filter(book => 
          book.title.toLowerCase().includes(searchTerm) || 
          (book.description && book.description.toLowerCase().includes(searchTerm))
        );
      }
      
      this.totalBooks = filtered.length;
      
      // Apply pagination
      const startIndex = (this.currentPage - 1) * this.booksPerPage;
      const endIndex = startIndex + this.booksPerPage;
      this.filteredBooks = filtered.slice(startIndex, endIndex);
    },
    changePage(page) {
      if (page < 1 || page > this.totalPages) return;
      this.currentPage = page;
      this.applyFilters();
      // Scroll to top of the page
      window.scrollTo(0, 0);
    },
    borrowBook(bookId) {
      console.log("Mượn sách với ID:", bookId);
      // Gọi API hoặc xử lý mượn ở đây
    },
    handleImageError(event) {
      event.target.src = this.placeholderImage;
    }
  },
  watch: {
    'filters.genre'() {
      this.currentPage = 1;
      this.applyFilters();
    },
    'filters.author'() {
      this.currentPage = 1;
      this.applyFilters();
    },
    'filters.status'() {
      this.currentPage = 1;
      this.applyFilters();
    },
    'filters.search'() {
      this.currentPage = 1;
      this.applyFilters();
    }
  },
  mounted() {
    this.loadBooks();
  }
};
</script>

<style scoped>
.book-list-page {
  padding: 60px 0;
  background-color: #f8f9fa;
}

.page-title {
  text-align: center;
  margin-bottom: 40px;
  color: #333;
  font-size: 32px;
  font-weight: 700;
  position: relative;
  padding-bottom: 15px;
}

.page-title:after {
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

.filter-section {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 15px;
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

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 30px;
}

.pagination {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.page-item {
  margin: 0 5px;
}

.page-link {
  display: block;
  padding: 8px 16px;
  border-radius: 4px;
  border: 1px solid #dee2e6;
  color: #4e4ffa;
  text-decoration: none;
  transition: all 0.3s;
}

.page-item.active .page-link {
  background-color: #4e4ffa;
  border-color: #4e4ffa;
  color: white;
}

.page-item.disabled .page-link {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-link:hover:not(.disabled) {
  background-color: #e9ecef;
}

@media (max-width: 768px) {
  .books-grid {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
  
  .filter-section .row {
    display: block;
  }
  
  .filter-section .col-md-3 {
    width: 100%;
    margin-bottom: 15px;
  }
}
</style> 