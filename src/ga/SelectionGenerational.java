package ga;

public class SelectionGenerational extends Selection {
	
	public Individual select(Population population) {
		Individual individual = population.getPopulation().get(0);
		// TODO: selection algorithm
		return individual;
	}

}

