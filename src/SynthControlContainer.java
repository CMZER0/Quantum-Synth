import javax.swing.JPanel;
import java.awt.Component;
import java.awt.*;

public class SynthControlContainer extends JPanel {
    protected Point mouseClickLocation /* = new Point(0, 0) */;
    protected boolean on;
    private QuantumSynth quantum;

    public SynthControlContainer(QuantumSynth quantum) {
        this.quantum = quantum;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public Component add(Component comp) {
        comp.addKeyListener(quantum.getKeyAdapter());
        return super.add(comp);
    }

    @Override
    public Component add(Component comp, int index) {
        comp.addKeyListener(quantum.getKeyAdapter());
        return super.add(comp, index);
    }

    @Override
    public Component add(String name, Component comp) {
        comp.addKeyListener(quantum.getKeyAdapter());
        return super.add(name, comp);
    }

    @Override
    public void add(Component comp, Object constraints) {
        comp.addKeyListener(quantum.getKeyAdapter());
        super.add(comp, constraints);
    }

    @Override
    public void add(Component comp, Object constraints, int index) {
        comp.addKeyListener(quantum.getKeyAdapter());
        super.add(comp, constraints, index);
    }
}
