package plague;

import mvc.*;
import simstation.*;

import javax.swing.*;
import java.awt.*;

public class PlagueView extends WorldView
{
    private JSlider initialInfectedSlider;
    private JSlider infectionProbabilitySlider;
    private JSlider populationSizeSlider;
    private JSlider fatalityRecoverySlider;

    private boolean slidersAdded = false;

    public PlagueView(Model model)
    {
        super(model);

        this.addHierarchyListener(e ->
        {
            if (slidersAdded)
            {
                return;
            }

            Container p = this.getParent();
            if (p != null)
            {
                for (int i = 0; i < p.getComponents().length; i++)
                {
                    Component c = p.getComponents()[i];
                    if (c != null && c.getBackground().equals(Color.PINK))
                    {
                        JPanel leftPanel = (JPanel) c;

                        JPanel slidersPanel = new JPanel();
                        slidersPanel.setLayout(new BoxLayout(slidersPanel, BoxLayout.Y_AXIS));
                        slidersPanel.setOpaque(false);

                        initialInfectedSlider = createSlider(0, 100, 50, "Initial % Infected");
                        infectionProbabilitySlider = createSlider(0, 100, 50, "Infection Probability");
                        populationSizeSlider = createSlider(0, 200, 100, "Initial Population Size");
                        fatalityRecoverySlider = createSlider(0, 500, 200, "Fatality/Recovery Time");

                        Dimension wider = new Dimension(380, 65);
                        setSliderSize(wider);

                        slidersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
                        slidersPanel.add(initialInfectedSlider);
                        slidersPanel.add(infectionProbabilitySlider);
                        slidersPanel.add(populationSizeSlider);
                        slidersPanel.add(fatalityRecoverySlider);
                        slidersPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                        JButton fatalButton = new JButton("Not Fatal");
                        fatalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                        slidersPanel.add(fatalButton);

                        leftPanel.add(slidersPanel);
                        leftPanel.revalidate();
                        leftPanel.repaint();
                        slidersAdded = true;

                        addSliderListeners();

                        break;
                    }
                }
            }
        });
    }

    private void setSliderSize(Dimension size)
    {
        initialInfectedSlider.setPreferredSize(size);
        initialInfectedSlider.setMaximumSize(size);
        infectionProbabilitySlider.setPreferredSize(size);
        infectionProbabilitySlider.setMaximumSize(size);
        populationSizeSlider.setPreferredSize(size);
        populationSizeSlider.setMaximumSize(size);
        fatalityRecoverySlider.setPreferredSize(size);
        fatalityRecoverySlider.setMaximumSize(size);
    }

    private JSlider createSlider(int min, int max, int initial, String title)
    {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initial);
        slider.setMajorTickSpacing((max - min) / 10);
        slider.setMinorTickSpacing((max - min) / 20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(title));
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        return slider;
    }

    private void addSliderListeners()
    {
        initialInfectedSlider.addChangeListener(e -> updatePlagueRun());
        infectionProbabilitySlider.addChangeListener(e -> updatePlagueRun());
        populationSizeSlider.addChangeListener(e -> updatePlagueRun());
        fatalityRecoverySlider.addChangeListener(e -> updatePlagueRun());
    }

    private void updatePlagueRun()
    {
        PlagueRun model = (PlagueRun) this.getModel();

        model.setInitialInfectedPercent(initialInfectedSlider.getValue());
        model.VIRULENCE = infectionProbabilitySlider.getValue();
        model.populationSize = populationSizeSlider.getValue();
        model.fatalRecovery = fatalityRecoverySlider.getValue();
    }

    @Override
    public void drawAgent(Agent a, Graphics g)
    {
        if (a instanceof Plague)
        {
            if (((Plague) a).isDead)
            {
                g.setColor(Color.BLACK);
            }
            else if (((Plague) a).isInfected())
            {
                g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.GREEN);
            }
            g.fillOval(a.getX(), a.getY(), 10, 10);
        }
        else
        {
            super.drawAgent(a, g);
        }
    }

    public void update()
    {
        PlagueRun model = (PlagueRun) this.getModel();
        initialInfectedSlider.setValue(model.getInitialInfectedPercent());
        infectionProbabilitySlider.setValue(model.VIRULENCE);
        populationSizeSlider.setValue(model.populationSize);
        fatalityRecoverySlider.setValue(model.fatalRecovery);

        revalidate();
        repaint();
    }
}