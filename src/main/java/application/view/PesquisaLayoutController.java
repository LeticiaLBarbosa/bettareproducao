package application.view;

import java.time.LocalDate;

import application.Main;
import application.model.Reproducao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private DatePicker ultimaAtualizacaoDatePicker;
	@FXML
	private TableView<Reproducao> tabelaReproducoes;
	@FXML
    private TableColumn<Reproducao, String> idColumn;
    @FXML
    private TableColumn<Reproducao, LocalDate> inicioColumn;
    @FXML
    private TableColumn<Reproducao, LocalDate> retiradaMachoColumn;
    @FXML
    private TableColumn<Reproducao, LocalDate> retiradaFemeaColumn;
    @FXML
    private TableColumn<Reproducao, LocalDate> ultimaAtualizacaoColumn;
	
	private Main main;
	
	private Stage stage;
	
	public PesquisaLayoutController(){
		
	}
	
	 @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty());
	        inicioColumn.setCellValueFactory(cellData -> cellData.getValue().getInicioProperty());
	        retiradaFemeaColumn.setCellValueFactory(cellData -> cellData.getValue().getRetirada_femeaProperty());
	        retiradaMachoColumn.setCellValueFactory(cellData -> cellData.getValue().getRetirada_machoProperty());
	        ultimaAtualizacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getUltimaAtualizacaoProperty());
	    }
	
	public void setMain(Main main) {
		this.main = main;
		tabelaReproducoes.setItems(main.getReproducaoData());
	}
	
	public void setPrevStage(Stage stage) {
		this.stage = stage;
		
	}
	
	public void pesquisar(){
		
	}
	
	public void cancelar(){
		
	}

}
