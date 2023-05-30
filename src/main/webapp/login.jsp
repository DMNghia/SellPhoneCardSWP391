<%-- 
    Document   : login
    Created on : May 24, 2023, 11:24:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
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
                            <h1>Login</h1>
                            <p>Login for buying card</p>
                        </div>
                        <div class="signup-detail">
                            <div class="signup-form">
                                <form id="form-signup" method="post" action="login">
                                    <div class="form-row">
                                        <div class="label"><h3>Account</h3></div>
                                        <div class="input-area">
                                            <input style="font-size: 1.5em" id="account-input" type="text"
                                                   name="account" value="${account}"><br>
                                            <p style="color: red;" id="account-validate" class="message-error"></p>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="label"><h3>Password</h3></div>
                                        <div class="input-area">
                                            <input style="font-size: 1.5em" id="password-input" type="password" name="password"
                                                   value="${password}"><br>
                                            <p style="color: red;" id="password-validate" class="message-error"></p>
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
                                        <button style="font-weight: 500;font-size: 18px; line-height: 30px;color: #fff;background: #23a6f7;border-radius: 10px;padding: 10px 32px;
                                                border: none;">Đăng nhập</button>
                                    </div>
                                    <div>
                                        <a href="forgotPassword">Quên mật khẩu</a>
                                        <a href="register">Đăng ký</a>
                                    </div>
                                    <div>
                                        <p>${error}</p>
                                    </div>      
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer">
                <div>
                    Powered by: SWP391-Group5 ©2023
                </div>
            </footer>
        </div>
        <script src="js/register.js"></script>
    </body>
</html>
