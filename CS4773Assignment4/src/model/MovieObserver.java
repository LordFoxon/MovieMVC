package model;

import java.util.Observable;

public class MovieObserver extends Observable {

	public interface updateMovie {
		public void update();
	}

	public class ObserverDelegate extends Observable implements updateMovie {
		public void update() {
			System.out.println("About to notify observers");
			setChanged();
			notifyObservers();
		}
	}

}
