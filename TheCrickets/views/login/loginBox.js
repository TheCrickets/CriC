var mailRegex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);


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
    if (!mailRegex.test(this.value)) {
        toggleError("Invalid email address");
    }
    else {
        toggleError();
    }
}

function validation(email) {
    return mailRegex.test(email);
}

function submitForm() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if (validation(email)) {
        document.getElementsByTagName("form")[0].submit();
        alert(email + " i wont show the password tho..");
    }
    else
        alert("error");
}