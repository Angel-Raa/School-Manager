<script setup lang="ts">
import type { Course } from '@/types'
import { ref, type Ref } from 'vue'
import { useCourseStore } from '@/stores/course'
const store = useCourseStore()
const props = defineProps({
  id: {
    required: true,
    type: Number
  }
})
const course: Ref<Course> = ref({
  name: '',
  description: ''
})

const createCourse = async () => {
  store.createCourse(props.id, course.value)
  course.value.description = ''
  course.value.name = ''
}
</script>

<template>
  <h2>Agregar asignatura</h2>
  <div class="ui form">
    <div class="field">
      <label for="name">nombre de asignatura</label>
      <input
        required
        type="text"
        class="name"
        id="name"
        placeholder="nombre de curso"
        v-model="course.name"
      />
    </div>
    <div class="field">
      <label for="description">descripcion</label>
      <textarea
        name="description"
        id="description"
        style="height: 177px"
        autocomplete="on"
        placeholder="descripcion de asignatura"
        required
        v-model="course.description"
        autocorrect="on"
      ></textarea>
    </div>
    <button type="submit" class="ui primary button" @click="createCourse">Save</button>
  </div>
</template>

<style scoped>
/* Media queries para diferentes dispositivos */

@media (max-width: 768px) {
  /* Ajustar estilos para pantallas pequeñas */
}

textarea {
  resize: none;
  border: 2px solid #eee;
  border-radius: 6px;
}
</style>
