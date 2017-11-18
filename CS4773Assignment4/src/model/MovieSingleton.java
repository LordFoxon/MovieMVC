package model;

public class MovieSingleton {

	private static  MovieSingleton singleton = null;
	public Movie movie = new Movie();
	
	private MovieSingleton() {
	}

	public static MovieSingleton getInstanceSingleThread() {
		if(singleton == null) {
			singleton = new MovieSingleton();
		}

		return singleton;
	}
	
}
