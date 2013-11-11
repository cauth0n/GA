package ga.selection;

import ga.Population;

public class SelectionRankBased extends Selection {
	
	/**
	 * Chooses individuals based on highest fitness value first, 
	 * and this process continues down the line so that individuals
	 * of comparable fitness breed with each other.
	 */
	public void select(Population population) {
		super.select(population);
		
		// select parents until target MatingPlan size is reached
		int rank = 0;
		while (plan.size() < returnSize) {
			if (rank >= population.size() - 1)
				rank = 1;
			// continue to move to less fit individuals when selecting from the population
			plan.add(population.getIndividuals().get(rank++), population.getIndividuals().get(rank++));
		}
	}
	
}

