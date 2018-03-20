var regexName = /^[a-zA-Z ]{2,30}$/;

function validateNameAndSurname(id)
{
    var control = document.getElemetnById(id);
    return regexName.test(id);
}

function saveChanges()
{
    var name = document.getElementById("NameContainer").value;
    var surname = document.getElementById("SurnameContainer").value;

    if(validateNameAndSurname(name) || validateNameAndSurname(surname))
    {
        document.getElementsByTagName("form")[0].submit();
    }
}