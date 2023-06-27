package ptithcm.nhom27.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.nhom27.dao.XuPhatDao;
import ptithcm.nhom27.entity.CT_XuphatEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Service
public class CT_XuphatService {
	
	@Autowired
	private XuPhatDao dao;
	
	public boolean addXuphat(CT_XuphatEntity ctxp) {
		return dao.addXuphat(ctxp);
	}
	
	public List<Object[]> getListPurnishOfEmployee(String mapb) {
		return dao.getListPurnishOfEmployee(mapb);
	}
	
	public List<Object[]> getListPurnishPersonalOfEmployee(String manv) {
		return dao.getListPersonalPurnishOfEmployee(manv);
	}
}
