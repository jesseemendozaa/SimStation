package mvc.simstation.greed;

import mvc.simstation.Agent;
import mvc.simstation.WorldView;

import javax.swing.*;
import java.awt.*;

public class GreedView extends WorldView {

    private circleJLabel[][] grid;

    private class circleJLabel extends JLabel{

        boolean hasCow = false;
        boolean hasAliveCow = false;

        public circleJLabel(){
            hasCow = false;
            hasAliveCow = false;
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            if (hasCow){
                int width = getWidth();
                int height = getHeight();
                int circleDiameter = Math.min(width, height)/2;
                int x = (width - circleDiameter)/2;
                int y = (height - circleDiameter)/2;
                if (hasAliveCow){
                    g.setColor(Color.RED);
                    g.fillOval(x, y, circleDiameter, circleDiameter);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillOval(x, y, circleDiameter, circleDiameter);
                }
            }

        }
    }

    public GreedView(Meadow model){
        super(model);
        setLayout(new GridLayout(Meadow.dimY, Meadow.dimX));
        grid = new circleJLabel[Meadow.dimY][Meadow.dimX];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new circleJLabel();
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
                grid[i][j].hasCow = false;
                grid[i][j].hasAliveCow = false;
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

        for (Agent a : mw.getAgents()){
            if (a.getName().equals("Cow")){
                Cow c = (Cow) a;
                grid[c.getY()][c.getX()].hasCow = true;
                if (c.alive){
                    grid[c.getY()][c.getX()].hasAliveCow = true;
                }
            }
        }
    }


}
