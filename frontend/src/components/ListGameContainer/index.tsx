import { useEffect, useState } from 'react'
import './styles.css';


interface GameListProps {
  games: any[];

};


export default function ({games}: GameListProps) {

  return (
   <>
   
        {games?.map(game => (
      <div className='list-game-container'>
        <div className="game-image-container">
        <img src={game.imgUrl} alt="Game image" />
        </div>
        <div className="right-container">
          <h1 key={game.id}>
            {game.title}
          </h1>
          <p>{game.shortDescription}</p>
        </div>
          

      </div>
        ))}
    </> 
  )
}