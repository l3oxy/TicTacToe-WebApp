package java112.project3;

import java.util.*;

/**
 * This is a JavaBean that gets and sets instance variables for the Team MVC
 * Challenge, which involves making a tic-tac-toe game.
 */
public class JavaBean {

    /**
     * Describes the state of the board.
     * The index represent cells:
     *   0 | 1 | 2
     *   ――+―――+―――
     *   3 | 4 | 5
     *   ――+―――+―――
     *   6 | 7 | 8
     */
    private List<String> boardStates;

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

    public List<String> getBoardStates() {
        return boardStates;
    }

    public void setBoardStates(List<String> boardStates) {
        this.boardStates = boardStates;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
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

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getTurnString() {
        return turnString;
    }

    public void setTurnString(String turnString) {
        this.turnString = turnString;
    }
}