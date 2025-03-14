document.getElementById("loginForm").addEventListener("submit", (event) => {
  event.preventDefault();

  const email = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  const employee = {
    email: email,
    password: password,
  };

  console.log(employee);
  
  // Construct the URL with query parameters

  // Construct the URL with encoded email and password
  // const url = `http://localhost:8080/login?email=${encodeURIComponent(
  //   email
  // )}&password=${encodeURIComponent(password)}`;

  fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(employee),
  })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((data) => {
          if (data.data == "email not found") {
            alert("Email is not found");
          } else {
            alert("password is not found");
          }
          throw data;
        });
      }
      return response.json();
    })
    .then((data) => {
      console.log(data.data.eid);
      // alert("login sucessfull")
      sessionStorage.clear();
      sessionStorage.setItem("employeeId", data.data.eid);
      sessionStorage.setItem("employeeName", data.data.name);
      // window.location.href = "../home/home.html"
      open("../home/home.html", "_self");
    })
    .catch((error) => {
      console.log("error");

      console.log(error);
    });
});
