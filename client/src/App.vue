<script setup lang="ts">
import ProfileForm from './components/ProfileForm.vue';
import NavBar from './views/NavBar.vue'
</script>

<template>
  <NavBar />
  <div class="ui container">
    <RouterView />
  
    <ProfileForm/>
  </div>
</template>

<style scoped></style>
