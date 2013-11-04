package ga;
import java.util.ArrayList;
import java.util.List;


public class InitializeDefault extends Initialize {
	
	/**
	 * Creates a population of a specified size with randomly generated individuals.
	 */
	public Population initializePopulation(int size, int chromosomeSize) {
		List<Individual> population = new ArrayList<Individual>();
		for (int individualIndex = 0; individualIndex < size; individualIndex++)
			population.add(getRandomIndividual(chromosomeSize));
		return new Population(population);
	}

	public Individual getRandomIndividual(int chromosomeSize) {
		return new Individual(chromosomeSize);
	}

}

