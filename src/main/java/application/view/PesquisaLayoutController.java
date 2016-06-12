package application.view;

import java.time.LocalDate;

import application.Main;
import application.model.Reproducao;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    
    @FXML
    private Button pesquisarButton;
    @FXML
    private Button cancelarButton;
	
	private Main main;
	
	private FilteredList<Reproducao> filteredData;
	
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
		
		tabelaReproducoes.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> mostrarReproducao(newValue));
	}
	
	public void setMain(Main main) {
		this.main = main;
		tabelaReproducoes.setItems(main.getReproducaoData());
		filteredData = new FilteredList<Reproducao>(main.getReproducaoData(), p -> true);
	}
	
	public void pesquisar(){
		clearValue(inicioDatePicker);
		clearValue(retiradaFemeaDatePicker);
		clearValue(retiradaMachoDatePicker);
		clearValue(ultimaAtualizacaoDatePicker);
		filteredData.setPredicate(reproducao -> {
			// If filter text is empty, display all persons.
			if(!(idTextField == null || idTextField.getText().isEmpty()) &&
            	!reproducao.getID().contains(idTextField.getText())){
            	return false;
            }
			if((inicioDatePicker.getValue() != null) && (reproducao.getInicio() != null)){
				if(!inicioDatePicker.getValue().toString().equals(reproducao.getInicio().toString())){
					return false;
				}
			}
			if((retiradaFemeaDatePicker.getValue() != null) && (reproducao.getRetirada_femea() != null)){
				if(!retiradaFemeaDatePicker.getValue().toString().equals(reproducao.getRetirada_femea().toString())){
					return false;
				}
			}
			if((retiradaMachoDatePicker.getValue() != null) && (reproducao.getRetirada_macho() != null)){
				if(!retiradaMachoDatePicker.getValue().toString().equals(reproducao.getRetirada_macho().toString())){
					return false;
				}
			}
			if((ultimaAtualizacaoDatePicker.getValue() != null) && (reproducao.getUltimaAtualizacao() != null)){
				if(!ultimaAtualizacaoDatePicker.getValue().toString().equals(reproducao.getUltimaAtualizacao().toString())){
					return false;
				}
			}
            return true;
        });
		
		SortedList<Reproducao> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabelaReproducoes.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabelaReproducoes.setItems(sortedData);
        filteredData = new FilteredList<Reproducao>(main.getReproducaoData(), p -> true);
	}
	
	private void clearValue(DatePicker datePicker) {
		if(datePicker.getEditor().getText().trim().equals("")){
			datePicker.setValue(null);
		}
	}

	public void cancelar(){
		main.showInitialLayout();
	}
	
	private void mostrarReproducao(Reproducao reproducao) {
		main.showReproducaoOverviewLayout(reproducao);	
	}

}
