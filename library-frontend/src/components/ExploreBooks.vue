<template>
  <section id="explore" class="featured-cars">
    <div class="container">
      <div class="section-header">
        <p>Khám phá <span>những</span> cuốn sách nổi bật</p>
        <h2>Sách nổi bật</h2>
      </div>
      <div class="featured-cars-content">
        <div class="row">
          <div class="col-lg-3 col-md-4 col-sm-6" v-for="book in featuredBooks" :key="book.id">
            <div class="single-featured-cars">
              <div class="featured-img-box">
                <div class="featured-cars-img">
                  <img :src="book.image" :alt="book.title">
                </div>
                <div class="featured-model-info">
                  <p>
                    Thể loại: {{ book.category }}
                    <span class="featured-mi-span"> {{ book.pages }} trang</span>
                    <span class="featured-hp-span"> {{ book.year }}</span>
                    {{ book.publisher }}
                  </p>
                </div>
              </div>
              <div class="featured-cars-txt">
                <h2><a href="#">{{ book.title }}</a></h2>
                <h3>{{ formatPrice(book.price) }}</h3>
                <p>
                  {{ book.description }}
                </p>
                <button class="welcome-btn" @click="borrowBook(book.id)">
                  mượn sách
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <button class="welcome-btn" @click="loadMoreBooks">
              Xem thêm
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import BookService from '../services/BookService';

export default {
  name: 'ExploreBooksComponent',
  data() {
    return {
      featuredBooks: [
        {
          id: 1,
          title: 'Đắc nhân tâm',
          price: 120000,
          description: 'Cuốn sách nổi tiếng nhất, bán chạy nhất và có tầm ảnh hưởng nhất mọi thời đại.',
          image: './assets/images/books/book1.jpg',
          category: 'Kỹ năng sống',
          pages: 320,
          year: 2019,
          publisher: 'NXB Tổng hợp'
        },
        {
          id: 2,
          title: 'Nhà giả kim',
          price: 85000,
          description: 'Tác phẩm kinh điển của Paulo Coelho về hành trình theo đuổi ước mơ.',
          image: './assets/images/books/book2.jpg',
          category: 'Văn học',
          pages: 228,
          year: 2020,
          publisher: 'NXB Văn học'
        },
        {
          id: 3,
          title: 'Dám nghĩ lớn',
          price: 95000,
          description: 'Cuốn sách giúp bạn mở rộng tư duy và đạt được những mục tiêu lớn trong cuộc sống.',
          image: './assets/images/books/book3.jpg',
          category: 'Kỹ năng sống',
          pages: 256,
          year: 2018,
          publisher: 'NXB Lao động'
        },
        {
          id: 4,
          title: 'Lập trình với Python',
          price: 150000,
          description: 'Sách hướng dẫn lập trình Python từ cơ bản đến nâng cao cho người mới bắt đầu.',
          image: './assets/images/books/book4.jpg',
          category: 'Công nghệ',
          pages: 450,
          year: 2021,
          publisher: 'NXB Thông tin và Truyền thông'
        }
      ],
      page: 1,
      loading: false
    }
  },
  methods: {
    formatPrice(price) {
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    },
    async loadMoreBooks() {
      this.loading = true;
      this.page += 1;
      
      try {
        // Gọi API từ backend
        const response = await BookService.getAllBooks({ page: this.page });
        
        // Nếu API trả về dữ liệu, sử dụng dữ liệu từ API
        if (response.data && response.data.length > 0) {
          this.featuredBooks = [...this.featuredBooks, ...response.data];
        } else {
          // Nếu không có dữ liệu từ API, sử dụng dữ liệu mẫu
          const newBooks = [
            {
              id: 5 + (this.page - 2) * 4,
              title: 'Tôi thấy hoa vàng trên cỏ xanh',
              price: 78000,
              description: 'Tác phẩm nổi tiếng của Nguyễn Nhật Ánh về tuổi thơ miền quê.',
              image: '/src/assets/images/books/book5.jpg',
              category: 'Văn học',
              pages: 300,
              year: 2018,
              publisher: 'NXB Trẻ'
            },
            {
              id: 6 + (this.page - 2) * 4,
              title: 'Người giàu có nhất thành Babylon',
              price: 92000,
              description: 'Những bài học về tài chính cá nhân qua các câu chuyện từ thành Babylon cổ đại.',
              image: '/src/assets/images/books/book6.jpg',
              category: 'Kinh tế',
              pages: 210,
              year: 2019,
              publisher: 'NXB Lao động'
            },
            {
              id: 7 + (this.page - 2) * 4,
              title: 'Đời ngắn đừng ngủ dài',
              price: 88000,
              description: 'Cuốn sách truyền cảm hứng về việc sống trọn vẹn mỗi ngày.',
              image: '/src/assets/images/books/book7.jpg',
              category: 'Kỹ năng sống',
              pages: 240,
              year: 2020,
              publisher: 'NXB Thế giới'
            },
            {
              id: 8 + (this.page - 2) * 4,
              title: 'Lập trình Java cơ bản',
              price: 135000,
              description: 'Sách hướng dẫn lập trình Java từ cơ bản đến nâng cao cho người mới bắt đầu.',
              image: '/src/assets/images/books/book8.jpg',
              category: 'Công nghệ',
              pages: 420,
              year: 2021,
              publisher: 'NXB Thông tin và Truyền thông'
            }
          ];
          
          this.featuredBooks = [...this.featuredBooks, ...newBooks];
        }
      } catch (error) {
        console.error('Lỗi khi tải thêm sách:', error);
      } finally {
        this.loading = false;
      }
    },
    async borrowBook(bookId) {
      try {
        // Gọi API từ backend
        await BookService.borrowBook(bookId);
        alert(`Đã đặt mượn sách có ID: ${bookId}`);
      } catch (error) {
        console.error('Lỗi khi mượn sách:', error);
        alert('Có lỗi xảy ra khi mượn sách. Vui lòng thử lại sau.');
      }
    },
    async fetchBooks() {
      try {
        // Gọi API từ backend
        const response = await BookService.getAllBooks();
        
        // Nếu API trả về dữ liệu, sử dụng dữ liệu từ API
        if (response.data && response.data.length > 0) {
          this.featuredBooks = response.data;
        }
        
        console.log('Đã tải danh sách sách');
      } catch (error) {
        console.error('Lỗi khi tải danh sách sách:', error);
      }
    }
  },
  mounted() {
    // Gọi API khi component được mount
    this.fetchBooks();
  }
}
</script>

<style scoped>
/* Các style specific cho ExploreBooks, nếu cần */
</style> 