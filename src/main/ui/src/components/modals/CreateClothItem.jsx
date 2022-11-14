import React, { useContext, useState } from 'react'
import { Button, Col, Dropdown, Form, Modal, Row } from 'react-bootstrap'
import { Context } from './../../index';

const CreateClothItem = ({show, onHide}) => {
  
  const {clothes} = useContext(Context)
  const[info, setInfo] = useState([])

  const addInfo = () => {
    setInfo([...info, {title:'', descr:'', number: Date.now()}])
  }
  const removeInfo = (number) =>{
    setInfo(info.filter(i => i.number !== number))
  }

  return (

    <Modal
    show = {show}
    onHide = {onHide}
    size="lg"
    centered
  >
    <Modal.Header closeButton>
      <Modal.Title id="contained-modal-title-vcenter">
        Додати новий одяг
      </Modal.Title>
    </Modal.Header>
    <Modal.Body>
      <Form >
        <div className="d-flex">
        <Dropdown>
          <Dropdown.Toggle>Виберіть вид одягу</Dropdown.Toggle>
          <Dropdown.Menu>
            {clothes.types.map(type => 
              <Dropdown.Item key = {type.id}> {type.name} </Dropdown.Item>)}
          </Dropdown.Menu>
        </Dropdown>
        <Dropdown className="mx-1">
          <Dropdown.Toggle>Виберіть розмір</Dropdown.Toggle>
          <Dropdown.Menu>
            {clothes.sizes.map(size => 
              <Dropdown.Item key = {size.id}> {size.name} </Dropdown.Item>)}
          </Dropdown.Menu>
        </Dropdown>
        <Form.Control className="mx-1" placeholder="Назва одягу" />
        <Form.Control className="mx-1" placeholder="Введіть ціну" type="number"/>
        <Form.Control className="mx-1" type="file" />
        </div>
        <hr/>
        <div>
        <Button variant={"outline-dark"} onClick={addInfo}>Додати опис</Button>
        {
          info.map(i => <Row>
            <Col md={4}>
              <Form.Control className='mt-2' placeholder={"Заголовок опису"}/>
            </Col>
            <Col md={4}>
              <Form.Control className='mt-2' placeholder={"Опис"}/>
            </Col>
            <Col>
              <Button className='mt-2' variant={"outline-danger"} onClick={() => removeInfo(i.number)}>Видалити</Button>
            </Col>
          </Row>)
        }
        </div>
      </Form>
    </Modal.Body>
    <Modal.Footer>
      <Button variant={"outline-danger"} onClick={onHide}>Закрити</Button>
      <Button variant={"outline-success"} onClick={onHide}>Додати</Button>
    </Modal.Footer>
  </Modal>
  )
}

export default CreateClothItem
