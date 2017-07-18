<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户操作</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
					},
					password: {
						required: true,
						minlength:3
					},
					passwordConfirm: {
						equalTo:"#password"
					},
					checkedRoleIds:{
						required:true
					}
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
		});
	</script>
</head>

<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<security:authorize ifAnyGranted="A_MODIFY_USER">
		<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>用户</h2>
	</security:authorize>
	<form id="inputForm" action="user!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td>用户名:</td>
				<td><input type="text" class="required" name="loginName" size="38" id="loginName" value="${loginName}"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" id="password" name="password" size="38" value="${password}"/></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="38" value="${password}"/>
				</td>
			</tr>
			<tr>
				<td>角色:</td>
				<td>
					<div style="word-break:break-all;width:250px; overflow:false; " class="required">
						<s:checkboxlist name="checkedRoleIds"  list="allRoleList"  listKey="id" listValue="name" theme="simple"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="A_MODIFY_USER">
						<input class="button" type="submit" value="提交"/>&nbsp;
					</security:authorize>
					<input class="button" type="button" value="返回" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</div>
</div>
</body>
</html>
