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
public class Kilpailu {
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
     * @return - 
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
     * @param s -
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
    
    @Override
    public String toString() {
        return ""+ getTunnusNro() + "|"+ idNro+"|"+kilpailu +"|" + vuosi +"|"+sarja+"|"+pisteet+"|"+sijoitus;
    }
}
