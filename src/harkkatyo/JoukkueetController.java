package harkkatyo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class JoukkueetController implements ModalControllerInterface<String>{
    
    @FXML private ListChooser<Joukkue> chooserJoukkueet;

    @FXML
    private void handleLisaaJoukkue() {
        uusiJoukkue();
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
//========
    private Rekisteri rekisteri;
    
    private void uusiJoukkue() {
        Joukkue uusi = new Joukkue();
        uusi.rekisteroi();
        uusi.vastaaLumo();
        try {
            rekisteri.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }
        hae(uusi.getIdNro());
    }
    
    private void hae(int nro) {
        chooserJoukkueet.clear();
        int index=0;
        for(int i=0; i< rekisteri.getJoukkueita(); i++) {
            Joukkue joukkue = rekisteri.annaJoukkue(i);
            if (joukkue.getIdNro() == nro) index =i;
            chooserJoukkueet.add(""+joukkue.getIdNro(), joukkue);
        }
        
        
       chooserJoukkueet.setSelectedIndex(index);
    }

    

   
    
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
