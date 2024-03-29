package harkkatyo;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author emiliarantonen
 * @version 3.3.2022
 *
 */
public class Rekisteri {
    
    private Joukkueet joukkueet = new Joukkueet();
    private Kilpailut kilpailut = new Kilpailut();
    
    //private String hakemisto="Lumo";

   
    /**
     * Lisää rekisteriin uuden joukkueen
     * @param joukkue lisättävä joukkue
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Rekisteri rekisteri = new Rekisteri();
     * Joukkue lumo1 = new Joukkue(), lumo2 = new Joukkue();
     * rekisteri.lisaa(lumo1);
     * rekisteri.lisaa(lumo2);
     * rekisteri.lisaa(lumo1);
     * Collection<Joukkue> loytyneet = rekisteri.etsi("",-1); 
     * Iterator<Joukkue> it = loytyneet.iterator();
     * it.next() === lumo1;
     * it.next() === lumo2;
     * it.next() === lumo1;
     * </pre>
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
    }
    
    /**
     * @param kilpailu -
     * @throws SailoException -
     */
    public void lisaa(Kilpailu kilpailu) throws SailoException {
        kilpailut.lisaa(kilpailu);
    }
    
    /**
     * @return joukkueiden lukumäärä
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
    }
    
    /**
     * @param i -
     * @return -
     */
    public Joukkue annaJoukkue(int i) {
        return joukkueet.anna(i);
    }
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien jäsenten viitteet 
     * @param hakuehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä joukkueista 
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Joukkue> etsi(String hakuehto, int k) throws SailoException { 
        return joukkueet.etsi(hakuehto, k); 
    }
    
    /**
     * Haetaan kaikki joukkueen kilpailut
     * @param joukkue joukkue jolle kilpailuja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin kilpailuihin
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.util.*;
     * 
     *  Rekisteri rekisteri = new Rekisteri();
     *  Joukkue lumo1 = new Joukkue(), lumo2 = new Joukkue(), lumo3 = new Joukkue();
     *  lumo1.rekisteroi(); lumo2.rekisteroi(); lumo3.rekisteroi();
     *  int id1 = lumo1.getIdNro();
     *  int id2 = lumo2.getIdNro();
     *  Kilpailu SM1= new Kilpailu(id1); rekisteri.lisaa(SM1);
     *  Kilpailu SM2 = new Kilpailu(id1); rekisteri.lisaa(SM2);
     *  Kilpailu SM3 = new Kilpailu(id2); rekisteri.lisaa(SM3);
     *  Kilpailu SM4 = new Kilpailu(id2); rekisteri.lisaa(SM4);
     *  Kilpailu SM5 = new Kilpailu(id2); rekisteri.lisaa(SM5);
     *  
     *  List<Kilpailu> loytyneet;
     *  loytyneet = rekisteri.annaKilpailut(lumo3);
     *  loytyneet.size() === 0; 
     *  loytyneet = rekisteri.annaKilpailut(lumo1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == SM1 === true;
     *  loytyneet.get(1) == SM2 === true;
     *  loytyneet = rekisteri.annaKilpailut(lumo2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == SM3 === true;
     * </pre> 
     */
    public List<Kilpailu> annaKilpailut(Joukkue joukkue){
        return kilpailut.annaKilpailut(joukkue.getIdNro());
    }
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        joukkueet.setTiedostonPerusNimi(hakemistonNimi + "joukkueet");
        kilpailut.setTiedostonPerusNimi(hakemistonNimi + "kilpailut");
    }

    
   
    /**
     * Lukee joukkueen tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *  Rekisteri rekisteri = new Rekisteri();
     *  
     *  Joukkue lumo = new Joukkue(); lumo.vastaaLumo(); lumo.rekisteroi();
     *  Joukkue lumo1 = new Joukkue(); lumo1.vastaaLumo(); lumo1.rekisteroi();
     *  Kilpailu SM1 = new Kilpailu(); SM1.vastaaSMKisat(lumo1.getIdNro());
     *  Kilpailu SM2 = new Kilpailu(); SM2.vastaaSMKisat(lumo.getIdNro());
     *   
     *  String hakemisto = "koerekisteri";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/joukkueet.dat");
     *  File fhtied = new File(hakemisto+"/kilailut.dat");
     *  dir.mkdir();  
     *  ftied.delete();
     *  fhtied.delete();
     *  rekisteri.lueTiedostosta(hakemisto); #THROWS SailoException
     *  rekisteri.lisaa(lumo);
     *  rekisteri.lisaa(lumo1);
     *  rekisteri.lisaa(SM1);
     *  rekisteri.lisaa(SM2);
     *  rekisteri.tallenna();
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException{
        joukkueet = new Joukkueet(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        kilpailut = new Kilpailut();

        setTiedosto(nimi);

        joukkueet.lueTiedostosta(nimi);
        kilpailut.lueTiedostosta("Lumo");
    }
    
    
    /**
     * @throws SailoException -
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            joukkueet.tallenna();            
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            kilpailut.tallenna();
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        
        if ( !"".equals(virhe)) throw new SailoException(virhe);
    }
    
    public void korvaaTaiLisaa(Kilpailu kilpailu) throws SailoException { 
        kilpailut.korvaaTaiLisaa(kilpailu); 
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Rekisteri rekisteri = new Rekisteri();
        
        try {
            rekisteri.lueTiedostosta("joukkueet");
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        
        Joukkue lumo = new Joukkue();
        lumo.rekisteroi();
        lumo.vastaaLumo();

        try {
            rekisteri.lisaa(lumo);
            
            
            for (int i = 0; i < rekisteri.getJoukkueita(); i++) {
                Joukkue joukkue = rekisteri.annaJoukkue(i);
                joukkue.tulosta(System.out);
            }
            
            System.out.println("============= Kerhon testi =================");
            Collection<Joukkue> joukkueet = rekisteri.etsi("", -1);
            int i = 0;
            for (Joukkue joukkue: joukkueet) {
                System.out.println("Joukkue paikassa: " + i);
                joukkue.tulosta(System.out);
                List<Kilpailu> loytyneet = rekisteri.annaKilpailut(joukkue);
                for (Kilpailu kilpailu : loytyneet)
                    kilpailu.tulosta(System.out);
                i++;
            }
            
            rekisteri.tallenna();
        } catch (SailoException e) {
            
            System.err.println(e.getMessage());
        }
        


    }

    public int poista(Joukkue joukkue) {
        if (joukkue == null) return 0;
        int ret = joukkueet.poista(joukkue.getIdNro());
        kilpailut.poistaJoukkueenKilpailut(joukkue.getIdNro());
        return ret;
    }
    
    public void poistaKilpailu(Kilpailu kilpailu) {
        kilpailut.poista(kilpailu);
    }

        
    

}
