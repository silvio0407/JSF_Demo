package br.com.devmedia.gestaoacademica.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.devmedia.gestaoacademica.model.Docente;

public class DocenteDAOImpl implements DocenteDAO {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void adicionarDocente(Docente docente) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(docente);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null)
				trns.rollback();
		} finally {
			session.flush();
			session.close();
		}
	}

	public List<Docente> listarDocentes() {
		return getSessionFactory().openSession().createCriteria(Docente.class).list();
	}

	public void excluirDocente(Docente docente) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(docente);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null)
				trns.rollback();
		} finally {
			session.flush();
			session.close();
		}
	}

}
