import React, {Component} from 'react';
import './App.css';
import IbanForm from './components/Content';

class App extends Component {
  render() {
    return (
      <div>
        <header className="App-header">
          <h1>IBAN CHECK APP</h1>
          <h3>Check if your IBAN is valid and which bank owns this IBAN number!</h3>
        </header>
        <IbanForm />
      </div>
    );
  }
}

export default App;
