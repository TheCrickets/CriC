import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;


public class RoutingHandlers
{


    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send("<!doctype html>\n" +
                "<html lang=\"zxx\">\n" +
                "\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>Login</title>\n" +
                "\t<link href = \"loginStyleSheet.css\" rel = \"stylesheet\" type = \"text/css\">\n" +
                "    <link href=\"loginBox.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"registerBox.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <script src=\"login.js\"></script>\n" +
                "    <script src=\"menuButtonAttachedFunctions.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body onload=\"onLoad();\">\n" +
                "    <div id = \"coverBlurrDiv\"></div>\n" +
                "    <div id = \"logoWrapper\"><div class = \"redLetter\">C</div>riC</div>\n" +
                "\n" +
                "\t<div id=\"navDivWrapper\">\n" +
                "\t\t<nav>\n" +
                "            <div class=\"lineFromCenter\"><a href=\"#\"></a></div>\n" +
                "\t\t\t<div class=\"lineFromCenter\"><a href=\"#\" onclick=\"removeOtherTextButAbout()\"><img class = \"navBarImage\" src=\"resources/about.png\" alt=\"About\">About</a></div>\n" +
                "\t\t\t<div class=\"lineFromCenter\"><a href=\"#loginBox\" onclick=\"removeOtherTextButLogin()\" ><img class = \"navBarImage\" src=\"resources/login.png\" alt=\"Login\">Login</a></div>\n" +
                "\t\t\t<div class=\"lineFromCenter\"><a href=\"#registerContainer\" onclick=\"removeOtherTextButRegister()\"><img class = \"navBarImage\" src=\"resources/register.png\" alt=\"Register\">Register</a></div>\n" +
                "\t\t\t<div class=\"lineFromCenter\"><a href=\"#\" onclick=\"removeOtherTextButContact()\"><img class = \"navBarImage\" src=\"resources/contact.png\" alt=\"Contact\">Contact</a></div>\n" +
                "\t\t</nav>\n" +
                "\t</div>\n" +
                "\t<div id = \"loginBox\" class = \"loginContainer\">\n" +
                "        <div id = \"loginText\"><p id = \"Welcome\">Welcome</p></div>\n" +
                "        <div id = \"errorLogin\"></div>\n" +
                "        <form name=\"loginForm\">\n" +
                "            <div id=\"emailLabelLogin\">Email</div>\n" +
                "\n" +
                "            <input type=\"text\" id=\"emailLogin\" placeholder=\"ex: something@email.com\">\n" +
                "\n" +
                "            <div id=\"passwordLabelLogin\">Password</div>\n" +
                "\n" +
                "            <input type=\"password\" id=\"passwordLogin\" placeholder=\"*****************\">\n" +
                "\n" +
                "            <br>\n" +
                "\n" +
                "            <img src=\"resources/eye/006-eye.png\" id=\"passEyeLogin\" onclick=\"togglePassLogin();\" alt = \"404\">\n" +
                "\n" +
                "            <label id=\"rememberContainer\">\n" +
                "                <input type=\"checkbox\" id=\"rememberBox\"> Remember me\n" +
                "            </label>\n" +
                "\n" +
                "            <div id=\"forgotPassword\">Forgot password?</div>\n" +
                "\n" +
                "            <input type=\"button\" value=\"Log In\" name=\"form\" onclick=\"login();\">\n" +
                "        </form>\n" +
                "    </div>\n" +
                "    <div id = \"loginLeftText\">\n" +
                "        <p>LOGIN> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p> \n" +
                "        <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>\n" +
                "        <p> Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p> \n" +
                "        <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <div id = \"registerLeftText\">\n" +
                "            <p>REGISTER> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p> \n" +
                "            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>\n" +
                "            <p> Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p> \n" +
                "            <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>\n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <div id = \"registerContainer\">\n" +
                "        <br><br>\n" +
                "            <div id = \"RegisterText\"><p id = \"WelcomeRegister\">Welcome</p></div>\n" +
                "    \n" +
                "            <div id=\"errorRegister\">\n" +
                "    \n" +
                "            </div>\n" +
                "    \n" +
                "            <form>\n" +
                "\n" +
                "                <div id=\"emailLabelRegister\">\n" +
                "                    Email\n" +
                "                </div>\n" +
                "    \n" +
                "                <input type=\"text\" id=\"emailRegister\" placeholder=\"ex: something@email.com\">\n" +
                "    \n" +
                "                <div id=\"passwordLabelRegister\">\n" +
                "                    Password\n" +
                "                </div>\n" +
                "    \n" +
                "                <input type=\"password\" id=\"passwordRegister\" placeholder=\"*****************\">\n" +
                "                <br>\n" +
                "    \n" +
                "                <img src=\"resources/eye/006-eye.png\" class=\"passEyeRegister\" onclick=\"togglePassRegister();\" alt = \"404\">\n" +
                "    \n" +
                "                <div id=\"passwordAgainLabel\">\n" +
                "                    Confirm Password\n" +
                "                </div>\n" +
                "    \n" +
                "                <input type=\"password\" id=\"passwordAgain\" placeholder=\"*****************\" onchange=\"checkPassword();\">\n" +
                "                <br>\n" +
                "    \n" +
                "                <img src=\"resources/eye/006-eye.png\" class=\"passEyeRegister\" onclick=\"togglePassAgain();\" alt = \"404\">\n" +
                "    \n" +
                "                <label id=\"acceptContainer\">\n" +
                "                    <p><input type=\"checkbox\" id=\"acceptBox\"> I accept the terms and conditions</p>\n" +
                "                </label>\n" +
                "    \n" +
                "                <input type=\"button\" value=\"Submit\" name=\"form\" onclick=\"register();\">\n" +
                "\n" +
                "            </form>\n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "<div id = \"scrollButton\" onclick=\"topFunction();\">Button</div>\n" +
                "\n" +
                "<div id = \"firstPart\">\n" +
                "    <div id = \"textFirstPart\"><div class=\"redLetter\">Crisis</div> Containment Service</div>\n" +
                "    <div id = \"textFirstPartUnder\">Helping people to find and use critical emergency information when they <div class = \"redLetter\">need it most</div></div>\n" +
                "    <div id = \"firstPartContainer\">\n" +
                "        <div id = \"fireContainer\">\n" +
                "            <div id = \"fire\"></div>\n" +
                "            <div id = \"fireText\">Fire</div>\n" +
                "        </div>\n" +
                "        <div id = \"floodingContainer\">\n" +
                "            <div id = \"flooding\"></div>\n" +
                "            <div id = \"floodingText\">Water</div>\n" +
                "        </div>\n" +
                "        <div id = \"tornadoContainer\">\n" +
                "            <div id = \"tornado\"></div>\n" +
                "            <div id = \"tornadoText\">Air</div>\n" +
                "        </div>\n" +
                "        <div id = \"earthquakeContainer\">\n" +
                "            <div id = \"earthquake\"></div>\n" +
                "            <div id = \"earthquakeText\">Earth</div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div id = \"secondPart\"></div>\n" +
                "\n" +
                "<div class = \"thirdPart\">\n" +
                "        <div class = \"thirdPartOne\"><p class = \"thirdPartText\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p></div>\n" +
                "        <div class = \"thirdPartTwo\"><img src=\"D:\\CriC\\CriC\\TheCrickets\\views\\login\\resources\\flood.png\" style=\"height: 50%; width: 50%\" alt = \"FLOOD.PNG\"></div>\n" +
                "</div>\n" +
                "\n" +
                "<div class = \"thirdPart\">\n" +
                "        <div class = \"thirdPartTwo\"><img src=\"resources/bonfire.png\" style = \"height: 40%; width: 40%\" alt = \"BONFIRE.PNG\"><img src=\"resources/fire-truck.png\" style = \"height: 40%; width: 40%\" alt = \"FIRETRUCK.PNG\"></div>\n" +
                "        <div class = \"thirdPartOne\"><p class = \"thirdPartText\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p></div>\n" +
                "</div>\n" +
                "\n" +
                "<div class = \"thirdPart\">\n" +
                "        <div class = \"thirdPartOne\"><p class = \"thirdPartText\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p></div>\n" +
                "        <div class = \"thirdPartTwo\"><img src=\"resources/earthquake.png\" style=\"height: 50%; width: 50%\" alt = \"EARTHQUAKE.PNG\"></div>\n" +
                "</div>\n" +
                "\n" +
                "<div class = \"thirdPart\">\n" +
                "        <div class = \"thirdPartTwo\"><img src=\"resources/umbrella.png\" style=\"height: 50%; width: 50%\" alt = \"UMBRELLA.PNG\"></div>\n" +
                "        <div class = \"thirdPartOne\"><p class = \"thirdPartText\">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p></div>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        /*CRUD_operations crud_operations = new CRUD_operations();
        User user = crud_operations.readUserData(exchange.getQueryParameters().get("email").getFirst());
        if (user.getId() == 0) {
            exchange.setStatusCode(404);
            JsonUtilities.sendJson(exchange,"Not Found!");
        }
            else JsonUtilities.sendJson(exchange, user);
        */
    }
}