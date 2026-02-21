import { useEffect, useState } from 'react'
import api from './services/api'
import './App.css'


interface Game {
  id:number;
  title:string;
  year:number;
  imgUrl:string;
}

function App() {
const [games,setGames] = useState<Game[]>([]);

useEffect(() =>{
  api.get('/games')
  .then(response =>{
    console.log("Dados que chegaram do Java:", response.data);
   setGames(response.data); 
  })
  .catch(err => console.error("erro na requisição", err));
},[])

  return (
    <>
      <input className='searchBar' type="text" />

     
      <ul>
        {games?.map(game => (
          <li key={game.id}>{game.title}</li>
        ))}
      </ul>
    </>
  )
}

export default App
