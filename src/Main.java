public class Main {

    public static void main(String args[]) {
        //
        PluginWindow window = new PluginWindow();
        QuantumSynth quantum = new QuantumSynth(window);
        new Thread(window).start();
        //
    }
}
