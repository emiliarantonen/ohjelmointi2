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
public class Kilpailut implements Iterable<Kilpailu> {
    
    private final List<Kilpailu> alkiot = new ArrayList<Kilpailu> ();
    //private Kilpailu alkiot[]=new Kilpailu[100];
    private int lkm=0;
    private boolean muutettu=false;
    
    /**
     * @param kil lisättävä kilpailu
     * @throws SailoException -
     */
    public void lisaa(Kilpailu kil) throws SailoException {
        alkiot.add(kil);
        muutettu = true;
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
        for (int i=0; i<getLkm(); i++)
            if ( alkiot.get(i).getIdNro() == tunnusNro ) etsityt.add(alkiot.get(i));

        //for (int i=0; i<lkm; i++)
            //if (alkiot[i].getIdNro()== tunnusNro) etsityt.add(alkiot[i]);
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

    public void korvaaTaiLisaa(Kilpailu kilpailu) throws SailoException {
        int id = kilpailu.getTunnusNro();
        for (int i = 0; i < getLkm(); i++) {
            if ( alkiot.get(i).getTunnusNro() == id ) {
                alkiot.set(i,kilpailu);
                muutettu = true;
                return;
            }
        }
        lisaa(kilpailu);
    }
    
    public int getLkm() {
        return alkiot.size();
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

        try {
            kilpailut.lisaa(SM1);
            kilpailut.lisaa(SM2);
            kilpailut.lisaa(SM3);
            kilpailut.lisaa(SM4);
        } catch (SailoException e1) {
            System.out.println(e1.getMessage());
        }
        

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

    @Override
    public Iterator<Kilpailu> iterator() {
        return alkiot.iterator();
    }

}
