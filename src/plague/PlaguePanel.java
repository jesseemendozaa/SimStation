package plague;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simstation.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class PlaguePanel extends WorldPanel implements ChangeListener, ActionListener {


    private final JPanel sliderPanel = new JPanel();

    private final JSlider initialInfectedSlider;
    private final JSlider infectionProbabilitySlider;
    private final JSlider populationSizeSlider;
    private final JSlider fatalityRecoverySlider;

    private final JButton notFatalButton;

    public PlaguePanel(PlagueFactory factory){
        super(factory);
        sliderPanel.setLayout(new GridLayout(5,1));
        sliderPanel.setOpaque(false);


        initialInfectedSlider = new JSlider(JSlider.HORIZONTAL,0, 100, 50);
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setMinorTickSpacing(2);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.setLabelTable(initialInfectedSlider.createStandardLabels(10));

        infectionProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0, 100, 50);
        infectionProbabilitySlider.setMajorTickSpacing(10);
        infectionProbabilitySlider.setMinorTickSpacing(2);
        infectionProbabilitySlider.setPaintTicks(true);
        infectionProbabilitySlider.setPaintLabels(true);
        infectionProbabilitySlider.setLabelTable(infectionProbabilitySlider.createStandardLabels(10));

        populationSizeSlider = new JSlider(JSlider.HORIZONTAL,0, 200, 100);
        populationSizeSlider.setMajorTickSpacing(20);
        populationSizeSlider.setMinorTickSpacing(4);
        populationSizeSlider.setPaintTicks(true);
        populationSizeSlider.setPaintLabels(true);
        populationSizeSlider.setLabelTable(populationSizeSlider.createStandardLabels(20));
        populationSizeSlider.setPreferredSize(new Dimension(380, 45));
        populationSizeSlider.setMaximumSize(new Dimension(380, 45));

        fatalityRecoverySlider = new JSlider(JSlider.HORIZONTAL,0, 500, 250);
        fatalityRecoverySlider.setMajorTickSpacing(50);
        fatalityRecoverySlider.setMinorTickSpacing(10);
        fatalityRecoverySlider.setPaintTicks(true);
        fatalityRecoverySlider.setPaintLabels(true);
        fatalityRecoverySlider.setLabelTable(fatalityRecoverySlider.createStandardLabels(100));
        fatalityRecoverySlider.setPreferredSize(new Dimension(380, 45));
        fatalityRecoverySlider.setMaximumSize(new Dimension(380, 45));

        notFatalButton = new JButton("Not Fatal");

        initialInfectedSlider.addChangeListener(this);
        infectionProbabilitySlider.addChangeListener(this);
        populationSizeSlider.addChangeListener(this);
        fatalityRecoverySlider.addChangeListener(this);

        notFatalButton.addActionListener(this);

        JPanel pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        JPanel ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Initial % Infected"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(initialInfectedSlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Infection Probability"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(infectionProbabilitySlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Initial Population Size"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(populationSizeSlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(new JLabel("Fatality/Recovery Time"));
        pp.add(ppp, BorderLayout.NORTH);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(fatalityRecoverySlider);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);

        pp = new JPanel();
        pp.setLayout(new BorderLayout());
        pp.setOpaque(false);

        ppp = new JPanel();
        ppp.setOpaque(false);
        ppp.add(notFatalButton);
        pp.add(ppp, BorderLayout.CENTER);

        sliderPanel.add(pp);
        controlPanel.add(sliderPanel, BorderLayout.CENTER);
        setModel(model);
    }

    public void stateChanged(ChangeEvent e) {
        PlagueRun p = (PlagueRun) model;
        if (e.getSource() == initialInfectedSlider){
            p.setInitialInfectedPercent(initialInfectedSlider.getValue());
        }
        if (e.getSource() == infectionProbabilitySlider){
            p.setInfectionProbability(infectionProbabilitySlider.getValue());
        }
        if (e.getSource() == populationSizeSlider){
            p.setInitialPopulationSize(populationSizeSlider.getValue());
        }
        if (e.getSource() == fatalityRecoverySlider){
            p.setFatalRecovery(fatalityRecoverySlider.getValue());
        }
        model.changed();
    }

    public void update(){
        PlagueRun p = (PlagueRun) model;
        initialInfectedSlider.setValue(p.getInitialInfectedPercent());
        infectionProbabilitySlider.setValue(p.getInfectionProbability());
        populationSizeSlider.setValue(p.getInitialPopulationSize());
        fatalityRecoverySlider.setValue(p.getFatalRecovery());
        repaint();
    }
}
