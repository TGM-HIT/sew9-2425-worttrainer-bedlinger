package at.ac.tgm.bedlinger.persistenz;

import at.ac.tgm.bedlinger.model.WortEintrag;
import at.ac.tgm.bedlinger.model.WortTrainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        String path = "C:\\Users\\Public\\Desktop";
        Persistenz persistenz2 = new PersistenzXML(path);
        Assertions.assertEquals(path, persistenz2.getStandardPath());
    }

    @Test
    @DisplayName("Testen des Setzens des Standardpfades")
    public void testSetStandardPath() {
        String path = "C:\\Users\\Public\\Desktop";
        this.persistenz.setStandardPath(path);
        Assertions.assertEquals(path, this.persistenz.getStandardPath());

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
}
