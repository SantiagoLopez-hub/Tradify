import React from 'react';
import ApiCall from "./Components/ApiCall";
import './App.css';

const App = () => {
    const [users, isLoading, error] = ApiCall('/users');

    console.log(error);

    if(error) {
        return (
            <div className="App">
                <header className="App-header">
                    {error}, Please try again later.
                </header>
            </div>
        )
    }

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
