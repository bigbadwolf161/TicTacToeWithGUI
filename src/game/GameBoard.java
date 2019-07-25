package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {

    static int dimension = 3;           //размерность
    static int cellSize = 150;          //размерность одной клетки
    private char[][] gameField;         //матрица игры
    private GameButton[] gameButtons;   //массив кнопок

    private Game game;                  //ссылка на игру
    static char nullSymbol = '\u0000';  //null символ

    public GameBoard(Game currenrGame){
        this.game = currenrGame;
        initField();

    }
    private void initField(){
        //Задаем настройки игры
        setBounds(cellSize*dimension, cellSize*dimension, 400, 400);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();     //панель управления игрой
        JButton newGameButton = new JButton("новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*dimension,150);

        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension,dimension));
        gameFieldPanel.setSize(cellSize*dimension, cellSize*dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        //инициализируем игровое поле
        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * метод очистки поля и матрицы игры
     */
    void emptyField(){
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y =  i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
    }
    Game getGame(){
        return game;
    }

    /**
     * метод проверки доступности клетки для хода
     * @param x - по горизонтали
     * @param  y - по вертикали
     * @return boolean
     */

    boolean isTurnable(int x, int y){
        boolean result = false;

        if(gameField[y][x] == nullSymbol)
            return false;

        return result;
    }

    /**
     * обновление матрицы игры после хода
     * @param x - по горизонтали
     * @param y - по вертикали
     */

    void updateGameField(int x, int y){
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin(){
        boolean result = false;
        char playerSybmol = getGame().getCurrentPlayer().getPlayerSign();

        if(checkWinDiagonals(playerSybmol)||checkWinLines(playerSybmol)){
            result = true;

        }
        return result;
    }

    /**
     * проверка победы по столбцам и линиям
     * @return флаг победы
     */

    private boolean checkWinLines(char playerSymbol){
        boolean cols, rows, result;

        result = false;

        for (int col = 0; col < dimension; col++) {
            cols = true;
            rows = true;

            for (int row = 0; row < dimension; row++) {
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }
            //это условие после каждой проверки колонки и столбца
            //позволяет остановить дальнейшее выполнение, без проверки
            //всех остальных столбцов и строк
            if(cols || rows){
                result = true;
                break;
            }

            if(result){
                break;
            }
        }
        return  result;
    }
    private boolean checkWinDiagonals(char playerSymbol){
        boolean leftRight, rightLeft, result;

        leftRight = true;
        rightLeft = true;
        result = false;

        for (int i = 0; i < dimension; i++) {
            leftRight &= (gameField[i][i] == playerSymbol);
            rightLeft &= (gameField[dimension-i-1][i] == playerSymbol);
        }
        if(rightLeft || leftRight){
            result = true;
        }
        return  result;
    }

    /**
     * метод проверки заполнености поля
     * @return boolean
     */

    boolean isFull(){
        boolean result = true;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(gameField[i][j] == nullSymbol)
                    result = false;
            }
        }
        return result;
    }

    public GameButton getButton(int buttonIndex){
        return gameButtons[buttonIndex];
    }

}
