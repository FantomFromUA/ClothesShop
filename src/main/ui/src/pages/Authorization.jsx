import React, { useState } from 'react'
import { Container,Form } from 'react-bootstrap';
import  Card  from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import { LOGIN_ROUTE, REGISTRATION_ROUTE } from './../utils/consts';
import { NavLink, useLocation } from 'react-router-dom';
import styles from '../components/Styles/Authorization.module.css'
import { registration, login } from './../http/UserAPI';

const Authorization = () => {

  const location = useLocation()
  const isLogin = location.pathname === LOGIN_ROUTE;
  console.log(location);

  const[email, setEmail] = useState()
  const[password, setPassword] = useState()

  const click = async() =>{
    if(isLogin){
      const response = await login()
    } else{
      const response = await registration(email, password);
      console.log(response)
    }
  }

  return (
    <Container className="d-flex justify-content-center align-items-center"
              style={{height:window.innerHeight - 54}}>
        <Card  className="p-2 auth" style={{width:500}}>
          <h2 className="m-auto">{ isLogin?  "Авторизація" : "Реєстрація"}</h2>
            <Form className="d-flex flex-column">
                <Form.Control 
                  className="mt-2"
                  placeholder="Введіть ваш @mail"
                  value={email}
                  onChange={e => setEmail(e.target.value)}
                />

                <Form.Control
                  className="mt-2"
                  type="password"
                  placeholder="Введіть ваш пароль"
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                />

               <div className="d-flex justify-content-between align-items-center mt-2 pl-3 pr-3">
                {
                  isLogin?
                  <NavLink className={styles.auth} to={REGISTRATION_ROUTE}>Зареєструватися</NavLink>
                  :
                  <NavLink className={styles.auth} to={LOGIN_ROUTE}>Є аккаунт? Вхід</NavLink>
                  }
                
                <Button variant="outline-success" onClick={click} >
                      { isLogin? "Увійти" : "Зареєструватися"}
                    </Button>
                </div>
            </Form>
        </Card>
    </Container>
  )
}

export default Authorization;
