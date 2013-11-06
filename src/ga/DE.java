package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverNPoint;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeDefault;
import ga.mutation.Mutate;
import ga.mutation.MutateTrialVector;

import java.util.ArrayList;
import java.util.List;

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
	public DE(int populationSize, int chromosomeSize, double beta, double probability) {
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize, chromosomeSize);
		this.beta = beta;
		this.probability = probability;
		this.fitness = new FitnessDefault();
		this.mutate = new MutateTrialVector();
		this.crossover = new CrossoverNPoint();
	}

	public void runGeneration() {
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		// loop through entire current population as parents
		for (Individual parent : population.getPopulation()) {
			// evaluate fitness of parent
			
			//TODO
			//double parentFitness = fitness.getIndividualFitness(parent);
			
			
			// create a trial vector
			Individual trial = mutate.mutate(parent, population);
			// perform crossover with parent and trial vector
			List<Individual> child = crossover.crossOverOneChild(parent, trial);
			// evaluate fitness of child
			
			//TODO
			//double childFitness = fitness.getIndividualFitness(child);
			
			
			// have parent and child compete for space in new population
//			if (childFitness > parentFitness)
//				newPopulation.add(child);
//			else
//				newPopulation.add(parent);
		}
		population = new Population(newPopulation);
	}
	
	public Population getPopulation() {
		return population;
	}

}
