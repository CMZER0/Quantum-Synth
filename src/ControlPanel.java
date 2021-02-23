import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class ControlPanel extends JPanel {
    final public Dimension controllDimension = new Dimension(60, 60);

    protected Knob knobOne;
    protected Knob knobTwo;

    ControlPanel() {

        // Init Knobs
        knobOne = new Knob();
        // Init Controll Panel
        this.setVisible(true);
        this.setPreferredSize(controllDimension);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(knobOne);
    }

    public Dimension getControllDimension() {
        return controllDimension;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // knobOne.paint(knobOne.getGraphics());
    }

}
