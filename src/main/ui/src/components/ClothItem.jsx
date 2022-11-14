import React from 'react'
import { Button, Card, Col, Image } from 'react-bootstrap';
import { useNavigate } from "react-router-dom"
import { CLOTHESPAGE_ROUTE } from './../utils/consts';
import {BsFillCartFill} from "react-icons/bs";

const ClothItem = ({cloth}) => {
  const navigate = useNavigate()
  return (
    <Col md={3} className='mt-3 mx-2' style={{width:200}}>
      <Card style={{width: 150}} border={"light"}>
        <Image width={150} height={150} src={cloth.img} style={{ cursor: 'pointer'}} onClick={() => navigate(CLOTHESPAGE_ROUTE +'/' + cloth.id)} />
        <div className='d-flex text-black-50  align-items-center justify-content-between'>
          Guess
          <div>{cloth.price}	&#8372;</div>
         </div>
        <div>
          <h6>{cloth.name}</h6>
          <Button variant={"outline-dark"} size={"sm"}><BsFillCartFill/></Button>
        </div>
      </Card>
    </Col>
  )
}

export default ClothItem;
