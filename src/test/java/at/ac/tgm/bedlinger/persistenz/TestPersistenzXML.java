package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortEintrag;
import at.ac.tgm.bedlinger.model.WortTrainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Testklasse für die Klasse PersistenzXML
 *
 * @author bedlinger
 * @version 2024-09-25
 */
public class TestPersistenzXML {
    private WortTrainer wortTrainer;
    private Persistenz persistenz;
    private static final String PATH = System.getProperty("user.home") + "\\WortTrainerNewStandardPath";

    @BeforeEach
    public void setUp() {
        this.wortTrainer = new WortTrainer();
        List<WortEintrag> wortListe = List.of(new WortEintrag("Haus", "https://www.haus.com"), new WortEintrag("Auto",
                "https://www.auto.com"));
        this.wortTrainer.setWortliste(wortListe);
        this.wortTrainer.setAktuellerWortEintragIndex(0);
        this.wortTrainer.setCounterAbgefragt(4);
        this.wortTrainer.setCounterKorrekt(2);

        this.persistenz = new PersistenzXML();
    }

    @Test
    @DisplayName("Testen der Erstellung eines PersistenzXML-Objekts")
    public void testPersistenzXML() {
        Persistenz persistenz1 = new PersistenzXML();
        String correctPath = System.getProperty("user.home") + "\\WortTrainer";
        Assertions.assertEquals(correctPath, persistenz1.getStandardPath());

        Persistenz persistenz2 = new PersistenzXML(PATH);
        Assertions.assertEquals(PATH, persistenz2.getStandardPath());
    }

    @Test
    @DisplayName("Testen des Setzens des Standardpfades")
    public void testSetStandardPath() {
        this.persistenz.setStandardPath(PATH);
        Assertions.assertEquals(PATH, this.persistenz.getStandardPath());

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.persistenz.setStandardPath(null);
        });
        Assertions.assertEquals("Der Pfad darf nicht null sein", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.persistenz.setStandardPath("");
        });
        Assertions.assertEquals("Der Pfad darf nicht leer sein", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.persistenz.setStandardPath("C:\\Users\\tgm\\Desktop");
        });
        Assertions.assertEquals("Der Pfad konnte nicht erstellt werden", exception.getMessage());
    }

    @Test
    @DisplayName("Testen des Speichern und Laden eines WortTrainers in einer XML-Datei")
    public void testSaveAndLoadWortTrainer() {
        System.out.println(this.persistenz.getStandardPath());
        try {
            this.persistenz.save(this.wortTrainer);
            Assertions.assertTrue(new File(this.persistenz.getStandardPath() + "\\worttrainer.xml").exists());
            WortTrainer wortTrainer = this.persistenz.load();
            for (int i = 0; i < this.wortTrainer.getWortliste().size(); i++) {
                Assertions.assertEquals(this.wortTrainer.getWortliste().get(i).getWort(),
                        wortTrainer.getWortliste().get(i).getWort());
                Assertions.assertEquals(this.wortTrainer.getWortliste().get(i).getUrl(),
                        wortTrainer.getWortliste().get(i).getUrl());
            }
            Assertions.assertEquals(this.wortTrainer.getAktuellerWortEintragIndex(),
                    wortTrainer.getAktuellerWortEintragIndex());
            Assertions.assertEquals(this.wortTrainer.getCounterAbgefragt(), wortTrainer.getCounterAbgefragt());
            Assertions.assertEquals(this.wortTrainer.getCounterKorrekt(), wortTrainer.getCounterKorrekt());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.persistenz.setStandardPath(PATH);
        try {
            this.persistenz.save(this.wortTrainer);
            Assertions.assertTrue(new File(this.persistenz.getStandardPath() + "\\worttrainer.xml").exists());
            WortTrainer wortTrainer = this.persistenz.load();
            for (int i = 0; i < this.wortTrainer.getWortliste().size(); i++) {
                Assertions.assertEquals(this.wortTrainer.getWortliste().get(i).getWort(),
                        wortTrainer.getWortliste().get(i).getWort());
                Assertions.assertEquals(this.wortTrainer.getWortliste().get(i).getUrl(),
                        wortTrainer.getWortliste().get(i).getUrl());
            }
            Assertions.assertEquals(this.wortTrainer.getAktuellerWortEintragIndex(),
                    wortTrainer.getAktuellerWortEintragIndex());
            Assertions.assertEquals(this.wortTrainer.getCounterAbgefragt(), wortTrainer.getCounterAbgefragt());
            Assertions.assertEquals(this.wortTrainer.getCounterKorrekt(), wortTrainer.getCounterKorrekt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
