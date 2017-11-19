package model;

import java.util.Observable;


public class MovieObserver{

	ObserverDelegate delegate = new ObserverDelegate();

	public class ObserverDelegate extends Observable{
		public void update() {
			setChanged();
			notifyObservers();
		}
	}

}
