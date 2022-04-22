import createFormString from "./createFormString.js";

//Retrieves the search form
const searchForm = document.getElementById("searchfrm");

//Sets the action to do when submitting the search form
searchForm.onsubmit = (e) => {
  e.preventDefault();
  btnSearch();
};

//Handler function for the search form
async function btnSearch() {
  const resp = await fetch("http://localhost:8080/user", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: createSearchFormData(),
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

    window.open("GUI_form.html", "_self");
  } else {
  }
}

//Creates string from the data retrieved from the search form
const createSearchFormData = () => {
  let keys = [];
  let values = [];

  keys.push("azon");
  values.push(document.getElementById("azon").value);

  return createFormString(keys, values);
};
