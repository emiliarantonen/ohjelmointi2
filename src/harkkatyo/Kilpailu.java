package harkkatyo;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class Kilpailu implements Cloneable, Tietue{
    private int idNro;
    private int tunnusNro;
    private String kilpailu;
    private int vuosi;
    private String sarja;
    private double pisteet;
    private int sijoitus;
    
    private static int seuraavaNro = 1;

    public void vastaaSMKisat(int nro) {
        
        idNro = nro;
        kilpailu = "SM-Kilpailut";
        vuosi = rand(2000, 2022);
        sarja = "14-16 v.";
        pisteet = rand(15.00, 19.00);
        sijoitus = rand(1, 10);
            
    }
    
    public Kilpailu(int nro) {
        this.idNro = nro;
    }
    
    public Kilpailu() {
        //
    }
    
    @Override
    public int getKenttia() {
        return 7;
    }
    
    @Override
    public int ekaKentta() {
        return 2;
    }

    /**
     * @param ala -
     * @param yla -
     * @return -
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
      }
    
    public static double rand(double ala, double yla) {
        double n = (yla-ala)*Math.random() + ala;
        return Math.round(n);
      }
       
    /**
     * Antaa kilpailulle seuraavan rekisterinumeron.
     * @return kilpailun uusi tunnus_nro
     * @example
     * <pre name="test">
     *   Kilpailu SM1 = new Kilpailu();
     *   SM1.getTunnusNro() === 0;
     *   SM1.rekisteroi();
     *   Kilpailu SM2 = new Kilpailu();
     *   SM2.rekisteroi();
     *   int n1 = SM1.getTunnusNro();
     *   int n2 = SM2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    /**
     * @return mille joukkueelle kilpailu kuuluu
     */
    public int getIdNro() {
        return idNro;
    }
    
    /**
     * Palautetaan kilpailun oma id
     * @return kilpailun id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    private void setTunnusNro(int nro) {
        tunnusNro= nro;
        if(tunnusNro>= seuraavaNro) seuraavaNro = tunnusNro +1;
    }
    
    @Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        switch ( k ) {
        case 0:
            idNro=Integer.parseInt(tjono);
            return null;
        case 1:
            tunnusNro=Integer.parseInt(tjono);
            return null;
        case 2:
            kilpailu = tjono;
            return null;
        case 3:
            vuosi = Integer.parseInt(tjono);;
            return null;
        case 4:
            sarja = tjono;
            return null;
        case 5:
            pisteet = Double.parseDouble(tjono);
            return null;
        case 6:
            sijoitus = Integer.parseInt(tjono);
            return null;
        default:
            return "ÄÄliö";
        }
    }
    
    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Id nro";
        case 1: return "tunnus nro";
        case 2: return "Kilpailu";
        case 3: return "Vuosi";
        case 4: return "Sarja";
        case 5: return "Pisteet";
        case 6: return "Sijoitus";
        default: return "Äääliö";
        }
    }
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */

    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + idNro;
        case 1: return "" + tunnusNro;
        case 2: return "" + kilpailu;
        case 3: return "" + vuosi;
        case 4: return "" + sarja;
        case 5: return "" + pisteet;
        case 6: return "" + sijoitus;
        default: return "Äääliö";
        }
    }
    
    /**
     * @param os -
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * @param out -
     */
    public void tulosta(PrintStream out) {
        out.println(tunnusNro +" "+idNro + " " +kilpailu + " " + vuosi + " " + sarja + " " + pisteet + " " + sijoitus);
    }
    
   
    
    /**
     * @param args -
     */
    public static void main(String[] args) {
        Kilpailu kil = new Kilpailu();
        kil.vastaaSMKisat(2);
        kil.tulosta(System.out);
    }

    /**
     * Selvitää kilpailun tiedot | erotellusta merkkijonosta.
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusnro.
     * @param s josta kilpailun tiedot otetaan
     * @example
     * <pre name="test">
     *   Kilpailu kilpailu = new Kilpailu();
     *   kilpailu.parse("   0   |  3  |   SM-kilpailut  ");
     *   kilpailu.getTunnusNro() === 0;
     *   kilpailu.toString().startsWith("0|3|SM-kilpailut|") === true;
     * </pre>
     */
    public void parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        idNro = Mjonot.erota(sb,  '|', idNro);
        kilpailu=Mjonot.erota(sb,  '|', kilpailu);
        vuosi=Mjonot.erota(sb,  '|', vuosi);
        sarja=Mjonot.erota(sb,  '|', sarja);
        pisteet=Mjonot.erota(sb,  '|', pisteet);
        sijoitus=Mjonot.erota(sb,  '|', sijoitus);
        
    }
    
    
    /**
     * Palauttaa kilpailun tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kilpailu tolpilla eroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kilpailu kil = new Kilpailu();
     *   kil.parse("   0   |  1  |   SM-kilpailu  | 2020 ");
     *   kil.toString().startsWith("0|1|SM-kilpailu|2020|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return ""+ getTunnusNro() + "|"+ idNro+"|"+kilpailu +"|" + vuosi +"|"+sarja+"|"+pisteet+"|"+sijoitus;
    }

    public String getNimi() {
        return kilpailu;
    }
    
    @Override
    public Kilpailu clone() throws CloneNotSupportedException {
        Kilpailu uusi;
        uusi = (Kilpailu) super.clone();
        return uusi;
    }
}
