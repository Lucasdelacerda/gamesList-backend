export function getUser() {
  const raw = localStorage.getItem('user')
  return raw ? JSON.parse(raw) : null
}

export function saveSession({ userName, email, token, refreshToken }) {
  localStorage.setItem('token', token)
  localStorage.setItem('refreshToken', refreshToken)
  localStorage.setItem('user', JSON.stringify({ userName, email }))
}

export function clearSession() {
  localStorage.removeItem('token')
  localStorage.removeItem('refreshToken')
  localStorage.removeItem('user')
}
