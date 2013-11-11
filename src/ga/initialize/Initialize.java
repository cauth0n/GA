package ga.initialize;

import ga.Population;

/**
 * Abstract class for initialization of population.
 */
public abstract class Initialize{
	
	/**
	 * Initializes the population of individuals.
	 * 
	 * @param size				The number of individuals to create in the population.
	 * @param chromosomeSize	The number of genes that each individual will contain.
	 * @return					The newly created population.
	 */
	public abstract Population initializePopulation(int size, int chromosomeSize);

}

