<script setup lang="ts">
import { useCourseStore } from '@/stores/course'
import { type Course } from '@/types/index'
import { ref, type Ref, onMounted } from 'vue'
onMounted(() => {
  loadignSujects()
})
const store = useCourseStore()

const props = defineProps({
  id: {
    required: true,
    type: Number
  }
})
const subjects: Ref<Course[]> = ref([])
const loadignSujects = async () => {
  subjects.value = await store.getCourseTeachers(props.id)
}
console.log(subjects.value)
</script>

<template>
  <h2>subjects</h2>
</template>

<style scoped></style>
