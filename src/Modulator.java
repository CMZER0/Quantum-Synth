public class Modulator extends Oscillator {

    // CONSTRUCTORS //
    public void Modulator() {
        // setBufferSize(DEFAULT_BUFFER);
        setType("MODULATOR");
    }

    // ABSTRACTS //
    @Override
    public byte singleSample(byte in) {
        // DO SOMTHING TO SAMPLE
        return in;
    }

    public byte[] multiSample(byte[] in) {
        for (byte b : in) {
            // DO SOMTHING TO SAMPLE
        }
        return in;
    }
}
