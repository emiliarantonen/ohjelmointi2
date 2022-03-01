package harkkatyo;

import java.io.PrintStream;

/**
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
     */
    public static void main(String args[]) {
        Joukkueet joukkue = new Joukkueet();

        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        sirius.rekisteroi();


        try {
            joukkue.lisaa(lumo);
            joukkue.lisaa(sirius);

            System.out.println("============= Joukkueet testi =================");

            for (int i = 0; i < joukkue.getLkm(); i++) {
                Joukkue joukkue1 = joukkue.anna(i);
                System.out.println("Jäsen nro: " + i);
                joukkue1.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }



}
