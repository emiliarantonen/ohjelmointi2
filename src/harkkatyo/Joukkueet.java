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
     * Lisää uuden joukkueen rekisteriin.  Ottaa joukkueen omistukseensa.
     * @param joukkue lisätäävän jäsenen viite.  Huom tietorakenne muuttuu omistajaksi
     * @example
     * <pre name="test">
     * Joukkueet joukkueet = new Joukkueet();
     * Joukkue lumo = new Joukkue(), lumo1 = new Joukkue();
     * joukkueet.getLkm() === 0;
     * joukkueet.lisaa(lumo); joukkueet.getLkm() === 1;
     * joukkueet.lisaa(lumo1); joukkueet.getLkm() === 2;
     * joukkueet.anna(0) === lumo;
     * joukkueet.anna(1) === lumo1;
     * joukkueet.anna(1) == lumo === false;
     * joukkueet.anna(1) == lumo1 === true;
     * joukkueet.anna(2) === lumo; #THROWS IndexOutOfBoundsException 
     * joukkueet.lisaa(lumo); joukkueet.getLkm() === 3;
     * joukkueet.lisaa(lumo); joukkueet.getLkm() === 4;
     * </pre>
     */
    public void lisaa(Joukkue joukkue) {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+20);
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
     * Lukee jäsenistön tiedostosta. 
     * @param hakemisto tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *  Joukkueet joukkueet = new Joukkueet();
     *  Joukkue lumo = new Joukkue(), lumo1 = new Joukkue();
     *  lumo.vastaaLumo();
     *  lumo1.vastaaLumo();
     *  String hakemisto = "Lumo";
     *  String tiedNimi = hakemisto+"";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  joukkueet.lueTiedostosta(tiedNimi); 
     *  joukkueet.lisaa(lumo);
     *  joukkueet.lisaa(lumo1);
     *  joukkueet.tallenna(hakemisto);
     *  joukkueet = new Joukkueet();            // Poistetaan vanhat luomalla uusi
     *  joukkueet.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
     *  joukkueet.lisaa(lumo1);
     *  joukkueet.tallenna(hakemisto);
     *  ftied.delete() === false;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === false;
     *  dir.delete() === false;
     * </pre>
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
     * Luokka joukkueiden iteroimiseksi.
     */
    public class JoukkueetIterator implements Iterator<Joukkue> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa jäsentä
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä jäseniä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava jäsen
         * @return seuraava jäsen
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Joukkue next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
            return anna(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Me ei poisteta");
        }
    }


    /**
     * Palautetaan iteraattori jäsenistään.
     * @return jäsen iteraattori
     */
    public Iterator<Joukkue> iterator() {
        return new JoukkueetIterator();
    }
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien jäsenten viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä jäsenistä 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Joukkueet joukkueet = new Joukkueet(); 
     *   Joukkue lumo = new Joukkue(); 
     *   lumo.parse("1|Lumo");
     *   Joukkue lumo1 = new Joukkue(); 
     *   lumo1.parse("2|Lumo"); 
     *   joukkueet.lisaa(lumo); joukkueet.lisaa(lumo1); 
     *   // TODO: toistaiseksi palauttaa kaikki jäsenet 
     * </pre> 
     */
    @SuppressWarnings("unused")
    public Collection<Joukkue> etsi(String hakuehto, int k) { 
        Collection<Joukkue> loytyneet = new ArrayList<Joukkue>(); 
        for (Joukkue joukkue : alkiot) { 
            loytyneet.add(joukkue);  
        } 
        return loytyneet; 
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
