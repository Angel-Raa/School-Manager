import { defineStore } from 'pinia'
import type { Course, Response } from '@/types'
import { HelpHttp } from '@/helper/HelpHttp'

const api = HelpHttp()
export const useCourseStore = defineStore('course', {
  state: () => ({
    courses: [] as Course[],
    course: {} as Course,
    error: false,
    loading: false,
    response: {} as Response
  }),

  actions: {
    async getAllCourses(): Promise<Course[]> {
      try {
        const courses = await api.get('http://localhost:9090/api/v1/course/all-courses')
        this.courses = courses
        return courses
      } catch (error) {
        this.error = true
        this.loading = false
        return []
      }
    },

    async createCourse(course: Course, teacherId: number): Promise<Course> {
      try {
        const response = await api.post(
          `http://localhost:9090/api/v1/course/save-course/${teacherId}`,
          { body: course }
        )
        this.response = response
        return response
      } catch (error) {
        this.error = true
        this.loading = false
        return {} as Course
      }
    }
  }
})
