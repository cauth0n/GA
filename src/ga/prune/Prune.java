package ga.prune;

import ga.Population;

/**
 * Abstract Prune class.
 */
public abstract class Prune {
	
	/**
	 * Prunes a population from its current size down to a specified new size.
	 * 
	 * @param population	The population to prune.
	 * @param newSize		The new size that the population should be pruned to.
	 * @return				The resulting population from the prune operation.
	 */
	public abstract Population prune(Population population, int newSize);
	
}
