package ga;

import java.util.ArrayList;

public class DE {
	
	Population population;
	double beta;
	double probability;
	Fitness fitness;
	Mutate mutate;
	Crossover crossover;
	
	/**
	 * Initializes parameters.
	 * 
	 * @param populationSize	The size of the population to construct
	 * @param beta				The beta value for the algorithm (empirical studies suggest 0.5)
	 * @param probability		The probability for the algorithm (higher values mean more diverse offspring)
	 */
	public DE(int populationSize, double beta, double probability) {
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize);
		this.beta = beta;
		this.probability = probability;
		this.fitness = new FitnessDefault();
		this.mutate = new MutateTrialVector();
		this.crossover = new CrossoverNPointOneChild();
	}
	
	/**
	 * Runs the differential evolution algorithm over all time steps.
	 */
	public void run() {
		while (true) {
			processGeneration();
		}
	}

	private void processGeneration() {
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		// loop through entire current population as parents
		for (Individual parent : population.getPopulation()) {
			// evaluate fitness of parent
			double parentFitness = fitness.getIndividualFitness(parent);
			// create a trial vector
			Individual trial = mutate.mutate(parent, population);
			// perform crossover with parent and trial vector
			Individual child = crossover.crossOver(parent, trial).get(0);
			// evaluate fitness of child
			double childFitness = fitness.getIndividualFitness(child);
			// have parent and child compete for space in new population
			if (childFitness > parentFitness)
				newPopulation.add(child);
			else
				newPopulation.add(parent);
		}
		population = new Population(newPopulation);
	}

}
