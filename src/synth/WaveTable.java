package synth;

public enum WaveTable {
    Sine, Square, Saw, Triangle;

    static final int SIZE = 8192;

    private final float[] samples = new float[SIZE];

    static {
        final double FUNDEMENTAL_FREQUENCY = 1d / (SIZE / (double) AudioInfo.SAMPLE_RATE);
        for (int i = 0; i < SIZE; i++) {
            double t = i / (double) AudioInfo.SAMPLE_RATE;
            double tDivP = t / (1d / FUNDEMENTAL_FREQUENCY);
            Sine.samples[i] = (float) Math
                    .sin(synth.utils.Utils.myMath.frequencyToAngularFrequency(FUNDEMENTAL_FREQUENCY) * t);
            Square.samples[i] = (float) Math
                    .signum(Math.sin(synth.utils.Utils.myMath.frequencyToAngularFrequency(FUNDEMENTAL_FREQUENCY) * t));
            Saw.samples[i] = (float) (2d * (tDivP - Math.floor(0.5 + tDivP)));
            Triangle.samples[i] = (float) (2d * Math.abs(2d * (tDivP - Math.floor(0.5 + tDivP))) - 1d);
        }
    }

    float[] getSamples() {
        return samples;
    }
}
