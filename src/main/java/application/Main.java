package application;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.model.Reproducao;
import application.model.ReproducaoListWrapper;
import application.view.InitialLayoutController;
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
		this.primaryStage.setTitle("BetaReprodução");
		showInitialLayout();
	}

	public void showInitialLayout() {
		try {
			// Load initial layout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/InitialLayout.fxml"));
			AnchorPane initialLayout = (AnchorPane) loader.load();
			
			// Change the scene to initialLayout
			Scene scene = new Scene(initialLayout);
			primaryStage.setTitle("Início");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			InitialLayoutController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = getReproducaoFilePath();
        if (file != null) {
            loadReproducaoDataFromFile(file);
        }
	}

	public void showOverviewLayout() {
//		try {
//			// Load person overview.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Main.class.getResource("view/OverviewLayout.fxml"));
//			AnchorPane overView = (AnchorPane) loader.load();
//
//			// Give the controller access to the main app.
//			ReproducaoOverviewController controller = loader.getController();
//			controller.setMain(this);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

	        // Save the file path to the registry.
	        setReproducaoFilePath(file);

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

	        // Save the file path to the registry.
	        setReproducaoFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
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
