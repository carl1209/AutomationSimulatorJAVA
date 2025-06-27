// src/simulator/hardware/ConveyorBelt.java
package simulator.hardware;

import java.util.*;
import simulator.model.ObjectItem;
import simulator.model.EventLog;

/**
 * Modella un nastro trasportatore con motore DC e encoder.
 * – speed: velocità in m/s controllata dal PID
 * – encoderResolution: numero di tick encoder per metro
 *
 * Fisicamente: un motore in corrente continua con sensore di posizione (encoder)
 * che fornisce un impulso ogni X millimetri di nastro.
 */
public class ConveyorBelt {
    private Queue<ObjectItem> buffer = new LinkedList<>();   // FIFO degli oggetti
    private double speed;           // m/s, impostata dal controller
    private int encoderResolution;  // tick per metro di nastro
    private int encoderCount;       // conteggio attuale dei tick
    private List<EventLog> logs = new ArrayList<>();

    public ConveyorBelt(double initSpeed, int encoderResolution) {
        this.speed = initSpeed;
        this.encoderResolution = encoderResolution;
        this.encoderCount = 0;
    }

    /**
     * Aggiunge un oggetto al nastro (simulazione di alimentazione meccanica)
     */
    public void addItem(ObjectItem item) {
        buffer.offer(item);
        logs.add(new EventLog("ADD", item.toString()));
    }

    /**
     * Simula l'avanzamento del nastro:
     * – Aggiorna encoderCount in base a speed * resolution
     * – Quando encoderCount ≥ encoderResolution, processa (rimuove) un oggetto
     *   (come se fosse passato sotto una stazione di smistamento)
     */
    public void move() {
        // Incremento tick (feedback reale da encoder)
        encoderCount += (int)(speed * encoderResolution);
        logs.add(new EventLog("ENC", Integer.toString(encoderCount)));

        // Ogni volta che abbiamo fatto un “metro” di nastro,
        // consideriamo un oggetto processato
        if (!buffer.isEmpty() && encoderCount >= encoderResolution) {
            ObjectItem done = buffer.poll();
            logs.add(new EventLog("PROC", done.toString()));
            encoderCount -= encoderResolution;
        }
    }






    public double getSpeed() { return speed; }

    /**
     * Imposta nuova velocità (ad es. correzione PID)
     */
    public void setSpeed(double spd) {
        this.speed = spd;
        logs.add(new EventLog("SPD", Double.toString(spd)));
    }

    /** Ritorna il log di tutti gli eventi (utile per debug o analisi) */
    public List<EventLog> getLogs() { return logs; }
}
