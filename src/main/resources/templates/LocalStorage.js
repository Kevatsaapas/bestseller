(function () {
    if(window.localStorage)
        console.log("Local Storage supported")
    else
        console.log("Local Storage not supported")

})();

// document.getElementById(kilpailijaId).innerHTML = localStorage.setItem(kilpailijaId, kilpailija);