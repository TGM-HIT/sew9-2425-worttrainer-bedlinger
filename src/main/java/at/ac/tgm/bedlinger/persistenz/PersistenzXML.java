package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortTrainer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

/**
 * Speichert einen WortTrainer in einer XML-Datei
 *
 * @author bedlinger
 * @version 2024-09-25
 */
public class PersistenzXML implements Persistenz {

    /**
     * Speichert den WortTrainer in einem XML, als Pfad wird der Standardpfad verwendet
     *
     * @param wortTrainer der zu speichernde WortTrainer
     */
    @Override
    public void save(WortTrainer wortTrainer) throws IOException {
        this.save(wortTrainer, STANDARD_PATH + "\\worttrainer.xml");
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
            marshaller.marshal(wortTrainer, new File(path));
        } catch (JAXBException e) {
            throw new IOException("Fehler beim Speichern des WortTrainers");
        }
    }

    /**
     * Lädt den WortTrainer aus einem XML, als Pfad wird der Standardpfad verwendet
     *
     * @return der geladene WortTrainer
     */
    @Override
    public WortTrainer load() throws IOException {
        return this.load(STANDARD_PATH + "\\worttrainer.xml");
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
            return (WortTrainer) context.createUnmarshaller().unmarshal(new File(path));
        } catch (JAXBException e) {
            throw new IOException("Fehler beim Laden des WortTrainers");
        }
    }
}
