import React from 'react';

const ListofPlayers = ({ players }) => {
  // Filter players with scores below 70 using arrow functions
  const playersBelow70 = players.filter(player => player.score < 70);

  return (
    <div>
      <h1>List of Players</h1>
      {players.map((item) => (
        <div key={item.name}>
          <span>Mr. {item.name}: </span><span>{item.score}</span>
        </div>
      ))}
      
      <hr />
      
      <h1>List of Players having Scores Less than 70</h1>
      {playersBelow70.map((item) => (
        <div key={item.name}>
          <span>Mr. {item.name}: </span><span>{item.score}</span>
        </div>
      ))}
    </div>
  );
};

export default ListofPlayers;
