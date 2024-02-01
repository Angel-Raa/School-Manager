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
  <section v-for="it in courses" :key="it.courseId">
    <h2>{{ it.name }}</h2>
    <p>{{ it.description }}</p>
  </section>
</template>
