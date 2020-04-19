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
        int draws = beanData.getDraws();
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
        if (boardStates.get(4).equals("X")) {
            for (int i = 0; i < 4; i++) {
                if ((boardStates.get(i).equals("X")) &&
                        (boardStates.get(i + 2 * (4 - i)).equals("X"))) {
                    gameOver = true;
                    ++player1Score;
                }
            }
        } else if (boardStates.get(4).equals("O")) {
            for (int i = 0; i < 4; i++) {
                if ((boardStates.get(i).equals("O")) &&
                        (boardStates.get(i + 2 * (4 - i)).equals("O"))) {
                    gameOver = true;
                    ++player2Score;
                }
            }
        }
        // Checking for a win involving just sides
        if (boardStates.get(0).equals("X")) {
            if ((boardStates.get(1).equals("X")) && (boardStates.get(2).equals("X"))) {
                gameOver = true;
                player1Score++;
            } else if ((boardStates.get(3).equals("X")) &&
                    (boardStates.get(6).equals("X"))) {
                gameOver = true;
                ++player1Score;
            }
        } else if (boardStates.get(8).equals("X")) {
            if ((boardStates.get(5).equals("X")) && (boardStates.get(2).equals("X"))) {
                gameOver = true;
                ++player1Score;
            } else if ((boardStates.get(7).equals("X")) &&
                    (boardStates.get(6).equals("X"))) {
                gameOver = true;
                ++player1Score;
            }
        } else if (boardStates.get(0).equals("O")) {
            if ((boardStates.get(1).equals("O")) && (boardStates.get(2).equals("O"))) {
                gameOver = true;
                ++player2Score;
            } else if ((boardStates.get(3).equals("O")) &&
                    (boardStates.get(6).equals("O"))) {
                gameOver = true;
                ++player2Score;
            }
        } else if (boardStates.get(8).equals("O")) {
            if ((boardStates.get(5).equals("O")) && (boardStates.get(2).equals("O"))) {
                gameOver = true;
                ++player2Score;
            } else if ((boardStates.get(7).equals("O")) &&
                    (boardStates.get(6).equals("O"))) {
                gameOver = true;
                ++player2Score;
            }
        }

        // Checking for Draw or Win, changing the turn string, and setting board
        if (!gameOver && !boardStates.contains("_")) {
            draws++;
            beanData.setDraws(draws);
            boardStates.clear();
            for (int i = 0; i < 9; i++) {
                boardStates.add("_");
                beanData.setBoardStates(boardStates);
            }
            if (player1Turn) {
                turnString = "Game Over! Draw! Starting new game. X's Turn";
            } else {
                turnString = "Game Over! Draw! Starting new game. O's Turn";
            }
        } else if (gameOver) {
            beanData.setPlayer1Score(player1Score);
            beanData.setPlayer2Score(player2Score);
            gameOver = false;
            boardStates.clear();
            for (int i = 0; i < 9; i++) {
                boardStates.add("_");
                beanData.setBoardStates(boardStates);
            }
            if (player1Turn) {
                turnString = "Game Over! O Wins! Starting new game. X's Turn";
            } else {
                turnString = "Game Over! X Wins! Starting new game. O's Turn";
            }
        } else if (player1Turn) {
            turnString = "X's Turn";
        } else {
            turnString = "O's Turn";
        }

        // Setting bean data
        beanData.setTurnString(turnString);
        beanData.setBoardStates(boardStates);
        beanData.setGameOver(gameOver);

        request.setAttribute("beanData", beanData);

        // Forwarding to the JSP.
        String url = "/teamMVC.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        // Changing the turn
        player1Turn = !player1Turn;
        beanData.setPlayer1Turn(player1Turn);
    }
}