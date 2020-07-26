package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        char[][] board = new char[3][3];
        String initialInput = "         ";
        Scanner sc = new Scanner(System.in);

        fillBoard(board, initialInput);
        printBoard(board);
        for(int i = 0; i<9; i++){
            if(i%2==0){
                fill(board,'X');
            }else{
                fill(board,'O');
            }
            printBoard(board);
            if(checkCondition(board).equals("X wins") || checkCondition(board).equals("O wins")){
                System.out.println(checkCondition(board));
                break;
            }else {
                System.out.println(checkCondition(board));
            }
        }
    }

    public static void fillBoard(char[][] board, String input) {
        char[] symbols = input.toCharArray();
        int charCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = symbols[charCounter++];
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String checkCondition(char[][] board) {
        boolean winX = false;
        boolean winO = false;
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            int sumRow = 0;
            int sumColumn = 0;
            int sumDiagonal = 0;
            int sumOtherDiagonal = 0;


            for (int j = 0; j < 3; j++) {
                sumRow += board[i][j];
                sumColumn += board[j][i];
                sumDiagonal += board[j][j];
                sumOtherDiagonal += board[j][board.length - 1 - j];
            }
            if (sumRow == 264 || sumColumn == 264 || sumDiagonal == 264 || sumOtherDiagonal == 264) {
                winX = true;
            } else if (sumRow == 237 || sumColumn == 237 || sumDiagonal == 237 || sumOtherDiagonal == 237) {
                winO = true;
            }
        }
        String result = Math.abs(countX - countO) > 1 || winX && winO ? "Impossible"
                : winX ? "X wins"
                : winO ? "O wins"
                : countX + countO == 9 ? "Draw"
                : "Game not finished";
        return result;

    }

    private static void fill(char[][] board,char symbol) {
        Scanner sc = new Scanner(System.in);
        boolean isFilled = false;
        int n;
        int m;
        while (!isFilled) {
            System.out.print("Enter the coordinate: ");
            try {
                n = sc.nextInt();
                m = sc.nextInt();

                if (board[3-m][n-1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    board[3-m][n-1] = symbol;
                    isFilled = true;
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("You should enter numbers!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

}

