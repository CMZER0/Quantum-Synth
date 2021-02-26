package synth;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class PluginWindow extends JFrame {
    static final Dimension windowDimension = new Dimension(1045, 832);
    Container cp;
    protected Oscillator oscOne;
    protected Oscillator oscTwo;
    protected KnobPanel knobOne;
    protected KnobPanel knobTwo;
    ImageIcon icon;
    Image backGround;
    JLabel label;

    public PluginWindow() {
        // Set Icon
        icon = new ImageIcon("ZNH.png");
        label = new JLabel();
        label.setIcon(icon);
        this.setTitle("QUANTUM Z3R0");
        // Set Background
        backGround = new ImageIcon("BackGround.png").getImage();
        // Init OscillatorPanels
        // oscOne = new Oscillator(Color.black);
        // oscOne.setBounds(10, 10, (int) Oscillator.oscDimension.getWidth(),
        // (int) Oscillator.oscDimension.getHeight());
        // oscTwo = new Oscillator(Color.black);
        // oscTwo.setBounds(10, 10, (int) Oscillator.oscDimension.getWidth(),
        // (int) Oscillator.oscDimension.getHeight());
        // Init Controls
        // knobOne = new KnobPanel();
        // knobTwo = new KnobPanel();
        // Init Jframe Properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(windowDimension);
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        // Add to Jframe
        this.setIconImage(icon.getImage());
        setLayout(new BorderLayout());
        cp = new JLabel(new ImageIcon("BackGround.png"));
        cp.setLayout(null);
        // new FlowLayout(FlowLayout.CENTER, 10, 10)
        this.add(cp);
        // cp.add(oscOne);
        // cp.add(oscTwo);
        // cp.add(knobOne);
        // cp.add(knobTwo);
        // Pack Frame
        this.pack();
    }

    public static Dimension getWindowdimension() {
        return windowDimension;
    }

    public int getFrequency() {
        return knobOne.getFrequency();
    }

}