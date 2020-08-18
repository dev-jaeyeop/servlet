package simpleTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "SimpleController", urlPatterns = "/simple")
public class SimpleController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Object resultObject;

        if (type == null || type.equals("greeting")) {
            resultObject = "Hello";
        } else if (type.equals("date")) {
            resultObject = new Date();
        } else {
            resultObject = "Invalid Type";
        }

        request.setAttribute("result", resultObject);
        request.getRequestDispatcher("WEB-INF/view/simpleTest/simpleView.jsp").forward(request, response);
    }
}
