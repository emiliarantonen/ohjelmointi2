package harkkatyo;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author mineanupponen
 * @version 1.3.2022
 *
 */
public class Kilpailu implements ModalControllerInterface<String>{
    
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
            //new Joukkue("SM-kilpailut", 2017, "14-16v.", 17.65, 7)
            );
    
    /**
     * 
     */
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
    
    String kilpailu, sarja;
    int vuosi, sijoitus;
    double pisteet;
    
    /**
     * @param kilpailu -
     * @param vuosi -
     * @param sarja -
     * @param pisteet -
     * @param sijoitus -
     */
    public Kilpailu(String kilpailu, int vuosi, String sarja, double pisteet, int sijoitus) {
        this.kilpailu = kilpailu;
        this.sarja = sarja;
        this.vuosi = vuosi;
        this.sijoitus = sijoitus;
        this.pisteet = pisteet;
    }
    
    /**
     * @param kilpailu -
     */
    public void setKilpailu(String kilpailu) {
        this.kilpailu = kilpailu;
    }
    
    /**
     * 
     * @param sarja -
     */
    public void setSarja(String sarja) {
        this.sarja = sarja;
    }
    
    /**
     * 
     * @param vuosi -
     */
    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }
    
    public void setSijoitus(int sijoitus) {
        this.sijoitus = sijoitus;
    }
    
    public void setPisteet(double pisteet) {
        this.pisteet = pisteet;
    }
    
    public String getKilpailu(){
        return kilpailu;
    }
    
    public String getSarja(){
        return sarja;
    }
    
    public int getVuosi() {
        return vuosi;
    }
    
    public int getSijoitus() {
        return sijoitus;
    }
    
    public double getPisteet() {
        return pisteet;
    }
}
