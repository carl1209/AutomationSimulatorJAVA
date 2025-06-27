// src/simulator/hardware/Actuator.java
package simulator.hardware;

import simulator.model.ActuationCommand;
import simulator.config.ActuatorConfig;

/**
 * Attuatore pneumatico: delay ms, forza N.
 */
public class Actuator {
    private String id;
    private int delayMs;
    private double force;

    public Actuator(ActuatorConfig cfg) {
        this.id = cfg.getId();
        this.delayMs = cfg.getDelayMs();
        this.force = cfg.getForce();
    }
    public void actuate(ActuationCommand cmd) {
        try { Thread.sleep(delayMs); } catch (InterruptedException ignored) {}
        System.out.println("Actuator["+id+"] cmd="+cmd+" force="+force+"N");
    }
}
