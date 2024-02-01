import { type Student, type Response } from '@/types'
const BASE = 'http://localhost:9090/api/v1/student/'
const DEFAULT_HEADERS = {
  'Content-Type': 'application/json'
}
export const getAllStudent = async (): Promise<Student[]> => {
  const response = await fetch(`${BASE}/all-student`)
  const data = (await response.json()) as Student[]
  return data
}

export const addStudent = async (student: Student): Promise<Response> => {
  const response = await fetch('http://localhost:9090/api/v1/student/all-student', {
    method: 'POST',
    headers: DEFAULT_HEADERS,
    body: JSON.stringify(student)
  })
  const data = (await response.json()) as Response
  return data
}
