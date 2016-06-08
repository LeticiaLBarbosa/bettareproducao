package application.view;

import java.util.HashMap;
import java.util.Map;

import application.Main;
import application.model.Reproducao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReproducaoOverviewController {
	
    @FXML
    private Label idLabel;
    @FXML
    private TextField linhagemPaiMachoTextField;
    @FXML
    private TextField linhagemMaeMachoTextField;
    @FXML
    private TextField linhagemMaeFemeaTextField;
    @FXML
    private TextField linhagemPaiFemeaTextField;
    @FXML
    private TextField linhagemMachoTextField;
    @FXML
    private TextField linhagemFemeaTextField;
    @FXML
    private TextField infoMachoTextField;
    @FXML
    private TextField infoFemeaTextField;
    @FXML
    private DatePicker inicioDatePicker;
    @FXML
    private DatePicker retiradaFemeaDatePicker;
    @FXML
    private DatePicker retiradaMachoDatePicker;
    
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
    private GridPane gridPane;
    
    @FXML
    private Button deletarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button imprimirButton;
    
    // Reference to the main application.
    private Main main;
    private Stage stage;
    private Reproducao reproducao;
    private boolean okClicked = false;
    private Map<String, Integer> ultimaPosVazia = new HashMap<>();

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
    	aditionalInfoTextArea.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> ob, String o, String n) {
                aditionalInfoTextArea.setPrefColumnCount(aditionalInfoTextArea.getText().length() +1);
            }
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;
    }

	public void setDialogStage(Stage dialogStage) {
		stage = dialogStage;
	}

	public void setReproducao(Reproducao reproducao) {
		this.reproducao = reproducao;
		
		idLabel.setText(reproducao.getID());
		linhagemPaiMachoTextField.setText(reproducao.getLinhagemPaiMacho());
		linhagemMaeMachoTextField.setText(reproducao.getLinhagemMaeMacho());
		linhagemPaiFemeaTextField.setText(reproducao.getLinhagemPaiFemea());
		linhagemMaeFemeaTextField.setText(reproducao.getLinhagemMaeFemea());
		linhagemMachoTextField.setText(reproducao.getLinhagemMacho());
		linhagemFemeaTextField.setText(reproducao.getLinhagemFemea());
		infoFemeaTextField.setText(reproducao.getInfoFemea());
		infoMachoTextField.setText(reproducao.getInfoMacho());
		inicioDatePicker.setValue(reproducao.getInicio());
		retiradaFemeaDatePicker.setValue(reproducao.getRetirada_femea());
		retiradaMachoDatePicker.setValue(reproducao.getRetirada_macho());
		aditionalInfoTextArea.setText(reproducao.getInformacoes());
		
		addFoto(paiMachoImageView, reproducao.getPai_macho());
		addFoto(maeMachoImageView, reproducao.getMae_macho());
		addFoto(paiFemeaImageView, reproducao.getPai_femea());
		addFoto(maeFemeaImageView, reproducao.getMae_femea());
		addFoto(machoImageView, reproducao.getMacho());
		addFoto(femeaImageView, reproducao.getFemea());
		
		showResultados(reproducao.getResultados());
	}

	private void addFoto(ImageView imageView, String foto) {
		Image noFotoImage = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
		if(foto != null && !foto.isEmpty()){
			System.out.println(foto);
			Image image = new Image(getClass().getResourceAsStream("/uploaded/"+foto));
			System.out.println(image);
			imageView.setImage(image);
		}else{
			imageView.setImage(noFotoImage);
		}
	}

	private void showResultados(Map<Integer, String> resultados) {
		ultimaPosVazia.put("lin", 14);
		ultimaPosVazia.put("col", 0);
		for (Integer key : resultados.keySet()) {
			shiftButtons();
			criarViews(resultados.get(key));
			shiftUltimaPosVazia();
		}
	}
	
	private void shiftButtons() {
		int linhaAtualDeletarButton = GridPane.getRowIndex(deletarButton);
		if(ultimaPosVazia.get("lin") == 14){
			if(ultimaPosVazia.get("col") == 0){
				GridPane.setRowIndex(deletarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(cancelarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(editarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(imprimirButton, linhaAtualDeletarButton + 1);
			}
		}else{
			if(ultimaPosVazia.get("col") == 0){
				GridPane.setRowIndex(deletarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(cancelarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(editarButton, linhaAtualDeletarButton + 1);
				GridPane.setRowIndex(imprimirButton, linhaAtualDeletarButton + 1);
			}
		}
	}

	private void criarViews(String resultadoInfo){
		BorderPane borderPane = new BorderPane();
		TextField linhagem = new TextField();
		ImageView imageView = new ImageView();
		
		String[] resultInfoList = resultadoInfo.split(";");
		Image noFotoImage = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
	
		if(resultInfoList[1] != null && !resultInfoList[1].trim().isEmpty()){
			System.out.println(resultInfoList[1]);
			imageView.setImage(new Image(getClass().getResourceAsStream("/uploaded/"+resultInfoList[1].trim())));
		}else{
			imageView.setImage(noFotoImage);
		}
		imageView.setFitHeight(100);
		imageView.setFitWidth(140);
		imageView.setPreserveRatio(true);
		
		if(resultInfoList[0] != null && !resultInfoList[0].isEmpty()){
			System.out.println(resultInfoList[0]);
			linhagem.setText(resultInfoList[0]);
		}else{
			linhagem.setPromptText("Linhagem");
		}
		
		linhagem.setDisable(true);
		linhagem.setEditable(false);
		linhagem.setOpacity(0.73);
		
		borderPane.setTop(linhagem);
		borderPane.setCenter(imageView);
		
		BorderPane.setMargin(imageView, new Insets(5,5,0,5));
		BorderPane.setMargin(linhagem, new Insets(5,5,0,5));
		
		gridPane.add(borderPane,ultimaPosVazia.get("col"), ultimaPosVazia.get("lin"));
	}
	
	private void shiftUltimaPosVazia() {
		if(ultimaPosVazia.get("col") == 3){
			ultimaPosVazia.put("lin", ultimaPosVazia.get("lin") + 1);
			ultimaPosVazia.put("col", 0);
		}else{
			ultimaPosVazia.put("col", ultimaPosVazia.get("col") + 1);
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
     * Called when the user clicks ok.
     */
    @FXML
    private void imprimir() {
//        person.setFirstName(firstNameField.getText());
//        person.setLastName(lastNameField.getText());
//        person.setStreet(streetField.getText());
//        person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
//        person.setCity(cityField.getText());
//        person.setBirthday(DateUtil.parse(birthdayField.getText()));

        okClicked = true;
        stage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void cancelar() {
        stage.close();
    }
    
    @FXML
    private void editar() {
        
    }
    
    @FXML
    private void deletar() {
        stage.close();
    }
}
