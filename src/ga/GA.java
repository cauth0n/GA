package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverNPoint;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeDefault;
import ga.mutation.Mutate;
import ga.mutation.MutateUniformDistribution;
import ga.selection.Selection;
import ga.selection.SelectionRankBasedExtremePreservation;

/**
 * Genetic Algorithm.
 */
public class GA {

	private Population population;
	private double mutationProbability;
	private Selection selection;
	private Fitness fitness;
	private Mutate mutate;
	private Crossover crossover;

	/**
	 * Constructs a new Genetic Algorithm class.
	 * 
	 * @param populationSize		The size of the population to use.
	 * @param chromosomeSize		The size of the chromosome for each individual (the number of variables being trained in network)
	 * @param mutationProbability	The probability of mutation for each individual during the generation
	 */
	public GA(int populationSize, int chromosomeSize, double mutationProbability) {
		this.mutationProbability = mutationProbability;
		// initialize genes for each individual
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize, chromosomeSize);
		// use default fitness calculation
		this.fitness = new FitnessDefault();
		// choose a selection method
		this.selection = new SelectionRankBasedExtremePreservation(this.fitness);
		// use a uniform distribution mutation
		this.mutate = new MutateUniformDistribution();
		// use N-Point crossover
		this.crossover = new CrossoverNPoint();
	}

	/**
	 * Runs a single generation of selection, crossover, and mutation.
	 */
	public void runGeneration() {
		
		// select from the population using selection method
		selection.select(population);
		// create children with crossover
		Population children = crossover.crossOver(population, selection.getMatingPlan());
		// mutate children with given probability
		children = mutate.mutate(children, mutationProbability);
		// continue into next generation
		population = children;

	}

	/**
	 * @return	The population of the algorithm.
	 */
	public Population getPopulation() {
		return population;
	}

}
