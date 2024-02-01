type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH'

type RequestOptions = {
  method?: HttpMethod
  headers?: Record<string, string>
  body?: Record<string, any>
  signal?: AbortSignal
}
export const HelpHttp = () => {
  const customFetch = async (endpoint: string | URL | Request, options: any | RequestOptions) => {
    const defaultHeaders = {
      'Content-Type': 'application/json',
      accept: 'application/json'
    }
    const controller = new AbortController()
    options.signal = controller.signal
    options.method = options.method || 'GET'
    options.headers = options.headers ? { ...defaultHeaders, ...options.headers } : defaultHeaders
    options.body = JSON.stringify(options.body) || false
    if (!options.body) delete options.body
    setTimeout(() => controller.abort(), 5000)
    return fetch(endpoint, options).then((res) =>
      res.ok
        ? res.json()
        : Promise.reject({
            err: true,
            status: res.status || '00',
            statusText: res.statusText || 'Ocurrión un error'
          })
    )
  }

  const get = async (url: any, options = {}) => {
    return await customFetch(url, options)
  }
  const post = async (url: any, options = {}) => {
    options.method = 'POST'
    return await customFetch(url, options)
  }
  const put = async (url: any, options = {}) => {
    options.method = 'PUT'
    return await customFetch(url, options)
  }
  const del = async (url: any, options = {}) => {
    options.method = 'DELETE'
    return await customFetch(url, options)
  }

  const patch = async (url: any, options = {}) => {
    options.method = 'PATCH'
    return await customFetch(url, options)
  }

  return {
    get,
    post,
    put,
    del,
    patch
  }
}
