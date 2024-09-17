# Worttrainer - README

## Projektbeschreibung

Der **Worttrainer** ist eine Anwendung, die Kindern (Zielgruppe: Volksschulkinder) hilft, ihre Rechtschreibfähigkeiten
zu trainieren. Das Programm zeigt Bilder (über URLs geladen) an, und die Kinder müssen das zugehörige Wort eingeben. Das
Programm vergleicht die Eingabe mit der korrekten Schreibweise und zeigt an, ob das Wort richtig oder falsch geraten
wurde. Zusätzlich wird eine Statistik geführt, die das Verhältnis von richtigen und falschen Antworten speichert.

Diese Applikation wurde unter Verwendung moderner Softwareentwicklungsmethoden entwickelt, insbesondere durch den
Einsatz von **Gradle** als Build-System und der Verwendung von **Git** für die Versionskontrolle. Die Implementierung
ist in **Java** erfolgt und die Benutzeroberfläche wird über **JOptionPane** bereitgestellt.

## Features

1. **Wort-Bild-Paare**:
    - Jedes Paar besteht aus einem Wort und einem Bild, welches über eine URL geladen wird.
    - Die Wort-Bild-Paare werden validiert, um ungültige Eingaben (z.B. `null`-Werte oder fehlerhafte URLs) zu
      verhindern.

2. **Rechtschreibtrainer**:
    - Auswahl eines Wort-Bild-Paares: Der Benutzer kann entweder ein bestimmtes Paar (über einen Index) auswählen oder
      ein zufälliges Paar wählen.
    - Rate-Logik: Nach Auswahl eines Paares wird das Bild angezeigt und der Benutzer muss das dazugehörige Wort raten.
      Bei falschen Antworten bleibt das Paar ausgewählt, bis das richtige Wort eingegeben wurde.
    - Statistiken: Der Trainer zählt alle geratenen, richtigen und falschen Versuche.

3. **Persistenz**:
    - Der Zustand des Worttrainers (Wort-Bild-Paare, aktuelle Auswahl, Statistiken) kann gespeichert und
      wiederhergestellt werden.
    - Verschiedene Speicherformate (z.B. JSON, XML, Java-Serialisierung, etc.) werden unterstützt. Es ist möglich,
      unterschiedliche Speicherstrategien zu implementieren, um die Persistierung flexibel zu gestalten.

4. **Grafische Oberfläche (GUI)**:
    - Über **JOptionPane** wird eine einfache Benutzeroberfläche zur Verfügung gestellt.
    - Der Benutzer sieht die aktuelle Statistik und das zu ratende Bild und gibt dann das Wort über eine
      Eingabeaufforderung ein.
    - Die Anwendung startet mit der geladenen Sitzung (falls vorhanden) oder mit einem neuen Worttrainer mit
      vordefinierten Paaren.

## Installation und Ausführung

### Voraussetzungen

- **Java** (ab Version 8 oder höher)
- **Gradle** (wird beim Projekt-Setup über Wrapper automatisch bereitgestellt)
- Ein **Git**-Client zur Verwaltung des Repositories.

### Setup

1. **Repository klonen**:
   ```
   git clone https://github.com/TGM-HIT/sew9-2425-worttrainer-bedlinger.git
   cd sew9-2425-worttrainer-bedlinger
    ```

2. **Gradle-Projekt initialisieren:**
    ```
    gradle init
    ```

3. **Projekt bauen:**
    ```
    gradle build
    ```

4. **Anwendung starten:**
    ```
    gradle run
    ```

## Testen

Automatisierte Tests wurden für die Kernfunktionen des Worttrainers implementiert, einschließlich der Validierung von
Wort-Bild-Paaren, der Ratelogik und der Persistierung. Um die Tests auszuführen, verwende den Befehl:

```
gradle test
```

## Verwendete Technologien

- **Programmiersprache**: Java
- **Build-System**: Gradle
- **Versionskontrolle**: Git
- **GUI**: JOptionPane
- **Persistenz**: Java-Serialisierung (als Standard), unterstützt verschiedene Speicherformate
- **Test-Framework**: JUnit

## Hinweis für Anwender

- Beim ersten Start erstellt die Anwendung einige fest eingebaute Wort-Bild-Paare. Beim nächsten Start werden die Daten
  aus der letzten Sitzung geladen, sofern sie vorhanden sind.
- Wörter müssen exakt in der richtigen Schreibweise (inkl. Groß- und Kleinschreibung) eingegeben werden, um als richtig
  zu gelten.