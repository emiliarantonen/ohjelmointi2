package harkkatyo.test;
// Generated by ComTest BEGIN
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import harkkatyo.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.04 12:29:14 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class JoukkueetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa32 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa32() throws SailoException {    // Joukkueet: 32
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue lumo = new Joukkue(), lumo1 = new Joukkue(); 
    assertEquals("From: Joukkueet line: 36", 0, joukkueet.getLkm()); 
    joukkueet.lisaa(lumo); assertEquals("From: Joukkueet line: 37", 1, joukkueet.getLkm()); 
    joukkueet.lisaa(lumo1); assertEquals("From: Joukkueet line: 38", 2, joukkueet.getLkm()); 
    assertEquals("From: Joukkueet line: 39", lumo, joukkueet.anna(0)); 
    assertEquals("From: Joukkueet line: 40", lumo1, joukkueet.anna(1)); 
    assertEquals("From: Joukkueet line: 41", false, joukkueet.anna(1) == lumo); 
    assertEquals("From: Joukkueet line: 42", true, joukkueet.anna(1) == lumo1); 
    try {
    assertEquals("From: Joukkueet line: 43", lumo, joukkueet.anna(2)); 
    fail("Joukkueet: 43 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    joukkueet.lisaa(lumo); assertEquals("From: Joukkueet line: 44", 3, joukkueet.getLkm()); 
    joukkueet.lisaa(lumo); assertEquals("From: Joukkueet line: 45", 4, joukkueet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta74 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta74() throws SailoException {    // Joukkueet: 74
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue lumo = new Joukkue(), lumo1 = new Joukkue(); 
    lumo.vastaaLumo(); 
    lumo1.vastaaLumo(); 
    String hakemisto = "Lumo"; 
    String tiedNimi = hakemisto+""; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    joukkueet.lueTiedostosta(tiedNimi); 
    joukkueet.lisaa(lumo); 
    joukkueet.lisaa(lumo1); 
    joukkueet.tallenna(hakemisto); 
    joukkueet = new Joukkueet();  // Poistetaan vanhat luomalla uusi
    joukkueet.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
    joukkueet.lisaa(lumo1); 
    joukkueet.tallenna(hakemisto); 
    assertEquals("From: Joukkueet line: 97", false, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Joukkueet line: 99", false, fbak.delete()); 
    assertEquals("From: Joukkueet line: 100", false, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi177 
   * @throws SailoException when error
   */
  @Test
  public void testEtsi177() throws SailoException {    // Joukkueet: 177
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue lumo = new Joukkue(); 
    lumo.parse("1|Lumo"); 
    Joukkue lumo1 = new Joukkue(); 
    lumo1.parse("2|Lumo"); 
    joukkueet.lisaa(lumo); joukkueet.lisaa(lumo1); 
  } // Generated by ComTest END
}