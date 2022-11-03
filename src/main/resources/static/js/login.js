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

var form = document.getElementById('main-form');
function handleForm(event) { event.preventDefault(); } 
form.addEventListener('submit', handleForm);

document.getElementById('login-button').addEventListener('click', async () => {
	console.log(123123);
	const login = document.getElementById('txt-input-login');
	const password = document.getElementById('password');
	
	const res = await fetch(`http://localhost:8082/users?user_login=${login.value}&password=${password.value}`);

	console.log(login, password);
	const user = await res.json();

  localstorage.setItem('userdata', user)

  console.log(user);

  //location.href = `http://localhost:8082/users?user_id=${user.id}`
  window.location.href=`http://localhost:8082/myProfile`;
  if (window.location.href.includes('myProfile')) {
    localstorage.getItem('userdata');
  }
	
	console.log(user);
	
	
})