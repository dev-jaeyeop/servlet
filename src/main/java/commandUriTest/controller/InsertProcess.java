package commandUriTest.controller;

import commandUriTest.model.dao.UserDAO;
import commandUriTest.model.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertProcess implements Handler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        new UserDAO().insert(user);

        request.setAttribute("users", new UserDAO().select());
        return "WEB-INF/view/commandUriTest/select.jsp";
    }
}
