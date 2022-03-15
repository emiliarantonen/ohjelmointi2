package harkkatyo;

import java.io.*;
import java.util.*;

/**
 * crc tietoja tähän
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class Joukkueet {
    private static final int Max_joukkueita = 1000;
    private int              lkm           = 0;
    private String           tiedostonNimi = "";
    private Joukkue          alkiot[]      = new Joukkue[Max_joukkueita];


    /**
     * Oletusmuodostaja
     */
    public Joukkueet() {
        // Attribuuttien oma alustus riittää
    }


    /**
     * @param joukkue -
     * @throws SailoException -
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = joukkue;
        lkm++;
    }


    /**
     * Palauttaa viitteen i:teen joukkueeseen.
     * @param i monennenko joukkueeseen viite halutaan
     * @return viite joukkueen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Joukkue anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }


    /**
     * Lukee joukkueen tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String tiedostonNimi = hakemisto + "/joukkueet.dat";
        File ftied = new File(tiedostonNimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))){
            while ( fi.hasNext()) {
                String s = fi.nextLine();
                if (s==null || "".equals(s) || s.charAt(0)==';') continue;
                Joukkue joukkue = new Joukkue();
                joukkue.parse(s);
                lisaa(joukkue);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
        }
    }


    /**
     * Palauttaa joukkueiden lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Tallentaa joukkueet tiedostoon
     * @param hakemisto -
     * @throws SailoException -
     */
    public void tallenna(String hakemisto) throws SailoException {
        File ftied = new File(hakemisto + "/joukkueet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))){
            for (int i=0; i<getLkm(); i++) {
                Joukkue joukkue = this.anna(i);
                fo.println(joukkue.toString());
            }
        } catch (FileNotFoundException ex ) {
            throw new SailoException("Tiedosto "+ ftied.getAbsolutePath()+ " ei aukea");
        }
    }
    
    

    /**
     * Testiohjelma jäsenistölle
     * @param args ei käytössä
     * @throws SailoException ei käytössä
     */
    public static void main(String args[]) throws SailoException {
        Joukkueet joukkueet = new Joukkueet();
        
        try {
            joukkueet.lueTiedostosta("rekisteri");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        lumo.vastaaLumo();
        sirius.rekisteroi();


        try {
            joukkueet.lisaa(lumo);
            joukkueet.lisaa(sirius);


        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("============= Joukkueet testi =================");

        for (int i = 0; i < joukkueet.getLkm(); i++) {
            Joukkue joukkue = joukkueet.anna(i);
            System.out.println("Joukkue nro: " + i);
            joukkue.tulosta(System.out);
        }
        
        try{
            joukkueet.tallenna("rekisteri");
        } catch (SailoException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }



}
