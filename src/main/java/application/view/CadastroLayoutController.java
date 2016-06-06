package application.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import application.Main;
import application.model.Reproducao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

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

	private Main main;
	private Reproducao reproducao; 

	private String fotoMacho;
	private String fotoFemea;
	private String fotoPaiMacho;
	private String fotoMaeMacho;
	private String fotoPaiFemea;
	private String fotoMaeFemea;
	private String ID = null;
	private String imagesDir = new String("src/main/resources/uploaded/");

	private HashMap<Integer,String> resultados;
	private Map<String, Integer> ultimaPosVazia;
	
	private int resultCount = 0;

	public CadastroLayoutController() {

//		machoDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				remove(fotoMacho.getId)
//			}
//		});
//		
		
		fotoMacho = null;
		fotoFemea = null;
		fotoPaiMacho = null;
		fotoMaeFemea = null;
		fotoPaiFemea = null;
		resultados =  new HashMap<Integer, String>();
		ultimaPosVazia = new HashMap<>();
		ultimaPosVazia.put("lin", 14);
		ultimaPosVazia.put("col", 0);

	
		
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
		reproducao.setResultados(resultados);
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
		String path = imagesDir+ID+"_"+file.getName();
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
		shiftAdicionarLink();
		resultCount++;
		criarViews(resultCount);
		shiftUltimaPosVazia();
		System.out.println(resultados.toString());
	}

	private void shiftUltimaPosVazia() {
		if(ultimaPosVazia.get("col") == 3){
			ultimaPosVazia.put("lin", ultimaPosVazia.get("lin") + 1);
			ultimaPosVazia.put("col", 0);
		}else{
			ultimaPosVazia.put("col", ultimaPosVazia.get("col") + 1);
		}
	}

	private void criarViews(Integer resultCount) {
		BorderPane borderPane = new BorderPane();
		StackPane pane = new StackPane();
		TextField linhagem = new TextField();
		ImageView imageView = new ImageView();
		Button editButton = new Button();
		Button deleteButton = new Button();
		
		linhagem.setId(resultCount+"");
		imageView.setId(resultCount+"");
		String resultInfo = linhagem.getText()+" ; ";
		resultados.put(resultCount,resultInfo);
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFotoResultado(event, imageView, linhagem.getText());
			}
		});
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deleteFotoResultado(event, imageView);
			}
		});
		
		linhagem.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String[] values = resultados.get(Integer.parseInt(linhagem.getId())).split(";");
				System.out.println(values.length);
				values[0] = newValue;
				resultados.put(Integer.parseInt(linhagem.getId()), values[0].trim()+" ; "+values[1].trim());
			}
			
		});
		
		Image noFotoImage = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
		Image editImage = new Image(getClass().getResourceAsStream("/edit_icon.png"));
		Image deleteImage = new Image(getClass().getResourceAsStream("/delete_icon.png"));
		
		imageView.setImage(noFotoImage);
		imageView.setFitHeight(100);
		imageView.setFitWidth(140);
		imageView.setPreserveRatio(true);
		editButton.setGraphic(new ImageView(editImage));
		deleteButton.setGraphic(new ImageView(deleteImage));
		deleteButton.setPadding(new Insets(2));
		editButton.setPadding(new Insets(2));
		linhagem.setPromptText("Linhagem");
		
		pane.getChildren().addAll(imageView, deleteButton, editButton);
		
		borderPane.setTop(linhagem);
		borderPane.setCenter(pane);
		
		StackPane.setAlignment(deleteButton,Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(editButton,Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(imageView,Pos.CENTER);
		StackPane.setMargin(deleteButton, new Insets(0,5,0,0));
		StackPane.setMargin(editButton, new Insets(0,24,0,0));
		StackPane.setMargin(imageView, new Insets(5,5,0,5));
		
		BorderPane.setMargin(linhagem, new Insets(5,5,0,5));
		
		gridPane.add(borderPane,ultimaPosVazia.get("col"), ultimaPosVazia.get("lin"));
	}

	private void addFotoResultado(ActionEvent event, ImageView imageView, String linhagem) {
		File file = abrirFileChooser();
		Integer resultCountInt = Integer.parseInt(imageView.getId());
		String imageName = ID+String.format("_result_%d", resultCountInt)+file.getName();
		String path = imagesDir+imageName;
		File newFile = new File(path);
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null); 
			imageView.setImage(image);
			String resultInfo = linhagem+" ; "+imageName;
			resultados.put(resultCountInt,resultInfo);
			ImageIO.write(bufferedImage,file.getName().substring(file.getName().length()-3), newFile);
		}catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void deleteFotoResultado(ActionEvent event, ImageView imageView) {
		Image image = new Image(getClass().getResourceAsStream("/default-no-image.jpg"));
		String[] values = resultados.get(Integer.parseInt(imageView.getId())).split(";");
		String imageName = values[1].trim();
		File file = null;
		
		imageView.setImage(image);
		file = new File(imagesDir+imageName);
		if(file.exists()){
			file.delete();
			values[1] = "";
			String valuesString = values[0].trim()+" ; "+values[1].trim();
			resultados.put(Integer.parseInt(imageView.getId()), valuesString);
		}
	}
	
	private void shiftAdicionarLink() {
		int linhaAtualAdicionarLink = GridPane.getRowIndex(adicionarResultadoHyperlink);
		if(ultimaPosVazia.get("lin") == 14){
			if(ultimaPosVazia.get("col") == 0){
				GridPane.setRowIndex(adicionarResultadoHyperlink, linhaAtualAdicionarLink + 1);
				GridPane.setRowIndex(cancelarButton, linhaAtualAdicionarLink + 2);
				GridPane.setRowIndex(salvarButton, linhaAtualAdicionarLink + 2);
			}
		}else{
			if(ultimaPosVazia.get("col") == 0){
				GridPane.setRowIndex(adicionarResultadoHyperlink, linhaAtualAdicionarLink + 1);
				GridPane.setRowIndex(cancelarButton, linhaAtualAdicionarLink + 2);
				GridPane.setRowIndex(salvarButton, linhaAtualAdicionarLink + 2);
			}
		}
	}

	public void setMain(Main main) {
		this.main = main;
		setRepID();		
	}

}
