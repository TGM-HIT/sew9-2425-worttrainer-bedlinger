package at.ac.tgm.bedlinger.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert einen Worttrainer. Ein Worttrainer besteht aus einer Liste von WortEinträgen, dem
 * aktuellen WortEintrag und jeweils einen Counter für die Anzahl der abgefragten, richtigen und falschen Worte.
 *
 * @author Benjamin Edlinger
 * @version 2024-09-29
 */
@XmlRootElement(name = "WortTrainer")
@XmlType(propOrder = {"wortliste", "counterAbgefragt", "counterKorrekt", "counterFalsch", "aktuellerWortEintragIndex"
        , "vorherigerVersuchKorrekt"})
public class WortTrainer {
    private List<WortEintrag> wortliste;
    private int counterAbgefragt, counterKorrekt, aktuellerWortEintragIndex;
    private boolean vorherigerVersuchKorrekt;

    /**
     * Der Konstruktor erstellt ein neues WortTrainer-Objekt mit einer leeren Wortliste.
     * Die Counter werden auf 0 gesetzt, sowie der index des aktuellen Worteintrags.
     * Der vorherige Versuch wird auf falsch gesetzt.
     * Als Standard-Persistenz wird die PersistenzXML verwendet.
     */
    public WortTrainer() {
        wortliste = new ArrayList<>();
        wortliste.add(new WortEintrag());
        setAktuellerWortEintragIndex((int) (Math.random() * wortliste.size()));
        setCounterAbgefragt(0);
        setCounterKorrekt(0);
        setVorherigerVersuchKorrekt(false);
    }

    /**
     * Überprüft, ob das Wort mit dem aktuellen Wort des WortEintrags übereinstimmt.
     * Falls ja, wird der Counter für die korrekten Worte erhöht und ein neuer WortEintrag wird ausgewählt, ansonsten der Counter für die falschen Worte.
     * Der Counter für die abgefragten Worte wird immer erhöht.
     * Der aktuelle WortEintrag wird zufällig ausgewählt.
     *
     * @param wort das zu überprüfende Wort
     * @return true, wenn das Wort korrekt ist, ansonsten false
     */
    public boolean check(String wort) {
        counterAbgefragt++;
        if (wort.equals(wortliste.get(aktuellerWortEintragIndex).getWort())) {
            counterKorrekt++;
            aktuellerWortEintragIndex = (int) (Math.random() * wortliste.size());
            setVorherigerVersuchKorrekt(true);
            return true;
        } else {
            setVorherigerVersuchKorrekt(false);
            return false;
        }
    }

    /**
     * Fügt einen neuen WortEintrag hinzu, aber nur, wenn das Wort und die URL korrekt sind.
     *
     * @param wort das hinzuzufügende Wort
     * @param url  die URL des hinzuzufügenden Wortes
     */
    public void addWortEintrag(String wort, String url) {
        wortliste.add(new WortEintrag(wort, url));
    }

    /**
     * Gibt den aktuellen WortEintrag zurück, auf den der Index zeigt.
     *
     * @return der aktuelle WortEintrag
     */
    public WortEintrag getAktuellerWortEintrag() {
        return wortliste.get(aktuellerWortEintragIndex);
    }

    /**
     * Fügt eine neue Liste von WortEinträgen hinzu, aber nur, wenn das Wort und die URL korrekt sind.
     *
     * @param wortliste die hinzuzufügende Liste von WortEinträgen
     */
    public void setWortliste(List<WortEintrag> wortliste) {
        if (wortliste == null)
            throw new IllegalArgumentException("Die Wortliste darf nicht null sein!");
        if (wortliste.isEmpty())
            throw new IllegalArgumentException("Die Wortliste darf nicht leer sein!");
        this.wortliste = new ArrayList<>();
        for (WortEintrag wortEintrag : wortliste) {
            if (WortEintrag.checkWort(wortEintrag.getWort()) && WortEintrag.checkUrl(wortEintrag.getUrl()))
                this.wortliste.add(wortEintrag);
        }
    }

    /**
     * Gibt die Liste von WortEinträgen zurück.
     *
     * @return die Liste von WortEinträgen
     */
    @XmlElementWrapper(name = "wortliste")
    @XmlElement(name = "wortEintrag")
    public List<WortEintrag> getWortliste() {
        return wortliste;
    }

    /**
     * Setzt den index für den aktuellen aktuellerWortEintragIndex.
     *
     * @param i der index für den aktuellen aktuellerWortEintragIndex
     */
    public void setAktuellerWortEintragIndex(int i) {
        if (i < 0 || i >= wortliste.size())
            throw new IllegalArgumentException("Der Index für den aktuellen Worteintrag ist ungültig!");

        aktuellerWortEintragIndex = i;
    }

    /**
     * Gibt den index für den aktuellen Worteintrag zurück.
     *
     * @return der index für den aktuellen Worteintrag
     */
    @XmlElement(name = "aktuellerWortEintragIndex")
    public int getAktuellerWortEintragIndex() {
        return aktuellerWortEintragIndex;
    }

    /**
     * Setzt den Counter für die abgefragten Worte.
     *
     * @param c die Anzahl der abgefragten Worte
     */
    public void setCounterAbgefragt(int c) {
        if (c < 0)
            throw new IllegalArgumentException("Der Counter für die abgefragten Worte darf nicht negativ sein!");

        counterAbgefragt = c;
    }

    /**
     * Gibt die Anzahl der abgefragten Worte zurück.
     *
     * @return die Anzahl der abgefragten Worte
     */
    @XmlElement(name = "counterAbgefragt")
    public int getCounterAbgefragt() {
        return counterAbgefragt;
    }

    /**
     * Setzt den Counter für die korrekt eingegebenen Worte.
     *
     * @param c die Anzahl der korrekt eingegebenen Worte
     */
    public void setCounterKorrekt(int c) {
        if (c < 0)
            throw new IllegalArgumentException("Der Counter für die korrekt eingegebenen Worte darf nicht negativ sein!");

        counterKorrekt = c;
    }

    /**
     * Gibt die Anzahl der korrekt eingegebenen Worte zurück.
     *
     * @return die Anzahl korrekt eingegebenen Worte
     */
    @XmlElement(name = "counterKorrekt")
    public int getCounterKorrekt() {
        return counterKorrekt;
    }

    /**
     * Gibt die Anzahl der falsch eingegebenen Worte zurück.
     *
     * @return die Anzahl der falsch eingegebenen Worte
     */
    @XmlElement(name = "counterFalsch")
    public int getCounterFalsch() {
        return counterAbgefragt - counterKorrekt;
    }

    /**
     * Setzt die korrekte Eingabe des vorherigen Versuchs.
     *
     * @param k true, wenn der vorherige Versuch korrekt war, ansonsten false
     */
    public void setVorherigerVersuchKorrekt(boolean k) {
        vorherigerVersuchKorrekt = k;
    }

    /**
     * Gibt zurück, ob der vorherige Versuch korrekt war.
     *
     * @return true, wenn der vorherige Versuch korrekt war, ansonsten false
     */
    @XmlElement(name = "vorherigerVersuchKorrekt")
    public boolean getVorherigerVersuchKorrekt() {
        return vorherigerVersuchKorrekt;
    }
}
