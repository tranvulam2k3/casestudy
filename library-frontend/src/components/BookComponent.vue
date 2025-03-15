<template>
    <div class="container mt-3">
      <button class="btn btn-primary">Add Book</button>
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
            <td>
              <button class="btn btn-warning">Edit</button>
              <button class="btn btn-danger">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import BookService from "@/services/BookService";
  
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
        },
      };
    },
    methods: {
      loadBooks() {
        BookService.getBooks()
          .then(response => {
            this.books = response.data;
          })
          .catch(error => {
            console.error("Error loading books:", error);
          });
      },
    },
    mounted() {
      this.loadBooks();
    }
  };
  </script>
  