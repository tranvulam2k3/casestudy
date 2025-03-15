<template>
  <section id="home" class="hero-wrapper">
    <div class="welcome-hero-content">
      <div class="container">
        <div class="welcome-hero-txt">
          <h2>Chào mừng bạn đến với techzen library</h2>
          <p>
            Khám phá hàng ngàn tựa sách ngay hôm nay Tất cả đều sẵn sàng cho bạn
          </p>
        </div>
      </div>

      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="model-search-content">
              <div class="row">
                <div class="col-md-offset-1 col-md-2 col-sm-12">
                  <div class="single-model-search">
                    <h2>Tên sách cần tìm</h2>
                    <div class="model-select-icon">
                      <input type="text" class="form-control" placeholder="Nhập tên sách cần tìm..." v-model="searchTitle" />
                    </div>
                  </div>
                  <div class="single-model-search">
                    <h2>Giá khoảng từ</h2>
                    <div class="model-select-icon">
                      <select class="form-control" v-model="priceFrom">
                        <option value="10000">10.000</option>
                        <option value="50000">50.000</option>
                        <option value="100000">100.000</option>
                        <option value="500000">500.000</option>
                        <option value="1000000">1.000.000</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-offset-1 col-md-2 col-sm-12">
                  <div class="single-model-search">
                    <h2>Thể loại</h2>
                    <div class="model-select-icon">
                      <select class="form-control" v-model="category">
                        <option value="all">Tất cả</option>
                        <option value="fiction">Văn học</option>
                        <option value="science">Khoa học</option>
                        <option value="business">Kinh doanh</option>
                      </select>
                    </div>
                  </div>
                  <div class="single-model-search">
                    <h2>Giá khoản đến</h2>
                    <div class="model-select-icon">
                      <select class="form-control" v-model="priceTo">
                        <option value="50000">50.000</option>
                        <option value="100000">100.000</option>
                        <option value="500000">500.000</option>
                        <option value="1000000">1.000.000</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-offset-1 col-md-2 col-sm-12">
                  <div class="single-model-search">
                    <h2>Tác giả</h2>
                    <div class="model-select-icon">
                      <select class="form-control" v-model="author">
                        <option value="all">Tất cả</option>
                        <option value="author1">Nguyễn Nhật Ánh</option>
                        <option value="author2">Dale Carnegie</option>
                        <option value="author3">Paulo Coelho</option>
                      </select>
                    </div>
                  </div>
                  <div class="single-model-search">
                    <h2>Trạng thái</h2>
                    <div class="model-select-icon">
                      <select class="form-control" v-model="status">
                        <option value="available">Còn trong thư viện</option>
                        <option value="borrowed">Đã được mượn</option>
                        <option value="processing">Đang xử lý</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-2 col-sm-12">
                  <div class="single-model-search text-center">
                    <button class="welcome-btn model-search-btn" @click="search">
                      Tìm kiếm
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import BookService from '../services/BookService';

export default {
  name: 'HeroComponent',
  data() {
    return {
      searchTitle: '',
      priceFrom: '10000',
      priceTo: '1000000',
      category: 'all',
      author: 'all',
      status: 'available'
    }
  },
  methods: {
    async search() {
      // Tạo đối tượng chứa các tham số tìm kiếm
      const searchParams = {
        title: this.searchTitle,
        priceFrom: this.priceFrom,
        priceTo: this.priceTo,
        category: this.category,
        author: this.author,
        status: this.status
      };
      
      try {
        // Gọi API tìm kiếm
        const response = await BookService.searchBooks(searchParams);
        
        // Emit event để component cha có thể xử lý kết quả tìm kiếm
        this.$emit('search', {
          params: searchParams,
          results: response.data
        });
        
        console.log('Tìm kiếm với các tham số:', searchParams);
        console.log('Kết quả tìm kiếm:', response.data);
      } catch (error) {
        console.error('Lỗi khi tìm kiếm sách:', error);
        // Emit event với kết quả rỗng
        this.$emit('search', {
          params: searchParams,
          results: [],
          error: error.message
        });
      }
    }
  }
}
</script>

<style scoped>
.hero-wrapper {
  position: relative;
  background-image: url('@/assets/images/welcome-hero/welcome-banner.jpg');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  z-index: 1;
}

.hero-wrapper::before {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(42,45,84,.60);
  z-index: -1;
}

.welcome-hero-txt {
  text-align: center;
  padding: 290px 0 372px;
}

.welcome-hero-txt h2 {
  font-size: 42px;
  color: #fff;
  font-weight: 700;
  text-transform: uppercase;
  margin-bottom: 45px;
}

.welcome-hero-txt p {
  font-size: 18px;
  text-transform: initial;
  color: #fff;
  font-weight: 500;
  max-width: 735px;
  margin: 0 auto;
}
</style>