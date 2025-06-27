// src/simulator/factory/ObjectFactory.java
package simulator.factory;

import java.util.*;
import simulator.model.ObjectItem;

public class ObjectFactory {
    public static ObjectItem createRandom(Map<String,Double> specs) {
        // Usa specs per generare valori randomici vicini a target
        Map<String,Double> props=new HashMap<>();
        specs.forEach((k,v)-> props.put(k, v + (Math.random()-0.5)*v*0.2));
        return new ObjectItem(props);
    }
}
