import React, { useContext } from 'react'
import { observer } from 'mobx-react-lite';
import { Context } from './../index';
import { ListGroup } from 'react-bootstrap';

const SizeBar =  observer(() => {

  const {clothes} = useContext(Context);

  return (

    <ListGroup horizontal className='m-2'>
      {clothes.sizes.map(size =>
        <ListGroup.Item
        style ={{cursor: 'pointer'}}
        active={size.id === clothes.selectedSize.id}  
        onClick={() => clothes.setSelectedSize(size)}
        action variant="light"
        key={size.id}>
          {size.name}
        </ListGroup.Item>
        )}
    </ListGroup>

    // <Form className='d-flex'>
    //     {clothes.sizes.map(size => 
    //       <Card className='p-3 m-2'
    //       style={{cursor:'pointer'}}
    //       key={size.id}
    //       onClick={()=>clothes.setSelectedSize(size)}
    //       text={size.id === clothes.selectedSize.id ? 'danger' : 'light'}
          
          
    //       >{size.name}</Card>
    //       )}

    // </Form>  
  )
});

export default SizeBar
