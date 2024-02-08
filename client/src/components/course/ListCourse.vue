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

const subscribeCourse = (courseId: number) => {
  // TODO para Luego
}
</script>

<template>
  <div v-if="courses.length === 0">
    <div class="no-courses">
      <i class="fa fa-exclamation-triangle"></i>
      <p>No A Cursos Disponibles</p>
    </div>
  </div>
  <div class="ui grid">
    <div class="three column row">
      <div class="column" v-for="course in courses" :key="course.courseId">
        <div class="course-card">
          <div class="image-container">
            <img src="@/assets/img/courses.png" alt="" />
            <div class="overlay">
              <div class="course-info">
                <h3 class="course-title">{{ course.name }}</h3>
                <p class="course-description">{{ course.description }}</p>
              </div>
            </div>
          </div>
          <div class="action-button">
            <button class="ui button" @click="() => console.log(course)">Añadir Curso</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Basic styles */
.ui.grid {
  margin: 0 auto;
  padding: 20px;
}

.three.column.row {
  display: flex;
  flex-wrap: wrap;
}

.column {
  padding: 10px;
}

/* Course card styles */
.course-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: all 0.2s ease-in-out;
  margin-top: 2rem;
  position: relative; /* Allow for overlay positioning */
}

.course-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.course-card .image-container {
  text-align: center;
  position: relative; /* Allow for overlay positioning */
}

.course-card .image-container img {
  max-width: 100%;
  height: auto;
}

.course-card .overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4); /* Semi-transparent overlay */
  color: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: opacity 0.3s ease-in-out;
}

.course-card:hover .overlay {
  opacity: 1;
}

.course-info {
  padding: 15px;
}

.course-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.course-description {
  font-size: 14px;
  color: #fff;
}

.action-button {
  display: flex;
  justify-content: center;
  padding: 10px;
}

.action-button button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 4px;
  cursor: pointer;
}

.action-button button:hover {
  background-color: #0062cc;
}
.no-courses {
  text-align: center;
  margin-top: 2rem;
  font-size: 18px;
  font-weight: bold;
  color: #777;
}

.no-courses p {
  margin: 0;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f7f7f7;
}
</style>
