package harkkatyo;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author mineanupponen
 * @authhor emiliarantonen
 * @version 1.2.2022
 *
 *Luokka käyttöliittymän tapahtumien hoitamiseksi
 */
public class RekisteriGUIController implements ModalControllerInterface<String>{
    
    @FXML private TextField textVastaus;
    
    
    private String vastaus = null;

    /**
     * Uuden joukkueen lisääminen
     */

    
    /**
     * Käsitellään joukkueen hakeminen
     */
    @FXML private void handleHaeJoukkue() {
        //Dialogs.showMessageDialog("Ei osata vielä lisätä");
        //ModalController.showModal(RekisteriGUIController.class.getResource("Joukkueet.fxml"), "Joukkue", null, "");
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
        
    }
   
   
    
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        textVastaus.requestFocus();
        
    }

    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    
  //=========================================================================
    
 
    /**
     * @param modalityStage -
     * @param oletus -
     * @return -
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                RekisteriGUIController.class.getResource("RekisteriGUIView.fxml"),
                "Rekisteri",
                modalityStage, oletus);
    }

        
  
}

    

    
