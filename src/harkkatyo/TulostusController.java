package harkkatyo;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

public class TulostusController implements ModalControllerInterface<String> {
    @FXML private TextArea tulostusAlue;
    
    @FXML private void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }

    @FXML private void handleTulosta() {
        // Dialogs.showMessageDialog("Ei osata vielä tulostaa");
         PrinterJob job = PrinterJob.createPrinterJob();
         if ( job != null && job.showPrintDialog(null) ) {
             WebEngine webEngine = new WebEngine();
             webEngine.loadContent("<pre>" + tulostusAlue.getText() + "</pre>");
             webEngine.print(job);
             job.endJob();
         }
     }
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     * @return kontrolleri, jolta voidaan pyytää lisää tietoa
     */

    public static TulostusController tulosta(String tulostus) {
        TulostusController tulostusCtrl = 
          ModalController.showModeless(TulostusController.class.getResource("Tuloste.fxml"),
                                       "Tulostus", tulostus);
        return tulostusCtrl;
    }
    
    /**
     * @return alue johon tulostetaan
     */

    public TextArea getTextArea() {
        return tulostusAlue;
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
        // if ( oletus == null ) return;
        tulostusAlue.setText(oletus);
    }

}
