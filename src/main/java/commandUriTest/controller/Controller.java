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
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesPath); // properties 파일을 읽어옴 key 와 value 형태 ex) /select.do : commandUriTest.controller.Select
        Enumeration<String> resourceBundleKeys = resourceBundle.getKeys(); // key 를 가져옴 ex) /select.do

        while (resourceBundleKeys.hasMoreElements()) { // key 가 존재하면
            try {
                String uri = resourceBundleKeys.nextElement(); // key 를 가져옴 ex) /select.do
                String controllerPath = resourceBundle.getString(uri); // resourceBundle 에서 key 에 해당하는 value 를 가져옴
//                Class controllerClass = Class.forName(controllerPath); // key 에 해당하는 value 의 이름을 가진 class 선언 -> newInstance()시 기본 생성자 호출 (new) -> class 초기화
//                Handler commandHandler = (Handler) controllerClass.newInstance(); // commandHandler = new controllerPath();
                Handler commandHandler = (Handler) Class.forName(controllerPath).newInstance();
                commandHandlerHashMap.put(uri, commandHandler); // String(uri): "/select.do", Handler(commandHandler): "commandUriTest.controller.Select"
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
        Handler commandHandler = commandHandlerHashMap.get(request.getRequestURI()); // commandHandler: "commandUriTest.controller.Select" -> 존재하지 않으면 null;
        String viewPage;

        if (commandHandler == null) { // 찿고자하는 uri 가 없을 때
            commandHandler = new Select(); // viewPage: "WEB-INF/view/commandUriTest/select.jsp"
        }

        try {
            if (request.getRequestURI().equals("/insertProcess.do")) {
                commandHandler.process(request, response);
                response.sendRedirect("/select.do");
            } else {
                viewPage = commandHandler.process(request, response); // viewPage: "WEB-INF/view/commandUriTest/select.jsp"
                request.getRequestDispatcher(viewPage).forward(request, response); // request, response 로 들어온 정보를 공유하기 위해 forward
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//         request.getRequestDispatcher(viewPage).forward(request, response): 새로운 페이지에서 request, response 객체를 공유 (요청 정보 전달)
//         response.sendRedirect(viewPage): 새로운 페이지에서 request, response 객체를 새로 생성
    }
}
