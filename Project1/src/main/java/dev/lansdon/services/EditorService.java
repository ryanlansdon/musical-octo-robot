package dev.lansdon.services;

import dev.lansdon.models.Editor;
import dev.lansdon.models.Genre;

import java.util.Set;

public interface EditorService {
    public Editor getEditorById(Integer id);
    public Editor getEditorByUsername(String username);
    public Set<Editor> getEditorsByGenre(Genre g);
    public void update(Editor e);
}
