package dev.lansdon.services;

import dev.lansdon.data.AuthorDAO;
import dev.lansdon.data.DAOFactory;
import dev.lansdon.exceptions.NonUniqueUsernameException;
import dev.lansdon.models.Author;

public class AuthorServiceImpl implements AuthorService {
	AuthorDAO authorDAO;
	
	public AuthorServiceImpl() {
		authorDAO = DAOFactory.getAuthorDAO();
	}

	@Override
	public Integer addAuthor(Author a) throws NonUniqueUsernameException {
		return authorDAO.add(a).getId();
	}

	@Override
	public void updateAuthor(Author a) {
		authorDAO.update(a);
	}

	@Override
	public Author getAuthorById(Integer id) {
		return authorDAO.getById(id);
	}

	@Override
	public Author getAuthorByUsername(String username) {
		return authorDAO.getByUsername(username);
	}

}
