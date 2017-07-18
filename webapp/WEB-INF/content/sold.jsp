<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>销售记录</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
</head>

<body>
<div id="doc3">
<div id="bd" align="center">
	<div id="yui-main">
	<div class="yui-b">
	<form id="mainForm" action="sold.action" method="get">
		<div id="filter">
	 		商品名:<input type="text" name="filter_LIKES_productName" value="${param['filter_LIKES_productName']}"  size="5"/> 
	 		&nbsp;&nbsp;&nbsp;
			客户名:<input type="text" name="filter_LIKES_customerName" value="${param['filter_LIKES_customerName']}"  size="5"/>
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="搜索" onclick="search()"/>
		</div>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="content">
			<table id="contentTable">
				<tr>
					<th>商品名称</th>
					<th>客户名称</th>
					<th>售出日期</th>
					<th>数量</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${productName}</td>
						<td>${customerName}</td>
						<td>${soldDate}</td>
						<td>${number}</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<div>
			第${page.pageNo}页, 共${page.totalPages}页
			<a href="javascript:jumpPage(1)">首页</a>
			<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
			<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
			<a href="javascript:jumpPage(${page.totalPages})">末页</a>
		</div>
	</form>
	</div>
	</div>
</div>
</div>
</body>
</html>
