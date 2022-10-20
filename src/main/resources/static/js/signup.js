function show() {
  var p = document.getElementById("pwd");
  p.setAttribute("type", "text");
}

function hide() {
  var p = document.getElementById("pwd");
  p.setAttribute("type", "password");
}

var pwShown = 0;

document.getElementById("eye").addEventListener(
  "click",
  function () {
    if (pwShown == 0) {
      pwShown = 1;
      show();
    } else {
      pwShown = 0;
      hide();
    }
  },
  false
);


function push(name, surname, login ,pass){
	const url = 'http://localhost:8082/users';
	const data = {
			  "name": name,
			  "surname": surname,
			  "login": login,
			  "password": pass,
			  "coins": 0,
			  "adminAccess": false
			};
	
	try {
	  const response = await fetch(url, {
	    method: 'POST', // или 'PUT'
	    body: JSON.stringify(data), // данные могут быть 'строкой' или {объектом}!
	    headers: {
	      'Content-Type': 'application/json'
	    }
	  });
	  const json = await response.json();
	  console.log('Успех:', JSON.stringify(json));
	} catch (error) {
	  console.error('Ошибка:', error);
	}
}

function sign_up(){
    nam = document.getElementById("txt-input-name").value;
    surname =  document.getElementById("txt-input-surname").value;
    pass =  document.getElementById("pass").value;
    login =  document.getElementById("email").value;
    
    /*let xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8082/users");
	
	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("Content-Type", "application/json");
	
	xhr.onload = () => console.log(xhr.responseText);
	
	let data = `{
			  "name": name,
			  "surname": surname,
			  "login": login,
			  "password": pass,
			  "coins": 0,
			  "adminAccess": false
			}`;
	
	xhr.send(data);*/
    
    push(nam, surname, login ,pass)
}
