import React from 'react'
import { Col, Container } from 'react-bootstrap';
import ClothList from '../components/ClothList';
import SideBar from '../components/SideBar';
import SizeBar from './../components/SizeBar';

const Main = () => {
  return (
    <Container className='d-flex' >
      <Col md={3}>
        <SideBar />
      </Col>

      <Col md={9}>
        <SizeBar />
        <ClothList/>
      </Col>
    </Container>
  )
}

export default Main;