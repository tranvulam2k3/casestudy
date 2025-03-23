<template>
  <header class="book-header">
    <div class="container">
      <div class="header-content">
        <div class="logo-area">
          <router-link to="/" class="logo">
            <span class="book-icon"><i class="fas fa-book"></i></span>
            <span class="logo-text">TechZen Library</span>
          </router-link>
        </div>
        
        <div class="nav-area">
          <ul class="main-nav">
            <li><router-link to="/" exact-active-class="active">Trang chủ</router-link></li>
            <li><router-link to="/book" active-class="active">Danh sách sách</router-link></li>
            <li><router-link to="/authors" active-class="active">Tác giả</router-link></li>
            <li><router-link to="/contact" active-class="active">Liên hệ</router-link></li>
          </ul>
        </div>
        
        <div class="actions-area">
          <div class="search-toggle" @click="toggleSearch">
            <i class="fas fa-search"></i>
          </div>
          <router-link to="/login" class="login-btn">
            <i class="fas fa-user"></i> Đăng nhập
          </router-link>
        </div>
      </div>
      
      <!-- Search Bar -->
      <div class="search-bar" :class="{ 'active': isSearchActive }">
        <input 
          type="text" 
          placeholder="Tìm kiếm sách..." 
          v-model="searchQuery"
          @keyup.enter="searchBooks"
        />
        <button @click="searchBooks">
          <i class="fas fa-search"></i>
        </button>
      </div>
    </div>
  
  </header>
</template>

<script>
export default {
  name: 'BookHeader',
  data() {
    return {
      isSearchActive: false,
      searchQuery: '',
      pageTitle: 'Danh sách sách'
    }
  },
  mounted() {
    // Dynamically load Font Awesome if not already loaded
    if (!document.getElementById('font-awesome-css')) {
      const link = document.createElement('link');
      link.id = 'font-awesome-css';
      link.rel = 'stylesheet';
      link.href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css';
      document.head.appendChild(link);
    }
  },
  methods: {
    toggleSearch() {
      this.isSearchActive = !this.isSearchActive;
    },
    searchBooks() {
      // Emit event to parent component or use route query params
      this.$emit('search', this.searchQuery);
      
      // Alternative: update route query params
      this.$router.push({ 
        path: '/book',
        query: { 
          ...this.$route.query,
          search: this.searchQuery 
        }
      });
      
      this.isSearchActive = false;
    }
  }
}
</script>

<style scoped>
.book-header {
  font-family: 'Roboto', sans-serif;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 0;
}

.logo-area .logo {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #4e4ffa;
  font-weight: 700;
  font-size: 24px;
}

.book-icon {
  margin-right: 10px;
  font-size: 28px;
}

.main-nav {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

.main-nav li {
  margin: 0 15px;
}

.main-nav a {
  color: #333;
  text-decoration: none;
  font-weight: 500;
  text-transform: uppercase;
  font-size: 14px;
  padding: 5px 0;
  position: relative;
  transition: color 0.3s;
}

.main-nav a:hover, .main-nav a.active {
  color: #4e4ffa;
}

.main-nav a:after {
  content: '';
  position: absolute;
  width: 0;
  height: 2px;
  bottom: 0;
  left: 0;
  background-color: #4e4ffa;
  transition: width 0.3s;
}

.main-nav a:hover:after, .main-nav a.active:after {
  width: 100%;
}

.actions-area {
  display: flex;
  align-items: center;
}

.search-toggle {
  cursor: pointer;
  margin-right: 20px;
  font-size: 18px;
  color: #333;
  transition: color 0.3s;
}

.search-toggle:hover {
  color: #4e4ffa;
}

.login-btn {
  background-color: #4e4ffa;
  color: white;
  padding: 8px 15px;
  border-radius: 4px;
  text-decoration: none;
  font-weight: 500;
  transition: background-color 0.3s;
}

.login-btn i {
  margin-right: 5px;
}

.login-btn:hover {
  background-color: #3838d1;
}

.search-bar {
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s;
  display: flex;
  margin-bottom: 20px;
}

.search-bar.active {
  max-height: 50px;
}

.search-bar input {
  flex: 1;
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-right: none;
  border-radius: 4px 0 0 4px;
  outline: none;
}

.search-bar button {
  padding: 10px 15px;
  background: #4e4ffa;
  color: white;
  border: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
}

.page-title-area {
  background-color: #f8f9fa;
  padding: 30px 0;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  text-align: center;
}

.page-title-area h1 {
  font-size: 32px;
  color: #333;
  margin-bottom: 10px;
  font-weight: 700;
}

.breadcrumb {
  font-size: 14px;
  color: #666;
}

.breadcrumb a {
  color: #4e4ffa;
  text-decoration: none;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    align-items: start;
  }
  
  .nav-area {
    margin: 15px 0;
    width: 100%;
  }
  
  .main-nav {
    flex-wrap: wrap;
  }
  
  .main-nav li {
    margin: 5px 10px 5px 0;
  }
  
  .actions-area {
    width: 100%;
    justify-content: space-between;
  }
}
</style> 