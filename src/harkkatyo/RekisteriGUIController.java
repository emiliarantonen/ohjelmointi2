package harkkatyo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import fi.jyu.mit.fxgui.*;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import harkkatyo.Joukkue;

/**
 * @author mineanupponen
 * @authhor emsukara
 * @version 1.2.2022
 *
 */
public class RekisteriGUIController implements Initializable{

    
    @FXML private void handleUusiJoukkue() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
    @FXML private void handleHaeJoukkue() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
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
    
    ObservableList<Joukkue> list = FXCollections.observableArrayList(
            new Joukkue("SM-kilpailut", 2017, "14-16v.", 17.65, 7)
            );
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        Kilpailu.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("kilpailu"));
        Vuosi.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("vuosi"));
        Sarja.setCellValueFactory(new PropertyValueFactory<Joukkue, String>("sarja"));
        Pisteet.setCellValueFactory(new PropertyValueFactory<Joukkue, Double>("pisteet"));
        Sijoitus.setCellValueFactory(new PropertyValueFactory<Joukkue, Integer>("sijoitus"));
        
        Joukkue.setItems(list);
    }

}