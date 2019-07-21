package game;

import javax.swing.*;

public class GameBoard extends JFrame {

    static int dimension = 3;           //размерность
    static int cellSize = 150;          //размерность одной клетки
    private char[][] gameField;         //матрица игры
    private GameButton[] gameButtons;   //массив кнопок

    private Game game;                  //ссылка на игру

    public GameBoard(Game currenrGame){
        this.game = currenrGame;

    }

}
