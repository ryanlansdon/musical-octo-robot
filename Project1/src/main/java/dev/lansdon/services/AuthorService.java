package dev.lansdon.services;

import dev.lansdon.exceptions.NonUniqueUsernameException;
import dev.lansdon.models.Author;

public interface AuthorService {
	public Integer addAuthor(Author a) throws NonUniqueUsernameException;
	public void updateAuthor(Author a);
	public Author getAuthorById(Integer id);
	public Author getAuthorByUsername(String username);
}
