package java112.project3;

import java.util.*;

/**
 * This is a JavaBean that gets and sets instance variables for the Team MVC
 * Challenge, which involves making a tic-tac-toe game.
 * @author github.com/kileader and github.com/l3oxy
 */
public class JavaBean {

    /**
     * The height of the game {@link #board}.
     * In other words, how many rows are there.
     * NOTE: if boardHeight and {@link #boardWidth} are not identical,
     * then diagonal victory detection will be non-perfect.
     */
    private int boardHeight;

    /**
     * The width of the game {@link #board}.
     * In other words, how many columns there are.
     * NOTE: if {@link #boardHeight} and boardWidth are not identical, then
     * diagonal victory detection will be non-perfect.
     */
    private int boardWidth;

    /**
     * Describes the state of the board.
     * For example if board is 3x3 then the index represent cells:
     *   0 | 1 | 2
     *   ――+―――+―――
     *   3 | 4 | 5
     *   ――+―――+―――
     *   6 | 7 | 8
     *
     *   <br />
     *   (for javaDoc/HTML viewers of this documentation:)
     *   <table>
     *       <tr>
     *           <td>0</td><td>1</td><td>2</td>
     *       </tr>
     *       <tr>
     *           <td>3</td><td>4</td><td>5</td>
     *       </tr>
     *       <tr>
     *           <td>6</td><td>7</td><td>8</td>
     *       </tr>
     *   </table>
     */
    private List<String> board;

    /**
     * The icon for an empty/un-claimed spot on the {@link #board}.
     */
    private String iconEmpty;

    /**
     * The icon for player 1.
     */
    private String iconPlayer1;

    /**
     * The icon for player 2.
     */
    private String iconPlayer2;

    /**
     * Number of games that player1 has won.
     */
    private int player1Score;

    /**
     * Number of games that player2 has won.
     */
    private int player2Score;

    /**
     * Number of times that the game ended in a draw.
     */
    private int draws;

    /**
     * Marks whose turn it is.
     * If true, it is player1's turn. If false, it is player2's turn.
     */
    private boolean player1Turn;

    /**
     * Describes if the game is over.
     */
    private boolean gameOver;

    /**
     * Words describing whose turn it is.
     */
    private String turnString;

    /**
     * Constructor that sets initial values for the variables.
     */
    public JavaBean() {
        boardHeight = -1;
        boardWidth = -1;
        board = new ArrayList<String>();
        player1Turn = true;
        gameOver = false;
        player1Score = 0;
        player2Score = 0;
        draws = 0;
    }

    /**
     * Returns the {@link #boardHeight}.
     * @return the {@link #boardHeight}.
     */
    public int getBoardHeight() {
        return this.boardHeight;
    }

    /**
     * Sets the {@link #boardHeight}.
     * @param boardHeight The new {@link #boardHeight}. Must be 1 or more.
     */
    public void setBoardHeight(int boardHeight) {
        if (boardHeight >= 1) {
            this.boardHeight = boardHeight;
        }
    }

    /**
     * Returns the {@link #boardWidth}.
     * @return The {@link #boardWidth}.
     */
    public int getBoardWidth() {
        return this.boardWidth;
    }

    /**
     * Sets the {@link #boardWidth}.
     * @param boardWidth The new {@link #boardWidth}.
     */
    public void setBoardWidth(int boardWidth) {
        if (boardWidth >= 1) {
            this.boardWidth = boardWidth;
        }
    }

    /**
     * The quantity / number of cells in the game {@link #board}.
     * @return The quantity / number of cells in the game {@link #board}.
     */
    public int getCellQuantity() {
        return (getBoardHeight() * getBoardWidth());
    }

    /**
     * Returns the {@link #board}.
     * @return The {@link #board}.
     */
    public List<String> getBoard() {
        return board;
    }

    /**
     * Sets the {@link #board} with a provided new board.
     * @param board The new {@link #board}. Must not be null.
     */
    public void setBoard(List<String> board) {
        if (board != null) {
            this.board = board;
        }
    }

    /**
     * Gets a cell/spot from the {@link #board}.
     * @param index On the {@link #board}, the index of the cell to get.
     * @return On the {@link #board}, at the provided index, the value at said
     * index.
     */
    public String getBoardCell(int index) {
        return this.board.get(index);
    }

    /**
     * Sets a cell/spot on the {@link #board}.
     * @param index On the {@link #board}, the index of the cell to set.
     * @param newValue On the {@link #board}, at the provided index, the new value to
     *                 put at said index.
     */
    public void setBoardCell(int index, String newValue) {
        this.board.set(index, newValue);
    }

    /**
     * Changes the game {@link #board} to be a new clean/blank board.
     */
    public void resetBoard() {
        List<String> newBoard = new ArrayList<String>();

        for (int i = 0; i < this.getCellQuantity(); ++i) {
            newBoard.add(this.getIconEmpty());
        }

        this.setBoard(newBoard);
    }

    /**
     * Returns {@link #iconPlayer1}.
     * @return {@link #iconPlayer1}.
     */
    public String getIconPlayer1() {
        return iconPlayer1;
    }

    /**
     * Sets {@link #iconPlayer1}.
     * @param iconPlayer1 The new {@link #iconPlayer1}.
     *                    Must be neither null nor empty.
     */
    public void setIconPlayer1(String iconPlayer1) {
        if (iconPlayer1 != null && !iconPlayer1.isEmpty()) {
            this.iconPlayer1 = iconPlayer1;
        }
    }

    /**
     * Returns {@link #iconPlayer2}.
     * @return {@link #iconPlayer2}.
     */
    public String getIconPlayer2() {
        return iconPlayer2;
    }

    /**
     * Sets {@link #iconPlayer2}.
     * @param iconPlayer2 The new {@link #iconPlayer2}.
     *                    Must be neither null nor empty.
     */
    public void setIconPlayer2(String iconPlayer2) {
        if (iconPlayer2 != null && !iconPlayer2.isEmpty()) {
            this.iconPlayer2 = iconPlayer2;
        }
    }

    /**
     * Returns the empty/unused icon, {@link #iconEmpty}.
     * @return The empty/unused icon, {@link #iconEmpty}.
     */
    public String getIconEmpty() {
        return iconEmpty;
    }

    /**
     * Sets the empty/unused icon, {@link #iconEmpty}.
     * @param iconEmpty The new {@link #iconEmpty}.
     *                  Must be neither null nor empty.
     *                  E.g. "_".
     */
    public void setIconEmpty(String iconEmpty) {
        if (iconEmpty != null && !iconEmpty.isEmpty()) {
            this.iconEmpty = iconEmpty;
        }
    }

    /**
     * Tells you if it is Player 1's turn, {@link #player1Turn}.
     * @return If it is Player 1's turn, then return true. Else false.
     */
    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    /**
     * Sets the value {@link #player1Turn} that determines if it is
     * Player 1's turn, or Player 2's.
     * @param player1Turn The new value for "player1turn".
     *                    If it should be player 1's turn, provide true.
     *                    Else if it should be player 2's turn, provide false.
     */
    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    /**
     * Changes the turn, by inverting {@link #player1Turn}
     */
    public void changeTurn() {
        this.player1Turn = !this.player1Turn;
    }

    /**
     * Returns value indicating if the game is over, {@link #gameOver}.
     * @return If the game is over, returns true. Else returns false.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets value that determines if the game is over, {@link #gameOver}.
     * @param gameOver If game should be over, provide true.
     *                 Else if game should not be over, provide false.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Gets Player 1's score, {@link #player1Score}.
     * @return Player 1's score.
     */
    public int getPlayer1Score() {
        return player1Score;
    }

    /**
     * Sets Player 1's score, {@link #player1Score}.
     * @param player1Score Player 1's new score. Must be 0 or greater.
     */
    public void setPlayer1Score(int player1Score) {
        if (player1Score >= 0) {
            this.player1Score = player1Score;
        }
    }

    /**
     * Increments Player 1's score, {@link #player1Score} (i.e. increases it by 1).
     */
    public void incrementScorePlayer1() {
        ++this.player1Score;
    }

    /**
     * Returns Player 2's score, {@link #player2Score}.
     * @return Player 2's score.
     */
    public int getPlayer2Score() {
        return player2Score;
    }

    /**
     * Sets Player 2's score, {@link #player2Score}.
     * @param player2Score Player 2's new score. Must be 0 or greater.
     */
    public void setPlayer2Score(int player2Score) {
        if (player2Score >= 0) {
            this.player2Score = player2Score;
        }
    }

    /**
     * Increments Player 2's score (i.e. increases it by 1), {@link #player2Score}.
     */
    public void incrementScorePlayer2() {
        ++this.player2Score;
    }

    /**
     * Returns the number of draws that have occurred / been detected,
     * {@link #draws).
     * @return The number of draws that have occurred / been detected.
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Sets the number of draws, {@link #draws}.
     * @param draws The new number of draws.
     */
    public void setDraws(int draws) {
        this.draws = draws;
    }

    /**
     * Increments the number of draws (i.e. increases it by 1), {@link #draws}.
     */
    public void incrementDraws() {
        ++this.draws;
    }

    /**
     * Returns {@link #turnString} used for displaying to players whose turn it is,
     * and if a player has won, and if a draw has occurred.
     * @return The "turnString" value.
     */
    public String getTurnString() {
        return turnString;
    }

    /**
     * Sets {@link #turnString} value used for displaying to players whose turn it
     * is, and if a player has won, and if a draw has occurred.
     * @param turnString The new value for "turnString".
     *                   Must not be null.
     */
    public void setTurnString(String turnString) {
        if (turnString != null) {
            this.turnString = turnString;
        }
    }
}