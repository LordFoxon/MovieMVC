package model;

import model.MovieObserver.ObserverDelegate;
import model.MovieObserver.updateMovie;

public class Movie implements updateMovie{
	private String movieTitle;
	private int releaseYear;
	private String director;
	private String writer;
	private int rating;
	
	//Need to rename these and simplify is possible
	MovieObserver observer = new MovieObserver();
	MovieObserver.ObserverDelegate delegate = observer.new ObserverDelegate();
	updateMovie uM;

	public Movie(String title, int releaseYear, String director, String writer, int rating) {
		this.movieTitle = title;
		this.releaseYear = releaseYear;
		this.director = director;
		this.writer = writer;
		this.rating = rating;
	}
	
	public Movie() {
		
	}

	public void setDelegate() {
		uM = delegate;
	}

	@Override
	public void update() {
		uM.update();
		// TODO Auto-generated method stub
	}
	
	public ObserverDelegate getObserver() {
		return delegate;
	}
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
		update();
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
		update();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
		update();
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
		update();
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
