package at.ac.tgm.bedlinger.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

/**
 * Diese Klasse repräsentiert ein Wort-Bild-Paar. Ein WortEintrag besteht aus einer URL, welche auf ein Bild zeigt
 * und einem Wort, welches das Bild darstellt.
 *
 * @author Benjamin Edlinger
 * @version 2024-09-27
 */
@XmlRootElement(name = "wortEintrag")
public class WortEintrag {
    private String wort;
    private String url;

    /**
     * Der Standardkonstruktor erstellt ein neues WortEintrag-Objekt.
     */
    public WortEintrag() {
        setWort("Hund");
        setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlfP2ySQQEN3_sTkKSyEDPwZHtTVF7oW8Nhg&s");
    }

    /**
     * Der Konstruktor erstellt ein neues WortEintrag-Objekt mit den gegebenen Werten.
     *
     * @param wort das Wort für den Worteintrag
     * @param url  die dazugehörige URL zum Wort
     */
    public WortEintrag(String wort, String url) {
        setWort(wort);
        setUrl(url);
    }

    /**
     * Legt das Wort als Attribut fest, wenn es korrekt fest.
     *
     * @param wort das festzulegende Wort
     */
    public void setWort(String wort) {
        if (checkWort(wort))
            this.wort = wort;
    }

    /**
     * Gibt das Wort zurück.
     *
     * @return das festgelegte Wort für den Worteintrag
     */
    @XmlElement(name = "wort")
    public String getWort() {
        return wort;
    }

    /**
     * Legt die URL als Attribut fest, wenn sie korrekt fest.
     *
     * @param url die festzulegende URL
     */
    public void setUrl(String url) {
        if (checkUrl(url))
            this.url = url;
    }

    /**
     * Gibt die URL zurück.
     *
     * @return die festgelegte URL für den Worteintrag
     */
    @XmlElement(name = "url")
    public String getUrl() {
        return url;
    }

    /**
     * Überprüft, ob das Übergeben Wort gültig ist.
     *
     * @param wort das auf gültigkeit geprüft werden soll
     * @return gibt true zurück, wenn es sich um ein gültiges Wort handelt
     * @throws IllegalArgumentException wenn der Parameter ungültig ist
     */
    public static boolean checkWort(String wort) throws IllegalArgumentException {
        if (wort == null) {
            throw new IllegalArgumentException("Das Wort darf nicht null sein!");
        } else if (wort.length() < 2) {
            throw new IllegalArgumentException("Das Wort muss mindestens zwei Buchstaben haben!");
        }
        return true;
    }

    /**
     * Überprüft die übergebene URL auf Sinnhaftigkeit.
     *
     * @param url die auf gültigkeit geprüft werden soll
     * @return gibt true zurück, wenn es sich um eine gültige URL handelt
     * @throws IllegalArgumentException wenn der Parameter ungültig ist
     */
    public static boolean checkUrl(String url) throws IllegalArgumentException {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
        if (url == null) {
            throw new IllegalArgumentException("Die URL darf nicht null sein!");
        } else if (!Pattern.compile(regex).matcher(url).matches()) {
            throw new IllegalArgumentException("Die URL ist ungültig!");
        }
        return true;
    }

    /**
     * Stellt den Worteintrag als String dar.
     *
     * @return gibt den Worteintrag als String zurück
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        sb.append(wort);
        sb.append(" - ");
        sb.append(url);
        sb.append(")");
        return sb.toString();
    }
}
