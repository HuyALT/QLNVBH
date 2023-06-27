<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="form-container" style="background-color: #fff;">
	<form action="admin/add_salary" id="form-salary"
		class="form form-salary" method="POST">
		<span class="text--success"
			style="display: ${isSuccess == ''? 'none': 'inline-block'}; background-color: ${color}">${isSuccess}</span>
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h1 class="form-heading">Tính lương cho nhân viên</h1>
			<div class="form-group">
				<label for="" class="form-label">Tháng:</label> 
				<select name="month" class="form-input" onchange="loadDataSalary()">
					<option value="0">-- Lựa chọn tháng --</option>
					<c:forEach var="month" items = "${listMonth}">
						<option ${ month == selectedMonth ? 'selected' : ''} value="${month}">Tháng ${month}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Năm:</label> 
				<select name="year" class="form-input" onchange="loadDataSalary()">
					<option value="0">-- Lựa chọn năm --</option>
					<c:forEach var="year" items = "${listYear}">
						<option ${ year == selectedYear ? 'selected' : ''} value="${year}">Năm ${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group width-full">
				<label for="" class="form-label">Nhân viên</label> 
				<select name="manv" class="form-input" onchange="loadDataSalary()">
					<option value="0">-- Lựa chọn mã nhân viên để tính lương --</option>
					<c:forEach var="employee" items = "${listEmployee}">
						<option ${ employee.manv == manv ? 'selected' : ''} value="${employee.manv}">${employee.manv} - ${employee.ho} ${employee.ten}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h2 class="form-heading">Dữ kiện chấm công:</h2>
			<div class="form-group">
				<label for="" class="form-label">Tổng số giờ theo quy định của hợp đồng (GIỜ):</label> <input
					name="gioquydinh" class="form-input" value="${totalTime}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Tổng số giờ được giao (GIỜ):</label> <input
					name="giolamviec" class="form-input" value="${totalApointedTime}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ đi trễ (GIỜ):</label> <input
					name="ditre" class="form-input" value="${timeLate}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ về sớm (GIỜ):</label> <input
					name="vesom" class="form-input" value="${timeEarly}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ không đi làm (GIỜ):</label> <input
					name="khonglam" class="form-input" value="${timeNotWorking}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Phần trăm giờ làm (%):</label> <input
					name="phantramgiolam" class="form-input" value="${percentageTime}" readonly/>
			</div>
		</div>
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h2 class="form-heading">Kết quả:</h2>
			<div class="form-group">
				<label for="" class="form-label">Tiền lương:</label> <input
					name="tienluong" class="form-input" value="${totalSalary}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Tiền phạt:</label> <input
					name="tienphat" type="text" class="form-input" value="${fine}" readonly />
			</div>
			<div class="form-group">
				<label for="tientrocap" class="form-label">Tiền trợ cấp (Tăng ca):</label> <input
					name="tientrocap" class="form-input" value="${supportedSalary}" readonly />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Tiền lương thực nhận:</label> <input
					name="tienluongthuc" class="form-input" value="${totalRealSalary}" readonly />
			</div>
		</div>
		<div class="form-button-setting" style="margin-top: 20px">
			<button class="form-button submit" type="submit">Tính lương</button>
		</div>
	</form>
</div>

<script src="<c:url value='/resources/assets/javascript/salary.js' />"></script>