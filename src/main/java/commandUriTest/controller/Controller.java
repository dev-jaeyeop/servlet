package commandUriTest.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

@WebServlet(name = "Controller", urlPatterns = "*.do")
public class Controller extends HttpServlet {
    private HashMap<String, Handler> commandHandlerHashMap;

    @Override
    public void init() {
        commandHandlerHashMap = new HashMap<>();
        String propertiesPath = "properties.commandUriTest";
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesPath); // 프로퍼티 파일 읽어옴
        Enumeration<String> resourceBundleKeys = resourceBundle.getKeys(); // 키를 Enumeration 타입으로 가져옴 ex) /select.do

        while (resourceBundleKeys.hasMoreElements()) { // 키가 존재하면
            try {
                String uri = resourceBundleKeys.nextElement(); // 키를 가져옴
                String controllerPath = resourceBundle.getString(uri); // 키에 해당하는 값을 가져옴
                Class controllerClass = Class.forName(controllerPath); // 키에 해당하는 값
                Handler commandHandler = (Handler) controllerClass.newInstance();
                commandHandlerHashMap.put(uri, commandHandler); // uri: "/select.do", commandHandler: "commandUriTest.controller.Select"
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commandCheck(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commandCheck(request, response);
    }

    public void commandCheck(HttpServletRequest request, HttpServletResponse response) {
        Handler commandHandler = commandHandlerHashMap.get(request.getRequestURI()); // commandHandler: "commandUriTest.controller.Select"

        if (commandHandler == null) {
            commandHandler = new Select();
        }

        try {
            String viewPage = commandHandler.process(request, response); // return: "WEB-INF/view/commandUriTest/select.jsp"
            request.getRequestDispatcher(viewPage).forward(request, response); // request, response ????????????????????????
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
