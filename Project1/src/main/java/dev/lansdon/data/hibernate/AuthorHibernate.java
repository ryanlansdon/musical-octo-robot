package dev.lansdon.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.lansdon.exceptions.NonUniqueUsernameException;
import dev.lansdon.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import dev.lansdon.utils.HibernateUtil;

import dev.lansdon.data.AuthorDAO;
import dev.lansdon.models.Author;

public class AuthorHibernate implements AuthorDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	private UserHibernate userHibernate = new UserHibernate();

	@Override
	public Author getById(Integer id) {
		Session s = hu.getSession();
		Author a = s.get(Author.class, id);
		s.close();
		return a;
	}

	@Override
	public Set<Author> getAll() {
		Session s = hu.getSession();
		String query = "FROM Author";
		Query<Author> q = s.createQuery(query, Author.class);
		List<Author> authorList = q.getResultList();
		Set<Author> authors = new HashSet<>();
		authors.addAll(authorList);
		s.close();
		return authors;
	}

	@Override
	public void update(Author t) {
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
	public void delete(Author t) {
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
	public Author add(Author a) throws NonUniqueUsernameException {
		try {
			User u = userHibernate.add(a.getUser());
			a.setUser(u);
			Session s = hu.getSession();
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.save(a);
				tx.commit();
			} catch (Exception e) {
				if (tx != null) tx.rollback();
			} finally {
				s.close();
			}
			return a;
		} catch (NonUniqueUsernameException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Author getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM Author WHERE user.username = :username";
		Query<Author> q = s.createQuery(query, Author.class);
		q.setParameter("username", username);
		Author a = q.getSingleResult();
		s.close();
		return a;
	}

}
