<script setup lang="ts">
import { useNota } from '@/stores/nota'
import type { Nota } from '@/types'
import { ref, type Ref, onMounted } from 'vue'

const datas: Ref<Nota[]> = ref([])
const store = useNota()

const loading = async () => {
  datas.value = await store.getNotas()
}
/** TODO realizar para actualizar nota  */
onMounted(() => {
  loading()
  console.log(datas)
})
</script>

<template>
  <table class="ui compact celled definition table">
    <thead class="full-width">
      <tr>
        <th></th>
        <th>Nombre</th>
        <th>Asignaturas</th>
        <th>calificación</th>
        <th>Editar</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="data in datas" :key="data.notaId">
        <td></td>
        <td>{{ data.student }}</td>
        <td>{{ data.course }}</td>
        <td>{{ data.qualification }}</td>
        <td>
          <button>
            <i class="edit outline icon"></i>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</template>
