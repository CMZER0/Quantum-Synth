package utils;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

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
