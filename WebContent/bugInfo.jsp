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
			<li><a href="index"><svg
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
			<li class="active"><a href="bug"><svg
						class="glyph stroked table">
						<use xlink:href="#stroked-table"></use></svg> bug</a></li>
			<li><a href="usecase"><svg class="glyph stroked star">
						<use xlink:href="#stroked-star"></use></svg> 用例 </a></li>

			<li role="presentation" class="divider"></li>
			<li><a href="logout"><svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use></svg> 回到登陆页</a></li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

		<%@include file="./WEB-INF/part/breadcrumb.html"%>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">查看bug详细信息</h1>
					</div>
					<div class="panel-body">
						<!-- 	<form action="addbug"> -->
						<div class="modal-body">
							<div class="form-group">
								<label for="exampleInputEmail1">bug编号</label>
								<p class="form-control-static">${item.id}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">状态</label>
								<p class="form-control-static">${item.status}</p>
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">bug标题</label>
								<p class="form-control-static">${item.name}</p>
							</div>
							<div class="form-group">
								<div class="row">

									<div class="col-sm-6">
										<div class="form-group input-group">
											<span class="input-group-addon">所使用用例</span> <select
												class="form-control">
												<option selected="selected">${item.usecaseId}</option>
											</select>
										</div>
									</div>

									<%-- <div class="col-sm-6">
											<div class="form-group input-group">
												<span class="input-group-addon">所属项目</span> <select
													name="project_id" class="form-control">
													<c:forEach var="item" items="${requestScope.projectList}">
														<option value="item.id">${item.name}</option>
													</c:forEach>
												</select>
											</div>
										</div> --%>
								</div>
							</div>

							<div class="form-group well">
								<label for="exampleInputEmail1">${itemType}重现步骤</label>
								<p>${item.steps}</p>
							</div>

							<!-- 由项目经理指派 -->
							<input type="hidden" name="status" value="待指派"> <input
								type="hidden" name="foundBy" value="${item.foundBy}">


							<div class="form-group">

								<div class="row">

									<div class="col-sm-4">
										<div class="form-group input-group">
											<span class="input-group-addon">bug类型</span> <select
												name="bug_type" class="form-control">
												<option selected="selected">${item.bug_type}</option>
											</select>
										</div>
									</div>

									<div class="col-sm-4">
										<div class="form-group input-group">
											<span class="input-group-addon">操作系统</span> <select name="os"
												class="form-control">
												<option selected="selected">${item.os}</option>
											</select>
										</div>
									</div>

									<div class="col-sm-4">
										<div class="form-group input-group">
											<span class="input-group-addon">浏览器</span> <select
												name="browser" class="form-control">
												<option selected="selected">${item.browser}</option>
											</select>
										</div>
									</div>

								</div>

							</div>


							<div class="form-group">
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group input-group">
											<span class="input-group-addon">所属任务</span> <select
												name="task" class="form-control">
												<option selected="selected">${item.task_testerId}</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group input-group">
											<span class="input-group-addon">优先级</span> <select
												name="priority" class="form-control">
												<option selected="selected">${item.priority}</option>
											</select>
										</div>
									</div>
									<form action="assignbug" method="post">
										<c:if test="${user.role=='测试人员'||user.role=='管理员'}">
											<c:if test="${item.status=='待指派'}">
												<div class="col-sm-4">
													<div class="form-group input-group">
														<span class="input-group-addon">提交给</span> <select
															name="chargeBy" class="form-control">
															<c:forEach var="ob" items="${sessionScope.chargedByList}">
																<c:if test="${ob.role=='测试经理'}">
																	<option selected="selected" value="${ob.id}">测试经理${ob.realname}</option>
																</c:if>
															</c:forEach>
														</select> <input type="hidden" name="type" value="指派"> <input
															type="hidden" name="id" value="${item.id}">
													</div>
													<button type="submit" class="btn btn-default">点击此处提交</button>
												</div>
											</c:if>
										</c:if>
									</form>
									<form action="assignbug" method="post">
										<c:if test="${user.role=='测试经理'||user.role=='管理员'}">
											<c:if test="${item.status=='待分配'}">
												<div class="col-sm-4">
													<div class="form-group input-group">
														<span class="input-group-addon">分配给</span> <select
															name="chargeBy" class="form-control">
															<c:forEach var="ob" items="${sessionScope.developerList}">
																<c:if test="${ob.role=='开发人员'}">
																	<option selected="selected" value="${ob.id}">开发人员${ob.realname}</option>
																</c:if>
															</c:forEach>
														</select> <input type="hidden" name="type" value="分配"> <input
															type="hidden" name="id" value="${item.id}">
													</div>
													<button type="submit" class="btn btn-default">点击此处分配</button>
												</div>
											</c:if>
										</c:if>
									</form>
									<form action="assignbug" method="post">
										<c:if test="${user.role=='开发人员'||user.role=='管理员'}">
											<c:if test="${item.status=='进行中'}">
												<div class="col-sm-4">
													<div class="form-group input-group">
														<span class="input-group-addon">提交给</span> <select
															name="chargeBy" class="form-control">
															<c:forEach var="ob" items="${sessionScope.tester}">
																<c:if test="${ob.role=='测试人员'}">
																	<option selected="selected" value="${ob.id}">测试人员${ob.realname}</option>
																</c:if>
															</c:forEach>
														</select> <input type="hidden" name="type" value="提交"> <input
															type="hidden" name="id" value="${item.id}">
													</div>
													<button type="submit" class="btn btn-default">提交给测试人员审核</button>
												</div>
											</c:if>
										</c:if>
									</form>
								</div>
							</div>


						</div>
						<div class="modal-footer">
							<c:if test="${user.role=='管理员'||user.role=='测试人员'}">
								<a type="button" class="btn btn-default" href="bug?q=all">返回</a>
								<a class="btn btn-danger" href="openclosebug?id=${item.id}&q=close">无法解决bug</a>
								<a class="btn btn-success" href="openclosebug?id=${item.id}&q=fix">已解决bug</a>
							</c:if>
						</div>
						<!-- </form> -->
					</div>
				</div>
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
			$(document)
					.on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
							});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
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
