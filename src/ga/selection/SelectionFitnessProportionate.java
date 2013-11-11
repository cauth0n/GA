package ga.selection;

import ga.Population;

public class SelectionFitnessProportionate extends Selection {
	
	/**
	 * Select each parent from the population to breed using
	 * a roulette-wheel system based on fitness.
	 * 
	 * No reserved individuals.
	 */
	public void select(Population population) {
		super.select(population);
		
		// evaluate fitness for every individual in population and find the sum
		double sum = population.getFitness();
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			// select the two parents weighted by fitness
			plan.add(selectParentProportionate(sum), selectParentProportionate(sum));
		}
		
	}

}

