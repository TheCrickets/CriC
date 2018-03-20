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

