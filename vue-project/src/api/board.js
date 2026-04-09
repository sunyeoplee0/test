import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' },
})

export const fetchBoardList = async () => {
  try {
    const res = await api.get('/board/list')
    return res.data
  } catch (e) {
    console.error('fetchBoardList failed', e)
    throw e
  }
}

export const fetchBoardDetail = async (idx) => {
  try {
    const res = await api.get(`/board/${idx}`)
    return res.data
  } catch (e) {
    console.error('fetchBoardDetail failed', e)
    throw e
  }
}

export const registerBoard = async (body) => {
  try {
    const res = await api.post('/board/reg', body)
    return res.data
  } catch (e) {
    console.error('registerBoard failed', e)
    throw e
  }
}
