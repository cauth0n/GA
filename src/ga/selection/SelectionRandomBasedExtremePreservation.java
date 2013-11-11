package ga.selection;

import ga.Individual;
import ga.Population;

public class SelectionRandomBasedExtremePreservation extends Selection {

	public void select(Population population) {
		super.select(population);
		
		// best of population
		Individual best1 = population.getMostFit(1);
		Individual best2 = population.getMostFit(2);
		Individual best3 = population.getMostFit(3);
		// worst of population
		Individual worst1 = population.getLeastFit(1);
		Individual worst2 = population.getLeastFit(2);
		Individual worst3 = population.getLeastFit(3);
		
		// add custom pairs, holding out extremes
		plan.reserve(best1);
		plan.reserve(best2);
		plan.reserve(best3);
		plan.reserve(worst1);
		//plan.add(worst2, worst2);
		//plan.add(worst3, worst3);
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			// continue to move to less fit individuals when selecting from the population
			plan.add(population.getRandomIndividual(), population.getRandomIndividual());
		}
		
	}

}
