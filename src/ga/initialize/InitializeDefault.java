package ga.initialize;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;


public class InitializeDefault extends Initialize {
	
	/**
	 * Initializes the population of individuals.
	 * Each individual is assigned a set of genes with random values.
	 * 
	 * @param size				The number of individuals to create in the population.
	 * @param chromosomeSize	The number of genes that each individual will contain.
	 * @return					The newly created population.
	 */
	public Population initializePopulation(int size, int chromosomeSize) {
		List<Individual> population = new ArrayList<Individual>();
		// create individuals until 'size' is reached
		for (int individualIndex = 0; individualIndex < size; individualIndex++) {
			// create a new random individual
			Individual newIndividual = new Individual(chromosomeSize);
			// add created individual to the list of individuals
			population.add(newIndividual);
		}
		// return population created from list of individuals
		return new Population(population);
	}

}

