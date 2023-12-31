<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<base href="${pageContext.servletContext.contextPath}/">

<header>
	<div class="header__menu-show-sidebar">
		<i class="bi bi-arrow bi-arrow-left"></i>
	</div>
	<div class="header__container">
		<div class="header__noti">
			<i class="bi bi-bell"></i>
			<div class="header__noti-details">
				<span class="header__noti-title">Thông báo</span>
				<ul class="header__noti-list">
				<c:forEach var="tb" items="${listNotifications}">
						<li class="header__noti-item">${tb[1]}</li>
				</c:forEach> 
				</ul>
			</div>
		</div>

		<div class="header__account-wrapped">
          <div class="header__name-account">${user_header}</div>
          <span class="header__name-office">${user_office}</span>
        </div>
        
		<div class="header__avatar-info">
			<img src="./resources/assets/avatar/avatar.png" alt=""
				class="header__avatar" width="40px" height="40px" />
			<div class="header__avatar-show_details">
				<ul class="header__list-details">
					<li class="header__item-detail"><a href="home"
						class="header__detail-link">Hồ sơ cá nhân</a></li>
					<li class="header__item-detail"><a href="notification"
						class="header__detail-link">Gửi thư quản lý</a></li>
					<li class="header__item-detail"><a href="reset-password"
						class="header__detail-link"> Cài lại mật khẩu </a></li>
					<li class="header__item-detail">
						<form action="<c:url value="/j_spring_security_logout" />"
							method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> <input
								style="outline: none; border: none; background: transparent; cursor: pointer;"
								type="submit" value="Đăng xuất" class="header__detail-link-logout" />
						</form>
					</li>
				</ul>
			</div>
		</div>
	</div>
</header>