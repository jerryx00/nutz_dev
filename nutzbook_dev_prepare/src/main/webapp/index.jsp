<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NutzBook demo</title>
    <!-- 导入jquery -->
    <script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>
    <!-- 把user id复制到一个js变量 -->
    <script type="text/javascript">
        var me = '<%=session.getAttribute("me") %>';
        var base = '${base}';
        $(function () {
            $("#login_button").click(function () {
                $.ajax({
                    url: base + "/user/login",
                    type: "POST",
                    data: $('#loginForm').serialize(),
                    error: function (request) {
                        alert("Connection error");
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data);
                        if (data == true) {
                            alert("登陆成功");
                            location.reload();
                        } else {
                            alert("登陆失败,请检查账号密码")
                        }
                    }
                });
                return false;
            });
            if (me != "null") {
                $("#login_div").hide();
                $("#userInfo").html("您的Id是" + me);
                $("#user_info_div").show();
                $("#user_list_div").show();
                $("#user_profile_div").show();
            } else {
                $("#login_div").show();
                $("#user_info_div").hide();
                $("#user_list_div").hide();
                $("#user_profile_div").hide();
            }
        });
    </script>
</head>
<body>
<div id="login_div">
    <form action="#" id="loginForm" method="post">
        用户名 <input name="name" type="text" value="admin">
        密码 <input name="password" type="password" value="123456">
        <button id="login_button">提交</button>
    </form>
</div>
<div id="user_info_div">
    <p id="userInfo"></p>
    <a href="${base}/user/logout">登出</a>
</div>


<div id="user_list_div">
    <p id="list"></p>
    <a href="${base}/user/list">用户列表</a>
</div>


<div id="user_profile_div">
    <p id="userProfile"></p>
    <a href="${base}/user/profile">个人信息</a>
</div>

</body>
</html>