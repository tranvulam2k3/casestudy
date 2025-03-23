<template>
    <div class="container mt-3">
        <button @click="showModal(null)" class="btn btn-primary">Add Author</button>
        <table class="table table-striped mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên tác giả</th>
                    <th>Mô tả</th>
                    <th>Ảnh tác giả</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="author in authors" :key="author.id">
                    <td>{{ author.id }}</td>
                    <td>{{ author.name }}</td>
                    <td>{{ author.bio }}</td>
                    <td>
                        <img v-if="author.image" :src="`http://localhost:8080${author.image}`" alt="Author image"
                            style="max-width: 50px; max-height: 50px;" />
                        <span v-else>No image</span>
                    </td>
                    <td>
                        <button class="btn btn-warning" @click="showModal(author)">Edit</button>
                        <button class="btn btn-danger" @click="deleteAuthor(author.id)">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- Modal -->
        <div class="modal fade" id="authorModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLabel">{{ modalTitle }}</h5>
                        <button class="btn-close" type="button" @click="hideModal"></button>
                    </div>

                    <div class="modal-body">
                        <form @submit.prevent="saveAuthor">
                            <div class="mb-3">
                                <label for="name" class="form-label">Tên tác giả</label>
                                <input type="text" id="name" v-model="authorForm.name" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label for="bio" class="form-label">Mô tả</label>
                                <textarea id="bio" v-model="authorForm.bio" class="form-control" rows="3" required></textarea>
                            </div>

                            <!-- Trường upload file -->
                            <div class="mb-3">
                                <label for="imageFile" class="form-label">Ảnh tác giả</label>
                                <input type="file" id="imageFile" class="form-control" accept="image/*"
                                    @change="onFileChange" />
                                <!-- Hiển thị ảnh preview nếu có -->
                                <img v-if="previewImage" :src="previewImage" alt="Preview"
                                    style="max-width: 100%; margin-top: 10px;" />
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
import AuthorService from "@/services/AuthorService";
import { Modal } from 'bootstrap';
import axios from 'axios';

export default {
    name: "AuthorComponent",
    data() {
        return {
            authors: [],
            authorForm: {
                id: null,
                name: "",
                bio: "",
                image: "",      // Đường dẫn ảnh (nếu có)
                imageFile: null // File ảnh user chọn
            },
            previewImage: "", // Ảnh xem trước
            editing: false,
            modalinstance: null
        };
    },
    computed: {
        modalTitle() {
            return this.editing ? "Chỉnh sửa tác giả" : "Thêm tác giả mới";
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
            // Lưu file vào authorForm
            this.authorForm.imageFile = file;
            // Tạo preview
            this.previewImage = URL.createObjectURL(file);
        },

        showModal(author) {
            console.log("Showing modal for author:", author);
            if (author) {
                // Sao chép dữ liệu từ author
                this.authorForm = {
                    id: author.id,
                    name: author.name,
                    bio: author.bio,
                    image: author.image,
                    imageFile: null    // chưa có file
                };
                // Hiển thị preview ảnh cũ nếu có
                this.previewImage = author.image ? `http://localhost:8080${author.image}` : "";
                this.editing = true;
            } else {
                this.restForm();
            }

            if (!this.modalinstance) {
                this.modalinstance = new Modal(document.getElementById('authorModal'));
            }
            this.modalinstance.show();
        },

        // Lưu author (thêm hoặc cập nhật)
        async saveAuthor() {
            console.log("saveAuthor method called", this.authorForm);

            try {
                // Upload file trước nếu có
                let imageUrl = this.authorForm.image || '';

                if (this.authorForm.imageFile) {
                    const formData = new FormData();
                    formData.append('file', this.authorForm.imageFile);

                    const response = await axios.post('http://localhost:8080/api/images/upload', formData);
                    if (response.data) {
                        imageUrl = response.data; // Lấy URL trả về từ server
                        console.log("Image uploaded, URL:", imageUrl);
                    }
                }

                // Tạo object data để gửi lên server
                const authorData = {
                    id: this.authorForm.id,
                    name: this.authorForm.name,
                    bio: this.authorForm.bio,
                    image: imageUrl // Sử dụng URL ảnh đã upload
                };

                console.log("Sending author data:", authorData);

                // Xác định gọi addAuthor hay updateAuthor
                const action = this.editing ? 'updateAuthor' : 'addAuthor';
                const result = await AuthorService[action](authorData);

                console.log("Success response:", result);
                this.loadAuthors();
                this.modalinstance.hide();
                this.restForm();
            } catch (error) {
                console.error("Error saving author:", error.response || error);
                alert("Lỗi khi lưu tác giả: " + (error.response?.data || error.message || "Lỗi không xác định"));
            }
        },

        loadAuthors() {
            AuthorService.getAuthors()
                .then(response => {
                    this.authors = response.data;
                })
                .catch(error => {
                    console.error("Error loading authors:", error);
                });
        },

        deleteAuthor(id) {
            AuthorService.deleteAuthor(id)
                .then(() => {
                    this.loadAuthors();
                })
                .catch(error => {
                    console.error("Error deleting author:", error);
                });
        },

        restForm() {
            this.authorForm = {
                id: null,
                name: '',
                bio: '',
                image: '',
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
        this.loadAuthors();
    }
};
</script>

<style scoped>
.modal-backdrop {
    opacity: 0.5;
}
</style>