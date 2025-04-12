package greed;

import simstation.WorldView;

import javax.swing.*;
import java.awt.*;

public class GreedView extends WorldView {

    private JLabel[][] grid;

    public GreedView(Meadow model){
        super(model);
        setLayout(new GridLayout(Meadow.dimY, Meadow.dimX));
        grid = new JLabel[Meadow.dimY][Meadow.dimX];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new JLabel();
                grid[i][j].setOpaque(true);
                if (model.field[i][j].energy > 75){
                    grid[i][j].setBackground(Color.GREEN);
                } else if (model.field[i][j].energy > 50){
                    grid[i][j].setBackground(new Color(0, 166, 0));
                } else if (model.field[i][j].energy > 25){
                    grid[i][j].setBackground(new Color(0, 66, 0));
                } else {
                    grid[i][j].setBackground(new Color(0, 0, 0));
                }

                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                add(grid[i][j]);
            }
        }
    }

    public void paintComponent(Graphics gc){

        Meadow mw = (Meadow) model;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (mw.field[i][j].energy > 75){
                    grid[i][j].setBackground(Color.GREEN);
                } else if (mw.field[i][j].energy > 50){
                    grid[i][j].setBackground(new Color(0, 166, 0));
                } else if (mw.field[i][j].energy > 25){
                    grid[i][j].setBackground(new Color(0, 66, 0));
                } else {
                    grid[i][j].setBackground(new Color(0, 0, 0));
                }

            }
        }
    }


}
