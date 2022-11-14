import { observer } from 'mobx-react-lite'
import React, { useContext } from 'react'
import { ListGroup} from 'react-bootstrap';
import { Context } from './../index';

const SideBar = observer(() => {

  const {clothes} = useContext(Context);

  return (
     <ListGroup className="mt-2">
      {clothes.types.map(type =>
        <ListGroup.Item
        style ={{cursor: 'pointer'}}
        active={type.id === clothes.selectedType.id}  
        onClick={() => clothes.setSelectedType(type)}
        action variant="light"
        key={type.id}>
          {type.name}
        </ListGroup.Item>
        )}
    </ListGroup>
    
  )
});

export default SideBar
