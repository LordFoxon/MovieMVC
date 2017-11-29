package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MovieObserver;
import model.MovieSingleton;
import view.MovieController;

public class AppMain extends Application {
	MovieObserver movieDelegate;
	public AppMain() {
	}
	
	public static void main(String[] args) {
		launch(args);
	}


	public void createMovieView(int viewX, int viewY) throws IOException {
		Stage stage = new Stage();
		MovieController controller = new MovieController();
		FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("MovieView.fxml"));
		loader.setController(controller);
		MovieSingleton.getInstanceSingleThread().movie.getObserver().addObserver(controller);

		Parent pane = loader.load();
		stage.setScene(new Scene(pane, 400, 300));
		stage.setTitle("CS 4773 Assignment 4 Movie View");
		stage.setX(viewX);
		stage.setY(viewY);
        stage.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MovieSingleton.getInstanceSingleThread();
		createMovieView(50, 100);
		
		createMovieView(460, 100);
	}
}
