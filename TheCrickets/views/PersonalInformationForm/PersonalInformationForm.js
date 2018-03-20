var regexNameSurname = /^[a-zA-Z ]{2,30}$/;
var regexPhoneNumber = /^[0-9]{10}$/;
function checkName()
{
    var auxiliary = document.getElementById("NameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid name.");
        return 0;
    }
    else
    {
        toggleError();
        return 1;
    }
}

function checkSurname()
{
    var auxiliary = document.getElementById("SurnameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid surname.");
        return 0;
    }
    else
    {
        toggleError();
        return 1;
    }
}

function checkBirthDate()
{
    if(new Date(document.getElementById('DateOfBirthContainer').value) > new Date())
    {
        toggleError("Your birthday is in the future.");
        return 0;
    }
    else
    {
        toggleError();
        return 1;
    }
}

function checkPhoneNumber()
{
    var auxiliary = document.getElementById("PhoneNumberContainer");
    if(!regexPhoneNumber.test(auxiliary.value))
    {
        toggleError("Invalid phone number.");
        return 0;
    }
    else
    {
        toggleError();
        return 1;
    }
}

function toggleError(error) 
{
    var errorElement = document.getElementById("error");
    if (error == undefined) 
    {
        errorElement.style.display = "none";
        errorElement.innerHTML = "";
    }
    else 
    {
        errorElement.style.display = "block";
        errorElement.innerHTML = error;
    }
}

function saveChanges()
{
    console.log("Hello");
    var name = document.getElementById("NameContainer").value;
    var surname = document.getElementById("SurnameContainer").value;
    var birthday = document.getElementById("DateOfBirthContainer").value;
    var phoneNumber = document.getElementById("PhoneNumberContainer");
    if(checkName(name) && checkSurname(surname) && checkBirthDate(birthday) && checkPhoneNumber(phoneNumber))
    {
        //document.getElementsByTagName("form")[0].submit();
        alert("Your data have been saved.");
    }
    else
    {
        alert("error");
    }
}
