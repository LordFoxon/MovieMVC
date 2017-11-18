package app;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public static void showError(String errorMessage) {
		try {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Value Entered");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		} catch(ExceptionInInitializerError e) {
			System.err.println("ERROR: JavaFX not initialized in test");
		} catch(NoClassDefFoundError e) {
			System.err.println("ERROR: JavaFX not initialized in test");
		}
	}
}



