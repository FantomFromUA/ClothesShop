import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { NavLink } from 'react-router-dom';
import { LOGIN_ROUTE, MAIN_ROUTE, ADMIN_ROUTE } from '../utils/consts';
import styles from './Styles/NavBar.module.css'
import { Context } from '../index';
import { useContext } from 'react';
import {observer} from 'mobx-react-lite';
import { useNavigate } from "react-router-dom"

const NavBar = observer( () => {

  const {user} = useContext(Context)
  console.log({user});

  const navigate = useNavigate()
  return (
    <Navbar bg="primary" variant="dark">
        <Container>
          <NavLink className={styles.title} to={MAIN_ROUTE}>BuyMe</NavLink>
            {user.isAuth ?
            <Nav className="ml-auto">
            <button className={styles.buttons} onClick={() => navigate(LOGIN_ROUTE)}>Вийти</button>
            <button className={styles.buttons} onClick={() => navigate(ADMIN_ROUTE)}>Адмін панель</button>
            </Nav>
             : 
             <Nav className="ml-auto">
              <button className={styles.buttons} onClick={() => user.setIsAuth(true)} >Авторизація</button>
             </Nav>
          }
        </Container>
      </Navbar>
  )
})

export default NavBar;