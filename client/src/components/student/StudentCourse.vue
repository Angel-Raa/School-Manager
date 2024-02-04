<script setup lang="ts">
import useStudent from '@/stores/student'
import { onMounted, ref, type PropType, type Ref } from 'vue'
import { type SubscribedCourse } from '@/types'

const props = defineProps({
  id: {
    type: Number as PropType<number | undefined>,
    required: true
  }
})

const store = useStudent()
const courses: Ref<SubscribedCourse[]> = ref([])
onMounted(async () => {
  console.log(props.id)
  if (props.id !== undefined) {
    courses.value = await store.subscribedCourse(props.id)
  } else {
    console.error('ID del estudiante no definido')
  }
})
</script>

<template>
  <table class="ui fixed table">
    <thead>
      <tr>
        <th>Profesor</th>
        <th>Asignatura</th>
        <th>descripción</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="it in courses" :key="it.courseId">
        <td>{{ it.teacher }}</td>
        <td>{{ it.name }}</td>
        <td>{{ it.description }}</td>
      </tr>
    </tbody>
  </table>
</template>
