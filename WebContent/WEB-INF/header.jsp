<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index"><span>Palesky</span>软件测试管理平台</a>
			<a class="navbar-brand" href="./introduce/introduce.html"><span>introduce</span></a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><svg
							class="glyph stroked male-user">
							<use xlink:href="#stroked-male-user"></use></svg> ${user.account} <span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">

						<li><a data-toggle="modal" data-target="#setInfo"><svg
									class="glyph stroked male-user">
									<use xlink:href="#stroked-male-user"></use></svg> 个人信息</a></li>
						<li><a data-toggle="modal" data-target="#setPassword"><svg
									class="glyph stroked gear">
									<use xlink:href="#stroked-gear"></use></svg> 更改密码</a></li>
						<li><a href="logout"><svg class="glyph stroked cancel">
									<use xlink:href="#stroked-cancel"></use></svg> 注销</a></li>
						<c:if test="${user.privilege==0}">
							<li><a data-toggle="modal" data-target="#adduser">
									<svg class="glyph stroked male-user">
						            <use xlink:href="#stroked-male-user"></use></svg>
									创建新用户
								</a></li>
						</c:if>
					</ul></li>
			</ul>
		</div>
	</div>
	<!-- /.container-fluid -->
</nav>
<!-- Modal -->
<div class="modal fade" id="setInfo" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">个人信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="updateUser" method="post"">
					<div class="form-group">
						<label class="col-sm-2 control-label">员工编号</label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.id}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">账户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="请输入账户名"
								name="account" required="required" value="${user.account}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">真实姓名</label>
						<div class="col-sm-10">
							<input type="text" id="realname" class="form-control"
								placeholder="请输入真实姓名" name="realname" required="required"
								value="${user.realname}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label> <label
							class="col-sm-2 control-label text-left"><p
								class="text-left">${user.gender}</p></label>
						<div class="col-sm-4">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="inlineRadio1" value="男"> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" id="inlineRadio2" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">职位</label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.role}</p>
						</div>
					</div>
					<!--  项目经理以下才有所属团队 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">所属团队</label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.belongTo}</p>
						</div>
					</div>


					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email地址</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="inputEmail"
								placeholder="Email" required="required" name="email"
								value=${user.email}>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputEmail"
								placeholder="电话或手机" name="phone" required="required" required
								pattern="^[0-9]+$" value=${user.phone}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">最后登录ip</label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.ip}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">最后登录时间</label>
						<div class="col-sm-10">
							<p class="form-control-static">${user.lastLogin}</p>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
				</form>
			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div> -->
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="setPassword" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="setPassword">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">请输入旧密码</label>
						<div class="col-sm-9">
							<input type="password" required="required" name="oldPassword"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">请输入新密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control"
								oncontextmenu="return false" required="required"
								name="newPassword" id="newPasswordOne"
								onchange="checkPasswords()">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-3 control-label">请再次输入</label>
						<div class="col-sm-9">
							<input type="password" class="form-control"
								oncontextmenu="return false" name="newPasswordTwo"
								id="newPasswordTwo" required="required"
								onchange="checkPasswords()">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-default">提交</button>
						</div>
					</div>
				</form>

			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>

<!-- Modal -->
<form action="adduser">
<div class="modal fade" id="adduser" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加新用户</h4>
			</div>
			<div class="modal-body">
				
					<select multiple class="form-control" name="role">
						<option value="产品经理">产品经理</option>
						<option value="项目经理">项目经理</option>
						<option value="测试经理">测试经理</option>
						<option value="开发人员">开发人员</option>
						<option value="测试人员">测试人员</option>
					</select>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">确认</button>
			</div>
		</div>
	</div>
</div>
</form>
