package java112.project3;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author
 */
@WebServlet(
        name = "teamMVC",
        urlPatterns = { "/team-mvc" }
)
public class TeamMVCServlet extends HttpServlet {

    /**
     *
     * Handles HTTP GET requests.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JavaBean aBean = new JavaBean();

        aBean.setData("I'm special!");

        request.setAttribute("myCoolBean", aBean);

        String url = "/teamMVC.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}