import java.io.*;
import javax.sound.sampled.*;
import javax.sound.midi.*;

public abstract class Oscillator {
    String type = "DEFAULT";
    public byte[] samples;
    byte sample;
    int BUFFER_SIZE;
    final int DEFAULT_BUFFER = 1024;
    final int DefaultFileStreamBufferSize = 4096;

    static int sampleRate = 44100;
    static int sampleSize = 16;
    static int numberOfChannels = 2;
    static int frameSizeInBytes = 4;
    // JAVA SOUND COMPONENTS //

    //////////////////
    // CONSTRUCTORS //
    //////////////////
    protected Oscillator() {
        setBufferSize(DEFAULT_BUFFER);
        setSamples(new byte[BUFFER_SIZE]);
    }

    /////////////
    // METHODS //
    /////////////

    ///////////////
    // ABSTRACTS //
    ///////////////
    public abstract byte singleSample(byte in);

    public abstract byte[] multiSample(byte[] in);

    ///////////////////////
    // GETTERS & SETTERS //
    ///////////////////////
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getSamples() {
        return samples;
    }

    public void setSamples(byte[] samples) {
        this.samples = samples;
    }

    public byte getSample() {
        return sample;
    }

    public void setSample(byte sample) {
        this.sample = sample;
    }

    public int getBufferSize() {
        return BUFFER_SIZE;
    }

    public void setBufferSize(int BUFFER_SIZE) {
        this.BUFFER_SIZE = BUFFER_SIZE;
    }

}