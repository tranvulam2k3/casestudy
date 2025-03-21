import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/librarys';

// Cấu hình axios
axios.interceptors.request.use(request => {
  console.log('Starting Request', request);
  return request;
});

axios.interceptors.response.use(
  response => {
    console.log('Response:', response);
    return response;
  },
  error => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

export default {
  getBooks() {
    return axios.get(BASE_URL);
  },

  addBook(bookData) {
    return axios.post(BASE_URL, bookData);
  },

  updateBook(bookData) {
    const id = bookData.id;
    
    if (!id) {
      console.error('No ID provided for updateBook');
      return Promise.reject(new Error('No ID provided for update'));
    }
    
    return axios.put(`${BASE_URL}/${id}`, bookData);
  },

  deleteBook(id) {
    return axios.delete(`${BASE_URL}/${id}`);
  }
};
