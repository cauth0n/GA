package ga.selection;

import ga.Population;

public class SelectionRandom extends Selection {

	/**
	 * Selects two parents at random.
	 */
	public void select(Population population) {
		super.select(population);
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			// select two random parents from the population as mating pairs
			plan.add(population.getRandomIndividual(), population.getRandomIndividual());
		}
		
	}

}
