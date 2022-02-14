package harkkatyo;

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
    

    @FXML
    void handleMuokkaa(ActionEvent event) {
        ModalController.showModal(JoukkueController.class.getResource("LisaaKilpailu.fxml"), "Joukkue", null, "");
    }

    @FXML
    void handleNayta(ActionEvent event) {
        alusta();
    }

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
    
    protected void alusta() {
        Kilpailu.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("kilpailu"));
        Vuosi.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("vuosi"));
        Sarja.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("sarja"));
        Pisteet.setCellValueFactory(new PropertyValueFactory<Joukkue, Double>("pisteet"));
        Sijoitus.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("sijoitus"));
        
        Joukkue.setItems(list);
        
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
