package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortTrainer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

/**
 * Speichert einen WortTrainer in einer XML-Datei
 *
 * @author bedlinger
 * @version 2024-09-27
 */
public class PersistenzXML implements Persistenz {
    private String standardPath;

    /**
     * Konstruktor, der den Standardpfad auf den Benutzerordner setzt
     */
    public PersistenzXML() {
        StringBuilder path = new StringBuilder(System.getProperty("user.home"));
        path.append("\\WortTrainer");
        setStandardPath(path.toString());
    }

    /**
     * Konsktruktor, der den Standardpfad auf den übergebenen Pfad setzt
     *
     * @param path der Standardpfad
     */
    public PersistenzXML(String path) {
        setStandardPath(path);
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
     * Speichert den WortTrainer in einem XML, als Pfad wird der Standardpfad verwendet
     *
     * @param wortTrainer der zu speichernde WortTrainer
     */
    @Override
    public void save(WortTrainer wortTrainer) throws IOException {
        this.save(wortTrainer, getStandardPath());
    }

    /**
     * Speichert den WortTrainer in einem XML
     *
     * @param wortTrainer der zu speichernde WortTrainer
     * @param path        der Pfad, wo die Datei gespeichert werden soll
     */
    @Override
    public void save(WortTrainer wortTrainer, String path) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(WortTrainer.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(path, "worttrainer.xml");
            marshaller.marshal(wortTrainer, file);
        } catch (JAXBException e) {
            throw new IOException("Fehler beim Speichern des WortTrainers", e);
        }
    }

    /**
     * Lädt den WortTrainer aus einem XML, als Pfad wird der Standardpfad verwendet
     *
     * @return der geladene WortTrainer
     */
    @Override
    public WortTrainer load() throws IOException {
        return this.load(getStandardPath());
    }

    /**
     * Lädt den WortTrainer einem XML
     *
     * @param path der Pfad, wo die Datei gespeichert ist
     * @return der geladene WortTrainer
     */
    @Override
    public WortTrainer load(String path) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(WortTrainer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File(path, "worttrainer.xml");
            return (WortTrainer) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new IOException("Fehler beim Laden des WortTrainers", e);
        }
    }
}
