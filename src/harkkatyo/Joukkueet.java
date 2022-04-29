package harkkatyo;

import java.io.*;
import java.util.*;

import fi.jyu.mit.ohj2.WildChars;

/**
 * crc tietoja tähän
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class Joukkueet implements Iterable<Joukkue>{
    private static final int Max_joukkueita = 1000;
    private int              lkm           = 0;
    private String           tiedostonNimi = "";
    private String tiedostonPerusNimi = "joukkueet";
    private Joukkue          alkiot[]      = new Joukkue[Max_joukkueita];
    private boolean muutettu=false;


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
        muutettu=true;
    }
    
    /**
     * @param joukkue -
     * @throws SailoException -  
     */
    public void korvaaTaiLisaa(Joukkue joukkue) throws SailoException {
        int id = joukkue.getIdNro();
        for (int i = 0; i < lkm; i++) {
            if ( alkiot[i].getIdNro() == id ) {
                alkiot[i] = joukkue;
                muutettu = true;
                return;
            }
        }
        lisaa(joukkue);
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
     * @param tied tiedoston perusnimi
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
    public void lueTiedostosta(String tied) throws SailoException {
            setTiedostonPerusNimi(tied);
            try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
                tiedostonNimi = fi.readLine();
                if ( tiedostonNimi == null ) throw new SailoException("Joukkueen nimi puuttuu");
                String rivi = fi.readLine();
                if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");
                // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

                while ( (rivi = fi.readLine()) != null ) {
                    rivi = rivi.trim();
                    if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                    Joukkue joukkue = new Joukkue();
                    joukkue.parse(rivi); // voisi olla virhekäsittely
                    lisaa(joukkue);
                }
                muutettu = false;
            } catch ( FileNotFoundException e ) {
                throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
            } catch ( IOException e ) {
                throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
            }

//        String tiedostonNimi =hakemisto + "/joukkueet.dat";
//        File ftied = new File(tiedostonNimi);
//        
//        try (Scanner fi = new Scanner(new FileInputStream(ftied))){
//            while ( fi.hasNext()) {
//                String s = fi.nextLine();
//                if (s==null || "".equals(s) || s.charAt(0)==';') continue;
//                Joukkue joukkue = new Joukkue();
//                joukkue.parse(s);
//                lisaa(joukkue);
//            }
//        } catch (FileNotFoundException e) {
//            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
//        }
    }
    
    
    /**
     * Tallentaa jäsenistön tiedostoon.  Kesken.
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }

    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi= nimi;
        
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
    @Override
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
    public Collection<Joukkue> etsi(String hakuehto, int k) {
        String ehto = "*";
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto;
        int hk=k;
        if (hk<0) hk=1;
        List<Joukkue> loytyneet = new ArrayList<Joukkue>();
        for (Joukkue joukkue : this) { 
            if (WildChars.onkoSamat(joukkue.anna(hk), ehto)) loytyneet.add(joukkue);  
        } 
        //Collections.sort(loytyneet, new Joukkue.Vertailija(hk));
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
     * @throws SailoException -
     */
    public void tallenna() throws SailoException {
        
//        File ftied = new File(hakemisto + "/kilpailut.dat");
//        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))){
//            for (Kilpailu kil: alkiot) {
//                  fo.println(kil.toString());
//            }
//        } catch (FileNotFoundException ex ) {
//            throw new SailoException("Tiedosto "+ ftied.getAbsolutePath()+ " ei aukea");
//        }
//        
        
        
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Joukkue joukkue : this) {
                fo.println(joukkue.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
    
    /**
     * Palauttaa Kerhon koko nimen
     * @return Kerhon koko nimi merkkijononna
     */
    public String getKokoNimi() {
        return tiedostonNimi;
    }
    
    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    public int etsiId(int id) { 
      for (int i = 0; i < lkm; i++) 
      if (id == alkiot[i].getIdNro()) return i; 
        return -1; 
    }
    
    public int poista(int id) {
        int ind = etsiId(id);
        if (ind < 0) return 0;
        lkm--;
        for (int i = ind; i < lkm; i++)
            alkiot[i] = alkiot[i + 1];
        alkiot[lkm] = null;
        muutettu = true;
        return 1;
    }
    


    /**
     * Testiohjelma jäsenistölle
     * @param args ei käytössä
     * @throws SailoException ei käytössä
     */
    public static void main(String args[]) throws SailoException {
        Joukkueet joukkueet = new Joukkueet();
        
        try {
            joukkueet.lueTiedostosta("Lumo");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Joukkue lumo = new Joukkue();
        lumo.rekisteroi();
        lumo.vastaaLumo();


        try {
            joukkueet.lisaa(lumo);

        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("============= Joukkueet testi =================");

        for (int i = 0; i < joukkueet.getLkm(); i++) {
            Joukkue joukkue = joukkueet.anna(i);
            System.out.println("Joukkue nro: " + i);
            joukkue.tulosta(System.out);
        }
        
        
    }



}
