// src/simulator/factory/ActuatorFactory.java
package simulator.factory;

import java.util.*;
import simulator.config.ActuatorConfig;
import simulator.hardware.Actuator;

public class ActuatorFactory {
    public static List<Actuator> createActuators(List<ActuatorConfig> cfgs) {
        List<Actuator> list=new ArrayList<>();
        for(ActuatorConfig c:cfgs) list.add(new Actuator(c));
        return list;
    }
}
