<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PaleSky 软件测试管理系统</title>

<link rel="stylesheet" type="text/css" href="./css/normalize.css" />
<link rel="stylesheet" type="text/css" href="./css/demo.css" />

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="./css/component.css" />
<script type="text/javascript">
    function check(){
        var myForm=document.getElementById("form");
        if(myForm[0].value==""){
            myForm[0].placeholder="账户名不能为空";
            myForm[0].focus();
            return false;
        }else if(myForm[1].value==""){
             myForm[1].placeholder="密码不能为空";
            myForm[1].focus();
            return false;
        }
        return true;
    }
</script>
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<h1 class="main-title">
					pale-sky <span class="thin"> pms</span>
				</h1>
			</div>
			<form id="form" method="post" action="LoginServlet.do">
				<div>
					<input type="text" autocomplete="off" placeholder="账户名"
						class="username" name="username" value="${username}">
				</div>
				<div>
					<input type="password" onpaste="return false"
						oncontextmenu="return false" placeholder="密码" class="password"
						name="password">
				</div>
				<button type="submit" id="submit" onclick="return check()">登陆</button>
				<br>
				<br><!--  <a href="#">忘记密码？</a> -->
			</form>


		</div>
		<!-- <div class="footer">
				<p id="powered">Powered By XJY-LK</p>
			</div> -->
	</div>
	<!-- /container -->
	<script src="./js/TweenLite.min.js"></script>
	<script src="./js/EasePack.min.js"></script>
	<script src="./js/rAF.js"></script>
	<script src="./js/demo-1.js"></script>
</body>
</html>