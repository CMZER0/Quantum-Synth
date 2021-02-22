import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class ControlPanel extends JPanel {
    final public Dimension controllDimension = new Dimension(800, 325);
    protected Knob knobOne;
    protected Knob knobTwo;

    ControlPanel() {
        // Init Knobs
        knobOne = new Knob();
        knobTwo = new Knob();
        // Init Controll Panel
        this.setVisible(true);
        this.setPreferredSize(controllDimension);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.add(knobOne);
        this.add(knobTwo);
    }

    public Dimension getControllDimension() {
        return controllDimension;
    }

    @Override
    public void paint(Graphics g) {
        knobOne.paint(knobOne.getGraphics());
        knobTwo.paint(knobTwo.getGraphics());
    }

}
