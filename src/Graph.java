import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import utils.Utils;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.*;
import java.util.Random;

public class Graph extends JPanel implements ActionListener {
    static final Dimension oscDimension = new Dimension(500, 350);
    Timer timer;
    int xPos = 0;
    int velocity = 5;
    Color c;
    Image fader;
    // Wave Graph Vars
    Sine sines = new Sine();
    double[] waveData = sines.getWaveData();
    int xPts = sines.getxPts();
    int[] yPts = sines.getyPts();

    Graph() {
        this.c = Color.BLACK;
        this.setBorder(utils.Utils.WindowDesing.LINE_BORDER);
        this.setVisible(true);
        this.setSize(oscDimension);
        setLayout(null);
        this.setBackground(c);
        fader = new ImageIcon("OscScanner.png").getImage();
        timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D Osc = (Graphics2D) g;
        Osc.setColor(c);
        Osc.fillRect(0, 0, getWidth(), getHeight());
        Osc.setColor(Color.WHITE);
        Osc.setStroke(new BasicStroke(3));
        ///////////////////////////
        // Drawing WAV functions //
        ///////////////////////////
        Graphics2D Wav = (Graphics2D) g;
        Osc.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        drawWaveGraph(Wav);// draws actual graph
        Osc.drawImage(fader, xPos - 500, 0, this);
        Osc.drawRect(0, 0, getWidth(), getHeight());

    }

    public void drawWaveGraph(Graphics2D g2d) {
        double hstep = (double) getWidth() / (double) xPts; // hstep mean the horizontal distance between two xPts on
                                                            // the screen
        yPts = new int[xPts];
        for (int i = 0; i < xPts; ++i) {
            yPts[i] = (int) (waveData[i] * getHeight() / 2 * 0.95 + getHeight() / 2);
        }
        for (int i = 1; i < xPts; ++i) {
            // Set distance of line to be equal to one unit of x
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            // Set Y to be the vealue of sin(x)
            int y1 = yPts[i - 1];
            int y2 = yPts[i];
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (xPos >= getWidth() + 500)
            xPos = 0;
        else
            xPos += velocity;
        this.repaint();
    }
}
