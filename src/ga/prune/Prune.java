package ga.prune;

import ga.Population;

public abstract class Prune {

	public Prune() {
		
	}
	
	public abstract Population prune(Population population, int newSize);
	
}
