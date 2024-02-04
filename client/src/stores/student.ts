import type { Student, Response, SubscribedCourse } from '@/types'
import { defineStore } from 'pinia'
import { HelpHttp } from '../helper/HelpHttp'

const api = HelpHttp()
export const useStudent = defineStore('student', {
  state: () => ({
    error: false,
    loading: false,
    response: {} as Response,
    students: [] as Student[],
    student: {} as Student,
    subscribedCourse: [] as SubscribedCourse[]
  }),
  actions: {
    async getStudentById(id: number): Promise<Student> {
      try {
        const student = api.get(`http://localhost:9090/api/v1/student/student-by/${id}`)
        return student
      } catch (error) {
        this.error = true
        return {} as Student
      }
    },
    async addStudentStore(student: Student): Promise<Response> {
      return api
        .post('http://localhost:9090/api/v1/student/add-student', { body: student })
        .then((res) => (this.response = res))
        .catch((err) => {
          this.error = true
          this.loading = false
          this.response.message = err.message
          this.response.status = err.status
          this.students = []
          return this.response as Response
        })
    },

    async subscribedCourse(studentId: number): Promise<SubscribedCourse[]> {
      try {
        const response = await api.get(
          `http://localhost:9090/api/v1/student/student-subscribed-courses/${studentId} `
        )

        return response as SubscribedCourse[]
      } catch (error) {
        this.error = true
        return [] as SubscribedCourse[]
      }
    }
  }
})

export default useStudent
