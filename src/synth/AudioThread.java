package synth;

import java.util.function.Supplier;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import utils.Utils;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioThread extends Thread {

    static final int BUFFER_SIZE = 512;
    static final int BUFFER_COUNT = 8;

    private final Supplier<short[]> bufferSupplier;
    private final int[] buffers = new int[BUFFER_COUNT];
    private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
    private final long context = alcCreateContext(device, new int[1]);
    private final int source;

    private int bufferIndex;
    private boolean closed;
    private boolean running;

    @Override
    public synchronized void run() {
        while (!closed) {
            while (!running) {
                Utils.invoke(this::wait, false);
            }
            int processedBufs = alGetSourcei(source, AL_BUFFERS_PROCESSED);
            for (int i = 0; i < processedBufs; i++) {
                short[] samples = bufferSupplier.get();
                if (samples == null) {
                    running = false;
                    break;
                }
                alDeleteBuffers(alSourceUnqueueBuffers(source));
                buffers[bufferIndex] = alGenBuffers();
                bufferSamples(samples);
            }
            if (alGetSourcef(source, AL_SOURCE_STATE) != AL_PLAYING) {
                alSourcePlay(source);
            }
            catchInternalException();
        }
        alDeleteSources(source);
        alDeleteBuffers(buffers);
        alcDestroyContext(context);
        alcCloseDevice(device);
        System.exit(0);
    }

    synchronized void triggerPlayback() {
        running = true;
        notify();
    }

    void close() {
        closed = true;
        // break out of loop
        triggerPlayback();
    }

    AudioThread(Supplier<short[]> bufferSupplier) {
        this.bufferSupplier = bufferSupplier;
        alcMakeContextCurrent(context);
        AL.createCapabilities(ALC.createCapabilities(device));
        source = alGenSources();
        for (int i = 0; i < BUFFER_COUNT; i++) {
            // buffer samples
            bufferSamples(new short[0]);
        }
        alSourcePlay(source);
        // catch internal exeptions
        catchInternalException();
        start();
    }

    public boolean isRunning() {
        return running;
    }

    private void bufferSamples(short[] samples) {
        int buf = buffers[bufferIndex++];
        alBufferData(buf, AL_FORMAT_MONO16, samples, AudioInfo.SAMPLE_RATE);
        alSourceQueueBuffers(source, buf);
        bufferIndex %= BUFFER_COUNT; // Resets buffer so it goes back to 0 when it's 8
    }

    private void catchInternalException() {
        int err = alcGetError(device);
        if (err != ALC_NO_ERROR) {
            throw new OpenALException(err);
        }
    }
}
