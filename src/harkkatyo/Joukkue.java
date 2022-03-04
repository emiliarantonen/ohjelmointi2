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
    
    private int idNro;
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
        joukkueenNimi = "Lumo " ;
    }

    /**
     * Tulostetaan joukkueen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", idNro)+" "+joukkueenNimi);
    }


    /**
     * Tulostetaan joukkueen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Antaa joukkueelle seuraavan rekisterinumeron.
     * @return joukkueen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Joukkue lumo = new Joukkue();
     *   lumo.getTunnusNro() === 0;
     *   lumo.rekisteroi();
     *   Joukkue sirius = new Joukkue();
     *   sirius.rekisteroi();
     *   int n1 = lumo.getTunnusNro();
     *   int n2 = sirius.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        idNro = seuraavaNro;
        seuraavaNro++;
        return idNro;
    }


    /**
     * Palauttaa joukkueen tunnusnumeron.
     * @return joukkueen tunnusnumero
     */
    public int getIdNro() {
        return idNro;
    }


    /**
     * Testiohjelma joukkueelle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        sirius.rekisteroi();
        lumo.vastaaLumo();
        sirius.vastaaLumo();
        lumo.tulosta(System.out);
        sirius.tulosta(System.out);

    }

}
