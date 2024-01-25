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
