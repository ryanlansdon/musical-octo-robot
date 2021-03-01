package dev.lansdon.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dev.lansdon.data.EditorDAO;
import dev.lansdon.models.Editor;
import dev.lansdon.models.Genre;
import dev.lansdon.utils.HibernateUtil;

public class EditorHibernate implements EditorDAO {
	HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Editor getById(Integer id) {
		Session s = hu.getSession();
		Editor e = s.get(Editor.class, id);
		s.close();
		return e;
	}

	@Override
	public Set<Editor> getAll() {
		Session s = hu.getSession();
		String query = "FROM Editor";
		Query<Editor> q = s.createQuery(query, Editor.class);
		List<Editor> editorList = q.getResultList();
		Set<Editor> editors = new HashSet<>();
		editors.addAll(editorList);
		s.close();
		return editors;
	}

	@Override
	public void update(Editor t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Editor t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Editor add(Editor e) {
		return null;
	}

	@Override
	public Editor getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM Editor WHERE user.username = :username";
		Query<Editor> q = s.createQuery(query, Editor.class);
		q.setParameter("username", username);
		Editor e = q.getSingleResult();
		s.close();
		return e;
	}

	@Override
	public Set<Editor> getEditorsByGenre(Genre e) {
		Session s = hu.getSession();
		String query = "FROM Editor WHERE genre.name = :genre";
		Query<Editor> q = s.createQuery(query, Editor.class);
		q.setParameter("genre", e.getName());
		List<Editor> editorList = q.getResultList();
		Set<Editor> editors = new HashSet<>();
		editors.addAll(editorList);
		s.close();
		return editors;
	}
	
	
}
