<%@ page contentType="text/html;charset=UTF-8" %>

<%--
    User: KennySo
    Date: 2024/3/27
--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>K-Mall</title>
    <base href="<%=request.getContextPath()%>/">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // 决定显示的是登录tab还是注册tab
            // 如果注册失败, 显示的就是注册tab
            if ("${requestScope.active}" === "register") {
                $("#register_tab")[0].click();
            }

            // 使用Ajax验证用户名是否已存在
            $("#username").blur(function () {
                $.getJSON(
                    "memberServlet",
                    {
                        "action" : "isExistUsername",
                        "username" : this.value,
                        "date" : new Date()
                    },
                    function(data) {
                        if (data.isExist) {
                            $("span.errorMsg").text("用户名已被占用");
                        } else {
                            $("span.errorMsg").text("用户名可用");
                        }
                    }
                );
            });


            // 点击验证码图片进行更新
            $("#code_img").click(function() {
                // 在url没有变化的时候, 图片不会发出新的请求(浏览器有缓存)
                // 所以要给url携带一个变化的参数
                this.src = "<%=request.getContextPath()%>/kaptchaServlet?date=" + new Date();
            });

            $("#sub-btn").click(function () {
                // 验证用户名
                let usernameVal = $("#username").val();
                let usernamePattern = /^\w{6,10}$/;
                if (!usernamePattern.test(usernameVal)) {
                    $("span[class='errorMsg']").text("用户名格式不对, 需要6-10个字符.");
                    return false;    // 验证失败, 设置表单不提交
                }

                // 验证密码
                let passwordVal = $("#password").val();
                let passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    $("span.errorMsg").text("密码格式不对, 需要6-10个字符.");
                    return false;
                }

                // 验证两次密码格式相等
                let repeatPasswordVal = $("#repeatPassword").val();
                if (repeatPasswordVal !== passwordVal) {
                    $("span.errorMsg").text("两次密码不相等, 请重新输入.");
                    return false;
                }

                // 验证邮箱
                let emailVal = $("#email").val();
                // 在java中, 正则表达式的转义是\\; 在js中, 正则表达式的转义是\
                let emailPattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                if (!emailPattern.test(emailVal)) {
                    $("span.errorMsg").text("邮箱格式不对, 请重新输入.");
                    return false;
                }

                // 验证验证码
                let codeVal = $("#code").val().trim();
                if (codeVal == null || codeVal === "") {
                    $("span.errorMsg").text("验证码不能为空.");
                    return false;
                }

                // 验证通过, 显示通过信息
                // $("span.errorMsg").text("验证通过...");
                // return false;
            });
        });
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo" /></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a id="login_tab" class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="register_tab" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <span class="errorMsg" style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px; color: red">${requestScope.error_msg}</span>
                                <div class="login-register-form">
                                    <form action="memberServlet" method="post">
                                        <input type="hidden" name="action" value="login">
                                        <input type="text" name="username" placeholder="Username" value="${requestScope.username}"/>
                                        <input type="password" name="password" placeholder="Password"/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg" style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px; color: red">${requestScope.error_msg}</span>
                                    <form action="memberServlet" method="post">
                                        <input type="hidden" name="action" value="register">
                                        <input type="text" id="username" name="username" value="${param.username}" placeholder="Username"/>
                                        <input type="password" id="password" name="password" value="${param.password}" placeholder="输入密码"/>
                                        <input type="password" id="repeatPassword" name="repeatPassword" value="${param.repeatPassword}" placeholder="确认密码"/>
                                        <input type="email" id="email" name="email" value="${param.email}" placeholder="电子邮件"/>
                                        <input type="text" id="code" name="code" value="${param.code}" style="width: 50%" placeholder="验证码"/>　　
                                            <img id="code_img" alt="" src="kaptchaServlet" style="width: 120px; height: 50px ">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.jsp">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2024 K-Mall</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>