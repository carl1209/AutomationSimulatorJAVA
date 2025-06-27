// src/simulator/model/EventLog.java
package simulator.model;

/**
 * Log di eventi (encoder, operazioni, guasti).
 */
public class EventLog {
    private String type; private String data;
    public EventLog(String t,String d){this.type=t;this.data=d;}
    public String toString(){return type+":"+data;}
}
