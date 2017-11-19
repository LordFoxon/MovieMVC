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
		movieTitle.setText(movie.getMovieTitle());
		director.setText(movie.getDirector());
		if (movie.getReleaseYear() != 0)
			releaseYear.setText(""+ movie.getReleaseYear());
		writer.setText(movie.getWriter());
		ratingText.setText(""+movie.getRating());
		ratingSlider.setValue(movie.getRating());
	}

	@FXML
	void handleUserInput(ActionEvent event) {
		try {
			setMovieValues(event);
		}catch (NumberFormatException e)
		{
			Alerts.showError("Cannot do numerical conversion "+e.getMessage().toLowerCase());
		}
	}

	void setMovieValues(ActionEvent event) throws NumberFormatException{
		if (event.getSource() == movieTitle)
			MovieSingleton.getInstanceSingleThread().movie.setMovieTitle(movieTitle.getText());
		if (event.getSource() == director)
			MovieSingleton.getInstanceSingleThread().movie.setDirector(director.getText());
		if (event.getSource() == releaseYear && !releaseYear.getText().equals(""))
			MovieSingleton.getInstanceSingleThread().movie.setReleaseYear(Integer.parseInt(releaseYear.getText()));
		if (event.getSource() == writer)
			MovieSingleton.getInstanceSingleThread().movie.setWriter(writer.getText());
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
