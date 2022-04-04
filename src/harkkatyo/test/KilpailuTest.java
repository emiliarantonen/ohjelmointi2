package harkkatyo.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import harkkatyo.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.04 12:59:03 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class KilpailuTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi69 */
  @Test
  public void testRekisteroi69() {    // Kilpailu: 69
    Kilpailu SM1 = new Kilpailu(); 
    assertEquals("From: Kilpailu line: 71", 0, SM1.getTunnusNro()); 
    SM1.rekisteroi(); 
    Kilpailu SM2 = new Kilpailu(); 
    SM2.rekisteroi(); 
    int n1 = SM1.getTunnusNro(); 
    int n2 = SM2.getTunnusNro(); 
    assertEquals("From: Kilpailu line: 77", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse135 */
  @Test
  public void testParse135() {    // Kilpailu: 135
    Kilpailu kilpailu = new Kilpailu(); 
    kilpailu.parse("   0   |  3  |   SM-kilpailut  "); 
    assertEquals("From: Kilpailu line: 138", 0, kilpailu.getTunnusNro()); 
    assertEquals("From: Kilpailu line: 139", true, kilpailu.toString().startsWith("0|3|SM-kilpailut|")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString159 */
  @Test
  public void testToString159() {    // Kilpailu: 159
    Kilpailu kil = new Kilpailu(); 
    kil.parse("   0   |  1  |   SM-kilpailu  | 2020 "); 
    assertEquals("From: Kilpailu line: 162", true, kil.toString().startsWith("0|1|SM-kilpailu|2020|")); 
  } // Generated by ComTest END
}