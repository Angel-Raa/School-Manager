export interface Student {
  id?: number
  name?: string
  surname?: string
  email?: string
  phone?: string
  gender?: string
  address: Address
}

export interface Address {
  city?: string
  street?: string
}

export interface Response {
  message?: string
  status?: string
  code?: number
  timestamp?: string
}

export interface Teacher {
  teacherId?: number
  name?: string
  surname?: string
  email?: string
  phone?: string
  gender?: string
  specialization?: string
  address: Address
}

export interface Course {
  courseId?: number
  name?: string
  description?: string
}

export interface SubscribedCourse {
  courseId: number
  name?: string
  description?: string
  teacher?: string
}

export interface Nota {
  notaId?: number
  student?: string
  course?: string
  qualification?: number
}
