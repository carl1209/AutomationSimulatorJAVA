// src/simulator/model/ObjectItem.java
package simulator.model;

import java.util.*;
/**
 * Rappresenta un oggetto: proprietà fisiche e rumore.
 */
public class ObjectItem {
    private Map<String,Double> props;
    public ObjectItem(Map<String,Double> props){this.props=props;}
    public double getProperty(String id){return props.getOrDefault(id,0.0);}
}
