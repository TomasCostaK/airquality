import React from 'react';
import './App.css';
import {Route, BrowserRouter as Router} from 'react-router-dom';
import Dashboard from "./Dashboard";

function App() {
  return (
    <Router>
      <Route exact path="/" component={Dashboard}></Route>
    </Router>
  );
}

export default App;
