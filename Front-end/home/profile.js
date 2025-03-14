document.addEventListener("DOMContentLoaded", () => {
  const employeeName = document.getElementById("employee-name");
  const employeeEmail = document.getElementById("employee-email");
  const employeeContact = document.getElementById("employee-contact");
  const employeeAddress = document.getElementById("employee-address");
  const employeeSalary = document.getElementById("employee-salary");
  const employeeImage = document.getElementById("employee-image");

  // Retrieve the ID from sessionStorage
  const id = sessionStorage.getItem("employeeId");
  if (!id) {
    alert("No employee ID found. Redirecting to login page...");
    location.href = "../login/login.html"; // Redirect if ID is missing
    return;
  }

  // Fetch employee data from the API
  fetch(`http://localhost:8080/find?id=${encodeURIComponent(id)}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch employee data");
      }
      return response.json();
    })
    .then((data) => {
      data = data.data;

      // Update the profile information with fetched data
      employeeEmail.textContent = data.email || "N/A";
      employeeName.textContent = data.name || "N/A";
      employeeContact.textContent = data.contact || "N/A";
      employeeAddress.textContent = data.address || "N/A";
      employeeSalary.textContent = data.sal || "N/A";
      employeeAddress.textContent = data.address || "N/A";
      // employeeImage.src = `http://localhost:8080/getimg?id=${id}`

      sessionStorage.clear();
      // Store the data in sessionStorage for later use
      sessionStorage.setItem("employeeSalary", data.sal || "N/A");
      sessionStorage.setItem("employeeAddress", data.address || "N/A");
      sessionStorage.setItem("employeeContact", data.contact || "N/A");
      sessionStorage.setItem("employeeEmail", data.email || "N/A");
      sessionStorage.setItem("employeeName", data.name || "N/A");
      sessionStorage.setItem("employeePassword", data.password || "N/A");
      sessionStorage.setItem("employeeId", data.eid || "N/A");
      // sessionStorage.setItem("employeeImage", employeeImage.src || "N/A");

      fetch(`http://localhost:8080/getimg?id=${id}`)
        .then((response) => {
          return response.blob();
        })
        .then((data) => {
          let url = URL.createObjectURL(data);
          employeeImage.setAttribute("src", url);
          sessionStorage.setItem("employeeImage",url)
        })
        .catch((error) => {
          console.log("Error fetching employee image:", error);
          employeeImage.src = "profile.jpg"; // Fallback to default image
        });
    })
    .catch((error) => {
      console.error("Error fetching employee data:", error);
      alert("Failed to load employee data. Please try again later.");
    });
});

// delete employee data from the API
document.getElementById("delete-employee").addEventListener("click", () => {
  const id = sessionStorage.getItem("employeeId");
  if (!id) {
    alert("No employee ID found. Cannot delete employee.");
    return;
  }

  if (confirm("Are you sure you want to delete this employee?")) {
    fetch(`http://localhost:8080/delete?id=${encodeURIComponent(id)}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to delete employee");
        }
        alert("Employee deleted successfully.");
        sessionStorage.clear();
        location.href = "../login/login.html"; // Redirect to login page after deletion
      })
      .catch((error) => {
        console.error("Error deleting employee:", error);
        alert("Failed to delete employee. Please try again later.");
      });
  }
});
