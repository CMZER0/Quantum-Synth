public class LFO extends Carrier {

    // VARS //
    protected double[] waveData; // The actual sine values calculated.
    private int amplitude = 24; // volume
    private int frequency = 440; // frequency

    // CONSTRUCTORS //
    public LFO() {
        setType("Sin.wav");
        setFrequency(440);
    }

    public LFO(int HZ) {
        setType("Sin.wav");
        setFrequency(HZ);
    }

    // METHODS //
    public double[] generateWaveData(int amplitude, int frequency) {
        double[] wave = new double[BUFFER_SIZE];
        for (int i = 0; i < BUFFER_SIZE; ++i) {
            // double radians = (Math.PI / sampleRate) * i;
            // wave[i] = Math.sin(radians);
            wave[i] = (amplitude * Math.sin((2 * Math.PI * i * frequency) / sampleRate));
        }
        return wave;
    }

    public byte[] generateByteArray() {
        byte[] rawOut = new byte[waveData.length];
        for (int i = 0; i < waveData.length; i++) {
            rawOut[i] = (byte) (waveData[i] * 100);
            // System.out.println(((byte) rawOut[i]));
        }
        System.out.println("Total bytes: " + rawOut.length);
        return rawOut;
    }

    // ABSTRACTS //
    @Override
    public byte[] multiSample(byte[] samples) {
        return samples;
    }

    // GETTERS & SETTERS //
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double[] getWaveData() {
        return waveData;
    }

    public void setWaveData(double[] waveData) {
        this.waveData = waveData;
    }

}