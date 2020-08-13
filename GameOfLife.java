package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    int size = 20;
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private final JButton reset;
    private final JToggleButton toggleButton;
    private JLabel[][] labels;
    private JPanel panel;
    private Universe universe = new Universe(size);
    private Universe saved = new Universe(size);

    public GameOfLife() {
        super("Game of life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLocationRelativeTo(null);

        Timer timer = new Timer(500, actionEvent -> {
            universe.grownNextGeneration();
        });


        genLabel = new JLabel("Generation #0");
        genLabel.setName("GenerationLabel");
        genLabel.setBounds(10, 0, 100, 25);
        add(genLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(10, 25, 100, 25);
        add(aliveLabel);

        reset = new JButton("Reset");
        reset.setName("ResetButton");
        reset.setBounds(180,0,100,25);
        add(reset);

        reset.addActionListener(actionEvent -> {
            if (reset.isSelected()) {
                reset.doClick();
            }
            universe = new Universe(20);
            universe.createFirstGeneration();
        });

        toggleButton = new JToggleButton("Stop");
        toggleButton.setName("PlayToggleButton");
        toggleButton.setBounds(180,25,100,25);
        add(toggleButton);

        toggleButton.addActionListener(event -> {
            if (toggleButton.isSelected()) {
                toggleButton.setText("Start");
                timer.stop();
            } else {
                toggleButton.setText("Stop");
                timer.start();
            }
        });

        setLayout(null);
        setVisible(true);
    }

    public void createField(int size) {
        panel = new JPanel();
        panel.setBounds(15, 50, 250, 250);
        panel.setLayout(new GridLayout(size, size, 0, 0));
        labels = new JLabel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setBackground(Color.BLACK);
                panel.add(labels[i][j]);
            }
        }
        add(panel);
    }

    public void refresh(boolean[][] universe, int gen, int alive) {
        genLabel.setText("Generation #" + gen);
        aliveLabel.setText("Alive: " + alive);

        int size = universe.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                labels[i][j].setOpaque(universe[i][j]);
            }
        }
        panel.repaint();
    }

    public void startGame() throws InterruptedException {
        universe.createFirstGeneration();

        while(!toggleButton.isSelected()){
            createField(size);
            refresh(universe.state, universe.getGeneration(),universe.countAlive());
            Thread.sleep(1000);
            universe.grownNextGeneration();
        }
    }
}