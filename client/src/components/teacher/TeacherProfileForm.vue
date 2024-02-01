<script setup lang="ts">
import { userTeacher } from '@/stores/teacher'
import type { Teacher } from '@/types'
import { ref, type Ref } from 'vue'
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
const save = async () => {
  const response = await store.save(teacher.value)
  console.log(teacher.value)
  teacher.value.address.city = ''
  teacher.value.specialization = ''
  teacher.value.email = ''
  teacher.value.gender = ''
  teacher.value.surname = ''
  teacher.value.name = ''
  teacher.value.address.street = ''
  teacher.value.phone = ''
}
</script>

<template>
  <h3 class="text-size-4">Información Personal</h3>
  <form class="ui form" @submit.prevent="save()">
    <div class="field">
      <div class="field">
        <label for="name">Nombre</label>
        <input type="text" name="name" id="name" placeholder="Nombre" v-model="teacher.name" />
      </div>
      <div class="field">
        <label for="surname">Apellido</label>
        <input
          type="text"
          name="surname"
          id="surname"
          placeholder="Apellido"
          v-model="teacher.surname"
        />
      </div>
      <div class="field">
        <label for="email">Correo</label>
        <input
          type="email"
          v-model="teacher.email"
          name="email"
          id="email"
          placeholder="example@gmail.com"
        />
      </div>
      <div class="field">
        <label for="phone">Telefono</label>
        <input
          v-model="teacher.phone"
          type="tel"
          name="phone"
          id="phone"
          placeholder="000-0000-000"
          pattern="^\d{3}-\d{3}-\d{4}$"
        />
      </div>
      <div class="field">
        <label for="gender">Genero</label>
        <select v-model="teacher.gender" class="ui search dropdown" id="gender">
          <option value="">Selecto</option>
          <option value="M">Masculino</option>
          <option value="F">Femenino</option>
        </select>
      </div>

      <div class="field">
        <label for="specialization">asignaturas</label>
        <select v-model="teacher.specialization" class="ui search dropdown" id="specialization">
          <option value="">Selecto</option>
          <option value="Matematica">Matemáticas</option>
          <option value="Ciencias">Ciencias</option>
          <option value="Literatura">Literatura</option>
          <option value="Historia">Historia</option>
          <option value="Física">Física</option>
          <option value="Química">Química</option>
          <option value="Biología">Biología</option>
          <option value="Arte">Arte</option>
        </select>
      </div>
      <div class="field">
        <label for="city" id="city">dirección</label>
        <div class="two fields">
          <div class="field">
            <input type="text" placeholder="Cuidad" name="city" v-model="teacher.address.city" />
          </div>
          <div class="field">
            <input type="text" placeholder="Calle" name="street" v-model="teacher.address.street" />
          </div>
        </div>
      </div>
      <button type="submit" class="ui primary button">Save</button>
    </div>
  </form>
</template>
