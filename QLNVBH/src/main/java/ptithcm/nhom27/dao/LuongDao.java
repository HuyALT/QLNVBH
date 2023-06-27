package ptithcm.nhom27.dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.LuongEntity;

@Repository(value = "LuongDao")
@Transactional
public class LuongDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<LuongEntity> getAllBacluong(){
		Session session = sessionFactory.openSession();
		String hql = "From LuongEntity";
		Query query = session.createQuery(hql);
		return query.getResultList();
	}
	
	public LuongEntity getbacluong(int bacluong) {
		List<LuongEntity> list = getAllBacluong();
		return list.stream().filter(o->o.getBacluong()==bacluong).findFirst().orElse(null);
	}
	
	public int getLevelSalaryOfEmployee_MONTH_YEAR(String manv, int month, int year) {
		int levelSalary = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetSalaryOfEmployee_MONTH_YEAR";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@LEVEL", Integer.class, 
						    ParameterMode.OUT)
					.registerStoredProcedureParameter("@SALARY", Integer.class, 
						    ParameterMode.OUT)
					.registerStoredProcedureParameter("@HOURS", Integer.class, 
						    ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
			
		    if(query.getOutputParameterValue("@LEVEL") != null) {
		    	levelSalary = (Integer) query.getOutputParameterValue("@LEVEL");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return levelSalary;
	}
	
	public int getSalaryOfEmployee_MONTH_YEAR(String manv, int month, int year) 
	{
		int totalSalary = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetSalaryOfEmployee_MONTH_YEAR";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@LEVEL", Integer.class, 
						     ParameterMode.OUT)
					.registerStoredProcedureParameter("@SALARY", Integer.class, 
				     ParameterMode.OUT)
					.registerStoredProcedureParameter("@HOURS", Integer.class, 
						     ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
			
		    if(query.getOutputParameterValue("@SALARY") != null) {
		    	totalSalary = (Integer) query.getOutputParameterValue("@SALARY");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return totalSalary;
	}
	
	public int getNeededHoursOfEmployee_MONTH_YEAR(String manv, int month, int year) 
	{
		int neededHours = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetSalaryOfEmployee_MONTH_YEAR";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, 
					        ParameterMode.IN)
					.registerStoredProcedureParameter("@LEVEL", Integer.class, 
						     ParameterMode.OUT)
					.registerStoredProcedureParameter("@SALARY", Integer.class, 
				     ParameterMode.OUT)
					.registerStoredProcedureParameter("@HOURS", Integer.class, 
						     ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
			
		    if(query.getOutputParameterValue("@HOURS") != null) {
		    	neededHours = (Integer) query.getOutputParameterValue("@HOURS");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return neededHours;
	}
	
	public int getFineOfEmployee_MONTH_YEAR(String manv, int month, int year) 
	{
		int fine = 0;
		Session session = sessionFactory.openSession();
		String procedureName = "SP_GetFineOfEmployee_MONTH_YEAR";
		try {
			
			StoredProcedureQuery query = session.createStoredProcedureCall(procedureName)
					.registerStoredProcedureParameter("@MANV", String.class, 
			        ParameterMode.IN)
					.registerStoredProcedureParameter("@MONTH", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("@YEAR", Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter("@RESULT", Integer.class, 
				     ParameterMode.OUT)
				    .setParameter("@MANV", manv).setParameter("@MONTH", month).setParameter("@YEAR", year);
			
			query.execute();
				    
			if(query.getOutputParameterValue("@RESULT") != null) {
		    	fine = (Integer) query.getOutputParameterValue("@RESULT");
		    }
		    
		} catch (HibernateException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		session.close();
		
		return fine;
	}
}
