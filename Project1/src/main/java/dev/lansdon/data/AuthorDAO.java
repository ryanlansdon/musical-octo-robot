package dev.lansdon.data;

import dev.lansdon.exceptions.NonUniqueUsernameException;
import dev.lansdon.models.Author;

public interface AuthorDAO extends GenericDAO<Author> {
	public Author add(Author a) throws NonUniqueUsernameException;
	public Author getByUsername(String username);
}
