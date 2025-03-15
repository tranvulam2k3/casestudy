<template>
  <section id="introduction" class="service">
    <div class="container">
      <div class="service-content">
        <div class="row">
          <div class="col-md-4 col-sm-6" v-for="(service, index) in services" :key="index">
            <div class="single-service-item">
              <div class="single-service-icon">
                <i :class="service.icon"></i>
              </div>
              <h2><a href="#">{{ service.title }}</a></h2>
              <p>
                {{ service.description }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'IntroductionComponent',
  data() {
    return {
      services: [
        {
          icon: 'fa fa-book',
          title: 'Kho sách khổng lồ',
          description: 'Hàng ngàn đầu sách thuộc nhiều thể loại: văn học, khoa học, kinh tế, kỹ năng sống... được cập nhật liên tục.'
        },
        {
          icon: 'fa fa-exchange-alt',
          title: 'Mượn & trả sách linh hoạt',
          description: 'Đặt sách online, mượn và trả nhanh chóng tại quầy hoặc qua dịch vụ giao nhận. Tối ưu thời gian cho bạn.'
        },
        {
          icon: 'fa fa-headphones-alt',
          title: 'Hỗ trợ 24/7',
          description: 'Đội ngũ nhân viên thân thiện, am hiểu sẵn sàng tư vấn và hỗ trợ bạn mọi lúc mọi nơi.'
        }
      ]
    }
  }
}
</script>

<style scoped>
/* Các style specific cho Introduction, nếu cần */
</style> 