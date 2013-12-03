package cz.muni.fi.pa165.languageschool.test;

import java.util.Collection;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = {
////    "classpath:testApplicationContext.xml"})
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
public abstract class BaseTest {
    
    protected EntityManager em;
    
    public void assertCollectionEquals(Collection<Object> exp, Collection<Object> given) {
        assertEquals(exp.getClass(), given.getClass());
        assertEquals(exp.size(), given.size());
        for(Object l: exp) {
            if (!given.contains(l))
                fail("Tested collection does not contain expected element. " + l);
        }
    }

}
