package utils;

import synth.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.*;

public class Utils {

    public static void invoke(Procedure procedure, boolean printStackTrace) {
        try {
            procedure.invoke();
        } catch (Exception e) {
            if (printStackTrace) {
                e.printStackTrace();
            }
        }
    }

    public static class WindowDesing {
        public static final Border LINE_BORDER = BorderFactory.createLineBorder(Color.WHITE);
    }

    public static class ParameterHandeling {
        public static final Robot PARAMETER_ROBOT;

        static {
            try {
                PARAMETER_ROBOT = new Robot();
            } catch (AWTException e) {
                throw new ExceptionInInitializerError("cannot construct robot instance");
            }
        }

        public static void addParameterMouseListeners(Component component, SynthControlContainer containor, int minVal,
                int maxVal, int valStep, RefWrapper<Integer> parameter, Procedure onChangeProcedure) {
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(
                            new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank_cursor");
                    component.setCursor(BLANK_CURSOR);
                    containor.setMouseClickLocation(e.getLocationOnScreen());
                    if (e.getClickCount() == 2) {
                        // reset val
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    component.setCursor(Cursor.getDefaultCursor());
                }
            });
            component.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (containor.getMouseClickLocation().y != e.getYOnScreen()) {
                        boolean mouseMovingUp = containor.getMouseClickLocation().y - e.getYOnScreen() > 0;
                        if (mouseMovingUp && parameter.val < maxVal) {
                            parameter.val += valStep;
                        } else if (!mouseMovingUp && parameter.val > maxVal) {
                            parameter.val -= valStep;
                        }
                        if (onChangeProcedure != null) {
                            invoke(onChangeProcedure, true);
                        }
                        PARAMETER_ROBOT.mouseMove(containor.getMouseClickLocation().x,
                                containor.getMouseClickLocation().y);
                    }
                }
            });
        }

    }

    public static class myMath {

        public static double frequencyToAngularFrequency(double freq) {
            return 2 * Math.PI * freq;
        }

        public static double getKeyFrequency(int keyNum) {
            return Math.pow(root(2, 12), keyNum - 49) * 440;
        }

        public static double root(double num, double root) {
            return Math.pow(Math.E, Math.log(num) / root);
        }
    }
}
