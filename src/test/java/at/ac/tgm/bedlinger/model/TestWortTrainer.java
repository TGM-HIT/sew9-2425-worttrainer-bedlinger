package at.ac.tgm.bedlinger.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Testklasse für die Klasse WortTrainer
 *
 * @author Benjamin Edlinger
 * @version 2024-09-28
 */
public class TestWortTrainer {

    @Test
    @DisplayName("Testen der Erstellung eines WortTrainers")
    public void testCreateWortTrainer() {
        WortTrainer wortTrainer = new WortTrainer();
        Assertions.assertEquals(0, wortTrainer.getCounterAbgefragt());
        Assertions.assertEquals(0, wortTrainer.getCounterKorrekt());
        Assertions.assertEquals(0, wortTrainer.getCounterFalsch());
        Assertions.assertEquals(0, wortTrainer.getAktuellerWortEintragIndex());
        Assertions.assertFalse(wortTrainer.getWortliste().isEmpty());
        Assertions.assertFalse(wortTrainer.getVorherigerVersuchKorrekt());
        Assertions.assertEquals("at.ac.tgm.bedlinger.persistenz.PersistenzXML", wortTrainer.getPersistenzClassName());
    }

    @Test
    @DisplayName("Testen des Setten und getten der Attribute")
    public void testSetAndGetAttributes() {
        WortTrainer wortTrainer1 = new WortTrainer();
        List<WortEintrag> wortEintragList1 = new ArrayList<>();
        WortEintrag wortEintrag1 = new WortEintrag("Hund", "https://www.google.com");
        WortEintrag wortEintrag2 = new WortEintrag("Katze", "https://www.google.com");
        wortEintragList1.add(wortEintrag1);
        wortEintragList1.add(wortEintrag2);
        Assertions.assertDoesNotThrow(() -> wortTrainer1.setWortliste(wortEintragList1));
        Assertions.assertDoesNotThrow(() -> wortTrainer1.setAktuellerWortEintragIndex(1));
        Assertions.assertDoesNotThrow(() -> wortTrainer1.setCounterAbgefragt(5));
        Assertions.assertDoesNotThrow(() -> wortTrainer1.setCounterKorrekt(3));
        // Assertions.assertEquals(wortEintrag1, wortTrainer1.getAktuellerWortEintrag());
        Assertions.assertEquals(wortEintragList1, wortTrainer1.getWortliste());
        // Assertions.assertEquals(1, wortTrainer1.getAktuellerWortEintragIndex());
        Assertions.assertEquals(5, wortTrainer1.getCounterAbgefragt());
        Assertions.assertEquals(3, wortTrainer1.getCounterKorrekt());
        Assertions.assertEquals(2, wortTrainer1.getCounterFalsch());
        wortTrainer1.setVorherigerVersuchKorrekt(true);
        Assertions.assertTrue(wortTrainer1.getVorherigerVersuchKorrekt());

        WortTrainer wortTrainer2 = new WortTrainer();
        Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setWortliste(null));
        Assertions.assertEquals("Die Wortliste darf nicht null sein!", e1.getMessage());
        Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setWortliste(new ArrayList<>()));
        Assertions.assertEquals("Die Wortliste darf nicht leer sein!", e2.getMessage());
        Exception e3 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setAktuellerWortEintragIndex(-1));
        Assertions.assertEquals("Der Index für den aktuellen Worteintrag ist ungültig!", e3.getMessage());
        wortTrainer2.setWortliste(wortEintragList1);
        Exception e4 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setAktuellerWortEintragIndex(3));
        Assertions.assertEquals("Der Index für den aktuellen Worteintrag ist ungültig!", e4.getMessage());
        Exception e5 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setCounterAbgefragt(-1));
        Assertions.assertEquals("Der Counter für die abgefragten Worte darf nicht negativ sein!", e5.getMessage());
        Exception e6 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setCounterKorrekt(-1));
        Assertions.assertEquals("Der Counter für die korrekt eingegebenen Worte darf nicht negativ sein!", e6.getMessage());
        Exception e7 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setPersistenz(null));
        Assertions.assertEquals("Die Persistenz darf nicht null sein!", e7.getMessage());
        Exception e8 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.setPersistenzClassName(null));
        Assertions.assertEquals("Der Klassenname der Persistenz darf nicht null sein!", e8.getMessage());
        Exception e9 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> wortTrainer2.setPersistenzClassName("PersistenzJson"));
        Assertions.assertEquals("Ungültige Persistenzklasse: PersistenzJson", e9.getMessage());
    }

    @Test
    @DisplayName("Testen der check Methode und der Counter")
    public void testCheck() {
        WortTrainer wortTrainer = new WortTrainer();
        List<WortEintrag> wortEintragList = new ArrayList<>();
        WortEintrag wortEintrag1 = new WortEintrag("Hund", "https://www.google.com");
        WortEintrag wortEintrag2 = new WortEintrag("Katze", "https://www.google.com");
        wortEintragList.add(wortEintrag1);
        wortEintragList.add(wortEintrag2);
        wortTrainer.setWortliste(wortEintragList);
        wortTrainer.setAktuellerWortEintragIndex(0);
        Assertions.assertTrue(wortTrainer.check("Hund"));
        Assertions.assertEquals(1, wortTrainer.getCounterAbgefragt());
        Assertions.assertEquals(1, wortTrainer.getCounterKorrekt());
        Assertions.assertEquals(0, wortTrainer.getCounterFalsch());
        wortTrainer.setAktuellerWortEintragIndex(0);
        Assertions.assertFalse(wortTrainer.check("Katze"));
        Assertions.assertEquals(2, wortTrainer.getCounterAbgefragt());
        Assertions.assertEquals(1, wortTrainer.getCounterKorrekt());
        Assertions.assertEquals(1, wortTrainer.getCounterFalsch());
    }

    @Test
    @DisplayName("Testen des Speicherns und Ladens")
    public void testSaveAndLoad() throws IOException {
        WortTrainer wortTrainer = new WortTrainer();
        List<WortEintrag> wortEintragList = new ArrayList<>();
        WortEintrag wortEintrag1 = new WortEintrag("Hund", "https://www.google.com");
        WortEintrag wortEintrag2 = new WortEintrag("Katze", "https://www.google.com");
        wortEintragList.add(wortEintrag1);
        wortEintragList.add(wortEintrag2);
        wortTrainer.setWortliste(wortEintragList);
        wortTrainer.setAktuellerWortEintragIndex(0);
        wortTrainer.setCounterAbgefragt(5);
        wortTrainer.setCounterKorrekt(3);
        wortTrainer.setVorherigerVersuchKorrekt(true);
        wortTrainer.save();
        WortTrainer wortTrainer2 = new WortTrainer();
        wortTrainer2.load();
        Assertions.assertEquals(wortTrainer.getCounterAbgefragt(), wortTrainer2.getCounterAbgefragt());
        Assertions.assertEquals(wortTrainer.getCounterKorrekt(), wortTrainer2.getCounterKorrekt());
        Assertions.assertEquals(wortTrainer.getCounterFalsch(), wortTrainer2.getCounterFalsch());
        Assertions.assertEquals(wortTrainer.getAktuellerWortEintragIndex(), wortTrainer2.getAktuellerWortEintragIndex());
        Assertions.assertEquals(wortTrainer.getVorherigerVersuchKorrekt(), wortTrainer2.getVorherigerVersuchKorrekt());
        for (int i = 0; i < wortTrainer.getWortliste().size(); i++) {
            Assertions.assertEquals(wortTrainer.getWortliste().get(i).getWort(),
                    wortTrainer2.getWortliste().get(i).getWort());
            Assertions.assertEquals(wortTrainer.getWortliste().get(i).getUrl(),
                    wortTrainer2.getWortliste().get(i).getUrl());
        }

        Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.save(null));
        Assertions.assertEquals("Der Pfad darf nicht null sein!", e1.getMessage());
        Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, () -> wortTrainer2.load(null));
        Assertions.assertEquals("Der Pfad darf nicht null sein!", e2.getMessage());
    }
}
