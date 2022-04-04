package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite rekisteri-ohjelmalle
 * @author emiliarantonen ja mineanupponen
 * @version 29.3.2022
 */
@RunWith(Suite.class)
@SuiteClasses({
    harkkatyo.test.KilpailuTest.class,
    harkkatyo.test.KilpailutTest.class,
    harkkatyo.test.JoukkueetTest.class,
    harkkatyo.test.JoukkueTest.class,
    harkkatyo.test.RekisteriTest.class
    })

public class AllTests {
    //
    
}


