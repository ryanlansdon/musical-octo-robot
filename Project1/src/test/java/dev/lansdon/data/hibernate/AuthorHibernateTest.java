package dev.lansdon.data.hibernate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.lansdon.models.Author;

import java.util.Set;

public class AuthorHibernateTest {
	public static AuthorHibernate authorDAO;
	
	@BeforeAll
	public static void setup() {
		authorDAO = new AuthorHibernate();
	}
	
	@Test
	public void testGetAll() {
		Set<Author> authors = authorDAO.getAll();

		for (Author a : authors) {
			assertNotNull(a);
		}
	}

	@Test
	public void testGetById() {
		Author a = authorDAO.getById(1);
		assertEquals(1,a.getId());
	}
	@Test
	public void testGetNoUser() {
		Author a = authorDAO.getById(10);
		assertNull(a);
	}

	@Test
	public void testUpdate() {
		Author a = authorDAO.getById(1);
		a.setPoints(10);
		authorDAO.update(a);
		assertEquals(10,authorDAO.getById(1).getPoints());
	}

//	@Test
//	public void testAddAuthor() {
//		Author a = new Author();
//		User u = new User();
//		u.setUsername("ryan2");
//		u.setPassword("pass");
//		u.setFirstName("Ryan");
//		u.setLastName("Blansdon");
//		a.setUser(u);
//		a = authorDAO.add(a);
//		assertEquals(a.getUser().getUsername(),authorDAO.getById(a.getId()).getUser().getUsername());
//	}
	
	@Test
	public void getByUsernameTest() {
		Author a = authorDAO.getByUsername("ryan");
		assertNotNull(a);
	}
}
