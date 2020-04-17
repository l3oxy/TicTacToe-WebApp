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

        List<String> boardStates = beanData.getBoardStates();
        boolean player1Turn = beanData.isPlayer1Turn();
        boolean gameOver = beanData.isGameOver();
        int player1Score = beanData.getPlayer1Score();
        int player2Score = beanData.getPlayer2Score();
        int cellSelected = -1;
        String turnString;

        // Getting the selected cell
        if (request.getParameter("cell") != null) {
            cellSelected = Integer.parseInt(request.getParameter("cell"));
        }

        // Changing the board state based on the selected cell
        for (int i = 0; i < 9; i++) {
            if (cellSelected == i) {
                if (player1Turn) {
                    boardStates.set(i, "O");
                } else {
                    boardStates.set(i, "X");
                }
            }
        }

        // Checking for a win
        // Checking for a win involving the center
        if (boardStates.get(4) == "X") {
            for (int i = 0; i < 4; i++) {
                if ((boardStates.get(i) == "X") &&
                        (boardStates.get(i + 2 * (4 - i)) == "X")) {
                    gameOver = true;
                    player1Score++;
                }
            }
        } else if (boardStates.get(4) == "O") {
            for (int i = 0; i < 4; i++) {
                if ((boardStates.get(i) == "O") &&
                        (boardStates.get(i + 2 * (4 - i)) == "O")) {
                    gameOver = true;
                    player2Score++;
                }
            }
        }
        // Checking for a win involving just sides
        if (boardStates.get(0) == "X") {
            if ((boardStates.get(1) == "X") && (boardStates.get(2) == "X")) {
                gameOver = true;
                player1Score++;
            } else if ((boardStates.get(3) == "X") &&
                    (boardStates.get(6) == "X")) {
                gameOver = true;
                player1Score++;
            }
        } else if (boardStates.get(8) == "X") {
            if ((boardStates.get(5) == "X") && (boardStates.get(2) == "X")) {
                gameOver = true;
                player1Score++;
            } else if ((boardStates.get(7) == "X") &&
                    (boardStates.get(6) == "X")) {
                gameOver = true;
                player1Score++;
            }
        } else if (boardStates.get(0) == "O") {
            if ((boardStates.get(1) == "O") && (boardStates.get(2) == "O")) {
                gameOver = true;
                player2Score++;
            } else if ((boardStates.get(3) == "O") &&
                    (boardStates.get(6) == "O")) {
                gameOver = true;
                player2Score++;
            }
        } else if (boardStates.get(8) == "O") {
            if ((boardStates.get(5) == "O") && (boardStates.get(2) == "O")) {
                gameOver = true;
                player2Score++;
            } else if ((boardStates.get(7) == "O") &&
                    (boardStates.get(6) == "O")) {
                gameOver = true;
                player2Score++;
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
        beanData.setGameOver(gameOver);
        beanData.setPlayer1Score(player1Score);
        beanData.setPlayer2Score(player2Score);

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