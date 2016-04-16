package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PesquisaLayoutController {
	
	@FXML
	private TextField idTextField;
	@FXML
	private DatePicker inicioDatePicker;
	@FXML
	private DatePicker retiradaMachoDatePicker;
	@FXML
	private DatePicker retiradaFemeaDatePicker;
	@FXML
	private DatePicker ultimaDatePicker;
	
	private Main main;
	
	private Stage stage;
	
	public void setMain(Main main) {
		this.main = main;
		setPrevStage(this.main.getPrimaryStage());
	}
	
	public void setPrevStage(Stage stage) {
		this.stage = stage;
		
	}

}
