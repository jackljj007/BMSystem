<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>首页</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>
<body style="padding:100px;">
<div>
	<table align="center" border="0">
		<tr>
        	<td colspan='2' align="center">
				<p style="font-size:200%; font-weight:bold;">商业管理系统</p>
			</td>
        </tr>
		<tr><td>
		<%
			if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
		%> 
		<div class="error"> 登录失败，请重试.</div> 
		<%
			}
		%>
		<form id="loginForm" action="${ctx}/j_spring_security_check" method="post" style="align:center">
			<table class="noborder">
				<tr>
					<td><label>用户名:</label></td>
					<td><input type='text' id='j_username' name='j_username' class="required"
						<s:if test="not empty param.error">
							value='<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if> />
					</td>
				</tr>
				<tr>
					<td><label>密码:</label></td>
					<td><input type='password' id='j_password' name='j_password' class="required"/></td>
				</tr>
				<tr>
					<td colspan='2' align="center">
						<input value="登录" type="submit" class="button"/>
						&nbsp;&nbsp;&nbsp;
						<input value="重置" type="reset" class="button"/>
					</td>
				</tr>
			</table>
		</form>
		</td></tr>
	</table>
</div>
</body>
</html>

