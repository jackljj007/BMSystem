<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>客户信息</title>
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
	<form id="mainForm" action="customer.action" method="get">
		<div id="filter">
			<security:authorize ifAnyGranted="A_MODIFY_USER">
	 			<a href="customer!input.action">增加新客户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		</security:authorize>
	 		<input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}"  size="10"/> 
			<input type="button" value="按客户名搜索" onclick="search()"/>
		</div>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th>客户名称</th>
					<th>联系方式</th>
					<th>地址</th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${name}</td>
						<td>${phone}</td>
						<td>${address}</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="A_VIEW_USER">
							<security:authorize ifNotGranted="A_MODIFY_USER">
								<a href="customer!input.action?id=${id}">查看</a>&nbsp;
							</security:authorize>
							</security:authorize>
							<security:authorize ifAnyGranted="A_MODIFY_USER">
								<a href="customer!input.action?id=${id}">修改</a>&nbsp;
								<a href="customer!delete.action?id=${id}">删除</a>
							</security:authorize>
						</td>
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
