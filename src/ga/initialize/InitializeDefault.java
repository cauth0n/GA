package ga.initialize;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;


public class InitializeDefault extends Initialize {
	
	/**
	 * Creates a population of a specified size with randomly generated individuals.
	 */
	public Population initializePopulation(int size, int chromosomeSize) {
		List<Individual> population = new ArrayList<Individual>();
		for (int individualIndex = 0; individualIndex < size; individualIndex++) {
			Individual newIndividual = getRandomIndividual(chromosomeSize, individualIndex);
			population.add(newIndividual);
		}
		return new Population(population);
	}

	public Individual getRandomIndividual(int chromosomeSize, int seed) {
		return new Individual(chromosomeSize, seed);
	}

}

