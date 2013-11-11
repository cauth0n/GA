package ga.prune;

import ga.Individual;
import ga.Population;

import java.util.List;

/**
 * Implemented Default Prune class.
 */
public class PruneDefault extends Prune {
	
	/**
	 * Prunes a population from its current size down to a specified new size.
	 * New population is simply the individuals with the highest fitness.
	 * 
	 * @param population	The population to prune.
	 * @param newSize		The new size that the population should be pruned to.
	 * @return				The resulting population from the prune operation.
	 */
	public Population prune(Population population, int newSize) {
		// calculate the number of individuals that need to be removed
		int toRemove = (population.size() - newSize);
		// create a copy of the existing population
		Population newPopulation = population.copy();
		// convert population to a list of individuals
		List<Individual> list = newPopulation.getIndividuals();
		// remove from the bottom of the list (least fit), until the list is the correct size
		for (int pruneIndex = 0; pruneIndex < toRemove; pruneIndex++) {
			list.remove(list.size() - 1);
		}
		// return new population
		return newPopulation;
	}
	
}
