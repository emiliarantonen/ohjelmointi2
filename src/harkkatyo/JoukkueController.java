package harkkatyo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author emiliarantonen
 * @version 14.2.2022
 *
 *Luokka joukkuuen tietojen käyttöliittymän hoitamiseksi
 */
public class JoukkueController implements ModalControllerInterface<String>{

    
    
    /**
     * 
     * @param event
     */
    @FXML
    void handleMuokkaa() {
        ModalController.showModal(JoukkueController.class.getResource("LisaaKilpailu.fxml"), "Joukkue", null, "");
    }
    
    /**
     * 
     * 
     */
    @FXML
    public void handleNayta() {
        Dialogs.showMessageDialog("Ei osata vielä näyttää");
    }
    
    @FXML
    public void handleHelp() {
        apua();
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void handleTulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    
    /**
     * @param url - 
     * @param bundle - 
     */
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }
    
    /**
     * Alustaa joukkueen tiedot
     */
    
    
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


    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
}
