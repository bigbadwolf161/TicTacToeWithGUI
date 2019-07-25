package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {

    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row,int cell, GameButton gButton){
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if(board.isTurnable(row, cell)){
            updatePlayersData(board);

            if(board.isFull()){
                board.getGame().showMessege("ничья!");
                board.emptyField();
            }
            else {
                updateByAiData(board);
            }

        }
        else {
            board.getGame().showMessege("некорректный ход");
        }

    }

    /**
     * ход человека
     * @param board GameBoard - ссылка на игровое поле
     */

    private void updatePlayersData(GameBoard board){
        // обновить матрицу игры
        board.updateGameField(row, cell);

        //обновить содержимое кнопки

        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if(board.checkWin()){
            button.getBoard().getGame().showMessege("Вы выйграли");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }

    }

    private void updateByAiData(GameBoard board){
        //генерация координат хода компьютра

        int x,y;
        Random rnd = new Random();

        do {
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);
        }
        while (!board.isTurnable(x,y));

        //обновить матрицу игры
        board.updateGameField(x,y);

        //обновить содержимое кнопки
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if(board.checkWin()){
            button.getBoard().getGame().showMessege("комьютер выйграл");
            board.emptyField();
        }
        else {
            //передать ход
            board.getGame().passTurn();
        }
    }

}
