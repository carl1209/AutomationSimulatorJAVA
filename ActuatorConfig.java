// src/simulator/config/ActuatorConfig.java
package simulator.config;

/**
 * Parametri di un attuatore: id, ritardo (ms), forza (N).
 */
public class ActuatorConfig {
    private String id;
    private int delayMs;
    private double force;

    // Costruttore per inizializzazione manuale
    public ActuatorConfig(String id, int delayMs, double force) {
        this.id = id;
        this.delayMs = delayMs;
        this.force = force;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getDelayMs() { return delayMs; }
    public void setDelayMs(int delayMs) { this.delayMs = delayMs; }

    public double getForce() { return force; }
    public void setForce(double force) { this.force = force; }
}
