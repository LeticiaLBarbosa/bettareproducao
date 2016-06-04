package application.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import application.Main;
import application.model.Reproducao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CadastroLayoutController {
	@FXML
	private Label idReproducaoLabel;

	@FXML
	private TextField linhagemPaiMachoTextField;
	@FXML
	private TextField linhagemMaeMachoTextField;
	@FXML
	private TextField linhagemPaiFemeaTextField;
	@FXML
	private TextField linhagemMaeFemeaTextField;
	@FXML
	private TextField linhagemMachoTextField;
	@FXML
	private TextField linhagemFemeaTextField;
	@FXML
	private TextField infoMachoTextField;
	@FXML
	private TextField infoFemeaTextField;

	@FXML
	private TextArea informacoesTextArea;

	@FXML
	private Button paiMachoButton;
	@FXML
	private Button maeMachoButton;
	@FXML
	private Button paiFemeaButton;
	@FXML
	private Button maeFemeaButton;
	@FXML
	private Button machoButton;
	@FXML
	private Button femeaButton;
	@FXML
	private Button paiMachoDeleteButton;
	@FXML
	private Button maeMachoDeleteButton;
	@FXML
	private Button paiFemeaDeleteButton;
	@FXML
	private Button maeFemeaDeleteButton;
	@FXML
	private Button machoDeleteButton;
	@FXML
	private Button femeaDeleteButton;
	@FXML
	private Button salvarButton;
	@FXML
	private Button cancelarButton;

	@FXML
	private DatePicker inicioDatePicker;
	@FXML
	private DatePicker retiradaFemeaDatePicker;
	@FXML
	private DatePicker retiradaMachoDatePicker;

	@FXML
	private GridPane gridPane;

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
	private Hyperlink adicionarResultadoHyperlink;

	private Stage prevStage;
	private Main main;
	private Reproducao reproducao; 

	private String fotoMacho;
	private String fotoFemea;
	private String fotoPaiMacho;
	private String fotoMaeMacho;
	private String fotoPaiFemea;
	private String fotoMaeFemea;
	private String ID = null;

	private Map<String, String> resultados;
	
	private boolean saveClicked = false;

	public CadastroLayoutController() {

		fotoMacho = null;
		fotoFemea = null;
		fotoPaiMacho = null;
		fotoMaeFemea = null;
		fotoPaiFemea = null;
		resultados =  new HashMap<String, String>();

	}

	@FXML
	private void initialize() {
		initEditButtons();
		initDeleteButtons();
		initFotos();
	}
	
	public void setReproducao(Reproducao reproducao){
		this.reproducao = reproducao;
	}
	
	private void setRepID(){
		int numeroFichas = main.getReproducaoData().size();
		if (numeroFichas < 1) {
			ID = "R0001";
		}else{
			Reproducao dadoAnterior = main.getReproducaoData().get(numeroFichas-1);
			int idAtual = Integer.parseInt(dadoAnterior.getID().substring(1)) + 1;
			if (idAtual > 9){
				ID = String.format("R00%d",idAtual);
			}else if (idAtual > 99) {
				ID = String.format("R0%d",idAtual);
			}else if (idAtual > 999) {
				ID = String.format("R%d",idAtual);
			}else if (idAtual > 9999) {
				ID = String.format("R%d",idAtual);
			}else{
				ID = String.format("R000%d",idAtual);
			}
		}
		idReproducaoLabel.setText(ID);
	}

	private void initEditButtons() {
		Image editImage = new Image(getClass().getResourceAsStream("/edit_icon.png"));
		paiMachoButton.setGraphic(new ImageView(editImage));
		maeMachoButton.setGraphic(new ImageView(editImage));
		paiFemeaButton.setGraphic(new ImageView(editImage));
		maeFemeaButton.setGraphic(new ImageView(editImage));
		machoButton.setGraphic(new ImageView(editImage));
		femeaButton.setGraphic(new ImageView(editImage));
	}

	private void initDeleteButtons() {
		Image deleteImage = new Image(getClass().getResourceAsStream("/delete_icon.png"));
		paiMachoDeleteButton.setGraphic(new ImageView(deleteImage));
		maeMachoDeleteButton.setGraphic(new ImageView(deleteImage));
		paiFemeaDeleteButton.setGraphic(new ImageView(deleteImage));
		maeFemeaDeleteButton.setGraphic(new ImageView(deleteImage));
		machoDeleteButton.setGraphic(new ImageView(deleteImage));
		femeaDeleteButton.setGraphic(new ImageView(deleteImage));
	}

	private void initFotos() {
		Image noFotoImage = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
		paiMachoImageView.setImage(noFotoImage);
		maeMachoImageView.setImage(noFotoImage);
		paiFemeaImageView.setImage(noFotoImage);
		maeFemeaImageView.setImage(noFotoImage);
		machoImageView.setImage(noFotoImage);
		femeaImageView.setImage(noFotoImage);
	}

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void cancelar() {
		main.showInitialLayout();
	}

	public void salvar() {
		reproducao.setID(idReproducaoLabel.getText());
		reproducao.setFemea(fotoFemea);
		reproducao.setInfoFemea(infoFemeaTextField.getText());
		reproducao.setInfoMacho(infoMachoTextField.getText());
		reproducao.setInformacoes(informacoesTextArea.getText());
		reproducao.setInicio(inicioDatePicker.getValue());
		reproducao.setLinhagemFemea(linhagemFemeaTextField.getText());
		reproducao.setLinhagemMacho(linhagemMachoTextField.getText());
		reproducao.setLinhagemMaeFemea(linhagemMaeFemeaTextField.getText());
		reproducao.setLinhagemMaeMacho(linhagemMaeMachoTextField.getText());
		reproducao.setLinhagemPaiFemea(linhagemPaiFemeaTextField.getText());
		reproducao.setLinhagemPaiMacho(linhagemPaiMachoTextField.getText());
		reproducao.setMacho(fotoMacho);
		reproducao.setMae_femea(fotoMaeFemea);
		reproducao.setMae_macho(fotoMaeMacho);
		reproducao.setPai_femea(fotoPaiFemea);
		reproducao.setPai_macho(fotoPaiMacho);
		reproducao.setRetirada_femea(retiradaFemeaDatePicker.getValue());
		reproducao.setRetirada_macho(retiradaMachoDatePicker.getValue());
		ObservableMap<String, String> resultadosObservable = FXCollections.observableMap(resultados);
		reproducao.setResultados(resultadosObservable);
		Date input = new Date();
		LocalDate diaAtual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		reproducao.setUltimaAtualizacao(diaAtual);
		
		handleSave();
		main.showPesquisaLayout();
	}
	
	private void handleSave(){
		File reproducaoFile = main.getReproducaoFilePath();
		main.getReproducaoData().add(reproducao);
        if (reproducaoFile != null) {
        	main.saveReproducaoDataToFile(reproducaoFile);
            System.out.println("ja tinha e salvou em cima");
        } else {
        	reproducaoFile = new File("src/main/resources/database.xml");
    		main.saveReproducaoDataToFile(reproducaoFile);
    		System.out.println("criou e salvou");
        }
	}

	public void addFoto(ActionEvent event) {
		Button botaoClicado = (Button) event.getSource();
		String idBotaoClicado = botaoClicado.getId();
		
		File file = abrirFileChooser();
		String path = new String("src/main/resources/uploaded/"+ID+"_"+file.getName());
		File newFile = new File(path);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			switch (idBotaoClicado) {
			case "paiMachoButton":
				paiMachoImageView.setImage(image);
				fotoPaiMacho = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			case "maeMachoButton":
				maeMachoImageView.setImage(image);
				fotoMaeMacho = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			case "paiFemeaButton":
				paiFemeaImageView.setImage(image);
				fotoPaiFemea = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			case "maeFemeaButton":
				maeFemeaImageView.setImage(image);
				fotoMaeFemea = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			case "machoButton":
				machoImageView.setImage(image);
				fotoMacho = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			case "femeaButton":
				femeaImageView.setImage(image);
				fotoFemea = path;
				ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
				break;
			}
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void deletarFoto(ActionEvent event) {
		Button botaoClicado = (Button) event.getSource();
		String idBotaoClicado = botaoClicado.getId();

		Image image = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
		File file = null;
		switch (idBotaoClicado) {
		case "paiMachoDeleteButton":
			paiMachoImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemPaiMacho());
			if(file.exists()){
				file.delete();
				fotoPaiMacho = null;
			}
			break;
		case "maeMachoDeleteButton":
			maeMachoImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemMaeMacho());
			if(file.exists()){
				file.delete();
				fotoMaeMacho = null;
			}
			break;
		case "paiFemeaDeleteButton":
			paiFemeaImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemPaiFemea());
			if(file.exists()){
				file.delete();
				fotoPaiFemea = null;
			}
			break;
		case "maeFemeaDeleteButton":
			maeFemeaImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemMaeFemea());
			if(file.exists()){
				file.delete();
				fotoMaeFemea = null;
			}
			break;
		case "machoDeleteButton":
			machoImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemMacho());
			if(file.exists()){
				file.delete();
				fotoMacho = null;
			}
			break;
		case "femeaDeleteButton":
			femeaImageView.setImage(image);
			file = new File("src/main/resources/uploaded/"+ID+"_"+reproducao.getLinhagemFemea());
			if(file.exists()){
				file.delete();
				fotoFemea = null;
			}
			break;
		}
	}

	public File abrirFileChooser() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		return file;
	}

	public void addResultado() {
		
	}

	public void setMain(Main main) {
		this.main = main;
		setRepID();		
	}

}
