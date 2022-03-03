package harkkatyo;

/**
 * @author emiliarantonen
 * @version 3.3.2022
 *
 */
public class Rekisteri {
    
    private Joukkueet joukkueet = new Joukkueet();

   
    /**
     * @param joukkue -
     * @throws SailoException -
     */
    public void lisaa(Joukkue joukkue) throws SailoException {
        joukkueet.lisaa(joukkue);
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Rekisteri rekisteri = new Rekisteri();
        
        Joukkue lumo = new Joukkue(), sirius = new Joukkue();
        lumo.rekisteroi();
        sirius.rekisteroi();
        lumo.vastaaLumo();
        sirius.vastaaLumo();
        
        try {
            rekisteri.lisaa(lumo);
            rekisteri.lisaa(sirius);
        } catch (SailoException e) {
            
            System.err.println(e.getMessage());
        }
        
        for (int i = 0; i < rekisteri.getJoukkueita(); i++) {
            Joukkue joukkue = rekisteri.annaJoukkue(i);
            joukkue.tulosta(System.out);
        }

    }


        
    

}
