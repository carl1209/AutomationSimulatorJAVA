// src/simulator/hardware/Sensor.java
package simulator.hardware;

import java.util.*;
import simulator.model.ObjectItem;
import simulator.model.SensorData;
import simulator.config.SensorConfig;

/**
 * Sensore con:
 * – rumore Gaussian (std dev specificata)
 * – filtro a media mobile per stabilizzare la misura
 *
 * Dal punto di vista ingegneristico: un sensore ottico/induttivo
 * con tempo di campionamento definito dal controller.
 */
public class Sensor {
    private final String id;
    private final double noiseStd;   // deviazione standard del rumore
    private final int windowSize;    // finestra della media mobile
    private final Deque<Double> window = new ArrayDeque<>();

    public Sensor(SensorConfig cfg) {
        this.id = cfg.getId();
        this.noiseStd = cfg.getNoiseStdDev();
        this.windowSize = cfg.getFilterWindow();
    }

    public String getId() {
        return id;
    }

    /**
     * Legge la proprietà dall'oggetto e aggiunge rumore Gaussiano:
     * valore reale + N(0, noiseStd).
     */
    public SensorData read(ObjectItem item) {
        double trueVal = item.getProperty(id);
        double noisy = trueVal + noiseStd * new Random().nextGaussian();
        return new SensorData(id, noisy);
    }

    /**
     * Applica un filtro a media mobile sulla storia delle ultime windowSize letture.
     * Emula l'elaborazione hardware/software del sensore per ridurre il rumore.
     */
    public SensorData applyFilter(SensorData raw) {
        window.addLast(raw.getValue());
        if (window.size() > windowSize) {
            window.removeFirst();
        }
        double avg = window.stream()
                .mapToDouble(v -> v)
                .average()
                .orElse(raw.getValue());
        return new SensorData(id, avg);
    }
}
