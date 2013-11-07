package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.ArrayList;
import java.util.List;

public class SelectionTournament extends Selection {
	
	private int tournamentSize;
	
	public SelectionTournament(Fitness fitness, int tournamentSize) {
		super(fitness);
		this.tournamentSize = tournamentSize;
	}
	
	/**
	 * Performs a proportionate based selection for a
	 * specified number of candidates. The top two individuals
	 * are then chosen by rank to continue on.
	 * 
	 * Consecutive calls may return same parents.
	 */
	public void select(Population population) {
		super.select(population);
		if (tournamentSize > population.size())
			throw new IllegalArgumentException("Tournament size must be smaller than entire population.");
		
		// TODO: fix to not remove elements, just used sorted population list
		
		List<Individual> candidates = new ArrayList<Individual>();
		
		// evaluate fitness for every individual in population and find the sum
		double sum = fitness.getPopulationFitnessSum(population);
		
		// create a mating pair for every element in the population (will use single child crossover)
		while (plan.size() < population.size()) {
			// select the two parents weighted by fitness
			for (int candidate = 0; candidate < tournamentSize; candidate++)
				candidates.add(selectParentProportionate(sum));
			
			// create tournament population from tournaments
			Population tournament = new Population(candidates);
			
			// choose top ranking individuals
			Individual parent1 = tournament.getIndividuals().get(0);
			Individual parent2 = tournament.getIndividuals().get(1);
			
			plan.add(parent1, parent2);
		}
		
	}

}

