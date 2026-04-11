import { useState } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import Home from './pages/Home'
import Games from './pages/Games'
import Login from './pages/Login'
import Register from './pages/Register'
import Profile from './pages/Profile'
import NavBar from './components/NavBar'
import { getUser, clearSession } from './services/auth'

function App() {
  const [user, setUser] = useState(getUser())

  const handleLogin = (userData) => {
    setUser(userData)
  }

  const handleLogout = () => {
    clearSession()
    setUser(null)
  }

  return (
    <div className="min-h-screen bg-slate-950 text-slate-100">
      <NavBar user={user} onLogout={handleLogout} />
      <main className="mx-auto max-w-6xl px-4 py-6 sm:px-6 lg:px-8">
        <Routes>
          <Route path="/" element={<Home user={user} />} />
          <Route path="/games" element={<Games />} />
          <Route path="/login" element={<Login onLogin={handleLogin} />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/profile"
            element={user ? <Profile /> : <Navigate to="/login" replace />}
          />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </main>
    </div>
  )
}

export default App
