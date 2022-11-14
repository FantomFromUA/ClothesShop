import React, { useState } from 'react'
import { Button, Container } from 'react-bootstrap';
import CreateClothItem from '../components/modals/CreateClothItem';
import CreateClothType from '../components/modals/CreateClothType';

const Admin = () => {
  const [clothItemVisible, setClothItemVisible] = useState(false);
  const [typeVisible, setTypeVisible] = useState(false);
  return (
    <Container className="d-flex flex-column">
      <Button variant={"outline-dark"} className="mt-2" onClick={()=> setClothItemVisible(true)}>Додати річ</Button>
      <Button variant={"outline-dark"} className="mt-2" onClick={()=> setTypeVisible(true)}>Додати вид</Button>

      <CreateClothType show={typeVisible} onHide={() => setTypeVisible(false)} />
      <CreateClothItem show={clothItemVisible} onHide={() => setClothItemVisible(false)}/>
    </Container>
  )
}

export default Admin;