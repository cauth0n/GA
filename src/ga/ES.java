package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverUniform;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeWithStrategyParameters;
import ga.mutation.Mutate;
import ga.mutation.MutateNormalDistribution;
import ga.selection.Selection;
import ga.selection.SelectionRandom;

/**
 * Evolution Strategy Algorithm.
 */
public class ES {

	private Population population;
	private double mutationProbability;
	private Selection selection;
	private Fitness fitness;
	private Mutate mutate;
	private Crossover crossover;
	
	private int mu;
	private int lambda;
	private String type = "+";

	/**
	 * Constructs a new Evolution Strategy algorithm class.
	 * 
	 * @param mu					The number of members in the population that are used for breeding.
	 * @param lambda				The number of children that are produced during each generation.
	 * @param chromosomeSize		The size of the chromosome for each individual (the number of variables being trained in network)
	 * @param mutationProbability	The probability of mutation for each individual during the generation
	 */
	public ES(int mu, int lambda, int chromosomeSize, double mutationProbability) {
		this.mu = mu;
		this.lambda = lambda;
		this.mutationProbability = mutationProbability;
		// verify the params are valid
		checkParams();
		// initialize connection weights and strategy parameters for mu individuals in the population
		Initialize init = new InitializeWithStrategyParameters();
		this.population = init.initializePopulation(mu, chromosomeSize);
		// set fitness as default
		this.fitness = new FitnessDefault();
		// use random selection method that returns lambda individuals
		this.selection = new SelectionRandom(this.fitness);
		this.selection.setReturnSize(lambda);
		// mutate using normal distribution
		this.mutate = new MutateNormalDistribution();
		// use uniform crossover
		this.crossover = new CrossoverUniform();
	}

	/**
	 * Runs a single generation of selection, crossover, and mutation.
	 */
	public void runGeneration() {
		
		// choose p >= 2 parents for each lambda
		selection.select(population);
		
		// create 1 offspring through crossover
		Population children = crossover.crossOver(population, selection.getMatingPlan());
		
		// mutate the offspring (features & strategy parameters)
		children = mutate.mutate(children, mutationProbability);
		
		// construct pool of individuals based on algorithm type
		if (this.type.equals("+")) {
			population.add(children);
		} else if (this.type.equals(",")) {
			population = children;
		}

	}

	/**
	 * @return	The population of the algorithm.
	 */
	public Population getPopulation() {
		return population;
	}
	
	/**
	 * @param population	The population that should be used by the algorithm.
	 */
	public void setPopulation(Population population) {
		this.population = population;
	}
	
	/**
	 * Set type of algorithm as "(mu + lambda)"
	 */
	public void setTypePlus() {
		this.type = "+";
		checkParams();
	}
	
	/**
	 * Set type of algorithm as "(mu , lambda)"
	 */
	public void setTypeComma() {
		this.type = ",";
		checkParams();
	}
	
	/**
	 * Verify that the parameters meet the constraints of the algorithm.
	 */
	private void checkParams() {
		// if (mu + lambda), 0 < mu <= lambda
		if (type.equals("+")) {
			if (mu < 1 || mu > lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu <= lambda.");
			}
		// if (mu , lambda), 0 < mu < lambda
		} else if (type.equals(",")) {
			if (mu < 1 || mu >= lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu < lambda.");
			}
		// type must be either "+" or ","
		} else {
			throw new IllegalArgumentException("Invalid type.");
		}
	}
	
	/**
	 * The string representation of the algorithm in "(mu * lambda) - ES" format.
	 */
	public String toString() {
		return "("+mu+" "+type+" "+lambda+") - ES";
	}

}
