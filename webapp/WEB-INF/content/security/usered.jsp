<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 帐号管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/table.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="/common/menu.jsp"%>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<form id="mainForm" action="user.action" method="get">
<div id="filter">
	你好,<%=SpringSecurityUtils.getCurrentUserName()%>.&nbsp;&nbsp;
 	登录名: <input type="text" name="filter_EQS_loginName" value="${param['filter_EQS_loginName']}"  size="9"/> 
          姓名或Email: <input type="text" name="filter_LIKES_name_OR_email" value="${param['filter_LIKES_name_OR_email']}" size="9"/>
	<input type="button" value="搜索" onclick="search()"/>
</div> 
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />
<div id="listContent">
<table id="listTable">
	<tr>
		<th><a href="javascript:sort('loginName','asc')"><b>登录名</b></a></th>
		<th><a href="javascript:sort('name','asc')"><b>姓名</b></a></th>
		<th><a href="javascript:sort('email','asc')"><b>电邮</b></a></th>
		<th><b>角色</b></th>
		<th><b>操作</b></th>
	</tr>

	<s:iterator value="page.result">
		<tr>
			<td>${loginName}&nbsp;</td>
			<td>${name}&nbsp;</td>
			<td>${email}&nbsp;</td>
			<td>${roleNames}&nbsp;</td>
			<td>&nbsp; 
				<security:authorize ifAnyGranted="A_VIEW_USER">
				<security:authorize ifNotGranted="A_MODIFY_USER">
					<a href="user!input.action?id=${id}">查看</a>&nbsp; 
				</security:authorize>
				</security:authorize>

				<security:authorize ifAnyGranted="A_MODIFY_USER">
					<a href="user!input.action?id=${id}">修改</a>&nbsp; 
					<a href="user!delete.action?id=${id}">删除</a>
				</security:authorize>
			</td>
		</tr>
	</s:iterator>
</table>
</div>

<div id="footer">
	第${page.pageNo}页, 共${page.totalPages}页 
	<a href="javascript:jumpPage(1)">首页</a>
	<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
	<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
	<a href="javascript:jumpPage(${page.totalPages})">末页</a>
	
	<security:authorize ifAnyGranted="A_MODIFY_USER">
		<a href="user!input.action">增加新用户</a>
	</security:authorize>
</div>
</form>
</body>
</html>
