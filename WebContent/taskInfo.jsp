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
			<li class="active"><a href="task"><svg
						class="glyph stroked pencil">
						<use xlink:href="#stroked-pencil"></use></svg> 任务</a></li>
			<li><a href="bug"><svg class="glyph stroked table">
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
				<h1 class="page-header">Task 任务</h1>
			</div>
		</div>
		
		<div class="row">
			<!-- 
			可用于product project demand task
			id name status createdBy createdDate explain chargeBy
			$sonUrl 子 的处理器地址
			$itemList->ob 子
			$item 父
 -->
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						${item.name}
						<button style="margin-left: 6px; margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#showUpdateModal">修改</button>
						<button style="margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#delete">删除</button>
					</div>
					<div class="panel-body">
						<p>${item.explain}</p>
					</div>
					<table class="table table-hover table-bordered">
						<tr>
							<td>id</td>
							<td>名称</td>
							<td>状态</td>
							<td>创建日期</td>
							<td>创建者</td>
							<td>负责人</td>
							<td>开启</td>
							<td>关闭</td>
						</tr>
						<tr>
							<td>${item.id}</td>
							<td>${item.name}</td>
							<td>${item.status}</td>
							<td>${item.createdDate}</td>
							<td>${item.createdBy}</td>
							<td>${item.chargeBy}</td>
							<td><a href="op?type=${url}&id=${item.id}&method=open">开启</a></td>
							<td><a href="op?type=${url}&id=${item.id}&method=close">关闭</a></td>
						</tr>
					</table>
				</div>
			</div>
			
			

			
		</div>
		
		<!-- 关联的bug -->
			<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						关联的bug
						<button style="margin-left: 6px; margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#showUpdateModal">修改</button>
						<button style="margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#delete">删除</button>
					</div>
					<!-- Table -->
					<table class="table table-hover table-bordered">
						<tr>
							<td>id</td>
							<td>名称</td>
							<td>状态</td>
							<td>提交日期</td>
							<td>提交者</td>
							<td>负责人</td>
							<td>操作</td>
						</tr>
						<c:forEach var="ob" items="${requestScope.bugList}">
							<tr>
								<td>${ob.id}</td>
								<td>${ob.name}</td>
								<td>${ob.status}</td>
								<td>${ob.foundDate}</td>
								<td>${ob.foundBy}</td>
								<td>${ob.chargeBy}</td>
								<td><a href="bug?q=${ob.id}">查看</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div></div>
			
			<!-- 关联的用例 -->
			<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						关联的用例
						<button style="margin-left: 6px; margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#showUpdateModal">修改</button>
						<button style="margin-top: 5px;"
							class="pull-right btn btn-primary " data-toggle="modal"
							data-target="#delete">删除</button>
					</div>
					<%-- <div class="panel-body">
						<p>${item.explain}</p>
					</div> --%>
					<!-- Table -->
					<table class="table table-hover table-bordered">
						<tr>
							<td>id</td>
							<td>创建日期</td>
							<td>创建者</td>
							<td>步骤</td>
							<td>操作</td>
						</tr>
						<c:forEach var="ob" items="${requestScope.usecaseList}">
							<tr>
								<td>${ob.id}</td>
								<td>${ob.createdDate}</td>
								<td>${ob.createdBy}</td>
								<td>${ob.steps}</td>
								<td><a href="usecase?q=${ob.id}">查看</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div></div>
		
		<!-- Modal -->
			<div class="modal fade" id="showUpdateModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">修改${itemType}</h4>
						</div>
						<form action="update${url}">
							<div class="modal-body">

								<div class="form-group">
									<label for="exampleInputEmail1">${itemType}编号</label> <input
										type="text" class="form-control" name="id" placeholder="输入编号"
										value="${item.id}">
								</div>

								<div class="form-group">
									<label for="exampleInputEmail1">${itemType}名称</label> <input
										type="text" class="form-control" name="name"
										placeholder="输入名称" value="${item.name}">
								</div>

								<div class="form-group">
									<label for="exampleInputEmail1">${itemType}简介</label>
									<textarea class="form-control" rows="5" name="explain"
										value="${item.explain}"></textarea>
								</div>

								<input type="hidden" name="status" value="待审核">
								<div class="form-group">
									<div class="row">
										<c:if test="${itemType=='产品'}">
											<div class="col-sm-6">
												<label for="exampleInputEmail1">${itemType}类型</label> <select
													name="pro_type">
													<option value="单平台">单平台</option>
													<option value="多平台">多平台</option>
												</select>
											</div>
										</c:if>

										<div class="col-sm-6">
											<label for="exampleInputEmail1">创建者:${item.createdBy}</label>
											<input type="hidden" name="createdBy"
												value="${item.createdBy}">
										</div>
									</div>
								</div>

								<c:if test="${itemType=='项目'}">
									<div class="form-group input-group">
										<span class="input-group-addon">所属团队</span> <select
											name="team" class="form-control">
											<c:forEach var="item" items="${requestScope.teamList}">
												<option value="item">${item}</option>
											</c:forEach>
										</select>
									</div>
								</c:if>

								<c:if test="${itemType=='项目'}">
									<div class="form-group input-group">
										<span class="input-group-addon">所属产品</span> <select
											name="pro_id" class="form-control">
											<c:forEach var="item" items="${requestScope.productList}">
												<option value="item.id">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</c:if>

								<c:if test="${itemType=='需求'}">
									<div class="form-group input-group">
										<span class="input-group-addon">所属项目</span> <select
											name="project_id" class="form-control">
											<c:forEach var="item" items="${requestScope.projectList}">
												<option value="item.id">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</c:if>

								<c:if test="${itemType=='任务'}">
									<div class="form-group input-group">
										<span class="input-group-addon">所属需求</span> <select
											name="demand_id" class="form-control">
											<c:forEach var="item" items="${requestScope.demandList}">
												<option value="item.id">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</c:if>

								<div class="form-group">
									<label for="exampleInputEmail1">结束日期</label> <input type="text"
										class="form-control" name="endDate"
										placeholder="输入日期 格式为:yyyy-mm-dd">
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-sm-6">
											<label for="exampleInputEmail1">指派给</label> <select
												name="chargeBy">
												<c:forEach var="chargedMan"
													items="${sessionScope.chargedByList}">
													<option value="${chargedMan.id}">${chargedMan.role}${chargedMan.realname}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-6">
											<label for="exampleInputEmail1">审核者</label> <select
												name="confirmedBy">
												<c:forEach var="confirmedMan"
													items="${sessionScope.confirmedByList}">
													<option value="${confirmedMan.id}">${confirmedMan.role}${confirmedMan.realname}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save
									changes</button>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="modal fade" id="delete" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">确认删除？</h4>
						</div>
						<div class="modal-footer">
							<form action="delete${url}">
								<input type="hidden" name="id" value="${item.id}">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-primary">确认</button>
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
