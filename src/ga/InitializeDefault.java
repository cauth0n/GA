package ga;
import java.util.ArrayList;
import java.util.List;


public class InitializeDefault extends Initialize {
	
	/**
	 * Creates a population of a specified size with randomly generated individuals.
	 */
	public Population initializePopulation(int size) {
		List<Individual> population = new ArrayList<Individual>();
		for (int individualIndex = 0; individualIndex < size; individualIndex++)
			population.add(getRandomIndividual());
		return new Population(population);
	}

	public Individual getRandomIndividual() {
		// TODO: generate random individual
		return new Individual();
	}

}

