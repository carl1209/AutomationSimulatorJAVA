// src/simulator/config/FaultSettings.java
package simulator.config;

/**
 * Impostazioni per iniezione guasti.
 */
public class FaultSettings {
    private double beltProb;

    // Costruttore di default
    public FaultSettings() {}

    // Costruttore personalizzato
    public FaultSettings(double beltProb) {
        this.beltProb = beltProb;
    }

    public double getBeltProb() { return beltProb; }
    public void setBeltProb(double beltProb) { this.beltProb = beltProb; }
}
