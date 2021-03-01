package dev.lansdon.data;

import java.util.Set;

import dev.lansdon.models.Editor;
import dev.lansdon.models.Genre;

public interface EditorDAO extends GenericDAO<Editor> {
	public Editor add(Editor e);
	public Editor getByUsername(String username);
	public Set<Editor> getEditorsByGenre(Genre e);
}
