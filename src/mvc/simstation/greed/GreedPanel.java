package mvc.simstation.greed;
import mvc.simstation.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GreedPanel extends WorldPanel implements ChangeListener{

    private final JPanel sliderPanel = new JPanel();
    private final JSlider greedSlider, growSlider, moveSlider;

    public GreedPanel(GreedFactory factory){
        super(factory);
        sliderPanel.setLayout(new GridLayout(4,1));
        sliderPanel.setOpaque(false);


        greedSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 50);
        greedSlider.setMajorTickSpacing(20);
        greedSlider.setMinorTickSpacing(10);
        greedSlider.setPaintTicks(true);
        greedSlider.setPaintLabels(true);
        greedSlider.setLabelTable(greedSlider.createStandardLabels(10));

        growSlider = new JSlider(JSlider.HORIZONTAL,0, 10, 5);
        growSlider.setMajorTickSpacing(2);
        growSlider.setMinorTickSpacing(1);
        growSlider.setPaintTicks(true);
        growSlider.setPaintLabels(true);
        growSlider.setLabelTable(growSlider.createStandardLabels(1));

        moveSlider = new JSlider(JSlider.HORIZONTAL,0, 50, 25);
        moveSlider.setMajorTickSpacing(5);
        moveSlider.setMinorTickSpacing(1);
        moveSlider.setPaintTicks(true);
        moveSlider.setPaintLabels(true);
        moveSlider.setLabelTable(moveSlider.createStandardLabels(5));

        greedSlider.addChangeListener(this);
        growSlider.addChangeListener(this);
        moveSlider.addChangeListener(this);

        JPanel pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        JPanel ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Greed"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(greedSlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Grow Back Rate"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(growSlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Move Energy"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(moveSlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);
        controlPanel.add(sliderPanel, BorderLayout.CENTER);
        setModel(model);
    }

    public void stateChanged(ChangeEvent e) {
        Meadow m = (Meadow) model;
        if (e.getSource() == greedSlider){
            m.updateGreed(greedSlider.getValue());
        }
        if (e.getSource() == growSlider){
            m.updateGrow(growSlider.getValue());
        }
        if (e.getSource() == moveSlider){
            m.updateMoveEnergy(moveSlider.getValue());
        }
        model.changed();
    }

    public void update(){
        Meadow m = (Meadow) model;
        greedSlider.setValue(m.getGreed());
        growSlider.setValue(m.getGrow());
        moveSlider.setValue(m.getMove());
        repaint();
    }

}
