import React, { Component } from 'react';
import './CountPeople.css';

class CountPeople extends Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  updateEntry = () => {
    this.setState((prevState) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }

  updateExit = () => {
    this.setState((prevState) => {
      if (prevState.exitcount < prevState.entrycount) {
        return { exitcount: prevState.exitcount + 1 };
      }
      return prevState;
    });
  }

  render() {
    return (
      <div className="count-people-container">
        <div className="counter-section">
          <button className="login-btn" onClick={this.updateEntry}>
            Login
          </button>
          <span className="counter-text">
            {this.state.entrycount} People Entered!!!
          </span>
        </div>
        <div className="counter-section">
          <button className="exit-btn" onClick={this.updateExit}>
            Exit
          </button>
          <span className="counter-text">
            {this.state.exitcount} People Left!!!
          </span>
        </div>
      </div>
    );
  }
}

export default CountPeople;
