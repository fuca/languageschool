package cz.muni.fi.pa165.languageschool.utils;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public enum DayOfWeek{
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");
    
    private String en;
    
    DayOfWeek(String en) {
        this.en = en;
    }
}
