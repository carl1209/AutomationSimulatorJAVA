// src/simulator/controller/AutomationController.java
package simulator.controller;
import simulator.model.ActuationCommand;


import java.util.*;
import simulator.hardware.*;
import simulator.model.SensorData;
import simulator.config.*;

/**
 * Orchestrator: gestisce PID, FSM e smistamento tramite attuatori.
 */
public class AutomationController {
    private ConveyorBelt belt;
    private List<Sensor> sensors;
    private List<Actuator> actuators;
    private PIDController pid;
    private StateMachine fsm;
    private Map<String,ThresholdConfig> th;
    private double target;
    public AutomationController(ConveyorBelt b, List<Sensor> s,
                                List<Actuator> a, Config cfg) {
        this.belt=b; this.sensors=s; this.actuators=a;
        this.pid=new PIDController(cfg.getKp(),cfg.getKi(),cfg.getKd());
        this.fsm=new StateMachine(cfg.getRecoveryTimeout());
        this.th=cfg.getThresholds(); this.target=cfg.getBeltSpeed();
    }
    public void controlCycle(List<SensorData> data) {
        fsm.eval(data);
        if (fsm.inError()) return;
        double corr = pid.compute(target, belt.getSpeed());
        belt.setSpeed(belt.getSpeed()+corr);
        for (SensorData d: data) {
            ThresholdConfig tc=th.get(d.getSensorId());
            String cmd = d.getValue()>tc.getMax()?"DIVERT":"PASS";
            actuators.get(0).actuate(new ActuationCommand(cmd));
        }
    }
}
