// src/simulator/controller/StateMachine.java
package simulator.controller;

import simulator.model.SensorData;
import java.util.*;

/**
 * FSM di linea: INIT → RUN → ERROR → RECOV → RUN.
 */
public class StateMachine {
    public enum State { INIT, RUN, ERROR, RECOV }
    private State st=State.INIT;
    private long errTime, timeout;
    public StateMachine(long toMs) { this.timeout=toMs; }
    public boolean inError() { return st==State.ERROR; }
    public void eval(List<SensorData> d) {
        long now=System.currentTimeMillis();
        switch(st) {
            case INIT: st=State.RUN; break;
            case RUN:
                if (d.stream().anyMatch(SensorData::isFault)) {
                    st=State.ERROR; errTime=now;
                    System.out.println("[FSM] ERROR");
                }
                break;
            case ERROR:
                if (now-errTime>timeout) { st=State.RECOV; System.out.println("[FSM] RECOV"); }
                break;
            case RECOV: st=State.RUN; System.out.println("[FSM] RUN"); break;
        }
    }
}
