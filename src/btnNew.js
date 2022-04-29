const ujgomb = document.getElementById("uj");

ujgomb.addEventListener("click", btnNew);

function btnNew(event) {
  event.preventDefault();
  localStorage.clear();

  window.open("GUI_regist.html", "_self");
}
