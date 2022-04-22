import createFormString from "./createFormString.js";

//Retrieves the userForm form
const userForm = document.getElementById("newUserForm");

//Sets the action to do when submitting the userForm form
userForm.onsubmit = (g) => {
  g.preventDefault();
  btnSaveNew();
};

//Handler function for the userForm form
async function btnSaveNew() {
  const resp = await fetch("http://localhost:8080/newUser", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: createUserFormData(),
  })
    .catch(() => console.log("Rejected"))
    .then((response) => {
      return response.json();
    });
  if (resp.saved) {
    console.log("siker!!");
    window.open("GUI_Search.html", "_self");
  }
}

//Creates string from the data retrieved from the userForm form
const createUserFormData = () => {
  let keys = [];
  let values = [];

  keys.push("azon");
  values.push(document.getElementById("adoazon").value);
  keys.push("fname");
  values.push(document.getElementById("fname").value);
  keys.push("lname");
  values.push(document.getElementById("lname").value);
  keys.push("telephely");
  values.push(document.getElementById("telephely").value);
  keys.push("szervezet");
  values.push(document.getElementById("szervezet").value);
  keys.push("belepeskelte");
  values.push(document.getElementById("belepeskelte").value);
  keys.push("kilepeskelte");
  values.push(document.getElementById("kilepeskelte").value);
  keys.push("bruttoalapber");
  values.push(document.getElementById("bruttoalapber").value);
  keys.push("cegneve");
  values.push(document.getElementById("cegneve").value);
  keys.push("cegadoszama");
  values.push(document.getElementById("cegadoszama").value);
  keys.push("cegszekhelye");
  values.push(document.getElementById("cegszekhelye").value);
  keys.push("ledolgnap");
  values.push(document.getElementById("ledolgnap").value);
  keys.push("munkaszunnap");
  values.push(document.getElementById("munkaszunnap").value);
  keys.push("torzsber");
  values.push(document.getElementById("torzsber").value);

  return createFormString(keys, values);
};
