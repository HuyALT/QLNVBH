<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/table_salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="purnish-searchField" placeholder="Tìm kiếm thông tin xử phạt"
			onkeyup="searchTable('purnish-searchField', 'table-purnish')" />
	</div>
	<div class="table-container__heading">
		<h1 class="table-title">Quản lý xử phạt</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-purnish">
			<tr class="table-row">
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading">Họ</th>
				<th class="table-heading">Tên</th>
				<th class="table-heading">Tên vi phạm</th>
				<th class="table-heading">Ngày xử phạt</th>
				<th class="table-heading">Số tiền phạt</th>
			</tr>
			<c:forEach var="o" items="${listPurnish}">
				<tr class="table-row">
					<td class="table-data">${o[0]}</td>
					<td class="table-data">${o[1]}</td>
					<td class="table-data">${o[2]}</td>
					<td class="table-data">${o[3]}</td>
					<td class="table-data">${o[4]}</td>
					<td class="table-data currency">${o[5]}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<script
	src="<c:url value='/resources/assets/javascript/searchTool.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/common/formatCurrency.js' />"></script>	

<script>
	sortTable("table-purnish");
</script>