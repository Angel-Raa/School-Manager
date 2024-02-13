<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import { type Teacher } from '@/types/index'
import { userTeacher } from '@/stores/teacher'
import SubjectsList from './SubjectsList.vue'
const store = userTeacher()
const teacher: Ref<Teacher> = ref({
  name: '',
  surname: '',
  email: '',
  phone: '',
  gender: '',
  specialization: '',
  address: {
    city: '',
    street: ''
  }
})

const loading = async () => {
  teacher.value = await store.getTeacherById(2)
}

onMounted(() => {
  loading()
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
          <p class="header">{{ teacher.name }} {{ teacher.surname }}</p>
          <div class="meta">
            <span class="email">{{ teacher.email }}</span>
          </div>
          <div class="meta">
            <span class="date">{{ teacher.phone }}</span>
          </div>
          <div class="meta">
            <span class="date">{{ teacher.specialization }}</span>
          </div>
          <div class="description">{{ teacher.address.city }}</div>
        </div>
      </div>
    </div>
    <div class="main-content" v-if="teacher.id">
      <!-- TODO obtener listado de toda la asignatura de maestro  --->
      <SubjectsList :id="teacher.id" />
    </div>
    <div class="student-status">
      <span>Maestro</span>
      <section class="status active">ACTIVO</section>
    </div>
    <!-- TODO Crear asignatura   --->
  </div>
</template>
<style scoped>
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
