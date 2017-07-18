<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/jquery/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript">
	$(function() {
		$( "#accordion" ).accordion({
			collapsible: true
		});
	});
	
	function formatNowDate(){
		var today = new Date();
		var year = today.getYear();
		var month = today.getMonth() + 1;
		var day = today.getDate();
		var hrs=today.getHours();
		var min=today.getMinutes();
		var sec=today.getSeconds();
		nowtime.innerHTML= year+ "年" + month + "月" + day + "日&nbsp;&nbsp;&nbsp" + hrs + ":" + min + ":" + sec + "&nbsp;&nbsp;&nbsp;星期"+"日一二三四五六".charAt(today.getDay());
	 }
	setInterval("formatNowDate()",1000);
</script>
</head>

<body>
	<div>
	<table height="580" width="1000" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr height="50">
        	<td colspan='2'  width="100%" align="center">
				<p style="font-size:180%; font-weight:bold;">商业管理系统</p>
			</td>
        </tr>
		<tr height="430">
			<td width="15%" align="center">
				<table border="0" cellpadding="0" cellspacing="0" align="center" height="80" width="100%">
					<tr>
						<td>
						<div id="accordion">
							<a href="#"></a>
							<div></div>
							欢迎您：<a href="#"><%=SpringSecurityUtils.getCurrentUserName()%><br/></a>
							<div>
								<a onclick="alert('请联系管理员进行修改');">修改信息</a>&nbsp;
								<!-- <p style="font-size:80%;">修改个人信息请联系管理员</p><br/> -->
								<a href="${ctx}/j_spring_security_logout">退出登录</a>
							</div>
						</div>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" align="center" height="350" width="100%">
					<tr height="50"></tr>
                	<tr>
                    	<td><a href="storeManager.action" target="show">仓库管理</a></td>
                    </tr>
                    <tr>
                    	<td><a href="saleManager.action" target="show">销售管理</a></td>
                    </tr>
                    <tr>
                    	<td><a href="statistics.action" target="show">销售统计</a></td>
                    </tr>
                    <tr>
                    	<td>
                    		<security:authorize ifAnyGranted="A_MODIFY_USER">
                    			<a href="managerment.action" target="show">管理员操作</a>
                    		</security:authorize>
                    	</td>
                    </tr>
                    <tr>
                    	<td>
                    		<security:authorize ifAnyGranted="A_VIEW_ROLE">
                    		<security:authorize ifNotGranted="A_MODIFY_USER">
                    			<a href="managerment.action" target="show">查看信息</a>
                    		</security:authorize>
                    		</security:authorize>
                    	</td>
                    </tr>
                    		
                    <tr height="100"></tr>
           		</table>
			</td>
       	  	<td width=85% align="center">
       	  		<iframe id="show"  name="show" src="" scrolling="auto" frameBorder="0" height="430" width="100%" align="top"></iframe>
       	  		<!-- 北京理工大学珠海学院&nbsp;&nbsp;&nbsp;08级软件2班&nbsp;&nbsp;&nbsp;080202021035&nbsp;&nbsp;&nbsp;廖嘉杰 -->
       	  	</td>
		</tr>
		<tr height="30">
        	<td colspan='2'  align="right">
					<div id="nowtime" style="font-size: 13px; padding-right:10px;"></div>
            </td>
        </tr>
		<tr height="30">
			<td  align="center" colspan='2' style="font-size: 13px;">
				本作品仅供研究SpringSide+jQuery技术使用<br />
				Copyright &copy; 2012
			</td>
		</tr>    
	</table>
	</div>
</body>
</html>