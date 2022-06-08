<template>
  <div id="body" class="home">
    <RegisterForm msg="Welcome to the best Pizza Restaurant in the town!"/>
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
