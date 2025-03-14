const form = document.getElementById("form");
const pwdError = document.getElementById("pwd-error");

const password = document.getElementById("password");
password.addEventListener("keyup", () => {
  const password = document.getElementById("password").value;

  let upper = /[A-Z]/.test(password);
  let lower = /[a-z]/.test(password);
  let number = /[0-9]/.test(password);
  let special = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  let minLength = password.length >= 8;

  if (!upper) {
    pwdError.innerText = "Password must contain Uppercase Letter";
  } else if (!lower) {
    pwdError.innerText = "Password must contain LowerCase Letter";
  } else if (!number) {
    pwdError.innerText = "Password must contain Number ";
  } else if (!special) {
    pwdError.innerText = "Password must contain special Letter";
  } else if (!minLength) {
    pwdError.innerText = "Password must contain 8 Charactes";
  } else {
    pwdError.style.color = "green";
    pwdError.innerText = "Password is STRONG";
  }

  function validate(password) {
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,}$/;
  console.log(passwordRegex.test(password))
} validate(password)
});

form.addEventListener("submit", (e) => {
  // e.preventDefault();
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const password1 = document.getElementById("password1").value;
  const name = document.getElementById("name").value;
  const sal = document.getElementById("sal").value;
  const contact = document.getElementById("contact").value;
  const address = document.getElementById("address").value;

  if(password != password1){
    alert("password is not matching")
  }else{

  let employee = {
    name: name,
    sal: sal,
    email: email,
    password: password,
    contact: contact,
    address: address,
  };

  const url = "http://localhost:8080/register";
  fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(employee),
  })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((data) => {
          if (data.data == "User already exist") {
            alert("User already exist");
          }
          throw data;
        });
      }
      return response.json();
    })
    .then((data) => {
      console.log("Success:", data);
      window.location.replace("../login/login.html");
    })
    .catch((error) => {
      console.error("Error:", error);
    });
  }
});



