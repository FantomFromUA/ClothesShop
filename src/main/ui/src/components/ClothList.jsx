import React, { useContext } from 'react'
import { observer } from 'mobx-react-lite';
import { Context } from './../index';
import { Row } from 'react-bootstrap';
import ClothItem from './ClothItem';

const ClothList = observer(() => {
  
  const {clothes} = useContext(Context);

  return (
    <Row className='d-flex'>
     {clothes.clothes.map(cloth =>
      <ClothItem key={cloth.id} cloth={cloth}  />
      )}
    </Row>
  )
})

export default ClothList;
