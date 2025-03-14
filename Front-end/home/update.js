const employeeId = sessionStorage.getItem("employeeId") || "N/A";
const employeeSalary = sessionStorage.getItem("employeeSalary") || "N/A";
const employeeAddress = sessionStorage.getItem("employeeAddress") || "N/A";
const employeeContact = sessionStorage.getItem("employeeContact") || "N/A";
const employeeEmail = sessionStorage.getItem("employeeEmail") || "N/A";
const employeeName = sessionStorage.getItem("employeeName") || "N/A";
const employeePassword = sessionStorage.getItem("employeePassword") || "N/A";

document.getElementById("id").value = employeeId;
document.getElementById("sal").value = employeeSalary;
document.getElementById("address").value = employeeAddress;
document.getElementById("contact").value = employeeContact;
document.getElementById("email").value = employeeEmail;
document.getElementById("name").value = employeeName;
document.getElementById("password").value = employeePassword;

const form = document.getElementById("form");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const employee = {
    eid: document.getElementById("id").value,
    name: document.getElementById("name").value,
    sal: document.getElementById("sal").value,
    address: document.getElementById("address").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    contact: document.getElementById("contact").value
  };
  console.log(employee);

  sessionStorage.setItem("employeeId", employeeId);
  sessionStorage.setItem("employeeAddress", employeeAddress);
  sessionStorage.setItem("employeeContact", employeeContact);
  sessionStorage.setItem("employeeEmail", employeeEmail);
  sessionStorage.setItem("employeeName", employeeName);
  sessionStorage.setItem("employeePassword", employeePassword);
  sessionStorage.setItem("employeeName", employeeName);

  const url = "http://localhost:8080/update";
  fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(employee),
  })
    .then((response) => {
      if (!response.ok) {
        throw new error(response.statusText);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Success:", data);
      window.location.href = "./home.html";
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});
