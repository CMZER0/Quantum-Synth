import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ItemEvent;
import java.util.Random;

public class OscillatorControl extends JPanel {

    private WaveForm wave = WaveForm.Sine;
    JComboBox<WaveForm> comboBox;

    private static final double FREQUENCY = 440;
    private static Random rdmNum = new Random();
    private int wavePos;

    public OscillatorControl() {
        // Init JCombo Box
        comboBox = new JComboBox<>(
                new WaveForm[] { WaveForm.Sine, WaveForm.Square, WaveForm.Saw, WaveForm.Triangle, WaveForm.Noise });
        comboBox.setSelectedItem(WaveForm.Sine);
        comboBox.setSize(75, 25);
        comboBox.addItemListener(l -> {
            if (l.getStateChange() == ItemEvent.SELECTED) {
                wave = (WaveForm) l.getItem();
            }
        });
        add(comboBox);
        setSize(279, 100);
        // setLayout(null);
    }

    private enum WaveForm {
        Sine, Square, Saw, Triangle, Noise
    }

    public double nextSample() {
        double tDivP = (wavePos++ / (double) AudioInfo.SAMPLE_RATE) / (1d / FREQUENCY);
        switch (wave) {
            case Sine:
                return Math.sin(utils.Utils.myMath.frequencyToAngularFrequency(FREQUENCY) * (wavePos - 1)
                        / AudioInfo.SAMPLE_RATE);
            case Square:
                return Math.signum(Math.sin(utils.Utils.myMath.frequencyToAngularFrequency(FREQUENCY) * (wavePos - 1)
                        / AudioInfo.SAMPLE_RATE));
            case Saw:
                return 2d * (tDivP - Math.floor(0.5 + tDivP));
            case Triangle:
                return 2d * Math.abs(2d * (tDivP - Math.floor(0.5 + tDivP))) - 1;
            case Noise:
                return rdmNum.nextDouble();
            default:
                throw new RuntimeException("Oscillator set to unkown waveform");
        }
    }
}
