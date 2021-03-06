package ga.selection;

import ga.Population;

public class SelectionRankBasedElitist extends Selection {

	private int numElitist = 1;// keeps n best individuals

	@Override
	public void select(Population population) {
		super.select(population);
		
		// keep 'n' best individuals for next generation
		for (int rank = 0; rank < numElitist; rank++) {
			plan.reserve(population.getIndividuals().get(rank));
		}
		
		// select parents until target MatingPlan size is reached
		int rank = 0;
		while (plan.size() < returnSize) {
			if (rank >= population.size())
				rank = 1;
			// continue to move to less fit individuals when selecting from the population
			plan.add(population.getIndividuals().get(rank++), population.getIndividuals().get(rank++));
		}
		
	}

}
