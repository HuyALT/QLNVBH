package ptithcm.nhom27.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.CTLuongDao;
import ptithcm.nhom27.entity.CTLuongEntity;

@Service
@Transactional
public class CTLuongService {

	@Autowired
	private CTLuongDao dao;
	
	
	public List<CTLuongEntity> getAllCTluongPB(String mapb){
		return dao.getAllCTluongPB(mapb);
	}
	
	public List<CTLuongEntity> getAllCTluongMANV(String manv) {
		return dao.getAllCTluongMANV(manv);
	}
	
	public boolean addCTLuong(CTLuongEntity ctLuong) {
		return dao.addCTLuong(ctLuong);
	}
	
	public List<Object[]> getListSalaryOfEmployee(String mapb) {
		return dao.getListSalaryOfEmployee(mapb);
	}
	
	public List<Object[]> getPersonalSalaryOfEmployee(String manv) {
		return dao.getListPersonalSalaryOfEmployee(manv);
	}
	
	public List<Object[]> getInformationCaculateSalaryOfEmployee(String manv, int month, int year) {
		return dao.getInformationCaculateSalaryOfEmployee(manv, month, year);
	}
}	
