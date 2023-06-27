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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.nhom27.entity.NhanVienEntity;
import ptithcm.nhom27.entity.PhongBanEntity;

@Repository(value="PhongBanDao")
@Transactional
public class PhongBanDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PhongBanEntity> getListOffice() {
		List<PhongBanEntity> listOffice = new ArrayList<>();
		String hql = "FROM PhongBanEntity";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		listOffice = query.getResultList();
		return listOffice;
	}
	
	public PhongBanEntity getPhongban(String mapb) {
		Session session = sessionFactory.openSession();
		return session.get(PhongBanEntity.class, mapb);
	}
}
