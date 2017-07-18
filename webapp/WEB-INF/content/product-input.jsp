<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="works.entity.entities.Supply"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商品信息</title>
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
		<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>商品信息</h2>
	</security:authorize>
	<form id="inputForm" action="product!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td colspan="2">商品名称:<input type="text" name="name" id="name" size="50" value="${name}"/></td>
			</tr>
			<tr>
				<td colspan="2">供应商名称:<input type="text" name="supplyName" id="supplyName" size="50" value="${supplyName}"/>
					<select id="supplyNames" name="supplyNames">
						<option value=""></option>
						<s:iterator value="supplys">
							<option value="<s:property  value="name"/>"><s:property  value="name"/></option>
						</s:iterator>
					</select>
					<script>
						$("#supplyNames").change(function(){
							$("#supplyName").val($(this).val());
						});
					</script>
				</td>
			</tr>
			<tr>
				<td>规格:<input type="text" id="size" name="size" size="30" value="${size}"/></td>
				<td>单位:<input type="text" id="unit" name="unit" size="30" value="${unit}"/></td>			
			</tr>
			<tr>
				<td>进货价:<input type="text" id="cost" name="cost" size="30" value="${cost}"/></td>
				<td>售价:<input type="text" id="prices" name="prices" size="30" value="${prices}"/></td>
			</tr>
			<tr>
				<td colspan="2">产地:<input type="text" id="origin" name="origin" size="80" value="${origin}"/></td>
			</tr>
			<tr>
				<td colspan="2">说明:<textarea name="describes" id="describes" >${describes}</textarea></td>
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
