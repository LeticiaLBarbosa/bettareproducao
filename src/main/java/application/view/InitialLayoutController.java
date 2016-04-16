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
		try {
			// Load cadastro layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CadastroLayout.fxml"));
			AnchorPane cadastroLayout = (AnchorPane) loader.load();

			// Set cadastro layout into the center of root layout.
			Scene scene = new Scene(cadastroLayout);
			prevStage.setScene(scene);
			prevStage.setTitle("Cadastro");
			prevStage.show();

			// Give the controller access to the main app.
			CadastroLayoutController controller = loader.getController();
			Reproducao tmpreproducao = new Reproducao();
			controller.setReproducao(tmpreproducao);
			controller.setMain(main);
			controller.setPrevStage(prevStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void abrirPesquisaLayout() {
		try {
			// Load pesquisa layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PesquisaLayout.fxml"));
			AnchorPane pesquisaLayout = (AnchorPane) loader.load();

			// Set pesquisa layout into the center of root layout.
			Scene scene = new Scene(pesquisaLayout);
			prevStage.setScene(scene);
			prevStage.setTitle("Pesquisa");
			prevStage.show();

			// Give the controller access to the main app.
			PesquisaLayoutController controller = loader.getController();
			controller.setMain(main);
			controller.setPrevStage(prevStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
		setPrevStage(this.main.getPrimaryStage());
	}
}
