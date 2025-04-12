package simstation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import mvc.*;

public class WorldView extends View {

    public WorldView(Model model) {
        super(model);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color oldColor = g.getColor();
        World c = (World) model;
        ArrayList<Agent> agents = c.getAgents();
        for (Agent a : agents){
            if(!a.getName().equals("Observer")){
                drawAgent(a, g);
            }
        }
        g.setColor(oldColor);

    }

    public void drawAgent(Agent a, Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(a.getX(), a.getY(), 10, 10);
    }

}
