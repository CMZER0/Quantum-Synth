import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.*;

public class PluginWindow extends JFrame implements Runnable {
    private WaveForm wave = WaveForm.Sine;
    static final Dimension windowDimension = new Dimension(1045, 832);
    Container cp;
    protected OscillatorPanel oscOne;
    JComboBox<WaveForm> comboBoxOne;
    protected OscillatorPanel oscTwo;
    JComboBox<WaveForm> comboBoxTwo;
    protected ControlPanel knobOne;
    protected ControlPanel knobTwo;

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
        oscOne = new OscillatorPanel(Color.black);
        oscOne.setBounds(10, 10, (int) OscillatorPanel.oscDimension.getWidth(),
                (int) OscillatorPanel.oscDimension.getHeight());
        oscTwo = new OscillatorPanel(Color.black);
        oscTwo.setBounds(10, 10, (int) OscillatorPanel.oscDimension.getWidth(),
                (int) OscillatorPanel.oscDimension.getHeight());
        // Init Controls
        // Init JCombo Box
        comboBoxOne = new JComboBox<>(
                new WaveForm[] { WaveForm.Sine, WaveForm.Square, WaveForm.Saw, WaveForm.Triangle, WaveForm.Noise });
        comboBoxOne.setSelectedItem(WaveForm.Sine);
        // comboBoxOne.setSize(75, 25);
        comboBoxOne.addItemListener(l -> {
            if (l.getStateChange() == ItemEvent.SELECTED) {
                wave = (WaveForm) l.getItem();
            }
        });
        comboBoxOne.setVisible(true);
        comboBoxTwo = new JComboBox<>(
                new WaveForm[] { WaveForm.Sine, WaveForm.Square, WaveForm.Saw, WaveForm.Triangle, WaveForm.Noise });
        comboBoxTwo.setSelectedItem(WaveForm.Sine);
        // comboBoxTwo.setSize(75, 25);
        comboBoxTwo.addItemListener(l -> {
            if (l.getStateChange() == ItemEvent.SELECTED) {
                wave = (WaveForm) l.getItem();
            }
        });
        comboBoxOne.setVisible(true);
        knobOne = new ControlPanel();
        knobTwo = new ControlPanel();
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
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(cp);
        cp.add(oscOne);
        cp.add(oscTwo);
        cp.add(comboBoxOne);
        cp.add(knobOne);
        cp.add(comboBoxTwo);
        cp.add(knobTwo);

        // Pack Frame
        this.pack();
    }

    public static Dimension getWindowdimension() {
        return windowDimension;
    }

    @Override
    public synchronized void run() {
        // while (this.isActive()) {
        // }

    }

    private enum WaveForm {
        Sine, Square, Saw, Triangle, Noise
    }
}