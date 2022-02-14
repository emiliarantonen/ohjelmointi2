package harkkatyo;

/**
 * Joukkueen tiedot, jolle lisätään uusia kilpailuja
 * 
 * @author emiliarantonen
 * @version 14.2.2022
 *
 */
public class Joukkue {
    
    String kilpailu, sarja;
    int vuosi, sijoitus;
    double pisteet;
    
    /**
     * @param kilpailu -
     * @param vuosi -
     * @param sarja -
     * @param pisteet -
     * @param sijoitus -
     */
    public Joukkue(String kilpailu, int vuosi, String sarja, double pisteet, int sijoitus) {
        this.kilpailu = kilpailu;
        this.sarja = sarja;
        this.vuosi = vuosi;
        this.sijoitus = sijoitus;
        this.pisteet = pisteet;
    }
    
    /**
     * @param kilpailu -
     */
    public void setKilpailu(String kilpailu) {
        this.kilpailu = kilpailu;
    }
    
    /**
     * 
     * @param sarja -
     */
    public void setSarja(String sarja) {
        this.sarja = sarja;
    }
    
    /**
     * 
     * @param vuosi -
     */
    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }
    
    public void setSijoitus(int sijoitus) {
        this.sijoitus = sijoitus;
    }
    
    public void setPisteet(double pisteet) {
        this.pisteet = pisteet;
    }
    
    public String getKilpailu(){
        return kilpailu;
    }
    
    public String getSarja(){
        return sarja;
    }
    
    public int getVuosi() {
        return vuosi;
    }
    
    public int getSijoitus() {
        return sijoitus;
    }
    
    public double getPisteet() {
        return pisteet;
    }
}
