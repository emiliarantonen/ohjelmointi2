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
public class Joukkue implements Cloneable, Tietue{
    
    private int idNro;
    private String joukkueenNimi = "";
    private static int seuraavaNro    = 1;
    
    public Joukkue() {
        //
    }
    
    /**
     * @return joukkueen nimi
     * @example
     * <pre name="test">
     *   Joukkue lumo = new Joukkue();
     *   lumo.vastaaLumo();
     *   lumo.getNimi() =R= "Lumo";
     * </pre>
     */
    @Override
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
        joukkueenNimi= Mjonot.erota(sb, '|', getNimi());
    }
    
    public boolean equals(Joukkue joukkue) {
        if ( joukkue == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(joukkue.anna(k)) ) return false;
        return true;
    }


    @Override
    public boolean equals(Object joukkue) {
        if ( joukkue instanceof Joukkue ) return equals((Joukkue)joukkue);
        return false;
    }


    @Override
    public int hashCode() {
        return idNro;
    }
    
    @Override
    public Joukkue clone() throws CloneNotSupportedException {
        Joukkue uusi;
        uusi = (Joukkue) super.clone();
        return uusi;
    }
    

    @Override
    public int getKenttia() {
        
        return 2;
    }

    @Override
    public int ekaKentta() {
        
        return 1;
    }

    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Id nro";
        case 1: return "Joukkueen nimi";
        default: return "Olet ihana";
        }
    }

    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + idNro;
        case 1: return "" + joukkueenNimi;
        default:
            return "Olet ihana";
        }
    }


    @Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setIdNro(Mjonot.erota(sb, '§', getIdNro()));
            return null;
        case 1:
            joukkueenNimi = tjono;
            return null;
        default:
            return "Olet ihana";
        }
    }
    
    /**
     * Testiohjelma joukkueelle.
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Joukkue lumo = new Joukkue();
        lumo.rekisteroi();
     
        lumo.vastaaLumo();
        
        lumo.tulosta(System.out);
       

    }

    public void setNimi(String s) {
        joukkueenNimi = s;
    }

    

}
        


