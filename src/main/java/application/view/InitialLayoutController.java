package application.view;

import java.io.IOException;

import application.Main;
import application.model.Reproducao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InitialLayoutController {
	@FXML
	private Button pesquisarButton;
	@FXML
	private Button cadastrarButton;
	@FXML
	private ImageView backgroundImageView;

	private Stage prevStage;

	private Main main;

	public InitialLayoutController() {
	}

	@FXML
	private void initialize() {
		Image image = new Image(getClass().getResourceAsStream("/background.jpg"));
		backgroundImageView.setImage(image);
	}
	
	public void abrirCadastroLayout() {
		main.showCadastroLayout();
	}
	
	public void abrirPesquisaLayout() {
		System.out.println(main.getClass().getName());
		main.showPesquisaLayout();
	}
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
		System.out.println("passou aqui");
		setPrevStage(main.getPrimaryStage());
	}
}
