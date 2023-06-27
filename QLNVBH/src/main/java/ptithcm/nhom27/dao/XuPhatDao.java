package ptithcm.nhom27.dao;

import java.time.ZoneId;
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

import ptithcm.nhom27.entity.CT_XuphatEntity;
import ptithcm.nhom27.entity.NhanVienEntity;

@Repository(value = "XuPhatDao")
@Transactional
public class XuPhatDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addXuphat(CT_XuphatEntity ctxp) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctxp);
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
	
	public List<Object[]> getListPurnishOfEmployee(String mapb) {
		List<Object[]> listPurnish = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetListPurnishOfEmployee";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MAPB", String.class, 
			        ParameterMode.IN)
				    .setParameter("@MAPB", mapb);
			
			query.execute();
			
			listPurnish = query.getResultList();
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return listPurnish;
	}
	
	public List<Object[]> getListPersonalPurnishOfEmployee(String manv) {
		List<Object[]> listPersonalPurnish = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetListPersonalPurnishOfEmployee";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
				    .setParameter("@MANV", manv);
			
			query.execute();
			
			listPersonalPurnish = query.getResultList();
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return listPersonalPurnish; 
	}
}
