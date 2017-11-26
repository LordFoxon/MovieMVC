package model;

public class MovieSingleton {

	//need an arbitrary object to manage the concurrency lock in getInstanceMultiThread2
	private static final Object lock = new Object();

	private static volatile MovieSingleton singleton = null;
	public Movie movie = new Movie();
	
	private MovieSingleton() {
	}

	public synchronized static MovieSingleton getInstanceSingleThread() {
		if (singleton == null) {
			synchronized (lock) {
				if (singleton == null) {
					singleton = new MovieSingleton();
				}
			}
		}
		return singleton;
	}
	
}
