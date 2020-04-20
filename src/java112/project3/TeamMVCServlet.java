package java112.project3;

import java.io.*;
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
        bean.resetBoard();
    }

    /**
     * Returns the icon for whose turn it is.
     * @return The icon for those turn it is.
     */
    private String getIconForWhoseTurnItIs() {
        if (bean.isPlayer1Turn()) {
            return bean.getIconPlayer1();
        } else {
            return bean.getIconPlayer2();
        }
    }

    /**
     * Returns the icon for whose turn it is not.
     * @return The icon for those turn it is not.
     */
    private String getIconForWhoseTurnItIsNot() {
        if (bean.isPlayer1Turn()) {
            return bean.getIconPlayer2();
        } else {
            return bean.getIconPlayer1();
        }
    }

    /**
     * Player attempted to put their icon in a cell. Check if that move is
     * valid, if so, do it.
     * @param cell The cell/spot that the player requested to take/claim.
     */
    private void processTurnIfValid(String cell) {
        if (cell != null) {
            int cellSelected = Integer.parseInt(cell);

            // If the selected cell exists on the board...
            if (0 <= cellSelected && cellSelected <=
                    (bean.getCellQuantity() - 1)) {
                // If the cell is empty/unclaimed...
                if (bean.getBoardCell(cellSelected).equals(bean.getIconEmpty()))
                {
                    // Give it to the player, and change turns.
                    bean.setBoardCell(cellSelected,
                            getIconForWhoseTurnItIs());
                    bean.changeTurn();
                }
            }
        }
    }

    /**
     * Inspects a vertical column on the board for a victory.
     * @param column the index on the board where the column to inspect begins
     *               the top part of the column).
     * @return the icon the won, or EMPTY if nobody won on the specified row.
     */
    private String checkThisColumn(int column) {
        // Get the icon at the top of the column.
        String topCellOfColumn = bean.getBoardCell(column);

        // If the top cell of this column is null or empty,
        // then go to the next column.
        if (topCellOfColumn != null &&
                !topCellOfColumn.equals(bean.getIconEmpty())) {

            // Regarding the top-most cell in this column, check if all other
            // cells in this column are equal to said top-most cell.
            for (int currentCell = (column + bean.getBoardWidth());
                     currentCell < bean.getCellQuantity();
                     currentCell += bean.getBoardWidth()) {
                // Compare this cell to the top cell of this column.
                if (!bean.getBoardCell(currentCell).equals(topCellOfColumn)) {
                    return bean.getIconEmpty();
                }
            }

            // All cells in this column are identical and not empty. Thus a
            // victory has been detected.
            return topCellOfColumn;
        }

        // No victory detected in this column.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a vertical win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win
     * detected.
     */
    private String checkForVerticalWins() {
        String rowVictor;

        // For each column ...
        for (int column = 0; column < bean.getBoardWidth(); ++column) {

            rowVictor = checkThisColumn(column);

            if (!rowVictor.equals(bean.getIconEmpty())) {
                return rowVictor;
            }
        }

        // No vertical victories detected.
        return bean.getIconEmpty();
    }

    /**
     * Inspects a horizontal row on the board for a victory.
     * @param row the index on the board where the row to inspect begins
     *            (the left-most part).
     * @return the icon that won, or EMPTY if nobody won on the specified row.
     */
    private String checkThisRow(int row) {
        // In the current row, in the cell that is most left,
        // the icon inside it.
        String leftMostCellInRow = bean.getBoardCell(row);

        // In the current row, if left-most cell is not empty/null, then this
        // row could have a victory.
        if (leftMostCellInRow != null &&
                !leftMostCellInRow.equals(bean.getIconEmpty())) {

            // Regarding the left-most cell in this row, check if all other
            // cells in this row are equal to said left-most cell.
            for (int cellInRow = row; cellInRow < (row + bean.getBoardWidth());
                    ++cellInRow) {
                if (!bean.getBoardCell(cellInRow).equals(leftMostCellInRow)) {
                    // Cells are not identical.
                    return bean.getIconEmpty();
                }
            }

            // All cells in this row are identical and not empty. Thus a
            // victory has been detected.
            return leftMostCellInRow;
        }

        // No victory detected in this row.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a horizontal win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win
     * detected.
     */
    private String checkForHorizontalWins() {
        String rowVictor;

        // For each row...
        for (int row = 0; row < (bean.getCellQuantity() - 1);
                row += bean.getBoardWidth()) {

            rowVictor = checkThisRow(row);

            if (!rowVictor.equals(bean.getIconEmpty())) {
                return rowVictor;
            }
        }

        // No horizontal victories detected.
        return bean.getIconEmpty();
    }

    /**
     * Checks if a diagonal top-left to bottom-right win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win
     * detected.
     */
    private String checkForDiagonalTopLeftToBottomRightWin() {
        String topMostCellInDiagonal = bean.getBoardCell(0);
        // In the current diagonal, in the cell that is nearest the top/start,
        // the icon inside it.

        // If the top-most cell is empty, then this diagonal does not have a
        // victory.
        if (topMostCellInDiagonal == null ||
                topMostCellInDiagonal.equals(bean.getIconEmpty())) {
            return bean.getIconEmpty();
        }

        // Regarding the top-most cell in this diagonal, check if all other
        // cells in this diagonal are equal to said top-most cell.
        for (int currentCell = (bean.getBoardWidth() + 1);
                currentCell < bean.getCellQuantity();
                currentCell += (bean.getBoardWidth() + 1)) {
            if (!bean.getBoardCell(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return bean.getIconEmpty();
            }
        }

        // Diagonal victory detected.
        return topMostCellInDiagonal;
    }

    /**
     * Checks if a diagonal top-right to bottom-left win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win
     * detected.
     */
    private String checkForDiagonalTopRightToBottomLeftWin() {
        // In the current diagonal, in the cell that is nearest the top/start,
        // the icon inside it.
        String topMostCellInDiagonal =
                bean.getBoardCell(bean.getBoardWidth() - 1);

        // If the top-most cell is empty, then this diagonal does not have a
        // victory.
        if (topMostCellInDiagonal == null ||
                topMostCellInDiagonal.equals(bean.getIconEmpty())) {
            return bean.getIconEmpty();
        }

        // Regarding the top-most cell in this diagonal, check if all other
        // cells in this diagonal are equal to said top-most cell.
        for (int currentCell = ((bean.getBoardWidth() * 2) - 2);
                currentCell < (bean.getCellQuantity() - 1);
                currentCell += (bean.getBoardWidth() - 1)) {
            if (!bean.getBoardCell(currentCell).equals(topMostCellInDiagonal)) {
                // No diagonal victories detected.
                return bean.getIconEmpty();
            }
        }

        // Diagonal victory detected.
        return topMostCellInDiagonal;
    }

    /**
     * Checks if a diagonal win has occurred.
     * @return The icon/symbol that has won, or the EMPTY icon if no win
     * detected.
     */
    private String checkForDiagonalWins() {
        String resultOfFirstDiagonalVictoryCheck;

        resultOfFirstDiagonalVictoryCheck
                = checkForDiagonalTopLeftToBottomRightWin();
        if (!resultOfFirstDiagonalVictoryCheck.equals(bean.getIconEmpty())) {
            return resultOfFirstDiagonalVictoryCheck;
        } else {
            return checkForDiagonalTopRightToBottomLeftWin();
        }
    }

    /**
     * Takes an icon, finds the player that has it, and increments their score.
     * @param icon The icon whose player's score shall be incremented by this
     *             method.
     */
    private void incrementScoreOfThisIconsPlayer(String icon) {
        if (icon.equals(bean.getIconPlayer1())) {
            bean.incrementScorePlayer1();
        } else if (icon.equals(bean.getIconPlayer2())) {
            bean.incrementScorePlayer2();
        }
    }

    /**
     * Checks the board for if any wins/victory conditions have taken place.
     * @return The winner (if any), else the empty icon.
     */
    private String checkForWins() {
        String winnerOrEmpty;

        // Checking for a vertical win
        winnerOrEmpty = checkForVerticalWins();
        incrementScoreOfThisIconsPlayer(winnerOrEmpty);

        // Checking for a horizontal win
        if (winnerOrEmpty.equals(bean.getIconEmpty())) {
            winnerOrEmpty = checkForHorizontalWins();
            incrementScoreOfThisIconsPlayer(winnerOrEmpty);
        }

        // Checking for a diagonal win
        if (winnerOrEmpty.equals(bean.getIconEmpty())) {
            winnerOrEmpty = checkForDiagonalWins();
            incrementScoreOfThisIconsPlayer(winnerOrEmpty);
        }

        return winnerOrEmpty;
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

        String urlToForwardTo;

        if (request.getParameter("details") != null) {
            // The details page was requested.
            urlToForwardTo = "/details.jsp";
        } else {
            // The details page was not requested, so show the game page.
            urlToForwardTo = "/teamMVC.jsp";

            // Changing the board state based on the selected cell
            processTurnIfValid(request.getParameter("cell"));

            // Checking for a win
            String winner = checkForWins();
            if (!winner.equals(bean.getIconEmpty())) {
                bean.setGameOver(true);
            }

            // Checking for Draw or Win, changing the turn string,
            // and setting board
            if (!bean.isGameOver() &&
                    !bean.getBoard().contains(bean.getIconEmpty())) {
                // Draw detected.
                bean.incrementDraws();
                bean.resetBoard();
                bean.setTurnString("Game Over! Draw! Starting new game. " 
			+ getIconForWhoseTurnItIs() + "'s Turn");
            } else if (bean.isGameOver()) {
                // Victory detected.
                bean.setGameOver(false);
                bean.resetBoard();
                bean.setTurnString("Game Over! " +
                        getIconForWhoseTurnItIsNot() +
                        " Wins! Starting new game. " +
                        getIconForWhoseTurnItIs() + "'s Turn");
            } else {
                // Neither a victory nor a draw detected. Play on.
                bean.setTurnString(getIconForWhoseTurnItIs() + "'s Turn");
            }
        }

        request.setAttribute("beanData", bean);

        // Forwarding to the JSP.
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(urlToForwardTo);
        dispatcher.forward(request, response);
    }
}
