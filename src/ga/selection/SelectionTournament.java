package ga.selection;

import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;

public class SelectionTournament extends Selection {
	
	private int tournamentSize;
	
	public SelectionTournament(int tournamentSize) {
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
		
		// create a list of individuals that will be the 'tournament'
		List<Individual> candidates;
		
		// evaluate fitness for every individual in population and find the sum
		double sum = population.getFitness();
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			
			// select 'n' candidates weighted by fitness
			candidates = new ArrayList<Individual>(tournamentSize);
			for (int candidate = 0; candidate < tournamentSize; candidate++)
				candidates.add(selectParentProportionate(sum));
			
			// create tournament population from tournaments
			Population tournament = new Population(candidates);
			
			// individuals in tournament 'compete'
			tournament.sortPopulationByFitness();
			
			// choose top ranking individuals
			Individual parent1 = tournament.getIndividuals().get(0);
			Individual parent2 = tournament.getIndividuals().get(1);
			
			// add winners to plan
			plan.add(parent1, parent2);
		}
		
	}

}

