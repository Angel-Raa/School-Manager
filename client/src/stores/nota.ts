import { defineStore } from 'pinia'
import { HelpHttp } from '@/helper/HelpHttp'
import { type Nota } from '@/types/index'

const api = HelpHttp()
export const useNota = defineStore('nota', {
  actions: {
    async getNotas(): Promise<Nota[]> {
      const data = await api.get('http://localhost:9090/api/v1/nota/all-nota')
      return data
    }
  }
})

// TODO METOD PARA OBTENER LISTA DE NOTA Y PARA CREAR NOTA
