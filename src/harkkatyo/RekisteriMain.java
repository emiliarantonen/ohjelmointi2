package harkkatyo;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author mineanupponen
 * @author emiliarantonen
 * @version 1.2.2022
 *
 *Pääohjelma rekisterin käynnistämiseksi
 */
public class RekisteriMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("Joukkueet.fxml"));
            final Pane root = (Pane)ldr.load();
            final JoukkueetController rekisteriCtrl = (JoukkueetController)ldr.getController();
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("rekisteri.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("rekisteri");
            
            Rekisteri rekisteri = new Rekisteri();
            rekisteriCtrl.setRekisteri(rekisteri);
           
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Käynnistetään käyttöliittymäs
     * @param args komentorivin parametrit
     */
    public static void main(String[] args) {
        launch(args);
    }
}