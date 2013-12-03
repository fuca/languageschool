package cz.muni.fi.pa165.languageschool.test.di;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author Michal Fučík
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
public abstract class BaseSpringInjectionTest {
    
}
