import { useEffect, useState } from 'react'
import api from '../services/api'

function Games() {
  const [games, setGames] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    api
      .get('/games')
      .then((response) => {
        setGames(response.data)
      })
      .catch(() => {
        setError('Não foi possível carregar os jogos. Verifique se o backend está rodando em http://localhost:8080')
      })
      .finally(() => {
        setLoading(false)
      })
  }, [])

  return (
    <section className="space-y-6">
      <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-xl shadow-slate-950/20">
        <h1 className="text-3xl font-semibold text-slate-100">Lista de Jogos</h1>
        <p className="mt-3 text-slate-300">
          Navegue por todos os jogos públicos disponíveis na API.
        </p>
      </div>

      {loading ? (
        <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 text-slate-300 shadow-xl shadow-slate-950/10">
          Carregando jogos...
        </div>
      ) : error ? (
        <div className="rounded-3xl border border-rose-600/40 bg-rose-950/20 p-6 text-rose-100 shadow-xl shadow-rose-950/20">
          {error}
        </div>
      ) : (
        <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {games.map((game) => (
            <article key={game.id} className="overflow-hidden rounded-3xl border border-slate-800 bg-slate-950/90 p-5 transition hover:-translate-y-1 hover:border-slate-600">
              <h2 className="text-lg font-semibold text-slate-100">{game.title}</h2>
              <p className="mt-2 text-sm text-slate-400">Score: <span className="font-semibold text-slate-100">{game.score?.toFixed(1) || 'N/A'}</span></p>
              <p className="mt-3 text-slate-300">{game.shortDescription || 'Sem descrição disponível.'}</p>
            </article>
          ))}
        </div>
      )}
    </section>
  )
}

export default Games
