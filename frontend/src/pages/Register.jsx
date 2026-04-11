import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../services/api'

function Register() {
  const navigate = useNavigate()
  const [userName, setUserName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [message, setMessage] = useState('')
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (event) => {
    event.preventDefault()
    setError('')
    setMessage('')
    setLoading(true)

    try {
      await api.post('/users', {
        userName,
        email,
        password,
      })

      setMessage('Conta criada com sucesso! Você pode fazer login agora.')
      setUserName('')
      setEmail('')
      setPassword('')
      setTimeout(() => navigate('/login'), 1200)
    } catch (err) {
      setError('Não foi possível criar a conta. Verifique os dados e tente novamente.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <section className="mx-auto max-w-md rounded-3xl border border-slate-800 bg-slate-900/90 p-8 shadow-xl shadow-slate-950/30">
      <h1 className="text-2xl font-semibold text-slate-100">Registrar</h1>
      <p className="mt-2 text-sm text-slate-400">Crie uma conta para acessar recursos protegidos e gerenciar sua lista de jogos.</p>

      <form className="mt-6 space-y-5" onSubmit={handleSubmit}>
        <label className="block text-sm text-slate-300">
          Nome de usuário
          <input
            value={userName}
            onChange={(event) => setUserName(event.target.value)}
            required
            className="mt-2 w-full rounded-2xl px-4 py-3 bg-slate-950 text-slate-100"
          />
        </label>

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
        {message && <p className="rounded-2xl border border-emerald-600/30 bg-emerald-950/80 p-3 text-sm text-emerald-100">{message}</p>}

        <button
          type="submit"
          disabled={loading}
          className="w-full rounded-2xl bg-slate-700 px-4 py-3 text-sm font-semibold text-slate-100 transition hover:bg-slate-600 disabled:cursor-not-allowed disabled:opacity-60"
        >
          {loading ? 'Criando conta...' : 'Criar conta'}
        </button>
      </form>

      <p className="mt-6 text-center text-sm text-slate-400">
        Já tem conta?{' '}
        <Link className="text-slate-100 underline hover:text-white" to="/login">
          Entrar
        </Link>
      </p>
    </section>
  )
}

export default Register
