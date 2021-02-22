public class Carrier extends Oscillator {

    // CONSTRUCTORS //
    public Carrier() {
        setType("CARRIER");
    }
    // METHODS //

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
