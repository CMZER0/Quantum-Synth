package utils;

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
}
