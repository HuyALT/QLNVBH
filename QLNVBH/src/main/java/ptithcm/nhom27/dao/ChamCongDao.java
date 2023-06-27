package ptithcm.nhom27.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import ptithcm.nhom27.entity.CT_LLVEntity;
import ptithcm.nhom27.entity.ChamCongEntity;
import ptithcm.nhom27.entity.LichLamViecEntity;
import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.services.CT_LLVService;
import ptithcm.nhom27.services.LichLamViecService;
import ptithcm.nhom27.services.NhanVienService;

@Repository("ChamCongDao")
@Transactional
public class ChamCongDao {
	@Autowired
	NhanVienService nvService;

	@Autowired
	LichLamViecService llvService;

	@Autowired
	CT_LLVService ctllvService;

	@Autowired
	SessionFactory sessionFactory;

	public List<ChamCongEntity> getAllChamCong() {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity";
		Query query = session.createQuery(hql);
		List<ChamCongEntity> list = query.getResultList();
		return list;
	}

	public List<ChamCongEntity> getAllChamCongPB() {
		List<ChamCongEntity> list = getAllChamCong();
		NhanVienEntity nv = nvService.getNVLogin();
		return list.stream().filter(o -> o.getManv().getMapb().getMapb().equals(nv.getMapb().getMapb()))
				.collect(Collectors.toList());
	}

	public List<ChamCongEntity> getAllChamCongPersonal() {
		NhanVienEntity nv = nvService.getNVLogin();
		List<ChamCongEntity> list = getAllChamCongPB();
		return list.stream().filter(o -> o.getManv().getManv().equals(nv.getManv())).collect(Collectors.toList());
	}

	public int getMaxID() {
		List<ChamCongEntity> list = getAllChamCong();
		return list.size();
	}

	public ChamCongEntity getCheckin(String manv) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity WHERE manv.manv=:manv AND ngaycc=:date AND giora = null";
		Query query = session.createQuery(hql);
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		query.setParameter("manv", manv);
		query.setParameter("date", date);
		return (ChamCongEntity) query.uniqueResult();
	}

	public ChamCongEntity getCCtheoLLV(LichLamViecEntity llv, String manv) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ChamCongEntity WHERE idcm.id=:id AND manv.manv=:manv";
		Query query = session.createQuery(hql);
		query.setParameter("id", llv.getId());
		query.setParameter("manv", manv);
		return (ChamCongEntity) query.uniqueResult();
	}

	public String createStatus(String statusCheckin, String statusCheckout) {
		if (statusCheckin.equals("Đi trễ") && statusCheckout.equals("Về sớm")) {
			return statusCheckin + ", " + statusCheckout;
		} else if (statusCheckin.equals("Đi trễ")) {
			return statusCheckin;
		} else if (statusCheckout.equals("Về sớm")) {
			return statusCheckout;
		} else {
			return statusCheckin;
		}
	}

	public List<ChamCongEntity> getDsChamCongThangHT(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> listcc = nv.getBangchamcong().stream().collect(Collectors.toList());
		List<ChamCongEntity> lccMonth = listcc.stream()
				.filter(o -> o.getNgaycc().toLocalDate().getMonthValue() == thang && o.getNgaycc().toLocalDate().getYear() == nam).collect(Collectors.toList());
		return lccMonth;
	}

	public List<ChamCongEntity> dsDiTreVesomThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Đi trễ, Về sớm")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsDiTreThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Đi trễ")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsVesomThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Về sớm")).collect(Collectors.toList());
	}

	public List<ChamCongEntity> dsKhongDiLamThang(NhanVienEntity nv, int thang, int nam) {
		List<ChamCongEntity> lccMonth = getDsChamCongThangHT(nv, thang, nam);
		return lccMonth.stream().filter(o -> o.getTrangthai().equals("Không đi làm")).collect(Collectors.toList());
	}

	public String createStatusCheckin(Time checkin, LichLamViecEntity llv) {
		if (checkin.getTime() - llv.getGiobatdau().getTime() > 0)
			return "Đi trễ";
		return "Đúng giờ";
	}

	public String createStatusCheckout(Time checkout, LichLamViecEntity llv) {
		if (checkout.before(llv.getGioketthuc()))
			return "Về sớm";
		return "Đúng giờ";
	}

	public boolean nvcheckin(Time checkin, LichLamViecEntity llv, NhanVienEntity nv) {
		ChamCongEntity cc = new ChamCongEntity();
		cc.setId(getMaxID() + 1);
		cc.setNgaycc(llv.getNgaylam());
		cc.setGiovao(checkin);
		cc.setIdcm(llv);
		cc.setManv(nv);
		cc.setTrangthai(createStatusCheckin(checkin, llv));
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(cc);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

	}

	public boolean nvcheckout(Time checkout, String manv) {
		ChamCongEntity cc = getCheckin(manv);
		cc.setGiora(checkout);
		String statuscheckout = createStatusCheckout(checkout, cc.getIdcm());
		cc.setTrangthai(createStatus(cc.getTrangthai(), statuscheckout));
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(cc);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public void autoCheckChamCong() {
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		LocalDate localdate = date.toLocalDate();
		localdate = localdate.minusDays(1);
		date = Date.valueOf(localdate);

		List<CT_LLVEntity> lctllv = ctllvService.getCTLLVofDate(date);

		for (int i = 0; i < lctllv.size(); i++) {
			ChamCongEntity cc = getCCtheoLLV(lctllv.get(i).getId(), lctllv.get(i).getIdctllv().getManv());
			if (cc == null) {
				Time t = Time.valueOf("00:00:00");
				Session session = sessionFactory.openSession();
				Transaction tran = session.beginTransaction();
				ChamCongEntity ccnew = new ChamCongEntity();
				ccnew.setId(getMaxID() + 1);
				ccnew.setGiora(t);
				ccnew.setGiovao(t);
				ccnew.setIdcm(lctllv.get(i).getId());
				ccnew.setManv(lctllv.get(i).getManvlv());
				ccnew.setNgaycc(date);
				ccnew.setTrangthai("Không đi làm");
				try {
					session.save(ccnew);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				} finally {
					session.close();
				}
			} else if (cc.getGiora() == null) {
				Session session = sessionFactory.openSession();
				Transaction tran = session.beginTransaction();
				Time t = Time.valueOf("00:00:00");
				cc.setGiora(t);
				cc.setTrangthai("Chưa checkout");
				try {
					session.update(cc);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				} finally {
					session.close();
				}
			}
		}

	}
	
	public Float getApointedHoursOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		float apointedHours = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetTotalApointedHoursOfEmployee_MONTH_YEAR";
		try {
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter("@HOURS", Float.class, 
				     ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
				    
			if(query.getOutputParameterValue("@HOURS") != null) {
		    	apointedHours = (Float) query.getOutputParameterValue("@HOURS");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return apointedHours;
	}
	
	public Float getWorkedHoursOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		float workedHours = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetTotalWorkedHoursOfEmployee_MONTH_YEAR";
		try {
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter("@RESULT", Float.class, 
				     ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
				    
			if(query.getOutputParameterValue("@RESULT") != null) {
		    	workedHours = (Float) query.getOutputParameterValue("@RESULT");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return workedHours; 
	}

}
