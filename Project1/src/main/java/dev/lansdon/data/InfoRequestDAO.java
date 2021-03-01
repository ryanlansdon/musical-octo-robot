package dev.lansdon.data;

import dev.lansdon.models.Editor;
import dev.lansdon.models.InfoRequest;
import dev.lansdon.models.User;

public interface InfoRequestDAO extends GenericDAO<InfoRequest> {
	public InfoRequest add(InfoRequest ir);
	public void getRequestByReqUser(User u);
	public void getRequestByEditor(Editor e);
}
