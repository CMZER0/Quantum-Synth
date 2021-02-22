import javax.swing.JFrame;
import javax.swing.JLabel;
import utils.Utils;
import javax.swing.ImageIcon;
import java.awt.*;

public class PluginWindow extends JFrame implements Runnable {
    static final Dimension windowDimension = new Dimension(1045, 800);
    protected OscillatorPanel oscOne;
    protected OscillatorPanel oscTwo;
    protected ControlPanel knobPanel;
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
        oscTwo = new OscillatorPanel(Color.black);
        // Init Controls
        knobPanel = new ControlPanel();
        // Init Jframe Properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(windowDimension);
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setVisible(true);
        // Add to Jframe
        this.setIconImage(icon.getImage());
        this.add(oscOne);
        this.add(oscTwo);
        this.add(knobPanel);
        // Pack Frame
        this.pack();
        // Jframe Execute Properties
        this.setLocationRelativeTo(null);
    }

    public static Dimension getWindowdimension() {
        return windowDimension;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(backGround, 0, 0, null);
        // To paint a component, call it's paint method
        knobPanel.paint(knobPanel.getGraphics());
    }

    @Override
    public synchronized void run() {
        while (this.isActive()) {
            Utils.invoke(this::wait, false);
        }
    }
}