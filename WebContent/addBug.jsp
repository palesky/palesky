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

				<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xmlns:xlink="http://www.w3.org/1999/xlink"
								xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active"><a href="bug?q=all">全部bug</a></li>
				<li class="active"><a href="bug?q=me">与我相关bug</a></li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">提交bug</h1>
					</div>
					<div class="panel-body">
						<form action="addbug" method="post">
							<div class="modal-body">
								<!-- <div class="form-group">
									<label for="exampleInputEmail1">bug编号</label> <input
										type="text" class="form-control" name="id" placeholder="输入编号"
										>
								</div> -->

								<div class="form-group">
									<label for="exampleInputEmail1">bug标题</label> <input
										type="text" class="form-control" name="name"
										placeholder="输入名称" >
								</div>
								<div class="form-group">
									<div class="row">

										<div class="col-sm-6">
											<div class="form-group input-group">
												<span class="input-group-addon">所使用用例</span> <select
													name="usecaseId" class="form-control">
													<c:forEach var="item" items="${requestScope.usecaseList}">
														<option value="item.id">${item.id}</option>
													</c:forEach>
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

								<div class="form-group">
									<label for="exampleInputEmail1">${itemType}重现步骤</label>
									<textarea class="form-control" rows="7" name="steps"
										></textarea>
								</div>

								<!-- 由项目经理指派 -->
								<input type="hidden" name="status" value="待指派"> <input
									type="hidden" name="foundBy" value="${user.id}" >


								<div class="form-group">

									<div class="row">

										<div class="col-sm-4">
											<div class="form-group input-group">
												<span class="input-group-addon">bug类型</span> <select
													name="bug_type" class="form-control">
													<option value="代码错误">代码错误</option>
													<option value="界面优化">界面优化</option>
													<option value="设计缺陷">设计缺陷</option>
													<option value="配置相关">配置相关</option>
													<option value="安装部署">安装部署</option>
													<option value="安全相关">安全相关</option>
													<option value="性能问题">性能问题</option>
													<option value="标准规范">标准规范</option>
													<option value="测试脚本">测试脚本</option>
													<option value="其他">其他</option>
												</select>
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group input-group">
												<span class="input-group-addon">操作系统</span> <select
													name="os" class="form-control">
													<option value="Windows">Windows</option>
													<option value="Windows 8">Windows 8</option>
													<option value="Windows 7">Windows 7</option>
													<option value="Windows Vista">Windows Vista</option>
													<option value="Windows XP">Windows XP</option>
													<option value="Windows 2012">Windows 2012</option>
													<option value="Windows 2008">Windows 2008</option>
													<option value="Windows 2003">Windows 2003</option>
													<option value="Windows 2000">Windows 2000</option>
													<option value="Android">Android</option>
													<option value="IOS">IOS</option>
													<option value="WP8">WP8</option>
													<option value="WP7">WP7</option>
													<option value="Symbian">Symbian</option>
													<option value="Linux">Linux</option>
													<option value="FreeBSD">FreeBSD</option>
													<option value="OS">OS X</option>
													<option value="Unix">Unix</option>
													<option value="其他">其他</option>
												</select>
											</div>
										</div>

										<div class="col-sm-4">
											<div class="form-group input-group">
												<span class="input-group-addon">浏览器</span> <select
													name="browser" class="form-control">
													<option value="all">全部</option>
													<option value="ie">IE系列</option>
													<option value="ie11">IE11</option>
													<option value="ie10">IE10</option>
													<option value="ie9">IE9</option>
													<option value="ie8">IE8</option>
													<option value="ie7">IE7</option>
													<option value="ie6">IE6</option>
													<option value="chrome">chrome</option>
													<option value="firefox">firefox系列</option>
													<option value="firefox4">firefox4</option>
													<option value="firefox3">firefox3</option>
													<option value="firefox2">firefox2</option>
													<option value="opera">opera系列</option>
													<option value="oprea11">opera11</option>
													<option value="oprea10">opera10</option>
													<option value="opera9">opera9</option>
													<option value="safari">safari</option>
													<option value="maxthon">傲游</option>
													<option value="uc">UC</option>
													<option value="other">其他</option>
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
													name="task_testerId" class="form-control">
													<c:forEach var="item" items="${requestScope.taskList}">
														<option value="${item.id}">${item.taskId}</option>
													</c:forEach>
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
													<option value="最高" selected="selected">普通</option>
													<option value="最高">最低</option>
													<option value="最高">紧急</option>
													<option value="最高">最高</option>
												</select>
											</div>
										</div>
										<c:if test="${user.role=='测试人员'}">
											<div class="col-sm-4">
												<div class="form-group input-group">
													<span class="input-group-addon">提交给</span> <select
														name="chargeBy" class="form-control">
														<c:forEach var="chargedMan"
															items="${sessionScope.chargedByList}">
															<c:if test="${chargedMan.role=='测试经理'}">
																<option value="${chargedMan.id}">${chargedMan.role}${chargedMan.realname}</option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
										</c:if>
										<c:if test="${user.role=='测试经理'}">
											<div class="col-sm-4">
												<div class="form-group input-group">
													<span class="input-group-addon">分配给</span> <select
														name="chargeBy" class="form-control">
														<c:forEach var="chargedMan"
															items="${sessionScope.developerList}">
															<option value="${chargedMan.id}">${chargedMan.role}${chargedMan.realname}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</c:if>
									</div>
								</div>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">返回</button>
								<button type="submit" class="btn btn-primary">保存</button>
							</div>
						</form>
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
