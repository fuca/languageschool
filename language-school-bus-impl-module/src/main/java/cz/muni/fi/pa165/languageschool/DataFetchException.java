package cz.muni.fi.pa165.languageschool;

import org.springframework.dao.DataAccessException;

/**
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class DataFetchException extends DataAccessException{
    
    public DataFetchException(String message) {
        super(message);
    }
    
    public DataFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
