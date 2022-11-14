import React from 'react'
import { Button, Form, Modal } from 'react-bootstrap'

const CreateClothType = ({show, onHide}) => {
  return (
    <Modal
    show = {show}
    onHide = {onHide}
    size="lg"
    centered
  >
    <Modal.Header closeButton>
      <Modal.Title id="contained-modal-title-vcenter">
        Додати новий вид одягу
      </Modal.Title>
    </Modal.Header>
    <Modal.Body>
      <Form>
        <Form.Control placeholder={"Введіть назву виду"} />
      </Form>
    </Modal.Body>
    <Modal.Footer>
      <Button variant={"outline-danger"} onClick={onHide}>Закрити</Button>
      <Button variant={"outline-success"} onClick={onHide}>Додати</Button>
    </Modal.Footer>
  </Modal>
  )
}

export default CreateClothType
