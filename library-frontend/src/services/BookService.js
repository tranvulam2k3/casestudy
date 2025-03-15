import axios from "axios";

const BASE_URL = "http://localhost:8080/api/v1/librarys"


export default {
    // Lấy danh sách Book
    getBooks() {
      return axios.get(BASE_URL);
    },
  
    // Lấy chi tiết 1 Book
    getBookById(id) {
      return axios.get(`${BASE_URL}/${id}`);
    },
  
    // Tạo mới Book (Controller là @PostMapping -> POST /api/v1/librarys)
    addBook(book) {
      return axios.post(BASE_URL, book);
    },
  
    // Cập nhật Book (Controller là @PutMapping("/{id}") -> PUT /api/v1/librarys/{id})
    updateBook(book) {
      return axios.put(`${BASE_URL}/${book.id}`, book);
    },
  
    // Xóa Book (Controller là @DeleteMapping("/{id}") -> DELETE /api/v1/librarys/{id})
    deleteBook(id) {
      return axios.delete(`${BASE_URL}/${id}`);
    }
  };