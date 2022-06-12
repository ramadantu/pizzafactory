<template>
  <div id="body" class="home">
    <RegisterForm/>
  </div>
</template>

<style lang="css">
@import "../assets/styles/home.css";
</style>

<script>
import RegisterForm from '@/components/RegisterForm.vue'

export default {
  name: 'HomeView',
  components: {
    RegisterForm
  }
}
</script>
