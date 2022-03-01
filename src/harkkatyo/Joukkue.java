package harkkatyo;

import java.io.*;

/**
 * Joukkueen tiedot, jolle lisätään uusia kilpailuja
 * 
 * @author emiliarantonen
 * @version 14.2.2022
 *
 */
public class Joukkue {
    
    private int tunnusNro;
    private String joukkueenNimi = "";
    private static int seuraavaNro    = 1;

    
    /**
     * @return joukkuuen nimi
     */
    public String getNimi() {
        return joukkueenNimi;
    }
    
    /**
     * 
     */
    public void vastaaLumo() {
        joukkueenNimi = "Lumo";
    }

    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(joukkueenNimi);
    }


    /**
     * Tulostetaan henkilön tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Antaa jäsenelle seuraavan rekisterinumeron.
     * @return jäsenen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Jasen aku1 = new Jasen();
     *   aku1.getTunnusNro() === 0;
     *   aku1.rekisteroi();
     *   Jasen aku2 = new Jasen();
     *   aku2.rekisteroi();
     *   int n1 = aku1.getTunnusNro();
     *   int n2 = aku2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }


    /**
     * Palauttaa jäsenen tunnusnumeron.
     * @return jäsenen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }


    /**
     * Testiohjelma jäsenelle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        sirius.rekisteroi();
        lumo.vastaaLumo();
        lumo.tulosta(System.out);
        sirius.tulosta(System.out);

    }

}
