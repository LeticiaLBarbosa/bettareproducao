package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ReproducaoOverviewController {
	
    @FXML
    private Label idLabel;
    @FXML
    private Label linhagemMaeMachoLabel;
    @FXML
    private Label linhagemPaiMachoLabel;
    @FXML
    private Label linhagemMaeFemeaLabel;
    @FXML
    private Label linhagemPaiFemeaLabel;
    @FXML
    private Label linhagemMachoLabel;
    @FXML
    private Label linhagemFemeaLabel;
    @FXML
    private Label infoMachoLabel;
    @FXML
    private Label infoFemeaLabel;
    @FXML
    private Label inicioLabel;
    @FXML
    private Label retiradaFemeaLabel;
    @FXML
    private Label retiradaMachoLabel;
    
    @FXML
    private TextArea aditionalInfoTextArea;
    
    @FXML
    private ImageView paiMachoImageView;
    @FXML
    private ImageView maeMachoImageView;
    @FXML
    private ImageView paiFemeaImageView;
    @FXML
    private ImageView maeFemeaImageView;
    @FXML
    private ImageView machoImageView;
    @FXML
    private ImageView femeaImageView;
    
    @FXML
    private GridPane resultadosGridPane;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ReproducaoOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;
    }
}
