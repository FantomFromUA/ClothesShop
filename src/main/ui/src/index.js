import React, { createContext } from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import UserStore from './store/UserStore';
import ClothesStore from './store/ClothesStore';

export const Context = createContext(null)

console.log(process.env.REACT_APP_API_URL);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

  <Context.Provider value={{
    user: new UserStore(),
    clothes: new ClothesStore(),
  }}>
      <App />
  </Context.Provider>
  
);


