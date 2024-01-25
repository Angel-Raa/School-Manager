<script setup lang="ts">
import type { Student } from '@/types'
import { ref, type Ref } from 'vue'
const students: Ref<Student> = ref({
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
</script>

<template>
  <h3 class="text-size-4">Información Personal</h3>
  <form class="ui form">
    <div class="field">
      <label for="name" id="name">Nombre</label>
      <div class="two fields">
        <div class="field">
          <input
            type="text"
            name="shipping[first-name]"
            placeholder="Nombre"
            v-model="students.name"
          />
        </div>

        <div class="field">
          <input
            type="text"
            name="shipping[last-name]"
            placeholder="Apellido"
            v-model="students.surname"
          />
        </div>
      </div>
      <label for="email" id="name">Correo</label>
      <div class="field">
        <input
          type="email"
          name="shipping[email]"
          id="email"
          placeholder="email"
          v-model="students.email"
        />
      </div>
      <label for="phone">Telefono</label>
      <div class="field">
        <input
          type="tel"
          name="shipping[phone]"
          id="phone"
          placeholder="800-000-0000"
          v-model="students.phone"
        />
      </div>
      <div class="field">
        <label for="gender">Genero</label>
        <select name="ui search dropdown" id="gender" v-model="students.gender">
          <option value="">Selecto</option>
          <option value="M">Masculino</option>
          <option value="F">Femenino</option>
        </select>
      </div>
      <div class="field">
        <label for="city" id="city">dirección</label>
        <div class="two fields">
          <div class="field">
            <input
              type="text"
              placeholder="Cuidad"
              name="shipping[city]"
              v-model="students.address.city"
            />
          </div>
          <div class="field">
            <input
              type="text"
              placeholder="Calle"
              name="shipping[street]"
              v-model="students.address.street"
            />
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<style scoped></style>
