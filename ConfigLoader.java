/*
// src/simulator/config/ConfigLoader.java
package simulator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

*
 * ConfigLoader: carica la configurazione da un file JSON utilizzando Jackson.


public class ConfigLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

*
     * Legge e deserializza il file JSON in un oggetto Config.
     * @param path percorso al file JSON
     * @return Config popolata con i valori del file
     * @throws RuntimeException in caso di errore di I/O o parsing


    public static Config loadFromJson(String path) {
        try {
            return mapper.readValue(new File(path), Config.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento della configurazione da: " + path, e);
        }
    }
}
*/
