// src/simulator/factory/SensorFactory.java
package simulator.factory;

import java.util.*;
import simulator.config.SensorConfig;
import simulator.hardware.Sensor;

public class SensorFactory {
    public static List<Sensor> createSensors(List<SensorConfig> cfgs) {
        List<Sensor> list=new ArrayList<>();
        for(SensorConfig c:cfgs) list.add(new Sensor(c));
        return list;
    }
}
