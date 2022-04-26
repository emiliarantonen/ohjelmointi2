package harkkatyo;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author emiliarantonen
 * @version 14.2.2022
 *
 */
public class LisaaKilpailu implements ModalControllerInterface<Kilpailu>,Initializable {
    
    @FXML private GridPane gridKilpailu;
    @FXML private Label labelVirhe;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();  
    }
    
    @FXML private void handleOK() {
        if ( kilpailuKohdalla != null && kilpailuKohdalla.getNimi().trim().equals("") ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        kilpailuKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
 //============================================
    private Kilpailu kilpailuKohdalla;
    private static Kilpailu apukilpailu = new Kilpailu(); 
    private TextField[] edits;
    private int kentta = 0;
    
    protected void alusta() {
        edits = luoKentat(gridKilpailu);
        for (TextField edit : edits)
            if (edit!=null)
                edit.setOnKeyReleased(e -> kasitteleMuutosKilpailuun((TextField)(e.getSource())));
        
    }
    
    protected void kasitteleMuutosKilpailuun(TextField edit) {
        if (kilpailuKohdalla == null) return;
        int k = getFieldId(edit,apukilpailu.ekaKentta());
        String s = edit.getText();
        String virhe = null;
        virhe = kilpailuKohdalla.aseta(k,s); 
        if (virhe == null) {
            Dialogs.setToolTipText(edit,"");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit,virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }

    
    public static TextField[] luoKentat(GridPane gridKilpailu) {
        gridKilpailu.getChildren().clear();
        TextField[] edits = new TextField[apukilpailu.getKenttia()];
        
        for (int i=0, k = apukilpailu.ekaKentta(); k < apukilpailu.getKenttia(); k++, i++) {
            Label label = new Label(apukilpailu.getKysymys(k));
            gridKilpailu.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e"+k);
            gridKilpailu.add(edit, 1, i);
        }
        return edits;
    }
    
    /**
     * Palautetaan komponentin id:stä saatava luku
     * @param obj tutkittava komponentti
     * @param oletus mikä arvo jos id ei ole kunnollinen
     * @return komponentin id lukuna 
     */
    public static int getFieldId(Object obj, int oletus) {
        if ( !( obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        return Mjonot.erotaInt(node.getId().substring(1),oletus);
    }

    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    @Override
    public void setDefault(Kilpailu oletus) {
        kilpailuKohdalla = oletus;
        naytaKilpailu(edits, kilpailuKohdalla);
    }

    
    @Override
    public Kilpailu getResult() {
        return kilpailuKohdalla;
    }
    
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    /**
    * Näytetään jäsenen tiedot TextField komponentteihin
    * @param edits taulukko TextFieldeistä johon näytetään
    * @param kilpailu näytettävä jäsen
    */
   public static void naytaKilpailu(TextField[] edits, Kilpailu kilpailu) {
       if (kilpailu == null) return;
       for (int k = kilpailu.ekaKentta(); k < kilpailu.getKenttia(); k++) {
           edits[k].setText(kilpailu.anna(k));
       }
   }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Luodaan jäsenen kysymisdialogi ja palautetaan sama tietue muutettuna tai null
     * TODO: korjattava toimimaan
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataan näytetään oletuksena
     * @param kentta mikä kenttä saa fokuksen kun näytetään
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Kilpailu kysyKilpailu(Stage modalityStage, Kilpailu oletus, int kentta) {
        return ModalController.<Kilpailu, LisaaKilpailu>showModal(
                    LisaaKilpailu.class.getResource("LisaaKilpailu.fxml"),
                    "Rekisteri",
                    modalityStage, oletus,
                    ctrl -> ctrl.setKentta(kentta) 
                );
    }
   


}
