package harkkatyo;

import static harkkatyo.KilpailuTietue.getFieldId;
import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class JoukkueetController implements Initializable{
    
    @FXML private ListChooser<Joukkue> chooserJoukkueet;
    @FXML private TextField hakuehto;
    @FXML private ScrollPane panelJoukkue;
    @FXML StringGrid<Kilpailu> tableKilpailut;
    @FXML private GridPane gridTietue;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    @FXML private TextField textJoukkueenNimi;
    @FXML private TextArea tulostusAlue;
    
    //private String joukkueenNimi = "Lumo";

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
        //Dialogs.showMessageDialog("Ei osaa vielä poistaa");
        poistaJoukkue();
    }
    
    @FXML
    void handlePoistaKilpailu() {
        //Dialogs.showMessageDialog("Ei osaa vielä poistaa");
        poistaKilpailu();
    }
    
    @FXML
    void handleHakuehto(KeyEvent event) {
        //
        //Dialogs.showMessageDialog("lolololo");
        hae(0);
    }
    
    @FXML
    void handleHakuehto() {
        //Dialogs.showMessageDialog("Ei osaa vielä poistaa");
//        if ( joukkueKohdalla != null )
//            hae(joukkueKohdalla.getIdNro());
        //hae(0);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    /**
     * 
     */
    @FXML
    public void handleHelp() {
        apua();
    }
    
    
    /**
     * 
     */
    @FXML
    public void handleAvaa() {
        avaa();
    }
    
    /**
     * 
     */
    @FXML
    public void handleTallenna() {
        tallenna();
    }
    @FXML 
    void handleSposti() {
        asiakkaaseenYhteys();
    }
    /**
     * 
     */
    @FXML
    public void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    /**
     * 
     */
    @FXML
    public void handleTulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea());
        //Dialogs.showMessageDialog("Tulostetaan, mutta ei osata vielä");

    }
    
    /** 
     * @throws SailoException -
     * 
     */
    @FXML
    public void handleLisaaKilpailu() throws SailoException {
        //ModalController.showModal(JoukkueetController.class.getResource("LisaaKilpailu.fxml"), "Lisää kilpailu", null, "");
        lisaaKilpailu();
    }
    
    @FXML private void handleMuokkaaKilpailua() {
        ModalController.showModal(JoukkueetController.class.getResource("KilpailuTietue.fxml"), "Kilpailut", null, "");
    }
   
    
    

   //==================
    
    private Rekisteri rekisteri;
    private Joukkue joukkueKohdalla;
    public Kilpailu kilpailuKohdalla;
    private TextArea areaJoukkue = new TextArea();
    private TextField edits[]; 
    private static Kilpailu apukilpailu = new Kilpailu();
    private static Joukkue apujoukkue = new Joukkue();
    int kentta=0;
    
    private String joukkueenNimi = "Lumo";
    
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

    
    
    
    
    /**
     * @param rekisteri -
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaJoukkue();
    }
    
    private void uusiJoukkue() {
        Joukkue uusi = new Joukkue();
        uusi.rekisteroi();
        String s = textJoukkueenNimi.getText();
        uusi.setNimi(s);
        textJoukkueenNimi.clear();
        try {
            rekisteri.lisaa(uusi);
        } catch (SailoException e) {
            try {
                Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        hae(uusi.getIdNro());

    }
    
    private void poistaJoukkue() {
        Joukkue joukkue = joukkueKohdalla;
        if (joukkue==null) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko joukkue: " + joukkue.getNimi(), "Kyllä", "Ei") )
            return;
        rekisteri.poista(joukkue);
        int index = chooserJoukkueet.getSelectedIndex();
        hae(0);
        chooserJoukkueet.setSelectedIndex(index);
    }
    
    /**git
     * @throws SailoException -
     * 
     */
    public void lisaaKilpailu() throws SailoException { 
        if ( joukkueKohdalla == null ) return;  
        try {
            Kilpailu uusi = new Kilpailu(joukkueKohdalla.getIdNro());
            uusi = KilpailuTietue.kysyTietue(null, uusi, 1);      
            if ( uusi == null ) return;
            uusi.rekisteroi();
            rekisteri.lisaa(uusi);
            naytaKilpailut(joukkueKohdalla);
            //tableKilpailut.selectRow(1000);
            //hae(uusi.getTunnusNro());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        }          
    }
    
     private void poistaKilpailu() {
         int rivi = tableKilpailut.getRowNr();
         if ( rivi < 0 ) return;
         Kilpailu kilpailu = tableKilpailut.getObject();
         if ( kilpailu == null ) return;
         rekisteri.poistaKilpailu(kilpailu);
         naytaKilpailut(joukkueKohdalla);
         int kilpailuja = tableKilpailut.getItems().size(); 
         if ( rivi >= kilpailuja ) rivi = kilpailuja -1;
         tableKilpailut.getFocusModel().focus(rivi);
         tableKilpailut.getSelectionModel().select(rivi);
    }
     
    protected void hae(int nro) {
        int jnro=nro;
        if ( jnro <=0) {
            Joukkue kohdalla = joukkueKohdalla;
            if (kohdalla != null) jnro = kohdalla.getIdNro();
        }
        int k = cbKentat.getSelectionModel().getSelectedIndex() + apujoukkue.ekaKentta();
        String ehto = hakuehto.getText(); 
        if (ehto.indexOf('*')<0)ehto = "*"+ ehto + "*";
        
        chooserJoukkueet.clear();

        int index = 0;
        Collection<Joukkue> joukkueet;
        try {
            joukkueet = rekisteri.etsi(ehto, k);
            int i = 0;
            for (Joukkue joukkue:joukkueet) {
                if (joukkue.getIdNro() == nro) index = i;
                chooserJoukkueet.add(joukkue.getNimi(), joukkue);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Jäsenen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserJoukkueet.setSelectedIndex(index);
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

    
    private void alusta() {       
        chooserJoukkueet.clear();
        chooserJoukkueet.addSelectionListener(e -> naytaJoukkue());  
        
        cbKentat.clear(); 
        for (int k = apujoukkue.ekaKentta(); k < apujoukkue.getKenttia(); k++) 
            cbKentat.add(apujoukkue.getKysymys(k), null); 
        cbKentat.getSelectionModel().select(0); 

        
//        edits = KilpailuTietue.luoKentat(gridTietue, new Kilpailu());  
//        for (TextField edit: edits)  
//            if ( edit != null ) {  
//                edit.setEditable(false);  
//                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(getFieldId(e.getSource(),0)); });  
//                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));
//                edit.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaa(kentta);}); 
//            }
        // alustetaan harrastustaulukon otsikot 
        int eka = apukilpailu.ekaKentta(); 
        int lkm = apukilpailu.getKenttia(); 
        String[] headings = new String[lkm-eka]; 
        for (int i=0, k=eka; k<lkm; i++, k++) headings[i] = apukilpailu.getKysymys(k); 
        tableKilpailut.initTable(headings); 
        tableKilpailut.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        tableKilpailut.setEditable(false); 
        tableKilpailut.setPlaceholder(new Label("Ei vielä kilpailuja")); 
         
        // Tämä on vielä huono, ei automaattisesti muutu jos kenttiä muutetaan. 
        tableKilpailut.setColumnSortOrderNumber(1); 
        tableKilpailut.setColumnSortOrderNumber(2); 
        tableKilpailut.setColumnWidth(1, 60); 
        
        tableKilpailut.setOnMouseClicked( e -> { if ( e.getClickCount() > 1 ) muokkaaKilpailua(); } );
        tableKilpailut.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaaKilpailua();});

 
    }
    
    private void muokkaa(int k) { 
        if ( kilpailuKohdalla == null ) return; 
        try { 
            Kilpailu kilpailu; 
            kilpailu = KilpailuTietue.kysyTietue(null, kilpailuKohdalla.clone(), k);   
            if ( kilpailu == null ) return; 
            rekisteri.korvaaTaiLisaa(kilpailu); 
            hae(kilpailu.getTunnusNro()); 
        } catch (CloneNotSupportedException e) { 
            // 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        } 
    }
    
    private void muokkaaKilpailua() {
        int r = tableKilpailut.getRowNr();
        if ( r < 0 ) return; // klikattu ehkä otsikkoriviä
        Kilpailu kil = tableKilpailut.getObject();
        if ( kil == null ) return;
        int k = tableKilpailut.getColumnNr()+kil.ekaKentta();
        try {
            kil = KilpailuTietue.kysyTietue(null, kil.clone(), k);
            if ( kil == null ) return;
            rekisteri.korvaaTaiLisaa(kil); 
            naytaKilpailut(joukkueKohdalla); 
            tableKilpailut.selectRow(r);  // järjestetään sama rivi takaisin valituksi
        } catch (CloneNotSupportedException  e) { /* clone on tehty */  
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia lisäämisessä: " + e.getMessage());
        }
    }
    
    private void naytaJoukkue() {

        joukkueKohdalla = chooserJoukkueet.getSelectedObject();

        if (joukkueKohdalla == null) return;

        areaJoukkue.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaJoukkue)) {
            tulosta(os,joukkueKohdalla);  
        }
        naytaKilpailut(joukkueKohdalla);
    }
    
    /**
     * näytetään joukkueen kilpailut
     * @param joukkue jolle etsitään kilpailuja
     */
    private void naytaKilpailut(Joukkue joukkue) {
        tableKilpailut.clear();
        if (joukkue==null) return;
       
 
        List<Kilpailu> kilpailut = rekisteri.annaKilpailut(joukkue);
        if (kilpailut.size()==0)return;
        for (Kilpailu kil : kilpailut)
            naytaKilpailu(kil);
    }
    
    private void naytaKilpailu(Kilpailu kil) {
        int kenttia = kil.getKenttia();
        String[] rivi = new String[kenttia-kil.ekaKentta()]; 
        for (int i=0, k=kil.ekaKentta(); k < kenttia; i++, k++) 
            rivi[i] = kil.anna(k); 
        tableKilpailut.add(kil,rivi);
        //KilpailuTietue.naytaTietue(edits, joukkueKohdalla);
        //naytaKilpailut(joukkueKohdalla);
    }
    
    /**
     * @param os -
     * @param joukkue --
     */
    public void tulosta(PrintStream os, final Joukkue joukkue)  {
        os.println("----------------------------------------------");
        joukkue.tulosta(os);
        os.println("----------------------------------------------");
        try {
            List<Kilpailu> kilpailut = rekisteri.annaKilpailut(joukkue);   
            for (Kilpailu kil:kilpailut)
                kil.tulosta(os);  
        } catch (Exception ex) {
            Dialogs.showMessageDialog("Kilpailun hakemisessa ongelmia! " + ex.getMessage());
        }  
    }
    
    /**
     * Tulostaa listassa olevat jäsenet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan joukkueiden kilpailut");
            for (Joukkue joukkue: chooserJoukkueet.getObjects()) { 
                tulosta(os, joukkue);
                os.println("\n\n");
            }
        }
    }

    
    /**
     * @return -
     */
    public boolean avaa() {
        String uusinimi = RekisteriGUIController.kysyNimi(null, joukkueenNimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    /**
     * @param nimi -
     * @return null jos onnistuu muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        joukkueenNimi = "Lumo/joukkueet";
        setTitle("Joukkue - " + joukkueenNimi);
        
        try {
            rekisteri.lueTiedostosta(joukkueenNimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;

        }
        
    }
   
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    /**
     * @return -
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            rekisteri.tallenna();
            return null;
        }catch (SailoException e) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + e.getMessage());
            return e.getMessage();
        }
    }
    
    /**
     * Käsittelee yhteydenoton asiakkaaseen
     * @param asiakas johon halutaan ottaa yhteyttä
     */
    private void asiakkaaseenYhteys() {        
        Alert alert = new Alert(AlertType.INFORMATION, "emilia.rantonen@gmail.com", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
    
    
}
