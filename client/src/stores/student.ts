import type { Student, Response } from '@/types'
import { defineStore } from 'pinia'
import { addStudent } from '@/service'
import { HelpHttp } from '../helper/HelpHttp'

const api = HelpHttp()
export const useStudent = defineStore('student', {
  state: () => ({
    error: false,
    loading: false,
    response: '' as Response,
    students: [] as Student[],
    student: {} as Student
  }),
  actions: {
    async getAllStudentsStore(): Promise<Student[]> {
      this.loading = true
      return api
        .get('http://localhost:9090/api/v1/student/add-student')
        .then((res) => res.json)
        .then((res) => (this.response = res))
        .catch((err) => {
          console.log(err)
          this.error = true
          this.loading = false
          this.response.message = err.message
          this.response.status = err.status
          this.students = []
          return []
        })
    },
    async addStudentStore(student: Student): Promise<Response> {
      return api
        .post('http://localhost:9090/api/v1/student/add-student', { body:student })
        .then((res) => (this.response = res))
        .catch((err) => {
          this.error = true
          this.loading = false
          this.response.message = err.message
          this.response.status = err.status
          this.students = []
          return this.response as Response
        })
        
    }

  }
})

export default useStudent
