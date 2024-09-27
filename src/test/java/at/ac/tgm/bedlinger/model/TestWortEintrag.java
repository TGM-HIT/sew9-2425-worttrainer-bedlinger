package at.ac.tgm.bedlinger.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testklasse für die Klasse WortEintrag
 *
 * @author Benjamin Edlinger
 * @version 2024-09-27
 */
public class TestWortEintrag {

    @Test
    @DisplayName("Testen der Erstellung eines WortEintrags")
    public void testCreateWortEintrag() {
        WortEintrag wortEintrag = new WortEintrag();
        Assertions.assertEquals("Hund", wortEintrag.getWort());
        Assertions.assertEquals("https://cdn.pixabay.com/photo/2023/08/18/15/02/dog-8198719_960_720.jpg", wortEintrag.getUrl());
        Assertions.assertDoesNotThrow(() -> new WortEintrag("Hund", "https://www.tiere.de/hund.jpg"));

        Throwable exceptionWort1 = Assertions.assertThrows(IllegalArgumentException.class, () -> new WortEintrag(null,
                "https://www.tiere.de/hund.jpg"));
        Assertions.assertEquals("Das Wort darf nicht null sein!", exceptionWort1.getMessage());
        Throwable exceptionWort2 = Assertions.assertThrows(IllegalArgumentException.class, () -> new WortEintrag("H",
                "https://www.tiere.de/hund.jpg"));
        Assertions.assertEquals("Das Wort muss mindestens zwei Buchstaben haben!", exceptionWort2.getMessage());

        Throwable exceptionUrl1 = Assertions.assertThrows(IllegalArgumentException.class, () -> new WortEintrag("Hund",
                null));
        Assertions.assertEquals("Die URL darf nicht null sein!", exceptionUrl1.getMessage());
        Throwable exceptionUrl2 = Assertions.assertThrows(IllegalArgumentException.class, () -> new WortEintrag("Hund",
                "htt://www.tiere.de/hund"));
        Assertions.assertEquals("Die URL ist ungültig!", exceptionUrl2.getMessage());
    }

    @Test
    @DisplayName("Testen der Richtigkeit für die gesetzten Werte")
    public void testGetters() {
        WortEintrag wortEintrag = new WortEintrag("Hund", "https://www.tiere.de/hund.jpg");
        Assertions.assertEquals("Hund", wortEintrag.getWort());
        Assertions.assertEquals("https://www.tiere.de/hund.jpg", wortEintrag.getUrl());
    }
}
