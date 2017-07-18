<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>供应商信息</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
</head>

<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<security:authorize ifAnyGranted="A_MODIFY_USER">
		<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>供应商信息</h2>
	</security:authorize>
	<form id="inputForm" action="supply!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td>客户名称:</td>
				<td><input type="text" name="name" id="name" size="50" value="${name}"/></td>
			</tr>
			<tr>
				<td>联系方式:</td>
				<td><input type="text" id="phone" name="phone" size="50" value="${phone}"/></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="text" id="address" name="address" size="80" value="${address}"/></td>
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
