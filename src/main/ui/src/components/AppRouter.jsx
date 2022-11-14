import React from 'react'
import { useContext } from 'react';
import{Routes, Route} from 'react-router-dom';
import { authorizationRoutes, publicRoutes } from '../routes';
import { Context } from '../index';

const AppRouter = () => {
  const {user} = useContext(Context)

  console.log(user);

  return (
    <Routes>
        {user.isAuth && authorizationRoutes.map(({path, Element}) => 
          <Route key={path} path={path} element={<Element />}/>
        )}
        {publicRoutes.map(({path, Element}) => 
          <Route key={path} path={path} element={<Element />} />
        )}
    </Routes>
  );
};
export default AppRouter;