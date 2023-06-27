<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link
	href="<c:url value='/resources/assets/css/table_salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="salary-searchField" placeholder="Tìm kiếm thông tin lương bổng"
			onkeyup="searchTable('salary-searchField', 'table-salary')" />
	</div>
	<div class="table-container__heading">
		<h1 class="table-title">Quản lý lương bổng</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-salary">
			<tr class="table-row">
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading">Họ</th>
				<th class="table-heading">Tên</th>
				<th class="table-heading">Tháng</th>
				<th class="table-heading">Năm</th>
				<th class="table-heading">Bậc lương</th>
				<th class="table-heading">Lương cơ bản</th>
				<th class="table-heading">Phụ cấp</th>
				<th class="table-heading">Số tiền phạt</th>
				<th class="table-heading">Tổng lương</th>
			</tr>
			<c:forEach var="o" items="${listSalary}">
				<tr class="table-row">
					<td class="table-data">${o[0]}</td>
					<td class="table-data">${o[1]}</td>
					<td class="table-data">${o[2]}</td>
					<td class="table-data">${o[3]}</td>
					<td class="table-data">${o[4]}</td>
					<td class="table-data">${o[5]}</td>
					<td class="table-data currency">${o[6]}</td>
					<td class="table-data currency">${o[7]}</td>
					<td class="table-data currency">${o[8]}</td>
					<td class="table-data currency">${o[9]}</td>
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
	sortTable("table-salary");
</script>