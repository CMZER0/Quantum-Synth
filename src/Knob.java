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
    double position = Math.toRadians(0);
    double knobOffset;

    Knob() {
        knobImage = new ImageIcon("PotKnob.png").getImage();
        this.setVisible(true);
        ClickListener click = new ClickListener();
        DragListener drag = new DragListener();
        this.addMouseListener(click);
        this.addMouseMotionListener(drag);
        this.setSize(knobDimension);
        imageCorner = new Point(0, 0);
        imageCenter = new Point((int) imageCorner.getX() + (knobDimension.width / 2),
                (int) imageCorner.getY() + (knobDimension.height / 2));
        super.repaint();

    }

    // METHODS //
    public void doRepaint() {
        this.update(this.getGraphics());

    }

    public double getKnobOffset() {
        return knobOffset;
    }

    public boolean minMaxValue() {
        // Checks for min/max value
        Double min = Math.toRadians(-127);
        Double max = Math.toRadians(127);
        if (position <= min) {
            setPosition(min);
            return false;
        } else if (position >= max) {
            setPosition(max);
            return false;
        }
        return true;
    }

    public void ckeckMovement(boolean mouseMovingUp) {
        if (mouseMovingUp) {

            knobOffset += Math.pow(((position / Math.toRadians(Oscillator.TONE_OFFSET_LIMIT / 360)) * 4), 1);

        } else if (!mouseMovingUp) {

            knobOffset -= Math.pow(((position / Math.toRadians(Oscillator.TONE_OFFSET_LIMIT / 360)) * 4), 1);

        }
        // apply ofset
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
        // super.paint(g);
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
            if (m.getClickCount() == 2) {
                position = Math.toRadians(0);
                knobOffset = 0d;
                doRepaint();
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent m) {
            boolean mouseMovingUp = clickPoint.y - m.getYOnScreen() > 0;
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
            ckeckMovement(mouseMovingUp);

            // debuging
            // System.out.println(Math.toDegrees(value) + ", " + Math.toDegrees(position));
            clickPoint = imageCenter;
            // call repaint
            doRepaint();
        }

    }

}