package java112.project3;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * This is a servlet that gets and changes data depending on the values in the
 * JavaBean for the Team MVC project.
 */
@WebServlet(
        name = "teamMVC",
        urlPatterns = { "/tic-tac-toe" }
)
public class TeamMVCServlet extends HttpServlet {

    private JavaBean beanData;

    public void init() throws ServletException {
        beanData = new JavaBean();
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<Integer, Integer> boardStates = beanData.getBoardStates();
        boolean player1Turn = beanData.isPlayer1Turn();
        int cellSelected = 0;
        String turnString;

        // Getting the selected cell
        if (request.getParameter("cell") != null) {
            cellSelected = Integer.parseInt(request.getParameter("cell"));
        }

        // Changing the board state based on the selected cell
        for (int i = 1; i < 10; i++) {
            if (cellSelected == i) {
                if (player1Turn) {
                    boardStates.replace(i, 1);
                } else {
                    boardStates.replace(i, 2);
                }
            }
        }

        // Changing the turn string
        if (player1Turn) {
            turnString = "X's Turn";
        } else {
            turnString = "O's Turn";
        }

        // Setting bean data
        beanData.setTurnString(turnString);
        beanData.setBoardStates(boardStates);

        request.setAttribute("beanData", beanData);

        String url = "/teamMVC.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        // Changing the turn
        if (player1Turn) {
            player1Turn = false;
        } else {
            player1Turn = true;
        }
        beanData.setPlayer1Turn(player1Turn);
    }
}