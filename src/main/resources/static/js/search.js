document.getElementById('searchplace').oninput = async function(){
  
    let val = this.value.trim();
    const getAllItem = await fetch(`http://localhost:8082/products`);

    const getAllitem = getAllItem.json();

    console.log(getAllitem);

    /*if(val != ''){
      getAllitem.json.name.forEach(function(elem){
        if(elem.innerText.search(val) == 1){
          elem.classList.add('hide');
        }
        else{
          elem.classList.remove('hide');
        }
      });
    }
    else{
      getAllitem.json.name.forEach(function(elem){
        elem.classList.remove('hide');
      });
    }*/

    document.querySelector('.search-button').addEventListener('click', async => {
      location.href = `http://localhost:8082/products?product_name=${val}`;
    })
};