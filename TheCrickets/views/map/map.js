
function table(nr) {
    
	var xhttp = new XMLHttpRequest();

var url = "https://www.googleapis.com/fusiontables/v2/query?sql=SELECT * FROM 16MVlANGXdtndx6ouQaufWzvPyzlfGPsl1DfTDJkH order by "+ nr+"&key=AIzaSyCZM6ADktRUOURrnm1DCvTodYP_HLlSJnU";
xhttp.open("POST", url,   true);
   
 var txt="" , x , myObj;

  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200){
  var json = JSON.parse(xhttp.responseText);

      myObj=  JSON.parse(xhttp.responseText);
       txt += "<table border='1' width='90%''>"
       txt += "<tr><td>" + myObj.columns[0] + "</td><td>" + myObj.columns[1] + "</td><td>" + myObj.columns[2] + "</td><td>" + myObj.columns[3] + "</td><td>" + myObj.columns[4] + "</td></tr>";
        for (x in myObj.rows) {

            txt += "<tr><td>" + myObj.rows[x][0] + "</td><td>" + myObj.rows[x][1] + "</td><td>" + myObj.rows[x][2] + "</td><td>" + myObj.rows[x][3] + "</td><td>" + myObj.rows[x][4] + "</td></tr>";
         
        }
        txt += "</table>"        
        document.getElementById("demo").innerHTML = txt;
    }
     //  console.log(xhttp.responseText);
  }
xhttp.send();
}
