package application;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.model.Reproducao;
import application.model.ReproducaoListWrapper;
import application.view.CadastroLayoutController;
import application.view.InitialLayoutController;
import application.view.PesquisaLayoutController;
import application.view.ReproducaoOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private Stage primaryStage;
	private ObservableList<Reproducao> reproData = FXCollections.observableArrayList();
	private String filePathDir = "src/main/resources/";

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Reproducao> getReproducaoData() {
		return reproData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		showInitialLayout();
	}

	public void showInitialLayout() {
		File file = getReproducaoFilePath();
        if (file != null && file.exists()) {
            loadReproducaoDataFromFile(file);
        }else{
        	file = new File(filePathDir+"database.xml");
        	setReproducaoFilePath(file);
        }
		changeView("view/InitialLayout.fxml", "BettaReprodução", 1, null, primaryStage);
	}
	
	public void showCadastroLayout(Reproducao reproducao, Stage stage) {
		changeView("view/CadastroLayout.fxml", "Cadastro", 2, reproducao, stage);
	}

	public void showPesquisaLayout() {
		changeView("view/PesquisaLayout.fxml", "Pesquisa", 3, null, primaryStage);
	}
	
	public void showReproducaoOverviewLayout(Reproducao reproducao){
		changeView("view/OverviewLayout.fxml", "Visualização", 4, reproducao, primaryStage);
	}

	private void changeView(String fxmlFile, String title, Integer id, Reproducao reproducao, Stage stage) {
		try {
			// Load pesquisa layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(fxmlFile));
			AnchorPane novoLayout = (AnchorPane) loader.load();

			// Set pesquisa layout into the center of root layout.
			Scene scene = new Scene(novoLayout);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.centerOnScreen();
			stage.sizeToScene();
			stage.setMaximized(false);
			stage.show();
			
			// Give the controller access to the main app.
			switch (id) {
			case 1:
				InitialLayoutController controller;
				
				controller = loader.getController();
				controller.setMain(this);
				
				break;
			case 2:
				CadastroLayoutController controller2;
				
				controller2 = loader.getController();
				controller2.setReproducao(reproducao);
				controller2.setMain(this);
				controller2.setStage(stage);
				
				break;
			case 3:
				PesquisaLayoutController controller3;
				
				controller3 = loader.getController();
				controller3.setMain(this);
				
				break;
			case 4:
				ReproducaoOverviewController controller4 = loader.getController();
		        
				controller4.setReproducao(reproducao);
		        controller4.setMain(this);
		        
		        break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getReproducaoFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setReproducaoFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        
        if (file != null) {
            prefs.put("filePath", file.getPath());
            
            // Update the stage title.
            primaryStage.setTitle("BettaReprodução - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("BettaReprodução");
        }
    }
	
	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadReproducaoDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(ReproducaoListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        ReproducaoListWrapper wrapper = (ReproducaoListWrapper) um.unmarshal(file);

	        reproData.clear();
	        reproData.addAll(wrapper.getReproducoes());

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void saveReproducaoDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(ReproducaoListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        ReproducaoListWrapper wrapper = new ReproducaoListWrapper();
	        wrapper.setReproducoes(reproData);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	        System.out.println(e);
	    }
	}


	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}