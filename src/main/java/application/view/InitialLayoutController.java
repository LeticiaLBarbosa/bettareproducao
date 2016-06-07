package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InitialLayoutController {
	@FXML
	private Button pesquisarButton;
	@FXML
	private Button cadastrarButton;
	@FXML
	private ImageView backgroundImageView;

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

	public void setMain(Main main) {
		this.main = main;
	}
}
