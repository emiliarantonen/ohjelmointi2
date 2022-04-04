package harkkatyo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * @author emiliarantonen
 * @version 8.3.2022
 *
 */
public class Kilpailut {
    
    private final Collection<Kilpailu> alkiot = new ArrayList<Kilpailu> ();
    
    /**
     * @param kil lisättävä kilpailu
     */
    public void lisaa(Kilpailu kil) {
        alkiot.add(kil);
    }
    
    /**
     * Haetaan kaikki joukkueen kilpailut
     * @param tunnusNro joukkueen tunnusnumero jolle kilpailuja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin kilpailuihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Kilpailut kil = new Kilpailut();
     *  Kilpailu sm1 = new Kilpailu(1); kil.lisaa(sm1);
     *  
     *  List<Kilpailu> loytyneet;
     *  loytyneet = kil.annaKilpailut(1);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == sm1 === true;
     * </pre> 
     */
    public List<Kilpailu> annaKilpailut(int tunnusNro) {
        List<Kilpailu> etsityt=new ArrayList<Kilpailu>();
        for (Kilpailu kil : alkiot)
            if (kil.getIdNro() == tunnusNro) etsityt.add(kil);
        return etsityt;
    }
    
    /**
     * @param hakemisto -
     * @throws SailoException -
     */
    public void tallenna(String hakemisto) throws SailoException {
        File ftied = new File(hakemisto + "/kilpailut.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))){
            for (Kilpailu kil: alkiot) {
                  fo.println(kil.toString());
            }
        } catch (FileNotFoundException ex ) {
            throw new SailoException("Tiedosto "+ ftied.getAbsolutePath()+ " ei aukea");
        }
    }
    
    
    /**
     * Lukee kilapilut tiedostosta.
     * @param hakemisto tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Kilpailut kilpailut = new Kilpailut();
     *  Kilpailu SM1 = new Kilpailu(); SM1.vastaaSMKisat(2);
     *  Kilpailu SM2 = new Kilpailu(); SM2.vastaaSMKisat(1);
     *  Kilpailu SM3 = new Kilpailu(); SM3.vastaaSMKisat(2); 
     *  String tiedNimi = "koerekisteri";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  kilpailut.lueTiedostosta(tiedNimi); 
     *  kilpailut.lisaa(SM1);
     *  kilpailut.lisaa(SM2);
     *  kilpailut.lisaa(SM3);
     *  kilpailut.tallenna(tiedNimi);
     *  kilpailut = new Kilpailut();
     *  kilpailut.lueTiedostosta(tiedNimi);
     *  kilpailut.lisaa(SM1);
     *  kilpailut.tallenna(tiedNimi);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String tiedostonNimi = hakemisto + "/kilpailut.dat";
        File ftied = new File(tiedostonNimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))){
            while ( fi.hasNext()) {
                String s = fi.nextLine().trim();
                if (s==null || "".equals(s) || s.charAt(0)==';') continue;
                Kilpailu kil = new Kilpailu();
                kil.parse(s);
                lisaa(kil);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
        }
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kilpailut kilpailut = new Kilpailut();
        
        try {
            kilpailut.lueTiedostosta("rekisteri");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        Kilpailu SM1 = new Kilpailu();
        SM1.vastaaSMKisat(2);
        Kilpailu SM2 = new Kilpailu();
        SM2.vastaaSMKisat(1);
        Kilpailu SM3 = new Kilpailu();
        SM3.vastaaSMKisat(2);
        Kilpailu SM4 = new Kilpailu();
        SM4.vastaaSMKisat(2);

        kilpailut.lisaa(SM1);
        kilpailut.lisaa(SM2);
        kilpailut.lisaa(SM3);
        kilpailut.lisaa(SM4);

        System.out.println("============= Kilpailut testi =================");

        List<Kilpailu> kilpailut2 = kilpailut.annaKilpailut(2);

        for (Kilpailu kil : kilpailut2) {
            System.out.print(kil.getIdNro() + " ");
            kil.tulosta(System.out);
        }
        
        try {
            kilpailut.tallenna("rekisteri");
            
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }

}
