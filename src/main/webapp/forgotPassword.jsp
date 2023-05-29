<%--
  Created by IntelliJ IDEA.
  User: dmngh
  Date: 5/16/2023
  Time: 12:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>ForgotPassword</title>
        <link rel="stylesheet" href="css/forgotpassword.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="root">
            <header class="nav-bar">

            </header>

            <div class="main">
                <div class="row">
                    <div class="signup">
                        <div class="signup-title">
                            <h1>Forgot Password</h1>
                        </div>
                        <div class="signup-detail">
                            <div class="signup-form">
                                <form id="form-signup" method="post" action="forgotPassword">
                                    <div class="form-row">
                                        <div class="label"><h3>Account</h3></div>
                                        <div class="input-area">
                                            <input style="font-size: 1.5em" id="account-input" type="text"
                                                   name="account" value="${account}"><br>
                                            <p style="color: red;" id="account-validate" class="message-error"></p>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="label"><h3>Mã Captcha</h3></div>
                                        <div class="input-area" style="display: flex">
                                            <img style="height: 40px;background-image: url('images/backgroundCaptcha.png')" id="captchaImg"
                                                 src="/SellPhoneCard/captcha-servlet">
                                            <div class="captcha-div">
                                                <div style="display: block">
                                                    <input id="captcha-input"
                                                           style="font-size: 1.5em;font-weight: bold;width: 30%;margin-right: 2px;height: 40px;"
                                                           type="text"
                                                           maxlength="5" placeholder="input" name="captcha">
                                                    <button type="button" onclick="refreshCaptcha()" id="refresh-button"
                                                            style="cursor: pointer;height: 68%;"><i style="font-size: 1.5em;"
                                                                                            class="fa fa-refresh"></i>
                                                    </button>
                                                </div>

                                                <p style="color: red;" id="captcha-validate" class="message-error">${captchaMessageErr}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <button id="submit-button" type="submit" 
                                                style="font-size: 1.2em;cursor: pointer;padding: 5px;">
                                            <i class="fa fa-plus"></i> Quên mật khẩu
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% String message = (String)request.getAttribute("message"); %>
            <% if(message != null) { %>
            <p style="color: red"><%=message%></p>
            <% } %>  
            <footer class="footer">
                <div>
                    Powered by: SWP391-Group5 ©2023
                </div>
            </footer>
        </div>
        <script src="js/register.js"></script>
    </body>
</html>
