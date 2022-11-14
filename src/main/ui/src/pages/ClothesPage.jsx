import React from 'react'
import { Button, Card, Col, Container, Image, Row } from 'react-bootstrap';

const ClothesPage = () => {
  
  const cloth =  {id:1, name:"Футболка чорна", price: 400, img:"https://media2.symbol.ua/aio-images/9c/a4/9ca45ef689db2c967f5cc4f5f3bcfeaa/b718eb6d-b8d4-427b-ac67-48d80ab1a0e7.jpg"};

  const description = [
    {id:1, title:'Бренд', descr:'Guess'},
    {id:2, title:'Колір', descr:'Чорний'},
    {id:3, title:'Матеріал', descr:'Хлопок'},
    {id:4, title:'Сезон', descr:'Весна/Літо'},
  ]
  return (
    <Container className='mt-3 d-flex'>
      <Col md={6}>
        <Image width={300} height={300} src={cloth.img} />
      </Col>
      <Col md={6}>
        <Card  className='d-flex  flex-column align-items-center justify-content-around p-3' style={{width:300, heigh:300, fontSize:32, border:'2px solid'}}>
          <h2>{cloth.name}</h2>
          <h3>{cloth.price} &#8372;</h3>

          <Row className="m-3" style={{fontSize: 16}}>
            {description.map(info =>
              <Row key={info.id}>
                {info.title}: {info.descr}
              </Row>
              )}
          </Row>

          <Button variant={"outline-dark"}>Додати в кошик</Button>
        </Card>
      </Col>
    </Container>
  )
}

export default ClothesPage;