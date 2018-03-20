var regexNameSurname = /^[a-zA-Z ]{2,30}$/;
function checkName()
{
    var auxiliary = document.getElementById("NameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid name.");
    }
    else
    {
        toggleError();
    }
}

function checkSurname()
{
    var auxiliary = document.getElementById("SurnameContainer");
    if(!regexNameSurname.test(auxiliary.value))
    {
        toggleError("Invalid surname.");
    }
    else
    {
        toggleError();
    }
}

function checkBirthDate()
{
    if(new Date(document.getElementById('DateOfBirthContainer').value) > new Date())
    {
        toggleError("Your birthday is in the future.");
    }
    else
    {
        toggleError();
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
    if(checkName(name) && checkSurname(surname) && checkBirthDate(birthday))
    {
        //document.getElementsByTagName("form")[0].submit();
        alert("Your data have been saved.");
    }
    else
    {
        alert("error");
    }
}
