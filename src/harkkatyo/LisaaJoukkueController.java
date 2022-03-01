package harkkatyo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class LisaaJoukkueController implements ModalControllerInterface<String>{

    @FXML
    void handleOK() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä");
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