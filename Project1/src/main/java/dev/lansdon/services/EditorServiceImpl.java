package dev.lansdon.services;

import dev.lansdon.data.DAOFactory;
import dev.lansdon.data.EditorDAO;
import dev.lansdon.models.Editor;
import dev.lansdon.models.Genre;

import java.util.Set;

public class EditorServiceImpl implements EditorService {
    EditorDAO editorDAO;

    public EditorServiceImpl() {
        editorDAO = DAOFactory.getEditorDAO();
    }

    @Override
    public Editor getEditorById(Integer id) {
        return editorDAO.getById(id);
    }

    @Override
    public Editor getEditorByUsername(String username) {
        return editorDAO.getByUsername(username);
    }

    @Override
    public Set<Editor> getEditorsByGenre(Genre g) {
        return editorDAO.getEditorsByGenre(g);
    }

    @Override
    public void update(Editor e) {
        editorDAO.update(e);
    }
}
