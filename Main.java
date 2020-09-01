package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board(9,9);
        boolean isRunning = true;

        board.fillBoard();
        System.out.println("How many mines do you want on the field?");
        int mines = sc.nextInt();
        board.printBoard();
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        sc.nextLine();
        String[] inputCommand = sc.nextLine().split(" ");
        int x = Integer.parseInt(inputCommand[0]);
        int y = Integer.parseInt(inputCommand[1]);
        String command = inputCommand[2];
        if (command.equals("mine")){
            board.board[y][x].isMine = true;
            board.board[y][x].character = '*';
            mines--;
        } else {
            board.board[y][x].character = '/';
        }
        board.startGame(mines);
        board.revealCells();
        board.printBoard();
        int minesFlags = mines - 1;

        while (isRunning) {
            if(minesFlags == 0){
                isRunning = false;
            }
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            inputCommand = sc.nextLine().split("\\s");
            x = Integer.parseInt(inputCommand[0]);
            y = Integer.parseInt(inputCommand[1]);
            command = inputCommand[2];
            if (board.board[y][x].character == '*') {
                board.board[y][x].character = '.';
                board.printBoard();
                minesFlags++;
            } else if (board.board[y][x].character == '.' && command.equals("mine")) {
                board.board[y][x].character = '*';
                board.printBoard();
                minesFlags--;
            } else if (board.board[y][x].character == '.' && command.equals("free")) {
                if(board.board[y][x].isMine) {
                    System.out.println("You stepped on a mine and failed!");
                    board.revealMines();
                    board.printBoard();
                    isRunning = false;
                } else {
                    board.board[y][x].character = '/';
                    board.revealCells();
                    board.printBoard();
                }
            } else {
                System.out.println("There is a number here!");
            }
        }
        if (minesFlags == mines - 1) {
            board.checkConditionBySecuring(mines);
        } else {
            board.checkCondition(mines);
        }
    }
}
