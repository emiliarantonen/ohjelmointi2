package harkkatyo;



import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author emiliarantonen
 * @version 14.2.2022
 *Luokka uuden tietojen muokkaamisen käyttöliittymän hoitamiseksi
 */
public class LisaaKilpailuController implements ModalControllerInterface<String>{
    
    /**
     * 
     */
    @FXML
    void handleTallenna() {
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
