package dev.lansdon.data;

import dev.lansdon.data.hibernate.*;

public class DAOFactory {
	public static AuthorDAO getAuthorDAO() {
		return new AuthorHibernate();
	}
	
	public static EditorDAO getEditorDAO() {
		return new EditorHibernate();
	}
	
	public static InfoRequestDAO getInfoRequestDAO() {
		return null;
	}
	
	public static PitchDAO getPitchDAO() {
		return new PitchHibernate();
	}

	public static GenreDAO getGenreDAO() { return new GenreHibernate(); }

	public static StoryTypeDAO getStoryTypeDAO() { return new StoryTypeHibernate(); }
}
