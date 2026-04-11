import { Link } from 'react-router-dom'

function NavBar({ user, onLogout }) {
  return (
    <header className="border-b border-slate-800 bg-slate-900/90 backdrop-blur-md">
      <div className="mx-auto flex max-w-6xl items-center justify-between px-4 py-4 sm:px-6 lg:px-8">
        <Link to="/" className="text-xl font-semibold text-slate-100">
          GamesList
        </Link>
        <nav className="flex items-center gap-3 text-sm text-slate-300">
          <Link className="hover:text-white" to="/">
            Home
          </Link>
          <Link className="hover:text-white" to="/games">
            Jogos
          </Link>
          {user ? (
            <>
              <Link className="hover:text-white" to="/profile">
                Perfil
              </Link>
              <button
                onClick={onLogout}
                className="rounded bg-slate-700 px-3 py-2 text-sm text-slate-100 transition hover:bg-slate-600"
              >
                Sair
              </button>
            </>
          ) : (
            <>
              <Link className="hover:text-white" to="/login">
                Login
              </Link>
              <Link className="rounded bg-slate-700 px-3 py-2 text-sm text-slate-100 transition hover:bg-slate-600" to="/register">
                Registrar
              </Link>
            </>
          )}
        </nav>
      </div>
    </header>
  )
}

export default NavBar
