import React from 'react';

// OddPlayers component using destructuring
export function OddPlayers(first, third, fifth) {
  return (
    <div>
      <li>First: {first}</li>
      <li>Third: {third}</li>
      <li>Fifth: {fifth}</li>
    </div>
  );
}

// EvenPlayers component using destructuring  
export function EvenPlayers(second, fourth, sixth) {
  return (
    <div>
      <li>Second: {second}</li>
      <li>Fourth: {fourth}</li>
      <li>Sixth: {sixth}</li>
    </div>
  );
}

// ListofIndianPlayers component for merging arrays
const ListofIndianPlayers = ({ IndianPlayers }) => {
  return (
    <div>
      {IndianPlayers.map((item) => (
        <div key={item}>
          <span>Mr. {item}</span>
        </div>
      ))}
    </div>
  );
};

const IndianPlayers = () => {
  // Declare arrays for merging using ES6 spread operator
  const T20Players = ['First Player', 'Second Player', 'Third Player'];
  const RanjiTrophyPlayers = ['Fourth Player', 'Fifth Player', 'Sixth Player'];
  
  // Merge arrays using spread operator
  const IndianTeam = [...T20Players, ...RanjiTrophyPlayers];
  
  // Team arrays for odd/even destructuring
  const IndianTeamArray = ['Sachin', 'Dhoni', 'Virat', 'Rohit', 'Yuvaraj', 'Raina'];

  // Destructuring for odd and even players
  const [first, second, third, fourth, fifth, sixth] = IndianTeamArray;

  return (
    <div>
      <div>
        <h1>Odd Players</h1>
        {OddPlayers(first, third, fifth)}
        <hr />
        <h1>Even Players</h1>
        {EvenPlayers(second, fourth, sixth)}
      </div>
      <hr />
      <div>
        <h1>List of Indian Players Merged:</h1>
        <ListofIndianPlayers IndianPlayers={IndianTeam} />
      </div>
    </div>
  );
};

export default IndianPlayers;
