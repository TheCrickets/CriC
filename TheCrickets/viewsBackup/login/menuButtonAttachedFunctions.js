function topFunction() {
    document.body.scrollTop += 600;
    document.documentElement.scrollTop += 600;
}


function removeOtherTextButLogin() {
    toggleError();
    var elements = document.querySelectorAll( "body > *" );
    Array.from( elements ).forEach( s => s.style.filter = "none" );
    var element = document.getElementById("registerLeftText");
    element.style.visibility = "hidden";

    element = document.getElementById("coverBlurrDiv");
    element.style.visibility = "hidden";
    showLoginText();
}

function showLoginText() {
    var elements = document.querySelectorAll( "body > *:not(.loginContainer):not(#navDivWrapper):not(#loginLeftText)" );
    Array.from( elements ).forEach( s => s.style.filter = "blur(3px)" );

    var background = document.getElementById("coverBlurrDiv");
    background.style.visibility = "visible";
    
    var elem = document.getElementById("loginLeftText");

    if(elem.style.visibility == "hidden")
      {
          elem.style.visibility = "visible"; 
          var pos = -50;
          var id = setInterval(frame, 1);
          function frame() {
              if (pos == 130) {
                  clearInterval(id);
              } 
              else {
                  pos+=2; 
                  elem.style.left = pos + 'px'; 
              }
          }
      }
  }


function removeOtherTextButRegister() {
    toggleError();
    var elements = document.querySelectorAll( "body > *" );
    Array.from( elements ).forEach( s => s.style.filter = "none" );
    
    var element = document.getElementById("loginLeftText");
    element.style.visibility = "hidden";
    
    element = document.getElementById("coverBlurrDiv");
    element.style.visibility = "hidden";
    
    showRegisterText();
    
}

function showRegisterText() {
  var elements = document.querySelectorAll( "body > *:not(#registerContainer):not(#navDivWrapper):not(#registerLeftText)" );
  Array.from( elements ).forEach( s => s.style.filter = "blur(3px)" );

  var background = document.getElementById("coverBlurrDiv");
  background.style.visibility = "visible";
  
  var elem = document.getElementById("registerLeftText");

  if(elem.style.visibility == "hidden")
    {
        
        elem.style.visibility = "visible"; 
        var pos = -50;
        var id = setInterval(frame, 1);
        function frame() {
            if (pos == 130) {
                clearInterval(id);
            } 
            else {
                pos+=2; 
                elem.style.left = pos + 'px'; 
            }
        }
    }
}


function removeOtherTextButAbout() {
    var element = document.getElementById("loginLeftText");
    element.style.visibility = "hidden";
    var element = document.getElementById("registerLeftText");
    element.style.visibility = "hidden";
    element = document.getElementById("coverBlurrDiv");
    element.style.visibility = "hidden";
    var elements = document.querySelectorAll( "body > *" );
    Array.from( elements ).forEach( s => s.style.filter = "none" );
}

function removeOtherTextButContact() {
    var element = document.getElementById("loginLeftText");
    element.style.visibility = "hidden";
    var element = document.getElementById("registerLeftText");
    element.style.visibility = "hidden";
    element = document.getElementById("coverBlurrDiv");
    element.style.visibility = "hidden";
    var elements = document.querySelectorAll( "body > *" );
    Array.from( elements ).forEach( s => s.style.filter = "none" );
}



