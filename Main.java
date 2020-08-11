package tictactoe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.openMenu();
    }
}

class Game {

    Scanner scan = new Scanner(System.in);
    String input;

    public void openMenu() {
        boolean running = true;
        while (running) {
            System.out.print("Input command:");
            input = scan.next().toLowerCase();
            switch (input) {
                case "start":
                    input = scan.nextLine().replaceAll(" ", "").toLowerCase();
                    switch (input) {
                        case "useruser":
                            Player human1 = new Player(Player.Type.HUMAN, "X");
                            Player human2 = new Player(Player.Type.HUMAN, "O");
                            start(human1, human2);
                            break;
                        case "easyeasy":
                            Player aiEasy1 = new Player(Player.Type.COMPUTER_EASY, "X");
                            Player aiEasy2 = new Player(Player.Type.COMPUTER_EASY, "O");
                            start(aiEasy1, aiEasy2);
                            break;
                        case "usereasy":
                            human1 = new Player(Player.Type.HUMAN, "X");
                            aiEasy1 = new Player(Player.Type.COMPUTER_EASY, "O");
                            start(human1, aiEasy1);
                            break;
                        case "easyuser":
                            aiEasy1 = new Player(Player.Type.COMPUTER_EASY, "X");
                            human1 = new Player(Player.Type.HUMAN, "O");
                            start(aiEasy1, human1);
                            break;
                        case "mediumuser":
                            Player aiMedium1 = new Player(Player.Type.COMPUTER_MEDIUM, "X");
                            human1 = new Player(Player.Type.HUMAN, "O");
                            start(aiMedium1, human1);
                            break;
                        case "usermedium":
                            human1 = new Player(Player.Type.HUMAN, "X");
                            aiMedium1 = new Player(Player.Type.COMPUTER_MEDIUM, "O");
                            start(human1, aiMedium1);
                            break;
                        case "mediummedium":
                            aiMedium1 = new Player(Player.Type.COMPUTER_MEDIUM, "X");
                            Player aiMedium2 = new Player(Player.Type.COMPUTER_MEDIUM, "O");
                            start(aiMedium1, aiMedium2);
                            break;
                        case "mediumeasy":
                            aiMedium1 = new Player(Player.Type.COMPUTER_MEDIUM, "X");
                            aiEasy1 = new Player(Player.Type.COMPUTER_EASY, "O");
                            start(aiMedium1, aiEasy1);
                            break;
                        case "easymedium":
                            aiEasy1 = new Player(Player.Type.COMPUTER_EASY, "X");
                            aiMedium1 = new Player(Player.Type.COMPUTER_MEDIUM, "O");
                            start(aiEasy1, aiMedium1);
                            break;
                        case "userhard":

                        case "harduser":

                        case "hardhard":
                            
                        case "mediumhard":

                        case "hardmedium":

                        case "easyhard":

                        case "hardeasy":

                        default:
                            System.out.println("Bad parameters!");
                            break;
                    }
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        }
    }

    private void start(Player player1, Player player2) {
        Board matrix = new Board();
        int playerChanger = 1;
        while (true) {
            System.out.println();
            matrix.printBoard();
            if (playerChanger % 2 != 0) {
                player1.makeMove(matrix);
            } else {
                player2.makeMove(matrix);
            }
            matrix.checkStatus();
            if (!matrix.getStatus().equals("game not finished")) {
                matrix.printBoard();
                System.out.println(matrix.getStatus());
                break;
            }
            playerChanger++;
        }
    }
}

class Board {

    private String[][] board;
    private String status;

    public Board() {
        board = new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        status = "game not finished";
    }

    public String getStatus() {
        return status;
    }

    protected void checkStatus() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] + board[i][1] + board[i][2]).equals("XXX") ||
                    (board[0][i] + board[1][i] + board[2][i]).equals("XXX")) {
                status = "X wins";
                break;
            } else if ((board[i][0] + board[i][1] + board[i][2]).equals("OOO") ||
                    (board[0][i] + board[1][i] + board[2][i]).equals("OOO")) {
                status = "O wins";
                break;
            }
        }
        if ((board[0][0] + board[1][1] + board[2][2]).equals("XXX") ||
                (board[0][2] + board[1][1] + board[2][0]).equals("XXX")) {
            status = "X wins";
        } else if ((board[0][0] + board[1][1] + board[2][2]).equals("OOO") ||
                (board[0][2] + board[1][1] + board[2][0]).equals("OOO")) {
            status = "O wins";
        } else if (Arrays.deepToString(board).replaceAll(" ", "").length() == 25) {
            status = "draw";
        }
    }

    protected boolean checkSquare(String input, String mark, Player.Type type) {
        if (!input.matches("\\d+")) {
            System.out.println("You should enter numbers!");
            return true;
        }
        String[][] indexId = {{"13", "23", "33"},
                            {"12", "22", "32"},
                            {"11", "21", "31"}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (input.equals(indexId[i][j])) {
                    if (!board[i][j].equals(" ")) {
                        if (type == Player.Type.HUMAN) {
                            System.out.println("This cell is occupied! Choose another one!");
                            return true;
                        }
                        return true;
                    } else {
                        board[i][j] = mark;
                        return false;
                    }
                }
        }
        return true;
    }

    protected boolean aiScan(String mark) {
        String sign = mark;
        for (int j = 0; j < 2; j++) {
            if ((board[0][0] + board[1][1] + board[2][2]).equals(sign + sign + " ")) {
                board[2][2] = sign;
                return false;
            } else if ((board[0][0] + board[1][1] + board[2][2]).equals(" " + sign + sign)) {
                board[0][0] = sign;
                return false;
            } else {
                for (int i = 0; i < 3; i++) {
                    if ((board[i][0] + board[i][1] + board[i][2]).equals(sign + sign + " ")) {
                        board[i][2] = sign;
                        return false;
                    } else if ((board[0][i] + board[1][i] + board[2][i]).equals(" " + sign + sign)) {
                        board[0][i] = sign;
                        return false;
                    }
                }
            }
            if (mark.equals("X")) {
                mark = "O";
            } else {
                mark = "X";
            }
        }
        return true;
    }

    protected int minimax(boolean isMaximizing, String mark) {
        if (status.equals("X wins")) {
            return 1;
        } else if (status.equals("O wins")) {
            return -1;
        } else if (status.equals("draw")) {
            return 0;
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            int score;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (board[i][j].equals(" ")) {
                        board[i][j] = mark;
                        score = minimax(false, mark);
                        maxScore = Math.max(score, maxScore);
                        board[i][j] = " ";
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            int score;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (board[i][j].equals(" ")) {
                        board[i][j] = mark;
                        score = minimax(true, mark);
                        minScore = Math.min(score, minScore);
                        board[i][j] = " ";
                    }
                }
            } return minScore;
        }
    }

    protected void printBoard() {
        for (int i = -1; i < 4; i++) {
            if (i == -1 || i == 3) {
                System.out.println("---------");
            } else {
                System.out.println("| " + board[i][0] + " " + board[i][1] + " " + board[i][2] + " |");
            }
        }
    }

    protected boolean checkIfEmpty(int i, int j) {
        if(board[i][j].equals(" ")){
            return true;
        } else {
            return false;
        }
    }
}

class Player {
    String mark;
    Type type;

    Player(Type type, String mark) {
        this.type = type;
        this.mark = mark;
    }

    protected void makeMove(Board matrix) {
        switch (type) {
            case HUMAN:
                humanMove(matrix);
                break;
            case COMPUTER_EASY:
                computerEasyMove(matrix);
                break;
            case COMPUTER_MEDIUM:
                computerMediumMove(matrix);
                break;
                /*
            case COMPUTER_HARD:
                computerHardMove(matrix);
                break;
                 */
        }
    }

    private void humanMove(Board matrix) {
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            System.out.println("Enter the coordinates:");
            input = scan.nextLine().replaceAll(" ", "");
        } while (matrix.checkSquare(input, mark, type));
    }

    private void computerEasyMove(Board matrix) {
        Random random = new Random();
        String indexGenerated;
        System.out.println("Making move level \"easy\"");
        do {
            indexGenerated = (1 + random.nextInt(4)) + "" + (1 + random.nextInt(4));
        } while (matrix.checkSquare(indexGenerated, mark, type));
    }

    private void computerMediumMove(Board matrix) {
        Random random = new Random();
        String indexGenerated;
        System.out.println("Making move level \"medium\"");
        if (matrix.aiScan(mark)) {
            do {
                indexGenerated = (1 + random.nextInt(4)) + "" + (1 + random.nextInt(4));
            } while (matrix.checkSquare(indexGenerated, mark, type));
        }
    }

    public void computerHardMove(Board matrix) {
        System.out.println("Making move level \"hard\"");

    }


    enum Type {
        HUMAN, COMPUTER_EASY, COMPUTER_MEDIUM, COMPUTER_HARD
    }
}
/*
(1, 3) (2, 3) (3, 3)
(1, 2) (2, 2) (3, 2)
(1, 1) (2, 1) (3, 1)
 */