<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="works.entity.entities.Supply"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>退货信息</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	
	<script src="${ctx}/jquery/ui/jquery.ui.core.js"></script>
	<script src="${ctx}/jquery/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/jquery/ui/jquery.ui.datepicker.js"></script>
	<script src="${ctx}/jquery/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
	<link rel="stylesheet" href="${ctx}/jquery/themes/cupertino/jquery.ui.all.css"/>
	<script type="text/javascript">
		$(function() {
			$.datepicker.setDefaults( $.datepicker.regional[ "" ] );
			$( "#returnDate" ).datepicker( $.datepicker.regional[ "zh-CN" ] );
		});
	</script>
</head>

<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>退货信息</h2>
	<form id="inputForm" action="returns!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td colspan="2">商品名称:<input type="text" name="product" id="product" size="50" value="${productName}"/>
					<select id="productName" name="productName">
						<option value=""></option>
						<s:iterator value="products">
							<option value="<s:property  value="name"/>"><s:property  value="name"/></option>
						</s:iterator>
					</select>
					<script>
						$("#productName").change(function(){
							$("#product").val($(this).val());
						});
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">客户名称:<input type="text" name="customer" id="customer" size="50" value="${customerName}"/>
					<select id="customerName" name="customerName">
						<option value=""></option>
						<s:iterator value="customers">
							<option value="<s:property  value="name"/>"><s:property  value="name"/></option>
						</s:iterator>
					</select>
					<script>
						$("#customerName").change(function(){
							$("#customer").val($(this).val());
						});
					</script>
				</td>
			</tr>
			<tr>
				<td>退货日期:<input type="text" id="returnDate" name="returnDate" size="30" value="${returnDate}"/></td>
				<td>数量:<input type="text" id="number" name="number" size="30" value="${number}"/></td>
			</tr>
			<tr>
				<td colspan="2">说明:<textarea name="reason" id="reason" >${reason}</textarea></td>
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
