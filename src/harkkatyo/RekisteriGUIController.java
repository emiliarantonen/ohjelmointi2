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
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 * @author mineanupponen
 * @authhor emiliarantonen
 * @version 1.2.2022
 *
 *Luokka käyttöliittymän tapahtumien hoitamiseksi
 */
public class RekisteriGUIController implements Initializable{

    /**
     * Uuden joukkueen lisääminen
     */
    @FXML private void handleUusiJoukkue() {
        ModalController.showModal(RekisteriGUIController.class.getResource("LisaaJoukkue.fxml"), "Lisää joukkue", null, "");
    }
    
    /**
     * Käsitellään joukkueen hakeminen
     */
    @FXML private void handleHaeJoukkue() {
        //Dialogs.showMessageDialog("Ei osata vielä lisätä");
        ModalController.showModal(RekisteriGUIController.class.getResource("Joukkue.fxml"), "Joukkue", null, "");
    }
    
    @FXML private void handleNaytaJoukkueet() {
        //Dialogs.showMessageDialog("Ei osata vielä lisätä");
        ModalController.showModal(RekisteriGUIController.class.getResource("Joukkueet.fxml"), "Joukkueet", null, "");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    protected void alusta() {
        //
        
    }

}