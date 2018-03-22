var regexNameSurname = /^[a-zA-Z ]{2,30}$/;
var regexPhoneNumber = /^[0-9]{10}$/;
function checkName()
{
    var auxiliary = document.getElementById("NameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid name.");
        return false;
    }
    else
    {
        toggleError();
        return true;
    }
}

function checkSurname()
{
    var auxiliary = document.getElementById("SurnameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid surname.");
        return false;
    }
    else
    {
        toggleError();
        return true;
    }
}

function checkBirthDate()
{
    if(new Date(document.getElementById('DateOfBirthContainer').value) > new Date())
    {
        toggleError("Your birthday is in the future.");
        return false;
    }
    else
    {
        toggleError();
        return true;
    }
}

function checkPhoneNumber()
{
    var auxiliary = document.getElementById("PhoneNumberContainer");
    if(!regexPhoneNumber.test(auxiliary.value))
    {
        toggleError("Invalid phone number.");
        return false;
    }
    else
    {
        toggleError();
        return true;
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
    if(checkName() && checkSurname() && checkBirthDate() && checkPhoneNumber())
    {
        //document.getElementsByTagName("form")[0].submit();
        alert("Your data have been saved.");
    }
    else
    {
        alert("error");
    }
}
