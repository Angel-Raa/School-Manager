<script setup lang="ts">
import { type Student } from '@/types'
import useStudent from '@/stores/student'
import { onMounted, ref, type Ref } from 'vue'
import StudentCourse from './StudentCourse.vue'

const store = useStudent()
const student: Ref<Student> = ref({
  name: '',
  surname: '',
  email: '',
  phone: '',
  gender: '',
  address: {
    city: '',
    street: ''
  }
})
const getStudent = async (id: number) => {
  student.value = await store.getStudentById(id)
}
onMounted(() => {
  getStudent(1)
})
</script>
<template>
  <div class="container">
    <div class="profile">
      <div class="ui card cards">
        <div class="image">
          <img src="@/assets/img/foto.jpg" alt="perfil" />
        </div>
        <div class="content">
          <p class="header">{{ student.name }} {{ student.surname }}</p>
          <div class="meta">
            <span class="email">{{ student.email }}</span>
          </div>
          <div class="meta">
            <span class="date">{{ student.phone }}</span>
          </div>
          <div class="description">{{ student.address.city }}</div>
        </div>
      </div>
    </div>
    <div class="main-content" v-if="student.id">
      <StudentCourse :id="student.id" />
    </div>
    <div class="student-status">
      <span>Estudiante</span>
      <section class="status active">ACTIVO</section>
    </div>
  </div>
</template>

<style>
.container {
  display: grid;
  grid-template-columns: 1fr 3fr; /* Two main columns: profile and main content */
  grid-gap: 1rem;
  margin-top: 3rem;
}

.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* Center profile card vertically */
}

.ui.card {
  width: 100%;
  max-width: 250px; /* Adjust as needed */
}

.main-content {
  display: grid;
  grid-template-rows: auto 1fr; /* Header and main content area */
}

.course {
  grid-row: 2; /* Place StudentCourse in the main content area */
}

.student-status {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 1rem;
  font-size: 16px;
}

.student-status section {
  display: flex;
  background-color: #007bff;
  color: #fff;
  padding: 5px 10px;
  margin-left: 10px;
  border-radius: 2rem;
}

.active section {
  /* Estilos adicionales para el estado activo, si deseas */
  font-weight: bold;
  border: 2px solid #007bff; /* Resaltar más visualmente */
}
</style>
