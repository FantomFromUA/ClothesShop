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
    $.ajax({
		    type: "POST",
		    url: "http://localhost:8082/users",
		    cahce: false,
		    contentType:"application/json; charset=UTF-8; application/x-www-form-urlencoded",
		    dataType: "json",
		    data: {
			  "name": name,
			  "surname": surname,
			  "login": login,
			  "password": pass,
			  "coins": 0,
			  "adminAccess": false
			},
		    success: function(result){
		        console.log(result)
		    }
		}
	);
}

function sign_up(){
    nam = document.getElementById("txt-input-name").value;
    surname =  document.getElementById("txt-input-surname").value;
    pass =  document.getElementById("pass").value;
    login =  document.getElementById("email").value;
    push(nam, surname, login ,pass)
}
