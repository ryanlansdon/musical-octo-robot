package dev.lansdon.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorDelegate implements FrontControllerDelegate {
    private ObjectMapper om = new ObjectMapper();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
