import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../services/api'
import { saveSession } from '../services/auth'

function Login({ onLogin }) {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (event) => {
    event.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await api.post('/users/login', {
        email,
        password,
      })

      saveSession(response.data)
      onLogin({ userName: response.data.userName, email: response.data.email })
      navigate('/profile')
    } catch (err) {
      setError('Falha ao autenticar. Verifique seu e-mail e senha.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <section className="mx-auto max-w-md rounded-3xl border border-slate-800 bg-slate-900/90 p-8 shadow-xl shadow-slate-950/30">
      <h1 className="text-2xl font-semibold text-slate-100">Login</h1>
      <p className="mt-2 text-sm text-slate-400">Entre com seu e-mail e senha para acessar o painel.</p>

      <form className="mt-6 space-y-5" onSubmit={handleSubmit}>
        <label className="block text-sm text-slate-300">
          E-mail
          <input
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            required
            className="mt-2 w-full rounded-2xl px-4 py-3 bg-slate-950 text-slate-100"
          />
        </label>

        <label className="block text-sm text-slate-300">
          Senha
          <input
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            required
            className="mt-2 w-full rounded-2xl px-4 py-3 bg-slate-950 text-slate-100"
          />
        </label>

        {error && <p className="rounded-2xl border border-rose-600/30 bg-rose-950/80 p-3 text-sm text-rose-100">{error}</p>}

        <button
          type="submit"
          disabled={loading}
          className="w-full rounded-2xl bg-slate-700 px-4 py-3 text-sm font-semibold text-slate-100 transition hover:bg-slate-600 disabled:cursor-not-allowed disabled:opacity-60"
        >
          {loading ? 'Entrando...' : 'Entrar'}
        </button>
      </form>

      <p className="mt-6 text-center text-sm text-slate-400">
        Ainda não tem conta?{' '}
        <Link className="text-slate-100 underline hover:text-white" to="/register">
          Registrar
        </Link>
      </p>
    </section>
  )
}

export default Login
