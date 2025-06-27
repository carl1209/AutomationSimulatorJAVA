// src/simulator/config/ThresholdConfig.java
package simulator.config;

/**
 * Definisce min/max per smistamento.
 */
public class ThresholdConfig {
    private double min;
    private double max;

    // Costruttore per inizializzazione manuale
    public ThresholdConfig(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() { return min; }
    public void setMin(double min) { this.min = min; }

    public double getMax() { return max; }
    public void setMax(double max) { this.max = max; }
}
