package ga;

public class SelectionTournament extends Selection {
	
	public Individual select(Population population) {
		Individual individual = population.getPopulation().get(0);
		// TODO: selection algorithm
		return individual;
	}

}

