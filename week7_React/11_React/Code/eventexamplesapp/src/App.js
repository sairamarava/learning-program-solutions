import React, { useState } from 'react';
import './App.css';

function App() {

  const [counter, setCounter] = useState(0);
  
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('');
  const [convertedAmount, setConvertedAmount] = useState('');

  const incrementCounter = () => {
    setCounter(counter + 1);
  };

  const sayHello = () => {
    alert('Hello Member!');
  };


  const handleIncrement = () => {
    incrementCounter();
    sayHello();
  };


  const decrementCounter = () => {
    setCounter(counter - 1);
  };


  const sayWelcome = (message) => {
    alert(`localhost:3000 says\n${message}`);
  };

  const handleOnPress = (e) => {
    alert('localhost:3000 says\nI was clicked');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (amount && currency.toLowerCase() === 'euro') {
      // Conversion rate: 1 Euro = 88 INR (approximate)
      const euroAmount = (parseFloat(amount) / 88).toFixed(2);
      setConvertedAmount(`Converting to Euro Amount is ${euroAmount}`);
      alert(`localhost:3000 says\nConverting to Euro Amount is ${euroAmount}`);
    } else {
      alert('Please enter a valid amount and currency (Euro)');
    }
  };

  return (
    <div className="App">
      <h1>{counter}</h1>
      
      {/* Event handling buttons */}
      <div>
        <button onClick={handleIncrement}>Increment</button>
        <button onClick={decrementCounter}>Decrement</button>
        <button onClick={() => sayWelcome('welcome')}>Say welcome</button>
        <button onClick={handleOnPress}>Click on me</button>
      </div>

      {/* Currency Converter Component */}
      <div style={{ marginTop: '50px' }}>
        <h2 style={{ color: 'green' }}>Currency Convertor!!!</h2>
        
        <form onSubmit={handleSubmit}>
          <div style={{ margin: '10px 0' }}>
            <label>Amount: </label>
            <input
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              placeholder="Enter amount in INR"
            />
          </div>
          
          <div style={{ margin: '10px 0' }}>
            <label>Currency: </label>
            <input
              type="text"
              value={currency}
              onChange={(e) => setCurrency(e.target.value)}
              placeholder="Enter currency (Euro)"
            />
          </div>
          
          <button type="submit">Submit</button>
        </form>

        {convertedAmount && (
          <div style={{ marginTop: '20px', color: 'blue' }}>
            {convertedAmount}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;