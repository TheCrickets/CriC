function contact() {

    var firstName = document.getElementById("field1").value;
    var lastName = document.getElementById("field2").value;
    var email = document.getElementById("field3").value;
    var comment = document.getElementById("field4").value;

    document.getElementsByName("contactForm")[0].submit();

    var xhttp = new XMLHttpRequest();
    var url = "http://localhost:55557/contact/submit";

    var params = JSON.stringify({

            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "comment": comment

        });

    xhttp.open("POST", url, true);

    xhttp.send(params);

}