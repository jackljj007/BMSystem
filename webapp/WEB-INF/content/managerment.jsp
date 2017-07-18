<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员操作</title>
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.tabs.js"></script>

<link rel="stylesheet" href="${ctx}/css/demos.css"/>
<link rel="stylesheet" href="${ctx}/jquery/themes/cupertino/jquery.ui.all.css"/>
<script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
</head>
<body>
	<div class="demo">
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">用户信息</a></li>
				<li><a href="#tabs-2">商品信息</a></li>
				<li><a href="#tabs-3">供应商信息</a></li>
				<li><a href="#tabs-4">客户信息</a></li>
			</ul>
			<div id="tabs-1">
				<iframe src="security/user.action" scrolling="auto" frameBorder="0" height="280" width="100%" align="top"></iframe>
			</div>
			<div id="tabs-2">
				<iframe src="product.action" scrolling="auto" frameBorder="0" height="280" width="100%" align="top"></iframe>
			</div>
			<div id="tabs-3">
				<iframe src="supply.action" scrolling="auto" frameBorder="0" height="280" width="100%" align="top"></iframe>
			</div>
			<div id="tabs-4">
				<iframe src="customer.action" scrolling="auto" frameBorder="0" height="280" width="100%" align="top"></iframe>
			</div>
		</div>
	</div>
</body>
</html>