import javax.swing.*;
import java.awt.event.*;

public class QuantumSynth {

    private boolean shouldGenerate;
    private int wavePos;
    private int frequency;

    private AudioThread audio = new AudioThread(() -> {
        if (!shouldGenerate) {
            return null;
        }
        short[] s = new short[AudioThread.BUFFER_SIZE];
        for (int i = 0; i < AudioThread.BUFFER_SIZE; i++) {
            s[i] = (short) (Short.MAX_VALUE * Math.sin((2 * Math.PI * 440) / AudioInfo.SAMPLE_RATE * wavePos++));
        }
        return s;

    });

    QuantumSynth(PluginWindow frame) {

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!audio.isRunning()) {
                    shouldGenerate = true;
                    audio.triggerPlayback();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                shouldGenerate = false;
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                audio.close();
            }
        });
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
