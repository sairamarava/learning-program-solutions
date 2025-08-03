import './App.css';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
  // Array with 11 players and their scores
  const players = [
    { name: 'Smith', score: 50 },
    { name: 'Warner', score: 70 },
    { name: 'Root', score: 40 },
    { name: 'Carse', score: 61 },
    { name: 'Stokes', score: 61 },
    { name: 'Sachin', score: 95 },
    { name: 'Dhoni', score: 100 },
    { name: 'Virat', score: 84 },
    { name: 'Jadeja', score: 64 },
    { name: 'Raina', score: 75 },
    { name: 'Rohit', score: 80 }
  ];

  // Flag variable to control component display
  var flag = false;
  
  if (flag === true) {
    return (
      <div>
        <ListofPlayers players={players} />
      </div>
    );
  } else {
    return (
      <div>
        <IndianPlayers />
      </div>
    );
  }
}

export default App;
