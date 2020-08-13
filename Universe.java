package life;

import java.util.Random;

class Universe {

    private int size;
    boolean[][] state;
    private int generation = 1;

    public Universe(int size) {
        this.size = size;
        state = new boolean[size][size];
    }

    public int getSize() {
        return size;
    }

    public int getGeneration() {
        return generation;
    }

    public void createFirstGeneration(){
        Random rd = new Random();
        for(int i = 0; i < state.length; i++){
            for(int j = 0; j < state[i].length; j++){
                state[i][j] = rd.nextBoolean();
            }
        }
    }

    public void grownNextGeneration() {
        boolean[][] nextState = new boolean[size][size];
        for(int i = 0; i < state.length; i++){
            for(int j = 0; j < state[i].length; j++) {
                int aliveNeighbours = countAliveNeighbours(i,j);
                nextState[i][j] = false;
                if (state[i][j]) {
                    if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        nextState[i][j] = true;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        nextState[i][j] = true;
                    }
                }
            }
        }
        state = nextState;
        generation++;
    }

    private int countAliveNeighbours(int row, int col) {
        int result = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (state[i < 0 ? size - 1 : i == size ? 0 : i]
                        [j < 0 ? size - 1 : j == size ? 0 : j]) {
                    result++;
                }
            }
        } return result - (state[row][col] ? 1 : 0);
    }

    void printGeneration() {
        for (boolean[] row : state) {
            for (boolean b : row) {
                System.out.print(b ? "O" : " ");
            }
            System.out.println();
        }
    }
    int countAlive() {
        int alive = 0;
        for (boolean[] row : state) {
            for (boolean b : row) {
                if (b) {
                    alive++;
                }
            }
        } return alive;
    }
}