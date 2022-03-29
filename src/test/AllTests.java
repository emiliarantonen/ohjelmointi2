package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite kerho-ohjelmalle
 * @author vesal
 * @version 3.1.2019
 */
@RunWith(Suite.class)
@SuiteClasses({
    harkkatyo.test.KilpailuTest.class,
    harkkatyo.test.KilpailutTest.class,
    harkkatyo.test.JoukkueetTest.class,
    harkkatyo.test.JoukkueTest.class
    })

public class AllTests {
    //
}
