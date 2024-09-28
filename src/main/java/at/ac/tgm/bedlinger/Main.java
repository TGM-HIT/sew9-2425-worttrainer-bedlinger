package at.ac.tgm.bedlinger;

import at.ac.tgm.bedlinger.controller.Controller;
import at.ac.tgm.bedlinger.persistenz.Persistenz;
import at.ac.tgm.bedlinger.persistenz.PersistenzXML;

import javax.swing.*;
import java.util.Map;

/**
 * Startet das Programm und fragt den Benutzer nach der gewünschten Persistenz.
 * Erstellt dann den Controller mit der gewählten Persistenz.
 *
 * @author Benjamin Edlinger
 * @version 2024-09-28
 */
public class Main {

    public static void main(String[] args) {
        // Nachfrage welche Persistenz der Benutzer verwenden möchte, mittels JOptionPane
        Map<String, Persistenz> persistenzMap = Map.of("XML", new PersistenzXML());
        String persistenz = (String) JOptionPane.showInputDialog(null, "Welche Persistenz möchten Sie verwenden?", "Persistenz", JOptionPane.QUESTION_MESSAGE, null, persistenzMap.keySet().toArray(), "XML");

        // Erstellung des Controllers mit der gewählten Persistenz
        Controller controller = new Controller(persistenzMap.get(persistenz));
    }
}
