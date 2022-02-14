package harkkatyo;

public class Joukkue {
    
    String kilpailu, sarja;
    int vuosi, sijoitus;
    double pisteet;
    
    public Joukkue(String kilpailu, int vuosi, String sarja, double pisteet, int sijoitus) {
        this.kilpailu = kilpailu;
        this.sarja = sarja;
        this.vuosi = vuosi;
        this.sijoitus = sijoitus;
        this.pisteet = pisteet;
    }
    
    public void setKilpailu(String kilpailu) {
        this.kilpailu = kilpailu;
    }
    
    public void setSarja(String sarja) {
        this.sarja = sarja;
    }
    
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
