package game;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playerTurn = 0;

    public Game() {
        this.board = new GameBoard(this);
    }

    public void initGame() {
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');
    }

    /**
     * Метод передачи хода
     */
    void passTurn() {
        if (playerTurn == 0)
            playerTurn = 1;
        else
            playerTurn = 0;
    }

    /**
     * Полчение объекта текущего игрока
     *
     * @return GamePlayer объекта игрока
     */
    GamePlayer getCurrentPlayer() {
        return gamePlayers[playerTurn];
    }

    /**
     * Метод показа popup-a для пользователя
     *
     * @param messageText - текст сообщения
     */
    void showMessege(String messageText) {
        JOptionPane.showMessageDialog(board, messageText);
    }
}

