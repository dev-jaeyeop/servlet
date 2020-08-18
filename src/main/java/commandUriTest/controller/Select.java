package commandUriTest.controller;

import commandUriTest.model.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Select implements Handler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", new UserDAO().select());
        return "WEB-INF/view/commandUriTest/select.jsp";
    }
}
