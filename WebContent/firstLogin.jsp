<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Forms</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<script type="text/JavaScript">
 function checkop(){
	 op=form1.oldPassword.value;
	 if(op.length<1){
		 alert("不能为空");
	 }
	 else{
	 }
 }
 function checkpsd1(){    
		psd1=form1.newPasswordOne.value;  
		var flagZM=false ;
		var flagSZ=false ; 
		var flagQT=false ;
		if(psd1.length<6 || psd1.length>18){   
			alert("长度错误");
		}else
			{   
			  for(i=0;i < psd1.length;i++)   
				{    
					if((psd1.charAt(i) >= 'A' && psd1.charAt(i)<='Z') || (psd1.charAt(i)>='a' && psd1.charAt(i)<='z')) 
					{   
						flagZM=true;
					}
					else if(psd1.charAt(i)>='0' && psd1.charAt(i)<='9')    
					{ 
						flagSZ=true;
					}else    
					{ 
						flagQT=true;
					}   
				}   
				if(!flagZM||!flagSZ||flagQT){
				alert("密码必须是字母数字的组合"); 
				 
				}else{					
				 
				}  
			 
			}	
	}
 function checkpsd2(){ 
		if(form1.newPasswordOne.value!=form1.newPasswordTwo.value) { 
		     alert("您两次输入的密码不一样");
		} else { 
		}
}

</script>
</head>

<body>
	
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">首次登陆请修改密码</div>
				<div class="panel-body">
					<form role="form" name="form1" action="setPassword" onSubmit="isCheck()">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="请输入旧密码" 
								onblur="checkop()" id="oldPassword" 
								name="oldPassword" type="password" autofocus="" onpaste="return false" 
								oncontextmenu="return false" required="required">						
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="请输入新密码" 
								onblur="checkpsd1()" id="newPasswordOne" 
								name="newPasswordOne" type="password" value="" onpaste="return false" 
								oncontextmenu="return false" required="required">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="请再次输入" 
								onblur="checkpsd2()" id="newPasswordTwo" 
								name="newPasswordTwo" type="password" value="" onpaste="return false" 
								oncontextmenu="return false" required="required">
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div><!-- /.col-->
	</div><!-- /.row -->	
	
		

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>
