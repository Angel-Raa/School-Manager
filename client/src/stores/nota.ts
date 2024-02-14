import { defineStore } from 'pinia'
import { HelpHttp } from '@/helper/HelpHttp'
import { type Nota } from '@/types/index'

const api = HelpHttp()
export const useNota = defineStore('nota', {})


// TODO METOD PARA OBTENER LISTA DE NOTA Y PARA CREAR NOTA 