package ptithcm.nhom27.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.CTLuongEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.LuongService;
import ptithcm.nhom27.services.NhanVienService;

@Repository(value = "CTLuongDao")
@Transactional
public class CTLuongDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	NhanVienService nvService;
	
	@Autowired
	LuongService luService;
	
	private final int defaultBacluong = 1;
	
	public List<CTLuongEntity> getAllCTLuong() {
		Session session = sessionFactory.openSession();
		String hql = "FROM CTLuongEntity";
		Query query = session.createQuery(hql);
		List<CTLuongEntity> list = query.getResultList();
		session.close();
		return list;
	}
	
	public List<CTLuongEntity> getAllCTLuongE() {
		List<CTLuongEntity> list = getAllCTLuong();
		return list.stream().filter(o->o.getManvluong().getMatk().getChucvu().getMacv().equals("E")).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getAllCTluongMANV(String manv) {
		NhanVienEntity nv = nvService.getNhanVien(manv);
		List<CTLuongEntity> list = nv.getDsctl().stream().collect(Collectors.toList());
		return list;
	}
	
	public List<CTLuongEntity> getAllCTluongPB(String mapb) {
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getManvluong().getMapb().getMapb().equals(mapb)).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongTheoThangNam(int thang, int nam){
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongPhongBan(String mapb, int thang, int nam) {
		List<CTLuongEntity> list = getAllCTluongPB(mapb);
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public List<CTLuongEntity> getLuongTheoNam(int nam) {
		List<CTLuongEntity> list = getAllCTLuongE();
		return list.stream().filter(o->o.getIdctluong().getNam()==nam).collect(Collectors.toList());
	}
	
	public CTLuongEntity getCTLuongNV(String manv,int thang, int nam) {
		List<CTLuongEntity> list = getAllCTluongMANV(manv);
		return list.stream().filter(o->o.getIdctluong().getThang()==thang&&o.getIdctluong().getNam()==nam).findFirst().orElse(null);
	}
	
	public boolean addCTLuong(CTLuongEntity ctLuong) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctLuong);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public List<Object[]> getListSalaryOfEmployee(String mapb) {
		List<Object[]> listSalary = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetListSalaryOfEmployee";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MAPB", String.class, 
			        ParameterMode.IN)
				    .setParameter("@MAPB", mapb);
			
			query.execute();
			
			listSalary = query.getResultList();
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return listSalary; 
	}
	
	public List<Object[]> getListPersonalSalaryOfEmployee(String manv) {
		List<Object[]> listPersonalSalary = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetListPersonalSalaryOfEmployee";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
				    .setParameter("@MANV", manv);
			
			query.execute();
			
			listPersonalSalary = query.getResultList();
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return listPersonalSalary; 
	}
	
	public List<Object[]> getInformationCaculateSalaryOfEmployee(String manv, int month, int year) {
		List<Object[]> informationSalary = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetInformationCaculateSalaryOfEmployee_MONTH_YEAR";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, 
			        ParameterMode.IN)
					.setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
			
			informationSalary = query.getResultList();
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return informationSalary; 
	}
}
