<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
	<script>
		function checkBox(name,flag) {
            $("input[name='" + name + "']").attr("checked", flag);
		}
		function optDelete() {
            $("#listForm").submit();
        }
	</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='/console/brand/add.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="get" style="padding-top:5px;">
品牌名称: <input type="text" name="name" value="${example.name}"/>
	<select name="isDisplay">
		<option value="1">是</option>
		<option value="0">否</option>
	</select>
	<script type="text/javascript">
		$("select[name='isDisplay']").val("${example.isDisplay}");
	</script>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="listForm" action="doDelete.do" method="get">
	<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
		<thead class="pn-lthead">
			<tr>
				<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
				<th>品牌ID</th>
				<th>品牌名称</th>
				<th>品牌图片</th>
				<th>品牌描述</th>
				<th>排序</th>
				<th>是否可用</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody id="brandTbody" class="pn-ltbody">

			<c:forEach items="${page.result}" var="brand">
				<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
					<td><input type="checkbox" value="${brand.id}" name="ids"/></td>
					<td align="center">${brand.id}</td>
					<td align="center">${brand.name}</td>
					<td align="center"><img width="40" height="40" src="${brand.imgUrl}"/></td>
					<td align="center">${brand.description}</td>
					<td align="center">${brand.sort}</td>
					<td align="center">
						<c:if test="${brand.isDisplay == 0}">否</c:if>
						<c:if test="${brand.isDisplay == 1}">是</c:if>
					</td>
					<td align="center">
					<a class="pn-opt" href="toEdit.do?brandId=${brand.id}">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="doDelete.do?ids=${brand.id}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</form>
<div class="page pb15">
	<span id="numSpan" class="r inb_a page_b">
		
		<c:if test="${page.pageNum==1}"><font size="2">首页</font></c:if>
		<c:if test="${page.pageNum!=1}"><a href="list.do?pageNum=1&name=${example.name}&isDisplay=${example.isDisplay}"><font size="2">首页</font></a></c:if>

		<c:if test="${page.pageNum==1}"><font size="2">上一页</font></c:if>
		<c:if test="${page.pageNum!=1}"><a href="list.do?pageNum=${page.pageNum-1}&name=${example.name}&isDisplay=${example.isDisplay}"><font size="2">上一页</font></a></c:if>

		<c:forEach begin="${page.startNum}" end="${page.endNum}" var="num">
			<c:if test="${page.pageNum == num}"><strong>${num}</strong></c:if>
			<c:if test="${page.pageNum != num}"><a href="list.do?pageNum=${num}&name=${example.name}&isDisplay=${example.isDisplay}">${num}</a></c:if>
		</c:forEach>

		<c:if test="${page.pageNum==page.pages}"><font size="2">下一页</font></c:if>
		<c:if test="${page.pageNum!=page.pages}"><a href="list.do?pageNum=${page.pageNum+1}&name=${example.name}&isDisplay=${example.isDisplay}"><font size="2">下一页</font></a></c:if>

		<c:if test="${page.pageNum==page.pages}}"><font size="2">尾页</font></c:if>
		<c:if test="${page.pageNum!=page.pages}"><a href="list.do?pageNum=${page.pages}&name=${example.name}&isDisplay=${example.isDisplay}"><font size="2">尾页</font></a></c:if>


		共<var>${page.pages}</var>页 到第<input type="text" size="3" id="PAGENO"/>页 <input type="button" onclick="javascript:window.location.href = 'list.do?pageNum=' + $('#PAGENO').val() + '&name=${example.name}&isDisplay=${example.isDisplay}'" value="确定" class="hand btn60x20" id="skip"/>
	
	</span>
</div>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete();"/></div>
</div>
</body>
</html>