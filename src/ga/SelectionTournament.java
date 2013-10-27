package ga;

import java.util.ArrayList;
import java.util.List;

public class SelectionTournament extends Selection {
	
	private int tournamentSize;
	
	public SelectionTournament(Population population, Fitness fitness, int tournamentSize) {
		super(population, fitness);
		if (tournamentSize > population.size())
			throw new IllegalArgumentException("Tournament size must be smaller than entire population.");
		this.tournamentSize = tournamentSize;
	}
	
	/**
	 * Performs a proportionate based selection for a
	 * specified number of candidates. The top two individuals
	 * are then chosen by rank to continue on.
	 * 
	 * Consecutive calls may return same parents.
	 */
	public List<Individual> select() {
		
		List<Individual> parents = new ArrayList<Individual>();
		List<Individual> candidates = new ArrayList<Individual>();
		
		// evaluate fitness for every individual in population and find the sum
		population.evaluateFitness(fitness);
		double sum = fitness.getPopulationFitnessSum(population);
		
		// select the two parents weighted by fitness
		for (int candidate = 0; candidate < tournamentSize; candidate++)
			candidates.add(selectParentProportionate(sum));
		
		// create tournament population from tournaments
		Population tournament = new Population(candidates);
		
		// choose best ranking individual as parent 1
		Individual parent1 = tournament.getMostFit();
		parents.add(parent1);
		
		// remove parent 1 from population so it is not chosen every time
		tournament.remove(parent1);
		
		// choose next best individual for parent 2
		Individual parent2 = tournament.getMostFit();
		parents.add(parent2);
		
		return parents;
	}

}

