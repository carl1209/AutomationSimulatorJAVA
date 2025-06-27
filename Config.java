// src/simulator/config/Config.java
package simulator.config;

import java.util.*;
/**
 * DTO di configurazione della simulazione.
 */
public class Config {
    private double beltSpeed;
    private int encoderResolution;
    private int cycleDelayMs;
    private int simulationCycles;
    private double kp, ki, kd;
    private long recoveryTimeout;
    private Map<String, ThresholdConfig> thresholds;
    private List<SensorConfig> sensorsConfig;
    private List<ActuatorConfig> actuatorsConfig;
    private FaultSettings faultSettings;
    private Map<String, Double> objectSpecs;

    // Getters utilizzati in Main.java
    public double getBeltSpeed() {
        return beltSpeed;
    }

    public int getEncoderResolution() {
        return encoderResolution;
    }

    public int getCycleDelayMs() {
        return cycleDelayMs;
    }

    public int getSimulationCycles() {
        return simulationCycles;
    }

    public double getKp() {
        return kp;
    }

    public double getKi() {
        return ki;
    }

    public double getKd() {
        return kd;
    }

    public long getRecoveryTimeout() {
        return recoveryTimeout;
    }

    public Map<String, ThresholdConfig> getThresholds() {
        return thresholds;
    }

    public List<SensorConfig> getSensorsConfig() {
        return sensorsConfig;
    }

    public List<ActuatorConfig> getActuatorsConfig() {
        return actuatorsConfig;
    }

    public FaultSettings getFaultSettings() {
        return faultSettings;
    }

    public Map<String, Double> getObjectSpecs() {
        return objectSpecs;
    }

    // Setters (utili per Jackson o init manuale)
    public void setBeltSpeed(double beltSpeed) {
        this.beltSpeed = beltSpeed;
    }

    public void setEncoderResolution(int encoderResolution) {
        this.encoderResolution = encoderResolution;
    }

    public void setCycleDelayMs(int cycleDelayMs) {
        this.cycleDelayMs = cycleDelayMs;
    }

    public void setSimulationCycles(int simulationCycles) {
        this.simulationCycles = simulationCycles;
    }

    public void setKp(double kp) {
        this.kp = kp;
    }

    public void setKi(double ki) {
        this.ki = ki;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public void setRecoveryTimeout(long recoveryTimeout) {
        this.recoveryTimeout = recoveryTimeout;
    }

    public void setThresholds(Map<String, ThresholdConfig> thresholds) {
        this.thresholds = thresholds;
    }

    public void setSensorsConfig(List<SensorConfig> sensorsConfig) {
        this.sensorsConfig = sensorsConfig;
    }

    public void setActuatorsConfig(List<ActuatorConfig> actuatorsConfig) {
        this.actuatorsConfig = actuatorsConfig;
    }

    public void setFaultSettings(FaultSettings faultSettings) {
        this.faultSettings = faultSettings;
    }

    public void setObjectSpecs(Map<String, Double> objectSpecs) {
        this.objectSpecs = objectSpecs;
    }
}
