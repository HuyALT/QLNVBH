<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/table_employee.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="employee-searchField" placeholder="Tìm kiếm thông tin nhân viên"
			onkeyup="searchTable('employee-searchField', 'table-employee')" />
	</div>

	<h1 class="table-title">Bảng danh sách nhân viên</h1>

	<div class="table-wrap">
		<table class="table table-employee" id="table-employee">
			<tr class="table-row">
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading">Họ và tên</th>
				<th class="table-heading">Giới tính</th>
				<th class="table-heading">Email</th>
				<th class="table-heading">Số điện thoại</th>
				<th class="table-heading">Trạng thái</th>
				<th class="table-heading">Sửa</th>
			</tr>
			<c:forEach var="nvs" items="${employees}">
				<tr class="table-row">
					<td class="table-data">${nvs.manv }</td>
					<td class="table-data">${nvs.ho} ${nvs.ten }</td>
					<td class="table-data">${nvs.gioitinh }</td>
					<td class="table-data">${nvs.email }</td>
					<td class="table-data">${nvs.sdt }</td>
					<td class="table-data"><span name="${nvs.matk.trangthai}"
						class="table-status">${nvs.matk.trangthai?'Còn làm': 'Đã nghỉ'}</span>
					</td>
					<td class="table-data"><a class="table-edit-btn"
						href="admin/edit?manv=${nvs.manv}&cmnd=${nvs.cmnd}&ho=${nvs.ho}&ten=${nvs.ten}&ngaysinh=${nvs.ngaysinh}&gioitinh=${nvs.gioitinh}&diachi=${nvs.diachi}&email=${nvs.email}&sdt=${nvs.sdt}&mapb=${nvs.mapb.mapb}&trangthai=${nvs.matk.trangthai}"
						class="table-edit-btn"> <i
							class="table-edit-search bi bi-pencil-square"></i>
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<div id="modal" style="display: ${display ? none : display}">
	<form action="admin/confirm_edit" id="modal__form" class="modal__form"
		method="POST">
		<div class="form-heading"
			style="padding: 0; min-width: 333px; border: 1px solid #ccc; border-radius: 10px">
			<h2 class="form-title">Chỉnh sửa thông tin</h2>
			<img class="form-avatar" src="./resources/assets/avatar/avatar.png"
				alt=""
				onclick="document.getElementById('form-input-avatar').click();" />
			<input id="form-input-avatar" class="form-input-avatar" name="avatar"
				type="file" style="display: none" />
		</div>
		<div class="form-content">
			<span class="text--success" id="text--error"
				style="display: ${success == ''? 'none': 'inline-block'}; height: auto">${success}</span>
			<div class="form-group">
				<label class="form-label" for="">Mã nhân viên</label> <input
					name="manv" class="form-input" type="text" value="${manv}" disabled />
			</div>
			<div class="form-group">
				<label class="form-label" for="">CMND</label> <input name="cmnd"
					class="form-input number" type="text" value="${cmnd}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Họ</label> <input name="ho"
					class="form-input" type="text" value="${ho}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Tên</label> <input name="ten"
					class="form-input" type="text" value="${ten}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Ngày sinh</label> <input
					name="ngaysinh" class="form-input" type="date" value="${ngaysinh}" />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Giới tính</label> <select
					name="gioitinh" class="form-input">
					<option value="male" ${gioitinh == 'Nam'?'selected':''}>Nam</option>
					<option value="female" ${gioitinh == 'Nữ'?'selected':''}>Nữ</option>
				</select>
			</div>
			<div class="form-group" style="width: 100%">
				<label class="form-label" for="">Địa chỉ</label> <input
					name="diachi" class="form-input" type="text" value="${diachi}" />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">SĐT</label> <input name="sdt"
					class="form-input number" type="text" value="${sdt}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Email</label> <input name="email"
					class="form-input email" type="email" value="${email}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group width-full">
				<label class="form-label" for="">Phòng ban</label> <select
					name="phongban" class="form-input">
					<c:forEach var="pb" items="${listOffice}">
						<option value="${pb.mapb}" ${mapb == pb.mapb?'selected':''}>${pb.tenpb}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group-btn">
				<button class="form-btn-submit" type="submit">Thay đổi</button>
				<button class="form-btn-exit">
					<a href="admin/list-employee" class="form-btn-exit__link">Thoát</a>
				</button>
			</div>
		</div>
	</form>
</div>

<script
	src="<c:url value='/resources/assets/javascript/searchTool.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/validationEdit.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/changeAvatarForm.js' />"></script>
<script>
	sortTable("table-employee");
	
	$(".table-edit-btn").click(function(e) {
		e.preventDefault();

		let url = $(this).attr("href");

		$.ajax({
			url : url,
			success : function(data) {
				$("main").html(data);
			},
		});
	});

	$(".form-btn-exit__link").click(function(e) {
		e.preventDefault();

		let url = $(this).attr("href");

		$.ajax({
			url : url,
			success : function(data) {
				$("main").html(data);
			},
		});
	});
	
	$("#modal__form").on("submit", function (e) {
		  e.preventDefault();
		  if(confirm("Bạn có muốn thay đổi thông tin của nhân viên này không?")) {
			  let url = $(this).attr("action");
			  let avatar = $(".form-input-avatar[name='avatar']").val();
			  let manv = $(".form-input[name='manv']").val();
			  let cmnd = $(".form-input[name='cmnd']").val();
			  let ho = $(".form-input[name='ho']").val();
			  let ten = $(".form-input[name='ten']").val();
			  let ngaysinh = $(".form-input[name='ngaysinh']").val();
			  let gioitinh = $(".form-input[name='gioitinh']").val();
			  let diachi = $(".form-input[name='diachi']").val();
			  let phongban = $(".form-input[name='phongban']").val();
			  let sdt = $(".form-input[name='sdt']").val();
			  let email = $(".form-input[name='email']").val();
			  
			  let date = new Date(); // Get the current date
			  let formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth()+1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
			  let currentYear = date.getFullYear();
			 if (currentYear - Number(ngaysinh.substring(0, 4)) <= 18) {
				  console.log(formattedDate);
				  
				  $('.form-content').animate({
				      scrollTop: $('#text--error').offset().top
				    }, 500);
				  $(".text--success").text("Ngày sinh nhân viên phải đủ 18 tuổi!");
				  
					$(".text--success").css({
						'display': 'inline-block',
						'backgroundColor': 'red'
					})
			  }
			  else {
				  $.ajax({
				    type: "POST",
				    data: {
				      //avatar,
				      manv,
				      cmnd,
				      ho,
				      ten,
				      ngaysinh,
				      gioitinh,
				      diachi,
				      phongban,
				      sdt,
				      email
				    },
				    url: url,
				    beforeSend: function() {
						$('main').append(`
					<div class="loader-container" style="display: none">
			<div class="load-6">
				<div class="letter-holder">
					<div class="l-1 letter">L</div>
					<div class="l-2 letter">o</div>
					<div class="l-3 letter">a</div>
					<div class="l-4 letter">d</div>
					<div class="l-5 letter">i</div>
					<div class="l-6 letter">n</div>
					<div class="l-7 letter">g</div>
					<div class="l-8 letter">.</div>
					<div class="l-9 letter">.</div>
					<div class="l-10 letter">.</div>
				</div>
			</div>
		</div>
					`)
						$('.loader-container').css({
							'display': 'flex'
						});

					},
					complete: function() {
						$('.loader-container').css({
							'display': 'none'
						});
					},
				    success: function (data) {
				      $("main").html(data);
				    },
				  })
			  }
		  }
		});
</script>
