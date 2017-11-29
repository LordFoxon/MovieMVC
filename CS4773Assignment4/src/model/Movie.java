package model;

import model.MovieObserver.ObserverDelegate;

public class Movie {
	private String movieTitle;
	private int releaseYear;
	private String director;
	private String writer;
	private int rating;
	
	MovieObserver observer = new MovieObserver();

	public Movie(String title, int releaseYear, String director, String writer, int rating) {
		this.movieTitle = title;
		this.releaseYear = releaseYear;
		this.director = director;
		this.writer = writer;
		this.rating = rating;
	}
	
	public Movie() {
		this.movieTitle = "";
		this.director = "";
		this.writer = "";
	}

	
	public ObserverDelegate getObserver() {
		return observer.delegate;
	}
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
		observer.delegate.update();
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
		observer.delegate.update();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
		observer.delegate.update();
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
		observer.delegate.update();
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
		observer.delegate.update();
	}
}
