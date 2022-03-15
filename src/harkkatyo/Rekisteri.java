package harkkatyo;

import java.io.File;
import java.util.List;

/**
 * @author emiliarantonen
 * @version 3.3.2022
 *
 */
public class Rekisteri {
    
    private Joukkueet joukkueet = new Joukkueet();
    private Kilpailut kilpailut = new Kilpailut();
    
    private String hakemisto="rekisteri";

   
    /**
     * @param joukkue -
     * @throws SailoException -
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
    }
    
    /**
     * @param kilpailu -
     */
    public void lisaa(Kilpailu kilpailu) {
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
     * @param joukkue -
     * @return -
     */
    public List<Kilpailu> annaKilpailut(Joukkue joukkue) {
        return kilpailut.annaKilpailut(joukkue.getIdNro());
    }
    
    /**
     * @param nimi -
     * @throws SailoException -
     */
    public void lueTiedostosta(String nimi) throws SailoException{
        File dir = new File(nimi);
        dir.mkdir();
        joukkueet = new Joukkueet();
        kilpailut = new Kilpailut();
        
        hakemisto=nimi;
        joukkueet.lueTiedostosta(nimi);
        kilpailut.lueTiedostosta(nimi);
    }
    
    
    /**
     * @throws SailoException -
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            joukkueet.tallenna(hakemisto);            
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            kilpailut.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        
        if ( !"".equals(virhe)) throw new SailoException(virhe);
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Rekisteri rekisteri = new Rekisteri();
        
        try {
            rekisteri.lueTiedostosta("koerekisteri");
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        
        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        sirius.rekisteroi();
        lumo.vastaaLumo();
        sirius.vastaaLumo();
        
        try {
            rekisteri.lisaa(lumo);
            rekisteri.lisaa(sirius);
            
            for (int i = 0; i < rekisteri.getJoukkueita(); i++) {
                Joukkue joukkue = rekisteri.annaJoukkue(i);
                joukkue.tulosta(System.out);
            }
            
            rekisteri.tallenna();
        } catch (SailoException e) {
            
            System.err.println(e.getMessage());
        }
        


    }


        
    

}
