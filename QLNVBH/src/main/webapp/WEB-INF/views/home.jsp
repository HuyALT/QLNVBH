<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="profile">
	<div class="profile-partical">
		<div class="profile-group">
			<h1 class="profile-title">Trang cá nhân của bạn</h1>
			<div class="profile-group-avatar">
				<img class="profile-avatar"
					src="./resources/assets/avatar/avatar.png" alt=""
					onclick="document.getElementById('form-input-avatar').click();" />
				<input id="profile-input-avatar" class="profile-input-avatar"
					name="avatar" type="file" style="display: none" />
			</div>
		</div>
		<div class="profile-information">
			<div class="profile-information__detail">
				<span>Họ và tên:</span> ${nv.ho} ${nv.ten}
			</div>

			<div class="profile-information__detail">
				<span>Giới tính:</span> ${nv.gioitinh}
			</div>

			<div class="profile-information__detail">
				<span>Phòng ban:</span> ${nv.mapb.tenpb}
			</div>
			<div class="profile-information__detail">
				<span>Mô tả:</span> ${nv.mapb.mota}
			</div>

			<div class="profile-information__detail">
				<span>Email:</span> ${nv.email}
			</div>

			<div class="profile-information__detail">
				<span>Số điện thoại:</span> ${nv.sdt}
			</div>

			<div class="profile-information__detail">
				<span>Địa chỉ nơi ở:</span> ${nv.diachi}
			</div>
		</div>
	</div>
	<div class="profile-partical"
		style="padding: 0; border: none; justify-content: space-between;">
		<div class="profile-analyze" style="background-color: #FFC300;">
			<div class="profile-analyze__title">Số giờ cần làm:</div>
			<span>${neededHours} Giờ</span>
		</div>

		<div class="profile-analyze" style="background-color: #39CCCC;">
			<div class="profile-analyze__title">Số giờ đã làm:</div>
			<span>${workedHours} Giờ</span>
		</div>

		<div class="profile-analyze" style="background-color: #FF5733;">
			<div class="profile-analyze__title">Số giờ đi trễ</div>
			<span>${lateHours} Giờ</span>
		</div>

		<div class="profile-analyze" style="background-color: #8E44AD;">
			<div class="profile-analyze__title">Số tiền bị phạt trong tháng:</div>
			<span>${totalFine} VND</span>
		</div>
	</div>
</div>