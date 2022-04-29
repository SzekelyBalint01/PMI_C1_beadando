//Handler function for the search form
async function deleteUser() {
  const resp = await fetch("http://localhost:8080/deletUser", {
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
  }
}

function createFormString(keys, values) {
  let returnValue = "";

  values.forEach((value, index) => {
    returnValue = returnValue.concat(keys[index] + "=");
    returnValue = returnValue.concat(value);
    if (index != values.length - 1) {
      returnValue = returnValue.concat("&");
    }
  });

  return returnValue;
}

//Creates string from the data retrieved from the search form
const createSearchFormData = () => {
  let keys = [];
  let values = [];

  keys.push("azon");
  values.push(document.getElementById("adoazon").textContent);

  return createFormString(keys, values);
};
