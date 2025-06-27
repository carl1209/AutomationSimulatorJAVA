// src/simulator/model/SensorData.java
package simulator.model;

/**
 * Dati letti dal sensore.
 */
public class SensorData {
    private String sensorId; private double value;
    public SensorData(String id,double v){this.sensorId=id;this.value=v;}
    public String getSensorId(){return sensorId;}
    public double getValue(){return value;}
    public boolean isFault(){return Double.isNaN(value);} // esempio
}

