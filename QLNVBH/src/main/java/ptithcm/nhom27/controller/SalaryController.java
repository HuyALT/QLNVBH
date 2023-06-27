package ptithcm.nhom27.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.Parser;
import ptithcm.nhom27.entity.CTLUONGKey;
import ptithcm.nhom27.entity.CTLuongEntity;
import ptithcm.nhom27.entity.LuongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.QuyDinhEntity;
import ptithcm.nhom27.services.CTLuongService;
import ptithcm.nhom27.services.CT_XuphatService;
import ptithcm.nhom27.services.ChamCongService;
import ptithcm.nhom27.services.LuongService;
import ptithcm.nhom27.services.NhanVienService;

@Controller
public class SalaryController {
	@Autowired
	private ChamCongService ccService; 
	
	@Autowired
	private LuongService lService; 
	
	@Autowired
	private CTLuongService ctluongService;
	
	@Autowired
	private NhanVienService nvService;
	
	@Autowired
	private CT_XuphatService xpService;
	
	@RequestMapping("admin/salary")
	public String caculatorSalary(ModelMap model) {
		model.addAttribute("manv", "");
		model.addAttribute("isSuccess", "");
		model.addAttribute("color", "green");
		return "admin/salary";
	}
	
	@RequestMapping("admin/list-salary")
	public String showListSalary() {
		return "admin/listSalary";
	}
	
	@RequestMapping("employee/list-salary")
	public String showListSalary_() {
		return "employee/listSalary";
	}
	
	@RequestMapping(value = "admin/add_salary", method = RequestMethod.POST)
	public String addSalaryOfEmployee(ModelMap model, @RequestParam("manv") String manv, 
			@RequestParam("month") String month, @RequestParam("year") String year,
			@RequestParam("supportedSalary") String supportedSalary,
			@RequestParam("totalRealSalary") String totalRealSalary)  
	{
		model.addAttribute("manv", manv);
		model.addAttribute("selectedMonth", month);
		model.addAttribute("selectedYear", year);
		
		int totalRealSalary_ = Integer.parseInt(totalRealSalary);
		int month_ = Integer.parseInt(month);
		int year_ = Integer.parseInt(year);
		int phuCap = Integer.parseInt(supportedSalary);
		int levelSalary = lService.getLevelSalaryOfEmployee_MONTH_YEAR(manv, month_, year_);

		CTLuongEntity ctLuong = new CTLuongEntity();
		NhanVienEntity nv = nvService.getNhanVien(manv);
		
		CTLUONGKey ctLuongKey = new CTLUONGKey(levelSalary, month_, year_, manv);
		
		LuongEntity luong = new LuongEntity();
		luong.setBacluong(levelSalary);
		
		ctLuong.setLuongthucnhan(totalRealSalary_);
		ctLuong.setPhucap(phuCap);
		ctLuong.setManvluong(nv);
		ctLuong.setIdctluong(ctLuongKey);
		ctLuong.setBacluongentity(luong);
			
		if(ctluongService.addCTLuong(ctLuong)) {
			model.addAttribute("isSuccess", "Tính lương nhân viên thành công!");
			model.addAttribute("color", "green");
		} else {
			model.addAttribute("isSuccess", "Lương của nhân viên ở thời điểm này đã được tính!");
			model.addAttribute("color", "red");
		}
		
		return "admin/salary";
	}
	
	@RequestMapping("admin/loadDataSalary")
	public String getDataSalary(ModelMap model, @RequestParam("manv") String manv, @RequestParam("month") String month_, @RequestParam("year") String year_) {
		model.addAttribute("manv", manv);
		model.addAttribute("isSuccess", "");
		model.addAttribute("selectedMonth", month_);
		model.addAttribute("selectedYear", year_);

		int month = Integer.parseInt(month_);
		int year = Integer.parseInt(year_);
		
		List<Object[]> informationSalary = ctluongService.getInformationCaculateSalaryOfEmployee(manv, month, year);
		
		if((int)informationSalary.get(0)[0] == 0.0) {
			model.addAttribute("isSuccess", "Nhân viên này chưa đi làm tại thời điểm này! Nên không có lương để tính");
			model.addAttribute("color", "red");
			
			return "admin/salary";
		}
		
		int tonggioquydinh = (int)informationSalary.get(0)[0];
		double tonggiolam = (double)informationSalary.get(0)[1];
		double sogioditre = (double)informationSalary.get(0)[2];
		double sogiovesom = (double)informationSalary.get(0)[3];
		double sogiokhongdilam = (double)informationSalary.get(0)[4];
		
		DecimalFormat df = new DecimalFormat("#.##");
		double soGioLamThuc = tonggiolam - sogioditre - sogiovesom - sogiokhongdilam;
		double phantramthoigianlamviec = 0;
		phantramthoigianlamviec  = Double.parseDouble(df.format((soGioLamThuc/tonggioquydinh) * 100).toString().replace(",", "."));
		
		int totalSalary = lService.getSalaryOfEmployee_MONTH_YEAR(manv, month, year);
		int fine = lService.getFineOfEmployee_MONTH_YEAR(manv, month, year);
		int supportedSalary = 0;
		int totalRealSalary = (int) (totalSalary * phantramthoigianlamviec / 100 - fine);
		
		if(phantramthoigianlamviec > 100) {
			supportedSalary = (int)((phantramthoigianlamviec - 100) * totalSalary / 100);
		}
		
		model.addAttribute("totalTime", tonggioquydinh);
		model.addAttribute("totalApointedTime", tonggiolam);
		model.addAttribute("timeLate", sogioditre);
		model.addAttribute("timeEarly", sogiovesom);
		model.addAttribute("timeNotWorking", sogiokhongdilam);
		model.addAttribute("percentageTime", phantramthoigianlamviec);
		model.addAttribute("totalSalary", totalSalary);
		model.addAttribute("fine", fine);
		model.addAttribute("supportedSalary", supportedSalary);
		model.addAttribute("totalRealSalary", totalRealSalary);
		
		return "admin/salary";
	}
	
	@ModelAttribute("listEmployee")
	public List<NhanVienEntity> getListEmployee() {
		List<NhanVienEntity> lstEmployee = nvService.getAllNhanVienOfPb();
		return lstEmployee;
	}
	
	@ModelAttribute("listMonth")
	public List<Integer> getMonth() {
		List<Integer> listMonth = new ArrayList<>();
		for(int i = 1; i<= 12; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}
	
	@ModelAttribute("listYear")
	public List<Integer> getYear() {
		LocalDate date = LocalDate.now();
		int currentYear = date.getYear();
		
		List<Integer> listYear = new ArrayList<>();
		for(int i = currentYear - 5; i<= currentYear; i++) {
			listYear.add(i);
		}
		return listYear;
	}
	
	@ModelAttribute("listSalary")
	public List<Object[]> lctluong(ModelMap model) {
		List<Object[]> listSalary = new ArrayList<>();
		
		NhanVienEntity nv = nvService.getNVLogin();
		String mapb = nv.getMapb().getMapb();
		
		listSalary = ctluongService.getListSalaryOfEmployee(mapb);
		return listSalary;
	}
	
	@ModelAttribute("listPersonalSalary")
	public List<Object[]> lctluong_(ModelMap model) {
		List<Object[]> listPersonalSalary = new ArrayList<>();
		
		NhanVienEntity nv = nvService.getNVLogin();
		String manv = nv.getManv();
		
		listPersonalSalary = ctluongService.getPersonalSalaryOfEmployee(manv);
		return listPersonalSalary;
	}
}
