package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverNPoint;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeDefault;
import ga.mutation.Mutate;
import ga.mutation.MutateTrialVector;
import ga.selection.MatingPlan;
import ga.trialVector.TrialVector;
import ga.trialVector.TrialVectorBest;

import java.util.ArrayList;
import java.util.List;

import neural_net.Network;
import driver.DataPoint;

/**
 * Differential Evolution Algorithm.
 */
public class DE {

	private Population population;
	private double beta;
	private Fitness fitness;
	private Crossover crossover;

	/**
	 * Initializes parameters.
	 * 
	 * @param populationSize
	 *            The size of the population to construct
	 * @param beta
	 *            The beta value for the algorithm (empirical studies
	 *            suggest 0.5)
	 */
	public DE(int populationSize, int chromosomeSize, double beta) {
		// initialize population to correct size
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize, chromosomeSize);
		// set the learning rate
		this.beta = beta;
		// set the fitness and crossover methods that will be used
		this.fitness = new FitnessDefault();
		this.crossover = new CrossoverNPoint();
	}

	/**
	 * Runs a single generation for the differential equation algorithm.
	 * 
	 * @param neuralNetwork	The neural network that will be trained using the DE.
	 * @param trainSet		The set of training data that will be used.			
	 */
	public void runGeneration(Network neuralNetwork, List<DataPoint> trainSet) {
		
		// create a new empty population and mating plan
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		MatingPlan plan = new MatingPlan();

		// loop through every individual in the current population
		for (Individual individual : population.getIndividuals()) {
			
			// get the best individual in the population
			Individual bestInPop = population.getMostFit();

			// get a random individual that has not already been chosen
			Individual two = population.getRandomIndividual();
			while (!two.geneticallyEquals(bestInPop) && !two.geneticallyEquals(individual)) {
				two = population.getRandomIndividual();
			}
			
			// get another random individual that has not already been chosen
			Individual three = population.getRandomIndividual();
			while (!three.geneticallyEquals(bestInPop) && !three.geneticallyEquals(two) && !three.geneticallyEquals(individual)) {
				three = population.getRandomIndividual();
			}
			
			// create a trial vector using the best individual and 2 random individuals
			TrialVector trialVector = new TrialVectorBest(bestInPop, two, three, beta);
			
			// set the current individual to breed with the new trial vector that has been created
			plan.add(individual, trialVector.getTrialVector());
		}

		// need to make sure the individual objects in the population
		// are preserved.
		Population newPop = crossover.crossOver(population, plan);

		fitness.calculateFitness(newPop, neuralNetwork, trainSet);

		// loop through the new and old populations
		for (int i = 0; i < population.size(); i++) {
			
			// get a member from the old population and 
			// the corresponding member from the new population
			Individual oldIndividual = population.getIndividuals().get(i);
			Individual newIndividual = newPop.getIndividuals().get(i);

			// add the better of the two individuals to the new population
			if (newIndividual.getFitness() > oldIndividual.getFitness()) {
				newPopulation.add(newIndividual);
			} else {
				newPopulation.add(oldIndividual);
			}

		}

		// move on to the next generation
		population = new Population(newPopulation);
	}

	/**
	 * @return	The current population of the algorithm.
	 */
	public Population getPopulation() {
		return population;
	}

}
