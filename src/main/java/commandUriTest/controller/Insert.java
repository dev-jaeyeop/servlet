package commandUriTest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert implements Handler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/view/commandUriTest/insert.jsp";
    }
}
