package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortTrainer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Paths;

/**
 * Speichert einen WortTrainer in einer JSON-Datei
 * @author Leo Mühlböck
 * @version 2024-09-30
 */
public class PersistenzJSON implements Persistenz {
    private static final String FILENAME = "worttrainer.json";
    private String standardPath;
    private final Gson gson;

    /**
     * Konstruktor, der den Standardpfad auf den Benutzerordner setzt
     */
    public PersistenzJSON() {
        this(Paths.get(System.getProperty("user.home"), "WortTrainer").toString());
    }

    /**
     * Konsktruktor, der den Standardpfad auf den übergebenen Pfad setzt
     *
     * @param path der Standardpfad
     */
    public PersistenzJSON(String path) {
        setStandardPath(path);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    /**
     * Setzt den Standardpfad, wo die Datei gespeichert werden soll
     * Wenn der Pfad nicht existiert, wird er erstellt
     *
     * @param path der Standardpfad
     */
    @Override
    public void setStandardPath(String path) {
        if (path == null)
            throw new IllegalArgumentException("Der Pfad darf nicht null sein");
        if (path.isEmpty())
            throw new IllegalArgumentException("Der Pfad darf nicht leer sein");
        if (!new File(path).exists()) {
            boolean created = new File(path).mkdirs();
            if (!created)
                throw new IllegalArgumentException("Der Pfad konnte nicht erstellt werden");
        }

        this.standardPath = path;
    }

    /**
     * Gibt den Standardpfad zurück
     *
     * @return der Standardpfad
     */
    @Override
    public String getStandardPath() {
        return this.standardPath;
    }

    /**
     * Speichert den WortTrainer in einer JSON-Datei, als Pfad wird der Standardpfad verwendet
     *
     * @param wortTrainer der zu speichernde WortTrainer
     */
    @Override
    public void save(WortTrainer wortTrainer) throws IOException {
        this.save(wortTrainer, getStandardPath());
    }

    /**
     * Speichert den WortTrainer in einer JSON-Datei
     *
     * @param wortTrainer der zu speichernde WortTrainer
     * @param path        der Pfad, wo die Datei gespeichert werden soll
     */
    @Override
    public void save(WortTrainer wortTrainer, String path) throws IOException {
        try (PrintWriter writer = new PrintWriter(Paths.get(path, FILENAME).toString())) {
            gson.toJson(wortTrainer, writer);
        }
    }

    /**
     * Lädt den WortTrainer aus einer JSON-Datei, als Pfad wird der Standardpfad verwendet
     *
     * @return der geladene WortTrainer
     */
    @Override
    public WortTrainer load() throws IOException {
        return this.load(getStandardPath());
    }

    /**
     * Lädt den WortTrainer aus einer JSON-Datei
     *
     * @param path der Pfad, wo die Datei gespeichert ist
     * @return der geladene WortTrainer
     */
    @Override
    public WortTrainer load(String path) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(Paths.get(path, FILENAME).toString()));
        return gson.fromJson(reader, WortTrainer.class);
    }
}
