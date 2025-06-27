// src/simulator/Main.java
package simulator;

import java.util.*;
import simulator.config.Config;
import simulator.config.ThresholdConfig;
import simulator.config.FaultSettings;
import simulator.config.SensorConfig;
import simulator.config.ActuatorConfig;
import simulator.hardware.ConveyorBelt;
import simulator.hardware.Sensor;
import simulator.hardware.Actuator;
import simulator.factory.SensorFactory;
import simulator.factory.ActuatorFactory;
import simulator.factory.ObjectFactory;
import simulator.controller.AutomationController;
import simulator.model.ObjectItem;
import simulator.model.SensorData;
import simulator.model.ActuationCommand;
import simulator.fault.FaultInjector;

/**
 * Punto di ingresso della simulazione di una linea di automazione,
 * con configurazione manuale hard-coded (senza JSON).
 */
public class Main {
    public static void main(String[] args) {
        // ==== CONFIGURAZIONE MANUALE ====
        Config cfg = new Config();
        // Parametri nastro
        cfg.setBeltSpeed(0.5);
        cfg.setEncoderResolution(100);
        // Ciclo di simulazione
        cfg.setCycleDelayMs(500);
        cfg.setSimulationCycles(50);
        // Parametri PID
        cfg.setKp(1.0);
        cfg.setKi(0.1);
        cfg.setKd(0.01);
        cfg.setRecoveryTimeout(2000);
        // Soglie sensori
        Map<String, ThresholdConfig> thr = new HashMap<>();
        thr.put("ColorSensor", new ThresholdConfig(0.0, 0.6));
        thr.put("WeightSensor", new ThresholdConfig(0.0, 200.0));
        cfg.setThresholds(thr);
        // Configurazione sensori
        List<SensorConfig> sensorCfgs = Arrays.asList(
                new SensorConfig("ColorSensor", 5.0, 3),
                new SensorConfig("WeightSensor", 2.0, 5)
        );
        cfg.setSensorsConfig(sensorCfgs);
        // Configurazione attuatori
        List<ActuatorConfig> actCfgs = Arrays.asList(
                new ActuatorConfig("diverter", 100, 50.0)
        );
        cfg.setActuatorsConfig(actCfgs);
        // Specifiche oggetti
        Map<String, Double> specs = new HashMap<>();
        specs.put("ColorSensor", 0.5);
        specs.put("WeightSensor", 100.0);
        cfg.setObjectSpecs(specs);
        // Guasti
        FaultSettings fs = new FaultSettings();
        fs.setBeltProb(0.05);
        cfg.setFaultSettings(fs);
        // ================================

        // Inizializza componenti
        ConveyorBelt belt = new ConveyorBelt(cfg.getBeltSpeed(), cfg.getEncoderResolution());
        List<Sensor> sensors = SensorFactory.createSensors(cfg.getSensorsConfig());
        List<Actuator> actuators = ActuatorFactory.createActuators(cfg.getActuatorsConfig());
        FaultInjector faultInjector = new FaultInjector(cfg.getFaultSettings());
        AutomationController controller = new AutomationController(belt, sensors, actuators, cfg);

        // Esegui simulazione
        for (int cycle = 1; cycle <= cfg.getSimulationCycles(); cycle++) {
            System.out.println(" === Ciclo " + cycle + " ===");
                    ObjectItem item = ObjectFactory.createRandom(cfg.getObjectSpecs());
            belt.addItem(item);

            List<SensorData> dataList = new ArrayList<>();
            for (Sensor s : sensors) {
                SensorData raw = s.read(item);
                SensorData filt = s.applyFilter(raw);
                dataList.add(filt);
                System.out.println("Sensor [" + s.getId() + "] = " + filt.getValue());
            }

            controller.controlCycle(dataList);
            belt.move();
            faultInjector.inject(belt, sensors, actuators, cycle);
            sleep(cfg.getCycleDelayMs());
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
