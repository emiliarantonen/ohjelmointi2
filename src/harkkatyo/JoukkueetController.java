package harkkatyo;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class JoukkueetController implements Initializable{
    
    @FXML private ListChooser<Joukkue> chooserJoukkueet;
    @FXML private TextField hakuehto;
    @FXML private ScrollPane panelJoukkue;
    
    private String joukkueenNimi = "Lumo";

    @FXML
    private void handleLisaaJoukkue() {
        uusiJoukkue();
    }

    @FXML
    void handleNayta() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
    }

    @FXML
    void handlePoistaJoukkue() {
        Dialogs.showMessageDialog("Ei osaa vielä poistaa");
    }
    
    @FXML
    void handleHakuehto() {
        Dialogs.showMessageDialog("Ei osaa vielä poistaa");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    /**
     * 
     */
    @FXML
    public void handleHelp() {
        apua();
    }
    
    
    /**
     * 
     */
    @FXML
    public void handleAvaa() {
        avaa();
    }
    
    /**
     * 
     */
    @FXML
    public void handleTallenna() {
        tallenna();
    }
    
    /**
     * 
     */
    @FXML
    public void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    /**
     * 
     */
    @FXML
    public void handleTulosta() {
        Dialogs.showMessageDialog("Tulostetaan, mutta ei osata vielä");
    }
    
    /**
     * 
     */
    @FXML
    public void handleLisaaKilpailu() {
        //ModalController.showModal(JoukkueetController.class.getResource("LisaaKilpailu.fxml"), "Lisää kilpailu", null, "");
        lisaaKilpailu();
    }
   
    
    

   //==================
    
    private Rekisteri rekisteri;
    private Joukkue joukkueKohdalla;
    private TextArea areaJoukkue = new TextArea();
    
    private void apua() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/mmnuppoz");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }

    }

    
    
    
    
    /**
     * @param rekisteri -
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
    }
    
    private void uusiJoukkue() {
        Joukkue uusi = new Joukkue();
        uusi.rekisteroi();
        uusi.vastaaLumo();
        try {
            rekisteri.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }
        hae(uusi.getIdNro());
    }
    
    /**git
     * 
     */
    public void lisaaKilpailu() { 
        if ( joukkueKohdalla == null ) return;  
        Kilpailu kil = new Kilpailu();  
        kil.rekisteroi();  
        kil.vastaaSMKisat(joukkueKohdalla.getIdNro());  
        rekisteri.lisaa(kil);  
        hae(joukkueKohdalla.getIdNro());          
    }
    
    private void hae(int nro) {
        chooserJoukkueet.clear();
        
        int index=0;
        for(int i=0; i< rekisteri.getJoukkueita(); i++) {
            Joukkue joukkue = rekisteri.annaJoukkue(i);
            if (joukkue.getIdNro() == nro) index =i;
            chooserJoukkueet.add(""+joukkue.getIdNro() +" "+ joukkue.getNimi(), joukkue);
        }
        
       chooserJoukkueet.setSelectedIndex(index);
    }

    
    private void alusta() {
        panelJoukkue.setContent(areaJoukkue);
        areaJoukkue.setFont(new Font("Courier New", 12));
        panelJoukkue.setFitToHeight(true);
        
        chooserJoukkueet.clear();
        chooserJoukkueet.addSelectionListener(e -> naytaJoukkue());
 
    }
    
    private void naytaJoukkue() {
        joukkueKohdalla = chooserJoukkueet.getSelectedObject();

        if (joukkueKohdalla == null) return;

        areaJoukkue.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaJoukkue)) {
            tulosta(os,joukkueKohdalla);  
        }
    }
    
    /**
     * @param os -
     * @param joukkue --
     */
    public void tulosta(PrintStream os, final Joukkue joukkue)  {
        os.println("----------------------------------------------");
        joukkue.tulosta(os);
        os.println("----------------------------------------------");
        try {
            List<Kilpailu> kilpailut = rekisteri.annaKilpailut(joukkue);   
            for (Kilpailu kil:kilpailut)
                kil.tulosta(os);  
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Kilpailun hakemisessa ongelmia! " + ex.getMessage());
        }  
    }
    
    /**
     * @return -
     */
    public boolean avaa() {
        String uusinimi = RekisteriGUIController.kysyNimi(null, joukkueenNimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    /**
     * @param nimi -
     */
    protected void lueTiedosto(String nimi) {
        joukkueenNimi = nimi;
        setTitle("Joukkue - " + joukkueenNimi);
        
        try {
            rekisteri.lueTiedostosta(nimi);
            hae(0);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        
    }
   
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    /**
     * @return -
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    private void tallenna() {
        try {
            rekisteri.tallenna();
        }catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    
}
