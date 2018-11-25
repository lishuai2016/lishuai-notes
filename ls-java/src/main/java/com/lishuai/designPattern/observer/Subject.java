package com.lishuai.designPattern.observer;

public interface Subject {

	void registerObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObservers();
}
