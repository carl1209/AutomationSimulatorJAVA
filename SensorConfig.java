// src/simulator/config/SensorConfig.java
package simulator.config;

/**
 * Parametri di un sensore: id, deviazione standard del rumore, window del filtro.
 */
public class SensorConfig {
    private String id;
    private double noiseStdDev;
    private int filterWindow;

    // Costruttore per inizializzazione manuale
    public SensorConfig(String id, double noiseStdDev, int filterWindow) {
        this.id = id;
        this.noiseStdDev = noiseStdDev;
        this.filterWindow = filterWindow;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getNoiseStdDev() { return noiseStdDev; }
    public void setNoiseStdDev(double noiseStdDev) { this.noiseStdDev = noiseStdDev; }

    public int getFilterWindow() { return filterWindow; }
    public void setFilterWindow(int filterWindow) { this.filterWindow = filterWindow; }
}
