package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortEintrag;
import at.ac.tgm.bedlinger.model.WortTrainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Testklasse für die Klasse PersistenzXML
 *
 * @author bedlinger
 * @version 2024-09-29
 */
public class TestPersistenzXML {
    private WortTrainer wortTrainer;
    private Persistenz persistenz;
    private static final String PATH = Paths.get(System.getProperty("user.home"), "WortTrainerNewStandardPath").toString();

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
        String correctPath = Paths.get(System.getProperty("user.home"), "WortTrainer").toString();
        Assertions.assertEquals(correctPath, persistenz1.getStandardPath());

        Persistenz persistenz2 = new PersistenzXML(PATH);
        Assertions.assertEquals(PATH, persistenz2.getStandardPath());
    }

    @Test
    @DisplayName("Testen des Setzens des Standardpfades")
    public void testSetStandardPath() {
        this.persistenz.setStandardPath(PATH);
        Assertions.assertEquals(PATH, this.persistenz.getStandardPath());

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> this.persistenz.setStandardPath(null));
        Assertions.assertEquals("Der Pfad darf nicht null sein", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> this.persistenz.setStandardPath(""));
        Assertions.assertEquals("Der Pfad darf nicht leer sein", exception.getMessage());
    }

    @Test
    @DisplayName("Testen des Speichern und Laden eines WortTrainers in einer XML-Datei")
    public void testSaveAndLoadWortTrainer() {
        System.out.println(this.persistenz.getStandardPath());
        try {
            this.persistenz.save(this.wortTrainer);
            // Assertions.assertTrue(new File(this.persistenz.getStandardPath() + "\\worttrainer.xml").exists());
            String FileLocation = Paths.get(this.persistenz.getStandardPath(), "worttrainer.xml").toString();
            Assertions.assertTrue(new File(FileLocation).exists());
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

        Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> this.persistenz.save(null));
        Assertions.assertEquals("Der WortTrainer darf nicht null sein", e1.getMessage());
        Exception e2 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.persistenz.save(this.wortTrainer, null));
        Assertions.assertEquals("Der Pfad darf nicht null sein", e2.getMessage());
        Exception e3 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.persistenz.load(null));
        Assertions.assertEquals("Der Pfad darf nicht null sein", e3.getMessage());
    }
}
