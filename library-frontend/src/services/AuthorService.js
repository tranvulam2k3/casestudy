import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/authors';

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
  getAuthors() {
    return axios.get(BASE_URL);
  },
  getAuthorById(id) {
    return axios.get(`${BASE_URL}/${id}`);
  },
  addAuthor(authorData) {
    return axios.post(BASE_URL, authorData);
  },
  updateAuthor(authorData) {
    const id = authorData.id;
    
    if (!id) {
      console.error('No ID provided for updateAuthor');
      return Promise.reject(new Error('No ID provided for update'));
    }
    
    return axios.put(`${BASE_URL}/${id}`, authorData);
  },
  deleteAuthor(id) {
    return axios.delete(`${BASE_URL}/${id}`);
  }
};