<script setup lang="ts">
import { useCourseStore } from '@/stores/course'
import type { Course } from '@/types'
import { ref, type Ref, onMounted } from 'vue'
const store = useCourseStore()
const courses: Ref<Course[]> = ref([])
onMounted(() => {
  loading()
})

const loading = async () => {
  courses.value = await store.getAllCourses()
  courses.value ? console.log(courses.value) : console.log('No se han Cargado los curso aun')
}
</script>

<template>
  <div v-if="courses.length === 0">
    <p>No Curso Disponibles</p>
  </div>
  <div class="ui cards top">
    <div class="card">
      <div class="content" v-for="it in courses" :key="it.courseId">
        <div class="header">{{ it.name }}</div>
        <div class="description">{{ it.description }}</div>
      </div>
      <div class="ui bottom attached button"><i class="add icon"></i> Añadir Curso</div>
    </div>
  </div>
</template>
<style scoped>
.top {
  margin-top: 1.5rem;
}
</style>
