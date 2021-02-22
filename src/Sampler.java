import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sampler extends Carrier {

    // CONSTRUCTORS //
    public Sampler() {
        setType("SAMPLER");
    }

    public void readSample() {
        int totalFramesRead = 0;
        File fileIn = new File("newSample");
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(fileIn);
            int bytesPerFrame = in.getFormat().getFrameSize() == AudioSystem.NOT_SPECIFIED ? 1
                    : in.getFormat().getFrameSize();
            int numBytesRead = 0;
            int numFramesRead = 0;
            // Try to read numBytes bytes from the file.
            while ((numBytesRead = in.read(samples)) != -1) {
                // Calculate the number of frames actually read.
                numFramesRead = numBytesRead / bytesPerFrame;
                totalFramesRead += numFramesRead;
            }
            // Here, do something useful with the audio data that's
            // now in the audioBytes array...
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
