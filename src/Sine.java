class Sine {
    // VARS //
    private static final int SCALEFACTOR = 200;
    protected double[] waveData; // The actual sine values calculated.
    private int cycles = 1;
    private int xPts; // Total number of xPts that will be joined to form the wave
    private int[] yPts; // The actual coordinates (vertical) calculated based on the sine values

    public Sine() {
        // Constructor Sets the number of cycles to display on the screen
        setCycles(1);
        setWaveData(generateWaveData(getCycles()));
    }

    // GETTERS & SETTERS //
    public double[] getWaveData() {
        return waveData;
    }

    public void setWaveData(double[] waveData) {
        this.waveData = waveData;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getxPts() {
        return xPts;
    }

    public void setxPts(int xPts) {
        this.xPts = xPts;
    }

    public int[] getyPts() {
        return yPts;
    }

    public void setyPts(int[] yPts) {
        this.yPts = yPts;
    }

    // METHODS //
    public double[] generateWaveData(int cycles) {
        xPts = SCALEFACTOR * cycles * 2;
        double[] wave = new double[xPts];
        for (int i = 0; i < xPts; ++i) {
            if (i == xPts / 2) {
                // System.out.println("***** Rest half *****");
            }
            double radians = (Math.PI / SCALEFACTOR) * i;
            wave[i] = Math.sin(radians);
            // System.out.println("Radian = " + radians + " wave = " + wave[i]);
        }
        return wave;
    }

    public byte[] getByteArray() {
        byte[] rawOut = new byte[waveData.length];
        for (int i = 0; i < waveData.length; i++) {
            rawOut[i] = (byte) (waveData[i] * 100);
            System.out.println(((byte) rawOut[i]));
        }
        return rawOut;
    }

}
