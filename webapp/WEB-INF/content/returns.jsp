<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>退货信息</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script type="text/javascript">
		function returnstore() {
			alert("回库成功");
			parent.window.location.href = "saleManager.action";
		}
	</script>
</head>

<body>
<div id="doc3">
<div id="bd" align="center">
	<div id="yui-main">
	<div class="yui-b">
	<form id="mainForm" action="returns.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<td colspan="6">
						<a href="returns!input.action">新增退货</a>
					</td>
				</tr>
				<tr>
					<th>商品名称</th>
					<th>客户名称</th>
					<th>退货日期</th>
					<th>原因</th>
					<th>数量</th>
					<th>操作</th>				
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${productName}</td>
						<td>${customerName}</td>
						<td>${returnDate}</td>
						<td>${reason}</td>
						<td>${number}</td>
						<td>&nbsp;
							<s:if test="state == 0">
								<a href="returns!returnstore.action?id=${id}" onclick="returnstore();">入库</a>
							</s:if>
							<s:else>已入库</s:else>
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
