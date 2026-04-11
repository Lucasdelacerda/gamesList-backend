import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../services/api'
import { getUser, clearSession } from '../services/auth'

function Profile() {
  const navigate = useNavigate()
  const [profile, setProfile] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    const user = getUser()
    if (!user) {
      navigate('/login', { replace: true })
      return
    }

    api
      .get('/users/me')
      .then((response) => {
        setProfile(response.data)
      })
      .catch(() => {
        setError('Falha ao carregar os dados. Faça login novamente.')
      })
      .finally(() => {
        setLoading(false)
      })
  }, [navigate])

  const handleLogout = () => {
    clearSession()
    navigate('/login', { replace: true })
  }

  if (loading) {
    return <div className="text-slate-300">Buscando informações do perfil...</div>
  }

  if (error) {
    return <div className="rounded-3xl border border-rose-600/40 bg-rose-950/20 p-6 text-rose-100">{error}</div>
  }

  return (
    <section className="mx-auto max-w-xl space-y-6 rounded-3xl border border-slate-800 bg-slate-900/90 p-8 shadow-xl shadow-slate-950/30">
      <div>
        <h1 className="text-2xl font-semibold text-slate-100">Meu Perfil</h1>
        <p className="mt-2 text-slate-400">Detalhes do usuário autenticado.</p>
      </div>

      <div className="grid gap-4 rounded-3xl border border-slate-800 bg-slate-950/80 p-6">
        <div>
          <h2 className="text-sm uppercase tracking-[0.2em] text-slate-500">Nome</h2>
          <p className="mt-2 text-xl font-semibold text-slate-100">{profile.userName}</p>
        </div>
        <div>
          <h2 className="text-sm uppercase tracking-[0.2em] text-slate-500">E-mail</h2>
          <p className="mt-2 text-slate-100">{profile.email}</p>
        </div>
        <div>
          <h2 className="text-sm uppercase tracking-[0.2em] text-slate-500">Função</h2>
          <p className="mt-2 text-slate-100">{profile.role || 'Usuário'}</p>
        </div>
      </div>

      <button
        onClick={handleLogout}
        className="w-full rounded-2xl bg-rose-600 px-5 py-3 text-sm font-semibold text-white transition hover:bg-rose-500"
      >
        Sair
      </button>
    </section>
  )
}

export default Profile
