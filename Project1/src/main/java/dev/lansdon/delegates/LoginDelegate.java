package dev.lansdon.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.lansdon.exceptions.NonUniqueUsernameException;
import dev.lansdon.models.Author;
import dev.lansdon.models.Editor;
import dev.lansdon.services.*;
import org.apache.log4j.Logger;

/*
 * Endpoints:
 *  /user/login - (POST) log user in
 *  			- (DELETE) log out user
 *  /user - (POST) register new user
 *  /user/:id - (GET) get user by id
 *  		  - (PUT) update user
 *  		  - (DELETE) deletes user
 */

public class LoginDelegate implements FrontControllerDelegate {
	private AuthorService aServ = new AuthorServiceImpl();
	private EditorServiceImpl eServ = new EditorServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	Logger log = Logger.getLogger(LoginDelegate.class);
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				// register user
				Author a = (Author) om.readValue(req.getInputStream(), Author.class);
				try {
					a.setId(aServ.addAuthor(a));
				} catch (NonUniqueUsernameException e) {
					res.sendError(HttpServletResponse.SC_CONFLICT, "Username already exists");
				}
				if (a.getId() == 0) {
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					res.getWriter().write(om.writeValueAsString(a));
					res.setStatus(HttpServletResponse.SC_CREATED);
				}
			} else {
				res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else if (path.contains("login")) {
			if ("POST".equals(req.getMethod()))
				logIn(req, res);
			else if ("DELETE".equals(req.getMethod()))
				req.getSession().invalidate();
			else
				res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
	}

	private void logIn(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		String role = req.getParameter("role");
		
		if ("author".equals(role)) {
			Author a = aServ.getAuthorByUsername(username);
			
			if (a != null) {
				if (a.getUser().getPassword().equals(password)) {
					req.getSession().setAttribute("user", a);
					res.getWriter().write(om.writeValueAsString(a));
				} else {
					res.sendError(400, "Incorrect password");
				}
			} else {
				res.sendError(404, "No user found with that username");
			}
		} else {
			Editor e = eServ.getEditorByUsername(username);

			if (e != null) {
				if (e.getUser().getPassword().equals(password)) {
					req.getSession().setAttribute("user", e);
					res.getWriter().write(om.writeValueAsString(e));
				} else {
					res.sendError(400, "Incorrect password");
				}
			} else {
				res.sendError(404, "No user found with that username");
			}
		}
	}
}
