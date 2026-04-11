function Home({ user }) {
  return (
    <section className="space-y-6">
      <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-xl shadow-slate-950/20">
        <h1 className="text-3xl font-semibold text-slate-100">GamesList</h1>
        <p className="mt-3 text-slate-300">
          Bem-vindo à interface de gerenciamento de jogos. Use a navegação acima para acessar a lista de jogos, fazer login ou registrar uma nova conta.
        </p>
        <p className="mt-4 text-slate-200">
          {user ? (
            <>Olá, <span className="font-semibold text-white">{user.userName}</span>! Navegue pela lista de jogos ou acesse seu perfil.</>
          ) : (
            <>Faça login para acessar seu perfil e endpoints protegidos.</>
          )}
        </p>
      </div>

      <div className="rounded-3xl border border-slate-800 bg-slate-900/80 p-6 shadow-xl shadow-slate-950/20">
        <h2 className="text-2xl font-semibold text-slate-100">Jogos</h2>
        <p className="mt-2 text-slate-400">Acesse a página de jogos para ver a lista completa e detalhes dos títulos disponíveis.</p>
      </div>
    </section>
  )
}

export default Home
