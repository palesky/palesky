<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Dashboard</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Icons-->
<script src="js/lumino.glyphs.js"></script>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<jsp:include page="./WEB-INF/header.jsp" flush="true" />
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="index.jsp"><svg
						class="glyph stroked dashboard-dial">
						<use xlink:href="#stroked-dashboard-dial"></use></svg> 个人面板</a></li>
			<li><a href="product"><svg class="glyph stroked calendar">
						<use xlink:href="#stroked-calendar"></use></svg> 产品</a></li>
			<li><a href="project"><svg class="glyph stroked line-graph">
						<use xlink:href="#stroked-line-graph"></use></svg> 项目</a></li>
			<li><a href="demand"><svg class="glyph stroked table">
						<use xlink:href="#stroked-table"></use></svg> 需求</a></li>
			<li><a href="task"><svg class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> 任务</a></li>
			<li><a href="bug"><svg class="glyph stroked table">
						<use xlink:href="#stroked-table"></use></svg> bug</a></li>
			<li class="active"><a href="usecase"><svg
						class="glyph stroked star">
						<use xlink:href="#stroked-star"></use></svg> 用例 </a></li>

			<li role="presentation" class="divider"></li>
			<li><a href="logout"><svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use></svg> 回到登陆页</a></li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xmlns:xlink="http://www.w3.org/1999/xlink"
								xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active"><a href="${url}?q=all">全部${itemType}</a></li>
				<li class="active"><a href="${url}?q=me">与我相关${itemType}</a></li>

			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Usecase 测试用例
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-7">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						测试用例列表
						<button type="button" class="btn btn-primary pull-right"
							data-toggle="modal" data-target="#addusecase" style="margin-top:7px;">添加测试用例</button>
					</div>
					<!-- Table -->
					<table class="table table-hover table-bordered">
						<tr>
							<td>id</td>
							<td>步骤</td>
							<td>创建日期</td>
							<td>创建者</td>
							<!-- <td>操作</td> -->
						</tr>
						<c:forEach var="ob" items="${requestScope.itemList}">
							<tr>
								<td>${ob.id}</td>
								<td>${ob.steps}</td>
								<td>${ob.createdDate}</td>
								<td>${ob.createdBy}</td>
		
							<%-- 	<td><a href="${sonUrl}?q=${ob.id}">查看</a></td> --%>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>

	<div class="modal fade" id="addusecase" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加测试用例</h4>
				</div>

				<div class="modal-body">
					<form action="addusecase" method="post">
						<div class="form-group">
							<input class="form-control" name="id" placeholder="编号">
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-12 control-label">测试用例步骤填写</label>
							<textarea rows="5" name="steps"  class="form-control" placeholder="[步骤][结果][期望]"></textarea>
						</div>
						<button type="submit" class="btn btn-primary">保存</button>
					</form>
				</div>
			</div>
		</div>

		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/chart.min.js"></script>
		<script src="js/chart-data.js"></script>
		<script src="js/easypiechart.js"></script>
		<script src="js/easypiechart-data.js"></script>
		<script src="js/bootstrap-datepicker.js"></script>
		<script>
			$('#calendar').datepicker({});

			!function($) {
				$(document).on(
						"click",
						"ul.nav li.parent > a > span.icon",
						function() {
							$(this).find('em:first').toggleClass(
									"glyphicon-minus");
						});
				$(".sidebar span.icon").find('em:first').addClass(
						"glyphicon-plus");
			}(window.jQuery);

			$(window).on('resize', function() {
				if ($(window).width() > 768)
					$('#sidebar-collapse').collapse('show')
			})
			$(window).on('resize', function() {
				if ($(window).width() <= 767)
					$('#sidebar-collapse').collapse('hide')
			})
		</script>
</body>

</html>
