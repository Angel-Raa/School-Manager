import { HelpHttp } from '@/helper/HelpHttp'
import { defineStore } from 'pinia'
import type { Response, Teacher } from '@/types'
const api = HelpHttp()

export const userTeacher = defineStore('teacher', {
  state: () => ({
    error: false,
    loading: true,
    response: {} as Response,
    teachers: [] as Teacher[],
    teacher: {} as Teacher
  }),

  actions: {
    async save(teacher: Teacher): Promise<Response> {
      this.loading = true
      return api
        .post('http://localhost:9090/api/v1/teacher/add-teacher', { body: teacher })
        .then((res) => res.json())
        .then((res) => {
          this.response = res
          return this.response as Response
        })
        .catch((error) => {
          this.error = true
          this.loading = false
          this.response.message = error.message
          this.response.status = error.status
          return this.response
        })
    }
  }
})
