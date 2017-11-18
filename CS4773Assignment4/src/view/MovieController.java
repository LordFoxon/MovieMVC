package view;

import java.net.URL;
import java.util.ResourceBundle;

import app.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.Movie;
import model.MovieSingleton;
import javafx.fxml.Initializable;

import java.util.Observable;
import java.util.Observer;
public class MovieController implements Initializable, Observer{

    @FXML
    private TextField movieTitle;

    @FXML
    private TextField director;

    @FXML
    private TextField releaseYear;

    @FXML
    private TextField writer;

    @FXML
    private Label ratingText;

    @FXML
    private Slider ratingSlider;

    public MovieController() {
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		releaseYear.textProperty().addListener(textFieldListener);

	}
	
	ChangeListener<String> textFieldListener = (observable, oldValue, newValue) -> {
	    if (!newValue.matches("\\d*")) {
	    		releaseYear.setText(newValue.replaceAll("[^\\d]", ""));
	    }
	    if(!newValue.matches("^[0-9]+$")){
			Alerts.showError("Release Year must be a numeric value");
	    }

	    if (newValue.isEmpty())
	    		releaseYear.setText("");

	};
	
	@FXML
	void handleUserInput(ActionEvent event) throws Exception {
		if (event.getSource() == movieTitle) {
			MovieSingleton.getInstanceSingleThread().movie.setMovieTitle(movieTitle.getText());
		}
		if (event.getSource() == director) {
			MovieSingleton.getInstanceSingleThread().movie.setDirector(director.getText());
		}
		if (event.getSource() == releaseYear && !releaseYear.getText().equals("")) {
			MovieSingleton.getInstanceSingleThread().movie.setReleaseYear(Integer.parseInt(releaseYear.getText()));
		}
		if (event.getSource() == writer) {
			MovieSingleton.getInstanceSingleThread().movie.setWriter(writer.getText());
		}
	}

	@FXML 
	void handleSliderAction(ActionEvent event){
		System.out.println("SLider");

		if(event.getSource() == ratingSlider) {
			System.out.println("SLider");
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Hello, we have been notified");
		Movie movie = MovieSingleton.getInstanceSingleThread().movie;
		updateFields(movie);
	}
	
	public void updateFields(Movie movie) {
		movieTitle.setText(movie.getMovieTitle());
		director.setText(movie.getDirector());
		releaseYear.setText(""+ movie.getReleaseYear());
		writer.setText(movie.getWriter());
	}

}
