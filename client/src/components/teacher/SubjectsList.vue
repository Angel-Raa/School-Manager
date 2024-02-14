<script setup lang="ts">
import { useCourseStore } from '@/stores/course'
import { type Course } from '@/types/index'
import { ref, type Ref, onMounted } from 'vue'

const store = useCourseStore()

const props = defineProps({
  id: {
    required: true,
    type: Number
  }
})
const subjects: Ref<Course[]> = ref([])

const loadignSujects = async (courseId: number) => {
  subjects.value = await store.getCourseTeachers(courseId)
}
onMounted(() => {
  loadignSujects(props.id)
  
})
</script>

<template>
  <h2>Asignatura</h2>
  <table class="ui fixed table">
    <thead>
      <tr>
        <th>#</th>
        <th>Nombre</th>
        <th>Descripción</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="subject in subjects" :key="subject.courseId">
        <td>{{ subject.courseId }}</td>
        <td>{{ subject.name }}</td>
        <td>{{ subject.description }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped></style>
