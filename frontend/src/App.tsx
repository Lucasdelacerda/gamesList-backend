import ListGameContainer from "./components/ListGameContainer"
import './App.css'
import { useEffect, useState } from "react";
import api from './services/api';


function App() {

  const [searchTerm, setSearchTerm] = useState('');
  const [games, setGames] = useState([]);

  const fetchAllGames = () => {
    api.get("/games")
      .then(response => setGames(response.data))
      .catch(err => console.error(err));
  }

  const handleSearch = () => {
  if (searchTerm === '') {

    fetchAllGames();
    return;    
  }
  
    api.get('games/title', {
      params: { title: searchTerm }
    })
      .then(response => {
        setGames(response.data);
      })
      .catch(err => console.error("Erro ao filtrar", err)
      )
  }

  useEffect(() =>{
  fetchAllGames();  
  },[])
  return (
    <div>
      <div className="search-bar">
      <input className='searchBar' type="text" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} placeholder="Nome do jogo" />
      <button onClick={handleSearch}>Pesquisar</button>
      </div>
      <ListGameContainer games={games} />
    </div>
  )
}

export default App
