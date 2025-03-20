<template>
  <div class="container mt-3">
    <button @click="showModal(null)" class="btn btn-primary">Add Book</button>
    <table class="table table-striped mt-3">
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên sách</th>
          <th>Tác giả</th>
          <th>Thể loại</th>
          <th>Số lượng</th>
          <th>Giá</th>
          <th>Trạng thái</th>
          <th>URL</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.author }}</td>
          <td>{{ book.genre }}</td>
          <td>{{ book.quantity }}</td>
          <td>{{ book.price }}</td>
          <td>{{ book.status }}</td>
          <td>{{ book.image }}</td>
          <td>
            <button class="btn btn-warning"  @click="showModal(book)">Edit</button>
            <button class="btn btn-danger"  @click="deleteBook(book.id)">Delete</button>
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
                <label for="author" class="form-label">Tác giả</label>
                <input type="text" id="author" v-model="bookForm.author" class="form-control" required />
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
              <button type="submit" class="btn btn-primary">{{ buttonLabel }}</button>
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
        image: "",
      },
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
    showModal(books) {
      if (books) {
        this.bookForm = { ...books };
        this.editing = true;
      } else {
        this.restForm();
      }
      if (!this.modalinstance) {
        this.modalinstance = new Modal(document.getElementById('bookModal'))
      }
      this.modalinstance.show();
    },
    saveBook() {
      const action = this.editing ? 'updateBook' : 'addBook';
      BookService[action](this.bookForm)
        .then(() => {
          this.loadBooks();
          this.modalinstance.hide();
          this.restForm();
        })
        .catch(error => {
          console.error("Error saving book:", error);
        });
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
      BookService.deleteBook(id).then(this.loadBooks)
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
        image: ''
      };
      this.editing = false;
    },
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