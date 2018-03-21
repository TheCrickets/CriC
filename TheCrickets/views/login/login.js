var mailRegex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);


function onLoad() {
    document.getElementById("emailLogin").addEventListener('change', checkMail);
    document.getElementById("emailRegister").addEventListener('change', checkMail);
}

function togglePassLogin() {
    var passwordInput = document.getElementById("passwordLogin");
    if (passwordInput.getAttribute("type") == "password")
        passwordInput.setAttribute("type", "text");
    else passwordInput.setAttribute("type", "password");
}

function togglePassRegister() {
    var passwordInput = document.getElementById("passwordRegister");
    if (passwordInput.getAttribute("type") == "password")
        passwordInput.setAttribute("type", "text");
    else passwordInput.setAttribute("type", "password");
}

function togglePassAgain() {
    var passwordInput = document.getElementById("passwordAgain");
    if (passwordInput.getAttribute("type") == "password")
        passwordInput.setAttribute("type", "text");
    else passwordInput.setAttribute("type", "password");
}

function toggleError(error) {
    var errorElement = document.getElementById("errorLogin");

    if (error == undefined) {
        errorElement.style.display = "none";
        errorElement.innerHTML = "";
    } else {
        errorElement.style.display = "block";
        errorElement.innerHTML = error;
    }

    errorElement = document.getElementById("errorRegister");

    if (error == undefined) {
        errorElement.style.display = "none";
        errorElement.innerHTML = "";
    } else {
        errorElement.style.display = "block";
        errorElement.innerHTML = error;
    }
}

function checkMail() {
    if (!mailRegex.test(this.value)) {
        toggleError("Invalid email address");
    } else {
        toggleError();
    }
}

function validation(email) {
    return mailRegex.test(email) && checkPassword() && checkTerms();
}

function login() {
    var email = document.getElementById("emailLogin").value;
    var password = document.getElementById("passwordLogin").value;

    if (mailRegex.test(email)) {
        document.getElementsByName("loginForm")[0].submit();
        alert(email + " i wont show the password tho..");
    } else
        alert("error");
}

function checkPassword() {
    var password = document.getElementById("passwordRegister").value;
    var passwordAgain = document.getElementById("passwordAgain").value;

    if (password != passwordAgain) {
        toggleError("Passwords do not match.");
        return false;
    } else {
        toggleError();
        return true;
    }
}

function checkTerms() {
    if (!document.getElementById("acceptBox").checked) {
        toggleError("You must accept the terms and conditions");
        return false;
    }
    toggleError();
    return true;
}

function register() {
    var email = document.getElementById("emailRegister").value;
    var password = document.getElementById("passwordRegister").value;
    if (validation(email)) {
        document.getElementsByTagName("form")[0].submit();
        alert(email + " registered");
    } else
        alert("error");
}