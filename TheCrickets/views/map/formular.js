var regex = /^-?\d+(\.?\d+)?$/;
function adaugare(){
 var tip = document.getElementById("Type").value;
 var lat = document.getElementById("Latitude").value;
 var lon = document.getElementById("Longitude").value;
if(regex.test(lat) && regex.test(lon))
if(tip!== ""){

var url = "http://138.68.64.239:55556/api/disasters";
var params = JSON.stringify({
"type":tip,
"latitude":lat,
"longitude":lon
});
var xhttp = new XMLHttpRequest();
xhttp.open("POST", url,   true);
 	xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
  			 console.log( xhttp.responseText );
			toggleError( "Succes!");
    }
    else 
    	toggleError("Ati gresit");
  };

xhttp.send(params);

}
else 
	alert("Add type!")
	else
alert("Latitude or Longitude invalid!")
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