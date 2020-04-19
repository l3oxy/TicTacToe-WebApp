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

    private JavaBean bean;

    /**
     * Sets up the game.
     * @throws ServletException Servlet Exception.
     */
    @Override
    public void init() throws ServletException {
        bean = new JavaBean();
        bean.setBoardHeight(3);
        bean.setBoardWidth(3);
        bean.setIconPlayer1("X");
        bean.setIconPlayer2("O");
        bean.setIconEmpty("_");
    }

    /**
     * Returns a new game board.
     * @return A new game board.
     */
    private List<String> getNewBoardStates() {
        List<String> boardStates = new ArrayList<String>();

        for (int i = 0; i < bean.getCellQuantity(); ++i) {
            boardStates.add(bean.getIconEmpty());
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
        if (topCellOfColumn != null && !topCellOfColumn.equals(bean.getIconEmpty())) {

            // Regarding the top-most cell in this column, check if all other cells in this column are equal to said top-most cell.
            for (int currentCell = (column + bean.getBoardWidth()); currentCell < bean.getCellQuantity(); currentCell += bean.getBoardWidth()) {
                // Compare this cell to the top cell of this column.
                if (!boardStates.get(currentCell).equals(topCellOfColumn)) {
                    return bean.getIconEmpty();
                }
            }

            // All cells in this column are identical and not empty. Thus a victory has been detected.
            return topCellOfColumn;
        }

        // No victory detected in this column.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a vertical win has occurred.
     * @param boardStates the current game board.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForVerticalWins(List<String> boardStates) {
        String rowVictor;

        // For each column ...
        for (int column = 0; column < bean.getBoardWidth(); ++column) {

            rowVictor = checkColumn(boardStates, column);

            if (!rowVictor.equals(bean.getIconEmpty())) {
                return rowVictor;
            }
        }

        // No vertical victories detected.
        return bean.getIconEmpty();
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
        if (leftMostCellInRow != null && !leftMostCellInRow.equals(bean.getIconEmpty())) {

            // Regarding the left-most cell in this row, check if all other cells in this row are equal to said left-most cell.
            for (int cellInRow = row; cellInRow < (row + bean.getBoardWidth()); ++cellInRow) {
                if (!boardStates.get(cellInRow).equals(leftMostCellInRow)) {
                    // Cells are not identical.
                    return bean.getIconEmpty();
                }
            }

            // All cells in this row are identical and not empty. Thus a victory has been detected.
            return leftMostCellInRow;
        }

        // No victory detected in this row.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a horizontal win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForHorizontalWins(List<String> boardStates) {
        String rowVictor;

        // For each row...
        for (int row = 0; row < (bean.getCellQuantity() - 1); row += bean.getBoardWidth()) {

            rowVictor = checkRow(boardStates, row);

            if (!rowVictor.equals(bean.getIconEmpty())) {
                return rowVictor;
            }
        }

        // No horizontal victories detected.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a diagonal top-left to bottom-right win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win detected.
     */
    private String checkForDiagonalTopLeftToBottomRightWin(List<String> boardStates) {
        String topMostCellInDiagonal = boardStates.get(0);  // In the current diagonal, in the cell that is nearest the top/start, the icon inside it.

        // If the top-most cell is empty, then this diagonal does not have a victory.
        if (topMostCellInDiagonal == null || topMostCellInDiagonal.equals(bean.getIconEmpty())) {
            return bean.getIconEmpty();
        }

        // Regarding the top-most cell in this diagonal, check if all other cells in this diagonal are equal to said top-most cell.
        for (int currentCell = (bean.getBoardWidth() + 1); currentCell < bean.getCellQuantity(); currentCell += (bean.getBoardWidth() + 1)) {
            if (!boardStates.get(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return bean.getIconEmpty();
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
        String topMostCellInDiagonal = boardStates.get(bean.getBoardWidth() - 1);  // In the current diagonal, in the cell that is nearest the top/start, the icon inside it.

        // If the top-most cell is empty, then this diagonal does not have a victory.
        if (topMostCellInDiagonal == null || topMostCellInDiagonal.equals(bean.getIconEmpty())) {
            return bean.getIconEmpty();
        }

        // Regarding the top-most cell in this diagonal, check if all other cells in this diagonal are equal to said top-most cell.
        for (int currentCell = ((bean.getBoardWidth() * 2) - 2); currentCell < (bean.getCellQuantity() - 1); currentCell += (bean.getBoardWidth() - 1)) {
            if (!boardStates.get(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return bean.getIconEmpty();
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
        if (!resultOfFirstDiagonalVictoryCheck.equals(bean.getIconEmpty())) {
            return resultOfFirstDiagonalVictoryCheck;
        } else {
            return checkForDiagonalTopRightToBottomLeftWin(boardStates);
        }
    }

    /**
     * Checks the board for if any wins/victory conditions have taken place.
     * @param boardStates The game board.
     * @return The winner (if any), else the empty icon.
     */
    private String checkForWins(List<String> boardStates) {
        String winner;

        // Checking for a vertical win
        winner = checkForVerticalWins(boardStates);
        if (winner.equals(bean.getIconPlayer1())) {
            bean.incrementScorePlayer1();
        } else if (winner.equals(bean.getIconPlayer2())) {
            bean.incrementScorePlayer2();
        }

        // Checking for a horizontal win
        if (winner.equals(bean.getIconEmpty())) {
            winner = checkForHorizontalWins(boardStates);
            if (winner.equals(bean.getIconPlayer1())) {
                bean.incrementScorePlayer1();
            } else if (winner.equals(bean.getIconPlayer2())) {
                bean.incrementScorePlayer2();
            }
        }

        // Checking for a diagonal win
        if (winner.equals(bean.getIconEmpty())) {
            winner = checkForDiagonalWins(boardStates);
            if (winner.equals(bean.getIconPlayer1())) {
                bean.incrementScorePlayer1();
            } else if (winner.equals(bean.getIconPlayer2())) {
                bean.incrementScorePlayer2();
            }
        }

        return winner;
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

        List<String> boardStates = bean.getBoardStates();
        int cellSelected = -1;

        // Getting the selected cell
        if (request.getParameter("cell") != null) {
            cellSelected = Integer.parseInt(request.getParameter("cell"));
        }

        // Changing the board state based on the selected cell
        if (0 <= cellSelected && cellSelected <= 8) {  // if spot is valid
            if (boardStates.get(cellSelected).equals(bean.getIconEmpty())) {  // If spot is not already taken
                if (bean.isPlayer1Turn()) {
                    boardStates.set(cellSelected, bean.getIconPlayer2());
                } else {
                    boardStates.set(cellSelected, bean.getIconPlayer1());
                }
            } else {
                // Change whose turn it is.
                bean.changeTurn();
            }
        }


        // Checking for a wins
        String winner = checkForWins(boardStates);
        if (!winner.equals(bean.getIconEmpty())) {
            bean.setGameOver(true);
        }

        // Checking for Draw or Win, changing the turn string, and setting board
        if (!bean.isGameOver() && !boardStates.contains(bean.getIconEmpty())) {
            bean.incrementDraws();
            boardStates = getNewBoardStates();
            if (bean.isPlayer1Turn()) {
                bean.setTurnString("Game Over! Draw! Starting new game. " + bean.getIconPlayer1() + "'s Turn");
            } else {
                bean.setTurnString("Game Over! Draw! Starting new game. " + bean.getIconPlayer2() + "'s Turn");
            }
        } else if (bean.isGameOver()) {
            bean.setGameOver(false);
            boardStates = getNewBoardStates();
            if (bean.isPlayer1Turn()) {
                bean.setTurnString("Game Over! " + bean.getIconPlayer2() + " Wins! Starting new game. " + bean.getIconPlayer1() + "'s Turn");
            } else {
                bean.setTurnString("Game Over! " + bean.getIconPlayer1() + " Wins! Starting new game. " + bean.getIconPlayer2() + "'s Turn");
            }
        } else if (bean.isPlayer1Turn()) {
            bean.setTurnString(bean.getIconPlayer1() + "'s Turn");
        } else {
            bean.setTurnString(bean.getIconPlayer2() + "'s Turn");
        }

        // Setting bean data
        bean.setBoardStates(boardStates);

        request.setAttribute("beanData", bean);

        // Forwarding to the JSP.
        String url = "/teamMVC.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        // Changing the turn
        bean.changeTurn();
    }
}