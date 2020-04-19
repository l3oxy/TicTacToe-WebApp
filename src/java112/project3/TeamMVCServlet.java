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
    private final String X = "X";  // Icon for Player1.
    private final String O = "O";  // Icon for Player2.
    private final String EMPTY = "_";  // Icon for an unused spot.
    private final int boardHeight = 3;  // I.e. quantity of board rows.
    private final int boardWidth = 3;  // I.e. quantity of board columns
    // NOTE: if boardHeight and boardWidth are not identical, the diagonal victory detection will be non-perfect.
    private final int quantityOfCells = boardHeight * boardWidth;

    public void init() throws ServletException {
        beanData = new JavaBean();
    }

    /**
     * Returns a new game board.
     * @return A new game board.
     */
    private List<String> getNewBoardStates() {
        List<String> boardStates = new ArrayList<String>();

        for (int i = 0; i < quantityOfCells; ++i) {
            boardStates.add(EMPTY);
        }

        return boardStates;
    }

    /**
     * Inspects a vertical column on the board for a victory.
     * @param boardStates the board.
     * @param column the index on the board where the column to inspect begins (the top part of the column).
     * @return the icon the won, or EMPTY if nobody won on the specified row.
     */
    private String checkColumn(List<String> boardStates, int column) {
        // Get the icon at the top of the column.
        String topCellOfColumn = boardStates.get(column);

        // If the top cell of this column is null or empty, then go to the next column.
        if (topCellOfColumn != null && !topCellOfColumn.equals(EMPTY)) {

            // Regarding the top-most cell in this column, check if all other cells in this column are equal to said top-most cell.
            for (int currentCell = (column + boardWidth); currentCell < quantityOfCells; currentCell += boardWidth) {
                // Compare this cell to the top cell of this column.
                if (!boardStates.get(currentCell).equals(topCellOfColumn)) {
                    return EMPTY;
                }
            }

            // All cells in this column are identical and not empty. Thus a victory has been detected.
            return topCellOfColumn;
        }

        // No victory detected in this column.
        return EMPTY;
    }

    /**
     * Checks if a vertical win has occurred.
     * @param boardStates the current game board.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForVerticalWins(List<String> boardStates) {
        String rowVictor;

        // For each column ...
        for (int column = 0; column < boardWidth; ++column) {

            rowVictor = checkColumn(boardStates, column);

            if (!rowVictor.equals(EMPTY)) {
                return rowVictor;
            }
        }

        // No vertical victories detected.
        return EMPTY;
    }

    /**
     * Inspects a horizontal row on the board for a victory.
     * @param boardStates the board.
     * @param row the index on the board where the row to inspect begins (the left-most part).
     * @return the icon that won, or EMPTY if nobody won on the specified row.
     */
    private String checkRow(List<String> boardStates, int row) {
        // In the current row, in the cell that is most left, the icon inside it.
        String leftMostCellInRow = boardStates.get(row);

        // In the current row, if left-most cell is not empty/null, then this row could have a victory.
        if (leftMostCellInRow != null && !leftMostCellInRow.equals(EMPTY)) {

            // Regarding the left-most cell in this row, check if all other cells in this row are equal to said left-most cell.
            for (int cellInRow = row; cellInRow < (row + boardWidth); ++cellInRow) {
                if (!boardStates.get(cellInRow).equals(leftMostCellInRow)) {
                    // Cells are not identical.
                    return EMPTY;
                }
            }

            // All cells in this row are identical and not empty. Thus a victory has been detected.
            return leftMostCellInRow;
        }

        // No victory detected in this row.
        return EMPTY;
    }

    /**
     * Checks if a horizontal win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForHorizontalWins(List<String> boardStates) {
        String rowVictor;

        // For each row...
        for (int row = 0; row < (quantityOfCells - 1); row += boardWidth) {

            rowVictor = checkRow(boardStates, row);

            if (!rowVictor.equals(EMPTY)) {
                return rowVictor;
            }
        }

        // No horizontal victories detected.
        return EMPTY;
    }

    /**
     * Checks if a diagonal top-left to bottom-right win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForDiagonalTopLeftToBottomRightWin(List<String> boardStates) {
        String topMostCellInDiagonal = boardStates.get(0);  // In the current diagonal, in the cell that is nearest the top/start, the icon inside it.

        // If the top-most cell is empty, then this diagonal does not have a victory.
        if (topMostCellInDiagonal == null || topMostCellInDiagonal.equals(EMPTY)) {
            return EMPTY;
        }

        // Regarding the top-most cell in this diagonal, check if all other cells in this diagonal are equal to said top-most cell.
        for (int currentCell = (boardWidth + 1); currentCell < quantityOfCells; currentCell += (boardWidth + 1)) {
            if (!boardStates.get(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return EMPTY;
            }
        }

        // Diagonal victory detected.
        return topMostCellInDiagonal;
    }

    /**
     * Checks if a diagonal top-right to bottom-left win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForDiagonalTopRightToBottomLeftWin(List<String> boardStates) {
        String topMostCellInDiagonal = boardStates.get(boardWidth - 1);  // In the current diagonal, in the cell that is nearest the top/start, the icon inside it.

        // If the top-most cell is empty, then this diagonal does not have a victory.
        if (topMostCellInDiagonal == null || topMostCellInDiagonal.equals(EMPTY)) {
            return EMPTY;
        }

        // Regarding the top-most cell in this diagonal, check if all other cells in this diagonal are equal to said top-most cell.
        for (int currentCell = ((boardWidth * 2) - 2); currentCell < (quantityOfCells - 1); currentCell += (boardWidth - 1)) {
            if (!boardStates.get(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return EMPTY;
            }
        }

        // Diagonal victory detected.
        return topMostCellInDiagonal;
    }

    /**
     * Checks if a diagonal win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForDiagonalWins(List<String> boardStates) {
        String resultOfFirstDiagonalVictoryCheck;

        resultOfFirstDiagonalVictoryCheck = checkForDiagonalTopLeftToBottomRightWin(boardStates);
        if (!resultOfFirstDiagonalVictoryCheck.equals(EMPTY)) {
            return resultOfFirstDiagonalVictoryCheck;
        } else {
            return checkForDiagonalTopRightToBottomLeftWin(boardStates);
        }
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
        if (0 <= cellSelected && cellSelected <= 8) {  // if spot is valid
            if (boardStates.get(cellSelected).equals(EMPTY)) {  // If spot is not already taken
                if (player1Turn) {
                    boardStates.set(cellSelected, O);
                } else {
                    boardStates.set(cellSelected, X);
                }
            } else {
                player1Turn = !player1Turn;
            }
        }


        // Checking for a win
        // Checking for a vertical win
        switch (checkForVerticalWins(boardStates)) {
            case X:
                gameOver = true;
                ++player1Score;
                break;
            case O:
                gameOver = true;
                ++player2Score;
                break;
            case EMPTY:
                break;
        }
        // Checking for a horizontal win
        switch (checkForHorizontalWins(boardStates)) {
            case X:
                gameOver = true;
                ++player1Score;
                break;
            case O:
                gameOver = true;
                ++player2Score;
                break;
            case EMPTY:
                break;
        }
        // Checking for a diagonal win
        switch (checkForDiagonalWins(boardStates)) {
            case X:
                gameOver = true;
                ++player1Score;
                break;
            case O:
                gameOver = true;
                ++player2Score;
                break;
            case EMPTY:
                break;
        }

        // Checking for Draw or Win, changing the turn string, and setting board
        if (!gameOver && !boardStates.contains("_")) {
            ++draws;
            beanData.setDraws(draws);
            boardStates = getNewBoardStates();
            if (player1Turn) {
                turnString = "Game Over! Draw! Starting new game. " + X + "'s Turn";
            } else {
                turnString = "Game Over! Draw! Starting new game. " + O + "'s Turn";
            }
        } else if (gameOver) {
            beanData.setPlayer1Score(player1Score);
            beanData.setPlayer2Score(player2Score);
            gameOver = false;
            boardStates = getNewBoardStates();
            if (player1Turn) {
                turnString = "Game Over! " + O + " Wins! Starting new game. " + X + "'s Turn";
            } else {
                turnString = "Game Over! " + X + " Wins! Starting new game. " + O + "'s Turn";
            }
        } else if (player1Turn) {
            turnString = X + "'s Turn";
        } else {
            turnString = O + "'s Turn";
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