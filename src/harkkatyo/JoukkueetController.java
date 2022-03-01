package harkkatyo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class JoukkueetController implements ModalControllerInterface<String>{

    @FXML
    void handleLisaaJoukkue() {
        ModalController.showModal(RekisteriGUIController.class.getResource("LisaaJoukkue.fxml"), "Joukkueet", null, "");
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
