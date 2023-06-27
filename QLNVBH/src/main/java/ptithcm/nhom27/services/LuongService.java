package ptithcm.nhom27.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.LuongDao;
import ptithcm.nhom27.entity.LuongEntity;

@Service
@Transactional
public class LuongService {
	
	@Autowired
	LuongDao dao;
	
	public List<LuongEntity> getAllBacluong() {
		return dao.getAllBacluong();
	}
	
	public LuongEntity getBacLuong(int bacluong) {
		return dao.getbacluong(bacluong);
	}
	
	public int getLevelSalaryOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		return dao.getLevelSalaryOfEmployee_MONTH_YEAR(manv, month, year);
	}
	
	public int getSalaryOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		return dao.getSalaryOfEmployee_MONTH_YEAR(manv, month, year);
	}
	
	public int getNeededHoursOfEmployee_MONTH_YEAR(String manv, int month, int year) 
	{
		return dao.getNeededHoursOfEmployee_MONTH_YEAR(manv, month, year);
	}
	
	public int getFineOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		return dao.getFineOfEmployee_MONTH_YEAR(manv, month, year);
	}
}
