package minesweeper;

import java.util.Random;

public class Board {

    int rows;
    int columns;
    Cell[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new Cell[rows + 2][columns + 2];
    }

    public void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell('.',false);
            }
        }
    }

    public void startGame(int numberOfMines) {
        Random rd = new Random();
        int counter = 0;

        if (numberOfMines > rows * columns) {
            System.out.println("Count of mines can't be higher than count of cells");
        } else {
            while (counter < numberOfMines) {
                int row = rd.nextInt(rows - 2 + 1) + 1;
                int column = rd.nextInt(columns - 2 + 1) + 1;
                if (!board[row][column].isMine) {
                    board[row][column].isMine = true;
                    board[row][column].character = '.';
                    counter++;
                }
            }
        }
    }

    public void revealMines() {
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if(board[i][j].isMine) {
                    board[i][j].character = 'X';
                }
            }
        }
    }

    public void revealCells() {
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                int minesAround  = 0;
                if(i > 1){
                    if(board[i-1][j].isMine){
                        minesAround++;
                    }
                    if(board[i-1][j+1].isMine){
                        minesAround++;
                    }
                    if(board[i-1][j-1].isMine){
                        minesAround++;
                    }
                }
                if(i < board.length - 1){
                    if(board[i+1][j].isMine){
                        minesAround++;
                    }
                    if(board[i+1][j+1].isMine){
                        minesAround++;
                    }
                    if(board[i+1][j-1].isMine){
                        minesAround++;
                    }
                }
                if(board[i][j+1].isMine){
                    minesAround++;
                }
                if(board[i][j-1].isMine){
                    minesAround++;
                }
                if(!board[i][j].isMine && minesAround > 0) {
                    board[i][j].character = (char) (minesAround + '0');
                } else if (!board[i][j].isMine && minesAround == 0) {
                    board[i][j].character = '/';
                }
            }
        }
    }
    public void checkConditionBySecuring(int mines) {
        int securedCells = 0;
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if(board[i][j].character == '.' && board[i][j].isMine) {
                    securedCells++;
                }
            }
        }
        if (securedCells == mines) {
            System.out.println("Congratulations! You found all mines!");
        } else {
            System.out.println("Failed. You found " + securedCells + "/" + mines + "mine(s)");
        }
    }

    public void checkCondition(int mines) {
        int minesFound = 0;
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if(board[i][j].character == '*' && board[i][j].isMine) {
                    minesFound++;
                }
            }
        }
        if (minesFound == mines) {
            System.out.println("Congratulations! You found all mines!");
        } else {
            System.out.println("Failed. You found " + minesFound + "/" + mines + "mine(s)");
        }
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < columns + 1; i++) {
            sb.append(i);
        }
        System.out.println(" |" + sb.toString() + "|");
        System.out.println("—│—————————│");
        for (int i = 1; i < board.length - 1; i++) {
            System.out.print(i + "|");
            for (int j = 1; j < board[i].length - 1; j++) {
                System.out.print(board[i][j].character);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("—│—————————│");
    }
}
