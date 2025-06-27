// src/simulator/controller/PIDController.java
package simulator.controller;

/**
 * Controllo PID discreto: U = Kp*e + Ki*∑e + Kd*(Δe).
 */
public class PIDController {
    private double kp, ki, kd;
    private double integral, prevErr;
    public PIDController(double kp, double ki, double kd) {
        this.kp=kp; this.ki=ki; this.kd=kd;
    }
    public double compute(double setpoint, double actual) {
        double err = setpoint - actual;
        integral += err;
        double der = err - prevErr;
        prevErr = err;
        return kp*err + ki*integral + kd*der;
    }
}
