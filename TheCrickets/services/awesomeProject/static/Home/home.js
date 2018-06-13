function profile() {
	stringBuilder = ("http://localhost:55557/home/").concat(localStorage.getItem("sessionID")).concat("/").concat(localStorage.getItem("email")).concat("/personalPage");
   	window.location = stringBuilder;
}

function settings() {
	stringBuilder = ("http://localhost:55557/profile/").concat(localStorage.getItem("sessionID")).concat("/").concat(localStorage.getItem("email"));
   	window.location = stringBuilder;
}