package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortTrainer;

/**
 * Interface Persistenz, welches die Methoden save und load vorgibt für mögliche Implementierungen von einer
 * Persistenzklasse, bspw. JSON, XML, Serialisierung, etc.
 *
 * @author Benjamin Edlinger
 * @version 2024-09-25
 */
public interface Persistenz {

    /**
     * Speichert den WortTrainer in einer Datei, als Pfad wird der Standardpfad verwendet
     *
     * @param wortTrainer der zu speichernde WortTrainer
     */
    void save(WortTrainer wortTrainer);

    /**
     * Speichert den WortTrainer in einer Datei
     *
     * @param wortTrainer der zu speichernde WortTrainer
     * @param path        der Pfad, wo die Datei gespeichert werden soll
     */
    void save(WortTrainer wortTrainer, String path);

    /**
     * Lädt den WortTrainer aus einer Datei, als Pfad wird der Standardpfad verwendet
     *
     * @return der geladene WortTrainer
     */
    WortTrainer load();

    /**
     * Lädt den WortTrainer aus einer Datei
     *
     * @param path der Pfad, wo die Datei gespeichert ist
     * @return der geladene WortTrainer
     */
    WortTrainer load(String path);
}
