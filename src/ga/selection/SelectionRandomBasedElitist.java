package ga.selection;

import ga.Population;

public class SelectionRandomBasedElitist extends Selection {

	private int numElitist = 1;// keeps n best individuals

	@Override
	public void select(Population population) {
		super.select(population);
		
		// keep 'n' best individuals for next generation
		for (int rank = 0; rank < numElitist; rank++) {
			plan.reserve(population.getIndividuals().get(rank));
		}
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			// select two individuals at random for breeding
			plan.add(population.getRandomIndividual(), population.getRandomIndividual());
		}
		
	}

}
