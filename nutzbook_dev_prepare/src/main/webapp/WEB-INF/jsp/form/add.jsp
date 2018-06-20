<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>

	<script type="text/javascript">
        var pageNumber = 1;
        var pageSize = 10;
        var base = '<%=request.getAttribute("base")%>';

        $(function() {
            $("#user_add_btn").click(function() {
                $.ajax({
                    url : base + "/form/insert",
                    data : $("#user_add_form").serialize(),
                    dataType : "json",
					type:"POST",
                    success : function(data) {
                        if (data.ok) {
                            alert("添加成功");
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            });
        });

	</script>
</head>
<body>

<div id="user_add">
	<form action="#" id="user_add_form">
		用户名<input name="name">
		密码<input name="password">
	</form>
	<button id="user_add_btn">新增</button>
</div>
</body>
</html>