function loadDataSalary() {
	let manv = $(".form-input[name='manv']").val();
	let month = $(".form-input[name='month']").val();
	let year = $(".form-input[name='year']").val();

	if (manv != "0" && month != "0" && year != "0") {
		let url = "admin/loadDataSalary";
		$.ajax({
			url: url,
			data: {
				manv,
				month,
				year
			},
			success: function(data) {
				$('main').html(data);
			}
		});
	}
	else {
		$(".form-input[name='gioquydinh']").val(0);
		$(".form-input[name='giolamviec']").val(0);
		$(".form-input[name='ditre']").val(0);
		$(".form-input[name='vesom']").val(0);
		$(".form-input[name='khonglam']").val(0);
		$(".form-input[name='phantramgiolam']").val(0);
		$(".form-input[name='tienluong']").val(0);
		$(".form-input[name='tienphat']").val(0);
		$(".form-input[name='tientrocap']").val(0);
		$(".form-input[name='tienluongthuc']").val(0);
	}
}

$("#form-salary").on("submit",function(e) {
		e.preventDefault();
		let manv = $(".form-input[name='manv']").val();
		let month = $(".form-input[name='month']").val();
		let year = $(".form-input[name='year']").val();
		
		if(manv == "0") {
			alert("Bạn phải chọn nhân viên cần chấm công!");
			return;
		}
		
		if (month == "0") {
			alert("Bạn phải chọn tháng cần chấm công!");
			return;
		}
		
		if (year == "0") {
			alert("Bạn phải chọn năm cần chấm công!");
			return;
		}
		
		if(confirm("Bạn thực sự muốn tính lương của nhân viên này?")) {
			let url = $(this).attr("action");
			let supportedSalary = $(".form-input[name='tientrocap']").val();
			let totalRealSalary = $(".form-input[name='tienluongthuc']").val();
		
			$.ajax({
				type: "POST",
			    data: {
					manv,
					supportedSalary,
			        totalRealSalary,
			        month,
			        year
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
				success: function(data) {
					$("main").html(data);
				}
		});
	}
});