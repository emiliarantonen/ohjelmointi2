package harkkatyo;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

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
     * @return joukkueen nimi
     * @example
     * <pre name="test">
     *   Joukkue lumo = new Joukkue();
     *   lumo.vastaaLumo();
     *   lumo.getNimi() =R= "Lumo";
     * </pre>
     */
    public String getNimi() {
        return joukkueenNimi;
    }
    
    public void vastaaLumo() {
        joukkueenNimi = "Lumo" ;
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
     *   lumo.getIdNro() === 0;
     *   lumo.rekisteroi();
     *   Joukkue sirius = new Joukkue();
     *   sirius.rekisteroi();
     *   int n1 = lumo.getIdNro();
     *   int n2 = sirius.getIdNro();
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
    
    private void setIdNro(int nro) {
        idNro= nro;
        if(idNro>= seuraavaNro) seuraavaNro = idNro +1;
    }
    
    /**
     * Palauttaa joukkueen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return joukkueen tolpilla erotetuilla merkkijonona 
     * @example
     * <pre name="test">
     *   Joukkue joukkue = new Joukkue();
     *   joukkue.parse("   3  |  Lumo   ");
     *   joukkue.toString().startsWith("3|Lumo") === true; // 
     * </pre>  
     */
    @Override
    public String toString() {
       return "" + getIdNro()+ "|" + joukkueenNimi;
    }

    /**
     * Selvitää joukkueen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta jäsenen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Joukkue joukkue = new Joukkue();
     *   joukkue.parse("   3  |  Lumo");
     *   joukkue.getIdNro() === 3;
     *   joukkue.toString().startsWith("3|Lumo") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     *
     *   joukkue.rekisteroi();
     *   int n = joukkue.getIdNro();
     *   joukkue.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   joukkue.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   joukkued.getIdNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setIdNro(Mjonot.erota(sb, '|', getIdNro()));
        joukkueenNimi= Mjonot.erota(sb, '|', "Sirius");
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
