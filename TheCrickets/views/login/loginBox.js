function togglePass() {
    var passwordInput = document.getElementById("password");
    if (passwordInput.getAttribute("type") == "password")
        passwordInput.setAttribute("type", "text");
    else passwordInput.setAttribute("type", "password");
}