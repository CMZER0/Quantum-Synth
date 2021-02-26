package synth;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.*;

public class Oscillator extends SynthControlContainer {

    // static final Dimension oscDimension = new Dimension(500, 350);
    static final Dimension oscDimension = new Dimension(300, 100);
    Knob knobOne = new Knob();
    // Wave Graph Vars
    Sine sines = new Sine();
    double[] waveData = sines.getWaveData();
    int xPts = sines.getxPts();
    int[] yPts = sines.getyPts();
    // Osc Control Vars //
    private WaveTable wave = WaveTable.Sine;
    private double keyFrequency;
    private int wavetableStepSize;
    private int wavetableIndex;

    // Tone vars
    public JLabel tone = new JLabel("x0.00");;
    protected double toneOffset;
    public static final int TONE_OFFSET_LIMIT = 2000;

    //

    Oscillator(QuantumSynth quantum) {
        // Init JPanel
        super(quantum);
        this.setBackground(Color.BLACK);
        this.setBorder(synth.utils.Utils.WindowDesing.LINE_BORDER);
        this.setVisible(true);
        this.setSize(oscDimension);
        setLayout(null);
        // Init JCombo Box
        JComboBox<WaveTable> comboBox = new JComboBox<>(WaveTable.values());
        comboBox.setSelectedItem(WaveTable.Sine);
        comboBox.setSize(75, 25);
        comboBox.addItemListener(l -> {
            if (l.getStateChange() == ItemEvent.SELECTED) {
                wave = (WaveTable) l.getItem();
            }
        });
        // Tone Control
        knobOne.setLocation(105, 35);
        add(knobOne);
        add(comboBox);
        tone = new JLabel("x0.00");
        tone.setForeground(Color.WHITE);
        tone.setBounds(165, 65, 50, 25);
        tone.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(
                        new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank_cursor");
                setCursor(BLANK_CURSOR);
                mouseClickLocation = e.getLocationOnScreen();
                if (e.getClickCount() == 2) {
                    toneOffset = 0d;
                    tone.setText(("x" + String.format("%.3f", getToneOffset())));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        tone.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                boolean mouseMovingUp = mouseClickLocation.y - e.getYOnScreen() > 0;
                if (mouseClickLocation.y != e.getYOnScreen()) {
                    if (mouseMovingUp && toneOffset < TONE_OFFSET_LIMIT) {
                        toneOffset++;
                    } else if (!mouseMovingUp && toneOffset > -TONE_OFFSET_LIMIT) {
                        toneOffset--;
                    }
                    applyToneOffset();
                    tone.setText(("x" + String.format("%.3f", getToneOffset())));
                }
                synth.utils.Utils.ParameterHandeling.PARAMETER_ROBOT.mouseMove(mouseClickLocation.x,
                        mouseClickLocation.y);
            }
        });
        add(tone);
        JLabel toneText = new JLabel("Tone");
        toneText.setForeground(Color.WHITE);

        toneText.setBounds(172, 40, 75, 25);
        add(toneText);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        knobOne.paint(knobOne.getGraphics());
    }

    public void setKeyFrequency(double frequency) {
        keyFrequency = frequency;
        applyToneOffset();
    }

    public double getToneOffset() {
        return toneOffset / 1000d;
    }

    public double nextSample() {
        double sample = wave.getSamples()[wavetableIndex];
        wavetableIndex = (wavetableIndex + wavetableStepSize) % WaveTable.SIZE;
        return sample;
    }

    private void applyToneOffset() {
        wavetableStepSize = (int) (WaveTable.SIZE * (keyFrequency * Math.pow(2, getToneOffset()))
                / AudioInfo.SAMPLE_RATE);
    }

    // public double nextSample() {
    // double tDivP = (wavePos++ / (double) AudioInfo.SAMPLE_RATE) / (1d /
    // frequency);
    // switch (wave) {
    // case Sine:
    // return Math.sin(utils.Utils.myMath.frequencyToAngularFrequency(frequency) *
    // (wavePos - 1)
    // / AudioInfo.SAMPLE_RATE);
    // case Square:
    // return
    // Math.signum(Math.sin(utils.Utils.myMath.frequencyToAngularFrequency(frequency)
    // * (wavePos - 1)
    // / AudioInfo.SAMPLE_RATE));
    // case Saw:
    // return 2d * (tDivP - Math.floor(0.5 + tDivP));
    // case Triangle:
    // return 2d * Math.abs(2d * (tDivP - Math.floor(0.5 + tDivP))) - 1;
    // case Noise:
    // return rdmNum.nextDouble();
    // default:
    // throw new RuntimeException("Oscillator set to unkown waveform");
    // }
    // }
}
