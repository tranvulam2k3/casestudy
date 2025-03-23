import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// Import toàn bộ CSS từ assets/css
import "@/assets/css/animate.css";
import "@/assets/css/owl.carousel.min.css";
import "@/assets/css/bootstrap.min.css";
import "@/assets/css/bootsnav.css";
import "@/assets/css/style.css";

// Nếu dùng thêm router hoặc pinia, import thêm ở đây

// import "@/assets/css/animate.css";
// import "@/assets/css/owl.carousel.min.css";

// Tạo app instance
const app = createApp(App);

// Sử dụng router
app.use(router);

// Tạo hàm loadScripts để tải các script cần thiết
const loadScripts = () => {
  // Xóa các script cũ để tránh trùng lặp
  const oldScripts = document.querySelectorAll('script[data-custom-script]');
  oldScripts.forEach(script => script.remove());

  // Danh sách script cần tải
  const scripts = [
    "/src/assets/js/jquery.js",
    "/src/assets/js/bootstrap.min.js",
    "/src/assets/js/bootsnav.js",
    "/src/assets/js/owl.carousel.min.js",
    "/src/assets/js/custom.js"
  ];

  // Tải tuần tự các script để đảm bảo thứ tự thực thi
  const loadScript = (index) => {
    if (index >= scripts.length) return;
    
    const script = document.createElement('script');
    script.src = scripts[index];
    script.async = false;
    script.setAttribute('data-custom-script', 'true');
    
    script.onload = () => loadScript(index + 1);
    script.onerror = () => console.error(`Failed to load script: ${scripts[index]}`);
    
    document.body.appendChild(script);
  };
  
  loadScript(0);
};

// Mount app vào DOM
app.mount('#app');

// Tải script ban đầu
loadScripts();

// Theo dõi thay đổi route để tải lại scripts khi cần
router.afterEach((to, from) => {
  // Chỉ tải lại scripts khi chuyển từ /book về trang chủ
  if (from.path.startsWith('/book') && to.path === '/') {
    // Cho phép DOM cập nhật trước khi tải lại scripts
    setTimeout(() => {
      loadScripts();
    }, 100);
  }
});