import createFormString from "./createFormString.js";

window.addEventListener("load", actualeDataUpload);

function actualeDataUpload() {
  document.getElementById("fname").value = localStorage.getItem("fename");
  document.getElementById("lname").value = localStorage.getItem("lename");
  document.getElementById("adoazon").innerHTML =
    localStorage.getItem("adoazon");
  document.getElementById("telephely").value =
    localStorage.getItem("telephely");
  document.getElementById("szervezet").value =
    localStorage.getItem("szervezet");
  document.getElementById("belepeskelte").value =
    localStorage.getItem("belepeskelte");
  document.getElementById("kilepeskelte").value =
    localStorage.getItem("kilepeskelte");
  document.getElementById("bruttoalapber").value =
    localStorage.getItem("bruttoalapber");
  document.getElementById("cegneve").value = localStorage.getItem("cegneve");
  document.getElementById("cegadoszama").value =
    localStorage.getItem("cegadoszama");
  document.getElementById("cegszekhelye").value =
    localStorage.getItem("cegszekhelye");
  document.getElementById("ledolgnap").value =
    localStorage.getItem("ledolgnap");
  document.getElementById("munkaszunnap").value =
    localStorage.getItem("munkaszunnap");
  document.getElementById("torzsber").value = localStorage.getItem("torzsber");
}

//Retrieves the userForm form
const userForm = document.getElementById("userForm");

//Sets the action to do when submitting the userForm form
userForm.onsubmit = (f) => {
  f.preventDefault();
  btnSave();
};

//Handler function for the userForm form
async function btnSave() {
  const resp = await fetch("http://localhost:8080/userMod", {
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

  fetch("http://localhost:8080/userMod", {
    method: "GET",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: createUserFormData(),
  })
    .catch(() => console.log("Rejected"))
    .then((response) => {
      return response.json();
    });

  if (resp.adoazon != null) {
    localStorage.setItem("adoazon", resp.adoazon);
    localStorage.setItem("lename", resp.lname);
    localStorage.setItem("fename", resp.fname);
    localStorage.setItem("szervezet", resp.szervezet);
    localStorage.setItem("belepeskelte", resp.belepeskelte);
    localStorage.setItem("kilepeskelte", resp.kilepeskelte);
    localStorage.setItem("bruttoalapber", resp.bruttoalapber);
    localStorage.setItem("cegneve", resp.cegneve);
    localStorage.setItem("cegadoszama", resp.cegadoszama);
    localStorage.setItem("cegszekhelye", resp.cegszekhelye);
    localStorage.setItem("ledolgnap", resp.ledolgnap);
    localStorage.setItem("munkaszunnap", resp.munkaszunnap);
    localStorage.setItem("torzsber", resp.torzsber);
    localStorage.setItem("telephely", resp.telephely);
    console.log("mentve!");
    $(".toast").toast("show");
  }
}

//Creates string from the data retrieved from the userForm form
const createUserFormData = () => {
  let keys = [];
  let values = [];

  keys.push("azon");
  values.push(localStorage.getItem("adoazon"));
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
