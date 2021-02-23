import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class Knob extends JPanel {
    final Dimension knobDimension = new Dimension(60, 60);
    final Dimension superDimension = PluginWindow.getWindowdimension();
    AffineTransform identity = new AffineTransform();
    Image knobImage;
    Point imageCorner;
    Point imageCenter;
    Point clickPoint;
    Double value = 0.0;
    double position = Math.toRadians(-127);

    Knob() {
        knobImage = new ImageIcon("PotKnob.png").getImage();
        this.setVisible(true);
        ClickListener click = new ClickListener();
        DragListener drag = new DragListener();
        this.addMouseListener(click);
        this.addMouseMotionListener(drag);
        this.setPreferredSize(knobDimension);
        imageCorner = new Point(0, 0);
        imageCenter = new Point((int) imageCorner.getX() + (knobDimension.width / 2),
                (int) imageCorner.getY() + (knobDimension.height / 2));
        super.repaint();

    }

    // METHODS //
    public void doRepaint() {
        this.update(this.getGraphics());
    }

    public void minMaxValue() {
        // Checks for min/max value
        Double min = Math.toRadians(-127);
        Double max = Math.toRadians(127);
        if (position <= min) {
            setPosition(min);
        } else if (position >= max) {
            setPosition(max);
        }
    }

    // GETTERS & SETTERS //
    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        AffineTransform trans = new AffineTransform();
        trans.rotate(position, imageCenter.getX(), imageCenter.getY());
        g2D.drawImage(knobImage, trans, this);

    }

    public class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent m) {
            // gets mouse position
            clickPoint = m.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent m) {
            value = ((m.getY() - clickPoint.getY())) / ((superDimension.getHeight()) / (double) .4);
            position -= value;
            // Removing unneeded rotations
            if (position > Math.toRadians(360)) {
                position -= Math.toRadians(360);
            } else if (position < Math.toRadians(-360)) {
                position += Math.toRadians(360);
            }
            // checking position min / max
            minMaxValue();
            // debuging
            // System.out.println(Math.toDegrees(value) + ", " + Math.toDegrees(position));
            // call repaint
            if (PluginWindow.quantum.getFrequency() != (int) position) {
                PluginWindow.quantum.setFrequency((int) (440 * getPosition()));
            }
            doRepaint();
        }
    }
}