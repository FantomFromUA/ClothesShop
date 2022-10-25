function show() {
  var p = document.getElementById("password");
  p.setAttribute("type", "text");
}

function hide() {
  var p = document.getElementById("password");
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


async function getAllUsers(){
	const res = await fetch('http://localhost:8082/users');
	const users = await res.json();

	console.log(users);
	console.log("Done");
}


document.getElementById('sign-up-button').addEventListener('click', async () => {
	const name = document.getElementById('txt-input-name');
	const surname = document.getElementById('txt-input-surname');
	const password = document.getElementById('password');
	const login = document.getElementById('login');
	console.log(name.value, surname.value, login.value, password.value, "asdfasdf\n");
	console.log(JSON.stringify({
		  name: name.value,
		  surname: surname.value,
		  login: login.value,
		  password: password.value
		}));
	
	const res = await fetch('http://localhost:8082/users', {
		method: 'POST',
		headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
		body: JSON.stringify({
		  name: name.value,
		  surname: surname.value,
		  login: login.value,
		  password: password.value
		})
	});
	
	const user = await res.json();
	
	
	
	name.value = '';
	surname.value = '';
	login.value = '';
	password.value = '';
	
	console.log(user);
})
window.addEventListener('DOMContentLoaded', getAllUsers);

