import React from 'react';
import ApiCall from "./Components/ApiCall";
import './App.css';

const App = () => {
    const [users, isLoading] = ApiCall('/users');

    return (
        <div className="App">
            <header className="App-header">
                Learn React
                <ul>
                    {isLoading ? <p>Loading...</p> : users.map((user, i) => <li key={i}>{user.email}</li>)}
                </ul>
            </header>
        </div>
    );
}

export default App;
