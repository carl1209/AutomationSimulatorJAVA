// src/simulator/fault/FaultInjector.java
package simulator.fault;

import java.util.*;
import simulator.hardware.*;
import simulator.config.FaultSettings;

/**
 * Simulazione guasti: blocco nastro, sensori e attuatori malfunzionanti.
 */
public class FaultInjector {
    private FaultSettings fs;
    private Random r = new Random();

    public FaultInjector(FaultSettings fs) {
        this.fs = fs;
    }

    public void inject(ConveyorBelt b, List<Sensor> s,
                       List<Actuator> a, int cycle) {
        if (r.nextDouble() < fs.getBeltProb()) {
            b.setSpeed(0);
            System.out.println("[Fault] Belt jam at " + cycle);
        }
    }
}