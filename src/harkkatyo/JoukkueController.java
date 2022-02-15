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

    @FXML
    private TableView<Joukkue> Joukkue;

    @FXML
    private TableColumn<Joukkue, String> Kilpailu;

    @FXML
    private TableColumn<Joukkue, Double> Pisteet;

    @FXML
    private TableColumn<Joukkue, String> Sarja;

    @FXML
    private TableColumn<Joukkue, Integer> Sijoitus;

    @FXML
    private TableColumn<Joukkue, Integer> Vuosi;
    
    /**
     * 
     * @param event
     */
    @FXML
    void handleMuokkaa(ActionEvent event) {
        ModalController.showModal(JoukkueController.class.getResource("LisaaKilpailu.fxml"), "Joukkue", null, "");
    }
    
    /**
     * 
     * @param event -
     */
    @FXML
    public void handleNayta(ActionEvent event) {
        alusta();
    }
    
    @FXML
    public void handleHelp(ActionEvent event) {
        apua();
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void handleTulosta(ActionEvent event) {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    ObservableList<Joukkue> list = FXCollections.observableArrayList(
            new Joukkue("SM-kilpailut", 2017, "14-16v.", 17.65, 7)
            );
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
    protected void alusta() {
        Kilpailu.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("kilpailu"));
        Vuosi.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("vuosi"));
        Sarja.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("sarja"));
        Pisteet.setCellValueFactory(new PropertyValueFactory<Joukkue, Double>("pisteet"));
        Sijoitus.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("sijoitus"));
        
        Joukkue.setItems(list);
        
    }
    
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
