package harkkatyo;

import java.io.PrintStream;

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
        tiedostonNimi = hakemisto + "/nimet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa joukkueen tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }


    /**
     * Palauttaa joukkueiden lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }


    /**
     * Testiohjelma jäsenistölle
     * @param args ei käytössä
     * @throws SailoException ei käytössä
     */
    public static void main(String args[]) throws SailoException {
        Joukkueet joukkueet = new Joukkueet();

        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        lumo.vastaaLumo();
        sirius.rekisteroi();


        try {
            joukkueet.lisaa(lumo);
            joukkueet.lisaa(sirius);

            System.out.println("============= Joukkueet testi =================");

            for (int i = 0; i < joukkueet.getLkm(); i++) {
                Joukkue joukkue = joukkueet.anna(i);
                System.out.println("Joukkue nro: " + i);
                joukkue.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }



}
