package synth;

import java.awt.event.*;
import java.util.HashMap;
import utils.Utils;

public class QuantumSynth {

    private static final HashMap<Character, Double> KEY_FREQUENCIES = new HashMap<>();

    private boolean shouldGenerate;

    private final Oscillator[] oscillators = new Oscillator[3];

    private final Graph[] graphs = new Graph[3];

    private final PluginWindow window = new PluginWindow();

    private AudioThread audio = new AudioThread(() -> {
        if (!shouldGenerate) {
            return null;
        }
        short[] s = new short[AudioThread.BUFFER_SIZE];
        for (int i = 0; i < AudioThread.BUFFER_SIZE; i++) {
            double d = 0.0;
            for (Oscillator o : oscillators) {
                d += o.nextSample() / oscillators.length;
            }
            s[i] = (short) (Short.MAX_VALUE * d);
        }
        return s;
    });

    private final KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!audio.isRunning()) {
                for (Oscillator o : oscillators) {
                    try {
                        o.setKeyFrequency(KEY_FREQUENCIES.get(e.getKeyChar()));
                        shouldGenerate = true;
                        audio.triggerPlayback();
                    } catch (Exception f) {
                        return;
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            shouldGenerate = false;
        }
    };

    static {
        final int STARTING_KEY = 16;
        final int KEY_FREQUENCY_INCRIMENT = 2;
        final char[] KEYS = "zxcvbnm,./asdfghjkl;'qwertyuiop[]".toCharArray();
        for (int i = STARTING_KEY, key = 0; i < KEYS.length * KEY_FREQUENCY_INCRIMENT
                + STARTING_KEY; i += KEY_FREQUENCY_INCRIMENT, key++) {
            KEY_FREQUENCIES.put(KEYS[key], Utils.myMath.getKeyFrequency(i));
        }
    }

    QuantumSynth() {

        int y = 35;
        for (int i = 0; i < oscillators.length; i++) {
            oscillators[i] = new Oscillator(this);
            oscillators[i].setLocation(5, y);
            window.cp.add(oscillators[i]);
            graphs[i] = new Graph();
            graphs[i].setLocation(window.getWidth() - (int) graphs[i].oscDimension.getWidth() - 50, y);
            window.cp.add(graphs[i]);
            y += 170;
        }
        window.addKeyListener(keyAdapter);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                audio.close();
            }
        });
        window.repaint();
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }
}
