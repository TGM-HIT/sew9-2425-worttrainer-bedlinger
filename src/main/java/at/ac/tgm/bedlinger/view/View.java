package at.ac.tgm.bedlinger.view;

import at.ac.tgm.bedlinger.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

/**
 * Repräsentiert die View des Programms, mittels Java Swing
 *
 * @author Benjamin Edlinger
 * @version 2024-09-28
 */
public class View extends JFrame {
    private Controller controller;

    private JTextField tfEingabe;
    private JLabel lBild, lCounterAbgefragt, lCounterKorrekt, lCounterFalsch, lVorherigerVersuch;

    /**
     * Erstellt die View des Programms
     *
     * @param controller Controller des Programms
     */
    public View(Controller controller) {
        if (controller == null)
            throw new IllegalArgumentException("Controller darf nicht null sein");
        this.controller = controller;

        this.setLayout(new BorderLayout(20, 20));
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lInstruction = new JLabel("Welches Wort wird unten dargestellt?", SwingConstants.CENTER);
        tfEingabe = new JTextField();
        tfEingabe.addActionListener(l -> controller.check(tfEingabe.getText()));
        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.add(lInstruction, BorderLayout.PAGE_START);
        top.add(tfEingabe, BorderLayout.PAGE_END);
        this.add(top, BorderLayout.PAGE_START);

        lBild = new JLabel();
        lBild.setHorizontalAlignment(JLabel.CENTER);
        lBild.setVerticalAlignment(JLabel.CENTER);
        this.add(lBild, SwingConstants.CENTER);

        JLabel lAbgefragt = new JLabel("Abgefragt: ", SwingConstants.RIGHT);
        lCounterAbgefragt = new JLabel("0");
        JLabel lKorrekt = new JLabel("Korrekt: ", SwingConstants.RIGHT);
        lCounterKorrekt = new JLabel("0");
        JLabel lFalsch = new JLabel("Falsch: ", SwingConstants.RIGHT);
        lCounterFalsch = new JLabel("0");
        JLabel lVorheriger = new JLabel("Vorheriger Versuch: ", SwingConstants.RIGHT);
        lVorherigerVersuch = new JLabel("Falsch");
        JPanel bottom = new JPanel(new GridLayout(1, 8, 5, 5));
        bottom.add(lAbgefragt);
        bottom.add(lCounterAbgefragt);
        bottom.add(lKorrekt);
        bottom.add(lCounterKorrekt);
        bottom.add(lFalsch);
        bottom.add(lCounterFalsch);
        bottom.add(lVorheriger);
        bottom.add(lVorherigerVersuch);
        this.add(bottom, BorderLayout.PAGE_END);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.save();
                windowEvent.getWindow().dispose();
            }
        });

        this.pack();
        this.setBounds(300, 100, 1500, 600);
        this.setVisible(true);
    }

    /**
     * Setzt die Counter der View
     *
     * @param abgefragt die Anzahl der abgefragten Wörter
     * @param korrekt   die Anzahl der korrekten Antworten
     * @param falsch    die Anzahl der falschen Antworten
     */
    public void setCounter(int abgefragt, int korrekt, int falsch) {
        lCounterAbgefragt.setText(String.valueOf(abgefragt));
        lCounterKorrekt.setText(String.valueOf(korrekt));
        lCounterFalsch.setText(String.valueOf(falsch));
    }

    /**
     * Setzt den vorherigen Versuch
     *
     * @param korrekt ob der vorherige Versuch korrekt war
     */
    public void setVorherigerVersuch(boolean korrekt) {
        lVorherigerVersuch.setText(korrekt ? "Korrekt" : "Falsch");
    }

    /**
     * Setzt das Bild der View
     *
     * @param url URL des Bildes
     */
    public void setBild(String url) {
        lBild.setIcon(new ImageIcon(url));
    }

    /**
     * Setzt die Eingabe der View
     *
     * @param eingabe die Eingabe
     */
    public void setEingabe(String eingabe) {
        tfEingabe.setText(eingabe);
    }
}
