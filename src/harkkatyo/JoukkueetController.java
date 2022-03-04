package harkkatyo;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class JoukkueetController implements Initializable{
    
    @FXML private ListChooser<Joukkue> chooserJoukkueet;
    @FXML private TextField hakuehto;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    

   //==================
    private Rekisteri rekisteri;
    
    
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
        chooserJoukkueet.clear();
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
        setTitle("Kerho - " + joukkueenNimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
   

}
