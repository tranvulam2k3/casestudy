<template>
  <div class="container mt-3">
    <button @click="showModal(null)" class="btn btn-primary">Add Book</button>
    <table class="table table-striped mt-3">
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên sách</th>
          <th>Thể loại</th>
          <th>Số lượng</th>
          <th>Giá</th>
          <th>Trạng thái</th>
          <th>URL</th>
          <th>Tác giả</th>
          <th>Ảnh tác giả</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.genre }}</td>
          <td>{{ book.quantity }}</td>
          <td>{{ book.price }}</td>
          <td>{{ book.status }}</td>
          <td>
            <img v-if="book.image" :src="`http://localhost:8080${book.image}`" 
                 alt="Book cover" style="max-width: 50px; max-height: 50px;" />
            <span v-else>No image</span>
          </td>
          <td>{{ book.authorName }}</td>
          <td>
            <img v-if="book.authorImage" :src="`http://localhost:8080${book.authorImage}`" 
                 alt="Author image" style="max-width: 50px; max-height: 50px;" />
            <span v-else>No image</span>
          </td>
          <td>
            <button class="btn btn-warning" @click="showModal(book)">Edit</button>
            <button class="btn btn-danger" @click="deleteBook(book.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Modal -->
    <div class="modal fade" id="bookModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ modalTitle }}</h5>
            <button class="btn-close" type="button" data-bs-dismiss="modal"></button>
          </div>

          <div class="modal-body">
            <form @submit.prevent="saveBook">
              <div class="mb-3">
                <label for="title" class="form-label">Tên sách</label>
                <input type="text" id="title" v-model="bookForm.title" class="form-control" required />
              </div>
              <div class="mb-3">
                <label for="genre" class="form-label">Thể loại</label>
                <input type="text" id="genre" v-model="bookForm.genre" class="form-control" required />
              </div>
              <div class="mb-3">
                <label for="quantity" class="form-label">Số lượng</label>
                <input type="number" id="quantity" v-model="bookForm.quantity" class="form-control" required />
              </div>
              <div class="mb-3">
                <label for="price" class="form-label">Giá</label>
                <input type="number" id="price" v-model="bookForm.price" class="form-control" required />
              </div>
              <div class="mb-3">
                <label for="status" class="form-label">Trạng thái</label>
                <input type="text" id="status" v-model="bookForm.status" class="form-control" required />
              </div>
              
              <!-- Trường upload file -->
              <div class="mb-3">
                <label for="imageFile" class="form-label">Ảnh bìa</label>
                <input
                  type="file"
                  id="imageFile"
                  class="form-control"
                  accept="image/*"
                  @change="onFileChange"
                />
                <!-- Hiển thị ảnh preview nếu có -->
                <img
                  v-if="previewImage"
                  :src="previewImage"
                  alt="Preview"
                  style="max-width: 100%; margin-top: 10px;"
                />
              </div>

              <!-- Button area -->
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="hideModal">Đóng</button>
                <button type="submit" class="btn btn-primary">{{ buttonLabel }}</button>
              </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "@/services/BookService";
import { Modal } from 'bootstrap';
import axios from 'axios';

export default {
  name: "BookComponent",
  data() {
    return {
      books: [],
      bookForm: {
        id: null,
        title: "",
        author: "",
        genre: "",
        quantity: 0,
        price: 0.0,
        status: "",
        image: "",      // Đường dẫn ảnh (nếu có)
        authorName: "",
        authorImage: "",
        imageFile: null // File ảnh user chọn
      },
      previewImage: "", // Ảnh xem trước
      editing: false,
      modalinstance: null
    };
  },
  computed: {
    modalTitle() {
      return this.editing ? "Chỉnh sửa sách" : "Thêm sách mới";
    },
    buttonLabel() {
      return this.editing ? "Cập nhật" : "Lưu";
    }
  },
  methods: {
    // Khi user chọn file
    onFileChange(event) {
      const file = event.target.files[0];
      if (!file) return;
      // Lưu file vào bookForm
      this.bookForm.imageFile = file;
      // Tạo preview
      this.previewImage = URL.createObjectURL(file);
    },

    showModal(book) {
      if (book) {
        // Sao chép dữ liệu từ book
        this.bookForm = {
          id: book.id,
          title: book.title,
          author: book.author,
          genre: book.genre,
          quantity: book.quantity,
          price: book.price,
          status: book.status,
          image: book.image,
          authorName: book.authorName,
          authorImage: book.authorImage, // URL ảnh cũ (nếu có)
          imageFile: null    // chưa có file
        };
        // Hiển thị preview ảnh cũ nếu có
        this.previewImage = book.image ? `http://localhost:8080${book.image}` : "";
        this.editing = true;
      } else {
        this.restForm();
      }

      if (!this.modalinstance) {
        this.modalinstance = new Modal(document.getElementById('bookModal'));
      }
      this.modalinstance.show();
    },

    // Lưu book (thêm hoặc cập nhật)
    async saveBook() {
      console.log("saveBook method called", this.bookForm);
      
      try {
        // Upload file trước nếu có
        let imageUrl = this.bookForm.image || '';
        
        if (this.bookForm.imageFile) {
          const formData = new FormData();
          formData.append('file', this.bookForm.imageFile);
          
          const response = await axios.post('http://localhost:8080/api/images/upload', formData);
          if (response.data) {
            imageUrl = response.data; // Lấy URL trả về từ server
            console.log("Image uploaded, URL:", imageUrl);
          }
        }
        
        // Tạo object data để gửi lên server
        const bookData = {
          id: this.bookForm.id,
          title: this.bookForm.title,
          author: this.bookForm.author,
          genre: this.bookForm.genre, 
          quantity: this.bookForm.quantity,
          price: this.bookForm.price,
          status: this.bookForm.status,
          authorName: this.bookForm.authorName,
          authorImage: this.bookForm.authorImage,
          image: imageUrl // Sử dụng URL ảnh đã upload
        };
        
        console.log("Sending book data:", bookData);
        
        // Xác định gọi addBook hay updateBook
        const action = this.editing ? 'updateBook' : 'addBook';
        const result = await BookService[action](bookData);
        
        console.log("Success response:", result);
        this.loadBooks();
        this.modalinstance.hide();
        this.restForm();
      } catch (error) {
        console.error("Error saving book:", error.response || error);
        alert("Lỗi khi lưu sách: " + (error.response?.data || error.message || "Lỗi không xác định"));
      }
    },

    loadBooks() {
      BookService.getBooks()
        .then(response => {
          this.books = response.data;
        })
        .catch(error => {
          console.error("Error loading books:", error);
        });
    },

    deleteBook(id) {
      BookService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        })
        .catch(error => {
          console.error("Error deleting book:", error);
        });
    },

    restForm() {
      this.bookForm = {
        id: null,
        title: '',
        author: '',
        genre: '',
        quantity: 0,
        price: 0,
        status: '',
        image: '',
        authorName: '',
        authorImage: '',
        imageFile: null
      };
      this.previewImage = "";
      this.editing = false;
    },

    hideModal() {
      this.modalinstance.hide();
    }
  },

  mounted() {
    this.loadBooks();
  }
};
</script>

<style scoped>
.modal-backdrop {
  opacity: 0.5;
}
</style>
