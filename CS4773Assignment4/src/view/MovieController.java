package view;

import java.net.URL;
import java.util.ResourceBundle;
import app.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Movie;
import model.MovieSingleton;
import javafx.fxml.Initializable;
import java.util.Observable;
import java.util.Observer;

public class MovieController implements Initializable, Observer{

	@FXML private TextField movieTitle;
	@FXML private TextField director;
	@FXML private TextField releaseYear;
	@FXML private TextField writer;
	@FXML private Label ratingText;
	@FXML private Slider ratingSlider;

	public MovieController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		releaseYear.textProperty().addListener(textFieldListener);
		ratingSlider.valueProperty().addListener(ratingSliderListener);
	}

	@Override
	public void update(Observable o, Object arg) {
		Movie movie = MovieSingleton.getInstanceSingleThread().movie;
		updateFields(movie);
	}

	public void updateFields(Movie movie) {
		clearFields();
		movieTitle.appendText(movie.getMovieTitle());
		director.appendText(movie.getDirector());
		if (movie.getReleaseYear() != 0)
			releaseYear.appendText(""+ movie.getReleaseYear());
		writer.appendText(movie.getWriter());
		ratingText.setText(""+movie.getRating());
		ratingSlider.setValue(movie.getRating());
	}
	
	public void clearFields() {
		releaseYear.clear();
		movieTitle.clear();
		director.clear();
		writer.clear();
	}

	@FXML
	void handleUserInput(KeyEvent event) {
		try {
			setMovieValues(event);
		} catch (NumberFormatException e) {
			Alerts.showError("Cannot do numerical conversion "+e.getMessage().toLowerCase());
		}
	}

	void setMovieValues(KeyEvent event) throws NumberFormatException{
		//TODO: Fix year deletion. If you delete the whole number, the last digit stays on the second view until another field is updated.
		if (event.getSource() == movieTitle)
			MovieSingleton.getInstanceSingleThread().movie.setMovieTitle(movieTitle.getText());
		if (event.getSource() == director)
			MovieSingleton.getInstanceSingleThread().movie.setDirector(director.getText());
		if (event.getSource() == releaseYear && !releaseYear.getText().equals(""))
			MovieSingleton.getInstanceSingleThread().movie.setReleaseYear(Integer.parseInt(releaseYear.getText()));
		else if(event.getSource() == releaseYear && releaseYear.getText().equals("")) {
			MovieSingleton.getInstanceSingleThread().movie.setReleaseYear(0);
		}
		if (event.getSource() == writer)
			MovieSingleton.getInstanceSingleThread().movie.setWriter(writer.getText());
	}
	
	@FXML
	void handleKeyInput(KeyEvent event) {
		if (event.getSource() == movieTitle)
			MovieSingleton.getInstanceSingleThread().movie.setMovieTitle(movieTitle.getText());
	}

	ChangeListener<String> textFieldListener = (observable, oldValue, newValue) -> {
		if (!newValue.matches("\\d*")) {
			releaseYear.setText(newValue.replaceAll("[^\\d]", ""));
			return;
		}
		if (newValue.isEmpty()) {
			releaseYear.setText("");
			return;
		}
	};

	ChangeListener<Number> ratingSliderListener = (observable, oldValue, newValue) -> {
		ratingText.setText(String.valueOf((int) ratingSlider.getValue()));
		MovieSingleton.getInstanceSingleThread().movie.setRating(Integer.parseInt(ratingText.getText()));
	};
}
