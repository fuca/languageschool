package cz.muni.fi.pa165.languageschool;

/**
 *
 * @author xfucik1
 */
public enum Language {
    CZECH("Czech"),
    ENGLISH("English"),
    GERMAN("German"),
    SPANISH("Spanish"),
    FRENCH("French"),
    MANDARIN("Mandarin"),
    HINDI("Hindi"),
    ARABIC("Arabic"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    SWAHILI("Swahili"),
    TAMIL("Serbian"),
    ITALIAN("Italian"),
    DUTCH("Dutch");
    
    private String text;

    Language(String text) {
        this.text = text;
    }
}
