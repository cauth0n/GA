package ga.selection;

import ga.Individual;
import ga.Population;

public class SelectionRankBasedDimishingReturn extends Selection {
	
	int numElitist = 1;
	double portion = 0.6;

	/**
	 * Selects individuals for breeding based on an
	 * 'alpha-male' type system, where the most fit individual
	 * breeds with the most parters, the second most fit with the
	 * second most individuals, and so on.
	 */
	public void select(Population population) {
		super.select(population);
		
		// keep 'n' best individuals for next generation
		for (int rank = 0; rank < numElitist; rank++) {
			plan.reserve(population.getIndividuals().get(rank));
		}
		
		// determine how many individuals the most fit will breed with
		// and create some set up information to iterate through population
		int alphaIndex = 0;
		Individual alpha = population.getIndividuals().get(alphaIndex);
		int toBreed = (int) (returnSize * portion);
		int stopIndex = toBreed;
		
		// select parents until target MatingPlan size is reached
		while (plan.size() < returnSize) {
			// if alpha has breed with enough individuals, switch to next best alpha
			if (plan.size() >= stopIndex) {
				toBreed *= portion;
				stopIndex = plan.size() + toBreed;
				alphaIndex++;
				if (alphaIndex == population.size())
					alphaIndex = 0;
				alpha = population.getIndividuals().get(alphaIndex);
			}
			// allow alpha to breed with a random individual in the population
			plan.add(alpha, population.getRandomIndividual());
		}
	}

}
