package java112.project3;

import java.util.*;

/**
 * This is a JavaBean that gets and sets instance variables for the Team MVC
 * Challenge, which involves making a tic-tac-toe game.
 */
public class JavaBean {

    /**
     * The height of the game board.
     * In other words, how many rows are there.
     * NOTE: if boardHeight and boardWidth are not identical, then diagonal victory detection will be non-perfect.
     */
    private int boardHeight;

    /**
     * The width of the game board.
     * In other words, how many columns there are.
     * NOTE: if boardHeight and boardWidth are not identical, then diagonal victory detection will be non-perfect.
     */
    private int boardWidth;

    /**
     * Describes the state of the board.
     * For example if board is 9x9 then the index represent cells:
     *   0 | 1 | 2
     *   ――+―――+―――
     *   3 | 4 | 5
     *   ――+―――+―――
     *   6 | 7 | 8
     */
    private List<String> boardStates;

    /**
     * The icon for player 1.
     */
    private String iconPlayer1;

    /**
     * The icon for player 2.
     */
    private String iconPlayer2;

    /**
     * The icon for an empty/un-claimed spot on the board.
     */
    private String iconEmpty;

    /**
     * Marks who's turn it is. If true, it's player1's turn. If false, it's
     * player2's turn
     */
    private boolean player1Turn;

    /**
     * Describes if the game is over.
     */
    private boolean gameOver;

    /**
     * Number of games that player1, who's symbol is X, won.
     */
    private int player1Score;

    /**
     * Number of games that player2, who's symbol is O, won.
     */
    private int player2Score;

    /**
     * Number of times that the game ended in a draw.
     */
    private int draws;

    /**
     * Words describing who's turn it is.
     */
    private String turnString;

    /**
     * Constructor that sets initial values for the variables.
     */
    public JavaBean() {
        boardStates = new ArrayList<String>();
        for (int i = 0; i < 9; ++i) {
            boardStates.add("_");
        }
        player1Turn = true;
        gameOver = false;
        player1Score = 0;
        player2Score = 0;
        draws = 0;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        if (boardHeight >= 1) {
            this.boardHeight = boardHeight;
        }
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        if (boardWidth >= 1) {
            this.boardWidth = boardWidth;
        }
    }

    /**
     * The quantity / number of cells in the game board.
     * @return The quantity / number of cells in the game board.
     */
    public int getCellQuantity() {
        return (getBoardHeight() * getBoardWidth());
    }

    public List<String> getBoardStates() {
        return boardStates;
    }

    public void setBoardStates(List<String> boardStates) {
        if (boardStates != null) {
            this.boardStates = boardStates;
        }
    }

    public String getIconPlayer1() {
        return iconPlayer1;
    }

    public void setIconPlayer1(String iconPlayer1) {
        if (iconPlayer1 != null && !iconPlayer1.isEmpty()) {
            this.iconPlayer1 = iconPlayer1;
        }
    }

    public String getIconPlayer2() {
        return iconPlayer2;
    }

    public void setIconPlayer2(String iconPlayer2) {
        if (iconPlayer2 != null && !iconPlayer2.isEmpty()) {
            this.iconPlayer2 = iconPlayer2;
        }
    }

    public String getIconEmpty() {
        return iconEmpty;
    }

    public void setIconEmpty(String iconEmpty) {
        if (iconEmpty != null && !iconEmpty.isEmpty()) {
            this.iconEmpty = iconEmpty;
        }
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    /**
     * Changes the turn.
     */
    public void changeTurn() {
        this.player1Turn = !this.player1Turn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void incrementScorePlayer1() {
        ++this.player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public void incrementScorePlayer2() {
        ++this.player2Score;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void incrementDraws() {
        ++this.draws;
    }

    public String getTurnString() {
        return turnString;
    }

    public void setTurnString(String turnString) {
        if (turnString != null) {
            this.turnString = turnString;
        }
    }
}