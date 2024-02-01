<script setup lang="ts">
import TeacherProfileForm from './components/teacher/TeacherProfileForm.vue';
import NavBar from './views/NavBar.vue'
</script>

<template>
  <NavBar />
  <div class="ui container">
    <TeacherProfileForm/>
    <RouterView />
  </div>
</template>

<style scoped></style>
