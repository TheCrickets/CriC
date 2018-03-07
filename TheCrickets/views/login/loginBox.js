function onLoad() {
    document.getElementById("email").addEventListener('change', checkMail);
}

function togglePass() {
    var passwordInput = document.getElementById("password");
    if (passwordInput.getAttribute("type") == "password")
        passwordInput.setAttribute("type", "text");
    else passwordInput.setAttribute("type", "password");
}

function toggleError(error) {
    var errorElement = document.getElementById("error");
    if (errorElement.style.display == "block" || error == undefined)
        errorElement.style.display = "none";
    else {
        errorElement.style.display = "block";
        errorElement.innerHTML = error;
    }
}

function checkMail() {
    var regex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

    if (!regex.test(this.value))
        toggleError("Invalid email address");
    else
        toggleError();
}